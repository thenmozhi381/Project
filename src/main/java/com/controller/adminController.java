package com.controller;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.DaoImpl.*;
import com.model.*;
@SuppressWarnings("unused")
@RequestMapping("/admin")
@Controller
@Repository
public class adminController {
	@Autowired
	SupplierDaoImpl supplierDaoImpl;
	@Autowired
	ProductDaoImpl productDaoImpl;
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;

	@RequestMapping("/modal")
	public String addprod()
	{return "modal";}
	
	@RequestMapping(value="/saveSupp",method=RequestMethod.POST)
	//public ModelAndView saveSupplierdetails(@RequestParam("sid")int sid,@RequestParam("sname")String sname)
	public ModelAndView saveSupplierdetails(@RequestParam("sname")String sname)
	
	{
	ModelAndView mv=new ModelAndView();
	Supplierdetails s=new Supplierdetails();
	//s.setSid(sid);
	s.setSname(sname);
	supplierDaoImpl.insertSupplierdetails(s);
	mv.setViewName("adding");
	return mv;
	}
	
	@RequestMapping(value="/saveCat",method=RequestMethod.POST)
	//public ModelAndView saveCategorydetails(@RequestParam("cid")int cid,@RequestParam("cname")String cname)
	public ModelAndView saveCategorydetails(@RequestParam("cname")String cname)
	
	{
	ModelAndView mv=new ModelAndView();
	Categorydetails c=new Categorydetails();
	//c.setCid(cid);
	c.setCname(cname);
	categoryDaoImpl.insertCategorydetails(c);
	mv.setViewName("adding");
	return mv;
	}
	
	
	@RequestMapping(value="/saveProd", method=RequestMethod.POST)
	
	public String saveProductdetails(HttpServletRequest req,@RequestParam("file") MultipartFile file)
	{
	Productdetails p=new Productdetails();
	p.setPname(req.getParameter("pName"));
	p.setPrice(Float.parseFloat(req.getParameter("pPrice")));
	p.setCategorydetails(categoryDaoImpl.findById(Integer.parseInt(req.getParameter("pCategory"))));
	p.setSupplierdetails(supplierDaoImpl.findById(Integer.parseInt(req.getParameter("pSupplier"))));
	p.setStock(Integer.parseInt(req.getParameter("pStock")));
	p.setDescription(req.getParameter("pDescription"));
	
	   
	
	String filepath=req.getSession().getServletContext().getRealPath("/");
	String filename=file.getOriginalFilename();
	p.setImgname(filename);
	productDaoImpl.insertProductdetails(p);
	System.out.println("File path File" +filepath+  " " +filename);
	
	try
	{
		byte imagebyte[]=file.getBytes();
		//BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream("C:\\Users\\THENMOZHI\\workspace\\FloristCharm\\src\\main\\resources\\"+"/ "+filename));
	BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(filepath+"/images/"+filename));

	//	BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(filepath+"/"+filename));
	/*BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream("C:/Users/THENMOZHI/workspace/FloristCharm/src/main/webapp/images/"+" "+filename));*/
	
	
	
	fos.write(imagebyte);
		fos.close();
	}
	catch(IOException e)
	{
		e.printStackTrace();}
	
	return "adding";
	}
	
	

	
	@ModelAttribute
	public Model addData(Model m)
	{m.addAttribute("catList",categoryDaoImpl.retrieve());
	m.addAttribute("prodList",productDaoImpl.retrieve());
	m.addAttribute("supList",supplierDaoImpl.retrieve());
	m.addAttribute("userList",userDaoImpl.retrieve());
	
	return m;
	}
	
	
	@RequestMapping("/custList")
	public ModelAndView customerList()
	{ModelAndView mav=new ModelAndView();
	mav.addObject("userList",userDaoImpl.retrieve());
	mav.setViewName("custList");
	return mav;	
		
	}
	
	
	
	
	@RequestMapping("/productList")
	public ModelAndView productList()
	{ModelAndView mav=new ModelAndView();
	mav.addObject("prodList",productDaoImpl.retrieve());
	mav.setViewName("productList");
	return mav;
	}
	
@ModelAttribute
	public void addAttributes(Model m)
	{m.addAttribute("catList",categoryDaoImpl.retrieve());
	m.addAttribute("supList",supplierDaoImpl.retrieve());
	m.addAttribute("prodList",productDaoImpl.retrieve());
	m.addAttribute("userList",userDaoImpl.retrieve());
	
	}

	@RequestMapping("/deleteProduct/{pid}")
	public String deleteProd(@PathVariable("pid")int pid)
	{productDaoImpl.deleteProductdetails(pid);
		return "redirect:/admin/productList?del";
		}
	
	
	@RequestMapping("/updateProduct")
	public ModelAndView updateProd(@RequestParam("id")int pid)
	{
		ModelAndView mv=new ModelAndView();
		Productdetails p=productDaoImpl.findById(pid);
		mv.addObject("prod",p);
		mv.addObject("cList",categoryDaoImpl.retrieve());
		mv.addObject("sList",supplierDaoImpl.retrieve());
		mv.setViewName("updateProduct");
		return mv;
	}
	
	@RequestMapping(value="/productUpdate",method=RequestMethod.POST)
	public ModelAndView updateProduct(HttpServletRequest request,@RequestParam("file") MultipartFile file)
	{	System.out.println("called");
		ModelAndView mv=new ModelAndView();
		
	String a=request.getParameter("id");
		String pname=request.getParameter("pName");
		
		String ct=request.getParameter("pCategory");
		String sp=request.getParameter("pSupplier");
		
		String p=request.getParameter("pPrice");
		String d=request.getParameter("pDescription");
		String q=request.getParameter("pStock");
		System.out.println("Controller!!!!"+a);
		//Categorydetails cat=new Categorydetails();
		
		//cat.setProducts(productDaoImpl.findById(Integer.parseInt(pid)));
		
		Productdetails prod2=new Productdetails();
		prod2.setId(Integer.parseInt(a));
	prod2.setPname(pname);
	prod2.setCategorydetails(categoryDaoImpl.findById(Integer.parseInt(ct)));
	prod2.setSupplierdetails(supplierDaoImpl.findById(Integer.parseInt(sp)));
	prod2.setPrice(Float.parseFloat(p));
	prod2.setDescription(d);
	prod2.setStock(Integer.parseInt(q));
	
	
	String filepath=request.getSession().getServletContext().getRealPath("/");
	String filename=file.getOriginalFilename();
	prod2.setImgname(filename);
	productDaoImpl.update(prod2);
	System.out.println("File path File" + filepath+ " " +filename);
	try
	{
		byte imagebyte[]=file.getBytes();
		BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(filepath+"/images/"+filename));
		fos.write(imagebyte);
		fos.close();
	}
	
	
	
	catch(IOException e)
	{e.printStackTrace();
	}
	
	catch(Exception e)
	{e.printStackTrace();
	}
	
	mv.setViewName("redirect:/admin/productList?update");
	return mv;
	}
	
	
	@RequestMapping("/categoryList")
	public ModelAndView categoryList()
	{ModelAndView mav=new ModelAndView();
	mav.addObject("catList",categoryDaoImpl.retrieve());
	mav.setViewName("categoryList");
	return mav;	
		
	}
	
	
	@RequestMapping("/deleteCat/{cid}")
	public String deleteCategory(@PathVariable("cid")int cid)
	{
		categoryDaoImpl.deleteCategorydetails(cid);
		return "redirect:/admin/categoryList?del";
	}

	

	
	
	

	@RequestMapping("/updateCategory")
	public ModelAndView updateCat(@RequestParam("cid")int cid)
	{ModelAndView mv=new ModelAndView();
	Categorydetails c=categoryDaoImpl.findById(cid);
mv.addObject("cat",c);
	mv.addObject("cList",categoryDaoImpl.retrieve());
	mv.setViewName("updateCategory");
	return mv;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/Categoryupdate",method=RequestMethod.POST)
	public ModelAndView updateCategory(@RequestParam("cid")int cid,@RequestParam("cname")String cname)
	{
		ModelAndView mav =new ModelAndView();
		Categorydetails c=new Categorydetails();
		c.setCid(cid);
		c.setCname(cname);
		categoryDaoImpl.update(c);
		mav.setViewName("redirect:/admin/categoryList?update");
		return mav;
	}
	

	@RequestMapping("/supplierList")
	public ModelAndView supplierList()
	{ModelAndView mav=new ModelAndView();
	mav.addObject("supList",supplierDaoImpl.retrieve());
	mav.setViewName("supplierList");
	return mav;	
		
	}
	
	
	@RequestMapping("/deleteSup/{sid}")
	public String deleteSupplier(@PathVariable("sid")int sid)
	{
		supplierDaoImpl.deleteSupplierdetails(sid);
		return "redirect:/admin/supplierList?del";
	}
	
	
	@RequestMapping("/updateSupplier")
	public ModelAndView updateSup(@RequestParam("sid")int sid)
	{ModelAndView mv=new ModelAndView();
	Supplierdetails s=supplierDaoImpl.findById(sid);
mv.addObject("sat",s);
	mv.addObject("sList",supplierDaoImpl.retrieve());
	mv.setViewName("updateSupplier");
	return mv;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/Supplierupdate",method=RequestMethod.POST)
	public ModelAndView updateSupplier(@RequestParam("sid")int sid,@RequestParam("sname")String sname)
	{
		ModelAndView mav =new ModelAndView();
		Supplierdetails s=new Supplierdetails();
		s.setSid(sid);
		s.setSname(sname);
		supplierDaoImpl.update(s);
		mav.setViewName("redirect:/admin/supplierList?update");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
/*	@RequestMapping("/modal")
	public String addpro()
	{return "modal";}*/
	
	@RequestMapping("/adding")
	public String addPage()
	{return "adding";}
	
	@RequestMapping("/index")
	public String addHome()
	{return "index";}
	
}