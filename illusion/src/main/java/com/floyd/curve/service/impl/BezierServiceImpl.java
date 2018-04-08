package com.floyd.curve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.converts.BezierConverter;
import com.floyd.curve.service.ICurveService;
import com.floyd.curve.utils.PointUtils;

@Service("BezierService")
public class BezierServiceImpl implements ICurveService {

	public String draw(String src) {
		try {
			return convertToc(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String convertToc(String pointSrc) throws Exception {
		
		if(StringUtils.isEmpty(pointSrc)) {
			throw new Exception("Point could NOT be empty..");
		}
		// parse the string inputed and convert to PointBean
		List<PointBean> ppList = PointUtils.fetchPoint(pointSrc);
		
		BezierConverter convert = new BezierConverter();
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
	 * fill up a virtual point with six 0 coordinate in c system
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
			if(StringUtils.equals(pp.getAlphaStr(), "M")) {
				result.append(pp.getAlphaStr())
					.append(pp.getArrayCoordinate().get(0))
					.append(",")
					.append(pp.getArrayCoordinate().get(1));
					
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
