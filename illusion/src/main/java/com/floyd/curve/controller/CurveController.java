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
		try {
			return curvService.draw4Point(testStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
	@RequestMapping(value="/p2b", method=RequestMethod.POST)
	public String postToBizier(@RequestParam("oriCurStr") String oriCurStr) {
		System.out.println("===[Input] oriCurStr:" + oriCurStr);
		try {
			String c = curvService.draw4Point(oriCurStr);
			return "{\"bPointList\": [{\"bData\":\"" + c + "\"}]}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
	@RequestMapping(value="/circle2b", method=RequestMethod.POST)
	public String postCircleToBizier(@RequestParam("oriCirStr") String oriCirStr) {
		try {
			String c =  curvService.draw4Circle(oriCirStr);
			return "{\"bPointList\": [{\"bData\":\"" + c + "\"}]}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
	@RequestMapping(value="/rect2b", method=RequestMethod.POST)
	public String postRectToBizier(@RequestParam("oriRectStr") String oriRectStr) {
		try {
			String c =  curvService.draw4Rect(oriRectStr);
			return "{\"bPointList\": [{\"bData\":\"" + c + "\"}]}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
	@RequestMapping(value="/poly2b", method=RequestMethod.POST)
	public String postPolygonToBizier(@RequestParam("oriPolyStr") String oriPolyStr) {
		try {
			String c =  curvService.draw4Polygon(oriPolyStr);
			return "{\"bPointList\": [{\"bData\":\"" + c + "\"}]}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
	@RequestMapping(value="/transferAnim", method=RequestMethod.POST)
	public String postTransferAnimate(@RequestParam("fromCurStr") String fromCurStr, 
			@RequestParam("toCurStr") String toCurStr) {
		try {
			String jsonStr =  curvService.animate4Transfer(fromCurStr, toCurStr);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errMsg\": "+ e.getMessage()+"}";
		}
	}
	
}
