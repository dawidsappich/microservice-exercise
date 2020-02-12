package de.cdiag.socialmultiplication.repository;

import de.cdiag.socialmultiplication.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String userAlias);
}
