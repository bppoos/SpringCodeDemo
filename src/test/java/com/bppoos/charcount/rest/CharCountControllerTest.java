package com.bppoos.charcount.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CharCountControllerTest
{
	@Autowired
	private MockMvc mvc;
	
	@Test
	void getSuccess() throws Exception 
	{
		mvc.perform(MockMvcRequestBuilders.get("/charcount?sample=aba&size=10&targetChar=a")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void getFailure() throws Exception 
	{
		mvc.perform(MockMvcRequestBuilders.get("/charcount?sample=aba&size=10&targetChar=1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
