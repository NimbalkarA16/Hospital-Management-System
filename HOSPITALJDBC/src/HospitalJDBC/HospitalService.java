package HospitalJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalService {
    public static void main(String[] args) {
        try {
            // Connect to the PostgreSQL database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MCASEM2", "postgres", "Ankita2002");

            // Create HospitalController
            HospitalControllerJDBC hospitalController = new HospitalControllerJDBC(connection);

            // Create scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Menu-driven loop
            while (true) {
                System.out.println("\nEffortless Management at Your Fingertips.");
                System.out.println("Here are the menu to perform several operations :-");
                System.out.println("1. To Create Patient");
                System.out.println("2. To Find Doctor");
                System.out.println("3. To Change Doctor");
                System.out.println("4. To Find Patient");
                System.out.println("5. To Find Patient with Same Disease");
                System.out.println("6. To Discount in Fees");
                System.out.println("7. To Print All Patients");
                System.out.println("8. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Create Patient
                        System.out.print("Enter Patient Name: ");
                        String patientName = scanner.next();
                        System.out.print("Enter Disease: ");
                        String disease = scanner.next();
                        System.out.print("Enter Doctor Name: ");
                        String doctorName = scanner.next();
                        System.out.print("Enter Fees: ");
                        double fees = scanner.nextDouble();
                        hospitalController.createPatient(patientName, disease, doctorName, fees);
                        break;

                    case 2:
                        // Find Doctor
                        System.out.print("Enter Doctor Name: ");
                        String searchDoctorName = scanner.next();
                       // hospitalController.findDoctor(searchDoctorName);
                        System.out.println("Doctor : " + searchDoctorName + " is available");
                        break;

                    case 3:
                        // Change Doctor
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        System.out.print("Enter New Doctor Name: ");
                        String newDoctorName = scanner.next();
                        hospitalController.changeDoctor(patientId, newDoctorName);
                        System.out.println("Dr."+ newDoctorName + "is assigned to patient having id: "+ patientId);
                        break;

                    case 4:
                        // Find Patient
                        System.out.print("Enter Patient Name: ");
                        String searchPatientName = scanner.next();
                        hospitalController.findPatient(searchPatientName);
                        break;

                    case 5:
                        // Find Patient with Same Disease
                        System.out.print("Enter Disease: ");
                        String searchDisease = scanner.next();
                        hospitalController.findPatientWithSameDisease(searchDisease);
                        break;

                    case 6:
                        // Discount in Fees
                        System.out.print("Enter Patient ID: ");
                        int discountPatientId = scanner.nextInt();
                        System.out.print("Enter Discount: ");
                        double discount = scanner.nextDouble();
                        hospitalController.applyDiscount(discountPatientId, discount);
                        break;

                    case 7:
                        // Print All Patients
                        hospitalController.printAllPatients();
                        break;

                    case 8:
                        // Exit
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

