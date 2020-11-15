package com.alejandro.ana.admincontroller;


import com.alejandro.ana.services.SalveProyectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Porfirio/Eneas")
public class AdminController {

    @Autowired
    private SalveProyectService salveProyectService;

/*
*
* get all proyect json
* get all user data
* get al count row proyect
* delete all proyecte
* delete 1 proyecte
*
* */




}
