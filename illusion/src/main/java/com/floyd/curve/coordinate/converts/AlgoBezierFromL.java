package com.floyd.curve.coordinate.converts;

import org.apache.commons.lang3.StringUtils;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;
import com.floyd.curve.coordinate.algo.IToRelative;
import com.floyd.curve.coordinate.algo.c.LToC;
import com.floyd.curve.coordinate.algo.relative.LToRelative;

public class AlgoBezierFromL implements IBezierAlgoService {

	public PointBean getCurPoint() {
		return algoService.getCurPoint();
	}
	
	public PointBean getPrevPoint() {
		return algoService.getPrevPoint();
	}
	
	private IBezierAlgoService algoService ; 
	
	public AlgoBezierFromL(IBezierAlgoService algoService) {
		this.algoService = algoService;
	}
	
	private IToCSystem toCsysImpl ; 
	
	private IToRelative relativeImpl;
	
	public void convertToBezier() {
//		System.out.println("This is convert of little l !");
//		System.out.println("point is !" + getCurPoint().getAlphaStr() + ";Prev:" + getPrevPoint().getAlphaStr());
//		System.out.println("---------------------------------");
		
		toCsysImpl = new LToC();
		relativeImpl = new LToRelative();
		
		if(check()) {	
			// change point form absolute to related
			relativeImpl.relative(getCurPoint(), getPrevPoint());
			System.out.println("---------------SUCCESS for abs Point------------------");
			
			if(toCsysImpl.change(getCurPoint(), getPrevPoint())) {
				System.out.println("==[SUCCESS] For[l] pointBean:" + getCurPoint());
			} else {
				System.out.println("==[FAIL] For[l] converting failed!! ");
			}
		} else {
//			System.out.println("== Current Point is not [l]");
		}			
		algoService.convertToBezier();
	}

	private boolean check() {
		PointBean cp = getCurPoint();
		if(StringUtils.equalsIgnoreCase(cp.getAlphaStr(), "l")) {
			return true;
		}
		return false;
	}

}
