package com.example.test;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonneController {
    @Autowired
    private PersonneRepository personneRepository;
    @PostMapping("/personnes")
    public ResponseEntity<Personne> ajouterPersonne(@RequestBody Personne personne){
        if (personne.getAge()>150){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Personne personneEnregistree =personneRepository.save(personne);
        return ResponseEntity.status(HttpStatus.CREATED).body(personneEnregistree);
    }
    @GetMapping("/personnes")
    public List<PersonneDto> listerPersonnes(){
        List<Personne> personnes= personneRepository.findByOrderNomAsc();
        return personnes.stream().map(p->new PersonneDto(p.getId(),p.getNom(),p.getPrenom(),p.getAge())).collect(Collectors.toList());
    }
    @GetMapping("/personne")
    public String personneForm(Model model){
        model.addAttribute("personne",new Personne());
        return "personne";
    }
    @PostMapping("/personne")
    public String personneSubmit(@ModelAttribute Personne personne, Model model ){
        if (personne.getAge()>=150){
            model.addAttribute("error","l'age de la personne est trop grand");
            return "personne";
        }
        model.addAttribute("personne",personne);
        return "resultat";
    }
    @PostMapping("/enregistrer")
    public String enregistrer(Personne p , Model model , HttpSession session){
        if ( p.getAge()>=150){
            throw new IllegalArgumentException("Age invalide");
        }
        session.setAttribute("personne",p);
        model.addAttribute("personne",p);
        return "resultat";
    }
}
