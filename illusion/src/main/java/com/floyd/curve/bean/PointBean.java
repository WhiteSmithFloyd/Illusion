package com.floyd.curve.bean;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PointBean {

	private String originStr ;
	/** The first letter of alphabet */
	private String AlphaStr; 
	
	/** the array of coordinate */
	private List<Double> arrayCoordinate;

	private double absX;
	private double absY;
	
	/**
	 * six coordinate of little c serial  
	 */
	private double c1 ; 
	private double c2 ; 
	private double c3 ; 
	private double c4 ; 
	private double c5 ; 
	private double c6 ; 
	
	public double getAbsX() {
		return absX ;
	}
	
	public double getAbsY() {
		return absY;
	}
	
	/**
	 * Calculate and Update the absolute coordinate X and Y
	 * @param prev
	 * @return
	 */
	public boolean calcAndUpdateAbsXY(PointBean prev) {
		if(!StringUtils.equals(this.AlphaStr, "M") && prev == null)
			return false;
		
		if(StringUtils.equals(this.AlphaStr, "M")) {
			absX = (arrayCoordinate==null || arrayCoordinate.size()<2) ? 
					0 : arrayCoordinate.get(0);
			absY = (arrayCoordinate==null || arrayCoordinate.size()<2) ? 
					0 : arrayCoordinate.get(1);
			return true;
		}

		absX = calcAbsXInc(prev);
		absY = calcAbsYInc(prev);
		return true;
	}
	

	/**
	 * @return
	 */
	public boolean wind(double[] coorArray) {
		if(coorArray == null || coorArray.length < 6) {
			return false;
		}
		return wind(coorArray[0], coorArray[1], coorArray[2], coorArray[3], coorArray[4], coorArray[5]);
	}
	
	public boolean wind(double c1, double c2, double c3, double c4, double c5, double c6) {
		if (!shuffle())
			return false;
		
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.c6 = c6;
		return true;
	}
	
	public boolean wind(List<Double> coorList) {
		if(coorList==null || coorList.size()<6) {
			return false; 
		}
		
		return wind(coorList.get(0), coorList.get(1), coorList.get(2), 
				coorList.get(3), coorList.get(4), coorList.get(5));
	}
	
	private double calcAbsXInc(PointBean prev) {
		// related coordinate
		if(StringUtils.isAllLowerCase(AlphaStr)) {
			
			if(StringUtils.equals(AlphaStr, "h")
					|| StringUtils.equals(this.AlphaStr, "l")) {
				return prev.getAbsX() + ((arrayCoordinate==null || arrayCoordinate.size()<1) ? 
						0 : arrayCoordinate.get(0));
			}
			
			if(StringUtils.equals(AlphaStr, "v")) {
				return prev.getAbsX();
			}
			
			if(StringUtils.equals(AlphaStr, "c")) {
				return prev.getAbsX() + ((arrayCoordinate==null || arrayCoordinate.size()<6) ? 
						0 : arrayCoordinate.get(4));
			}
			
			return 0;
		}
		
		// absolute coordinate
		if(StringUtils.equals(this.AlphaStr, "H")
			|| StringUtils.equals(this.AlphaStr, "L") ) {
			return (arrayCoordinate==null || arrayCoordinate.size()<1) ? 
					0 : arrayCoordinate.get(0);
		}
		
		if(StringUtils.equals(this.AlphaStr, "V")) {
			return prev.getAbsX();
		}
		
		if(StringUtils.equals(AlphaStr, "C")) {
			return (arrayCoordinate==null || arrayCoordinate.size()<6) ? 
					0 : arrayCoordinate.get(4);
		}
		return 0;
	}
	
	private double calcAbsYInc(PointBean prev) {
		// related coordinate
		if(StringUtils.isAllLowerCase(AlphaStr)) {
			
			if(StringUtils.equals(this.AlphaStr, "l")) {
				return prev.getAbsY() + ((arrayCoordinate==null || arrayCoordinate.size()<2) ? 
						0 : arrayCoordinate.get(1));
			}
			
			if(StringUtils.equals(AlphaStr, "h")) {
				return prev.getAbsY();
			}
			
			if(StringUtils.equals(this.AlphaStr, "v")) {
				return prev.getAbsY() + ((arrayCoordinate==null || arrayCoordinate.size()<1) ? 
						0 : arrayCoordinate.get(0));
			}
			
			if(StringUtils.equals(AlphaStr, "c")) {
				return prev.getAbsY() + ((arrayCoordinate==null || arrayCoordinate.size()<6) ? 
						0 : arrayCoordinate.get(5));
			}
			
			return 0;
		}
		
		// absolute coordinate
		if(StringUtils.equals(this.AlphaStr, "L") ) {
			return (arrayCoordinate==null || arrayCoordinate.size()<2) ? 
					0 : arrayCoordinate.get(1);
		}
		
		if(StringUtils.equals(this.AlphaStr, "H")) {
			return prev.getAbsY();
		}
		
		if(StringUtils.equals(this.AlphaStr, "V")) {
			return (arrayCoordinate==null || arrayCoordinate.size()<1) ? 
					0 : arrayCoordinate.get(0);
		}
		
		if(StringUtils.equals(AlphaStr, "C")) {
			return (arrayCoordinate==null || arrayCoordinate.size()<6) ? 
					0 : arrayCoordinate.get(5);
		}
		return 0;
	}
	
	/**
	 * @return 
	 */
	// TODO
	public boolean shuffle() {
		this.c1 = 0;
		this.c2 = 0;
		this.c3 = 0;
		this.c4 = 0;
		this.c5 = 0;
		this.c6 = 0;
		return true;
	}
	
	public String getOriginStr() {
		return originStr;
	}

	public void setOriginStr(String originStr) {
		this.originStr = originStr;
	}

	public String getAlphaStr() {
		return AlphaStr;
	}

	public void setAlphaStr(String alphaStr) {
		AlphaStr = alphaStr;
	}

	public List<Double> getArrayCoordinate() {
		return arrayCoordinate;
	}

	public void setArrayCoordinate(List<Double> arrayCoordinate) {
		this.arrayCoordinate = arrayCoordinate;
		if(isWindible())
			wind(arrayCoordinate);
	} 
	
	private boolean isWindible() {
		if(StringUtils.isNotEmpty(AlphaStr) 
				&& StringUtils.equals(AlphaStr, "c")
				&& arrayCoordinate != null 
				&& arrayCoordinate.size() >= 6) 
			return true;
		
		return false;
	}
	
	public double getC1() {
		return c1;
	}

	public double getC2() {
		return c2;
	}

	public double getC3() {
		return c3;
	}

	public double getC4() {
		return c4;
	}

	public double getC5() {
		return c5;
	}

	public double getC6() {
		return c6;
	}

	@Override
	public String toString() {
		return "PointBean [originStr=" + originStr + ", AlphaStr=" + AlphaStr + ", arrayCoordinate=" + arrayCoordinate
				+ ", absX=" + absX + ", absY=" + absY + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4
				+ ", c5=" + c5 + ", c6=" + c6 + "]";
	}
	
}
