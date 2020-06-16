package com.gviktor.grafika.model;

public class CoordinateComputer {
	public static int maxY,centerX,centerY;
	public static float pixelWidth,pixelHeight,pixelSize;
	public static int logicalToDeviceX(float x) {return Math.round(x);}
	public static int logicalToDeviceY(float y) {return maxY-Math.round(y);}
	public static float deviceToLogicalX(int x) {return (float)x;}
	public static float devicetoLogicalY(int y) {return (float)maxY-y;}
	
	public static int logicalToDeviceIsotropicX(float x) {return Math.round(centerX+x/pixelSize);}
	public static int logicalToDeviceIsotropicY(float y) {return Math.round(centerY-y/pixelSize);}
	public static float deviceToLogicalIsoTropicX(int x) {return (float)(x-centerX)*pixelSize;}
	public static float deviceToLogicalIsoTropicY(int y) {return (float)(centerY-y)*pixelSize;}
	public static int countOrientation(Point2D a, Point2D b, Point2D c) {
		float orientation =orientation(a,b,c);
		if(orientation>0) {
			return 1;
		}else if(orientation<0) {
			return -1;
		}else {
			return 0;
		}
	}
	public static float orientation(Point2D a, Point2D b, Point2D c) {
		return (a.getX()-c.getX())*(b.getY()-c.getY())-(a.getY()-c.getY())*(b.getX()-c.getX());
	}
}
