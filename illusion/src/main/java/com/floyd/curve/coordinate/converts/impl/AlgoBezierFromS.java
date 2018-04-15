package com.floyd.curve.coordinate.converts.impl;

import org.apache.commons.lang3.StringUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;
import com.floyd.curve.coordinate.algo.IToRelative;
import com.floyd.curve.coordinate.algo.c.SToC;
import com.floyd.curve.coordinate.algo.relative.SToRelative;
import com.floyd.curve.coordinate.converts.IPointConverter;

/**
 * Convert Point starting with little s to Bezier Point
 * @author Hevo
 */
public class AlgoBezierFromS implements IPointConverter {

	public PointBean getCurPoint() {
		return algoService.getCurPoint();
	}
	
	public PointBean getPrevPoint() {
		return algoService.getPrevPoint();
	}
	
	private IPointConverter algoService ; 
	
	public AlgoBezierFromS(IPointConverter algoService) {
		this.algoService = algoService;
	}
	
	private IToCSystem toCsysImpl ; 
	
	private IToRelative relativeImpl;
	
	public void convertToBezier() throws Exception {
//		System.out.println("This is for little s!");
//		System.out.println("point is !" + getCurPoint().getAlphaStr() + ";Prev:" + getPrevPoint().getAlphaStr());
//		System.out.println("---------------------------------");
	
		toCsysImpl = new SToC();
		relativeImpl = new SToRelative();
		
		if(check()) {
			// change point form absolute to related
			relativeImpl.relative(getCurPoint(), getPrevPoint());
			System.out.println("---------------SUCCESS for abs Point------------------");
			
			if(toCsysImpl.change(getCurPoint(), getPrevPoint())) {
				System.out.println("==[SUCCESS] For[s] pointBean:" + getCurPoint());
			} else {
				System.out.println("==[FAIL] For[s] converting failed!! ");
			}
		} else {
//			System.out.println("== Current Point is not [s]");
		}
		algoService.convertToBezier();
	}
	
	private boolean check() {
		PointBean cp = getCurPoint();
		if(StringUtils.equalsIgnoreCase(cp.getAlphaStr(), "s")) {
			return true;
		}
		return false;
	}

	
}
