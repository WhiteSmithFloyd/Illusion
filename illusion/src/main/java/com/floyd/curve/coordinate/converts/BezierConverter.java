package com.floyd.curve.coordinate.converts;

import com.floyd.curve.bean.PointBean;

public class BezierConverter implements IBezierAlgoService {

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

	public void launch(PointBean curPoint, PointBean prevPoint) {
		init(this);
		
		this.curPoint = curPoint;
		this.prevPoint = prevPoint;
		convert.convertToBezier();
	}

	private IBezierAlgoService convert = null;
	
	private void init(IBezierAlgoService convert) {
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
	}
	
	public void changeFromAbs() {
		return ;
	}

	public static void main(String[] args) {
		PointBean point = new PointBean();
		point.setAlphaStr("123test");
		PointBean prevPoint = new PointBean();
		prevPoint.setAlphaStr("prev Point");
		BezierConverter aa = new BezierConverter();
		
		aa.launch(point, prevPoint);
		
		System.out.println("==========================");
		point = new PointBean();
		point.setAlphaStr("abc123");
		
		prevPoint = new PointBean();
		prevPoint.setAlphaStr("Prev 123");
		aa.launch(point, prevPoint);
		
	}
}
