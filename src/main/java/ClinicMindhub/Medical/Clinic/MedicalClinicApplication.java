package ClinicMindhub.Medical.Clinic;

import ClinicMindhub.Medical.Clinic.models.Appointment;
import ClinicMindhub.Medical.Clinic.models.Doctor;
import ClinicMindhub.Medical.Clinic.models.MedicalSpeciality;
import ClinicMindhub.Medical.Clinic.models.Patient;
import ClinicMindhub.Medical.Clinic.repositories.AppointmentRepository;
import ClinicMindhub.Medical.Clinic.repositories.DoctorRepository;
import ClinicMindhub.Medical.Clinic.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MedicalClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PatientRepository patientRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
		return args -> {

			Patient prueba1 = new Patient("Guillermo", "Perez", "guilleperez@gmail.com", "guille1234", "male", LocalDate.of(1990, 1, 1));
			Patient prueba2 = new Patient("Jose", "Lopez", "joselopez@gmail.com", "jose1234", "male", LocalDate.of(1982, 6, 11));

			patientRepository.save(prueba1);
			patientRepository.save(prueba2);

			Doctor prueba4 = new Doctor("Pablo", "Lopez", "male", MedicalSpeciality.CARDIOLOGIST);

			doctorRepository.save(prueba4);


			Appointment prueba3 = new Appointment(LocalDate.of(2024, 3, 25), LocalDateTime.of(2024, 3, 25, 10, 0));


			prueba1.addAppointment(prueba3);
			prueba4.addAppointment(prueba3);

			appointmentRepository.save(prueba3);



			System.out.println(prueba1);
			System.out.println(prueba2);

		};





	}

}
