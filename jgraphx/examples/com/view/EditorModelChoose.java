package com.view;


import javax.swing.BorderFactory;
import javax.swing.JToolBar;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;


import com.mxgraph.examples.swing.editor.EditorActions.SoftwareAction;
import com.mxgraph.examples.swing.editor.EditorActions.SoftwareActionThree;

import com.util.myConstants;

public class EditorModelChoose extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015443128436394471L;

	/**
	 * 
	 * @param frame
	 * @param orientation
	 */
	private boolean ignoreZoomChange = false;

	/**
	 * 
	 */
	// 工具栏添加类
	// 这条代码对初学者比较重要,可以结合BasicGraphEditor一起看
	public EditorModelChoose(final BasicGraphEditor editor, int orientation) {
		super(orientation);// 创建具有指定 orientation 的新工具栏
		// 设置边框的凹下去的效果
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));

		setFloatable(false);// 如果要移动工具栏，此属性必须设置为 true
		if (myConstants.ks == 0) {// 主界面

			add(editor.bind("软件资源", new SoftwareAction(), "/com/mxgraph/examples/swing/images/alignleft.gif"));

		}

		// aligncenter,alignleft,alignright,aligntop,alignbottom
		if (myConstants.ks == 3) {// 图文资料

			add(editor.bind("软件资源", new SoftwareActionThree(), "/com/mxgraph/examples/swing/images/alignleft.gif"));

		}
		if (myConstants.ks == 4) {// 人员

			add(editor.bind("软件资源", new SoftwareAction(), "/com/mxgraph/examples/swing/images/alignleft.gif"));

		}
		// aligncenter,alignleft,alignright,aligntop,alignbottom
		if (myConstants.ks == 5) {// 组织

			add(editor.bind("软件资源", new SoftwareActionThree(), "/com/mxgraph/examples/swing/images/alignleft.gif"));

		}
	}
}
