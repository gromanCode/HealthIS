package com.groman.healthis.mysql_repository;

import com.groman.healthis.mysql_domain.SensorInDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorInDeviceRepository extends JpaRepository<SensorInDevice, Long> {

}
