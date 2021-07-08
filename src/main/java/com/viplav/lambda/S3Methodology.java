/**
 * 
 */
package com.viplav.lambda;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * @author vkallam2
 *
 */
public class S3Methodology {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	    public String handleRequest(Map<String,String> input, Context context) {
	        context.getLogger().log("Hello:::::: " + input.get("name"));
	        return "Hello World - " + input;
	}
}
