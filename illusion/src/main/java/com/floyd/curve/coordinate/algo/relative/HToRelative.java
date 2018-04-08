package com.floyd.curve.coordinate.algo.relative;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.floyd.curve.bean.PointBean;
import com.floyd.curve.coordinate.algo.IToRelative;


@Component("relativeH")
public class HToRelative implements IToRelative {

	@Override
	public boolean relative(PointBean curP, PointBean prevP) {
		if(StringUtils.isAllLowerCase(curP.getAlphaStr())) {
			System.out.println("==[debug] SKIP... for related point ");
			return true;
		}
		
		// deal with absolute coordinate
		
		// H100 -> h(100-prevX)
		double h = curP.getArrayCoordinate().get(0);
		curP.getArrayCoordinate().set(0, h-prevP.getAbsX());
		System.out.println("==---[debug] Abosulte Point:" + curP);
		
		// change alphabet string from Upper Case to lower case
		curP.setAlphaStr(StringUtils.lowerCase(curP.getAlphaStr()));
		return true;
	}

}
