package weixin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nutz.json.Json;

public class PoiTest {
	//@Test
	public static List<ExcelBean> testRead() {
		List<ExcelBean> dataList = new ArrayList<ExcelBean>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("F://易起跑.xlsx"));
			Sheet sheet = workbook.getSheetAt(2);
			int rowCount = sheet.getLastRowNum();

			Row row1 = sheet.getRow(0);
			int cellCount = row1.getLastCellNum();
			Map<Integer, Date> cellMap = new HashMap<Integer, Date>();
			for (int i = 1; i < cellCount; i++) {
				Cell cell = row1.getCell(i);
				Date d = cell.getDateCellValue();
				cellMap.put(i, d);
			}
			//System.out.println(Json.toJson(cellMap));
			// return;
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				ExcelBean excelBean = new ExcelBean();
				excelBean.setName(row.getCell(0).getStringCellValue());
				HashMap<Date, Integer> data = new HashMap<Date, Integer>();
				for (int j = 1; j < cellCount; j++) {
					Cell cell = row.getCell(j);
					int rank = new Double(cell.getNumericCellValue()).intValue();
					data.put(cellMap.get(j), rank);
				}
				excelBean.setData(data);
				dataList.add(excelBean);
			}
			//System.out.println(Json.toJson(dataList));
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
}
