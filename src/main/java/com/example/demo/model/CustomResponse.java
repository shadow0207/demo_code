package com.example.demo.model;

import java.beans.Transient;
import java.util.Set;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class CustomResponse {

	HttpStatus responseCode;
	@JsonInclude(Include.NON_NULL)
	@JsonProperty
	int status;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty
	String data;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty
	Set<Integer> Array;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public HttpStatus getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode=responseCode;
		this.setCode(responseCode.value());
	}

	public int getCode() {
		return status;
	}

	public void setCode(int code) {
		this.status = code;
	}

	public Set getArray() {
		return Array;
	}

	public void setArray(Set<Integer> array) {
		Array = array;
	}

}
