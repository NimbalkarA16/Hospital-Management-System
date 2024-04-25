package HospitalJDBC;

public class Hospital {
    private int patientId;
    private String patientName;
    private String disease;
    private String doctorName;
    private double fees;

    //Constructor
    public Hospital(String patientName, String disease, String doctorName, double fees) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.disease = disease;
        this.doctorName = doctorName;
        this.fees = fees;
    }

    // Getters and setters

    public int getPatientIid() {
        return patientId;
    }

    public void setPatientIid(int patientIid) {
        this.patientId = patientIid;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    // toString method

    @Override
    public String toString() {
        return "Hospital{" +
                "patientIid=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", disease='" + disease + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", fees=" + fees +
                '}';
    }
}