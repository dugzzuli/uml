package com.mxgraph.examples.swing.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import com.mxgraph.examples.swing.editor.EditorActions.SoftwareAction;

import com.mxgraph.examples.swing.editor.EditorActions.SoftwareModelScanAction;
import com.mxgraph.examples.swing.editor.EditorActions.ScaleAction;
import com.mxgraph.examples.swing.editor.EditorActions.SoftwareActionThree;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleGridItem;
import com.mxgraph.examples.swing.editor.EditorActions.TogglePropertyItem;
import com.mxgraph.examples.swing.editor.EditorActions.ToggleRulersItem;
import com.mxgraph.examples.swing.editor.EditorActions.ZoomPolicyAction;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;

//菜单添加类
public class FunctionBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4060203894740766714L;

	@SuppressWarnings("serial")
	public FunctionBar(final BasicGraphEditor editor) {
		final mxGraphComponent graphComponent = editor.getGraphComponent();
		final mxGraph graph = graphComponent.getGraph();
		JMenu menu = null;
		JMenu submenu = null;
		// aligncenter,alignleft,alignright,aligntop,alignbottom
		// Creates the file menu
		menu = add(new JMenu("工作流建模"));

		menu.add(editor.bind("打开", new SoftwareAction()));

		// Creates the edit menu
		menu = add(new JMenu("求解知识资源建模"));

		menu.add(editor.bind("打开", new SoftwareActionThree()));
// Creates the edit menu
		menu = add(new JMenu("知识检索"));

		menu.add(editor.bind("打开", new SoftwareActionThree()));
		// Creates the view menu
		menu = add(new JMenu("系统管理"));

		JMenuItem item = menu.add(new TogglePropertyItem(graphComponent, mxResources.get("pageLayout"), "PageVisible",
				true, new ActionListener() {
					/**
					 * 
					 */
					public void actionPerformed(ActionEvent e) {
						if (graphComponent.isPageVisible() && graphComponent.isCenterPage()) {
							graphComponent.zoomAndCenter();
						} else {
							graphComponent.getGraphControl().updatePreferredSize();
						}
					}
				}));

		item.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof TogglePropertyItem) {
					final mxGraphComponent graphComponent = editor.getGraphComponent();
					TogglePropertyItem toggleItem = (TogglePropertyItem) e.getSource();

					if (toggleItem.isSelected()) {
						// Scrolls the view to the center
						SwingUtilities.invokeLater(new Runnable() {
							/*
							 * (non-Javadoc)
							 * 
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								graphComponent.scrollToCenter(true);
								graphComponent.scrollToCenter(false);
							}
						});
					} else {
						// Resets the translation of the view
						mxPoint tr = graphComponent.getGraph().getView().getTranslate();

						if (tr.getX() != 0 || tr.getY() != 0) {
							graphComponent.getGraph().getView().setTranslate(new mxPoint());
						}
					}
				}
			}
		});

		menu.add(new TogglePropertyItem(graphComponent, mxResources.get("antialias"), "AntiAlias", true));

		menu.addSeparator();

		menu.add(new ToggleGridItem(editor, mxResources.get("grid")));
		menu.add(new ToggleRulersItem(editor, mxResources.get("rulers")));

		menu.addSeparator();

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("zoom")));

		submenu.add(editor.bind("400%", new ScaleAction(4)));
		submenu.add(editor.bind("200%", new ScaleAction(2)));
		submenu.add(editor.bind("150%", new ScaleAction(1.5)));
		submenu.add(editor.bind("100%", new ScaleAction(1)));
		submenu.add(editor.bind("75%", new ScaleAction(0.75)));
		submenu.add(editor.bind("50%", new ScaleAction(0.5)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("custom"), new ScaleAction(0)));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("zoomIn"), mxGraphActions.getZoomInAction()));
		menu.add(editor.bind(mxResources.get("zoomOut"), mxGraphActions.getZoomOutAction()));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("page"), new ZoomPolicyAction(mxGraphComponent.ZOOM_POLICY_PAGE)));
		menu.add(editor.bind(mxResources.get("width"), new ZoomPolicyAction(mxGraphComponent.ZOOM_POLICY_WIDTH)));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("actualSize"), mxGraphActions.getZoomActualAction()));
	}
}
