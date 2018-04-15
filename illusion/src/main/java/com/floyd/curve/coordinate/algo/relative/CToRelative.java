package com.floyd.curve.coordinate.algo.relative;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToRelative;


@Component("relativeC")
public class CToRelative implements IToRelative {

	@Override
	public boolean relative(PointBean curP, PointBean prevP) {
		if(StringUtils.isAllLowerCase(curP.getAlphaStr())) {
			System.out.println("==[debug] SKIP... for related point ");
			return true;
		}
		
		// deal with absolute coordinate
		
		// Ca,b,c,d,e,f -> c(a-prevX, b-prevY, c-prevX, d-prevY, e-prevX, f-prevY)
		// checking
		if(curP.getArrayCoordinate().size()<6) {
			for(int i=0;i<6;i++) {
				curP.getArrayCoordinate().set(i, 0d);
			}
		}
		double item = 0;
		for(int i=0; i<6; i++) {
			item = curP.getArrayCoordinate().get(i);
			// for x
			if(i%2==0) 
				curP.getArrayCoordinate().set(i, item - prevP.getAbsX());
			else	// for y 
				curP.getArrayCoordinate().set(i, item - prevP.getAbsY());
		}

		// change alphabet string from Upper Case to lower case
		curP.setAlphaStr(StringUtils.lowerCase(curP.getAlphaStr()));
		return true;
	}

}
