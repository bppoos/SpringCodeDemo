package com.bppoos.charcount.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bppoos.charcount.rest.CharCountResponse;

@Component
@Scope("prototype")
public class CharCountService
{
	@Autowired
	private CharCountValidationService charCountValidationService;
	
	/**
	 * Find the number of occurrences of a character in a size determined subset of an infinitely long
	 * String that is a repeating sample string.
	 * 
	 * @param String sample A set of characters that will repeat as an infinite string. Between 1 and 100 characters in length.
	 * @param long size How large a subset of the infinite string to evaluate. Between 1 and 10^12.
	 * @param char targetChar The character that is being counted in the string defined by size.
	 * @return CharCountResponse Contains the number of occurrences of targetChar and all passed in arguments.
	 */
    public CharCountResponse findOccurences(String sample, long size, char targetChar) 
    {
    	// Validate the query parameters.
    	charCountValidationService.validateCharCountQueryParameters(sample, size, targetChar);
    	
    	// Use integer division to find the number of times sample repeats in the subset
    	long full = size / sample.length();
		
    	// Use modulo to find the size of partial sample that gets the subset up to size
		int leftover = Long.valueOf(size % sample.length()).intValue();
		
		// Get the number of times targetChar occurs in sample.
		long sampleCount = howManyOfLetterInString(sample,targetChar);
    	
		long remainder = 0; 
		
		// if there is any leftover, get the number of times targetChar occurs in the leftover string.
		if(0 != leftover)
		{
			remainder = howManyOfLetterInString(sample.substring(0, leftover), targetChar);
		}
		
		// The number of full samples times the number of occurences in the full sample plus the number of occurences in the remaninder
		long occurrences = (full * sampleCount) + remainder;
		
		return new CharCountResponse(sample, size, targetChar, occurrences);
    }
    
    /**
     * Count the number of occurrences of targetChar in sample.
     * 
     * @param String sample The string to be evaluated.
     * @param char targetChar The character to be counted
     * @return
     */
    public long howManyOfLetterInString(String sample, char targetChar)
    {
    	// Remove all instances of any character that isn't the target char and return string length.
    	String subset = sample.replaceAll("[^" + targetChar + "]", "");
    	return subset.length();
    }
}
