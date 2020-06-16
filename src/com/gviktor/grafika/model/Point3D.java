package com.gviktor.grafika.model;

public class Point3D extends Point {
	protected float z;
	public Point3D(float x, float y, float z){
		this.x = x; this.y = y;this.z = z;
	}
	public Point3D() {
		this.x = 0.00f; this.y = 0.00f;this.z = 0.00f;	
	}
	protected void setCoordinates(float x,float y, float z) {
		super.setCoordinates(x, y);
		this.z = z;
	}
}
