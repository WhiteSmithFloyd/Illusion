package com.floyd.curve.coordinate.converts.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.bean.RectBean;
import com.floyd.curve.coordinate.converts.IBezierAlgoService;

public class RectBezierConverter implements IBezierAlgoService {

	private RectBean rect = null;
	
	public RectBean getRect() {
		return rect;
	}
	public void setRect(RectBean rect) {
		this.rect = rect;
	}

	@Override
	public void convertToBezier() {
		if(rect == null) {
			rect = new RectBean();
		}
		List<PointBean> result = genPoint(rect);
		rect.setPointList(result);
	}

	private List<PointBean> genPoint(RectBean rect) {
		
		List<PointBean> result = new ArrayList<PointBean>();
		
		String rx = StringUtils.isEmpty(rect.getX()) ? "0" : rect.getX();
		String ry = StringUtils.isEmpty(rect.getY()) ? "0" : rect.getY();
		String rw = StringUtils.isEmpty(rect.getWidth()) ? "0" : rect.getWidth();
		String rh = StringUtils.isEmpty(rect.getHeight()) ? "0" : rect.getHeight();
		
		double x = NumberUtils.toDouble(rx);
		double y = NumberUtils.toDouble(ry);
		double w = NumberUtils.toDouble(rw);
		double h = NumberUtils.toDouble(rh);
		
		// for m 	M	x	y
		double ma = x;
		double mb = y;
		
		PointBean m = new PointBean();
		m.setAlphaStr("M");
		m.setOriginStr("M" + round4String(ma) 
				+ (mb<0 ? round4String(mb) : "," + round4String(mb)));
		
		List<Double> coorList = new ArrayList<Double>();
		coorList.add(ma);
		coorList.add(mb);
		m.setArrayCoordinate(coorList);
		result.add(m);
		
		// for c1 	c	0	0	width	0	width	0
		PointBean c1 = genCPoint(round4Double(0),
				round4Double(0),
				round4Double(w),
				round4Double(0),
				round4Double(w),
				round4Double(0));
		result.add(c1);
		
		// for c2 	c	0	0	0	height	0 	height
		PointBean c2 = genCPoint(round4Double(0),
				round4Double(0),
				round4Double(0),
				round4Double(h),
				round4Double(0),
				round4Double(h));
		result.add(c2);
		
		// for c3 	c	0	0	-width	0	-width	0
		PointBean c3 = genCPoint(round4Double(0),
				round4Double(0),
				round4Double(-w),
				round4Double(0),
				round4Double(-w),
				round4Double(0));
		result.add(c3);
		
		// for c4 	c	0	0	0	-height	0	-height
		PointBean c4 = genCPoint(round4Double(0),
				round4Double(0),
				round4Double(0),
				round4Double(-h),
				round4Double(0),
				round4Double(-h));
		result.add(c4);
		
		return result;
	}
	
	private double round4Double(double f) {
		BigDecimal bg = new BigDecimal(f).setScale(3, RoundingMode.UP);
		return bg.doubleValue();
	}
	
	private String round4String(double f) {
		return String.format("%.2f", f);
	}
	
	
	private PointBean genCPoint(double a, double b, double c, double d, double e, double f) {
		PointBean pp = new PointBean();
		pp.setAlphaStr("c");
		List<Double> coorList = new ArrayList<Double>();
		coorList.add(a);
		coorList.add(b);
		coorList.add(c);
		coorList.add(d);
		coorList.add(e);
		coorList.add(f);
		pp.setArrayCoordinate(coorList);
		
		StringBuilder sb = new StringBuilder();
		sb.append("c");
		sb.append(a);
		if(b >= 0)
			sb.append(",");
		sb.append(b);
		if(c >= 0)
			sb.append(",");
		sb.append(c);
		if(d >= 0)
			sb.append(",");
		sb.append(d);
		if(e >= 0)
			sb.append(",");
		sb.append(e);
		if(f >= 0)
			sb.append(",");
		sb.append(f);
		pp.setOriginStr(sb.toString());
		return pp;
	}
	
}
