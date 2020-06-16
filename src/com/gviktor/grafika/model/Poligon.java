package com.gviktor.grafika.model;

import java.util.ArrayList;
import java.util.List;

public class Poligon {
	protected List<Point> vertexes;
	public Poligon() {
		vertexes= new ArrayList<Point>();
	}
	public void addVertex(Point a) {
		this.vertexes.add(a);
	}
	public void clear() {
		this.vertexes.removeAll(vertexes);
	}
	public int size() {
		return vertexes.size();
	}
	public Point getVertexOfIndex(int index) {
		return vertexes.get(index);
	}

}
