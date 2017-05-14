/**
 * $Id: GraphEditor.java,v 1.3 2014/02/08 14:05:58 gaudenz Exp $ Copyright (c)
 * 2006-2012, JGraph Ltd
 */
package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.w3c.dom.Document;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.examples.swing.editor.FunctionBar;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.util.myConstants;

public class Main extends BasicGraphEditor {

    /**
     *
     */
    private static final long serialVersionUID = -4601740824088314699L;

    /**
     * Holds the shared number formatter.
     *
     * @see NumberFormat#getInstance()
     */
    public static final NumberFormat numberFormat = NumberFormat.getInstance();

    /**
     * Holds the URL for the icon to be used as a handle for creating new
     * connections. This is currently unused.
     */
    public static URL url = null;

    // GraphEditor.class.getResource("/com/mxgraph/examples/swing/images/connector.gif");
    public Main() {
        this("知识模块建模系统", new CustomGraphComponent(new CustomGraph()));
    }

    /**
     *
     */
    public Main(String appTitle, mxGraphComponent component) {
        super(appTitle, component);

        myConstants.graphComponent = graphComponent;

        final mxGraph graph = graphComponent.getGraph();

        // 去掉网格
        graphComponent.setGridVisible(false);
        // 画布显示pageLayout
        graphComponent.setPageVisible(false);

        JSplitPane outer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        outer.add(new MainOne());
        outer.setOneTouchExpandable(true);
        outer.setDividerLocation(220);
        outer.setDividerSize(6);
        outer.setBorder(null);

        statusBar = createStatusBar();

        // Display some useful information about repaint events
        installRepaintListener();
        setLayout(new BorderLayout());
        add(new MainOne(), BorderLayout.CENTER);

        // Creates the shapes palette
        EditorPalette shapesPalette = insertPalette(mxResources.get("shapes"));

        // Sets the edge template to be used for creating new edges if an edge
        // is clicked in the shape palette
        // 当edge被点击时，为新建一个edge准备edge template
        shapesPalette.addListener(mxEvent.SELECT, new mxIEventListener() {
            public void invoke(Object sender, mxEventObject evt) {
                Object tmp = evt.getProperty("transferable");

                if (tmp instanceof mxGraphTransferable) {
                    mxGraphTransferable t = (mxGraphTransferable) tmp;
                    Object cell = t.getCells()[0];

                    if (graph.getModel().isEdge(cell)) {
                        ((CustomGraph) graph).setEdgeTemplate(cell);
                    }
                }
            }

        });
    }

    /**
     *
     */
    public static class CustomGraphComponent extends mxGraphComponent {

        private static final long serialVersionUID = -6833603133512882012L;

        public CustomGraphComponent(mxGraph graph) {
            super(graph);
            // Sets switches typically used in an editor
            setPageVisible(true);
//            setGridVisible(true);
            // getConnectionHandler().setCreateTarget(true);
            graph.setCellsSelectable(false);// 设置主界面中图元能否选中

            // Loads the defalt stylesheet from an external file
            mxCodec codec = new mxCodec();
            Document doc = mxUtils.loadDocument(
                    Main.class.getResource("/com/mxgraph/examples/swing/resources/default-style.xml").toString());
            codec.decode(doc.getDocumentElement(), graph.getStylesheet());

            getViewport().setOpaque(false);
        }

        /**
         * Overrides drop behaviour to set the cell style if the target is not a
         * valid drop target and the cells are of the same type (eg. both
         * vertices or both edges).
         */
        public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location) {
            if (target == null && cells.length == 1 && location != null) {
                target = getCellAt(location.x, location.y);

                if (target instanceof mxICell && cells[0] instanceof mxICell) {
                    mxICell targetCell = (mxICell) target;
                    mxICell dropCell = (mxICell) cells[0];

                    if (targetCell.isVertex() == dropCell.isVertex() || targetCell.isEdge() == dropCell.isEdge()) {
                        mxIGraphModel model = graph.getModel();
                        model.setStyle(target, model.getStyle(cells[0]));
                        graph.setSelectionCell(target);

                        return null;
                    }
                }
            }

            return super.importCells(cells, dx, dy, target, location);
        }

    }

    /**
     * A graph that creates new edges from a given template edge.
     */
    public static class CustomGraph extends mxGraph {

        /**
         * Holds the edge to be used as a template for inserting new edges.
         */
        protected Object edgeTemplate;

        /**
         * Custom graph that defines the alternate edge style to be used when
         * the middle control point of edges is double clicked (flipped).
         */
        public CustomGraph() {
            setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
        }

        /**
         * Sets the edge template to be used to inserting edges.
         */
        public void setEdgeTemplate(Object template) {
            edgeTemplate = template;
        }

        /**
         * Prints out some useful information about the cell in the tooltip.
         */
        public String getToolTipForCell(Object cell) {
            String tip = "<html>";
            mxGeometry geo = getModel().getGeometry(cell);
            mxCellState state = getView().getState(cell);

            if (getModel().isEdge(cell)) {
                tip += "points={";

                if (geo != null) {
                    List<mxPoint> points = geo.getPoints();

                    if (points != null) {
                        Iterator<mxPoint> it = points.iterator();

                        while (it.hasNext()) {
                            mxPoint point = it.next();
                            tip += "[x=" + numberFormat.format(point.getX()) + ",y=" + numberFormat.format(point.getY())
                                    + "],";
                        }

                        tip = tip.substring(0, tip.length() - 1);
                    }
                }

                tip += "}<br>";
                tip += "absPoints={";

                if (state != null) {

                    for (int i = 0; i < state.getAbsolutePointCount(); i++) {
                        mxPoint point = state.getAbsolutePoint(i);
                        tip += "[x=" + numberFormat.format(point.getX()) + ",y=" + numberFormat.format(point.getY())
                                + "],";
                    }

                    tip = tip.substring(0, tip.length() - 1);
                }

                tip += "}";
            } else {
                tip += "geo=[";

                if (geo != null) {
                    tip += "x=" + numberFormat.format(geo.getX()) + ",y=" + numberFormat.format(geo.getY()) + ",width="
                            + numberFormat.format(geo.getWidth()) + ",height=" + numberFormat.format(geo.getHeight());
                }

                tip += "]<br>";
                tip += "state=[";

                if (state != null) {
                    tip += "x=" + numberFormat.format(state.getX()) + ",y=" + numberFormat.format(state.getY())
                            + ",width=" + numberFormat.format(state.getWidth()) + ",height="
                            + numberFormat.format(state.getHeight());
                }

                tip += "]";
            }

            mxPoint trans = getView().getTranslate();

            tip += "<br>scale=" + numberFormat.format(getView().getScale()) + ", translate=[x="
                    + numberFormat.format(trans.getX()) + ",y=" + numberFormat.format(trans.getY()) + "]";
            tip += "</html>";

            return tip;
        }

        /**
         * Overrides the method to use the currently selected edge template for
         * new edges.
         *
         * @param graph
         * @param parent
         * @param id
         * @param value
         * @param source
         * @param target
         * @param style
         * @return
         */
        public Object createEdge(Object parent, String id, Object value, Object source, Object target, String style) {
            if (edgeTemplate != null) {
                mxCell edge = (mxCell) cloneCells(new Object[]{edgeTemplate})[0];
                edge.setId(id);

                return edge;
            }

            return super.createEdge(parent, id, value, source, target, style);
        }

    }

    protected void installListeners() {
        // Installs the popup menu in the graph component
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handles context menu on the Mac where the trigger is on
                // mousepressed
                mouseReleased(e);

                mxCell cell = (mxCell) graphComponent.getCellAt(e.getX(), e.getY(), false);

                mxGraph graph = graphComponent.getGraph();
                mxCell cell1 = (mxCell) graphComponent.getCellAt(310, 240);
                mxCell cell2 = (mxCell) graphComponent.getCellAt(60, 240);
                mxCell cell3 = (mxCell) graphComponent.getCellAt(560, 240);
                mxCell cell4 = (mxCell) graphComponent.getCellAt(310, 50);
                mxCell cell5 = (mxCell) graphComponent.getCellAt(310, 430);

            }

            /**
             *
             */
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showGraphPopupMenu(e);
                }
            }

        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        mxConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
        mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";
        myConstants.ks = 6;
        Main editor = new Main();
        myConstants.mainEditor = editor;
        myConstants.jf = editor.createFrame(new FunctionBar(editor));
        myConstants.jf.setLocationRelativeTo(myConstants.jf);// Jframe居中
        myConstants.jf.setVisible(true);
    }

}