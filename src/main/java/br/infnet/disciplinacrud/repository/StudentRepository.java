package br.infnet.disciplinacrud.repository;

import br.infnet.disciplinacrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
