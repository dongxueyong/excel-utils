import bean.DemoPO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        String strPath = "/Users/dongxueyong/Desktop/解析Excel/1/粮食报表/小麦(1).xlsx";
        try {
            InputStream is = new FileInputStream(strPath);
            List<Object[]> objext = readExcelToObj(is,256);
//            System.out.println(objext.toString());
            for(Object obj : objext){
                DemoPO demoPO = new DemoPO();

                System.out.println((obj));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    /**
     * 读取excel数据,调用这方法开始
     *
     * @param is
     * @param indexNum 至少需要多少列数据
     */
    public static List<Object[]> readExcelToObj(InputStream is, int indexNum) {

        Workbook wb = null;
        List<Object[]> objArrList = null;
        try {
            objArrList = new ArrayList<>();
            wb = WorkbookFactory.create(is);
            readExcel(wb, 0, 0, 0, objArrList, indexNum);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objArrList;
    }

    /**
     * 读取excel文件
     *
     * @param wb
     * @param sheetIndex    sheet页下标：从0开始
     * @param startReadLine 开始读取的行:从0开始
     * @param tailLine      去除最后读取的行
     */
    public static void readExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine, List<Object[]> objArrList, int indexNum) {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = null;

        for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {
            row = sheet.getRow(i);
            List<Object> objList = new ArrayList<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                //for(Cell c : row) {
                Cell c = row.getCell(j);
                if (c == null) {
                    objList.add("");
                    continue;
                }
                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                //判断是否具有合并单元格
                if (isMerge) {
                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
                    objList.add(rs);
                } else {
                    objList.add(getCellValue(c));
                }

            }
            while (objList.size() < indexNum) {
                objList.add("");
            }
            objArrList.add(objList.toArray());
        }
    }


    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        //System.out.println("=========="+sheetMergeCount);
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {

                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param row    行下标
     * @param column 列下标
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {

        if (cell == null) return "";

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            System.out.println("=========="+cell.getStringCellValue());
            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            System.out.println("=========="+cell.getBooleanCellValue());
            return String.valueOf(cell.getBooleanCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            System.out.println("=========="+cell.getCellFormula());
            return cell.getCellFormula();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            System.out.println("=========="+cell.getNumericCellValue());
            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }

}
