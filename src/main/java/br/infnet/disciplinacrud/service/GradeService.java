package br.infnet.disciplinacrud.service;

import br.infnet.disciplinacrud.model.Subject;
import br.infnet.disciplinacrud.model.Grade;
import br.infnet.disciplinacrud.repository.SubjectRepository;
import br.infnet.disciplinacrud.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> findApproved(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + subjectId));
        return gradeRepository.findBySubjectAndValueGreaterThanEqual(subject, 7.0);
    }

    public List<Grade> findFailed(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + subjectId));
        return gradeRepository.findBySubjectAndValueLessThan(subject, 7.0);
    }
}
