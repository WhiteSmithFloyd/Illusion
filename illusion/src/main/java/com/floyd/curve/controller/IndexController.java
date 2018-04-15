package com.floyd.curve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String idx(){
		return "index";
	}
	
	@RequestMapping(value="/getTry")
	public String getTry() {
		return "demo/try";
	}
	
}
