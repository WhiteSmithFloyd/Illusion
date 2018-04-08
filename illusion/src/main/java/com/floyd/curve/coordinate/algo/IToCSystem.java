package com.floyd.curve.coordinate.algo;

import com.floyd.curve.bean.PointBean;

/**
 * Convert to related c system coordinate
 * @author hevo
 */
public interface IToCSystem {

	public boolean change(PointBean cp, PointBean prevP);
}
