package com.gviktor.grafika.model;

import java.util.ArrayList;

public class LagrangeInterpolation extends Interpolation{

	public LagrangeInterpolation() {
		this.control_points = new ArrayList<Point>();
	}
	public float li(float x, int i) {
		int n= numOfControlpoints();
		float up=1.0f,down=1.0f;
		float xi =control_points.get(i).getX();
		float result=1.0f;
		if(n >=2) {
			for(int j = 0; j < n;j++) {
				float xj  = control_points.get(j).getX();
				if(i!=j) {up=(x-xj);down=(xi-xj);result*=up/down;}
			}
		}
		return result;
	}
	public float computeCoordAtx(float x) {
		int n= numOfControlpoints();
		float fx=0.0f;
		if (n >=2) {
			for (int i=0; i< n ;i++ ) {
				float yi  = control_points.get(i).getY();
				fx+=li(x,i)*yi;
			}
		}
		return fx;
	}
}
