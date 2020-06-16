package com.gviktor.grafika.model;

import java.util.List;

public abstract class Interpolation {
	protected List<Point> control_points;
	public void addControlPoint(Point ctrl) {
		control_points.add(ctrl);
	}
	
	public List<Point> getControl_points() {
		return control_points;
	}

	public void setControl_points(List<Point> control_points) {
		this.control_points = control_points;
	}

	public void clear() {
		control_points.removeAll(control_points);
	}
	public int numOfControlpoints() {
		return control_points.size();
	}
	public Point getControlPointOfIndex(int i) {
		// TODO Auto-generated method stub
		return control_points.get(i);
	}
	public abstract float computeCoordAtx(float t);
}