package com.example.demo.controller;

import java.util.Set;

import javax.naming.TimeLimitExceededException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomResponse;

import com.example.demo.service.DemoServices;
import com.example.demo.util.ArrayListsCustom;

@RestController()
public class DemoController {
	@Autowired
	DemoServices service;
	
	@Value("${app.error.integer}")
	String ERROR_MESSAGE;
	@Value("${app.error.timeout}")
	String ERROR_MESSAGE_TIMEOUT;

	@GetMapping("/api/Fibonacci")
	public ResponseEntity<CustomResponse> getFibonacci(@RequestParam(name = "n") String number, final CustomResponse response,
			HttpServletResponse httpServletResponse) {

		String fibSeries;
		

		try {
			
			
			
			fibSeries = service.computeFibonacci(Long.parseLong(number)).toString();
			System.err.println(fibSeries);
			response.setResponseCode(HttpStatus.OK);
			response.setData(fibSeries);
			
		} catch (TimeLimitExceededException e) {
			response.setResponseCode(HttpStatus.REQUEST_TIMEOUT);
			response.setData(ERROR_MESSAGE_TIMEOUT);
		} catch (NumberFormatException e) {
			
			response.setResponseCode(HttpStatus.BAD_REQUEST);
			response.setData(ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			response.setResponseCode(HttpStatus.BAD_REQUEST);
			response.setData(ERROR_MESSAGE);
		}
		httpServletResponse.setHeader("vary", "Accept-Encoding");
		httpServletResponse.setHeader("Cache-Control","no-cache");
		httpServletResponse.setHeader("Pragma","no-cache");
		//httpServletResponse.setHeader("content-encoding","gzip");
		httpServletResponse.setHeader("Pragma","no-cache");
		httpServletResponse.setDateHeader("Expires", -1);
		return new ResponseEntity<CustomResponse>(response,response.getResponseCode());
	}

	@GetMapping("/api/ReverseWords")
	public ResponseEntity<CustomResponse> getReverseWord(@RequestParam(name = "sentence") String sentence,
			final CustomResponse response,HttpServletResponse httpServletResponse) {
		String result = null;
		
			result = sentence;

			response.setResponseCode(HttpStatus.OK);
			response.setData(service.computeReverseString(result));
			httpServletResponse.setHeader("vary", "Accept-Encoding");
			httpServletResponse.setHeader("Cache-Control","no-cache");
			httpServletResponse.setHeader("Pragma","no-cache");
			//httpServletResponse.setHeader("content-encoding","gzip");
			httpServletResponse.setHeader("Pragma","no-cache");
			httpServletResponse.setDateHeader("Expires", -1);
			return new ResponseEntity<CustomResponse>(response,response.getResponseCode());

		
		
	}

	@GetMapping("/api/TriangleType")
	public ResponseEntity<CustomResponse> getTraingleName(@RequestParam(value = "a") String sideA,
			@RequestParam(value = "b") String sideB, @RequestParam(value = "c") String sideC,
			final CustomResponse response,HttpServletResponse httpServletResponse) {
		try {
		int a = Integer.parseInt(sideA);
		int b = Integer.parseInt(sideB);
		int c = Integer.parseInt(sideC);
		response.setResponseCode(HttpStatus.OK);
		response.setData(service.computeTraingleName(a, b, c));
		}catch(Exception e)
		{
			response.setResponseCode(HttpStatus.BAD_REQUEST);
			response.setData(ERROR_MESSAGE);
		}
		httpServletResponse.setHeader("vary", "Accept-Encoding");
		httpServletResponse.setHeader("Cache-Control","no-cache");
		httpServletResponse.setHeader("Pragma","no-cache");
		//httpServletResponse.setHeader("content-encoding","gzip");
		httpServletResponse.setHeader("Pragma","no-cache");
		httpServletResponse.setDateHeader("Expires", -1);
		return new ResponseEntity<CustomResponse>(response,response.getResponseCode());
	}

	@PostMapping("/api/makeonearray")
	public ResponseEntity<CustomResponse> getOneArray(@RequestBody ArrayListsCustom listsCustom, final CustomResponse response,HttpServletResponse httpServletResponse) {

		Set<Integer> dataOut = service.computeArrayIntoOne(listsCustom);
		if (dataOut != null) {
			response.setResponseCode(HttpStatus.OK);
			response.setArray(dataOut);
		} else {
			response.setResponseCode(HttpStatus.BAD_REQUEST);
			response.setData(ERROR_MESSAGE);
		}
		httpServletResponse.setHeader("vary", "Accept-Encoding");
		httpServletResponse.setHeader("Cache-Control","no-cache");
		httpServletResponse.setHeader("Pragma","no-cache");
		//httpServletResponse.setHeader("content-encoding","gzip");
		httpServletResponse.setHeader("Pragma","no-cache");
		httpServletResponse.setDateHeader("Expires", -1);
		return new ResponseEntity<CustomResponse>(response,response.getResponseCode());
	}
}
