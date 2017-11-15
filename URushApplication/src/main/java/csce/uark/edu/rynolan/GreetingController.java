package csce.uark.edu.rynolan;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import csce.uark.edu.rynolan.models.Greeting;

@RestController
public class GreetingController {
	public static final String messageTemplate = "Hello, %s!";
	public final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greet")
	public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), name, String.format(messageTemplate, name));
	}
}
