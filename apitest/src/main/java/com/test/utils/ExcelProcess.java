package com.test.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelProcess {
	public static Object[][] proessExcel(String filePath,int sheetId) throws IOException{
		//数据读入excel
		//user.dir                        用户的当前工作目录
		File file = new File(System.getProperty("user.dir")+filePath);
		
		FileInputStream fis = new FileInputStream(file);
		
		HSSFWorkbook wb = new HSSFWorkbook(fis);

		//读取特定表单并计算行列数
		HSSFSheet sheet = wb.getSheetAt(sheetId);
//		getPhysicalNumberOfRows()获取的是物理行数，也就是不包括那些空行（隔行）的情况。
		int numberOfRow = sheet.getPhysicalNumberOfRows();
		
		System.out.println(numberOfRow+"---");
		
		int numberOfCell = sheet.getRow(0).getLastCellNum();
		
		System.out.println(numberOfCell+"-----");
		
		//将表单数据存入dtt对象
		Object[][] dttData = new Object[numberOfRow][numberOfCell];
		for(int i=0;i<numberOfRow;i++) {
			if(null==sheet.getRow(i) || "".equals(sheet.getRow(i))){
				continue;
			}
			for(int j=0;j<numberOfCell;j++) {
				if(null==sheet.getRow(i).getCell(j) || "".equals(sheet.getRow(i).getCell(j))){
					continue;
				}
				HSSFCell cell = sheet.getRow(i).getCell(j);
				cell.setCellType(CellType.STRING);
				dttData[i][j]= cell.getStringCellValue();
				System.out.println(dttData[i][j]);
			}
						
		}
		
		return dttData;
		
	}
}
