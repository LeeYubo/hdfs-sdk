import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author liyubo
 * @create 2020-08-05 2:59 下午
 **/
public class TestSDK {

    private static final String HDFS_PATH = "hdfs://192.168.0.106:8020";
    private static final String HDFS_USER = "root";
    private static FileSystem fileSystem;

    @Before
    public void init() {
        try {
            Configuration configuration = new Configuration();
            // 这里我启动的是单节点的 Hadoop,所以副本系数设置为 1,默认值为 3
            configuration.set("dfs.replication", "1");
            fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, HDFS_USER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {

    }
}
