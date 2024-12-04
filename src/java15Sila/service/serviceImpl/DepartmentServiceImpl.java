package java15Sila.service.serviceImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.DepartmentDao;
import java15Sila.dao.DoctorDao;
import java15Sila.generic.GenericService;
import java15Sila.model.Department;
import java15Sila.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId,department);
    }

    @Override
    public void removeById(Long id) {
        try {
            departmentDao.removeById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        try {
        return departmentDao.updateById(id,department);
        }catch (NotFoundException e ){
            System.out.println(e.getMessage());
            return "Update success";
        }
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
        return departmentDao.getAllDepartmentByHospital(id);
        }catch (NotFoundException e ){
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
        return departmentDao.findDepartmentByName(name);
        }catch (NotFoundException e ){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
