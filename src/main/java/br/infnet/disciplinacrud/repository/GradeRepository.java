package br.infnet.disciplinacrud.repository;

import br.infnet.disciplinacrud.model.Subject;
import br.infnet.disciplinacrud.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findBySubjectAndValueGreaterThanEqual(Subject subject, Double value);

    List<Grade> findBySubjectAndValueLessThan(Subject subject, Double value);
}
