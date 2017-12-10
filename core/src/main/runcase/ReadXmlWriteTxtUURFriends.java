import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ReadXmlWriteTxtUURFriends {
	public static void main(String [] args) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File("/Users/xj/Desktop/datalist/friendsList/imported/关注人列表_3867259_2341652427.xml"));
			Element root = document.getRootElement();
			List allChildren = root.getChildren();
			Pattern intPattern = Pattern.compile("[^0-9]");
			
			Element Children11 =  (Element)(((Element)(allChildren.get(11))).getChildren().get(0));
			Element Children11Content = (Element)Children11.getContent().get(1);
			String userId = Children11Content.getContent().toString();
			userId =intPattern.matcher(userId).replaceAll("").trim();
			List<Map> UURFriendList = new ArrayList<>();
			List  Children12List =  ((Element)(allChildren.get(12))).getChildren();
			for(int i=0;i<Children12List.size();i++) {
				Map<String , String > UURFriendMap =new HashMap<>();
				//得到关注人列表信息
				List friendInfo =((Element)(Children12List.get(i))).getChildren();
				//关注人Id friendId
				String friendId=((Element)(friendInfo.get(5))).getContent().toString();
				friendId = intPattern.matcher(friendId).replaceAll("").trim();
				//关注人足迹数  friendTrackCount
				String friendTrackCount = ((Element)(friendInfo.get(4))).getContent().toString();
				friendTrackCount=intPattern.matcher(friendTrackCount).replaceAll("").trim();
				//关注人性别 friendSex
				List  friendSex =((Element)friendInfo.get(1)).getContent();
				if (friendSex.size()>0) {
					UURFriendMap.put("friendSex", "1");
				}else {
					UURFriendMap.put("friendSex", "0");
				}
				UURFriendMap.put("userId", userId);
				UURFriendMap.put("friendId", friendId);
				UURFriendMap.put("friendTrackCount", friendTrackCount);
				UURFriendList.add(UURFriendMap);
			}
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/xj/Desktop/datalist/friendsList/friedds.txt", true));
				for(int i=0; i<UURFriendList.size();i++ ) {
				bufferedWriter.write(((Map) UURFriendList.get(i)).get("userId").toString());
				bufferedWriter.write(" ");
				bufferedWriter.write(((Map) UURFriendList.get(i)).get("friendId").toString());
				bufferedWriter.write(" ");
				bufferedWriter.write("1");
				bufferedWriter.newLine(); // 注意\n不一定在各种计算机上都能产生换行的效果
				}
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
