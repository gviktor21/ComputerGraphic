package com.gviktor.grafika.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.gviktor.grafika.model.CoordinateComputer;

public class TriangleCanvas extends Canvas {

	/**
	 * 
	 */
	private int width,height;
	private static final long serialVersionUID = 1L;
	public TriangleCanvas() {
		this.setMinimumSize(new Dimension(300,300));
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(this.getPreferredSize());
		Dimension d = this.getSize();
		this.width=(int)d.getWidth();
		this.height=(int)d.getHeight();
		CoordinateComputer.maxY=height;
		System.out.println(this.height+" "+ this.width);

	}
	public void initG(Graphics g) {
		Dimension d = this.getSize();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
	}
	public void paint(Graphics g) {
		initG(g);
		float xa,xb,ya,yb,xc,yc;
		float xa1,xb1,ya1,yb1,xc1,yc1;
		float sidequarter=this.width/4;
		float sideheight = this.height/4;
		xa=sidequarter;ya=sideheight;
		xb=(float) (sidequarter*2.0);yb=sideheight*3;
		xc=(float) (sidequarter*3.0);yc=sideheight;
		float q=0.05f;
		float p = 1-q;
		for(int i = 0; i < 50; i++) {
			g.drawLine(CoordinateComputer.logicalToDeviceX(xa), CoordinateComputer.logicalToDeviceY(ya), CoordinateComputer.logicalToDeviceX(xb), CoordinateComputer.logicalToDeviceY(yb));
			g.drawLine(CoordinateComputer.logicalToDeviceX(xb), CoordinateComputer.logicalToDeviceY(yb), CoordinateComputer.logicalToDeviceX(xc), CoordinateComputer.logicalToDeviceY(yc));
			g.drawLine(CoordinateComputer.logicalToDeviceX(xa), CoordinateComputer.logicalToDeviceY(ya), CoordinateComputer.logicalToDeviceX(xc), CoordinateComputer.logicalToDeviceY(yc));
			xa1= p*xa+q*xb;
			ya1 = p*ya+q*yb;
			xb1= p*xb+q*xc;
			yb1 = p*yb+q*yc;
			xc1= p*xc+q*xa;
			yc1 = p*yc+q*ya;
			xa=xa1;ya=ya1;xb=xb1;yb=yb1;xc=xc1;yc=yc1;
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
	}
}
