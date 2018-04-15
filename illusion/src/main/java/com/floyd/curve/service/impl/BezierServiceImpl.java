package com.floyd.curve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.floyd.curve.bean.CircleBean;
import com.floyd.curve.bean.PointBean;
import com.floyd.curve.bean.PolygonBean;
import com.floyd.curve.bean.RectBean;
import com.floyd.curve.coordinate.converts.impl.CircleBezierConverter;
import com.floyd.curve.coordinate.converts.impl.PointBezierConverter;
import com.floyd.curve.coordinate.converts.impl.PolygonBezierConverter;
import com.floyd.curve.coordinate.converts.impl.RectBezierConverter;
import com.floyd.curve.service.ICurveService;
import com.floyd.curve.utils.CurveUtils;
import com.floyd.curve.utils.PointUtils;

@Service("BezierService")
public class BezierServiceImpl implements ICurveService {

	/** 
	 * convert to Bezier from string of normal Point
	 */
	public String draw4Point(String pointSrc) {
		try {
			
			if(StringUtils.isEmpty(pointSrc)) {
				throw new Exception("Point could NOT be empty..");
			}
			// parse the string inputed and convert to PointBean
			List<PointBean> ppList = PointUtils.fetchPoint(pointSrc);
			
			return pointToBezier(ppList);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/** 
	 * convert to Bezier from string of Circle
	 */
	public String draw4Circle(String circleSrc)  {
		try {
			if(StringUtils.isEmpty(circleSrc)) {
					throw new Exception("Circle could NOT be empty..");
			}
			
			CircleBean circle = CurveUtils.fetchCircle(circleSrc);		
			CircleBezierConverter convert = new CircleBezierConverter();
			convert.setCircle(circle);
			convert.convertToBezier();
			
			return pointToBezier(circle.getPointList());
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * convert to Bezier from string of Rect
	 */
	public String draw4Rect(String rectStr) {
		try {
			if(StringUtils.isEmpty(rectStr)) {
				throw new Exception("Rect String could NOT be empty..");
			}
			
			RectBean rect = CurveUtils.fetchRect(rectStr);		
			RectBezierConverter convert = new RectBezierConverter();
			convert.setRect(rect);
			convert.convertToBezier();
			
			return pointToBezier(rect.getPointList());
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	/**
	 * convert to Bezier from string of Polygon
	 */
	public String draw4Polygon(String polyStr) {
		try {
			if(StringUtils.isEmpty(polyStr)) {
				throw new Exception("Polygon String could NOT be empty..");
			}
			
			PolygonBean poly = new PolygonBean();	
			poly.setOriginStr(polyStr);
			
			PolygonBezierConverter convert = new PolygonBezierConverter();
			convert.setPoly(poly);
			convert.convertToBezier();
			
			return pointToBezier(poly.getPointList());
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/**
	 * Convert point to Bezier
	 * @param pointSrc
	 * @return
	 * @throws Exception
	 */
	private String pointToBezier(List<PointBean> ppList) throws Exception {
		
		PointBezierConverter convert = new PointBezierConverter();
		if(ppList==null || ppList.size()==0) {
			return "Wrong! Calculation Has Exception!";
		}
		
		PointBean virtualPoint = firstP2c();
		for(int i=0; i<ppList.size(); i++) {
			
			// for Absolute coordinate
			if(i==0) {
				ppList.get(i).calcAndUpdateAbsXY(null);
				System.out.println("---------absX:" + ppList.get(i).getAbsX()
						+ ",absY:" + ppList.get(i).getAbsY());
				continue;
			}
			
			// for Absolute coordinate
			if(i==1) {
				virtualPoint.calcAndUpdateAbsXY(ppList.get(i-1)); 
				System.out.println("========absX:" + (i==1 ? virtualPoint.getAbsX() : ppList.get(i-1).getAbsX())
						+ ",absY:" + (i==1 ? virtualPoint.getAbsY() : ppList.get(i-1).getAbsY()));
				ppList.get(i).calcAndUpdateAbsXY(virtualPoint);
			} else {
				ppList.get(i).calcAndUpdateAbsXY(ppList.get(i-1));
			}
			
			System.out.println("========absX:" + ppList.get(i-1).getAbsX()
					+ ",absY:" + ppList.get(i-1).getAbsY());
			convert.launch(ppList.get(i), i==1 ? virtualPoint : ppList.get(i-1));
		}
		
		return pointListToStr(ppList);
	}
	
	/**
	 * fill up a virtual point with six 0 coordinate within c system
	 * @param p0
	 * @return
	 */
	private PointBean firstP2c() {
		PointBean vp = new PointBean();
		vp.setAlphaStr("c");
		vp.wind(0, 0, 0, 0, 0, 0);
		List<Double> arr = new ArrayList<Double>();
		for(int l=0; l<6; l++) {
			arr.add(0d);
		}
		vp.setArrayCoordinate(arr);
		return vp;
	}

	private String pointListToStr(List<PointBean> ppList) {
		StringBuilder result = new StringBuilder();
		if(ppList == null || ppList.size() ==0) {
			return "Error, Point List is Empty, Please Check!";
		}
		
		PointBean pp =  null;
		for (int i = 0; i < ppList.size(); i++) {
			pp = ppList.get(i);
			// if its first point which usually starts with "M"
			if(pp.isBeginPoint()) {
				result.append(pp.getAlphaStr())
					.append(pp.getArrayCoordinate().get(0))
					.append(",")
					.append(pp.getArrayCoordinate().get(1));
					
				continue;
			}
			
			// if its last point which usually starts with "z"
			if(pp.isEndPoint()) {
				result.append(pp.getAlphaStr());
				continue;
			}
			
			result.append("c")
				.append(pp.getC1())
				.append(",")
				.append(pp.getC2())
				.append(",")
				.append(pp.getC3())
				.append(",")
				.append(pp.getC4())
				.append(",")
				.append(pp.getC5())
				.append(",")
				.append(pp.getC6());
		}
		return result.toString();
	}
	
}
