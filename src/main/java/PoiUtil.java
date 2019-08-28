import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

import bean.DlGrainData;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {

    public static void main(String[] args) {
        try {
            Map<String, String> cellmap = new LinkedHashMap<String, String>();

            String ss = "20190813,20190814,20190815,20190816,20190819,20190820,20190821";
            String[] sp = ss.split(",");
            for (int i = 0; i < sp.length; i++) {
                cellmap.put(String.valueOf(i + 3), sp[i]);
            }
//	        System.out.println(cellmap);
            String strPath = "/Users/dongxueyong/Desktop/解析Excel/1/粮食报表/小麦(1).xlsx";
            InputStream is = new FileInputStream(strPath);

            XSSFWorkbook workbook1 = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook1.getSheetAt(0); // 创建对工作表的引用
            String gartype = "2";
//			System.out.println("rows:"+rows);
            int columns = sp.length + 3;
            String contry = "";

            Map<String, Map<String, DlGrainData>> map = new LinkedHashMap<String, Map<String, DlGrainData>>();
            for (int r = 0; r < sheet.getLastRowNum(); r++) { // 循环遍历表格的行
                if (r == 2 || isDivisibleBy7(r - 2)) {//地区，最低价\
                    Row row = sheet.getRow(r);
                    String province = "";
                    String city = "";
                    for (int i = 0; i < columns; i++) {

                        Cell cell = row.getCell(i);
                        String value = getCellValue(cell);
//						System.out.println(value);
                        if (i == 0) {
                            String[] vs = value.split(" ");
                            province = vs[0];
                            city = vs[1];
                        }
                        if (i == 1) {
                            if (StringUtils.isNotBlank(value) && !value.equals(contry)) {
                                contry = value;
                            }
                        }
                        if (i > 2) {
                            String date = cellmap.get(String.valueOf(i));
                            Map<String, DlGrainData> beanMap = map.get(date);
                            if (beanMap == null) {
                                Map<String, DlGrainData> beansMap = new LinkedHashMap<String, DlGrainData>();
                                beansMap.put(contry, new DlGrainData(province, city, contry, value));
                                map.put(date, beansMap);
                            } else {
                                beanMap.put(contry, new DlGrainData(province, city, contry, value));
                                map.put(date, beanMap);
                            }
                        }

                    }
                }
                if (r == 3 || isDivisibleBy7(r - 3)) {//主流最低价
                    Row row = sheet.getRow(r);
                    for (int i = 0; i < columns; i++) {
                        Cell cell = row.getCell(i);
                        String value = getCellValue(cell);
                        if (i > 2) {
                            String date = cellmap.get(String.valueOf(i));
                            Map<String, DlGrainData> beanMap = map.get(date);
                            DlGrainData grainBean = beanMap.get(contry);
                            grainBean.setDataMainminprice(value);
                            beanMap.put(contry, grainBean);
                            map.put(date, beanMap);
                        }
                    }
                }
                if (r == 4 || isDivisibleBy7(r - 4)) {//主流最高价
                    Row row = sheet.getRow(r);
                    for (int i = 0; i < columns; i++) {
                        Cell cell = row.getCell(i);
                        String value = getCellValue(cell);
                        if (i > 2) {
                            String date = cellmap.get(String.valueOf(i));
                            Map<String, DlGrainData> beanMap = map.get(date);
                            DlGrainData grainBean = beanMap.get(contry);
                            grainBean.setDataMainmaxprice(value);
                            beanMap.put(contry, grainBean);
                            map.put(date, beanMap);
                        }
                    }
                }
                if (r == 5 || isDivisibleBy7(r - 5)) {//最高价
                    Row row = sheet.getRow(r);
                    for (int i = 0; i < columns; i++) {
                        Cell cell = row.getCell(i);
                        String value = getCellValue(cell);
                        if (i > 2) {
                            String date = cellmap.get(String.valueOf(i));
                            Map<String, DlGrainData> beanMap = map.get(date);
                            DlGrainData grainBean = beanMap.get(contry);
                            grainBean.setDataMaxprice(value);
                            beanMap.put(contry, grainBean);
                            map.put(date, beanMap);
                        }
                    }
                }

            }

//			System.out.println(map);

            StringBuffer sb = new StringBuffer();
            for (Entry<String, Map<String, DlGrainData>> entry : map.entrySet()) {
                String date = entry.getKey();
                String formatDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                Map<String, DlGrainData> beansMap = entry.getValue();
                for (Entry<String, DlGrainData> et : beansMap.entrySet()) {
                    DlGrainData grainBean = et.getValue();
                    String dataMaxprice = grainBean.getDataMaxprice();
                    if (StringUtils.isNotBlank(dataMaxprice)) {
                        sb.append("INSERT INTO dl_grain_data (data_id, data_type, data_regiontype, data_province, data_city, data_county, grain_type, grain_variety, data_year, data_gbgrade, data_number, data_dealnumber, data_maxprice, data_minprice, data_mainmaxprice, data_mainminprice, data_trantime) VALUES "
                                + "('" + UUID.randomUUID() + "', 2, 1, '" + grainBean.getDataProvince() + "', '" + grainBean.getDataCity() + "', '" + grainBean.getDataCounty() + "', " + gartype + ", null, null, null, null, null, " + grainBean.getDataMaxprice() + ", " + grainBean.getDataMinprice() + ", " + grainBean.getDataMainmaxprice() + ", " + grainBean.getDataMainminprice() + ", '" + formatDate + " 00:00:00');");

                        sb.append("\n");
                    }
                }
            }

            String[] s = sb.toString().split(";");
            System.out.println(s.length);
            System.out.println(sb.toString());
            is.close();
            workbook1.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell != null) {
            int cellType = cell.getCellType();
            if (cellType == 1) {//string
                String temp = cell.getStringCellValue();
//				System.out.println(temp);
                return temp;
            } else if (cellType == 0) {
                int numericCellValue = (int) cell.getNumericCellValue();
//				System.out.println(numericCellValue);
                return String.valueOf(numericCellValue);
            }
        }
        return "";
    }

    private static boolean isDivisibleBy7(int n) {
        return (n % 7 == 0) ? true : false;
    }
}
