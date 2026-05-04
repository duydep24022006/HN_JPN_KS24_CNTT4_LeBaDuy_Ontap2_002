package com.example.ontap.controller;

import com.example.ontap.dto.DeviceDto;
import com.example.ontap.entity.Device;
import com.example.ontap.repository.IBrandRepository;
import com.example.ontap.repository.IDeviceRepository;
import com.example.ontap.service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private final IBrandRepository brandRepository;
    private final IDeviceRepository deviceRepository;
    private final FileStorageService fileStorageService;

    public DeviceController(IBrandRepository brandRepository,
                            IDeviceRepository deviceRepository,
                            FileStorageService fileStorageService) {
        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
        this.fileStorageService = fileStorageService;
    }

    // Danh sách thiết bị
    @GetMapping
    public String listDevices(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "deviceName") String sortField,
                              @RequestParam(defaultValue = "asc") String sortDir,
                              @RequestParam(defaultValue = "") String keyword,
                              Model model) {

        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Device> devicePage = keyword.isEmpty()
                ? deviceRepository.findAll(pageable)
                : deviceRepository.findByDeviceNameContainingIgnoreCase(keyword, pageable);

        model.addAttribute("devices", devicePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", devicePage.getTotalPages());
        model.addAttribute("totalItems", devicePage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("brand", brandRepository.findAll());

        return "device-list";
    }

    // Form tạo mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("device", new DeviceDto());
        model.addAttribute("brand", brandRepository.findAll());
        return "device-form";
    }

    // Lưu thiết bị mới
    @PostMapping("/save")
    public String saveDevice(@Valid @ModelAttribute("device") DeviceDto deviceDto,
                             BindingResult result,
                             @RequestParam("file") MultipartFile file,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brand", brandRepository.findAll());
            return "device-form";
        }

        Device dev = new Device();
        dev.setDeviceName(deviceDto.getDeviceName());
        dev.setPrice(deviceDto.getPrice());
        dev.setModelCode(deviceDto.getModelCode());
        dev.setManufactureDate(deviceDto.getManufactureDate());
        dev.setIsAvailable(deviceDto.getIsAvailable());
        dev.setBrand(brandRepository.findById(deviceDto.getBrandId()).orElse(null));

        if (!file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            dev.setProductImage(fileName);
        }

        deviceRepository.save(dev);
        return "redirect:/device";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Device dev = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid device Id:" + id));

        DeviceDto dto = new DeviceDto();
        dto.setId(dev.getId());
        dto.setDeviceName(dev.getDeviceName());
        dto.setModelCode(dev.getModelCode());
        dto.setPrice(dev.getPrice());
        dto.setManufactureDate(dev.getManufactureDate());
        dto.setAvailable(dev.getIsAvailable());
        dto.setBrandId(dev.getBrand().getId());

        model.addAttribute("device", dto);
        model.addAttribute("brand", brandRepository.findAll());
        return "device-form";
    }

    // Cập nhật thiết bị
    @PostMapping("/update/{id}")
    public String updateDevice(@PathVariable Long id,
                               @Valid @ModelAttribute("device") DeviceDto deviceDto,
                               BindingResult result,
                               @RequestParam("file") MultipartFile file,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brand", brandRepository.findAll());
            return "device-form";
        }

        Device dev = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid device Id:" + id));

        dev.setDeviceName(deviceDto.getDeviceName());
        dev.setPrice(deviceDto.getPrice());
        dev.setModelCode(deviceDto.getModelCode());
        dev.setManufactureDate(deviceDto.getManufactureDate());
        dev.setIsAvailable(deviceDto.getIsAvailable());
        dev.setBrand(brandRepository.findById(deviceDto.getBrandId()).orElse(null));

        if (!file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            dev.setProductImage(fileName);
        }

        deviceRepository.save(dev);
        return "redirect:/device";
    }

    // Xóa thiết bị
    @GetMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        Device dev = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid device Id:" + id));
        deviceRepository.delete(dev);
        return "redirect:/device";
    }
}
