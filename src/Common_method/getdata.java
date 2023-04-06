package Common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class getdata {
	public static ArrayList<String> getdataexcle(String testsheetname, String testcasename) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		
		//Stept1 locate excel data file by creating object of fileinputstrem
		FileInputStream fis = new FileInputStream("C:\\Users\\Nucleus Computer\\OneDrive\\Documents\\test_data.xlsx");
		
		//Step2 Create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Step3 access the desired sheet
		//Step 3.1 fetch the count of sheet available in excel file 
		int countofSheet = workbook.getNumberOfSheets();
		
		//Step 3.2 fetch the name of sheet and compare against desired sheet name
		for(int i=0;i<countofSheet;i++)
		{
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testsheetname))
			{
				// stept 4 access the sheet and iterate through row to fetch the column in which test case name is found
				XSSFSheet sheet = workbook.getSheetAt(i);
				// step 4.1 create iterator for rows
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				// step 4.2 create iterator for cells
				Iterator<Cell> cells = firstRow.cellIterator();
				int j = 0;
				int tc_column=0;
				
				// 4.3 Read the cell Values of row no1 to compare against the test case name
				while(cells.hasNext())
				{
					Cell cellvalue = cells.next();
					if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
					{
						tc_column = j;
					}
					j++;
				}
				// step 5 fetch the data for designated test case
				while(rows.hasNext())
				{
					Row dataRow = rows.next();
					if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> datacellvalue = dataRow.cellIterator();
						while(datacellvalue.hasNext())
						{
				            Cell dataofcell = datacellvalue.next();
				            try
				            {   
				            	String testdata = dataofcell.getStringCellValue();
							    arrayData.add(testdata);
						    }
				            catch (IllegalStateException e)
							{
								int inttestData = (int) dataofcell.getNumericCellValue();
								String StringtestData = Integer.toString(inttestData);
								arrayData.add(StringtestData);
							}
//				            CellType datatype = dataofcell.getcellType();
//						
//							if(datatype.toString() == "NUMERIC")
//							{
//								int inttestData = (int) dataofcell.getNumericCellValue();
//								System.out.println(inttestData);
//							}
//							else if (datatype.toString() == "STRING")
//							{
//								String testData = dataofcell.getStringCellValue();
//							    System.out.println(testData);
//							}
						}
					}
				}
			}
		}
		return arrayData;
	}
}