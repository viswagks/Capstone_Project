package utilities;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ExcelUtility {
    public static HashMap<String, String> getData(String fileName, String sheetName, int id) throws Exception{
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Datasheets\\" + fileName + ".xls";

        DataFormatter df = new DataFormatter();
        HSSFWorkbook wb = new HSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
        HSSFSheet ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        HashMap<String, String> hm = new HashMap<>();

        int i;
        for (i=1; i<=rowCount; i++) {
            if (String.valueOf(id).equalsIgnoreCase(df.formatCellValue(ws.getRow(i).getCell(0)))) {
                break;
            } else if (i == rowCount) {
                return null;
            }
        }

        for (int j=0; j<ws.getRow(0).getLastCellNum(); j++) {
            String key = df.formatCellValue(ws.getRow(0).getCell(j));
            String value = df.formatCellValue(ws.getRow(i).getCell(j));
            hm.put(key, value);
        }

        return hm;
    }
}
