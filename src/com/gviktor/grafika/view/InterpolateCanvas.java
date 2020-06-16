package com.gviktor.grafika.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gviktor.grafika.model.CoordinateComputer;
import com.gviktor.grafika.model.Curve;
import com.gviktor.grafika.model.Interpolation;
import com.gviktor.grafika.model.LagrangeInterpolation;
import com.gviktor.grafika.model.Point2D;

public class InterpolateCanvas extends DrawingCanvas {

	CanvasMouseAdapter canvasMouseListener;
	Interpolation interPolatedCurve;
	Curve curve;
	public InterpolateCanvas() {
		canvasMouseListener = new CanvasMouseAdapter();
		this.addMouseListener(canvasMouseListener);
		interPolatedCurve= new LagrangeInterpolation();
		computeSizes();
		curve=new Curve();
	}
	public void paint(Graphics g) {
		super.initG(g);
		int n =interPolatedCurve.numOfControlpoints();
		if(n>0) {
			for(int i = 0; i < n;i++) {
				Point2D b =(Point2D) interPolatedCurve.getControlPointOfIndex(i);
				int x = CoordinateComputer.logicalToDeviceIsotropicX(b.getX());
				int y = CoordinateComputer.logicalToDeviceIsotropicY(b.getY());
				g.drawRect(x-2, y-2, 4, 4);
			}
			
		}
		if(n >= 2 ) {
			drawCurve(g);
		}
	}
	private void drawCurve(Graphics g) {
		curve.computeVertexesWithT();
		int n = curve.size();
		System.out.println("drawcurve: "+n);
		Point2D a =(Point2D) curve.getVertexOfIndex(0);
		System.out.println("0 x: "+a.getX()+" y: "+a.getY());
		int x = CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
		int y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
		for(int i = 1; i < n;i++) {
			Point2D b =(Point2D) curve.getVertexOfIndex(i);
			System.out.println(i+" x: "+b.getX()+" y: "+b.getY());
			x = CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
			y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
			g.drawLine(x, y, CoordinateComputer.logicalToDeviceIsotropicX(b.getX()),CoordinateComputer.logicalToDeviceIsotropicY(b.getY()));
			a=b;
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
			interPolatedCurve.addControlPoint(new Point2D(x,y));
			curve.addControlPointT(new Point2D(x,y));
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
