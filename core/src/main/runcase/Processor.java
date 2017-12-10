import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.LinkedList;

public class Processor {
	public static void main(String[] args) {
	    Processor processor = new Processor();
	    //processor.dealOneTypeXml("/Users/xj/Desktop/datalist/itemAndRatingList/imported", "/Users/xj/Desktop/datalist/itemAndRatingList/uirt.txt");
	   // processor.dealOneTypeXml("/Users/xj/Desktop/datalist/itemAndRatingList/imported", "/Users/xj/Desktop/datalist/itemAndRatingList/uirt.txt");

		//all data

		ReadXmlAndWriteTxt readXmlAndWriteTxtAll = new ReadXmlWriteTxtUIRT();
		processor.dealOneTypeXml(readXmlAndWriteTxtAll, "/Users/xj/Desktop/shanDong/allinfor/imported", "/Users/xj/Desktop/shanDong/allinfor/uirt.txt");

		//hebei
//	    ReadXmlAndWriteTxt readXmlAndWriteTxt = new ReadXmlWriteTxtUIRT();
//	    processor.dealOneTypeXml(readXmlAndWriteTxt, "/Users/xj/Desktop/datalist/itemAndRatingList/imported", "/Users/xj/Desktop/datalist/itemAndRatingList/uirt.txt");
//        //shandong
//		ReadXmlAndWriteTxt readXmlAndWriteTxtShanDong = new ReadXmlWriteTxtUIRT();
//		processor.dealOneTypeXml(readXmlAndWriteTxtShanDong,"/Users/xj/Desktop/shanDong/shanDongDetail/imported","/Users/xj/Desktop/shanDong/shanDongDetail/shanDong.txt");
//		//shanxi
//		ReadXmlAndWriteTxt readXmlAndWriteTxtShanXi = new ReadXmlWriteTxtUIRT();
//		processor.dealOneTypeXml(readXmlAndWriteTxtShanXi,"/Users/xj/Desktop/shanDong/shanXiDetail/imported","/Users/xj/Desktop/shanDong/shanXiDetail/shanXi.txt");
//
//		//henan
//		ReadXmlAndWriteTxt readXmlAndWriteTxtHeNan = new ReadXmlWriteTxtUIRT();
//		processor.dealOneTypeXml(readXmlAndWriteTxtHeNan ,"/Users/xj/Desktop/shanDong/heNanDetail/imported","/Users/xj/Desktop/shanDong/heNanDetail/heNanuirtSe12.txt");

//	    readXmlAndWriteTxt = new ReadXmlWriteTxtItem();
//	    processor.dealOneTypeXml(readXmlAndWriteTxt, "/Users/xj/Desktop/datalist/itemAndRatingList/imported", "/Users/xj/Desktop/datalist/itemAndRatingList/item.txt");
//
//	    readXmlAndWriteTxt = new ReadXmlWriteTxtUser();
//	    processor.dealOneTypeXml(readXmlAndWriteTxt, "/Users/xj/Desktop/datalist/userList/imported", "/Users/xj/Desktop/datalist/userList/user.txt");
//	    System.out.println("success");
	} 
	
	public String dealOneTypeXml(ReadXmlAndWriteTxt readXmlAndWriteTxt, String dirName, String txtPath) {
		try {
			File txtFile = new File(txtPath);
			if (txtFile.exists()) {
				txtFile.delete();
			}
			File file = new File(dirName);
			String[] fileList = file.list();

			int xmlCount = 0;
			int xmlSuccessCount = 0;
			int xmlFailCount = 0;
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].contains(".xml")) {
					xmlCount++;
					String result = readXmlAndWriteTxt.process(dirName + File.separator + fileList[i], txtPath);
					System.out.println(result);
					if (result.contains("success")) {
						xmlSuccessCount++;
					}else if(result.contains("fail")) {
						xmlFailCount++;
					}else {
						new Exception("unknow exception");
					}
				} else {
					System.out.println(fileList[i] + " is invalid !!!");
				}
			}
			boolean flag = xmlCount == xmlSuccessCount + xmlFailCount;
			assert flag;
			
            int txtLineCount = Processor.getFileLineCount(txtPath);
            System.out.println("txtLineCount is : " + txtLineCount);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return dirName + " is fail !!!";
		}
		return dirName + " is ok !!!";
	}
	
	public static int getFileLineCount(String filename) {
        int cnt = 0;
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(filename));
            @SuppressWarnings("unused")
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {
            }
            cnt = reader.getLineNumber();
        } catch (Exception ex) {
            cnt = -1;
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return cnt;
    }
}
