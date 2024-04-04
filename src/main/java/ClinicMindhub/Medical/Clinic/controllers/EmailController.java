package ClinicMindhub.Medical.Clinic.controllers;

import ClinicMindhub.Medical.Clinic.dto.FormDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/form")
public class EmailController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody FormDTO formDTO) {
        try {
            SimpleMailMessage clinicMessage = new SimpleMailMessage();
            clinicMessage.setTo("equaino.ir@gmail.com");
            clinicMessage.setFrom("quai1921@hotmail.com");
            clinicMessage.setSubject("New message from Serenety Health Center");
            clinicMessage.setText(
                    "Name: " + formDTO.firstName() + " " + formDTO.lastName() + "\n" +
                            "Phone: " + formDTO.phone() + "\n" +
                            "Email: " + formDTO.email() + "\n" +
                            "City: " + formDTO.city() + "\n" +
                            "ZipCode: " + formDTO.zipCode() + "\n" +
                            "Discover: " + formDTO.discover() + "\n" +
                            "Live in Miami: " + formDTO.liveMiami() + "\n" +
                            "Message: " + formDTO.message()
            );

//            SimpleMailMessage userMessage = new SimpleMailMessage();
//            userMessage.setTo(formDTO.email());
//            userMessage.setFrom("quai1921@hotmail.com");
//            userMessage.setSubject("Welcome to Serenety Health Center");
//            userMessage.setText("Welcome " + formDTO.firstName() + ", we will contact you soon.");


            emailSender.send(clinicMessage);
//            emailSender.send(userMessage);


            MimeMessage userMessage = emailSender.createMimeMessage();
            MimeMessageHelper userMessageHelper = new MimeMessageHelper(userMessage, true);

            userMessageHelper.setTo(formDTO.email());
            userMessageHelper.setFrom("quai1921@hotmail.com");
            userMessageHelper.setSubject("Welcome to Serenety Health Center");

            String userHtmlContent =
                            "<div style='background-color: #f3f4f6; padding: 24px;'>" +
                            "<div style='max-width: 480px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); overflow: hidden;'>" +
                            "<div style='padding: 24px;'>" +
                            "<img src='cid:clinicLogo' alt='Company Logo' style='display: block; margin: 0 auto; max-width: 200px; margin-bottom: 24px;'/>" +
                            "<h2 style='font-size: 24px; font-weight: bold; color: #333333; margin-bottom: 16px;'>Thank You for Your Inquiry!</h2>" +
                            "<p style='color: #666666; margin-bottom: 24px;'>Dear valued patient, " + formDTO.firstName() + " " + formDTO.lastName() + ":</p>" +
                            "<p style='color: #666666; margin-bottom: 24px;'>Thank you for reaching out to Serenety Health Center. We have received your inquiry and one of our representatives will be in touch shortly. We appreciate your patience and understanding. If you have any further questions or concerns, we will get back to you shortly.</p>" +
                            "<p style='color: #666666; margin-bottom: 24px;'>In the meantime, if you have any urgent questions or concerns, feel free to contact us directly at <a href='tel:+123456789'>+1 (234) 567-89</a>.</p>" +
                            "<p style='color: #666666; margin-bottom: 24px;'>Warm regards,</p>" +
                            "<p style='font-weight: bold; color: #06A9B2;'>Serenety Health Center</p>" +
                            "</div></div></div>";


            userMessageHelper.setText(userHtmlContent, true);

            FileSystemResource file = new FileSystemResource(new File("E:/Desktop/MindHub/sprintModulo3/React/MedicalClinic/public/LogoSerenetyH.png"));

            userMessageHelper.addInline("clinicLogo", file);



            emailSender.send(userMessage);

            return "Email succesfully sent";
        } catch (Exception e) {
            return "Error in sending email " + e.getMessage();
        }
    }

}