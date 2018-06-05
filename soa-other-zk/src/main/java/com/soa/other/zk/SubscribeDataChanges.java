package com.soa.other.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by pengyunlong on 2018/6/4.
 * 订阅节点的数据内容发生变化
 */
public class SubscribeDataChanges {
    public static class ZKDataListenter implements IZkDataListener{

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            System.out.println("订阅节点的数据内容的变化"+dataPath+":"+data.toString());
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println("订阅节点的数据内容被删除"+dataPath);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = CreateSession.connectZK();
        zkClient.subscribeDataChanges("/aganNode",new ZKDataListenter());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
