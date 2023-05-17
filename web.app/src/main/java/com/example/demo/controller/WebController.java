package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
public class WebController {

	@GetMapping(value = "/")
	public String index(Model model) {

		model.addAttribute("message", "ようこそ");
		model.addAttribute("datetime", LocalDateTime.now());

		return "index";
	}

}