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
	public static int countOrientation(Point a, Point b, Point c) {
		float orientation =orientation((Point2D)a,(Point2D)b,(Point2D)c);
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
	public static boolean isPolygonCounterClockwise(Poligon p) {
		int n = p.size(), k=0;
		Point pointK = p.getVertexOfIndex(k);
		for(int i = 1 ; i< n ; i++) { //looking for the smallest x point with smallest y
			Point pointI =  p.getVertexOfIndex(i);
			if(pointI.getX()<= pointK.getX() && (pointI.getX()< pointK.getX() ||pointI.getY() < pointK.getY()   )) {
				k=i;
			}
		}
		
		int previous =k-1;
		int next = k+1;
		if(previous==-1) {previous=n-1;}if(next == n) {next=0;}
		System.out.println("previous"+previous );

		pointK = p.getVertexOfIndex(k);
		Point pointPrevious=p.getVertexOfIndex(previous);
		Point pointNext=p.getVertexOfIndex(next);
		
		return (CoordinateComputer.countOrientation((Point2D)pointPrevious,(Point2D)pointK,(Point2D)pointNext) >0) ;
	}
	public static String isPolygonCounterClockwiseString(Poligon p) {
		if(isPolygonCounterClockwise(p)) {
			return "CounterClockwise";
		}
		return "not CounterClockwise";

	}
	public static double areaSize(Poligon p) {
		double size=0.0f;
		int n = p.size();
		int k= n-1;
		for(int i =0;i<n;i++) {
			Point pointK = p.getVertexOfIndex(k);
			Point pointi = p.getVertexOfIndex(i);
			size+=pointK.getX()*pointi.getY()-pointi.getX()*pointK.getY();
			k=i;
		}
		return size;
		
	}
	public static boolean insideTriangle(Point a, Point b, Point c, Point p) {
		return (countOrientation(a,b,p)>=0 && countOrientation(b,c,p)>=0 && countOrientation(c,a,p)>=0 );
	}
	public static boolean insidePolygon(Point p, Poligon polygon) {
		float x,y;
		boolean b = false;
		int n=polygon.size(),j=n-1;
		x= p.getX();y= p.getY();
		for(int i = 0; i<n;i++) {
			Point pointj = polygon.getVertexOfIndex(j);
			Point pointi  = polygon.getVertexOfIndex(i);
			if((pointj.getY() <= y && y < pointi.getY() && CoordinateComputer.countOrientation(pointj,pointi,p) > 0) || (pointi.getY() <= y && y < pointj.getY() && CoordinateComputer.countOrientation(pointi,pointj,p) > 0)) {
				b=!b;
			}
			j=i;
		}
		return b;
	}
	public static boolean isPointOnLineSegments(Point2D a,Point2D b, Point2D p) {
		float  pY=p.getY(), pX=p.getX(),aX =a.getX(), bX= b.getX(),aY=a.getY(),bY=b.getY(), distanceX=bX-aX, distanceY=aY-bY;
		float epsilon = 0.0001f *(distanceX*distanceX +distanceY * distanceY);
		boolean isPXCordBettweenSegment=(aX!=bX) &&(aX <= pX && pX <= bX || bX <= pX && pX <=aX );
		boolean isPYCordBetweenSegment=(aX==bX) && (aY <= pY && pY <= bY || bY <= pY && pY <=aY );
		return (isPXCordBettweenSegment||isPYCordBetweenSegment) && Math.abs(CoordinateComputer.countOrientation(a,b,p))< epsilon;
	}
	
	//Do the sameas isPointOnLineSegments() but in a different approach
	public static boolean isPointOnProjectionOfLine(Point2D a,Point2D b, Point2D p) {
		float  pY=p.getY(), pX=p.getX(),aX =a.getX(), bX= b.getX(),aY=a.getY(),bY=b.getY(), distanceX=bX-aX, distanceY=aY-bY;
		float len2=distanceX*distanceX +distanceY * distanceY;
		float epsilon = 0.0001f *len2;
		float innerProduct = distanceX*(pX-aX)- distanceY *(pY-aY);
		return innerProduct > -epsilon && innerProduct<len2+epsilon ;
	}
	public static void tessellatePolygon(Poligon p,Triangle[] triangles) {
		//Polygon vertexes must be counter clockwise with n vertexes it will create n-2 trinagles
		int n = p.size(),indexA,indexB,indexC; //Indexes of the three point of the triangle
		int j = n-1;
		int[] next = new int[n];
		for(int i = 0; i< n;i++) {
			next[j] =i;
			j=i;
		}
		
		for(int k=0;k<n-2;k++) {
			
		}
	
	}
	public static float distance2(Point a ,Point b) {
		float distance=0.0f;
		float  aX =a.getX(), bX= b.getX(),aY=a.getY(),bY=b.getY(), distanceX=bX-aX, distanceY=aY-bY;
		distance=distanceX*distanceX +distanceY * distanceY;
		return distance;
	}
}
