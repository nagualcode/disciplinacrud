package br.infnet.disciplinacrud.repository;

import br.infnet.disciplinacrud.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
