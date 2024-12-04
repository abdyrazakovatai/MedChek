package java15Sila.service;

import java15Sila.generic.GenericService;
import java15Sila.model.Department;

import java.util.List;

public interface DepartmentService extends GenericService<Department> {
    List<Department> getAllDepartmentByHospital(Long id);

    Department findDepartmentByName(String name);
}
