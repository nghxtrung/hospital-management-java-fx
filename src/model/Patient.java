package model;

public class Patient {
    private String code;
    private String fullName;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String address;
    private String job;
    private String bloodName;
    private String soCmnd;

    public Patient() {
    }

    public Patient(String code, String fullName, String birthday, String gender, String phoneNumber, String soCmnd ,String address , String job, String bloodName) {
        this.code = code;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.soCmnd = soCmnd;
        this.address = address;
        this.job = job;
        this.bloodName = bloodName;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBloodName() {
        return bloodName;
    }

    public void setBloodName(String bloodName) {
        this.bloodName = bloodName;
    }

    public String getSoCmnd() {
        return soCmnd;
    }

    public void setSoCmnd(String soCmnd) {
        this.soCmnd = soCmnd;
    }

    @Override
    public String toString() {


        return " " +
                "code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", job='" + job + '\'' +
                ", bloodName='" + bloodName + '\'' +
                ", soCmnd='" + soCmnd + '\'' +
                '\n';
    }
}
