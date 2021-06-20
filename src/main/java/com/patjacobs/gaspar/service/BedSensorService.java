package com.patjacobs.gaspar.service;

import com.patjacobs.gaspar.model.BedSensor;
import com.patjacobs.gaspar.repository.BedSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BedSensorService {

	@Autowired
	private BedSensorRepository bedSensorRepository;

	/**
	 * get a single bed sensor information
	 * @param sensorId id of the bed sensor to fetch
	 * @return the specified bed sensor
	 */
	public Mono<BedSensor> getBedSensor(Integer sensorId) {
		return bedSensorRepository.findById(sensorId);
	}

	public Flux<BedSensor> getAllBedSensors() {
		return bedSensorRepository.findAll();
	}

	public Mono<Boolean> getBedSensorStatus(Integer sensorId) {
		return bedSensorRepository.getActivityById(sensorId);
	}

	public Mono<Integer> updateSensorStatus(Integer sensorId, boolean isActive) {
		return bedSensorRepository.updateActivityById(sensorId, isActive);
	}
}
