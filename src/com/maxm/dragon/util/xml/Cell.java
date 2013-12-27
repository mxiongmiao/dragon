package com.maxm.dragon.util.xml;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class Cell {
	private String type;
	private Element data;
	private static Logger logger = Logger.getRootLogger();
	private static final QName QNAMEType = new QName("Type", new Namespace("ss",
			"urn:schemas-microsoft-com:office:spreadsheet"));

	public Cell(Element cell) throws Exception {
		data = cell.element("Data");
		if (data == null) {
			// 此处获取SSData
			throw new Exception("null data");
		}
		type = data.attributeValue(QNAMEType);
	}

	public double getNumericCellValue() {
		if (!type.equals("Number")) {
			logger.warn("cell type is error.type is not number.value=" + data.getTextTrim());
			return 0;
		}
		return Double.parseDouble(data.getText());
	}

	public String getCellType() {
		return type;
	}

	public String getStringCellValue() {
		if (!type.equals("String")) {
			logger.warn("cell type is error.type is not string.value=" + data.getTextTrim());
		}
		String res = data.getText();
		if (res == null || res.length() == 0) {
			@SuppressWarnings("unchecked")
			List<Element> list = data.elements();
			for (Element e : list) {
				res = e.getText();
				if (res != null && res.length() > 0) {
					break;
				}
			}
		}
		return res;
	}
}
