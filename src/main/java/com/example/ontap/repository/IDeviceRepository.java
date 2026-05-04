package com.example.ontap.repository;

import com.example.ontap.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface IDeviceRepository extends JpaRepository<Device, Long> {
    Page<Device> findByDeviceNameContainingIgnoreCase(String deviceName, Pageable pageable);
//    List<Device> findByDepartment_Id(Long departmentId);

}
