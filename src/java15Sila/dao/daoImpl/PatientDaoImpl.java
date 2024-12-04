package java15Sila.dao.daoImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.PatientDao;
import java15Sila.db.Database;
import java15Sila.model.Hospital;
import java15Sila.model.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao {

    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                if (hospital.getPatients() == null){
                    hospital.setPatients(new ArrayList<>());
                }
                for (Patient patient : patients) {
                    hospital.getPatients().add(patient);
                }
                return "Patient success added ";
            }
        }
        throw new NotFoundException("Hospital with id " + id + "not found");
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    return patient;
                }
            }
        }
        throw new NotFoundException("Patient with " + id + "not found");
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer, Patient> patientMap = new HashMap<>();
        for (Hospital hospital : database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                patientMap.put(patient.getAge(), patient);
            }
        }
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        if (ascOrDesc.equals("asc")) {
            for (Hospital hospital : database.hospitals) {
                Collections.sort(hospital.getPatients(), Comparator.comparing(Patient::getAge));
                return hospital.getPatients();
            }
        } else if (ascOrDesc.equals("desc")) {
            for (Hospital hospital : database.hospitals) {
                Collections.sort(hospital.getPatients(), Comparator.comparing(Patient::getAge).reversed());
                return hospital.getPatients();
            }
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                if (hospital.getPatients() == null){
                    hospital.setPatients(new ArrayList<>());
                }
                hospital.getPatients().add(patient);
                return " Patient added success";
            }
        }
        throw new NotFoundException("Hospital with id " + hospitalId + "not found");
    }

    @Override
    public void removeById(Long id) {
        boolean patientRemove = false;
        for (Hospital hospital : database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    hospital.getPatients().remove(patient);
                    patientRemove = true;
                    System.out.println("Patient deleted success");
                    break;
                }
            }
        }
        if (!patientRemove) {
            throw new NotFoundException("Doctor with id " + id + " not found");
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : database.hospitals) {
            for (Patient hospitalPatient : hospital.getPatients()) {
                if (hospitalPatient.getId().equals(id)) {
                    hospitalPatient.setFirstName(patient.getFirstName());
                    hospitalPatient.setLastName(patient.getLastName());
                    hospitalPatient.setAge(patient.getAge());
                    hospitalPatient.setGender(patient.getGender());
                    return "Patient updated success";
                }
            }
        }
        throw new NotFoundException("Patient with id " + id + "not found ");
    }
}
