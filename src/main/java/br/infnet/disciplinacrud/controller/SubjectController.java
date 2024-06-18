package br.infnet.disciplinacrud.controller;

import br.infnet.disciplinacrud.model.Subject;
import br.infnet.disciplinacrud.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.save(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> findAll() {
        List<Subject> subjects = subjectService.findAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
