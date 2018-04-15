package com.floyd.curve.coordinate.algo.c;

import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToCSystem;

@Component("c2c")
public class CToC implements IToCSystem {

    /**
     * 小c→小c
     */
	@Override
	public boolean change(PointBean cp, PointBean prevP) {
		// Nothing need to do
		return cp.wind();
	}

}
