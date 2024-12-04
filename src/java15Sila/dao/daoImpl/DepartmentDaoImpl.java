package java15Sila.dao.daoImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.DepartmentDao;
import java15Sila.db.Database;
import java15Sila.model.Department;
import java15Sila.model.Doctor;
import java15Sila.model.Hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoImpl implements DepartmentDao {

    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                List<Department> departments = hospital.getDepartments();
                if (departments.isEmpty()) {
                    throw new NotFoundException("No department found id hospital " + id);
                }
                return departments;
            }
        }
        throw new NotFoundException("Hospital with id " + id + " not found");
    }

    @Override
    public Department findDepartmentByName(String name) {
        Department department = database.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department1 -> department1.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Department with name: " + name + " not found"));
        return department;
    }

    @Override
    public void getAllDoctorByDepartment() {
        Map<Department,Doctor> doctorMap = new HashMap<>();
        for (Hospital hospital : database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getDoctors() == null){
                    department.setDoctors(new ArrayList<>());
                }
                for (Doctor doctor : department.getDoctors()) {
                    System.out.println(doctorMap.put(department, doctor));
                }
            }
        }
    }


    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                if (hospital.getDepartments() == null){
                    hospital.setDepartments(new ArrayList<>());
                }
                hospital.getDepartments().add(department);
                return "Added success";
            }
        }
        throw new NotFoundException("Hospital with id " + hospitalId + " not found ");
    }

    @Override
    public void removeById(Long id) {
        boolean departmentRemove = false;
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                database.hospitals.remove(hospital);
                departmentRemove = true;
                System.out.println("Deleted success");
                break;
            }
        }
        if (!departmentRemove) {
            throw new NotFoundException("Department with " + id + " not found");
        }
    }


    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                for (Department hospitalDepartment : hospital.getDepartments()) {
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());
                    hospitalDepartment.setDoctors(department.getDoctors());
                    return "Updated success";
                }
            }
        }
        throw new NotFoundException("Department with id " + id + " not found");
    }
}
