package HospitalJDBC;

import java.sql.*;

public class HospitalControllerJDBC {
    private Connection connection;

    public HospitalControllerJDBC(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS hospital (" +
                "patient_id SERIAL PRIMARY KEY," +
                "patient_name VARCHAR(255) NOT NULL," +
                "disease VARCHAR(255) NOT NULL," +
                "doctor_name VARCHAR(255) NOT NULL," +
                "fees DOUBLE PRECISION NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Stepping towards the first Java Project");
            System.out.println("Welcome to Hospital Management System\n      Empowering Healthcare through Technology ! ! !    -By Ankita");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create patient
    public void createPatient(String patientName, String disease, String doctorName, double fees) throws SQLException {
        String query = "INSERT INTO hospital(patient_name, disease, doctor_name, fees) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patientName);
            preparedStatement.setString(2, disease);
            preparedStatement.setString(3, doctorName);
            preparedStatement.setDouble(4, fees);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int patientID = generatedKeys.getInt(1);
                    System.out.println(" Details of Patient:" + patientName + "is saved successfully");
                }

            }
        }

    }

    // Method to find doctor and print patient data
    public void findDoctor(String doctorName) throws SQLException {
        String query = "SELECT * FROM hospital WHERE doctor_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, doctorName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hospital patient = mapResultSetToHospital(resultSet);
                    System.out.println(patient);
                }
            }
        }
    }

    // Method to change doctor
    public void changeDoctor(int patientId, String newDoctorName) throws SQLException {
        String query = "UPDATE hospital SET doctor_name =? WHERE patient_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newDoctorName);
            preparedStatement.setInt(2, patientId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor changed for patient ID:" + patientId);
            } else {
                System.out.println("Patient not found with ID" + patientId);
            }
        }
    }

    // Method to find patient by name
    public void findPatient(String patientName) throws SQLException {
        String query = "SELECT * FROM hospital WHERE patient_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patientName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hospital patient = mapResultSetToHospital(resultSet);
                    System.out.println(patient);
                }
            }
        }
    }

    // Method to find patients with the same disease
    public void findPatientWithSameDisease(String disease) throws SQLException {
        String query = "SELECT * FROM hospital WHERE disease = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, disease);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Hospital patient = mapResultSetToHospital(resultSet);
                    System.out.println(patient);
                }
            }
        }
    }

    // Method to apply discount in fees
    public void applyDiscount(int patientId, double discount) throws SQLException {
        String query = "UPDATE hospital SET fees = fees - ? WHERE patient_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, discount);
            preparedStatement.setInt(2, patientId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Discount applied for patient ID: " + patientId);
            } else {
                System.out.println("Patient not found with ID: " + patientId);
            }
        }
    }

    // Method to print all patients
    public void printAllPatients() throws SQLException {
        String query = "SELECT * FROM hospital";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Hospital patient = mapResultSetToHospital(resultSet);
                System.out.println(patient);
            }
        }
    }

    // Helper method to map ResultSet to Hospital object
    private Hospital mapResultSetToHospital(ResultSet resultSet) throws SQLException {
        Hospital patient = new Hospital(
                resultSet.getString("patient_name"),
                resultSet.getString("disease"),
                resultSet.getString("doctor_name"),
                resultSet.getDouble("fees"));
              //  resultSet.getDouble("fees"));
        return patient;
    }

  }



