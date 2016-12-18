package com.leafchild.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by: leaf
 * Project: spring-mvc-angular-test
 * Date: 11/26/15
 * Time: 12:53 PM
 */

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "UserManagement";
	}

}
