package com.meizu;/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Authors:
 *   wuhua <wq163@163.com> , boyan <killme2008@gmail.com>
 */

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.utils.ZkUtils;

import java.util.concurrent.Executor;


/**
 * <pre>
 * 异步消息消费者
 *
 * 先用原始的客户端,以后升级为结合配置中心的客户端会非常简便
 *
 * spring中使用可以把发送者和消费者统一放在一个bean里初始化好,然后外部调用这个bean的方法进行消息发送
 * </pre>
 */
public class Consumer {
    public static void main(final String[] args) throws Exception {
        //======================================
        //以下代码只需初始化一次就够了;
        //如果使用spring, 可配置bean的init方法中 初始化以下代码一次即可;
        //======================================

        //1.设置必要的信息并New session factory,强烈建议使用单例
        //一个工程里只需一个sessionFactory就够了,建议生产者和消费者共用一个sessionFactory
        final MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(initMetaConfig());

        //2.设置并创建消费者
        // subscribed topic,需要申请过才能用
        final String topic = "meta-test";
        // consumer group
        final String group = "meta-example";
        // create consumer,强烈建议使用单例
        ConsumerConfig consumerConfig = new ConsumerConfig(group);
        final MessageConsumer consumer = sessionFactory.createConsumer(consumerConfig);


        //3.subscribe topic
        //  这里可以调用多次以订阅不同的topic
        consumer.subscribe(topic, 1024 * 1024, new MessageListener() {

            public void recieveMessages(final Message message) {
                System.out.println("Receive message " + new String(message.getData()));
            }


            public Executor getExecutor() {
                // Thread pool to process messages,maybe null.
                return null;
            }
        });


        //4. complete subscribe
        // 所有topic订阅完毕后,调用此api后才真正开始接收消息
        consumer.completeSubscribe();

    }

    private static MetaClientConfig initMetaConfig() {
        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZkUtils.ZKConfig zkConfig = new ZkUtils.ZKConfig();
        //测试环境： 172.16.200.239:2181,172.16.200.233:2181,172.16.200.234:2181
        //生产环境： 192.168.16.131:2181,192.168.16.132:2181,192.168.16.133:2181
        //不同的环境连接不同的集群,请自行想办法修改
        zkConfig.zkConnect = "127.0.0.1:2181";
        metaClientConfig.setZkConfig(zkConfig);
        return metaClientConfig;
    }

}
