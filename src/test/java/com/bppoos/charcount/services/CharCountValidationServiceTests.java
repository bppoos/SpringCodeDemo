package com.bppoos.charcount.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CharCountValidationServiceTests
{
	@Autowired
    private CharCountValidationService mockCharCountValidationService;
	
	@Test
	public void testSuccess()
	{
		assertTrue(mockCharCountValidationService.validateCharCountQueryParameters("aba", 10l, 'a'));
	}
	
	@Test
	public void testSampleFailureOneCap()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("abA", 10l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testSampleFailureAllCaps()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("ABA", 10l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testSampleFailureNonAlpha()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("ab1a", 10l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testTargetFailureCap()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("aba", 10l, 'A');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testTargetFailureNumber()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("aba", 10l, '1');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testSampleFailureTooManyAlpha()
	{
		StringBuilder tooManyLetter = new StringBuilder("a");
		for(int i = 0; i < 100 ; i++)
		{
			tooManyLetter.append("a");
		}
		
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters(tooManyLetter.toString(), 10l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("more"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testToConsiderFailureTooLarge()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("aba", 1000000000001l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("more"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testToConsiderFailureTooSmallToConsider()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("aba", 0l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("less"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testSampleFailureTooSmallAlpha()
	{
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters("", 10l, 'a');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("less"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
		}
	}
	
	@Test
	public void testMultipleFailures()
	{
		StringBuilder tooManyLetter = new StringBuilder("1");
		for(int i = 0; i < 100 ; i++)
		{
			tooManyLetter.append("a");
		}
		
		try
		{
			mockCharCountValidationService.validateCharCountQueryParameters(tooManyLetter.toString(), 0l, '1');
			fail("Should have thrown an exception");
		} 
		catch (ResponseStatusException rse)
		{
			assertTrue(rse.getMessage().contains("less"));
			assertTrue(rse.getMessage().contains("more"));
			assertTrue(rse.getMessage().contains("lowercase"));
			assertEquals(HttpStatus.BAD_REQUEST, rse.getStatus());
			
			System.out.println(rse.getMessage());
		}
	}
	
	
}
