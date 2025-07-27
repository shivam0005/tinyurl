package com.shivam.tinyurl;

import com.shivam.tinyurl.service.codegenerator.impl.Base62CodeGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TinyurlApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	void generateCode(){
		Base62CodeGenerator encoder = new Base62CodeGenerator();
		String url = "https://leetcode.com/problems/two-sum/description/";
		String code = encoder.generateCode(url);
		System.out.println(code);
	}



}
