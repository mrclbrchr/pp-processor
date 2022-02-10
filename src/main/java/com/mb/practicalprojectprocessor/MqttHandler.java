package com.mb.practicalprojectprocessor;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MqttHandler implements MqttCallback {

    org.eclipse.paho.client.mqttv3.MqttClient client;
    String outputTopic        = "numbers-trunced";
    String inputTopic        = "numbers";
    int qos             = 0;
    String broker       = "tcp://localhost:1883";
    String clientId     = "pp-processor";
    MemoryPersistence persistence = new MemoryPersistence();

    public MqttHandler() throws MqttException, InterruptedException {
    }

    public void connect()  {
        try {
            client = new MqttClient("tcp://localhost:1883", "Sending");
            client.connect();
            client.setCallback((MqttCallback) this);
            client.subscribe(inputTopic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void send(Integer number) throws InterruptedException, MqttException {
            String content      = Integer.toString(number);
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(outputTopic, message);
            TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        String string = message.toString();
        int integer = Integer.parseInt(string);
        Object limitedNumber = Processor.limitNumbers(integer);
        if(limitedNumber!=null){
            send((Integer)limitedNumber);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
