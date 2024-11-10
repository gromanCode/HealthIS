package com.groman.healthis.mysql_repository;

import com.groman.healthis.mysql_domain.Values;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuesRepository extends JpaRepository<Values, Long> {
}
