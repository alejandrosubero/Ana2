

package com.alejandro.code.controller;
import com.alejandro.code.entitys.Sombis;
import com.alejandro.code.service.SombisService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sombis")
public class SombisController {

@Autowired
SombisService sombisService;



        @GetMapping("/Getdia/{dia}")
        private Sombis findByDia(@PathVariable("dia") String  dia) {
            return sombisService.findByDia(dia);
        }

        @GetMapping("/Getdiacontain/{dia}")
        private List<Sombis> findByDiaContain(@PathVariable("dia") String  dia) {
            return sombisService.findByDiaContaining(dia);
        }


        @GetMapping("/GetSombis/{id}")
          private Sombis findById(@PathVariable("id") Long id) {
            return sombisService.findById(id);
          }


        @GetMapping("/GetAllSombis")
        private  List<Sombis> getAllSombis(){
            return sombisService.getAllSombis();}



        @PostMapping("/save")
        private Boolean  saveSombis(@RequestBody Sombis sombis){ 
            return sombisService.saveSombis(sombis); }



        @PostMapping("/Update")
        private Long UpdateSombis(@RequestBody Sombis sombis){ 
            sombisService.updateSombis(sombis);
            return sombis.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateSombis(@RequestBody Sombis sombis){ 
            return sombisService.saveOrUpdateSombis(sombis); }


        @DeleteMapping("/deleteSombis/{id}")
            private boolean deleteSombis(@PathVariable("id") Long id) {
            return sombisService.deleteSombis(id); }


}
