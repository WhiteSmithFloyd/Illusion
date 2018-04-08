package com.floyd.curve.coordinate.algo;

import com.floyd.curve.bean.PointBean;

/**
 * change Coordinate to relative 
 * @author hevo
 */
public interface IToRelative {

	public boolean relative(PointBean curP, PointBean prevP);
}
