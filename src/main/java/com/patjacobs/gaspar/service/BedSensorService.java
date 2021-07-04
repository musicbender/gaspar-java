package com.patjacobs.gaspar.service;

import com.patjacobs.gaspar.model.BedSensor;
import com.patjacobs.gaspar.repository.BedSensorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Log4j2
@Service
public class BedSensorService {

	@Autowired
	private BedSensorRepository bedSensorRepository;

	public Mono<BedSensor> getBedSensor(Integer id) {
		return bedSensorRepository.findById(id);
	}

	public Flux<BedSensor> getAllBedSensors() {
		return bedSensorRepository.findAll();
	}

	public Mono<BedSensor> getBedSensorStatus(Integer id) {
		return bedSensorRepository.getActivityById(id);
	}

	public Mono<Integer> updateSensorStatus(Integer id, Boolean isActive) {
		return bedSensorRepository.updateActivityById(id, isActive);
	}

	public Mono<BedSensor> createBedSensor(String name) {
		System.out.println("service -- " + name);
		BedSensor newSensor = BedSensor.builder().name("test_sensor_x").build();
		return bedSensorRepository.save(newSensor);
	}
}
