package com.alejandro.ana.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.ana.ServiceImpl.ProyectoServiceImpl;
import com.alejandro.ana.entity.Proyecto;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Anas")
public class Donwload {

	protected static final Log logger = LogFactory.getLog(Donwload.class);

	@Autowired
	private ProyectoServiceImpl proyectoServiceImpl;

	/*
	 * Download Files|2
	 */
	@GetMapping("/downloadLogFiles/{id}")
	public ResponseEntity<byte[]> getFiles(@PathVariable String id) {
		
		logger.info("inicia la ejecusion de getFiles recibe: " + id);
		try {
			Proyecto file = proyectoServiceImpl.findByName(id);
			logger.info("inicia la respuesta ResponseEntity.ok() " + file.getName());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
							+ file.getName()+".zip" + "\"").body(file.getPic());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}



	@RequestMapping(value = "/downloadLog/{archivo}")
	public void getLogFile(HttpSession session, HttpServletResponse response, @PathVariable String archivo) {
		String fileName = archivo + ".zip";
		String filePathToBeServed = "C:\\Users\\crismarycastillo\\Desktop\\pruebas\\" + fileName; //UBICACION DEL FILE
		// String
		// pathOutputZip
		// complete file name with path;
		try {
			File fileToDownload = new File(filePathToBeServed);
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// genera un txt y lo descarga
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getDownloadData() throws Exception {

		String regData = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
				+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an "
				+ "unknown printer took a galley of type and scrambled it to make a type specimen book."
				+ " It has survived not only five centuries, but also the leap into electronic typesetting, "
				+ "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset "
				+ "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software "
				+ "like Aldus PageMaker including versions of Lorem Ipsum.";

		byte[] output = regData.getBytes();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("charset", "utf-8");
		responseHeaders.setContentType(MediaType.valueOf("text/html"));
		responseHeaders.setContentLength(output.length);
		responseHeaders.set("Content-disposition", "attachment; filename=filename.class");
		return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
	}

}

//@GetMapping("/subir/{archivo}")
//	public String getFiless(@PathVariable String archivo) {
//   	String filepath = "C:\\Users\\Alejandro\\Desktop\\pruebas\\" + archivo;
//// application/zip, application/octet-stream


//@RequestMapping(value = "/order/{orderId}", method=RequestMethod.GET)
//	public String getOrder(@PathVariable String orderId){
//		return orderId;
//	}


//	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
//	@ResponseBody
//	public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
//		HttpHeaders responseHeaders = new HttpHeaders();
//	return new FileSystemResource("C:\\Users\\Alejandro\\Desktop\\pruebas\\"+fileName+".zip"); 
//	}
