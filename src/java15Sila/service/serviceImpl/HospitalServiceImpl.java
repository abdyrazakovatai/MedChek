package java15Sila.service.serviceImpl;

import java15Sila.MyException.NotFoundException;
import java15Sila.dao.HospitalDao;
import java15Sila.model.Hospital;
import java15Sila.model.Patient;
import java15Sila.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {

    private final HospitalDao hospitalDao;

    public HospitalServiceImpl(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        try {
            return hospitalDao.addHospital(hospital);
        } catch (NotFoundException e) {
            return "Erroe adding hospital" + e.getMessage();
        }
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            return hospitalDao.findHospitalById(id);
        } catch (NotFoundException e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Hospital> getAllHospital() {
        try {
            return hospitalDao.getAllHospital();
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            return hospitalDao.getAllPatientFromHospital(id);
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDao.deleteHospitalById(id);

    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            return hospitalDao.getAllHospitalByAddress(address);
        } catch (NotFoundException e) {
            System.out.println("Error" + e.getMessage());
            return Map.of();
        }
    }
}
