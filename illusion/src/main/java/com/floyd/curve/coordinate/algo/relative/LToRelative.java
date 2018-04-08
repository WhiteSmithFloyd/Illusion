package com.floyd.curve.coordinate.algo.relative;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToRelative;


@Component("relativeL")
public class LToRelative implements IToRelative {

	@Override
	public boolean relative(PointBean curP, PointBean prevP) {
		if(StringUtils.isAllLowerCase(curP.getAlphaStr())) {
			System.out.println("==[debug] SKIP... for related point ");
			return true;
		}
		
		// deal with absolute coordinate
		
		// L100,200 -> l(100-prevX, 200-prevY)
		double lx = curP.getArrayCoordinate().size()<2 ? 0: curP.getArrayCoordinate().get(0);
		double ly = curP.getArrayCoordinate().size()<2 ? 0: curP.getArrayCoordinate().get(1);
		curP.getArrayCoordinate().set(0, lx - prevP.getAbsX());
		curP.getArrayCoordinate().set(1, ly - prevP.getAbsY());
		
		// change alphabet string from Upper Case to lower case
		curP.setAlphaStr(StringUtils.lowerCase(curP.getAlphaStr()));
		return true;
	}

}
