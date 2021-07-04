package com.patjacobs.gaspar.handler;

import com.patjacobs.gaspar.model.BedSensor;
import com.patjacobs.gaspar.service.BedSensorService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.net.URI;

@Component
public class BedSensorHandler {

	@Autowired
	private BedSensorService bedSensorService;

	public Mono<ServerResponse> getTest(ServerRequest req) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.build();
	}

	public Mono<ServerResponse> getBedSensor(ServerRequest req) {
		return defaultReadResponse(bedSensorService.getBedSensor(id(req)));
	}

	public Mono<ServerResponse> getAllBedSensors(ServerRequest req) {
		return defaultReadResponse(bedSensorService.getAllBedSensors());
	}

	public Mono<ServerResponse> getBedSensorStatus(ServerRequest req) {
		return defaultReadResponse(bedSensorService.getBedSensorStatus(id(req)));
	}

	public Mono<ServerResponse> updateSensorStatus(ServerRequest req) {
		Mono<BedSensor> mono = req.bodyToMono(BedSensor.class)
				.doOnNext(b -> bedSensorService.updateSensorStatus(id(req), b.getIsActive()));
		return defaultWriteResponse(mono);
	}

	public Mono<ServerResponse> createBedSensor(ServerRequest req) {
		System.out.println("handler -- " + req);
		Mono<BedSensor> mono = req.bodyToMono(BedSensor.class)
				.flatMap(input -> {
					System.out.println("--- " + input);
					return bedSensorService.createBedSensor(input.getName());
				});

		return defaultWriteResponse(mono);
	}

	private static Mono<ServerResponse> defaultWriteResponse(Publisher<BedSensor> profiles) {
		return Mono
				.from(profiles)
				.flatMap(p -> ServerResponse
						.created(URI.create("/bed-sensors/" + p.getId()))
						.contentType(MediaType.APPLICATION_JSON)
						.build()
				);
	}


	private static Mono<ServerResponse> defaultReadResponse(Publisher<BedSensor> bedSensor) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(bedSensor, BedSensor.class);
	}

	private static Integer id(ServerRequest req) {
		return Integer.valueOf(req.pathVariable("id"));
	}
}
