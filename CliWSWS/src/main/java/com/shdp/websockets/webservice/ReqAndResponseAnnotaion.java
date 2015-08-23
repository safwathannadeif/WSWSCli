package com.shdp.websockets.webservice;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



import com.shdp.websockets.webservice.DefaultJsonConverter;
import com.shdp.websockets.webservice.JsonConvertIF;
//@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqAndResponseAnnotaion{
   public Class<? extends JsonConvertIF<?>> jsonConverter() default DefaultJsonConverter.class; 
}

