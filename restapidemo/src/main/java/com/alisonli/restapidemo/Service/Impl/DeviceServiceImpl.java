package com.alisonli.restapidemo.Service.Impl;

import com.alisonli.restapidemo.Service.DeviceService;
import com.alisonli.restapidemo.entity.Device;
import com.alisonli.restapidemo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository repository;

    @Override
    public Device save(Device device) {
        return repository.save(device);
    }

    @Override
    public Device createNewDevice(String serialNumber, String machineCode, String deviceName) throws Exception {
        Device findDevice = repository.findBySerialNumber(serialNumber).orElse(null);
        if (findDevice != null){
            throw new Exception("Device already exists!");
        }
        Device device = new Device();
        device.setSerialNumber(serialNumber);
        device.setDeviceName(deviceName);
        device.setMachineCode(machineCode);

        return repository.save(device);
    }

    @Override
    public List<Device> findAllByMachineCode(String machineCode) {
        return repository.findAllByMachineCode(machineCode).orElse(null);
    }

    @Override
    public List<Device> findAllByDeviceName(String deviceName) {
        return repository.findAllByDeviceName(deviceName).orElse(null);
    }

    @Override
    public Device findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber).orElse(null);
    }

    @Override
    public void delete(Device device) {
        repository.delete(device);
    }
}
