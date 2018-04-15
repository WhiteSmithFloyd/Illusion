package com.floyd.curve.bean;

import java.util.List;

public class PolygonBean {

	private String originStr ; 

	private List<PointBean> pointList = null;
	
	public String getOriginStr() {
		return originStr;
	}
	public void setOriginStr(String originStr) {
		this.originStr = originStr;
	}
	public List<PointBean> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointBean> pointList) {
		this.pointList = pointList;
	}
	
}
