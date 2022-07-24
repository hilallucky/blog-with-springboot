package me.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.blog.blog.model.domain.User;

import java.util.Optional;

/**
 * @author Hilal
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findOneWithAuthoritiesByEmail(String lowercaseEmail);

    Boolean existsByEmail(String email);
}