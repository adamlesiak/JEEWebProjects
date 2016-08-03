package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Welcome {

	@RequestMapping(value = "/")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome").addObject("var", 123);
		return model;
	}
	
	@RequestMapping(value = "/page-1")
	public ModelAndView page1() {
		ModelAndView model = new ModelAndView("page1").addObject("var", "page 1");
		return model;
	}
	
	@RequestMapping(value = "/page-2")
	public ModelAndView page2() {
		ModelAndView model = new ModelAndView("page2").addObject("var", "page 2");
		return model;
	}
	
}
