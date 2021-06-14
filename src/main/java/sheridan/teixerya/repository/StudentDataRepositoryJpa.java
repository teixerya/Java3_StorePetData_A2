package sheridan.teixerya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDataRepositoryJpa extends JpaRepository<StudentEntityJpa, Integer> {
}
