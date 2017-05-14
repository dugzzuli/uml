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
import java.net.URL;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import org.w3c.dom.Document;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorMenuBar;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.SolutionBuild;
import com.mxgraph.model.YUHD;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.mysqlUtils.DaoSolution;
import com.mxgraph.mysqlUtils.DaoYUHD;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.util.JsonOperation;
import com.util.myConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoftwareModeling extends BasicGraphEditor {

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

    public SoftwareModeling() {
        this("知识模块建模主界面", new CustomGraphComponent(new CustomGraph()));

    }
    public Softwear3 swRight;

    /**
     *
     */
    public SoftwareModeling(String appTitle, mxGraphComponent component) {
        super(appTitle, component);
        final mxGraph graph = graphComponent.getGraph();
        swRight = new Softwear3();

        myConstants.graphComponent = graphComponent;

        JSplitPane inner = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        inner.add(libraryPane);
        inner.setOneTouchExpandable(true);
        inner.setDividerLocation(300);
        inner.setResizeWeight(1);
        inner.setDividerSize(6);
        inner.setBorder(null);

        JSplitPane outer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inner, graphComponent);
        outer.setOneTouchExpandable(true);
        outer.setDividerLocation(250);
        outer.setDividerSize(6);
        outer.setBorder(null);

        JSplitPane outer1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, outer, swRight);
        outer1.setOneTouchExpandable(true);
        outer1.setDividerLocation(950);
        outer1.setDividerSize(6);
        outer1.setBorder(null);

        JSplitPane etb = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etb1, etb2);
        etb.setOneTouchExpandable(true);
        etb.setDividerLocation(700);
        etb.setDividerSize(6);
        etb.setBorder(null);
        // Creates the status bar状态栏
        statusBar = createStatusBar();

        // Display some useful information about repaint events
        installRepaintListener();
        // Puts everything together 注意这边的方位摆放,south改成east后,位置的确变了
        setLayout(new BorderLayout());
        add(outer1, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(etb, BorderLayout.NORTH);
        EditorPalette shapesPalette = insertPalette("图元");
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

        shapesPalette// 软件步骤
                .addTemplate("求解活动情景",
                        new ImageIcon(
                                SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/qiujiehuodong.png")),
                        "roundImage;image=/com/mxgraph/examples/swing/images/qiujiehuodong.png;one", 70, 40, "求解活动情景");
        shapesPalette
                .addEdgeTemplate(
                        "直线",
                        new ImageIcon(
                                SoftwareModelingThree.class
                                        .getResource("/com/mxgraph/examples/swing/images/straight.png")),
                        "straight", 100, 100, "");
        shapesPalette// 软件步骤
                .addTemplate("知识单元",
                        new ImageIcon(
                                SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/知识单元.png")),
                        "roundImage;image=/com/mxgraph/examples/swing/images/知识单元.png;knowledge", 70, 40, "知识单元");
        shapesPalette.addTemplate("业务活动",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/业务活动.png")),
                "rounded=1;two", 70, 40, "业务活动");
        shapesPalette.addTemplate("图文资料",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/图文资料.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/图文资料.png;tuwenziliao", 70, 70, "图文资料");
        shapesPalette.addTemplate("异或",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/异或.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/异或.png", 50, 50, "异或");

        shapesPalette.addTemplate("硬件资源",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/硬件资源.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/硬件资源.png;yingjianshebei", 70, 70, "硬件资源");
        shapesPalette.addTemplate("计算机资源",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/计算机资源.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/计算机资源.png;jisuanjiziyuan", 70, 70, "计算机资源");

        shapesPalette.addTemplate("资源",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/资源.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/资源.png", 70, 70, "资源");
        shapesPalette.addTemplate("进度条",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/进度条.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/进度条.png", 70, 70, "进度条");

        shapesPalette.addTemplate("软件资源",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/软件资源.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/软件资源.png;ruanjianshebei", 70, 70, "软件资源");

        shapesPalette.addEdgeTemplate("连接线", new ImageIcon(
                SoftwareModelingThree.class
                        .getResource("/com/mxgraph/examples/swing/images/vertical.png")),
                "vertical", 100, 100, "");

        shapesPalette
                .addTemplate(
                        "求解人员",
                        new ImageIcon(
                                SoftwareModelingThree.class
                                        .getResource("/com/mxgraph/examples/swing/images/actor.png")),
                        "shape=actor;person", 50, 50, "");
        shapesPalette.addTemplate("或",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/或.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/或.png", 50, 50, "或");

        shapesPalette.addTemplate("与",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/yu.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/yu.png", 50, 50, "与");
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
            setToolTips(true);
            getConnectionHandler().setCreateTarget(true);

            // Loads the defalt stylesheet from an external file
            mxCodec codec = new mxCodec();
            Document doc = mxUtils.loadDocument(SoftwareModeling.class
                    .getResource("/com/mxgraph/examples/swing/resources/default-style.xml").toString());
            codec.decode(doc.getDocumentElement(), graph.getStylesheet());

            // Sets the background to white
            getViewport().setOpaque(true);
            getViewport().setBackground(Color.WHITE);
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

        private String uuId = String.valueOf(UUID.randomUUID());

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
            myConstants.uuid = uuId;
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

            tip += "<br>id=" + uuId;

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

        /**
         * @return the uuId
         */
        public String getUuId() {
            return uuId;
        }

        /**
         * @param uuId the uuId to set
         */
        public void setUuId(String uuId) {
            this.uuId = String.valueOf(UUID.fromString("dugking"));
        }

    }

    protected void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            graphComponent.zoomIn();
        } else {
            graphComponent.zoomOut();
        }

        status(mxResources.get("scale") + ": " + (int) (100 * graphComponent.getGraph().getView().getScale()) + "%");
    }

    protected void installListeners() {
        // Installs mouse wheel listener for zooming
        MouseWheelListener wheelTracker = new MouseWheelListener() {
            /**
             *
             */
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getSource() instanceof mxGraphOutline || e.isControlDown()) {
                    SoftwareModeling.this.mouseWheelMoved(e);
                }
            }

        };

        // Handles mouse wheel events in the outline and graph component
        graphOutline.addMouseWheelListener(wheelTracker);
        graphComponent.addMouseWheelListener(wheelTracker);

        // Installs the popup menu in the outline
        graphOutline.addMouseListener(new MouseAdapter() {

            /**
             *
             */
            public void mousePressed(MouseEvent e) {
                // Handles context menu on the Mac where the trigger is on
                // mousepressed
                mouseReleased(e);
            }

            /**
             *
             */
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showOutlinePopupMenu(e);
                }
            }

        });

        // Installs the popup menu in the graph component
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Handles context menu on the Mac where the trigger is on
                // mousepressed
                mouseReleased(e);

                mxCell cell = (mxCell) graphComponent.getCellAt(e.getX(), e.getY(), false);
                myConstants.cell = cell;
                if (cell != null) {
                    if (cell != null && graphComponent.getGraph().isCellEditable(cell)) {
                        javax.swing.JTabbedPane p2 = myConstants.jTabbedPane2;
                        String tip = graphComponent.getGraph().getToolTipForCell(cell);
                        System.out.println(tip);
                        String tiptext = tip.substring(6, tip.length() - 7);
                        System.out.println(tiptext);
                        String[] arr = tiptext.split("<br>");
                        String dir = arr[arr.length - 1].split("=")[1];//获取路径
                        p2.setEnabled(true);
                        swRight.setProid(dir);
                        swRight.setObjid(cell.getId());
                        String[] arrStyle = cell.getStyle().split(";");
                        String style = arrStyle[arrStyle.length - 1];
                        // 软件资源建模界面,软件步骤
                        if (style.equals("one")) {
                            try {
                                // buRight.setCellId(cell.getId());
                                p2.setSelectedIndex(1);
                                swRight.getjButton1().setEnabled(false);
                                swRight.getjButton2().setEnabled(true);
                                swRight.getQingjingmingcheng().setText("");
                                swRight.getZhixingzhe().setText("");
                                swRight.getShuchuchengguo().setText("");
                                swRight.getShijian().setText("");
                                swRight.getDidian().setText("");
                                swRight.getBiaozhun().setText("");
                                swRight.getFuzhusheshi().setText("");
                                swRight.getKongqihuanjing().setText("");
                                swRight.getKongjian().setText("");
                                swRight.getZuzhijili().setText("");
                                swRight.getNeirongmiaoshu().setText("");
                                swRight.setProid(dir);
                                swRight.setObjid(String.valueOf(cell.getId()));

                                int count = DaoSolution.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    SolutionBuild model = DaoSolution.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getQingjingmingcheng().setText(model.getQingjingmingcheng());
                                    swRight.getZhixingzhe().setText(model.getZhixingzhe());
                                    swRight.getShuchuchengguo().setText(model.getShuchuchengguo());
                                    swRight.getShijian().setText(model.getDate());
                                    swRight.getDidian().setText(model.getPlace());
                                    swRight.getBiaozhun().setText(model.getStandard());
                                    swRight.getFuzhusheshi().setText(model.getFuzhusheshi());
                                    swRight.getKongqihuanjing().setText(model.getKongqihuanjing());
                                    swRight.getKongjian().setText(model.getKongjian());
                                    swRight.getZuzhijili().setText(model.getZuzhijili());
                                    swRight.getNeirongmiaoshu().setText(model.getNeirongmiaoshu());
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModeling.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (style.equals("two")) {
                            try {
                                p2.setSelectedIndex(0);
                                swRight.getjButton1().setEnabled(true);
                                swRight.getjButton2().setEnabled(false);
                                swRight.setProid(dir);
                                swRight.setObjid(String.valueOf(cell.getId()));

                                swRight.getYewuhuodong().setText("");
                                swRight.getYewuhuodongbianhao().setText("");

                                swRight.getFuguocheng().setText("");
                                swRight.getHuodongmiaoshu().setText("");

                                int count = DaoYUHD.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    YUHD model = DaoYUHD.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.setProid(dir);
                                    swRight.setObjid(model.getObjid());

                                    swRight.getYewuhuodong().setText(model.getHuodongmingcheng());
                                    swRight.getYewuhuodongbianhao().setText(model.getYwuhuodongbianhao());

                                    swRight.getFuguocheng().setText(model.getFuguocheng());
                                    swRight.getHuodongmiaoshu().setText(model.getHuodongmiaoshu());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModeling.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                }
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

        // Installs a mouse motion listener to display the mouse location
        graphComponent.getGraphControl().addMouseMotionListener(new MouseMotionListener() {

            /*
             * (non-Javadoc)
             * 
             * @see
             * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
             * MouseEvent)
             */
            public void mouseDragged(MouseEvent e) {
                mouseLocationChanged(e);
            }

            /*
             * (non-Javadoc)
             * 
             * @see
             * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.
             * MouseEvent)
             */
            public void mouseMoved(MouseEvent e) {
                mouseDragged(e);
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
        myConstants.ks = 2;
        SoftwareModeling editor = new SoftwareModeling();

        editor.createFrame(new EditorMenuBar(editor)).setVisible(true);
    }
}
