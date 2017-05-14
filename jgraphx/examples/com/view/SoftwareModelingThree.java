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
import com.mxgraph.model.Jisuanjiziyuan;
import com.mxgraph.model.KnowledgeUnit;
import com.mxgraph.model.Person;
import com.mxgraph.model.Ruanjianshebei;
import com.mxgraph.model.Tuwenziliao;
import com.mxgraph.model.Yingjianshebei;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.mysqlUtils.DaoJisuanjiziyuan;
import com.mxgraph.mysqlUtils.DaoKnowledgeUnit;
import com.mxgraph.mysqlUtils.DaoPerson;
import com.mxgraph.mysqlUtils.DaoRuanjianshebei;
import com.mxgraph.mysqlUtils.DaoTuwenziliao;
import com.mxgraph.mysqlUtils.DaoYingjianshebei;
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
import com.util.myConstants;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoftwareModelingThree extends BasicGraphEditor {

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

    public SoftwareModelingThree() {
        this("求解知识资源建模", new CustomGraphComponent(new CustomGraph()));
    }
    public ThreePanel swRight;

    /**
     *
     */
    public SoftwareModelingThree(String appTitle, mxGraphComponent component) {
        super(appTitle, component);
        final mxGraph graph = graphComponent.getGraph();
        swRight = new ThreePanel();
// 设置绘图窗口和右侧信息的大小

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
        outer1.setDividerLocation(880);
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

        // Adds some template cells for dropping into the graph
        // addTemplate("图元名称",image,"style",280,280,user object)
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
                        "shape=actor;renyuan", 50, 50, "");
        shapesPalette.addTemplate("或",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/或.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/或.png", 50, 50, "或");

        shapesPalette.addTemplate("与",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/yu.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/yu.png", 50, 50, "与");
           shapesPalette.addTemplate("事件",
                new ImageIcon(SoftwareModelingThree.class.getResource("/com/mxgraph/examples/swing/images/事件.png")),
                "roundImage;image=/com/mxgraph/examples/swing/images/事件.png", 50, 50, "事件");

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
            Document doc = mxUtils.loadDocument(SoftwareModelingThree.class
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
                    SoftwareModelingThree.this.mouseWheelMoved(e);
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
                        javax.swing.JTabbedPane p2 = myConstants.jTabbedPaneThree;

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
                        if (style.equals("renyuan")) {

                            try {
                                // buRight.setCellId(cell.getId());
                                p2.setSelectedIndex(0);//

                                swRight.getShizhidanyuanSave().setEnabled(false);
                                swRight.getRenyuanSave().setEnabled(true);
                                swRight.getTuwenziliaoSave().setEnabled(false);
                                swRight.getJisuanjiziyuanSave().setEnabled(false);
                                swRight.getRuanjianshebeiSave().setEnabled(false);
                                swRight.getYingjianshebeiSave().setEnabled(false);

                                swRight.getPname().setText("");
                                swRight.getPnum().setText("");
                                swRight.getPlingyu().setText("");
                                swRight.getPbumen().setText("");
                                swRight.getPzhishineirong().setText("");
                                swRight.getPsuoshuzaiti().setText("");
                                int count = DaoPerson.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    Person model = DaoPerson.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getPname().setText(model.getXingming());
                                    swRight.getPnum().setText(model.getGonghao());
                                    swRight.getPlingyu().setText(model.getLingyu());
                                    swRight.getPbumen().setText(model.getBumen());
                                    swRight.getPzhishineirong().setText(model.getZhishineirong());
                                    swRight.getPsuoshuzaiti().setText(model.getSuoshuzaiti());
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else if (style.equals("tuwenziliao")) {
                            try {
                                p2.setSelectedIndex(1);//

                                swRight.getShizhidanyuanSave().setEnabled(false);
                                swRight.getRenyuanSave().setEnabled(false);
                                swRight.getTuwenziliaoSave().setEnabled(true);
                                swRight.getJisuanjiziyuanSave().setEnabled(false);
                                swRight.getRuanjianshebeiSave().setEnabled(false);
                                swRight.getYingjianshebeiSave().setEnabled(false);
                                swRight.getTname().setText("");
                                swRight.getTAuthor().setText("");
                                swRight.getTversion().setText("");
                                swRight.getTtype().setText("");
                                swRight.getTuseliyongli().setText("");
                                swRight.getTwuliweizhi().setText("");
                                int count = DaoTuwenziliao.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    Tuwenziliao model = DaoTuwenziliao.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getTname().setText(model.getMingcheng());
                                    swRight.getTAuthor().setText(model.getZuozhe());
                                    swRight.getTversion().setText(model.getBanbenhao());
                                    swRight.getTtype().setText(model.getLeixing());
                                    swRight.getTuseliyongli().setText(model.getKeliyonglv());
                                    swRight.getTwuliweizhi().setText(model.getWuliweizhi());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else if (style.equals("jisuanjiziyuan")) {
                            try {
                                p2.setSelectedIndex(2);
                                swRight.getShizhidanyuanSave().setEnabled(false);
                                swRight.getRenyuanSave().setEnabled(false);
                                swRight.getTuwenziliaoSave().setEnabled(false);
                                swRight.getJisuanjiziyuanSave().setEnabled(true);
                                swRight.getRuanjianshebeiSave().setEnabled(false);
                                swRight.getYingjianshebeiSave().setEnabled(false);
                                swRight.getJName().setText("");
                                swRight.getJnum().setText("");
                                swRight.getJType().setText("");
                                swRight.getJsource().setText("");
                                swRight.getJcunfangweizhi().setText("");
                                int count = DaoJisuanjiziyuan.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    Jisuanjiziyuan model = DaoJisuanjiziyuan.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getJName().setText(model.getMingcheng());
                                    swRight.getJnum().setText(model.getBianhao());
                                    swRight.getJType().setText(model.getLeixing());
                                    swRight.getJsource().setText(model.getLaiyuan());
                                    swRight.getJcunfangweizhi().setText(model.getCunfangweizhi());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else if (style.equals("ruanjianshebei")) {
                            try {

                                p2.setSelectedIndex(3);//
                                swRight.getShizhidanyuanSave().setEnabled(false);
                                swRight.getRenyuanSave().setEnabled(false);
                                swRight.getTuwenziliaoSave().setEnabled(false);
                                swRight.getJisuanjiziyuanSave().setEnabled(false);
                                swRight.getRuanjianshebeiSave().setEnabled(true);
                                swRight.getYingjianshebeiSave().setEnabled(false);
                                swRight.getRname().setText("");
                                swRight.getRversion().setText("");
                                swRight.getRyingyongfanwei().setText("");
                                swRight.getRyingyongjiaocheng().setText("");
                                swRight.getRwuliweizhi().setText("");

                                int count = DaoRuanjianshebei.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    Ruanjianshebei model = DaoRuanjianshebei.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getRname().setText(model.getMingcheng());
                                    swRight.getRversion().setText(model.getBanben());
                                    swRight.getRyingyongfanwei().setText(model.getYingyongfanwei());
                                    swRight.getRyingyongjiaocheng().setText(model.getYingyongjiaocheng());
                                    swRight.getRwuliweizhi().setText(model.getWuliweizhi());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else if (style.equals("yingjianshebei")) {
                            try {
                                swRight.getShizhidanyuanSave().setEnabled(false);
                                swRight.getRenyuanSave().setEnabled(false);
                                swRight.getTuwenziliaoSave().setEnabled(false);
                                swRight.getJisuanjiziyuanSave().setEnabled(false);
                                swRight.getRuanjianshebeiSave().setEnabled(false);
                                swRight.getYingjianshebeiSave().setEnabled(true);
                                p2.setSelectedIndex(4);
                                swRight.getYname().setText("");
                                swRight.getYtype().setText("");
                                swRight.getYyingjianyaoqiu().setText("");
                                swRight.getYyingjianyaoqiu().setText("");
                                swRight.getYyingyongjiaocheng().setText("");
                                int count = DaoYingjianshebei.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    Yingjianshebei model = DaoYingjianshebei.getModel(dir, String.valueOf(cell.getId()));
                                    swRight.getYname().setText(model.getMingcheng());
                                    swRight.getYtype().setText(model.getXinghao());
                                    swRight.getYyingjianyaoqiu().setText(model.getYingjianyaoqiu());
                                    swRight.getYwuliweizhi().setText(model.getWuliweizhi());
                                    swRight.getYyingyongjiaocheng().setText(model.getYingyongjiaocheng());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (style.equals("knowledge")) {
                            try {
                                p2.setEnabled(false);
                                swRight.getShizhidanyuanSave().setEnabled(true);
                                swRight.getRenyuanSave().setEnabled(false);
                                swRight.getTuwenziliaoSave().setEnabled(false);
                                swRight.getJisuanjiziyuanSave().setEnabled(false);
                                swRight.getRuanjianshebeiSave().setEnabled(false);
                                swRight.getYingjianshebeiSave().setEnabled(false);
                                swRight.getZsdymingcheng().setText("");
                                swRight.getZsdylingyu().setText("");
                                swRight.getZsdybianhao().setText("");
                                swRight.getZsdysuoshuzaiti().setText("");
                                swRight.getZsdyzhishineirong().setText("");
                                int count = DaoKnowledgeUnit.exist(dir, String.valueOf(cell.getId()));
                                if (count > 0) {
                                    KnowledgeUnit model = DaoKnowledgeUnit.getModel(dir, String.valueOf(cell.getId()));

                                    swRight.getZsdymingcheng().setText(model.getMingcheng());
                                    swRight.getZsdylingyu().setText(model.getLingyu());
                                    swRight.getZsdybianhao().setText(model.getBianhao());
                                    swRight.getZsdysuoshuzaiti().setText(model.getSuoshuzaiti());
                                    swRight.getZsdyzhishineirong().setText(model.getZhishineirong());
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(SoftwareModelingThree.class.getName()).log(Level.SEVERE, null, ex);
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
        SoftwareModelingThree editor = new SoftwareModelingThree();
        editor.createFrame(new EditorMenuBar(editor)).setVisible(true);
    }
}
