package helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static String readExcel(String filePath, int sheetNumber, int row, int column) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetNumber);
            Row excelRow = sheet.getRow(row);
            Cell cell = excelRow.getCell(column);
            return cell.getStringCellValue();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

