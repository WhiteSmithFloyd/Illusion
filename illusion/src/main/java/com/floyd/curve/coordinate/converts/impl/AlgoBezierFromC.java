package com.floyd.curve.coordinate.converts.impl;

import org.apache.commons.lang3.StringUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;
import com.floyd.curve.coordinate.algo.IToRelative;
import com.floyd.curve.coordinate.algo.c.CToC;
import com.floyd.curve.coordinate.algo.relative.CToRelative;
import com.floyd.curve.coordinate.converts.IPointConverter;

public class AlgoBezierFromC implements IPointConverter {
	
	public PointBean getCurPoint() {
		return algoService.getCurPoint();
	}

	public PointBean getPrevPoint() {
		return algoService.getPrevPoint();
	}
	
	private IPointConverter algoService ; 
	
	public AlgoBezierFromC(IPointConverter algoService) {
		this.algoService = algoService;
	}
	
	private IToCSystem toCsysImpl ; 
	
	private IToRelative relativeImpl;
	
	public void convertToBezier() throws Exception {
		toCsysImpl = new CToC();
		relativeImpl = new CToRelative();
		
		if(check()) {		
			// change point form absolute to related
			relativeImpl.relative(getCurPoint(), getPrevPoint());
			System.out.println("---------------SUCCESS for abs Point------------------");
			
			if(toCsysImpl.change(getCurPoint(), getPrevPoint())) {
				System.out.println("==[SUCCESS] For[c] pointBean:" + getCurPoint());
			} else {
				System.out.println("==[FAIL] For[c] converting failed!! ");
			}
		} else {
//			System.out.println("== Current Point is not [c]");
		}
		algoService.convertToBezier();
	}
	
	private boolean check() {
		PointBean cp = getCurPoint();
		if(StringUtils.equalsIgnoreCase(cp.getAlphaStr(), "c")) {
			return true;
		}
		return false;
	}

}
