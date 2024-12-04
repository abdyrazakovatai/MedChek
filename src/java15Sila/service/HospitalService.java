package java15Sila.service;

import java15Sila.generic.GenericService;
import java15Sila.model.Hospital;
import java15Sila.model.Patient;

import java.util.List;
import java.util.Map;

public interface HospitalService {
    String addHospital(Hospital hospital);
    Hospital findHospitalById(Long id);
    List<Hospital> getAllHospital();
    List<Patient> getAllPatientFromHospital(Long id);
    String deleteHospitalById(Long id);
    Map<String, Hospital> getAllHospitalByAddress(String address);
}
