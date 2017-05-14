package com.mxgraph.examples.swing.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxEventSource.mxIEventListener;


//左面板图形添加类
public class EditorPalette extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7771113885935187066L;

	/**
	 * 
	 */
	protected JLabel selectedEntry = null;

	/**
	 * 
	 */
	protected mxEventSource eventSource = new mxEventSource(this);

	/**
	 * 
	 */
	protected Color gradientColor = Color.WHITE;

	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public EditorPalette() {
		// 这个非常重要!!Flowlayout都是UI设计中的布局部分,终于找到它的布局设计方式了!
		// Flowlayou表示 布局如同书页上的字一样在容器中排列组队.从左至右填充顶行,直到不能容纳其他组件为止
		// 然后再以同样的方式继续填充后面每一行. 看于<netbeans权威指南>P59
		// leading表示每一行组件都应该与容器方向的开始边对齐,例如对从左到右的方向,则与左边对齐,5,5为水平和垂直间隙
		// 自己尝试将leading改为TRAILING后,panel内部的图形都往结束边靠了
		// 将5,5改为20,20,则发现图形间距变得很大,且滚动条并没有随之变化,以致下面图形即使拖动滚动条也无法看到
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		// Clears the current selection when the background is clicked
		// 当鼠标点击在背景时,解除选定的图标,即clearSelection.该方法在后面有定义
		// 只定义了mousePressed,但预留了其他鼠标响应的反应
		addMouseListener(new MouseListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.
			 * MouseEvent)
			 */
			public void mousePressed(MouseEvent e) {
				clearSelection();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseReleased(MouseEvent e) {
			}

		});

		// Shows a nice icon for drag and drop but doesn't import anything
		// 拖曳时候有个良好完整的图形样子,但不会引入于左面板中
		// setTransferHandler 是允许该组件数据传输的意思
		setTransferHandler(new TransferHandler() {
			public boolean canImport(JComponent comp, DataFlavor[] flavors) {
				return true;
			}
		});
	}

	/**
	 * 
	 */
	public void setGradientColor(Color c) {
		gradientColor = c;
	}

	/**
	 * 
	 */
	public Color getGradientColor() {
		return gradientColor;
	}

	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		if (gradientColor == null) {
			super.paintComponent(g);
		} else {
			Rectangle rect = getVisibleRect();

			if (g.getClipBounds() != null) {
				rect = rect.intersection(g.getClipBounds());
			}

			Graphics2D g2 = (Graphics2D) g;

			g2.setPaint(new GradientPaint(0, 0, getBackground(), getWidth(), 0, gradientColor));
			g2.fill(rect);
		}
	}

	/**
	 * 
	 */
	public void clearSelection() {
		setSelectionEntry(null, null);
	}

	/**
	 * 
	 */
	public void setSelectionEntry(JLabel entry, mxGraphTransferable t) {
		JLabel previous = selectedEntry;
		selectedEntry = entry;

		if (previous != null) {
			previous.setBorder(null);
			previous.setOpaque(false);
		}

		if (selectedEntry != null) {
			selectedEntry.setBorder(ShadowBorder.getSharedInstance());
			selectedEntry.setOpaque(true);
		}

		eventSource.fireEvent(
				new mxEventObject(mxEvent.SELECT, "entry", selectedEntry, "transferable", t, "previous", previous));
	}

	/**
	 * 
	 */
	// 设置图形在library中的最佳宽度
	public void setPreferredWidth(int width) {
		int cols = Math.max(1, width / 55);
		setPreferredSize(new Dimension(width, (getComponentCount() * 55 / cols) + 30));
		revalidate();
	}

	// 设置边的类型所需参数
	/**
	 * 
	 * @param name
	 * @param icon
	 * @param style
	 * @param width
	 * @param height
	 * @param value
	 */
	public void addEdgeTemplate(final String name, ImageIcon icon, String style, int width, int height, Object value) {
		mxGeometry geometry = new mxGeometry(0, 0, width, height);
		geometry.setTerminalPoint(new mxPoint(0, height), true);
		geometry.setTerminalPoint(new mxPoint(width, 0), false);
		geometry.setRelative(true);

		mxCell cell = new mxCell(value, geometry, style);
		cell.setEdge(true);

		addTemplate(name, icon, cell);
	}

	/**
	 * 
	 * @param name
	 * @param icon
	 * @param style
	 * @param width
	 * @param height
	 * @param value
	 */
	public void addTemplate(final String name, ImageIcon icon, String style, int width, int height, Object value) {
		mxCell cell = new mxCell(value, new mxGeometry(0, 0, width, height), style);
		cell.setVertex(true);

		addTemplate(name, icon, cell);
	}

	/**
	 * 
	 * @param name
	 * @param icon
	 * @param cell
	 */
	public void addTemplate(final String name, ImageIcon icon, mxCell cell) {
		mxRectangle bounds = (mxGeometry) cell.getGeometry().clone();
		final mxGraphTransferable t = new mxGraphTransferable(new mxCell[] { cell }, bounds);

		// Scales the image if it's too large for the library
		// 设置图形在library中的最佳大小,以防撑爆
		if (icon != null) {
			if (icon.getIconWidth() > 32 || icon.getIconHeight() > 32) {
				icon = new ImageIcon(icon.getImage().getScaledInstance(32, 32, 0));
			}
		}

		final JLabel entry = new JLabel(icon);
		entry.setPreferredSize(new Dimension(200, 50));
		entry.setBackground(EditorPalette.this.getBackground().brighter());
		entry.setFont(new Font(entry.getFont().getFamily(), 0, 10));

		entry.setVerticalTextPosition(JLabel.BOTTOM);
		entry.setHorizontalTextPosition(JLabel.CENTER);
		entry.setIconTextGap(0);

		entry.setToolTipText(name);
		entry.setText(name);

		entry.addMouseListener(new MouseListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.
			 * MouseEvent)
			 */
			public void mousePressed(MouseEvent e) {
				setSelectionEntry(entry, t);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseClicked(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseEntered(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseExited(MouseEvent e) {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.
			 * MouseEvent)
			 */
			public void mouseReleased(MouseEvent e) {
			}

		});

		// Install the handler for dragging nodes into a graph
		DragGestureListener dragGestureListener = new DragGestureListener() {
			/**
			 * 
			 */
			public void dragGestureRecognized(DragGestureEvent e) {
				e.startDrag(null, mxConstants.EMPTY_IMAGE, new Point(), t, null);
			}

		};

		DragSource dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(entry, DnDConstants.ACTION_COPY, dragGestureListener);

		add(entry);
	}

	/**
	 * @param eventName
	 * @param listener
	 * @see com.mxgraph.util.mxEventSource#addListener(java.lang.String,
	 *      com.mxgraph.util.mxEventSource.mxIEventListener)
	 */
	public void addListener(String eventName, mxIEventListener listener) {
		eventSource.addListener(eventName, listener);
	}

	/**
	 * @return whether or not event are enabled for this palette
	 * @see com.mxgraph.util.mxEventSource#isEventsEnabled()
	 */
	public boolean isEventsEnabled() {
		return eventSource.isEventsEnabled();
	}

	/**
	 * @param listener
	 * @see com.mxgraph.util.mxEventSource#removeListener(com.mxgraph.util.mxEventSource.mxIEventListener)
	 */
	public void removeListener(mxIEventListener listener) {
		eventSource.removeListener(listener);
	}

	/**
	 * @param eventName
	 * @param listener
	 * @see com.mxgraph.util.mxEventSource#removeListener(java.lang.String,
	 *      com.mxgraph.util.mxEventSource.mxIEventListener)
	 */
	public void removeListener(mxIEventListener listener, String eventName) {
		eventSource.removeListener(listener, eventName);
	}

	/**
	 * @param eventsEnabled
	 * @see com.mxgraph.util.mxEventSource#setEventsEnabled(boolean)
	 */
	public void setEventsEnabled(boolean eventsEnabled) {
		eventSource.setEventsEnabled(eventsEnabled);
	}

}
