package com.codegym.controller;

import com.codegym.model.PhoneNumber;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public ModelAndView getHomePage(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("phoneNumber", new PhoneNumber());
        return modelAndView;
    }
    @PostMapping("/validatePhoneNumber")
    public ModelAndView validatePhoneNumber(@Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, BindingResult bindingResult){
        new PhoneNumber().validate(phoneNumber, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("result");
            modelAndView.addObject("phoneNumber", phoneNumber.getNumber());
            return modelAndView;
        }

    }
}