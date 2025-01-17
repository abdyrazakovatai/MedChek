package java15Sila.dao;

import java15Sila.MyException.NotFoundException;
import java15Sila.generic.GenericService;
import java15Sila.model.Doctor;

import java.util.List;

public interface DoctorDao extends GenericService<Doctor> {
    // Department‘ти id менен табып, анан Doctor‘лорду листтеги айдилери менен
    // табып анан ошол табылган Department'ге табылган Doctor'лорду кошуп коюнунуз

    Doctor findById(Long id) throws NotFoundException;

    String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    List<Doctor> getAllDoctorsByDepartmentId(Long id) throws NotFoundException;
}
