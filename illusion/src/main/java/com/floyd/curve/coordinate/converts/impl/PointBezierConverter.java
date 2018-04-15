package com.floyd.curve.coordinate.converts.impl;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.converts.IPointConverter;

public class PointBezierConverter implements IPointConverter {

	private PointBean prevPoint;
	private PointBean curPoint;
	
	public PointBean getCurPoint() {
		return curPoint;
	}
	
	public PointBean getPrevPoint() {
		return prevPoint;
	}

	public void convertToBezier() {
		// nothing need to do
		System.out.println("This is outter converter!! finish!");
	}

	public void launch(PointBean curPoint, PointBean prevPoint) throws Exception {
		init(this);
		
		this.curPoint = curPoint;
		this.prevPoint = prevPoint;
		convert.convertToBezier();
	}

	private IPointConverter convert = null;
	
	private void init(IPointConverter convert) {
		if(this.convert != null) 
			return ;
		
		// 4 s
		this.convert = new AlgoBezierFromS(convert);
		// 4 l
		this.convert = new AlgoBezierFromL(this.convert);
		// 4 h
		this.convert = new AlgoBezierFromH(this.convert);
		// 4 v
		this.convert = new AlgoBezierFromV(this.convert);
		// 4 c
		this.convert = new AlgoBezierFromC(this.convert);
	}
	
	public void changeFromAbs() {
		return ;
	}

	public static void main(String[] args) throws Exception {
		PointBean point = new PointBean();
		point.setAlphaStr("123test");
		PointBean prevPoint = new PointBean();
		prevPoint.setAlphaStr("prev Point");
		PointBezierConverter aa = new PointBezierConverter();
		
		aa.launch(point, prevPoint);
		
		System.out.println("==========================");
		point = new PointBean();
		point.setAlphaStr("abc123");
		
		prevPoint = new PointBean();
		prevPoint.setAlphaStr("Prev 123");
		aa.launch(point, prevPoint);
		
	}
}
