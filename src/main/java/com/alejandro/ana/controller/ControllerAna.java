package com.alejandro.ana.controller;

import com.alejandro.ana.services.ServicioGenerarproyectoRest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alejandro.ana.ServiceImpl.SaveProyectServiceImpl;
import com.alejandro.ana.mapper.ProyectMapper;
import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;

import com.alejandro.ana.services.GenerarInstanciasService;
import com.alejandro.ana.services.SalveProyectService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Ana")
public class ControllerAna {

	protected static final Log logger = LogFactory.getLog(ControllerAna.class);
	
	@Autowired
	private GenerarInstanciasService instancia;

	@Autowired
	private SalveProyectService salveProyectService;
	
	@Autowired
	private ProyectMapper mapper;

	@Autowired
	private ServicioGenerarproyectoRest servicioGenerarproyectoRest;


	@GetMapping("/check")
	public String buenosDias() {
		logger.info("Loading the test pages");
		return "<h1>--------------The Sevice is Run----------</h1>";
	}


	@PostMapping("/archivosBase")
	public boolean createBaseApp(@RequestBody ArchivoBaseDatosPojo archivo) throws Exception {
		logger.info("rescive datos del proyecto: " + archivo.getProyectoName());
		salveProyectService.saveProyectInternamente(mapper.pojoToEntity(archivo));
		return servicioGenerarproyectoRest.ejecutaBase(archivo);
	//	return servicioGenerarproyectoRest.generarBase07(archivo);
	}


//	@PostMapping("/salveUpdate")
//	public boolean salveUpdate(@RequestBody ArchivoBaseDatosPojo archivo) throws Exception {
//		logger.info("Rescive datos del proyecto a salvar: " + archivo.getProyectoName());
//		salveProyectService.saveProyect(mapper.pojoToEntity(archivo));
//		return instancia.ejecutaBase(archivo);
//	}


	@GetMapping("/GetProyectoU/{user}")
	private ResponseEntity<ArchivoBaseDatosPojo> findByUser(@PathVariable("user") String  user) {
		try {
			ArchivoBaseDatosPojo archivo = mapper.entidadToPojo(salveProyectService.findByUser(user));
			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.OK);
		} catch (DataAccessException e) {
			ArchivoBaseDatosPojo archivo = null;
			return new ResponseEntity<ArchivoBaseDatosPojo>( archivo, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/GetProyectoN/{nombre}")
	private ResponseEntity<ArchivoBaseDatosPojo> findBynombreProyectoNombre(@PathVariable("nombre") String nombre) {
		try {
			ArchivoBaseDatosPojo archivo = mapper.entidadToPojo(salveProyectService.findByProyectoName(nombre));
			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.OK);
		} catch (DataAccessException e) {
			ArchivoBaseDatosPojo archivo = null;
			return new ResponseEntity<ArchivoBaseDatosPojo>( archivo, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/GetProyectoA/{autor}")
	private ResponseEntity<ArchivoBaseDatosPojo> findBynombreProyectoAutor(@PathVariable("autor") String autor) {
		try {
			ArchivoBaseDatosPojo archivo = mapper.entidadToPojo(salveProyectService.findByAutor(autor));
			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.OK);
		} catch (DataAccessException e) {
			ArchivoBaseDatosPojo archivo = null;
			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.BAD_REQUEST);
		}
	}


}

//
//
//@PostMapping("/adjunto")
//public CuerpoDeCorreo2 sendEmailAdjuntos(@RequestBody CuerpoDeCorreo2 body) {
//    try {
//        return body;
//    } catch (Exception e) {
//        System.out.println(e);
//        System.out.println(body);
//        return body = null;
//    }
//}
//
//
//@PostMapping("/MuchosCorreos")
//public int sendEmailAMuchos(@RequestBody CuerpoDeCorreo body) {
//
//    try {
//        return envioEmailService.sendEmails(CorreosServices.getListacorreos(body.getCategoria()),body);
//
//    } catch (Exception e) {
//        System.out.println(e);
//        System.out.println(body);
//        return 0;
//    }
//}
//
//
//@PostMapping("/correos")
//public boolean sendEmail1(@RequestBody Correo correo, BindingResult bindingResult) {
//
//    if (bindingResult.hasErrors()) {
//        throw new ValidationException("Feedback in not valid");
//    }
//
//    try {
//        envioEmailService.sendMail(correo);
//        return true;
//    } catch (Exception e) {
//        System.out.println(e);
//        System.out.println(correo);
//        return false;
//    }
//}
//
//
//@PostMapping("/correosRapidos")
//public boolean sendEmailRapido(@RequestBody String cuerpo) {
//	try {
//		envioEmailService.sendPreConfiguredMail(cuerpo);
//		return true;
//	} catch (Exception e) {
//		System.out.println(e);
//		return false;
//	}
//
//}
//
//
//public FeebadbackController(Emailcfg emailcfg) {
//    this.emailcfg = emailcfg;
//}
//@PostMapping("/lig")
//public void sendFeeback(@RequestBody Feedback feedback, BindingResult bindingResult) {
//
//    if (feedback.getToken().equals("")) {
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException("Feedback in not valid");
//        }
//
//        Properties props = System.getProperties();
//
//        props.put("mail.smtp.host", this.emailcfg.getHost());
//        props.put("mail.smtp.user", this.emailcfg.getUsername());
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.port", this.emailcfg.getPort());
//
//        Session session = Session.getDefaultInstance(props);
//
//        MimeMessage message = new MimeMessage(session);
//
//        try {
//            message.setFrom(new InternetAddress(this.emailcfg.getUsername()));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("alejandrosubero.ar@gmail.com"));
//            message.setSubject("new feedback from " + feedback.getName());
//            message.setText("el nombre de la persona que contacta: " + feedback.getName() + "\nCorreo = "
//                    + feedback.getEmail() + "\n" + feedback.getFeedback());
//
//            Transport transport = session.getTransport("smtp");
//            transport.connect(this.emailcfg.getHost(), this.emailcfg.getUsername(), this.emailcfg.getPassword());
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//        } catch (MessagingException me) {
//            me.printStackTrace();
//        }
//
//    }
//
//}
//
//
//@PostMapping("/ligeros")
//public boolean sendFeebacks(@RequestBody Feedback feedback, BindingResult bindingResult) {
//
//    boolean valor = false;
//
//    if (bindingResult.hasErrors()) {
//        throw new ValidationException("Feedback in not valid");
//    }
//
//    Properties props = System.getProperties();
//
//    props.put("mail.smtp.host", this.emailcfg.getHost());
//    props.put("mail.smtp.user", this.emailcfg.getUsername());
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.port", this.emailcfg.getPort());
//
//    Session session = Session.getDefaultInstance(props);
//
//    MimeMessage message = new MimeMessage(session);
//
//    try {
//        message.setFrom(new InternetAddress(this.emailcfg.getUsername()));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress("alejandrosubero@hotmail.com"));
//        message.setSubject("new feedback from " + feedback.getName());
//        message.setText("el nombre de la persona que contacta: " + feedback.getName() + "\nCorreo = "
//                + feedback.getEmail() + "\n" + feedback.getFeedback());
//
//        Transport transport = session.getTransport("smtp");
//        transport.connect(this.emailcfg.getHost(), this.emailcfg.getUsername(), this.emailcfg.getPassword());
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
//        valor = true;
//    } catch (MessagingException me) {
//        me.printStackTrace();
//    }
//    return valor;
//
//}
