import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ReadXmlWriteTxtUser implements ReadXmlAndWriteTxt{

	@Override
	public String process(String xmlFilePath, String txtFilePath) {
		// TODO Auto-generated method stub
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File(xmlFilePath));
			Element root = document.getRootElement();
			List allChildren = root.getChildren();
			List children12Content = ((Element)allChildren.get(12)).getContent();//定位到用户信息
			Element userInfo =(Element)children12Content.get(0);//定位到 用户信息-item
			List userInfoList =userInfo.getContent();//得到用户信息列表
			Map<String, String> userMap = new HashMap<>();
			Pattern intPattern = Pattern.compile("[^0-9]");
			//userId
			String userId =((Element)userInfoList.get(5)).getContent().toString();
			userId=intPattern.matcher(userId).replaceAll("").trim();
			userMap.put("userId", userId);
			//userSex
			List userSex	=((Element)userInfoList.get(1)).getContent();
			if (userSex.size()>0) {
				userMap.put("userSex", "0");//0代表male
			}else
			{
				userMap.put("userSex", "1");//1代表female
			}
		    //userLevel
			String userLevel = ((Element)userInfoList.get(2)).getContent().toString();//userLevel
			userLevel=intPattern.matcher(userLevel).replaceAll("").trim();
			userMap.put("userLevel", userLevel);
			//userFriendsCount
			String userFriendsCount = ((Element)userInfoList.get(4)).getContent().toString();//userFriendsCount
			userFriendsCount=intPattern.matcher(userFriendsCount).replaceAll("").trim();
			userMap.put("userFriendsCount", userFriendsCount);
			//userFansCount
			String userFansCount = ((Element)userInfoList.get(6)).getContent().toString();//userFansCount
			userFansCount=intPattern.matcher(userFansCount).replaceAll("").trim();
			userMap.put("userFansCount", userFansCount);
			//userAddress 没想好代号方式,没想好怎么使用
			//该属性可能为空，为空时居住地用“0”表示
			List userAddressList =((Element)userInfoList.get(3)).getContent();
			if (userAddressList.size()>0) {
			    String userAddress=userAddressList.toString().split(":")[1].split("：")[1].split("]")[0];
			    userMap.put("userAddress", userAddress);
			}else {
				userMap.put("userAddress", "0");
			}
			try {//userId  userSex  userLevel userFriendsCount userFansCount userAddress
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtFilePath,true));
				for(int i = 0; i < userMap.size(); i++) {
					bufferedWriter.write(userMap.get("userId").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(userMap.get("userSex").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(userMap.get("userLevel").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(userMap.get("userFriendsCount").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(userMap.get("userFansCount").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(userMap.get("userAddress").toString());
					bufferedWriter.write(",");
					bufferedWriter.newLine(); // 注意\n不一定在各种计算机上都能产生换行的效果
				}
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return xmlFilePath + " is fail !!!";
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return xmlFilePath + " is fail !!!";
		}
		return xmlFilePath + " is success !!!";
	}
}
