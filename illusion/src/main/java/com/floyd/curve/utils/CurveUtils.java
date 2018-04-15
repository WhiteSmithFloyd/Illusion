package com.floyd.curve.utils;


import org.apache.commons.lang3.StringUtils;

import com.floyd.curve.bean.CircleBean;
import com.floyd.curve.bean.RectBean;

public class CurveUtils {

	/**
	 * Parse the circle string  
	 * @param circleStr
	 * @return
	 */
	public static CircleBean fetchCircle(String circleStr) {
		
		CircleBean result = new CircleBean();
		result.setOriginStr(circleStr);
		
		String[] circleArr ; 
		if(StringUtils.contains(circleStr, ",")) {
			circleArr = StringUtils.split(circleStr, ",");
		} else {
			circleArr = StringUtils.split(circleStr, " ");
		}
		
		if(circleArr == null || circleArr.length<3) {
			result.setX("0");
			result.setY("0");
			result.setR("0");
			return result; 
		}
		
		result.setX(circleArr[0]);
		result.setY(circleArr[1]);
		result.setR(circleArr[2]);
		return result;
	}
	
	public static RectBean fetchRect(String rectStr) {
		RectBean rect = new RectBean();
		rect.setOriginStr(rectStr);
		
		String[] rectArr ; 
		if(StringUtils.contains(rectStr, ",")) {
			rectArr = StringUtils.split(rectStr, ",");
		} else {
			rectArr = StringUtils.split(rectStr, " ");
		}
		
		if(rectArr == null || rectArr.length<4) {
			rect.setX("0");
			rect.setY("0");
			rect.setWidth("0");
			rect.setHeight("0");
			return rect; 
		}
		
		rect.setX(rectArr[0]);
		rect.setY(rectArr[1]);
		rect.setWidth(rectArr[2]);
		rect.setHeight(rectArr[3]);
		return rect ; 
	}
	
}
