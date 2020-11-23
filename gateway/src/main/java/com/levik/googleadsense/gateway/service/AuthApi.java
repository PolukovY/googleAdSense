package com.levik.googleadsense.gateway.service;


import com.levik.googleadsense.common.IdentityResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "auth")
@RibbonClient(name = "auth")
public interface AuthApi {

   @RequestMapping(method = RequestMethod.POST, value = "/api/v1/identity/{identity}", produces = "application/json")
   IdentityResponse findIdentity(@PathVariable("identity") String identity);

}
