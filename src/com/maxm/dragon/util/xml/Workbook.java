package com.maxm.dragon.util.xml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

public class Workbook {
	private Document document;
	private HashMap<String, Sheet> workSheets;
	private static final QName QNAME = new QName("Name", new Namespace("ss",
			"urn:schemas-microsoft-com:office:spreadsheet"));
	private static final String TABLE_NAME = "Table";
	private static final String ROW_NAME = "Row";
	private static Logger logger = Logger.getRootLogger();

	@SuppressWarnings("unchecked")
	public Workbook(InputStream inp) throws DocumentException {
		SAXReader reader = new SAXReader();
		document = reader.read(inp);
		workSheets = new HashMap<String, Sheet>();
		Element root = document.getRootElement();
		List<Element> temp = root.elements("Worksheet");
		for (Element obj : temp) {
			Element table = obj.element(TABLE_NAME);
			List<Element> tempE = table.elements(ROW_NAME);

			String name = obj.attributeValue(QNAME);
			Sheet s = new Sheet(tempE, name);
			workSheets.put(name, s);
		}
	}

	public Sheet getSheet(String sheetName) {
		if (sheetName == null || sheetName.length() == 0) {
			logger.warn("sheetName is null");
			return null;
		}
		Sheet s = workSheets.get(sheetName);
		if (s == null) {
			logger.warn("can not find sheet by name=" + sheetName);
			return null;
		}
		return s;
	}

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		// String name = "E:/svn/docs/number/l10n.xml";
		// InputStream inp = new FileInputStream(name);
		// Workbook wb = new Workbook(inp);
		// Sheet sheet = wb.getSheet("道具");
		// int rows = sheet.getPhysicalNumberOfRows();
		// ArrayList<ItemType> itemTypes = new ArrayList<ItemType>();
		// for (int i = 1; i < rows; i++) {
		// Row row = sheet.getRow(i);
		// if (row == null || row.getCell(0) == null) {
		// logger.warn("key is empty, row=" + i + ",sheet=" + sheet.getName());
		// continue;
		// }
		// for (int j = 1; j < row.getPhysicalNumberOfCells(); j++) {
		// // for (int j = 1; j <= 1; j++) {
		// L10n l10n = new L10n(row, j);
		// if (l10n.getKey().length() == 0) {
		// String msg = "key is empty, row=" + i + ", col=" + j;
		// logger.warn(msg + ", sheet=" + sheet.getName());
		// continue;
		// }
		// }
		// }
	}
}
