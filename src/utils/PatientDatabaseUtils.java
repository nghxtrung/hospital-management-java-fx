package utils;

import javafx.collections.ObservableList;
import model.Patient;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientDatabaseUtils {
    private static List<Patient> PATIENTS = new ArrayList<>();

    private static LocalDate DATE = LocalDate.now();

    static {
        readPatients();
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

    // Tất cả Patient có tuổi lớn hơn tuổi truyền vào
    public  static List<Patient> isAgeMoreThan(Integer age ){
        List<Patient> patientList = PATIENTS.stream().filter(patient -> (DATE.getYear() - Integer.parseInt(patient.getBirthday().split("-")[2]) )>= age )
                .collect(Collectors.toList());
        return  patientList;
    }

    // Tất cả Patient có tuổi nằm trong khoảng tuổi truyền vào
    public static List<Patient>  filterByAge(Integer age1 , Integer age2 ){
        List<Patient> patientList = PATIENTS.stream().filter(patient -> (DATE.getYear() - Integer.parseInt(patient.getBirthday().split("-")[2]) )>= age1 &&  (DATE.getYear() - Integer.parseInt(patient.getBirthday().split("-")[2]) ) <=age2 )
                .collect(Collectors.toList());
        return  patientList;

    }

    public static  Patient findByCode(String code){

        try {
            Optional<Patient> patientByCode =  PATIENTS.stream().filter(patient -> patient.getCode().equals(code)).findFirst();
            return  patientByCode.get();
        }catch (NoSuchElementException ex){
            return  null;
        }


    }

    public static void readPatients() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("file/patient.txt")));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                PATIENTS.add(new Patient(data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void write(String filename, ObservableList<Patient> listPatients) throws IOException {
        FileWriter write = new FileWriter(filename);
        int index = 0;
        for (Patient patient : listPatients) {
            write.write(index +","+ patient.getCode()+","+ patient.getFullName()+","+ patient.getBirthday()
                    +","+ patient.getGender()+","+ patient.getPhoneNumber()+","+ patient.getSoCmnd()+","+ patient.getAddress()
                    +","+ patient.getJob()+","+ patient.getBloodName()+"\n");
            index++;
        }
        write.close();

    }

}
