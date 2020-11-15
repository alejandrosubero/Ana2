package com.alejandro.ana.services;


import com.alejandro.ana.modelo.mail.Correo;
import com.alejandro.ana.modelo.mail.CuerpoDeCorreo;

import java.util.List;

public interface EnvioEmailService {

	public void sendMail(Correo mail);
	public int sendEmails(List<String> correos, CuerpoDeCorreo cuerpo);
	public void sendPreConfiguredMail(String message);
	public void sendPreConfiguredMailR();
	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach);
	public void sendMailWithInlineResources(String to, String subject, String fileToAttach);
}
