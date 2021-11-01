package com.bppoos.charcount.rest;

public class CharCountResponse
{
	private final String sample;
	private final long size;
	private final char targetChar;
	private final long occurrences;
	
	public CharCountResponse(String sample, long size, char targetChar, long occurrences)
	{
		this.sample = sample;
		this.size = size;
		this.targetChar = targetChar;
		this.occurrences = occurrences;
	}
	public String getSample()
	{
		return sample;
	}
	public long getSize()
	{
		return size;
	}
	public char getTargetChar()
	{
		return targetChar;
	}
	public long getOccurrences()
	{
		return occurrences;
	}
	
	
}
