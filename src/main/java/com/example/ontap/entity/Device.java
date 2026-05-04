package com.example.ontap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "device")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Không đc bỏ trống")
    @Size(min = 5,max = 150,message = "số lượng từ lớn hơn 3 và nhỏ hơn 100")
    @Column(name = "device_name")
    private String deviceName;

    @NotBlank(message = "Không đc bỏ trống")
    @Column(name = "model_code")
    private String modelCode;

    @Column(name = "price")
    private double price;

    @Past(message = "ngày phải ở quá khứ")
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    @Column(name = "product_image")
    private String productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "is_available")
    private Boolean isAvailable;


}
