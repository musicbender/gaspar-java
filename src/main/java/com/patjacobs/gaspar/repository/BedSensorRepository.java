package com.patjacobs.gaspar.repository;

import com.patjacobs.gaspar.model.BedSensor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BedSensorRepository extends ReactiveCrudRepository<BedSensor, Integer> {

	@Query("SELECT is_active FROM bed_sensors WHERE id = :id")
	Mono<BedSensor> getActivityById(Integer id);

	@Query("UPDATE bed_sensor SET is_active = :isActive WHERE id= :id")
	Mono<Integer> updateActivityById(Integer id, Boolean isActive);
}
