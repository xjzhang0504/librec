import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Util {
	public static void main(String[] args) {
		//Util.removeRepeatInTxt("/Users/xj/Desktop/datalist/itemAndRatingList/item.txt", "/Users/xj/Desktop/datalist/itemAndRatingList/itemout.txt");
		//Util.removeRepeatInTxt("/Users/xj/Desktop/datalist/userList/user.txt", "/Users/xj/Desktop/datalist/userList/userout.txt");
        Util.countUser("./data/xcly/uirt.arff");

	}
	
    public static String removeRepeatInTxt(String inputTxtPath, String outpuTxtPath) {
    	    try {
				FileReader fileReader = new FileReader(inputTxtPath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(outpuTxtPath);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				Set itemIdSet = new HashSet<>();
				String str = null;
	            try {
					while((str = bufferedReader.readLine()) != null) {
                        String itemId = str.split(",")[0];
                        if(itemIdSet.contains(itemId)) {
                        	    continue;
                        }else {
                        	    itemIdSet.add(itemId);
							bufferedWriter.write(str);
							bufferedWriter.newLine();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            bufferedReader.close();
	            bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    return null;
    }
    //计算用户数
    public  static void  countUser(String inputTxtPath) {
		int count = 0;
		try {
			FileReader fileReader = new FileReader(inputTxtPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//FileWriter fileWriter = new FileWriter(outpuTxtPath);
			//BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			Set userSet = new HashSet();
			String str = null;
			try {
				while ((str = bufferedReader.readLine()) != null) {
					String userId = str.split(",")[0];
					if (userSet.contains(userId)) {
						continue;
					} else {
						userSet.add(userId);
						count++;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(count);
	}
}
