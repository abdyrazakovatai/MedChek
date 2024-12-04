package java15Sila.dao;

import java15Sila.generic.GenericService;
import java15Sila.model.Patient;

import java.util.List;
import java.util.Map;

public interface PatientDao extends GenericService<Patient> {
    String addPatientsToHospital(Long id, List<Patient> patients);
    Patient getPatientById(Long id);
    Map<Integer, Patient> getPatientByAge();
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
