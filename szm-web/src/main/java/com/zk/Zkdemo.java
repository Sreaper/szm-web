package com.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author songzhimao
 * @date 2018/1/17
 */
public class Zkdemo implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zkdemo());
        countDownLatch.await();

        // 创建父节点/test
        // 在父节点/test下面创建a1节点
        if (zooKeeper.exists("/jmq/test", true) == null) {
            zooKeeper.create("/jmq/test", "456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 同步获得结果
        List<String> childrenList = zooKeeper.getChildren("/jmq/test", true);
        System.out.println("同步getChildren获得数据结果：" + childrenList);
        for (String path : childrenList) {
            zooKeeper.getData("/jmq/test/"+path, true, null);
        }

        // 在父节点/test下面创建a2节点
        zooKeeper.create("/jmq/test/t1", "456".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Thread.sleep(100000);
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if(Event.EventType.None == event.getType() && null == event.getPath()){ // 连接时的监听事件
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged){ // 子节点变更时的监听
                try {
                    System.out.println("重新获得Children，并注册监听：" + zooKeeper.getChildren(event.getPath(),true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("重新获得data数据，并注册监听：" + zooKeeper.getData(event.getPath(),true,null));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("触发了一次事件");
        }
    }

    class MyChildren2Callback implements AsyncCallback.Children2Callback{

        public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
            System.out.println("异步获得getChildren结果，rc=" + rc
                    + "；path=" + path + "；ctx=" + ctx + "；children=" + children +"；stat=" + stat);
        }
    }
}
