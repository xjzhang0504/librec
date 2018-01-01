
import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.conf.Configuration.Resource;
import net.librec.job.RecommenderJob;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        System.out.println("hello librec");
        Configuration configuration = new Configuration();
        Resource resource = new Resource("rec/cf/rating/fmsgd-test.properties");
        configuration.set("dfs.data.dir", "/Users/xj/Jworkspace/librec/data");
        configuration.addResource(resource);
        RecommenderJob recommenderJob = new RecommenderJob(configuration);
        try {
            recommenderJob.runJob();
        } catch (LibrecException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
