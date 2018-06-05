package com.soa.other.zk;

import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.Watcher;

/**
 * Created by pengyunlong on 2018/6/4.
 */
public class CreateSession {
    public static ZkClient connectZK(){
        //zk集群地址
        String ZKServer = "47.94.102.25:2181";
        ZkClient zkClient = new ZkClient(ZKServer, 10000, 10000, new SerializableSerializer());
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleNewSession() throws Exception {
                System.out.println("handlerNewSession()");
            }
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("handleStateChanged,stat:"+keeperState);
            }
        });
        System.out.println("connected ok!");
        return zkClient;
    }
}
