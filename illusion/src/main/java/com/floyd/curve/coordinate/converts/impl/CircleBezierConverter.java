package com.floyd.curve.coordinate.converts.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.floyd.curve.bean.CircleBean;
import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.converts.IBezierAlgoService;

public class CircleBezierConverter implements IBezierAlgoService {

	private CircleBean circle = null;
	
	public CircleBean getCircle() {
		return circle;
	}
	
	public void setCircle(CircleBean circle) {
		this.circle = circle;
	}
	
	@Override
	public void convertToBezier() {
		if(circle == null) {
			circle = new CircleBean();
		}
		List<PointBean> result = genPoint(circle);
		circle.setPointList(result);
	}

	private List<PointBean> genPoint(CircleBean cir) {
		
		List<PointBean> result = new ArrayList<PointBean>();
		
		String cx = StringUtils.isEmpty(cir.getX()) ? "0" : cir.getX();
		String cy = StringUtils.isEmpty(cir.getY()) ? "0" : cir.getY();
		String cr = StringUtils.isEmpty(cir.getR()) ? "0" : cir.getR();
		
		double x = NumberUtils.toDouble(cx);
		double y = NumberUtils.toDouble(cy);
		double r = NumberUtils.toDouble(cr);
		
		// for m 	M	cx-r	cy
		double ma = x - r;
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
		
		// for c1 	c	0	-r*α	r*(1-α)	-r	r	-r
		PointBean c1 = genCPoint(round4Double(0),
				round4Double(-r * CircleBean.CIRCLE_T),
				round4Double(r * (1-CircleBean.CIRCLE_T)),
				round4Double(-r),
				round4Double(r),
				round4Double(-r));
		result.add(c1);
		
		// for c2 	c	r*α	0	r	r*(1-α)	r	r
		PointBean c2 = genCPoint(round4Double(r * CircleBean.CIRCLE_T),
				round4Double(0),
				round4Double(r),
				round4Double(r * (1 - CircleBean.CIRCLE_T)),
				round4Double(r),
				round4Double(r));
		result.add(c2);
		
		// for c3 	c	0	r*α		r*（α-1）	r	-r	r
		PointBean c3 = genCPoint(round4Double(0),
				round4Double(r * CircleBean.CIRCLE_T),
				round4Double(r * (CircleBean.CIRCLE_T - 1)),
				round4Double(r),
				round4Double(-r),
				round4Double(r));
		result.add(c3);
		
		// for c4 	c	-r*α	0	-r	r*(α-1)	-r	-r
		PointBean c4 = genCPoint(round4Double(-r * CircleBean.CIRCLE_T),
				round4Double(0),
				round4Double(-r),
				round4Double(r * (CircleBean.CIRCLE_T - 1)),
				round4Double(-r),
				round4Double(-r));
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
