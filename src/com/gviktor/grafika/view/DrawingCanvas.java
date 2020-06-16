package com.gviktor.grafika.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.gviktor.grafika.model.CoordinateComputer;

public class DrawingCanvas extends Canvas {
	protected int width,height;
	protected float x0,y0,rWidth=10.0f,rHeight=10.0f,pixelSize;
	public void computeSizes() {
		this.setMinimumSize(new Dimension(300,300));
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(this.getPreferredSize());
		Dimension d = this.getSize();
		this.width=(int)d.getWidth()-1;
		this.height=(int)d.getHeight()-1;
		pixelSize=Math.max(rWidth/width,rHeight/height);
		CoordinateComputer.centerX=width/2;
		CoordinateComputer.centerY=height/2;
		CoordinateComputer.pixelSize=pixelSize;
		CoordinateComputer.maxY=height;
	}
	public void initG(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
	}
}
