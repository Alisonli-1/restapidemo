package com.alisonli.restapidemo.Service;

import com.alisonli.restapidemo.entity.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DeviceService {

    Device save(Device device);

    Device createNewDevice(String serialNumber, String machineCode, String deviceName) throws Exception;

    List<Device> findAllByMachineCode(String machineCode);
    List<Device> findAllByDeviceName(String deviceName);
    Device findBySerialNumber(String serialNumber);

    void delete(Device device);
}
