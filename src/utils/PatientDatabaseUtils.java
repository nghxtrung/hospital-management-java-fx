package utils;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseUtils {
    private static List<Patient> PATIENTS = new ArrayList<>();

    static {
        initData();
    }

    private static void initData() {
        PATIENTS.add(new Patient("BN0001", "Nguyễn Văn A", "01/01/2001", false,
                "0123456789", "Hà Nội", "Lập trình viên", "A"));
        PATIENTS.add(new Patient("BN0002", "Nguyễn Văn B", "01/01/2001", false,
                "0123456789", "Hà Nội", "Lập trình viên", "A"));
        PATIENTS.add(new Patient("BN0003", "Nguyễn Văn C", "01/01/2001", false,
                "0123456789", "Hà Nội", "Lập trình viên", "A"));
        PATIENTS.add(new Patient("BN0004", "Nguyễn Văn D", "01/01/2001", false,
                "0123456789", "Hà Nội", "Lập trình viên", "A"));
    }

    public static List<Patient> getPatients() {
        return PATIENTS;
    }

    public static void addPatient(Patient patient) {
        PATIENTS.add(patient);
    }

    public static void changePatient(int indexPatient, Patient patient) {
        PATIENTS.set(indexPatient, patient);
    }

    public static void removePatient(Patient patient) {
        PATIENTS.remove(patient);
    }
}
