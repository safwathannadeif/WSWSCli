package com.shdp.websockets.webservice.usecases.employeews;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.shdp.websockets.webservice.JsonConvertIF;

public final class EmpRespJsonConvert implements JsonConvertIF<RespEmp>   {
		
	
	public RespEmp convertToPOJO(String jsonstrjx,@SuppressWarnings("rawtypes") Class RespEmp) throws InstantiationException, IllegalAccessException, JsonParseException, JsonMappingException, IOException  {
		ObjectMapper objectMapper = new ObjectMapper();
		RespEmp respEmpObj  =new RespEmp() ; 
		respEmpObj = objectMapper.readValue(jsonstrjx,RespEmp.class) ;
		//
		Base64.Decoder decoder = Base64.getDecoder();
		for (Employee emp  : respEmpObj.getEmpLisResp()) 
		{
			String recentIncomeString = emp.getRecentYearIncomeEncoded() ;
			byte[] decodedByteArray = decoder.decode(recentIncomeString);
			recentIncomeString = new String(decodedByteArray);
			BigDecimal bigDecimal = new BigDecimal(recentIncomeString);
			emp.setRecentYearIncome(bigDecimal);
		}
		return respEmpObj ;
	}

	public String convertToJsonStr(RespEmp respEmp) throws JsonGenerationException, JsonMappingException, IOException {
		
		Base64.Encoder encoder = Base64.getEncoder();
		for (Employee emp  : respEmp.getEmpLisResp()) 
		{
		 String strIncomeToSend  =	emp.getRecentYearIncome().toPlainString();
		 strIncomeToSend = encoder.encodeToString(strIncomeToSend.getBytes(StandardCharsets.UTF_8));
		 emp.setRecentYearIncomeEncoded(strIncomeToSend);
		 emp.setRecentYearIncome(null);
		}
				
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
	    StringWriter sw = new StringWriter();
		objectMapper.writeValue(sw,respEmp) ; 
		String jsonStr =sw.toString() ;
	    return jsonStr ;
	}
}