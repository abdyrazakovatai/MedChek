package java15Sila.service;

import java15Sila.generic.GenericService;
import java15Sila.model.Doctor;

import java.util.List;

public interface DoctorService extends GenericService<Doctor> {
    Doctor findById(Long id);

    String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    List<Doctor> getAllDoctorsByDepartmentId(Long id);
}
