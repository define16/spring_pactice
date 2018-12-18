package com.define.HelloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.define.HelloWorld.VO.ProductVO;

@Controller
public class JSONController {
	@RequestMapping("doJSON")
	public @ResponseBody ProductVO doJSON() {
		ProductVO product = new ProductVO("Test", 5000);
		return product;
	}

}
