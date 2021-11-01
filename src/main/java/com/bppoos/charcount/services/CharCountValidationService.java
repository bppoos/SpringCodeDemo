package com.bppoos.charcount.services;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Scope("prototype")
public class CharCountValidationService
{
	private static final long LOWER_LIMIT = 1;
	private static final long MAX_SAMPLE_SIZE = 100;
	private static final long MAX_CHARS_TO_CONSIDER = (long) Math.pow(10, 12);
	
	// A StringBuilder to pass around that accumulates error messages
	StringBuilder errors = new StringBuilder();
	
	/**
	 * A method to run all the lower level validation, accumulate error messages and throw an exception if
	 * any errors occurred.
	 * 
	 * @param String sample A set of characters that will repeat as an infinite string. Between 1 and 100 characters in length.
	 * @param long size How large a subset of the infinite string to evaluate. Between 1 and 10^12.
	 * @param char targetChar The character that is being counted in the string defined by size.
	 * @return boolean return true if no errors were found. This makes testing simpler.
	 */
	public boolean validateCharCountQueryParameters(String sample, long size, char targetChar)
	{
		// Check that the sample is all lowercase
		checkCase(sample);
		// Check that the target character is lower case
		checkCase(String.valueOf(targetChar));
		// Check that size is within the sizes defined in the problem statement
		checkSize(LOWER_LIMIT, MAX_CHARS_TO_CONSIDER, size, "The number of characters to consider");
		//Check that the sample is within the sizes defined in the problem statment
		checkSize(LOWER_LIMIT, MAX_SAMPLE_SIZE, sample.length(), "The length of the String to repeat");
		
		// If there are any error messages, throw a 400 exception with all the error messages.
		if(errors.toString().length() > 0)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		
		//No errors so return true.
		return true;
	}
	
	/**
	 * Check a string for non-lowercase alpha characters. If so, add them to the error string
	 * 
	 * @param String checkString the string to be validated.
	 */
	private void checkCase(String checkString)
	{
	    if(!checkString.matches("[a-z]+"))
		{
	    	errors.append(checkString);
			errors.append(" should be all lowercase alpha.");
		}
	}
	
	/**
	 * Check that the passed size is between the defined upper and lower limits. If not, add an error message.
	 * @param long lowerLimit
	 * @param long upperLimit
	 * @param long size The amount to be validated.
	 * @param String messagePrefix The validation specific prefix to add to the error message;
	 */
	private void checkSize(long lowerLimit, long upperLimit, long size, String messagePrefix)
	{
		if(size < lowerLimit)
		{
			errors.append(messagePrefix);
			errors.append(" can't be less than ");
			errors.append(upperLimit);
			errors.append(". ");
		}
		else if(size > upperLimit)
		{
			errors.append(messagePrefix);
			errors.append(" can't be more than ");
			errors.append(upperLimit);
			errors.append(". ");
		}
	}
}
