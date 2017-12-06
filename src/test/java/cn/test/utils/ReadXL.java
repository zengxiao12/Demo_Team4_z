package cn.test.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
public class ReadXL {
    /** Excel文件的存放位置。注意是反斜线*/
    public static String fileToBeRead = "E:\\test1.xls";
    public static void main(String argv[]) {
        try {
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
            // 创建对工作表的引用。
            // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
            //HSSFSheet sheet = workbook.getSheet("Sheet1");
            // 也可用getSheetAt(int index)按索引引用，
            // 在Excel文档中，第一张工作表的缺省索引是0，
            HSSFSheet sheet = workbook.getSheetAt(0);
            // 读取左上端单元
            //获得第一行
           int startRow=sheet.getFirstRowNum();
           //获得该表最后一行
           int lastRow=sheet.getLastRowNum();
           //获得该表行数
           int rowSize=lastRow-startRow;
           HSSFRow row = sheet.getRow(1);
           //获得第一行第一列数
           short firstRow=row.getFirstCellNum();
           //获得第一行最后一列数
           short endRow=row.getLastCellNum();
           //获得该表总列数
           int rowNum=endRow-firstRow;
           Map<Integer,String> maps=new HashMap<Integer,String>();
           for(int i=1;i<=rowSize;i++){
               for(int j=1;j<=rowNum;j++){
                   HSSFCell cell = row.getCell((short)j);
                   String cellValue=cell.getStringCellValue();
                   maps.put(i, cellValue);
               }
               System.out.println(maps);
           }
            //HSSFRow row = sheet.getRow(0);
            HSSFCell cell = row.getCell((short)0);
            // 输出单元内容，cell.getStringCellValue()就是取所在单元的值
            System.out.println("左上端单元是： " + cell.getStringCellValue());
        } catch (Exception e) {
            System.out.println("已运行xlRead() : " + e);
        }
    }
}
