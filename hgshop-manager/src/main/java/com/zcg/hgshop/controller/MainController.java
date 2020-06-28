package com.zcg.hgshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({"/","index","main"})
	public String main() {
		return "main";
	}
	
}
