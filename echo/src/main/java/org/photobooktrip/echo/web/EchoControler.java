package org.photobooktrip.echo.web;

import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echo")
public class EchoControler {


	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<String> echo(@RequestParam(value = "name") String name) {
		return new ResponseEntity<String>(name + " from server today " + new Date(), HttpStatus.OK);
	} 
	
	

}