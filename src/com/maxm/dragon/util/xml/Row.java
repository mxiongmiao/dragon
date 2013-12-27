package com.maxm.dragon.util.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class Row {
	private Element row;
	private HashMap<Integer, Element> cells;
	private static final QName QNAME = new QName("Index", new Namespace("ss",
			"urn:schemas-microsoft-com:office:spreadsheet"));
	private static Logger logger = Logger.getRootLogger();

	public Row(Element row) {
		this.row = row;
		@SuppressWarnings("unchecked")
		List<Element> cellList = row.elements();
		cells = new HashMap<>();
		int index = -1;
		for (Element cell : cellList) {
			String indexStr = cell.attributeValue(QNAME);
			if (indexStr == null || indexStr.length() == 0) {
				index++;
			} else {
				index = Integer.parseInt(indexStr) - 1;
			}
			cells.put(index, cell);
		}
	}

	public Cell getCell(int index) {
		Element obj = cells.get(index);
		if (obj != null) {
			try {
				return new Cell(obj);
			} catch (Exception e) {
				return null;
			}
		}
		logger.warn("can not find config for index=" + index);
		return null;
	}

	@SuppressWarnings("unchecked")
	public int getPhysicalNumberOfCells() {
		int length = 0;
		for (Iterator<Element> i = row.elementIterator(); i.hasNext();) {
			i.next();
			length++;
		}
		if (length == 0) {
			logger.warn("row length is null.");
		}
		return length;
	}
}
