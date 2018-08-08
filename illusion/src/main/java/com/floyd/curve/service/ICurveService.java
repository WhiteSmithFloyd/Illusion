package com.floyd.curve.service;

import org.springframework.stereotype.Service;

@Service
public interface ICurveService {

	public String draw4Point(String src) throws Exception;
	
	public String draw4Circle(String src) throws Exception;
	
	public String draw4Rect(String src) throws Exception;
	
	public String draw4Polygon(String src) throws Exception;
	
	public String animate4Transfer(String pStrFrom, String pStrTo) throws Exception;

}
