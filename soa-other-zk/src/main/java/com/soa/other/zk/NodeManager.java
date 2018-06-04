package com.soa.other.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * Created by pengyunlong on 2018/6/4.
 */
public class NodeManager {
    public void createNode(){
        ZkClient zkClient = CreateSession.connectZK();
        User user = new User();
        user.setId(1);
        user.setName("agan1");
        /**
         * "/testUserNode":节点的地址user:数据的对象CreateMode。PERSISTENT：创建节点类型
         * */
        String path = zkClient.create("/aganNode", user, CreateMode.PERSISTENT);
        //输出创建节点的路径
        System.out.println("创建节点："+path);
    }
    public void updateNode(){
        ZkClient zkClient = CreateSession.connectZK();
        User user = new User();
        user.setId(2);
        user.setName("agan2");
        /**
         * /testUserNode 节点路径user传入的数据对象
         * */
        zkClient.writeData("/aganNode",user);
        System.out.println("修改aganNode节点成功");
    }
    public void deleteNode(){
        ZkClient zkClient = CreateSession.connectZK();
        //删除单独一个节点，返回true表示曾工
        boolean delete = zkClient.delete("/aganNode");
        //返回true表示删除成功，false表示删除失败
        System.out.println("删除aganNode节点是否成功："+delete);
    }

    public static void main(String[] args) {
        NodeManager nm = new NodeManager();
        nm.createNode();
        nm.updateNode();
        nm.deleteNode();
    }
}
