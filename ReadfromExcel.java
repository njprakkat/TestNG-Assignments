package RedBusDataProvider;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadfromExcel {
	
public String[][] readExcel(String filename) throws IOException
{

	XSSFWorkbook wb = new XSSFWorkbook("./Data/"+filename+".xlsx");
	XSSFSheet ws = wb.getSheet("Sheet1");
	int rowCount = ws.getLastRowNum();
	int colCount = ws.getRow(0).getLastCellNum();
	
	String[][] data = new String[rowCount][colCount];
	
	for(int i=1; i<= rowCount; i++)
	{
		for(int j=0 ; j < colCount; j++)
		{
			String value = ws.getRow(i).getCell(j).getStringCellValue();
		
			data[i-1][j] = value;
		}
	}
	
	wb.close();
	
	return data;
}

}
