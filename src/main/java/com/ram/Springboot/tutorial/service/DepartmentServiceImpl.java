package com.ram.Springboot.tutorial.service;

import com.ram.Springboot.tutorial.entity.Department;
import com.ram.Springboot.tutorial.error.DepartmentNotFoundException;
import com.ram.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentByID(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> dept= departmentRepository.findById(departmentId);
        if(!dept.isPresent())
        {
            throw new DepartmentNotFoundException("Department not available");
        }
        return dept.get();

    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
         Department deptDb=departmentRepository.findById(departmentId).get();

         //not null and blank checker
         if(Objects.nonNull(department.getDepartmentName()) &&
                 !"".equalsIgnoreCase(department.getDepartmentName())
         )
         {
             deptDb.setDepartmentName(department.getDepartmentName());
         }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())
        )
        {
            deptDb.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())
        )
        {
            deptDb.setDepartmentCode(department.getDepartmentCode());
        }



        return departmentRepository.save(deptDb);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
