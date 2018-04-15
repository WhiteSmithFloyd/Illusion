package com.floyd.curve.coordinate.converts;

import com.floyd.curve.bean.PointBean;

public interface IPointConverter extends IBezierAlgoService {
	
	public PointBean getCurPoint() ;
	
	public PointBean getPrevPoint() ;
}
