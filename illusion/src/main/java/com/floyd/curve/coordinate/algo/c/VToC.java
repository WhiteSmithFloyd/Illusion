package com.floyd.curve.coordinate.algo.c;

import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;

@Component("v2c")
public class VToC implements IToCSystem {

    /**
     * 小v→小c
		(错误!!!)vA←→c0,0,前一组路径曲线小c的倒数第二个值,A,前一组路径曲线小c的倒数第二个值,A
		(更正)vA←→c0,0,0,A,00,A
     */
	@Override
	public boolean change(PointBean cp, PointBean prevP) {
		
		double[] result = new double[6];
		result[0] = 0;
		result[1] = 0;
		result[2] = 0;
		result[3] = cp.getArrayCoordinate().get(0);
		result[4] = 0;
		result[5] = cp.getArrayCoordinate().get(0);
		
		return cp.wind(result);
	}

}
