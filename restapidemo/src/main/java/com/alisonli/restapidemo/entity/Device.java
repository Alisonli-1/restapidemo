package com.alisonli.restapidemo.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "devices")
@EntityListeners(AuditingEntityListener.class)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    
    @Pattern(regexp = "^[0-9]+\\-.[0-9]+$", message = "serial number is not in format. It has to be a combination of 2 positive integers, connected with -.")
    @Column(name="serial_number", unique = true, nullable = false)
    String serialNumber;

    @Column(name="machine_code", nullable = false)
    String machineCode;

    @Column(name="device_name", nullable = false)
    String deviceName;


    public Device() {}

    public Device(String serialNumber, String machineCode, String deviceName) {
        this.serialNumber = serialNumber;
        this.machineCode = machineCode;
        this.deviceName = deviceName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
