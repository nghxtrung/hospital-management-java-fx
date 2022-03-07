package model;

public class Treatment {
    private String treatmentCode;
    private String patientCode;
    private String treatmentRoom;
    private String doctor;
    private String date;
    private String diagnostic;
    private String treatmentContent;

    public Treatment(String treatmentCode, String patientCode, String treatmentRoom, String doctor, String date, String diagnostic, String treatmentContent) {
        this.treatmentCode = treatmentCode;
        this.patientCode = patientCode;
        this.treatmentRoom = treatmentRoom;
        this.doctor = doctor;
        this.date = date;
        this.diagnostic = diagnostic;
        this.treatmentContent = treatmentContent;
    }

    public String getTreatmentCode() {
        return treatmentCode;
    }

    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getTreatmentRoom() {
        return treatmentRoom;
    }

    public void setTreatmentRoom(String treatmentRoom) {
        this.treatmentRoom = treatmentRoom;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTreatmentContent() {
        return treatmentContent;
    }

    public void setTreatmentContent(String treatmentContent) {
        this.treatmentContent = treatmentContent;
    }
}
