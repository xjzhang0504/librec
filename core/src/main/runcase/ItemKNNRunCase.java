
import java.util.ArrayList;
import java.util.List;

import net.librec.conf.Configuration;
import net.librec.conf.Configuration.Resource;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.ranking.AUCEvaluator;
import net.librec.eval.ranking.PrecisionEvaluator;
import net.librec.eval.ranking.RecallEvaluator;
import net.librec.eval.rating.MAEEvaluator;
import net.librec.eval.rating.MSEEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.CosineSimilarity;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

public class ItemKNNRunCase {
	
	public static void main(String[] args) throws Exception{
		// build data model
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "/Users/xj/Jworkspace/librec/data");//配置数据的读取路径
        Resource resource = new Resource("rec/cf/itemknn-test.properties");
        conf.addResource(resource);
       // System.out.println("实验data.input.path="+conf.get("data.input.path"));
        TextDataModel dataModel = new TextDataModel(conf);//进行数据的读取与分割
        dataModel.buildDataModel();
        
        //build recommendr context
        RecommenderContext context = new RecommenderContext(conf,dataModel);
        
        //build similarity
        //conf.set("rec.recommender.similarity.key", "item");
        RecommenderSimilarity similarity = new CosineSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);
        
        //build recommender
        Recommender recommender = new ItemKNNRecommender();
        recommender.setContext(context);
        
        //run recommender algorithm
        recommender.recommend(context);
        
        //evaluate the recommended result
      //  RecommenderEvaluator evaluator = new RMSEEvaluator();
//        System.out.println("RMSE:"+recommender.evaluate(evaluator));
//        evaluator = new MAEEvaluator();
//        System.out.println("MAE:"+recommender.evaluate(evaluator));
//        evaluator = new MSEEvaluator();
//        System.out.println("MSE:"+recommender.evaluate(evaluator));
        
        
//        RecommenderEvaluator   evaluator = new PrecisionEvaluator();
//        System.out.println("Precision  "+recommender.evaluate(evaluator));
//        evaluator = new AUCEvaluator();
//        System.out.println("AUC  "+recommender.evaluate(evaluator));
        RecommenderEvaluator  evaluator = new RecallEvaluator();
        System.out.println("Recall  "+recommender.evaluate(evaluator));
        evaluator = new PrecisionEvaluator();
        System.out.println("Precision  "+recommender.evaluate(evaluator));
        //set id list of filter
//        List<String> userIdList = new ArrayList<>();
//        List<String> itemIdList = new ArrayList<>();
//        userIdList.add("1");
//        itemIdList.add("70");
//        
//       // filter the recommended result
//        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
//        GenericRecommendedFilter filter = new GenericRecommendedFilter();
//        filter.setUserIdList(userIdList);
//        filter.setItemIdList(itemIdList);
//        recommendedItemList = filter.filter(recommendedItemList);
//        
//        // print filter result
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(
//                    "user:" + recommendedItem.getUserId() + " " +
//                    "item:" + recommendedItem.getItemId() + " " +
//                    "value:" + recommendedItem.getValue()
//            );
//	
//	}

}
}
