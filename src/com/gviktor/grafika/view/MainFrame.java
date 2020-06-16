package com.gviktor.grafika.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener {
	
	private JMenuBar menubar;
	private JMenu main;
	private JMenu canvases;
	private JMenuItem m_exit;
	private JMenuItem m_defPolygon;
	private JMenuItem m_triangle;
	private JMenuItem m_curve;
	public static final String EXIT_MENU_ITEM="Exit";
	public static final String DEFINE_POLYGON_MENU_ITEM="Define Polygon";
	public static final String TRIANGLE_PLAY__MENU_ITEM="Play Trinagles";
	public static final String DEFINE_CURVE_MENU_ITEM="Define curve";



	
	private BorderLayout mainLayout;
	private JPanel toolPanel,north,south,east;
	IconButton iconButton1=new IconButton("icon/line.png");
	IconButton iconButton2 = new IconButton("icon/cursor.png");
	private IconButtonGroup iconButtonGroup;
	private Canvas drawingcanvas;
	public MainFrame(Canvas canvas) {
		super("GraphicsTest");
		mainLayout = new BorderLayout();
		drawingcanvas=canvas;
		this.setLayout(mainLayout);
		setSize(500, 500);
		setPanels();
		prepareActions();
		this.add(canvas,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setResizable(false);
		setVisible (true);
	}
	public void setPanels() {
		north=new JPanel(new GridLayout(3,2));
		south = new JPanel(new GridLayout(3,2));
		east = new JPanel(new GridLayout(3,2));
		setToolPanel();
		setMenu();
		add(toolPanel,BorderLayout.WEST);
		add(north,BorderLayout.NORTH);
		add(east,BorderLayout.EAST);
		add(south,BorderLayout.SOUTH);
	}
	private void setMenu() {
		menubar=new JMenuBar();
		main = new JMenu("Main");
		canvases = new JMenu("Canvases");
		m_exit = new JMenuItem("Exit");
		m_defPolygon = new JMenuItem("Define Polygon");
		m_triangle = new JMenuItem("Triangle");
		m_curve = new JMenuItem("Curve Fitting");
		menubar.add(main);
		menubar.add(canvases);
		main.add(m_exit);
		canvases.add(m_triangle);
		canvases.add(m_defPolygon);
		canvases.add(m_curve);
		this.setJMenuBar(menubar);
	}
	private void prepareActions() {
		m_exit.addActionListener(this);
		m_defPolygon.addActionListener(this);
		m_triangle.addActionListener(this);
		m_curve.addActionListener(this);
		m_exit.setActionCommand(MainFrame.EXIT_MENU_ITEM);
		m_defPolygon.setActionCommand(MainFrame.DEFINE_POLYGON_MENU_ITEM);
		m_triangle.setActionCommand(MainFrame.TRIANGLE_PLAY__MENU_ITEM);
		m_curve.setActionCommand(MainFrame.DEFINE_CURVE_MENU_ITEM);

	}
	private void setToolPanel() {
		toolPanel = new JPanel(new GridLayout(3,2));
		toolPanel.setPreferredSize(new Dimension(90,30));
		toolPanel.setMaximumSize(new Dimension(90,30));
		 iconButtonGroup = new IconButtonGroup(); //Viktor
		 //iconButton1 = new IconButton("icon/line.png");
		 iconButtonGroup.add(iconButton1);
		 iconButtonGroup.add(iconButton2);
		 toolPanel.add(iconButton1);
		 toolPanel.add(iconButton2);
		 toolPanel.add(Box.createHorizontalGlue());
		 toolPanel.add(Box.createHorizontalGlue());	
	}
	public void setCanvas(Canvas canvas) {
		remove(drawingcanvas);
		add(canvas,BorderLayout.CENTER);
		repaint();
		drawingcanvas=canvas;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		if(command.equals(MainFrame.EXIT_MENU_ITEM)) {
		}else if(command.equals(MainFrame.DEFINE_POLYGON_MENU_ITEM)) {
			this.setCanvas(new DefinePolyCanvas());
		}else if(command.equals(MainFrame.TRIANGLE_PLAY__MENU_ITEM)) {
			this.setCanvas(new TriangleCanvas());
		}else if(command.equals(MainFrame.DEFINE_CURVE_MENU_ITEM)) {
			this.setCanvas(new InterpolateCanvas());
		}
	}
	
}
