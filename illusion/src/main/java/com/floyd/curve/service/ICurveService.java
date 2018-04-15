package com.floyd.curve.service;

import org.springframework.stereotype.Service;

@Service
public interface ICurveService {

	public String draw4Point(String src);
	
	public String draw4Circle(String src);
	
	public String draw4Rect(String src);
	
	public String draw4Polygon(String src);
	
}
