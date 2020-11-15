

package com.alejandro.code.controller;
import com.alejandro.code.entitys.Piesas;
import com.alejandro.code.service.PiesasService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/piesas")
public class PiesasController {

@Autowired
PiesasService piesasService;



        @GetMapping("/Gethumanos/{humanos}")
        private Piesas findByHumanos(@PathVariable("humanos") String  humanos) {
            return piesasService.findByHumanos(humanos);
        }

        @GetMapping("/Getoriginal/{original}")
        private Piesas findByOriginal(@PathVariable("original") String  original) {
            return piesasService.findByOriginal(original);
        }

        @GetMapping("/Gethumanoscontain/{humanos}")
        private List<Piesas> findByHumanosContain(@PathVariable("humanos") String  humanos) {
            return piesasService.findByHumanosContaining(humanos);
        }

        @GetMapping("/Getoriginalcontain/{original}")
        private List<Piesas> findByOriginalContain(@PathVariable("original") String  original) {
            return piesasService.findByOriginalContaining(original);
        }


        @GetMapping("/GetPiesas/{id}")
          private Piesas findById(@PathVariable("id") Long id) {
            return piesasService.findById(id);
          }


        @GetMapping("/GetAllPiesas")
        private  List<Piesas> getAllPiesas(){
            return piesasService.getAllPiesas();}



        @PostMapping("/save")
        private Boolean  savePiesas(@RequestBody Piesas piesas){ 
            return piesasService.savePiesas(piesas); }



        @PostMapping("/Update")
        private Long UpdatePiesas(@RequestBody Piesas piesas){ 
            piesasService.updatePiesas(piesas);
            return piesas.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdatePiesas(@RequestBody Piesas piesas){ 
            return piesasService.saveOrUpdatePiesas(piesas); }


        @DeleteMapping("/deletePiesas/{id}")
            private boolean deletePiesas(@PathVariable("id") Long id) {
            return piesasService.deletePiesas(id); }


}
