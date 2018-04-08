package com.floyd.curve.coordinate.converts;

import org.springframework.stereotype.Service;

import com.floyd.curve.bean.PointBean;

@Service
public interface IBezierAlgoService {

	public void convertToBezier();
	
	public PointBean getCurPoint() ;
	
	public PointBean getPrevPoint() ;

}
