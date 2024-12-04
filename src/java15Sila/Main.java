package java15Sila;

import java15Sila.dao.DepartmentDao;
import java15Sila.dao.DoctorDao;
import java15Sila.dao.HospitalDao;
import java15Sila.dao.PatientDao;
import java15Sila.dao.daoImpl.DepartmentDaoImpl;
import java15Sila.dao.daoImpl.DoctorDaoImpl;
import java15Sila.dao.daoImpl.HospitalDaoImpl;
import java15Sila.dao.daoImpl.PatientDaoImpl;
import java15Sila.db.Database;
import java15Sila.model.Department;
import java15Sila.model.Doctor;
import java15Sila.model.Enum.Gender;
import java15Sila.model.Hospital;
import java15Sila.model.Patient;
import java15Sila.service.DepartmentService;
import java15Sila.service.DoctorService;
import java15Sila.service.HospitalService;
import java15Sila.service.PatientService;
import java15Sila.service.serviceImpl.DepartmentServiceImpl;
import java15Sila.service.serviceImpl.DoctorServiceImpl;
import java15Sila.service.serviceImpl.HospitalServiceImpl;
import java15Sila.service.serviceImpl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        HospitalDao hospitalDao = new HospitalDaoImpl(database);
        DoctorDao doctorDao = new DoctorDaoImpl(database);
        DepartmentDao departmentDao = new DepartmentDaoImpl(database);
        PatientDao patientDao = new PatientDaoImpl(database);
        Scanner scanner = new Scanner(System.in);

        HospitalService hospitalService = new HospitalServiceImpl(hospitalDao);
        DoctorService doctorService = new DoctorServiceImpl(database, doctorDao);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDao);
        PatientService patientService = new PatientServiceImpl(patientDao);

        List<Patient> patients = new ArrayList<>(List.of(new Patient(1L, "Динара ", " Назарова", 54, Gender.FEMALE),
                new Patient(2L, "Белек ", " Муратов", 54, Gender.MALE),
                new Patient(3L, "Арген ", " Эрмеков", 46, Gender.MALE),
                new Patient(4L, "Сыймык ", " Жоломанов", 36, Gender.MALE)));

        List<Doctor> doctors = new ArrayList<>(List.of(new Doctor(16L, "Динара ", " Назарова", Gender.FEMALE, 12),
                new Doctor(17L,"Бек","Маржанов",Gender.MALE,5),
                new Doctor(18L,"Айхан","Турдубекова",Gender.MALE,7)));

        while (true) {
            System.out.println("""

                    Commands:

                    1 -> Hospital add
                    2 -> Find Hospital By ID
                    3 -> Get All Hospital
                    4 -> Delete Hospital By ID
                    5 -> Get All Patients By Hospital
                    6 -> Add Doctor
                    7 -> Find Doctor By ID
                    8 -> Get All Doctors By Hospital ID
                    9 -> Add Department
                    10 -> Get All Department By Hospital
                    11 -> Assign Doctor To Department
                    12 -> Update Doctor
                    13 -> Update Department
                    14 -> Remove Doctor
                    15 -> Remove Department
                    16 -> Sort Patients
                    17 -> Get All Hospital By Address
                    18 -> Add Patients By Hospital ID
                    """);

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println(hospitalService.addHospital(new Hospital(1L, "Городская Болница", "#4")));
                    System.out.println(hospitalService.addHospital(new Hospital(2L, "Городская Болница", "#6")));
                    System.out.println(hospitalService.addHospital(new Hospital(3L, "Мамакеев", "Гагарина")));
                }
                case 2 -> {
                    System.out.println(hospitalService.findHospitalById(1L));
                }
                case 3 -> {
                    hospitalService.getAllHospital().forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println(hospitalService.deleteHospitalById(2L));
                }
                case 5 -> {
                    System.out.println(hospitalService.getAllPatientFromHospital(1L));
                }
                case 6 -> {
                    System.out.println(doctorService.add(1L, new Doctor(1L, "Айбек", "Маткеримов", Gender.MALE, 7)));
                    System.out.println(doctorService.add(1L, new Doctor(2L, "Айзада", "Керимова", Gender.FEMALE, 8)));
                    System.out.println(doctorService.add(1L, new Doctor(3L, "Бакыт", "Исмаилов", Gender.MALE, 10)));
                    System.out.println(doctorService.add(1L, new Doctor(4L, "Жаркын", "Бекмуратов", Gender.MALE, 12)));
                    System.out.println(doctorService.add(1L, new Doctor(5L, "Асел", "Токтосунова", Gender.FEMALE, 9)));
                    System.out.println(doctorService.add(2L, new Doctor(6L, "Чынгыз", "Абылов", Gender.MALE, 11)));
                    System.out.println(doctorService.add(2L, new Doctor(7L, "Нурия", "Сатыбалдиева", Gender.FEMALE, 13)));
                    System.out.println(doctorService.add(2L, new Doctor(8L, "Эркин", "Усубалиев", Gender.MALE, 6)));
                    System.out.println(doctorService.add(2L, new Doctor(9L, "Жанара", "Асанова", Gender.FEMALE, 14)));
                    System.out.println(doctorService.add(2L, new Doctor(10L, "Улан", "Байышев", Gender.MALE, 8)));
                    System.out.println(doctorService.add(3L, new Doctor(11L, "Гулназ", "Абдиева", Gender.FEMALE, 7)));
                    System.out.println(doctorService.add(3L, new Doctor(12L, "Эльдар", "Мусабеков", Gender.MALE, 9)));
                    System.out.println(doctorService.add(3L, new Doctor(13L, "Дамира", "Токтоналиева", Gender.FEMALE, 15)));
                    System.out.println(doctorService.add(3L, new Doctor(14L, "Кубан", "Курманалиев", Gender.MALE, 10)));
                    System.out.println(doctorService.add(3L, new Doctor(15L, "Мээрим", "Шаршеева", Gender.FEMALE, 12)));
                }
                case 7 -> {
                    System.out.println(doctorService.findById(1L));
                }
                case 8 -> {
                    System.out.println(doctorService.getAllDoctorsByHospitalId(1L));
                }
                case 9 -> {
                    System.out.println(departmentService.add(1L, new Department(1L, "Кардиология")));
                    System.out.println(departmentService.add(1L, new Department(2L, "Неврология")));
                    System.out.println(departmentService.add(2L, new Department(3L, "Онкология")));
                    System.out.println(departmentService.add(3L, new Department(4L, "Педиатрия")));
                    System.out.println(departmentService.add(2L, new Department(5L, "Хирургия")));
                }
                case 10 -> {
                    System.out.println(departmentService.getAllDepartmentByHospital(1L));
                }
                case 11 -> {
                    System.out.println(doctorService.assignDoctorToDepartment(1L,List.of(11L,17L)));
                }
                case 12 -> {
                    System.out.println(doctorService.updateById(1L, new Doctor(1L, "Азат", "Эшенов", Gender.MALE, 11)));
                }
                case 13 -> {
                    System.out.println(departmentService.updateById(2L, new Department(2L, "Диогностический отделение", List.of(new Doctor(3L, "Элнура ", " Атаканова", Gender.FEMALE, 9), new Doctor(6L, "Таалай ", "Ынтымаков", Gender.FEMALE, 7)))));
                }
                case 14 -> {
                    doctorService.removeById(2L);
                }
                case 15 -> {
                    departmentService.removeById(1L);
                }
                case 16 -> {
                    System.out.println(patientService.sortPatientsByAge("asc"));
                }
                case 17 -> {
                    System.out.println(hospitalService.getAllHospitalByAddress("Гагарина"));
                }
                case 18 -> {
                    System.out.println(patientService.addPatientsToHospital(1L, patients));
                }
                case 19 -> {
                    departmentDao.getAllDoctorByDepartment();
                }

                default -> System.err.println("Invalid command");
            }
        }
    }
}