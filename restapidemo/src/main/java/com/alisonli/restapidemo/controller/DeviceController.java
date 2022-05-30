package com.alisonli.restapidemo.controller;

import com.alisonli.restapidemo.Service.DeviceService;
import com.alisonli.restapidemo.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/createDevice")
    public ResponseEntity<?> createDevice( @Valid @RequestBody Device device){
        Device d = null;
        try{
            d = deviceService.createNewDevice(device.getSerialNumber(),device.getMachineCode(), device.getDeviceName());
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @RequestMapping("/getDeviceBySerialNumber")
    public ResponseEntity<?> getDeviceBySerialNumber(@RequestParam(name="serialNumber") String serialNumber){
        Device findDevice = deviceService.findBySerialNumber(serialNumber);
        if (findDevice == null){
            return new ResponseEntity<>("{\"message\": there's no such device with this serial number.}", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(findDevice, HttpStatus.OK);
    }

    @RequestMapping("/getDevicesByMachineCode")
    public ResponseEntity<?> getDevicesByMachineCode(@RequestParam(name="machineCode") String machineCode){
        List<Device> findDevice = deviceService.findAllByMachineCode(machineCode);
        if (findDevice == null){
            return new ResponseEntity<>("{\"message\": there's no such device with this machine code.}", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(findDevice, HttpStatus.OK);
    }

    @RequestMapping("/getDevicesByDeviceName")
    public ResponseEntity<?> getDevicesByDeviceName(@RequestParam(name="deviceName") String deviceName){
        List<Device> findDevice = deviceService.findAllByDeviceName(deviceName);
        if (findDevice == null){
            return new ResponseEntity<>("{\"message\": there's no such device with this machine code.}", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(findDevice, HttpStatus.OK);
    }

    @RequestMapping("/updateDevice")
    public ResponseEntity<?> updateDevice(@RequestParam(name="serialNumber") String serialNumber,
                                          @RequestParam(name="machineCode", required = false) String machineCode,
                                          @RequestParam(name="deviceName", required = false) String deviceName){
        Device findDevice = deviceService.findBySerialNumber(serialNumber);
        if (findDevice == null){
            return new ResponseEntity<>("{\"message\": there's no such device with this machine code.}", HttpStatus.EXPECTATION_FAILED);
        }
        if (machineCode != null){
            findDevice.setMachineCode(machineCode);
        }
        if (deviceName != null){
            findDevice.setDeviceName(deviceName);
        }
        findDevice = deviceService.save(findDevice);
        return new ResponseEntity<>(findDevice, HttpStatus.OK);
    }

    @RequestMapping("/deleteDeviceBySerialNumber")
    public ResponseEntity<?> deleteDeviceBySerialNumber(@RequestParam(name="serialNumber") String serialNumber){
        Device findDevice = deviceService.findBySerialNumber(serialNumber);
        if (findDevice == null){
            return new ResponseEntity<>("{\"message\": there's no such device with this machine code.}", HttpStatus.EXPECTATION_FAILED);
        }
        deviceService.delete(findDevice);
        return new ResponseEntity<>("{\"message\": device deleted.}",HttpStatus.OK);
    }


}
