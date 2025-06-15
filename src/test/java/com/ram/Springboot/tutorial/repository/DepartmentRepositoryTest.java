package com.ram.Springboot.tutorial.repository;

import com.ram.Springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department= Department.builder()
                .departmentName("IT")
                .departmentAddress("Pune")
                .departmentCode("IT-1")
                .build();

        entityManager.persist(department);

    }

    @Test
    public void whenfindById_thenReturnDepartment()
    {
        Department dept=deptRepo.findById(1L).get();
        assertEquals(dept.getDepartmentName(),"IT");
    }




}