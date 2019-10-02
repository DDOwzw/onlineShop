/*
 * This is a controller class.
 * It handles a kind of url request:
 * 		GET & POST /customer/registration;
 * It maps to the register.jsp page. (see the view name).
 * If register success, it will redirect to the login.jsp.
 */
package onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import onlineShop.model.Customer;
import onlineShop.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class RegistrationController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationForm() {
		//                      view name,   key,       value
		return new ModelAndView("register", "customer", new Customer());
	}

	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@ModelAttribute(value = "customer") Customer customer,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}
		customerService.addCustomer(customer);
		modelAndView.setViewName("login");
		modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
		return modelAndView;
	}
}
