package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.example.demo.util.ArrayListsCustom;

public interface DemoServices {

	public BigInteger computeFibonacci(long number) throws Exception;
	public String computeReverseString(String sentence);
	public String computeTraingleName(int a,int b,int c);
	public Set<Integer> computeArrayIntoOne(ArrayListsCustom arrayListsCustom);

}
