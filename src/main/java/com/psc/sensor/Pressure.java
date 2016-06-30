package com.psc.sensor;

import java.util.Date;

/**
 * Created by rogertucker on 6/28/16.
 */
public class Pressure {
    private Double pressure;
    private String slave;

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getSlave() {
        return slave;
    }

    public void setSlave(String slave) {
        this.slave = slave;
    }
}
