package java15Sila.dao.daoImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.HospitalDao;
import java15Sila.db.Database;
import java15Sila.model.Hospital;
import java15Sila.model.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {

    private final Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        database.hospitals.add(hospital);
        return "Hospital Added success";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital;
            }
        }
        throw new NotFoundException("Hospital with id " + id + "not found");
    }

    @Override
    public List<Hospital> getAllHospital() {
        if (database.hospitals.isEmpty()){
            throw new NotFoundException("No hospitals found");
        }
        return database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospital = findHospitalById(id);
        List<Patient> patients = hospital.getPatients();
        if (patients.isEmpty()){
            throw new NotFoundException("Patients not found in hospital with id " + id);
        }
        return patients;
    }

    @Override
    public String deleteHospitalById(Long id) {
       boolean deleted = database.hospitals.removeIf(hospital -> hospital.getId().equals(id));
       if (!deleted){
           throw new NotFoundException("Hospital with id " + id + "not found");
       }
        return "Hospital deleted success";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalMap = new HashMap<>();
        for (Hospital hospital : database.hospitals) {
            if (hospital.getAddress().equals(address)) {
                hospitalMap.put(address, hospital);
            }
        }
        if (hospitalMap.isEmpty()){
            throw new NotFoundException("Hospitals not found with address " + address);
        }
        return hospitalMap;
    }
}
