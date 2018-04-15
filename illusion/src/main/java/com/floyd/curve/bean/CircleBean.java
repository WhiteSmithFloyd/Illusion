package com.floyd.curve.bean;

import java.util.List;

public class CircleBean {

	public static double CIRCLE_T = 0.551915024494d;
	
	private String originStr ; 
	private String x;
	private String y;
	private String r;

	private List<PointBean> pointList = null;
	
	public String getOriginStr() {
		return originStr;
	}
	public void setOriginStr(String originStr) {
		this.originStr = originStr;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public List<PointBean> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointBean> pointList) {
		this.pointList = pointList;
	}
	
}
