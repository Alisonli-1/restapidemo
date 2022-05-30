package com.alisonli.restapidemo.repository;

import com.alisonli.restapidemo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
 //CRUD - create, read, update, delete

    Optional<List<Device>> findAllByMachineCode(String machineCode);
    Optional<List<Device>> findAllByDeviceName(String deviceName);
    Optional<Device> findBySerialNumber(String serialNumber);


}
