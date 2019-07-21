package com.ms.aop.controller;



import com.ms.aop.aop.ExceptionCatch;
import com.ms.aop.aop.MyAop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("aop")

public class AopController {



    @GetMapping("/test")
    @ResponseBody
    public Map<String,Object> aopTest(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key","success");
         return map;
    }

    @GetMapping("/test2")
    @MyAop
    @ResponseBody
    public Map<String,Object> aopTest2(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key","success");
        return map;
    }

    /**
     *测试异常切面捕获
     * @return map
     */
    @GetMapping("/test3")
    @ExceptionCatch
    @ResponseBody
    public Map<String,Object> aopTest3(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key","success");
        String i =null;
        i.equals("123");
        return map;
    }

}
