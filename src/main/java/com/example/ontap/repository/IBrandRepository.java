package com.example.ontap.repository;

import com.example.ontap.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository  extends JpaRepository<Brand,Long> {
}
