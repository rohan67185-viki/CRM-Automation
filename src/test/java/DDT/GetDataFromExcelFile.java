package DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelFile {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\LOQ\\eclipse-workspace\\vtiger-crm\\src\\test\\resources\\TestData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh =  wb.getSheet("Dbz");
		Row row = sh.getRow(3);
		Cell cell = row.getCell(1);
		
		//String data = cell.getStringCellValue();
		//System.out.println(data);
		
		int data2 = (int) cell.getNumericCellValue();
		System.out.println(data2);
		
	}
	

}
