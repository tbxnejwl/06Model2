package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addProductView.do")
	public ModelAndView addProductView() throws Exception{
		System.out.println("/addProductView.do");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/addProductView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct(@ModelAttribute("product") Product product ) throws Exception{
		
		System.out.println("/addProduct.do");
		
		productService.addProduct(product);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/addProduct.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/getProduct.do")
	public ModelAndView getProduct(@RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		System.out.println("/getProduct.do");
		
		String history = null;
		Cookie[] cs = request.getCookies();
		if(cs != null && cs.length>0){
			for(int i=0; i<cs.length; i++){
				Cookie c = cs[i];
				if(c.getName().equals("history")){
					history = c.getValue();
				}	
			}
		}
		history = history + "," + prodNo;
		Cookie cookie = new Cookie("history", history);
		
		response.addCookie(cookie);
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/getProduct.jsp");
		
		return modelAndView;
		
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search , @RequestParam("orderby") String orderby, Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct.do");
		System.out.println(search.getCurrentPage()+" : currentpage");
		
		if(search.getCurrentPage() == 0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println(search.getCurrentPage()+" : currentpage");
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search,orderby);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}

	@RequestMapping("/updateProductView.do")
	public ModelAndView updateProductView(@RequestParam("prodNo") String prodNo) throws Exception{
		System.out.println("/updateProductView.do");
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
	
		modelAndView.setViewName("/product/updateProductView.jsp");
		
		return modelAndView;
	}
		
	@RequestMapping("/updateProduct.do")
	public ModelAndView updateProduct( @ModelAttribute("product") Product product) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(product);
		
		//int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/getProduct.jsp");
		
		return modelAndView;
	
	}

}
