package com.gviktor.grafika.model;

public abstract class Point {
	protected float x, y;
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	protected void setCoordinates(float x,float y) {
		this.x = x;
		this.y = y;
	}
	public static float  distance(Point a, Point b) {
		float dx = (a.getX()-b.getX()),dy=(a.getY()-b.getY());
		return (float) Math.sqrt(dx*dx +dy *dy);
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
