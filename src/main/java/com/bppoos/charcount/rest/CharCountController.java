package com.bppoos.charcount.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bppoos.charcount.services.CharCountService;

@RestController
public class CharCountController
{
	@Autowired
	private CharCountService charCountService;
	
	@GetMapping("/charcount")
	public CharCountResponse findOccurences(@RequestParam(value = "sample") String sample, 
			@RequestParam(value = "size") long size, 
			@RequestParam(value = "targetChar") char targetChar)
	{
		return charCountService.findOccurences(sample, size, targetChar);
	}
}
