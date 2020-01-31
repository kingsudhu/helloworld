package example.smallest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import example.smallest.JaegerTracerService;
import io.swagger.annotations.Api;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("WelcomeController")
@Slf4j
@Api(value = "/WelcomeController", tags = {"WelcomeController Interface"})
public class WelcomeController {
	
	@Autowired
	private JaegerTracerService jaegerTracerService;
	
	//@RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
	 @GetMapping("/helloWorld")
	public @ResponseBody String helloWorld() {
		
		val span = jaegerTracerService.span("test helo world");
        jaegerTracerService.restFinish(span);
		return "Hello webhook World!!!"; //"application/json" mean this is a text not a redirect
	}
}
