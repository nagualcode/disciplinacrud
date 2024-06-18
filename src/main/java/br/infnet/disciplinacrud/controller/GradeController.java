package br.infnet.disciplinacrud.controller;

import br.infnet.disciplinacrud.model.Grade;
import br.infnet.disciplinacrud.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<Grade> save(@RequestBody Grade grade) {
        Grade savedGrade = gradeService.save(grade);
        return new ResponseEntity<>(savedGrade, HttpStatus.CREATED);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Grade>> findApproved(@RequestParam Long subjectId) {
        List<Grade> approvedGrades = gradeService.findApproved(subjectId);
        if (approvedGrades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(approvedGrades, HttpStatus.OK);
    }

    @GetMapping("/failed")
    public ResponseEntity<List<Grade>> findFailed(@RequestParam Long subjectId) {
        List<Grade> failedGrades = gradeService.findFailed(subjectId);
        if (failedGrades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(failedGrades, HttpStatus.OK);
    }
}
