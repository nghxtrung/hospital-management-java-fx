package utils;

import model.Treatment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TreatmentDatabaseUtils {
    private static List<Treatment> TREATMENT = new ArrayList<>();

    static {
        readData();
    }

    private static void readData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("file/treatment.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] treatmentInfomation = line.split(Pattern.quote("|"));
                TREATMENT.add(new Treatment(treatmentInfomation[0], treatmentInfomation[1],
                        treatmentInfomation[2], treatmentInfomation[3],
                        treatmentInfomation[4], treatmentInfomation[5], treatmentInfomation[6]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData() {
        try {
            FileWriter fileWriter = new FileWriter("file/treatment.txt");
            for (Treatment treatment : TREATMENT) {
                fileWriter.write(treatment.getTreatmentCode() + "|" + treatment.getPatientCode() + "|" +
                                    treatment.getTreatmentRoom() + "|" + treatment.getDoctor() + "|" +
                                    treatment.getDate() + "|" + treatment.getDiagnostic() + "|" +
                                    treatment.getTreatmentContent() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Treatment> getTreatment() {
        return TREATMENT;
    }
}
