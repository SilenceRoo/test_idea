package com.wei.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerCallback {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hdp-1:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        /*Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hdp-1:9092");
        properties.put("ack", true);
        //重试次数
        properties.put("retries", 3);

        // 批次大小：16k
        properties.put("batch.size", 16384);
        //等待时间1ms
        properties.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
*/



        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("test_api", "我你妈"), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println(recordMetadata.topic() + recordMetadata.offset() +"--"+ recordMetadata.partition());
                    }else {
                        e.printStackTrace();
                    }
                }
            });
        }
        producer.close();
    }
}
