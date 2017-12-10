import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ReadXmlWriteTxtUURFans {
	public static void main(String [] args) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File("/Users/xj/Desktop/datalist/fansList/imported/粉丝人列表_3868982_2342382609.xml"));
			Element root = document.getRootElement();
			List allChildren = root.getChildren();
			Pattern intPattern = Pattern.compile("[^0-9]");
			
			Element Children11 =  (Element)(((Element)(allChildren.get(11))).getChildren().get(0));
			Element Children11Content = (Element)Children11.getContent().get(1);
			String userId = Children11Content.getContent().toString();
			userId =intPattern.matcher(userId).replaceAll("").trim();
			List<Map> UURFansList = new ArrayList<>();
			List  Children12List =  ((Element)(allChildren.get(12))).getChildren();
			for(int i=0;i<Children12List.size();i++) {
				Map<String , String > UURFansMap =new HashMap<>();
				//得到粉丝人列表信息
				List fansInfo =((Element)(Children12List.get(i))).getChildren();
				//粉丝人Id FansId
				String fansId=((Element)(fansInfo.get(5))).getContent().toString();
				fansId = intPattern.matcher(fansId).replaceAll("").trim();
				//粉丝人足迹数  fansTrackCount
				String fansTrackCount = ((Element)(fansInfo.get(4))).getContent().toString();
				fansTrackCount=intPattern.matcher(fansTrackCount).replaceAll("").trim();
				//粉丝人性别 fansSex
				List  fansSex =((Element)fansInfo.get(1)).getContent();
				if (fansSex.size()>0) {
					UURFansMap.put("fansSex", "0");
				}else {
					UURFansMap.put("fansSex", "1");
				}
				UURFansMap.put("userId", userId);
				UURFansMap.put("fansId", fansId);
				UURFansMap.put("fansTrackCount", fansTrackCount);
				UURFansList.add(UURFansMap);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
