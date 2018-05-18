package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.DemoController;
import com.example.demo.model.CustomResponse;
import com.example.demo.service.DemoServiceImpl;
import com.example.demo.service.DemoServices;
import com.example.demo.util.ArrayListsCustom;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TelstraCodingApplicationTests {

	private MockMvc demoMvc;

	
	
	@InjectMocks
	DemoServiceImpl services;
	
	
	
	@Autowired
	private DemoController demoController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		demoMvc = MockMvcBuilders.standaloneSetup(demoController).build();

	}

	/**
	 * TEST CASES FIBONACCI
	 */
	@Test
	public void testStringInvalidFibonacci() throws Exception {
		demoMvc.perform(get("/api/Fibonacci").param("n", "abc")).andExpect(status().isBadRequest());
	}

	@Test
	public void testNoValueInvalidFibonacci() throws Exception {
		demoMvc.perform(get("/api/Fibonacci").param("n", "")).andExpect(status().isBadRequest());
	}

	@Test
	public void testValueValidFibonacci() throws Exception {
		demoMvc.perform(get("/api/Fibonacci").param("n", "10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", is("55")));

	}

	@Test
	public void testReverseStringValid() throws Exception {
		demoMvc.perform(get("/api/ReverseWords").param("sentence", "How are you?")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", is("woH era ?uoy")));
	}
	@Test
	public void testReverseStringInvalidParam() throws Exception {
		demoMvc.perform(get("/api/ReverseWords").param("sentenc", "How are you?")).andExpect(status().isBadRequest());
	}
	@Test
	public void testTriangleEquilateralValid() throws Exception
	{
		demoMvc.perform(get("/api/TriangleType").param("a", "13").param("b", "13").param("c", "13")).andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is("Equilateral")));
	
	}
	@Test
	public void testTriangleIsoscelesValid() throws Exception
	{
		demoMvc.perform(get("/api/TriangleType").param("a", "17").param("b", "17").param("c", "13")).andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is("Isosceles")));
	
	}
	@Test
	public void testTriangleSceleneValid() throws Exception
	{
		demoMvc.perform(get("/api/TriangleType").param("a", "11").param("b", "12").param("c", "13")).andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is("Scalene")));
	
	}
	@Test
	public void testTriangleNotValid() throws Exception
	{
		demoMvc.perform(get("/api/TriangleType").param("a", "5").param("b", "3").param("c", "8")).andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is("Triangle not possble")));
	
	}
	@Test
	public void testOneArrayValid() throws Exception
	{
		ArrayListsCustom listsCustom=new ArrayListsCustom();
		List<Object> numberList=new ArrayList<>();
		numberList.add(1);
		numberList.add(123);
		numberList.add(12);
		numberList.add(4);
		listsCustom.put("array1", numberList);
		demoMvc.perform(post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(listsCustom))).andExpect(status().isOk());
	
	}
	@Test
	public void testOneArrayInvalid() throws Exception
	{
		ArrayListsCustom listsCustom=new ArrayListsCustom();
		List<Object> numberList=new ArrayList<>();
		numberList.add(1);
		numberList.add("a");
		numberList.add(12);
		numberList.add(4);
		listsCustom.put("array1", numberList);
		demoMvc.perform(post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(listsCustom))).andExpect(status().isBadRequest());
	
	}
	
	@Test
	public void testCoverageCheck() throws Exception
	{
		
		//demoController.getFibonacci("10", response, httpServletResponse);
		BigInteger result=services.computeFibonacci(10);
		
		assertEquals( "55",result.toString());
	}
	@Test
	public void testCoverageCheckOne() throws Exception
	{
		
		//demoController.getFibonacci("10", response, httpServletResponse);
		String result=services.computeReverseString("how");
		
		assertEquals( "woh",result);
	}
	
	/*
     * converts a Java object into JSON representation
     */
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
