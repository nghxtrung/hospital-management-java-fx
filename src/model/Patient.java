package model;

public class Patient {
    private String code;
    private String fullName;
    private String birthday;
    private boolean gender;
    private String phoneNumber;
    private String address;
    private String job;
    private String bloodName;

    public Patient(String code, String fullName, String birthday, boolean gender, String phoneNumber, String address, String job, String bloodName) {
        this.code = code;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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
}
