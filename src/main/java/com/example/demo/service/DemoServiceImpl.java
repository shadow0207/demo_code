package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.TimeLimitExceededException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.util.ArrayListsCustom;

@Service
public class DemoServiceImpl implements DemoServices{

	@Value("${app.triangle.equilateral}")
	String equiTriangle;
	@Value("${app.triangle.scalene}")
	String scalTriangle;
	@Value("${app.triangle.isosceles}")
	String isoTriangle;
	@Value("${app.triangle.invalid}")
	String noTriangle;
	
	@Override
	public BigInteger computeFibonacci(long number) throws NumberFormatException, TimeLimitExceededException {
		// TODO Auto-generated method stub
		 BigInteger a = BigInteger.valueOf(0);
	        BigInteger b = BigInteger.valueOf(1);
	        BigInteger c = BigInteger.valueOf(1);
	        
	        long startTime = System.currentTimeMillis();
	       
	        for (int j=2 ; j<=number ; j++)
	        {
	        	if((System.currentTimeMillis()-startTime)>=5000)
	        	{
	        		
	        		throw new TimeLimitExceededException();
	        		
	        	}
	            c =  a.add(b);
	            a = b;
	            b = c;
	        }
	        
	        return c;
	}

	@Override
	public String computeReverseString(String sentence) {
		// TODO Auto-generated method stub
		String temp="";
		String output="";
		for(int i=0;i<sentence.length();i++)
		{
			if(sentence.charAt(i)==' ')
			{
				
				
				StringBuffer buff=new StringBuffer(temp);
				output=output+buff.reverse().toString()+" ";
				
				temp="";
				
			}else if(i==sentence.length()-1)
			{
				temp=temp+sentence.charAt(i);
				StringBuffer buff=new StringBuffer(temp);
				output=output+buff.reverse().toString();
				
			}
			else{
			 temp=temp+sentence.charAt(i);
			
			}
		}
		return output;
	}

	@Override
	public String computeTraingleName(int a, int b, int c) {
		// TODO Auto-generated method stub
		String triangleType="";
		 if((a + b > c))
	        {
	            
	            if((a==b) && (b==c))
	            {
	               triangleType= equiTriangle;
	            }
	            else if ((a == b) & (b != c) || (b == c) & (c!= a)|| (a == c) & (c!= b))
	            {
	            	triangleType= isoTriangle;
	            }
	                 else if((a != b) & (b != c))
	                 {
	                	 triangleType= scalTriangle;
	                 }
	 
	        }
	        else
	        {
	        	triangleType= noTriangle;
	        }
		return triangleType;
	}

	@Override
	public Set<Integer> computeArrayIntoOne(ArrayListsCustom arrayListsCustom) {
		// TODO Auto-generated method stub
		
		TreeSet<Integer> dataSet=new TreeSet<>();
		for (Map.Entry<String, List<Object>> entry : arrayListsCustom.entrySet()) {
			 
		   for (Object element : entry.getValue()) {
			   try {
			dataSet.add((int)element);
			   }catch(Exception e)
			   {
				   
				   return null;
			   }
		}
		   
		    
		}
	
		
		return dataSet;
	}

	

}
