package com.ram.Springboot.tutorial.controller;

import com.ram.Springboot.tutorial.entity.Department;
import com.ram.Springboot.tutorial.error.DepartmentNotFoundException;
import com.ram.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;


    private Department department;

    @BeforeEach
    void setUp() {

         department= Department.builder()
                .departmentName("IT")
                .departmentAddress("Pune")
                .departmentCode("IT-1")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception{
        Department inputDepartment= Department.builder()
                .departmentName("IT")
                .departmentAddress("Pune")
                .departmentCode("IT-1")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                            .content("{\"departmentName\":\"IT\"," +
                                    "\"departmentAddress\":\"PUNE\"," +
                                    "\"departmentCode\":\"IT-1\"}"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void fetchDepartmentByID() throws Exception {
        Mockito.when(departmentService.fetchDepartmentByID(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}