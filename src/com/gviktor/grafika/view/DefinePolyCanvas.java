package com.gviktor.grafika.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.gviktor.grafika.model.CoordinateComputer;
import com.gviktor.grafika.model.Point;
import com.gviktor.grafika.model.Point2D;
import com.gviktor.grafika.model.Poligon;

public class DefinePolyCanvas extends DrawingCanvas {
	CanvasMouseAdapter mouseAdapter;
	private float x0,y0;
	boolean ready;
	Poligon poligon;
	Point p;
	public static int difference=20;
	
	public DefinePolyCanvas() {
		computeSizes();
		mouseAdapter = new CanvasMouseAdapter();
		this.addMouseListener(mouseAdapter);
		ready = true;
		poligon= new Poligon();
	}
	public void paint(Graphics g) {
		initG(g);
		g.setColor(Color.BLACK);
		int n =poligon.size();
		if(n>0) {
			Point2D a =(Point2D) poligon.getVertexOfIndex(0);
			int x = CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
			int y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
			g.drawRect(x-2, y-2, 4, 4);
			for(int i = 0; i < n;i++) {
				if(i==n && !ready) {break;}
				Point2D b =(Point2D) poligon.getVertexOfIndex(i);
				x = CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
				y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
				g.drawLine(x, y, CoordinateComputer.logicalToDeviceIsotropicX(b.getX()),CoordinateComputer.logicalToDeviceIsotropicY(b.getY()));
				a=b;
			}
		}
		if(p!= null) {
			g.setColor(Color.RED);
			int x = CoordinateComputer.logicalToDeviceIsotropicX(p.getX());
			int y = CoordinateComputer.logicalToDeviceIsotropicY(p.getY());
			g.drawRect(x-2, y-2, 4, 4);
		}
	}
	private class CanvasMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			float x =CoordinateComputer.deviceToLogicalIsoTropicX(e.getX());
			float y =CoordinateComputer.deviceToLogicalIsoTropicY(e.getY());
			
			 if (e.getButton() == MouseEvent.BUTTON1) {
				 System.out.println("Mouse 1 pressed");
				 if(ready) {//new Polygon
						poligon.clear();
						x0=x;
						y0=y;
						ready= false;
						p=null;
					}
					float dx = (x-x0),dy=(y-y0);
					if(poligon.size()>0 && dx*dx+dy*dy < difference*pixelSize*pixelSize) {
						System.out.println("poligon is   "+ CoordinateComputer.isPolygonCounterClockwiseString(poligon));
						System.out.println(CoordinateComputer.areaSize(poligon));
						if(p!= null) {System.out.println("Is point is Inside Polygon? "+CoordinateComputer.insidePolygon(p,poligon));}
						poligon.addVertex(new Point2D(x0,y0));
						repaint();
						ready=true;
					}else {
						poligon.addVertex(new Point2D(x,y));
						System.out.println("add+ "+x+" "+ y);
					}
			 } else if(e.getButton() == MouseEvent.BUTTON3) {
				 // Add a p vertex for testing if it lies inside or outside of the polygon
				 System.out.println("Mouse 3 pressed");
				 p=new Point2D(x,y);
			 }
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
