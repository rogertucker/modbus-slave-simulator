package com.psc.modbus;

import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.ProcessImage;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.tcp.TcpSlave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rogertucker on 6/28/16.
 */
@Component
public class ModbusSlaveHandler {
    @Autowired
    private Environment env;
    private Map<String, BasicProcessImage> processImageMap = new HashMap<>();
    final ModbusSlaveSet listener = new TcpSlave(4080, false);


    public Map<String, BasicProcessImage> getProcessImageMap() {
        return processImageMap;
    }

    public void setProcessImageMap(Map<String, BasicProcessImage> processImage) {
        this.processImageMap = processImage;
    }

    public void addProcessImage(String name){
        BasicProcessImage pi = new BasicProcessImage(Integer.parseInt(env.getProperty(name)));
        listener.addProcessImage(pi);
        processImageMap.put(name, pi);
    }

    public void run() {
        try {
            listener.start();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
    }

    public BasicProcessImage getProcessImage(String name) {
        return processImageMap.get(name);
    }

    public int getPressureSensorRegister() {
        return Integer.parseInt(env.getProperty("pressure.sensor.register"));
    }
}
