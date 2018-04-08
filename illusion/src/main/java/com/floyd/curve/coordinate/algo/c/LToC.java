package com.floyd.curve.coordinate.algo.c;

import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;

@Component("l2c")
public class LToC implements IToCSystem {

    /**
     * 小l→小c
       	前面补两个0，后面复制一组小l后面的两个值。  lA,B←→c0,0,A,B,A,B
     */
	@Override
	public boolean change(PointBean cp, PointBean prevP) {
		
		double[] result = new double[6];
		result[0] = 0;
		result[1] = 0;
		result[2] = cp.getArrayCoordinate().get(0);
		result[3] = cp.getArrayCoordinate().get(1);
		result[4] = cp.getArrayCoordinate().get(0);
		result[5] = cp.getArrayCoordinate().get(1);
		
		return cp.wind(result);
	}

}
