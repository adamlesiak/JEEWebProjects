package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Welcome {

	@RequestMapping(value = "/welcome")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome").addObject("var", 123);
		return model;
	}
	
}
