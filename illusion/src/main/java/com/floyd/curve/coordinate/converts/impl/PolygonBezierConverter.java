package com.floyd.curve.coordinate.converts.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.bean.PolygonBean;
import com.floyd.curve.coordinate.converts.IBezierAlgoService;
import com.floyd.curve.utils.PointUtils;

public class PolygonBezierConverter implements IBezierAlgoService {

	private PolygonBean poly = null;
	
	public PolygonBean getPoly() {
		return poly;
	}
	public void setPoly(PolygonBean poly) {
		this.poly = poly;
	}

	@Override
	public void convertToBezier() throws Exception {
		if(poly == null) {
			poly = new PolygonBean();
		}
		// parse the string inputed and convert to PointBean
		List<PointBean> result = PointUtils.fetchPoint(switchToPoint(poly));
		poly.setPointList(result);
	}

	private String switchToPoint(PolygonBean poly) throws Exception {
		
		System.out.println("===poly.getOriginStr:" + poly.getOriginStr());
		
		// format: X1,Y1 X2,Y2 X3,Y3 X4,Y4 ……
		if(StringUtils.contains(poly.getOriginStr(), " ")) {
			
			String[] pair = StringUtils.split(poly.getOriginStr(), " ");
			StringBuilder sb = new StringBuilder();
			
			if(pair.length <=0) 
				throw new Exception("[Error] Polygan (blank) String is Wrong");
			
			for (int i = 0; i < pair.length; i++) {
				if(i==0) 
					sb.append("M");
				else
					sb.append("L");
				
				sb.append(genCoor(pair[i]));				
			}
			sb.append("L").append(genCoor(pair[0]));
			
			return sb.toString();
		}
		
		// format X1,Y1,X2,Y2,X3,Y3,X4,Y4 ……
		String[] x = StringUtils.split(poly.getOriginStr(), ",");
		// TODO size
		if(x.length <=0) 
			throw new Exception("[Error] Polygan (,) String is Wrong");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < x.length; i++) {
			if(i % 2 ==0) {
				if(i==0) 
					sb.append("M");
				else
					sb.append("L");
			}
			if(NumberUtils.toDouble(x[i]) < 0)
				sb.append(x[i]);
			else 
				sb.append(",").append(x[i]);
		}
		return sb.toString();
	}
	
	
	private String genCoor(String x) {
		String[] subPair = StringUtils.split(x, ",");
		
		String result = "";
		for(int i=0; i<subPair.length; i++) {
			if(NumberUtils.toDouble(subPair[i]) < 0) 
				result += subPair[i];
			else 
				result += ("," + subPair[i]);
		}
		return result;
	}
	
}
