package com.bppoos.charcount.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bppoos.charcount.rest.CharCountResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CharCountServiceTest
{
	@Autowired
	private CharCountService charCountService;
	
	@Test
	public void testCountCharSuccess()
	{
		assertEquals(2, charCountService.howManyOfLetterInString("aba", 'a'));
	}
	
	@Test
	public void testCountCharSuccessZero()
	{
		assertEquals(0, charCountService.howManyOfLetterInString("", 'a'));
	}
	
	@Test
	public void testFindOccurencesSuccess()
	{
		CharCountResponse response =charCountService.findOccurences("aba", 10, 'a');
		assertEquals(7, response.getOccurrences());
	}
	
	@Test
	public void testFindOccurencesOneCharSample()
	{
		CharCountResponse response =charCountService.findOccurences("a", 100, 'a');
		assertEquals(100, response.getOccurrences());
	}
	
	@Test
	public void testFindOccurencesIncomplete()
	{
		CharCountResponse response =charCountService.findOccurences("ababababa", 3, 'a');
		assertEquals(2, response.getOccurrences());
	}
	
	@Test
	public void testFindOccurencesNotThere()
	{
		CharCountResponse response =charCountService.findOccurences("ababababa", 3, 'c');
		assertEquals(0, response.getOccurrences());
	}
}
