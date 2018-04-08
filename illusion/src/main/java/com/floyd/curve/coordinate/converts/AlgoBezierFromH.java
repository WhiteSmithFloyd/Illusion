package com.floyd.curve.coordinate.converts;

import org.apache.commons.lang3.StringUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;
import com.floyd.curve.coordinate.algo.IToRelative;
import com.floyd.curve.coordinate.algo.c.HToC;
import com.floyd.curve.coordinate.algo.relative.HToRelative;

public class AlgoBezierFromH implements IBezierAlgoService {

	public PointBean getCurPoint() {
		return algoService.getCurPoint();
	}
	
	public PointBean getPrevPoint() {
		return algoService.getPrevPoint();
	}
	
	private IBezierAlgoService algoService ; 
	
	public AlgoBezierFromH(IBezierAlgoService algoService) {
		this.algoService = algoService;
	}
	
	private IToCSystem toCsysImpl ; 
	
	private IToRelative relativeImpl;
	
	public void convertToBezier() {
//		System.out.println("This is convert of little h !");
//		System.out.println("point is !" + getCurPoint().getAlphaStr() + ";Prev:" + getPrevPoint().getAlphaStr());
//		System.out.println("---------------------------------");
		
		toCsysImpl = new HToC();
		relativeImpl = new HToRelative();
		
		if(check()) {	
			// change point form absolute to related
			relativeImpl.relative(getCurPoint(), getPrevPoint());
			System.out.println("---------------SUCCESS for abs Point------------------");
			
			if(toCsysImpl.change(getCurPoint(), getPrevPoint())) {
				System.out.println("==[SUCCESS] For[h] pointBean:" + getCurPoint());
			} else {
				System.out.println("==[FAIL] For[h] converting failed!! ");
			}
		} else {
//			System.out.println("== Current Point is not [h]");
		}
		algoService.convertToBezier();
	}
	
	private boolean check() {
		PointBean cp = getCurPoint();
		if(StringUtils.equalsIgnoreCase(cp.getAlphaStr(), "h")) {
			return true;
		}
		return false;
	}

}