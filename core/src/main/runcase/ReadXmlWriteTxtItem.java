import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.DocumentationTool.Location;

import org.jdom.*;
import org.jdom.input.*;

public class ReadXmlWriteTxtItem implements ReadXmlAndWriteTxt{
	
	@Override
	public String process(String xmlFilePath, String txtFilePath) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> itemMap = new HashMap<>();
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File(xmlFilePath));
			Element root = document.getRootElement();
			List allChildren = root.getChildren();
			Element element11 = (Element) allChildren.get(11);
			Pattern intPattern = Pattern.compile("[^0-9]");
			String itemId = ((Element) ((Element) element11.getContent().get(0)).getContent().get(1)).getContent().get(0).toString();
			itemId = intPattern.matcher(itemId).replaceAll("").trim();
			itemMap.put("itemId", itemId);

			//景点名
			Element element12 = (Element) allChildren.get(12);
			Element element12Content = (Element) element12.getContent().get(0);
			List element12ContentList = element12Content.getContent();
			String itemName = ((Element) element12ContentList.get(0)).getContent().get(0).toString().split(":")[1].split("]")[0];		
			itemMap.put("itemName", itemName);
			
			//所处位置没想好怎么应用，格式没想好怎么代替汉字   
			//该属性可能为空，采集数据时，错位到了“景点描述”标签，因此采集“景点描述”标签
			List itemlocationContent=((Element)element12ContentList.get(6)).getContent();
			if (itemlocationContent.size()>0) {
				String itemLocation =itemlocationContent.get(0).toString().split(":")[1].split("]")[0];
				itemMap.put("itemLocation", itemLocation);
			}else {
				String itemLocation = ((Element) element12ContentList.get(1)).getContent().get(0).toString().split(":")[1].split("]")[0];
				itemMap.put("itemLocation", itemLocation);
			}
			
			
			for(int i = 7; i < 11; i++) {
				List list = ((Element)element12ContentList.get(i)).getContent();
				if(list.size() > 0) {//解决了好评、中评、差评为空的问题
					String value = intPattern.matcher(list.get(0).toString()).replaceAll("").trim();
					switch (i) {
					case 7:
						itemMap.put("itemCommentCount", value);
						break;
					case 8:
						itemMap.put("itemGreatCommentCount", value);
						break;
					case 9:
						itemMap.put("itemGoodCommentCount", value);
						break;
					case 10:
						itemMap.put("itemBadCommentCount", value);
						break;
					default:
						break;
					}
				}else {
					switch (i) {
					case 7:
						itemMap.put("itemCommentCount", "0");
						break;
					case 8:
						itemMap.put("itemGreatCommentCount", "0");
						break;
					case 9:
						itemMap.put("itemGoodCommentCount", "0");
						break;
					case 10:
						itemMap.put("itemBadCommentCount", "0");
						break;
					default:
						break;
					}
				}
			}
			try {//itemId  itemName  itemLocation itemCommentCount itemGreatCommentCount 
				//itemGoodCommentCount  itemBadCommentCount
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtFilePath,true));
				for(int i = 0; i < itemMap.size(); i++) {
//					for(Map.Entry<String, String> mapEntry : itemMap.entrySet()) {
//						bufferedWriter.write(mapEntry.getValue().toString());
//						bufferedWriter.write(",");
//					}
//					bufferedWriter.newLine(); // 注意\n不一定在各种计算机上都能产生换行的效果
					bufferedWriter.write(itemMap.get("itemId").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemName").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemLocation").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemCommentCount").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemGreatCommentCount").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemGoodCommentCount").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(itemMap.get("itemBadCommentCount").toString());

					bufferedWriter.newLine(); // 注意\n不一定在各种计算机上都能产生换行的效果
				}
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return xmlFilePath + " is fail !!!";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return xmlFilePath + " is fail !!!";
		}
		
		return xmlFilePath + " is success !!!";
	}
}
