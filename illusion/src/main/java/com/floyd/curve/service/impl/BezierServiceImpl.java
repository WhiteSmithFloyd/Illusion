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
	 * @throws Exception 
	 */
	public String draw4Point(String pointSrc) throws Exception {
		if(StringUtils.isEmpty(pointSrc)) {
			throw new Exception("Point could NOT be empty..");
		}
		
		// parse the string inputed and convert to PointBean
		List<PointBean> ppList = PointUtils.fetchPoint(pointSrc);
		
		return pointToBezierToStr(ppList);
	}
	
	/** 
	 * convert to Bezier from string of Circle
	 * @throws Exception 
	 */
	public String draw4Circle(String circleSrc) throws Exception  {
		if(StringUtils.isEmpty(circleSrc)) {
				throw new Exception("Circle could NOT be empty..");
		}
		
		CircleBean circle = CurveUtils.fetchCircle(circleSrc);		
		CircleBezierConverter convert = new CircleBezierConverter();
		convert.setCircle(circle);
		convert.convertToBezier();
		
		return pointToBezierToStr(circle.getPointList());
	}
	
	/**
	 * convert to Bezier from string of Rect
	 * @throws Exception 
	 */
	public String draw4Rect(String rectStr) throws Exception {
		if(StringUtils.isEmpty(rectStr)) {
			throw new Exception("Rect String could NOT be empty..");
		}
		
		RectBean rect = CurveUtils.fetchRect(rectStr);		
		RectBezierConverter convert = new RectBezierConverter();
		convert.setRect(rect);
		convert.convertToBezier();
		
		return pointToBezierToStr(rect.getPointList());
	}
	
	
	/**
	 * convert to Bezier from string of Polygon
	 * @throws Exception 
	 */
	public String draw4Polygon(String polyStr) throws Exception {
		if(StringUtils.isEmpty(polyStr)) {
			throw new Exception("Polygon String could NOT be empty..");
		}
		
		PolygonBean poly = new PolygonBean();	
		poly.setOriginStr(polyStr);
		
		PolygonBezierConverter convert = new PolygonBezierConverter();
		convert.setPoly(poly);
		convert.convertToBezier();
		
		return pointToBezierToStr(poly.getPointList());
	}
	
	/**
	 * Animation of transfer, return JSON String
	 * @throws Exception 
	 */
	public String animate4Transfer(String pStrFrom, String pStrTo) throws Exception {
		if(StringUtils.isEmpty(pStrFrom)) {
				throw new Exception("[The From Curvse] String could NOT be empty..");
		}
		if(StringUtils.isEmpty(pStrTo)) {
			throw new Exception("[The To Curvse] String could NOT be empty..");
		}
		
		// parse the string inputed and convert to PointBean
		List<PointBean> ppFromList = PointUtils.fetchPoint(pStrFrom);
		ppFromList = pointToBezier(ppFromList);
		
		List<PointBean> ppToList = PointUtils.fetchPoint(pStrTo);
		ppToList = pointToBezier(ppToList);
		
		int fromSize = ppFromList.size();
		int toSize = ppToList.size();
		
		if(fromSize ==0 || toSize ==0) {
			throw new Exception("From Point or To Point bizier changing Exception");
		}
		
		int ft = fromSize - toSize;
		if(ft == 0) {
			return "{bFromPoint:{bData: " +pointListToStr(ppFromList)+", length:" + fromSize + "}"
					+", bToPoint:{bData: " +pointListToStr(ppToList)+ ", length:" + toSize +"}}";
		}
		
		if(ft > 0) {
			ppFromList = fillup(ppFromList, ft);
		} else {
			ppToList = fillup(ppToList, -ft);
		}
		
		return "{bFromPoint:{bData: " +pointListToStr(ppFromList)+", length:" + fromSize + "}"
			+", bToPoint:{bData: " +pointListToStr(ppToList)+ ", length:" + toSize +"}}";
	}
	
	/**
	 * Fill Up Point for animation.
	 * @param ppList
	 * @param length
	 * @return
	 */
	private List<PointBean> fillup(List<PointBean> ppList, int length) {
		List<PointBean> rsList = new ArrayList<PointBean>();
		
		for(int i=0; i<ppList.size(); i++) {
			if(i==0) {
				rsList.add(ppList.get(i));
				continue;
			}
			
			for(int count=0; count<length; count ++) {
				rsList.add(new PointBean());
			}
			rsList.add(ppList.get(i));
		}
		return rsList;
	}
	
	/**
	 * Convert point to Bezier
	 * @param pointSrc
	 * @return
	 * @throws Exception
	 */
	private List<PointBean> pointToBezier(List<PointBean> ppList) throws Exception {
		
		PointBezierConverter convert = new PointBezierConverter();
		if(ppList==null || ppList.size()==0) {
			throw new Exception("Wrong! Calculation Has Exception!");
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
		
		return ppList;
	}
	
	/**
	 * Convert point to Bezier
	 * @param pointSrc
	 * @return
	 * @throws Exception
	 */
	private String pointToBezierToStr(List<PointBean> ppList) throws Exception {
		return pointListToStr(pointToBezier(ppList));
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
				.append(pp.getC1toStr())
				.append(",")
				.append(pp.getC2toStr())
				.append(",")
				.append(pp.getC3toStr())
				.append(",")
				.append(pp.getC4toStr())
				.append(",")
				.append(pp.getC5toStr())
				.append(",")
				.append(pp.getC6toStr());
		}
		return result.toString();
	}
	
}
