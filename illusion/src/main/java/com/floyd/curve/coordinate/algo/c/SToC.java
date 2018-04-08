package com.floyd.curve.coordinate.algo.c;

import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;

@Component("s2c")
public class SToC implements IToCSystem {

    /**
     * 小s→小c
       sA,B,C,D←→c前一组倒数第二个数减去倒数第四个数,前一组倒数第一个数减去前一组倒数第三个数,A,B,C,D
     */
	@Override
	public boolean change(PointBean cp, PointBean prevP) {
		
		double[] result = new double[6];
		result[0] = prevP.getC5() - prevP.getC3();
		result[1] = prevP.getC6() - prevP.getC4();
		result[2] = cp.getArrayCoordinate().get(0);
		result[3] = cp.getArrayCoordinate().get(1);
		result[4] = cp.getArrayCoordinate().get(2);
		result[5] = cp.getArrayCoordinate().get(3);
		
		return cp.wind(result);
	}

}
