package com.example.rest_api_assignment.services;

import com.example.rest_api_assignment.dtos.response.EmployeeResDto;
import com.example.rest_api_assignment.entity.EmployeeInfo;
import com.example.rest_api_assignment.exception.ResourceNotFoundException;
import com.example.rest_api_assignment.repository.EmployeeInfoRepo;
import org.springframework.cache.annotation.Cacheable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private final EmployeeInfoRepo employeeInfoRepo;
    private final ModelMapper modelMapper;
    private final String Upload_DIR = "C:\\Users\\ranaakri\\OneDrive - Roima Intelligence Inc\\Desktop\\Krish\\Day11\\rest-api-assignment\\src\\main\\resources\\static\\uploads";

    @Autowired
    public ImageService(EmployeeInfoRepo employeeInfoRepo, ModelMapper modelMapper)
    {
        this.employeeInfoRepo = employeeInfoRepo;
        this.modelMapper = modelMapper;
    }

    public EmployeeResDto upload(long id, MultipartFile file) throws IOException {

        if(file == null || file.isEmpty()){
            throw new RuntimeException("File is empty");
        }
        Path uploadPath = Paths.get(Upload_DIR);
        Files.createDirectories(uploadPath);

        String fname = id + "_" + file.getOriginalFilename();
        Path path = uploadPath.resolve(fname);


        Files.write(path, file.getBytes());

        EmployeeInfo emp = employeeInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        emp.setProfile_img_path(fname);

        employeeInfoRepo.save(emp);

        return modelMapper.map(emp, EmployeeResDto.class);
    }

    @Cacheable(value = "image", key = "#id")
    public byte[] getProfile(long id) throws IOException{
        EmployeeInfo emp = employeeInfoRepo.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Not Found"));
        return Files.readAllBytes(Paths.get(Upload_DIR , emp.getProfile_img_path()));
    }
}
