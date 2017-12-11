package com.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.DaoImpl.CategoryDaoImpl;
import com.DaoImpl.ProductDaoImpl;
import com.DaoImpl.SupplierDaoImpl;
import com.DaoImpl.UserDaoImpl;
import com.model.Productdetails;
import com.model.User;

@Controller
public class Indexcontroller {
	@Autowired
	User user;

	@Autowired
	UserDaoImpl userDaoImpl;
	@Autowired
	SupplierDaoImpl supplierDaoImpl;
	@Autowired
	ProductDaoImpl productDaoImpl;
	@Autowired
	CategoryDaoImpl categoryDaoImpl;

	@RequestMapping("/index")
	public String index()
	{
		return "index";
	}
	/*@RequestMapping("/register")
	public String registration()
	{
		return "register";
}*/
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView goToRegister() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",new User());
		mv.setViewName("register");
	return mv;
	}
	
	@RequestMapping(value="/saveRegister",method=RequestMethod.POST)
	public ModelAndView saveUser( @Valid @ModelAttribute("user") User user, BindingResult res)
	{
		ModelAndView mv=new ModelAndView();
		user.setRole("ROLE_USER");
		userDaoImpl.insertUser(user);
		mv.setViewName("login");
		return mv;
	}
	
	
	@ModelAttribute
	public Model fetchData(Model m)
	{m.addAttribute("catList",categoryDaoImpl.retrieve());
	m.addAttribute("prodList",productDaoImpl.retrieve());
    m.addAttribute("supList",supplierDaoImpl.retrieve());
    m.addAttribute("userList",userDaoImpl.retrieve());
	
	return m;
	}
	
	@RequestMapping(value="/prodCustList")
	public ModelAndView getCustTable(@RequestParam ("cid") int cid)
	{
		System.out.println(cid);
	ModelAndView mv=new ModelAndView();
	mv.addObject("prodList",productDaoImpl.getProdById(cid));
	mv.setViewName("prodCustList");//ProductCustomerList
	return mv;
	}
	
	
/*	@RequestMapping(value="/prodDetails/{pid}")
	
	
	public ModelAndView productDetails(@PathVariable("pid")int pid)
	{
		ModelAndView mv= new ModelAndView();
		Productdetails p=productDaoImpl.findById(pid);
		mv.addObject("product",p);
		mv.setViewName("prodDetails");
		return mv;
	}
	
	*/
	/*
	@RequestMapping(value="/addToCart",method=RequestMethod.POST)
	public ModelAndView addToCart(HttpServletRequest req)
	{
	ModelAndView mv= new ModelAndView();
	Principal principal=req.getUserPrincipal();
	String userEmail=principal.getName();
	int pid= Integer.parseInt(req.getParameter("pid"));
	Double price=Double.parseDouble(req.getParameter("pPrice"));
    String pName=req.getParameter("pName");
    String imgName=req.getParameter("imgname");
    Integer qty=Integer.parseInt(req.getParameter("pQty"));
   // Cart crt=new Cart();
    //crt.setCartPrice(price);
    //crt.setCartPrice(pName);
    //crt.setCartQty(qty);
			
	return mv;		
	}
	
	*/
	
	/*@RequestMapping(value="/prodCustList")
	public ModelAndView getCustTable(@RequestParam ("cid") int cid)
	{
		System.out.println(cid);
	ModelAndView mv=new ModelAndView();
	mv.addObject("prodList",productDaoImpl.retrieve());
	mv.setViewName("prodCustList");//productCustList
	return mv;
	}
	*/
	
	
	@RequestMapping("/custList")
	public ModelAndView customerList()
	{ModelAndView mav=new ModelAndView();
	mav.addObject("custList",userDaoImpl.retrieve());
	mav.setViewName("custList");
	return mav;	
		
	}
	
	/*@RequestMapping("/login")
	public ModelAndView gotologin()
	{ModelAndView mav=new ModelAndView();
	mav.setViewName("login");
	return mav;
	}*/
	
	@RequestMapping("/login")
	public String signin()
	{
		return "login";

}

@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login_session_attributes")
	public String login_session_attributes(HttpSession session,Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		//User username = userDaoImpl.insertUser(email);
		session.setAttribute("username", user.getEmail());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("LoggedIn", "true");

		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()	.getAuthentication().getAuthorities();
		String page="";
		String role="ROLE_ADMIN";
		for (GrantedAuthority authority : authorities) 
		{
		  
		     if (authority.getAuthority().equals(role)) 
		     {
		    	 session.setAttribute("LoggedIn", "true");
			 page="/adding";
		    	 }
		     else 
		     {
		    	 session.setAttribute("UserLoggedIn", "true");
			page="/prodCustList";
		    }
		}
		return page;
	}




	@RequestMapping("/error")
	public String err()
	{
		return "error";

}
	@RequestMapping("/admin")
	public String add()
	{
		return "admin";

}
	
	@RequestMapping("/contact")
	public String cont()
	{
		return "contact";

}
	@RequestMapping("/adding")
	public String ad()
	{
		return "adding";

}

	@RequestMapping("/about")
	public String abt()
	{
		return "about";

}

}
