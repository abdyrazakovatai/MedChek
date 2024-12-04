package java15Sila.dao.daoImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.DoctorDao;
import java15Sila.db.Database;
import java15Sila.model.Department;
import java15Sila.model.Doctor;
import java15Sila.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findById(Long id) {
        for (Hospital hospital : database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
        }
        throw new NotFoundException("Doctor with Id " + id + "not found");
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getDepartments() == null){
                hospital.setDepartments(new ArrayList<>());
            }
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    if (hospital.getDoctors() == null){
                        hospital.setDoctors(new ArrayList<>());
                    }
                    for (Long doctorId : doctorsId) {
                        Doctor doctor = findById(doctorId);
                        if (doctor != null){
                            department.getDoctors().add(doctor);
                        }
                    }
                    return "Doctor assign success";
                }
            }
        }
        throw new NotFoundException("Depatment with id " + departmentId + "not found");
    }


    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                List<Doctor> doctors = hospital.getDoctors();
                if (doctors.isEmpty()) {
                    throw new NotFoundException("No doctors found in hospital with id " + id);
                }
                return doctors;
            }
        }
        throw new NotFoundException("Hospital with id " + id + "not found");
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) throws NotFoundException {
        for (Hospital hospital : database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    List<Doctor> doctors = department.getDoctors();
                    if (doctors.isEmpty()) {
                        throw new NotFoundException("No doctors found in department " + id);
                    }
                    return doctors;
                }
            }
        }
        throw new NotFoundException("Department with id " + id + "not found");
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                if (hospital.getDoctors() == null){
                    hospital.setDoctors(new ArrayList<>());
                }
                hospital.getDoctors().add(doctor);
                return "Doctor added success ";
            }
        }
        throw new NotFoundException("Hospital with id " + hospitalId + "not found");
    }

    @Override
    public void removeById(Long id) {
        boolean doctorRemove = false;
        for (Hospital hospital : database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    hospital.getDoctors().remove(doctor);
                    doctorRemove = true;
                    System.out.println("Doctor added success");
                    break;
                }
            }
        }
        if (!doctorRemove) {
            throw new NotFoundException("Doctor with " + id + "not found");
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : database.hospitals) {
            for (Doctor hospitalDoctor : hospital.getDoctors()) {
                if (hospitalDoctor.getId().equals(id)) {
                    hospitalDoctor.setFirstName(doctor.getFirstName());
                    hospitalDoctor.setLastName(doctor.getLastName());
                    hospitalDoctor.setGender(doctor.getGender());
                    hospitalDoctor.setExperienceYear(doctor.getExperienceYear());
                    return "Doctor updated success";
                }
            }
        }
        throw new NotFoundException("Doctor with id " + id + " not found");
    }
}

