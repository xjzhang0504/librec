
import jxl.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

public class ReadExcel {
	public static void main(String[] args) {
		int i;
		Sheet sheet;
		Workbook book;
		Cell cell0, cell1, cell2, cell3, cell4, cell5, cell6;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File("/Users/xj/Desktop/listInfo/itemRating.xls"));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			// 获取左上角的单元格
			cell1 = sheet.getCell(0, 0);
			System.out.println("标题：" + cell1.getContents());

			i = 1;
			
			File file = new File("/Users/xj/Desktop/listInfo/itemRating.txt");
			FileWriter fileWriter = new FileWriter(file);
			while (true) {
				// 获取每一行的单元格
//				cell1 = sheet.getCell(0, i);// （列，行）
				cell2 = sheet.getCell(1, i);
				String[] strArray = cell2.getContents().split("/");
				String itemId = strArray[4].split(".html")[0];
				fileWriter.write(itemId.toCharArray());
				fileWriter.write(",");
				
				cell3 = sheet.getCell(2, i);
			//	fileWriter.write();
				
				if ("".equals(cell1.getContents()) == true) // 如果读取的数据为空
					break;
				System.out.println(cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents());
				i++;
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
