package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.SoftwareValidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidityRepository extends JpaRepository<SoftwareValidity, Long> {
}
