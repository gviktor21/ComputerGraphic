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
import com.gviktor.grafika.model.Point2D;
import com.gviktor.grafika.model.Poligon;

public class DefinePolyCanvas extends DrawingCanvas {
	CanvasMouseAdapter mouseAdapter;
	private float x0,y0;
	boolean ready;
	Poligon poligon;
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
	}
	private class CanvasMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			float x =CoordinateComputer.deviceToLogicalIsoTropicX(e.getX());
			float y =CoordinateComputer.deviceToLogicalIsoTropicY(e.getY());
			if(ready) {//new Polygon
				poligon.clear();
				x0=x;
				y0=y;
				ready= false;
			}
			float dx = (x-x0),dy=(y-y0);
			if(poligon.size()>0 && dx*dx+dy*dy < difference*pixelSize*pixelSize) {
				poligon.addVertex(new Point2D(x0,y0));
				repaint();
				ready=true;
			}else {
				poligon.addVertex(new Point2D(x,y));
				System.out.println("add+ "+x+" "+ y);
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
