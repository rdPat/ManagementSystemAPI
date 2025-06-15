package com.ram.Springboot.tutorial.service;

import com.ram.Springboot.tutorial.entity.Department;
import com.ram.Springboot.tutorial.error.DepartmentNotFoundException;
import com.ram.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl dept;

    @Mock
    private DepartmentRepository deptRepo;


    @BeforeEach
    void setUp(){
        Department department=Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Pune")
                .departmentCode("nnnbvc")
                .build();

       Mockito.when(deptRepo.findByDepartmentNameIgnoreCase("IT"))
               .thenReturn(department);

    }


    @Test
    @DisplayName("Checking get data for department")
    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
            String deptName="IT";
        Department found=dept.fetchDepartmentByName(deptName);

        assertEquals(deptName,found.getDepartmentName());
    }






}