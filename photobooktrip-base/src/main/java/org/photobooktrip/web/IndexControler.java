package org.photobooktrip.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControler {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/ang")
	public String ang() {
		return "ang";
	}
	@RequestMapping("/store")
	public String store() {
		return "store";
	}

	
}