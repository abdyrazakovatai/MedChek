package java15Sila.dao;

import java15Sila.generic.GenericService;
import java15Sila.model.Department;
import java15Sila.model.Doctor;

import java.util.List;

public interface DepartmentDao extends GenericService<Department> {

    List<Department> getAllDepartmentByHospital(Long id);

    Department findDepartmentByName(String name);

    void getAllDoctorByDepartment();
}
