import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.sun.istack.internal.localization.NullLocalizable;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ReadXmlWriteTxtUIRT implements ReadXmlAndWriteTxt {
	
	@Override
	public String process(String xmlFilePath, String txtFilePath) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File(xmlFilePath));
			Element root = document.getRootElement();
			List allChildren = root.getChildren();
			Element element11 = (Element) allChildren.get(11);
			Pattern intPattern = Pattern.compile("[^0-9]");
			String itemId = ((Element) ((Element) element11.getContent().get(0)).getContent().get(1)).getContent()
					.get(0).toString();
			itemId = intPattern.matcher(itemId).replaceAll("").trim();

			List<Map> itemRatingMapList = new ArrayList<>();
//			Map<String, String> itemRatingMap = new HashMap<>();

			//合并物品表和用户评论表

			//获取物品名
			Element element12 = (Element) allChildren.get(12);
			Element element12Content = (Element) element12.getContent().get(0);
			List element12ContentList = element12Content.getContent();
			String itemName = ((Element) element12ContentList.get(0)).getContent().get(0).toString().split(":")[1].split("]")[0].trim();
			//itemMap.put("itemName", itemName);
			System.out.println("景点名："+itemName);

			//所处位置没想好怎么应用，格式没想好怎么代替汉字
			//该属性可能为空，采集数据时，错位到了“景点描述”标签，因此采集“景点描述”标签
			List itemlocationContent=((Element)element12ContentList.get(6)).getContent();
			String itemLocation ;
			if (itemlocationContent.size()>0) {
				itemLocation =itemlocationContent.get(0).toString().split(":")[1].split("]")[0];
				//去掉字符串中的中文逗号“,”
				itemLocation=itemLocation.replaceAll("，","").trim();
				itemLocation=itemLocation.replaceAll(",","").trim();
				itemLocation=itemLocation.replaceAll(" ","").trim();
				itemLocation=itemLocation.replaceAll("；","").trim();
				itemLocation=itemLocation.replaceAll(";","").trim();
				//itemMap.put("itemLocation", itemLocation);
			}else {
				itemLocation = ((Element) element12ContentList.get(1)).getContent().get(0).toString().split(":")[1].split("]")[0];
				//去掉字符串中的中文逗号“,”
				itemLocation=itemLocation.replaceAll("，","").trim();
				itemLocation=itemLocation.replaceAll(",","").trim();
				itemLocation=itemLocation.replaceAll(" ","").trim();
				itemLocation=itemLocation.replaceAll("；","").trim();
				itemLocation=itemLocation.replaceAll(";","").trim();
				//itemMap.put("itemLocation", itemLocation);
			}

			//提取景点评论数，总评，好评，中评，差评
			//String声明的时候必须赋值为空才可以操作？
			String itemCommentCount = null;
			String itemGreatCommentCount= null;
			String itemGoodCommentCount=null;
			String itemBadCommentCount=null;
			//System.out.println(itemCommentCount);
			for(int i = 7; i < 11; i++) {
				List list = ((Element)element12ContentList.get(i)).getContent();
				if(list.size() > 0) {//解决了好评、中评、差评为空的问题
					String value = intPattern.matcher(list.get(0).toString()).replaceAll("").trim();
					switch (i) {
						case 7:
							itemCommentCount=value;
							break;
						case 8:
							itemGreatCommentCount=value;
							break;
						case 9:
							itemGoodCommentCount=value;
							break;
						case 10:
							itemBadCommentCount=value;
							break;
						default:
							break;
					}
				}else {
					switch (i) {
						case 7:
							itemCommentCount="0";
							break;
						case 8:
							itemGreatCommentCount="0";
							break;
						case 9:
							itemGoodCommentCount="0";
							break;
						case 10:
							itemBadCommentCount="0";
							break;
						default:
							break;
					}
				}
			}
			//System.out.println("总评论数="+itemCommentCount);
			//System.out.println(itemGreatCommentCount);

			Element element13 = (Element) allChildren.get(13);// 定位到“评论列表”
			List element13Children = element13.getChildren();// 得到评论列表“item”的list



			for (int i = 0; i < element13Children.size(); i++) {
				//Map<String, String> itemRatingMap = new HashMap<>();

				List element13ChildrenList = ((Element) element13Children.get(i)).getContent();

				// 用户Id
				String userId = ((Element) element13ChildrenList.get(1)).getContent().toString();
				userId = intPattern.matcher(userId).replaceAll("").trim();
				// 评分
				String rating = ((Element) element13ChildrenList.get(2)).getContent().toString();
				rating = intPattern.matcher(rating).replaceAll("").trim();
				// 评论时间,保留yyyy-mm-dd
				String ratingTime = ((Element) element13ChildrenList.get(4)).getContent().toString();

				ratingTime = intPattern.matcher(ratingTime).replaceAll("").trim();
				ratingTime = ratingTime.substring(0,8);
				System.out.println("采集到的时间为："+ratingTime);
				//评论季节
				String seasonTime = ratingTime.substring(4, 6);//截取出月份
				System.out.println("截取出的时间为："+seasonTime);
//				switch (seasonTime) {
//				case "03":
//					seasonTime = "1";
//					break;
//				case "04":
//					seasonTime = "1";
//					break;
//				case "05":
//					seasonTime = "1";
//					break;
//				case "06":
//					seasonTime = "2";
//					break;
//				case "07":
//					seasonTime = "2";
//					break;
//				case "08":
//					seasonTime = "2";
//					break;
//				case "09":
//					seasonTime = "3";
//					break;
//				case "10":
//					seasonTime = "3";
//					break;
//				case "11":
//					seasonTime = "3";
//					break;
//				case "12":
//					seasonTime = "4";
//					break;
//				case "01":
//					seasonTime = "4";
//					break;
//				case "02":
//					seasonTime = "4";
//					break;
//				}
				System.out.println("转化成季节后的时间为："+seasonTime);
                Map<String, String> itemRatingMap = new HashMap<>();
				itemRatingMap.put("itemId", itemId);
				itemRatingMap.put("userId", userId);
				itemRatingMap.put("rating", rating);
				itemRatingMap.put("ratingTime", ratingTime);
				itemRatingMap.put("seasonTime", seasonTime);
				itemRatingMap.put("itemName", itemName);
				itemRatingMap.put("itemLocation", itemLocation);
				itemRatingMap.put("itemCommentCount", itemCommentCount);
				itemRatingMap.put("itemGreatCommentCount", itemGreatCommentCount);
				itemRatingMap.put("itemGoodCommentCount", itemGoodCommentCount);
				itemRatingMap.put("itemBadCommentCount", itemBadCommentCount);

				itemRatingMapList.add(itemRatingMap);
				System.out.println("加入一条评论");
			}

			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtFilePath, true));
				for (int i = 0; i < itemRatingMapList.size(); i++) {
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("userId").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemId").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("rating").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("ratingTime").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("seasonTime").toString());
					bufferedWriter.write(",");
//					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemCommentCount").toString());
//					bufferedWriter.write(",");
//					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemGreatCommentCount").toString());
//					bufferedWriter.write(",");
//					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemGoodCommentCount").toString());
//					bufferedWriter.write(",");
//					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemBadCommentCount").toString());
//					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemName").toString());
					bufferedWriter.write(",");
					bufferedWriter.write(((Map) itemRatingMapList.get(i)).get("itemLocation").toString());
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
