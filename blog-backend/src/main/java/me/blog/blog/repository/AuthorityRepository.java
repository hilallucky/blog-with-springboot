package me.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.blog.blog.model.domain.Authority;

/**
 * @author Hilal
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}