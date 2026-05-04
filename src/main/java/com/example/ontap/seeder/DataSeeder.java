package com.example.ontap.seeder;


import com.example.ontap.entity.Brand;
import com.example.ontap.entity.Device;
import com.example.ontap.repository.IBrandRepository;
import com.example.ontap.repository.IDeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final IBrandRepository brandRepository;
    private final IDeviceRepository deviceRepository;


    public DataSeeder(IBrandRepository brandRepository, IDeviceRepository deviceRepository) {
        this.brandRepository = brandRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0 && deviceRepository.count() == 0) {
            Brand d1 = new Brand();
            d1.setName("Apple");
            d1.setDescription("Apple xịn");


            Brand d2 = new Brand();
            d2.setName("Samsung");
            d2.setDescription("Samsung xịn");

            Brand d3 = new Brand();
            d3.setName("Sony");
            d3.setDescription("Sony xịn");

            brandRepository.saveAll(Arrays.asList(d1, d2,d3));

            Device e1 = new Device();
            e1.setBrand(d1);
            e1.setDeviceName("Iphone 17");
            e1.setPrice(17777777);
            e1.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e1.setModelCode("123");
            e1.setProductImage("#");
            e1.setIsAvailable(true);


            Device e2 = new Device();
            e2.setBrand(d1);
            e2.setDeviceName("iPhone 16");
            e2.setPrice(17777700);
            e2.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e2.setModelCode("123");
            e2.setProductImage("#");
            e2.setIsAvailable(true);

            Device e3 = new Device();
            e3.setBrand(d1);
            e3.setDeviceName("Iphone 15");
            e3.setPrice(17777777);
            e3.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e3.setModelCode("123");
            e3.setProductImage("#");
            e3.setIsAvailable(true);


            Device e4 = new Device();
            e4.setBrand(d1);
            e4.setDeviceName("iPhone 14");
            e4.setPrice(17777700);
            e4.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e4.setModelCode("123");
            e4.setProductImage("#");
            e4.setIsAvailable(true);

            Device e5 = new Device();
            e5.setBrand(d2);
            e5.setDeviceName("Samsung 17");
            e5.setPrice(17777777);
            e5.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e5.setModelCode("123");
            e5.setProductImage("#");
            e5.setIsAvailable(true);


            Device e6 = new Device();
            e6.setBrand(d2);
            e6.setDeviceName("Samsung 16");
            e6.setPrice(17777700);
            e6.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e6.setModelCode("123");
            e6.setProductImage("#");
            e6.setIsAvailable(true);

            Device e7 = new Device();
            e7.setBrand(d2);
            e7.setDeviceName("Samsung 15");
            e7.setPrice(17777777);
            e7.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e7.setModelCode("123");
            e7.setProductImage("#");
            e7.setIsAvailable(true);


            Device e8 = new Device();
            e8.setBrand(d2);
            e8.setDeviceName("Samsung 14");
            e8.setPrice(17777700);
            e8.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e8.setModelCode("123");
            e8.setProductImage("#");
            e8.setIsAvailable(true);

            Device e9 = new Device();
            e9.setBrand(d3);
            e9.setDeviceName("Sony 17");
            e9.setPrice(17777777);
            e9.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e9.setModelCode("123");
            e9.setProductImage("#");
            e9.setIsAvailable(true);


            Device e10 = new Device();
            e10.setBrand(d3);
            e10.setDeviceName("Sony 16");
            e10.setPrice(17777700);
            e10.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e10.setModelCode("123");
            e10.setProductImage("#");
            e10.setIsAvailable(true);

            Device e11 = new Device();
            e11.setBrand(d3);
            e11.setDeviceName("Sony 15");
            e11.setPrice(17777777);
            e11.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e11.setModelCode("123");
            e11.setProductImage("#");
            e11.setIsAvailable(true);


            Device e12 = new Device();
            e12.setBrand(d3);
            e12.setDeviceName("Sony 14");
            e12.setPrice(17777700);
            e12.setManufactureDate(LocalDate.ofEpochDay(2025-12-12));
            e12.setModelCode("123");
            e12.setProductImage("#");
            e12.setIsAvailable(true);

            deviceRepository.saveAll(Arrays.asList(e1, e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12));
        }
    }
}

