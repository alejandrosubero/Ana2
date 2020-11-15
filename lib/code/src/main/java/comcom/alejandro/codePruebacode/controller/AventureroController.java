

package com.alejandro.code.controller;
import com.alejandro.code.entitys.Aventurero;
import com.alejandro.code.service.AventureroService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.alejandro.code.entitys.Piesas;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/aventurero")
public class AventureroController {

@Autowired
AventureroService aventureroService;



        @GetMapping("/Getnombre/{nombre}")
        private Aventurero findByNombre(@PathVariable("nombre") String  nombre) {
            return aventureroService.findByNombre(nombre);
        }

        @GetMapping("/Getpoder/{poder}")
        private Aventurero findByPoder(@PathVariable("poder") String  poder) {
            return aventureroService.findByPoder(poder);
        }

        @GetMapping("/Getnombrecontain/{nombre}")
        private List<Aventurero> findByNombreContain(@PathVariable("nombre") String  nombre) {
            return aventureroService.findByNombreContaining(nombre);
        }

        @GetMapping("/Getpodercontain/{poder}")
        private List<Aventurero> findByPoderContain(@PathVariable("poder") String  poder) {
            return aventureroService.findByPoderContaining(poder);
        }


        @GetMapping("/GetAventurero/{id}")
          private Aventurero findById(@PathVariable("id") Long id) {
            return aventureroService.findById(id);
          }


        @GetMapping("/GetAllAventurero")
        private  List<Aventurero> getAllAventurero(){
            return aventureroService.getAllAventurero();}



        @PostMapping("/save")
        private Boolean  saveAventurero(@RequestBody Aventurero aventurero){ 
            return aventureroService.saveAventurero(aventurero); }



        @PostMapping("/Update")
        private Long UpdateAventurero(@RequestBody Aventurero aventurero){ 
            aventureroService.updateAventurero(aventurero);
            return aventurero.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateAventurero(@RequestBody Aventurero aventurero){ 
            return aventureroService.saveOrUpdateAventurero(aventurero); }


        @DeleteMapping("/deleteAventurero/{id}")
            private boolean deleteAventurero(@PathVariable("id") Long id) {
            return aventureroService.deleteAventurero(id); }



        @PostMapping("/findRelacion")
        private List<Aventurero> findRelacionPiesas(@RequestBody Piesas piesas){ 
            return aventureroService.findByRelacionPiesas(piesas); }

}
