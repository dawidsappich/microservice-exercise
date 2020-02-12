package de.cdiag.socialmultiplication.repository;

import de.cdiag.socialmultiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
    Multiplication findByFactorAAndFactorB(int factorA, int factorB);
}
