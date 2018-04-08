package com.floyd.curve.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.floyd.curve.bean.PointBean;


public class PointUtils {
	
	public static List<PointBean> fetchPoint(String pointSerial) {
		
//		d="M195,168c12-26,68-68,68-68h116v92l103-63s88,82,47,119s-332,61.3-334,8s0-88,0-88
		pointSerial = StringUtils.replace(pointSerial, "-", ",-");
		System.out.println("-----pointSerial:" + pointSerial);
		
		// (\-|\+)?\d+(\.\d+)?
//		String regex = "[a-zA-Z]([\\d,-]*)";
        String regex = "[a-zA-Z]([[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+,]*)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(pointSerial);
        
        List<PointBean> rsList = new ArrayList<PointBean>();
        String originStr ;
        while (mMatcher.find()) {
        	originStr = mMatcher.group(0);
        	System.out.println("====[Debug] originStr:" + originStr);
        	
        	if(!StringUtils.isEmpty(originStr)) {
        		PointBean pp = new PointBean();
        		pp.setOriginStr(originStr);
        		pp.setAlphaStr(retreiveAlpha(originStr));
        		pp.setArrayCoordinate(splitCoordinate(originStr));
        		rsList.add(pp);
        	}
        }
		return rsList;
	}
	
	/**
	 * retrieve first letter of alphabet from origin point string
	 * @param alpha
	 * @return
	 */
	public static String retreiveAlpha(String alpha) {
		if(!StringUtils.isEmpty(alpha) && alpha != null) {
			return alpha.substring(0, 1);
		}
		return "";
	}
	
	/**
	 * parse point string to coordinate integer array
	 * @param originStr
	 * @return
	 */
	private static List<Double> splitCoordinate(String originStr) {
		List<Double> coorArr = new ArrayList<Double>();
		
		originStr = originStr.substring(1);	// S242,115,242,115 -> 242,115,242,115
		if(StringUtils.isEmpty(originStr)) {
			return coorArr;
		}
		String[] xy = originStr.split(",");
		if(xy == null || xy.length==0 ) {
			return coorArr;
		} 
		
		for (int i=0; i<xy.length; i++) {
			if(StringUtils.isEmpty(xy[i])) 	// to escape empty string
				continue;
			coorArr.add(NumberUtils.parseNumber(xy[i], Double.class))  ;
		}
		System.out.println("===[Debug]After Parse:" + coorArr);
		return coorArr;
	}
	
	public static void main(String[] args) throws Exception {
		
		/*
		String pointSerial = "M195,168c12-26,68-68,68-68h116v92l103-63s88,82,47,119s-332,61.3-334,8s0-88,0-88";
		List<PointBean> ppList = PointUtils.fetchPoint(pointSerial);
		
		System.out.println(" finish! ");
		int[] rs = PointUtils.splitCoordinate("S242,115,242,115");
		for (int i : rs) {
			System.out.println(i);
		}
		System.out.println("-------------------");
		rs = PointUtils.splitCoordinate("V505");
		for (int i : rs) {
			System.out.println(i);
		}
		System.out.println("-------------------");
		rs = PointUtils.splitCoordinate("Z");
		for (int i : rs) {
			System.out.println(i);
		}
		
		String test = "M166,291H391V505l176,81s-44,140,0,192,208-8,208-8,149-489,57-529S242,115,242,115Z";
		
		String test = "-44,140,0,192,208,-8,208,-8,149,-489,57,-529";
		String[] rs = PointUtils.splitCoordinate(test);
		for (String str : rs) {
			System.out.println(str);
		}
		
		test = "505";
		rs = PointUtils.splitCoordinate(test);
		for (String str : rs) {
			System.out.println(str);
		}
		System.out.println(test);
		
		String test = "M166,291H391V505l176,81s-44,140,0,192,208-8,208-8,149-489,57-529S242,115,242,115Z";
		test = StringUtils.replace(test, "-", ",-");
		
//        String desStr = "{0=房源序号, 1=序号, 2=合同编号, 3=项目名称}";
//        String regex = "\\d=([^(,+})]*)";
        
        String regex = "[a-zA-Z]([\\d,-]*)";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(test);
        while (mMatcher.find()) {
        	System.out.println(mMatcher.group(0));
//            System.out.println(mMatcher.group(1));
        }
		 */
		
	}

}
