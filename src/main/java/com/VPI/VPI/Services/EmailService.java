package com.VPI.VPI.Services;

import com.VPI.VPI.Dtos.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;
    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("notificacionesVPI@gmail.com");
        message.setTo("peter-126@hotmail.com");
        message.setSubject("Prueba ");
        message.setText("Esto es una prueba");

        javaMailSender.send(message);
    }

    public void sendEmailTemplate(EmailDto emailDto){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //  model.put("url",urlFront + emailDto.getPasswordTemporal());
            model.put("codigo",emailDto.getPasswordTemporal());
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template",context);
            helper.setFrom(emailDto.getMailFrom());
            helper.setTo(emailDto.getMailTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendEmailAprobacion(String emailRestaurante){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);

            Context context = new Context();
            String htmlText= templateEngine.process("email-template-aprobacion",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(emailRestaurante);
            helper.setSubject("Aprobacion de registro");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }


    public void sendEmailRechazo(String email , String motivo){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("motivo",motivo);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-rechazo",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Registro rechazado");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }


    }

    public void sendEmailBloqueoRestaurante(String email , String motivo){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("motivo",motivo);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-bloqueoRestaurante",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Cuenta bloqueada");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendEmailBloqueoCliente(String email , String motivo){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("motivo",motivo);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-bloqueoCliente",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Cuenta bloqueada");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
    public void sendEmailAceptaReclamo(String email , String estado){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("text",estado);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-AceptaReclamo",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Hemos procesado tu reclamo");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendEmailRechazarReclamo(String email , String motivo){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("motivo",motivo);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-rechazoReclamo",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Reclamo rechazado");
            helper.setText(htmlText,true);
            javaMailSender.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }


    }






    public void sendEmailConfirmarPedido(String emailCliente, Integer tiempoE){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);

            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("tiempoE",tiempoE);
            context.setVariables(model);
            String htmlText= templateEngine.process("email-template-confirmar-pedido",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(emailCliente);
            helper.setSubject("Pedido confirmado");
            helper.setText(htmlText,true);

            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void sendEmailRechazarPedido(String email , String motivo){
        MimeMessage message =  javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper( message,true);
            Context context = new Context();

            Map<String, Object> model = new HashMap<>();
            //model.put("userName", emailDto.getUserName());
            model.put("motivo",motivo);
            context.setVariables(model);


            String htmlText= templateEngine.process("email-template-rechazo-pedido",context);
            helper.setFrom("notificacionesVPI@gmail.com");
            helper.setTo(email);
            helper.setSubject("Pedido rechazado");
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }


    }


}
