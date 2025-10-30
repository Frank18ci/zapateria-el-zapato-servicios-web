package com.cibertec.emailservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailManagerPersonalizado {
    public static final String HTML_TEMPLATE = """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Detalle del Producto - Zapatería El Zapato</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        background: #f9fafc;
                        margin: 0;
                        padding: 0;
                        font-family: 'Segoe UI', Arial, sans-serif;
                    }
                    .container {
                        background: #fff;
                        border-radius: 10px;
                        box-shadow: 0 4px 20px rgba(0,0,0,0.05);
                        max-width: 600px;
                        margin: 40px auto;
                        overflow: hidden;
                    }
                    .header {
                        background: #e85d04;
                        color: #fff;
                        text-align: center;
                        padding: 24px 16px;
                    }
                    .header img {
                        width: 80px;
                        margin-bottom: 10px;
                    }
                    .header h1 {
                        margin: 0;
                        font-size: 24px;
                        letter-spacing: 1px;
                    }
                    .content {
                        padding: 24px;
                        color: #333;
                    }
                    .content h2 {
                        color: #e85d04;
                        font-size: 20px;
                        margin-bottom: 16px;
                        text-align: center;
                    }
                    .details {
                        width: 100%%;
                        border-collapse: collapse;
                    }
                    .details td {
                        padding: 10px 0;
                        font-size: 15px;
                        vertical-align: top;
                    }
                    .details td.label {
                        font-weight: 600;
                        color: #444;
                        width: 35%%;
                    }
                    .details td.value {
                        color: #222;
                    }
                    .color-box {
                        display:inline-block;
                        width:18px;
                        height:18px;
                        border-radius:3px;
                        vertical-align:middle;
                        margin-right:6px;
                        border:1px solid #ccc;
                    }
                    .footer {
                        text-align: center;
                        font-size: 13px;
                        color: #777;
                        padding: 20px;
                        background: #f9fafc;
                    }
                    .footer strong {
                        color: #e85d04;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Zapatería El ELSAZAPATO</h1>
                    </div>
                    <div class="content">
                        <h2>Detalle del Producto</h2>
                        <table class="details">
                            <tr><td class="label">ID:</td><td class="value">%s</td></tr>
                            <tr><td class="label">Modelo:</td><td class="value">%s</td></tr>
                            <tr><td class="label">Descripción:</td><td class="value">%s</td></tr>
                            <tr><td class="label">Color:</td><td class="value"><span class="color-box" style="background:%s"></span>%s</td></tr>
                            <tr><td class="label">Talla:</td><td class="value">%s</td></tr>
                            <tr><td class="label">Ancho:</td><td class="value">%s</td></tr>
                            <tr><td class="label">SKU:</td><td class="value">%s</td></tr>
                            <tr><td class="label">Estado:</td><td class="value">%s</td></tr>
                        </table>
                    </div>
                    <div class="footer">
                        ¡Gracias por confiar en nosotros!<br>
                        <strong>Zapatería El Zapato</strong> © %s
                    </div>
                </div>
            </body>
            </html>
            """;


    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public MailManagerPersonalizado(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setText(body, true);
            helper.setFrom(from);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
