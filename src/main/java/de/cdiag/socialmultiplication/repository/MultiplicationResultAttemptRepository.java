package de.cdiag.socialmultiplication.repository;

import de.cdiag.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {
    List<MultiplicationResultAttempt> findByUserAliasOrderByIdDesc(String userAlias);
}
