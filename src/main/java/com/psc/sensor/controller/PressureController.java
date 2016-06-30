package com.psc.sensor.controller;

import com.psc.modbus.ModbusSlaveHandler;
import com.psc.sensor.Pressure;
import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rogertucker on 6/28/16.
 */

@RestController
public class PressureController {
    @Autowired
    private Environment env;
    @Autowired
    ModbusSlaveHandler modbusSlaveHandler;


    @RequestMapping(value = "/sensor/pressure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Pressure> add(@RequestBody Pressure input){
        BasicProcessImage processImage = modbusSlaveHandler.getProcessImage(input.getSlave());
        processImage.setNumeric(RegisterRange.HOLDING_REGISTER, modbusSlaveHandler.getPressureSensorRegister(),
                DataType.EIGHT_BYTE_FLOAT_SWAPPED,
                input.getPressure());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
