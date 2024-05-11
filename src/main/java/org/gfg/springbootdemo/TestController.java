package org.gfg.springbootdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
//spring.application.name=springboot-demo

@RestController
    public class TestController {

    private static final Logger logger
            = LoggerFactory.getLogger(TestController.class);

        @GetMapping("/test")
        public String getText(){
        logger.info("Dummy Text");
            return "dummy Text";
        }

        @GetMapping("/static-data")
        public ModelAndView getStaticData(){
            return new ModelAndView("demo.html");
        }

        @GetMapping("/dynamic-data")
    public ModelAndView getDynamicData() {
            Date d=new Date();

            logger.debug("Dummy Text",d);
            ModelAndView modelAndView = new ModelAndView("ProductPage.html");
            modelAndView.getModelMap().addAttribute("name", "Jayansh");

            //this attribute will usually come from Api call
        return modelAndView;
        }
    }

