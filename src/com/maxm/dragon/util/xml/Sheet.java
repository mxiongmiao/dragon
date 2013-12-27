package com.maxm.dragon.util.xml;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;

public class Sheet {
	private List<Element> element;
	private static Logger logger = Logger.getRootLogger();
	private String name;

	public Sheet(List<Element> element, String name) {
		this.name = name;
		this.element = element;
	}

	public String getName() {
		return name;
	}

	public int getPhysicalNumberOfRows() {
		if (element == null || element.size() == 0) {
			logger.warn("config is null.");
		}
		return element.size();
	}

	public Row getRow(int i) {
		if (element == null || element.size() == 0) {
			logger.warn("config is null.");
		}
		return new Row(element.get(i));
	}
}
