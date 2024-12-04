package java15Sila.service.serviceImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.DoctorDao;
import java15Sila.db.Database;
import java15Sila.model.Doctor;
import java15Sila.service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;


    public DoctorServiceImpl(Database database, DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public Doctor findById(Long id) {
        try {
            return doctorDao.findById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        return null;
        }
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
           return doctorDao.assignDoctorToDepartment(departmentId,doctorsId);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        return "Assignment failed";
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
          return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        return List.of();
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
           return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        return List.of();
        }
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
       return doctorDao.add(hospitalId,doctor);
    }

    @Override
    public void removeById(Long id) {
        try {
            doctorDao.removeById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
        return doctorDao.updateById(id,doctor);
        }catch (NotFoundException e ){
            System.out.println(e.getMessage());
            return "Update ";
        }
    }
}
