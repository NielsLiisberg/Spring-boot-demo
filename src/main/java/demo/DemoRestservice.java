package demo;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("api")
public class DemoRestservice {
	
	@GetMapping("/demo1")
	String demo1() {
		return "This is demo 1";
	}
	
	@Data
	class MyData {
		boolean success = true;
		String message = "This is the message";
		Object data;
	}
	
	@GetMapping("/demo2")
	MyData get() {
		return new MyData();
	}

	@GetMapping("/demo2/{id}")
	MyData get(@PathVariable("id") int id,
			@RequestParam(value="param", required=false, defaultValue="") String param) {
		MyData myData = new MyData();
		if (!param.isEmpty()) myData.message = param;
		myData.data = new BigDecimal(id);
		return myData;
	}

}
