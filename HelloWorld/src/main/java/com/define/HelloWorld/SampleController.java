package com.define.HelloWorld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.define.HelloWorld.VO.ProductVO;

@Controller
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	
	// return 타입 void일때
	@RequestMapping("doA")
	public void doA() {
		logger.info("doA called.............................");
	}
	
	@RequestMapping("doB")
	public void doB() {
		logger.info("doB called.............................");
	}
	
	// return 타입 String일때
	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg) {
		logger.info("doC called.............................");
		return "result1"; //jsp 파일 이름(jsp파일안에 있는 msg에 입력된 String 전달
	}
	
	// Class(model)을 이용한 데이터 전달
	@RequestMapping("doD")
	public String doD(Model model) {
		ProductVO product = new ProductVO("Test", 5000);
		logger.info("doD called.............................");
		// 모델의 이름을 정하지 않는 경우 자동으로 저장되는 객체의 클래스명 앞글자를 소문자로 처리한 클래스명을 이름으로 간주함
		// addAttribute("모델명",객체) ex) model.addAttribute("product",product)
		model.addAttribute(product);
		return "result2"; //jsp 파일 이름(jsp파일에 Class(model)을 전달
	}
	
	
	// 특정 컨트롤러의 로직을 처리할때 다른 경로를 호출해야하는 경우에 사용
	// 스프링의 MVC의 특별한 문자열인 'redirect:'를 이용하는데 ':'을 사용하는 것을 주의할 필요가 있음
	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) {
		logger.info("doE called.............................");
		rttr.addFlashAttribute("msg","This is the Message ! with redirected");
		return "redirect:/doF"; //jsp 파일 이름(jsp파일에 Class(model)을 전달
		// "redirect:/doF" -> "forward:/doF" 도 같이 지원
	}
	
	
	@RequestMapping("doF")
	public String doF(String msg) {
		logger.info("doF called............................. "+msg);
		return "HelloWorld";
	}
	
}
