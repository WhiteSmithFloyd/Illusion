package com.floyd.curve.coordinate.algo.c;

import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;

@Component("h2c")
public class HToC implements IToCSystem {

    /**
     * 小h→小c
        (错误!!!)hA←→c0,0,A,前一组路径曲线小c的最后一个值,A,前一组路径曲线小c的最后一个值
        (更正)hA←→c0,0,A,0,A,0
     */
	@Override
	public boolean change(PointBean cp, PointBean prevP) {
		
		double[] result = new double[6];
		result[0] = 0;
		result[1] = 0;
		result[2] = cp.getArrayCoordinate().get(0);
		result[3] = 0;
		result[4] = cp.getArrayCoordinate().get(0);
		result[5] = 0;
		
		return cp.wind(result);
	}

}
