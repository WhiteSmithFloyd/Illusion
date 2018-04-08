package com.floyd.curve.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.floyd.curve.service.ICurveService;

@RestController()
public class CurveController {

	@Resource(name="BezierService")
	private ICurveService curvService; 
	
	private String testStr = "M195,168c12-26,68-68,68-68h116v92l103-63s88,82,47,119s-332,61.3-334,8s0-88,0-88";
//	private String testStr = "M126,132s58-120,146-45c54,46,16,144,82,181c66,37,207-19,207-19";
	
	
	@RequestMapping(value="/g2b", method=RequestMethod.GET)
	public String getToBezier() {
//		return "Please use the Post method to request...";
		return curvService.draw(testStr);
	}
	
	@RequestMapping(value="/p2b", method=RequestMethod.POST)
	public String postToBizier(@RequestParam("oriCurStr") String oriCurStr) {
		System.out.println("===[Input] oriCurStr:" + oriCurStr);
		return curvService.draw(oriCurStr);
	}
	
}
