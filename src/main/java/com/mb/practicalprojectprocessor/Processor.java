package com.mb.practicalprojectprocessor;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Processor {

    public static Object limitNumbers(int number) throws MqttException, InterruptedException {
        if (number < 50) {
            return number;
        } return null;
    }
}
