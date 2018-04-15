package com.floyd.curve.bean;

import java.util.List;

public class RectBean {

	private String originStr ; 
	private String x;
	private String y;
	private String width;
	private String height;

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
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
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
	public List<PointBean> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointBean> pointList) {
		this.pointList = pointList;
	}
	
}
