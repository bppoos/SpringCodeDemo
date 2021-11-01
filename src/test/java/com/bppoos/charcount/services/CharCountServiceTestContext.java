package com.bppoos.charcount.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CharCountServiceTestContext
{

	public CharCountServiceTestContext()
	{
		System.out.println("Starting CharCountServiceTestContext");
	}
	
}
