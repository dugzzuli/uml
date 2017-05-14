package com.util;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

public class myConstants {

    // 代表知识资源建模界面类型的变量
    // 取值0,1,2,3,4,5对应主界面、业务过程、软件资源、图文资料、人员和组织
    public static int ks = 0;
    public static String uuid = "";
    // 代表知识资源模型浏览界面类型的变量
    public static int ks1 = 0;

    // 业务过程属性界面
    public static JTabbedPane jTabbedPane1;
    // 软件资源属性界面
    public static JTabbedPane jTabbedPane2;
    // 图文资料属性界面
    public static JTabbedPane jTabbedPane3;

    //three  jatabpannel
    public static JTabbedPane jTabbedPaneThree;
    // 人员属性界面
    public static JTabbedPane jTabbedPane4;
    // 组织属性界面
    public static JTabbedPane jTabbedPane5;
    // 主框架
    public static BasicGraphEditor editor;
    // 主界面用到的editor
    public static BasicGraphEditor mainEditor;
    // 主界面
    public static JFrame jf;

    // 当前选中的cell
    public static mxCell cell = null;
    public static mxGraphComponent graphComponent;

    // 代表知识资源模型图的类型
    public static int modelStyle;
    // 为了在建模好某类型知识模型后更新树的节点
    public static JTree jtree;
    public static DefaultTreeModel model;
    private static String currentProid;

    // 在一个字符串的某个位置插入字符串，用于修改文件路径a\b.png为a\\b.png
    public static String Stringinsert(String a, String b, int t) {
        return a.substring(0, t) + b + a.substring(t, a.length());
    }

    public static DefaultMutableTreeNode selectNode;// 当前选中的树节点

    /**
     * @return the currentProid
     */
    public static String getCurrentProid() {
        return currentProid;
    }

    /**
     * @param aCurrentProid the currentProid to set
     */
    public static void setCurrentProid(String aCurrentProid) {
        currentProid = aCurrentProid;
    }
}
