package java15Sila.service.serviceImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.PatientDao;
import java15Sila.model.Patient;
import java15Sila.service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
          return patientDao.addPatientsToHospital(id,patients);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return "Patients not added";
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
           return patientDao.getPatientById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        try {
            return patientDao.getPatientByAge();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
            return patientDao.add(hospitalId, patient);
    }

    @Override
    public void removeById(Long id) {
        try {
            patientDao.removeById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
        return patientDao.updateById(id,patient);
        }catch (NotFoundException e ){
            System.out.println(e.getMessage());
            return "Update";
        }
    }
}
