package com.gviktor.grafika.model;

import java.util.ArrayList;
import java.util.List;


public class Curve  extends Poligon{
	private int numOfBetweenVertexes;
	Interpolation interpolx;
	Interpolation interpoly;
	float numofControlPoints=0.0f;// t increme
	List<Point>controlPoints;
	private int fittingMethod;
	public static final int CURVE_FITTING_LAGRANGE=0;
	public static final int CURVE_FITTING_BEZIER=1;

	public Curve() {
		numOfBetweenVertexes=10;
		vertexes = new ArrayList<Point>();
		controlPoints = new ArrayList<Point>();
		interpolx= new LagrangeInterpolation();
		interpoly= new LagrangeInterpolation();
		fittingMethod=CURVE_FITTING_BEZIER;
		setfitting();
	}
	private void setfitting() {
		switch(fittingMethod) {
		case Curve.CURVE_FITTING_LAGRANGE :
			interpolx= new LagrangeInterpolation();
			interpoly= new LagrangeInterpolation();
			break;
		case CURVE_FITTING_BEZIER :
			interpolx= new Bezier();
			interpoly= new Bezier();
		break;
		default:
			interpolx= new LagrangeInterpolation();
			interpoly= new LagrangeInterpolation();
		}
	}
	
	public void addControlPoint(Point a) {
		interpolx.addControlPoint(a);
	}
	public void addControlPointT(Point a) {
		controlPoints.add(a);
		interpoly.addControlPoint(new Point2D(numofControlPoints,a.getY()));
		interpolx.addControlPoint(new Point2D(numofControlPoints,a.getX()));
		numofControlPoints+=1.0f;
	}
	public int getNumOfInnerVertexes() {
		return numOfBetweenVertexes;
	}
	public void setNumOfInnerVertexes(int numOfInnerVertexes) {
		this.numOfBetweenVertexes = numOfInnerVertexes;
	}
	public void computeVertexes() {
		clear();
		int numOfCtrlPoints=interpolx.numOfControlpoints();
		Point a =interpolx.getControl_points().get(0);
		vertexes.add(a);
		for (int i = 1;i<numOfCtrlPoints;i++){
			Point b =interpolx.getControl_points().get(i);
			computeInnerVertexesBetweenControlPoints(a ,b);
			vertexes.add(b);
			a=b;
		}
	}
	public void computeVertexesWithT() {
		super.clear();
		int numOfCtrlPoints=this.controlPoints.size();
		Point a =controlPoints.get(0);
		vertexes.add(a);
		switch(fittingMethod) {
		case Curve.CURVE_FITTING_LAGRANGE :
			for (int i = 1;i<numOfCtrlPoints;i++){
				Point b =controlPoints.get(i);
				computeInnerVertexesBetweenControlPointsWithT(a ,b,i-1);
				vertexes.add(b);
				a=b;
			}
			break;
		case CURVE_FITTING_BEZIER :
			for (int i = 1;i<numOfCtrlPoints;i++){
				Point b =controlPoints.get(i);
				computeInnerVertexesBetweenControlPointsWithT(a ,b,i-1);
				a=b;
			}
			vertexes.add(a);
		break;
		default:
			interpolx= new LagrangeInterpolation();
			interpoly= new LagrangeInterpolation();
		}
	}
	private void computeInnerVertexesBetweenControlPointsWithT(Point a , Point b,int t) {
		//float increment=Point.distance(a, b)/numOfBetweenVertexes;
		float increment = computeIncrementWithT();
		float currentT =computecurrentT(t,increment)+increment;
		for (int i=0;i<numOfBetweenVertexes-1;i++) {
			Point innerPoint;
			float x = interpolx.computeCoordAtx(currentT);
			float y = interpoly.computeCoordAtx(currentT);
			innerPoint= new Point2D(x,y);
			vertexes.add(innerPoint);
			currentT+=increment;
		}
	}
	public float computecurrentT(int t,float increment) {
		float currentT;
		switch(fittingMethod) {
		case Curve.CURVE_FITTING_LAGRANGE :
			currentT=t;
			break;
		case CURVE_FITTING_BEZIER :
			currentT= (t*numOfBetweenVertexes)*increment;
		break;
		default:
			currentT=t;
		}
		return currentT;
	}
	public float computeIncrementWithT() {
		float increment;
		switch(fittingMethod) {
		case Curve.CURVE_FITTING_LAGRANGE :
				increment= 0.1f;
			break;
		case CURVE_FITTING_BEZIER :
			increment= 1/(this.numOfBetweenVertexes*numofControlPoints);
		break;
		default:
			increment= 0.1f;
		}
		return increment;
	}
	
	private float computeIncrement(Point a , Point b) {
		float increment = Math.abs((a.getX()-b.getX()))/numOfBetweenVertexes;
		if(b.getX()< a.getX()) {
			increment*=-1;
		}
		return increment;
	}
	private void computeInnerVertexesBetweenControlPoints(Point a , Point b) {
		//float increment=Point.distance(a, b)/numOfBetweenVertexes;
		float increment = computeIncrement(a,b);
		System.out.println("a"+a+"b: "+b+"increment: "+increment);
		float currentx =a.getX()+increment;
		for (int i=0;i<numOfBetweenVertexes-1;i++) {
			Point innerPoint = new Point2D(currentx,((LagrangeInterpolation) interpolx).computeCoordAtx(currentx));
			vertexes.add(innerPoint);
			currentx+=increment;
		}

	}
	public void clear() {
		super.clear();
		controlPoints.clear();
		this.numofControlPoints=0.0f;
	}
}
