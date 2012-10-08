package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.xmltagparser.Tag;

interface PropertyTagHandler
{
	public void handlePropertyChange(String propertyName);
	public void handlePropertyValue(Tag propertyValue);
}
