package com.patjacobs.gaspar.router;

import com.patjacobs.gaspar.handler.BedSensorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static com.patjacobs.gaspar.constants.RoutesConstants.BED_SENSOR_ENDPOINT;
import static com.patjacobs.gaspar.constants.RoutesConstants.BED_SENSOR_ENDPOINT_BY_ID;
import static com.patjacobs.gaspar.constants.RoutesConstants.BED_SENSOR_ENDPOINT_STATUS;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class BedSensorRouter {

	@Value("${gaspar.api-version}")
	private Integer apiVersion;

	@Bean
	public RouterFunction<ServerResponse> bedSensorRoutes(BedSensorHandler bedSensorHandler) {
		return RouterFunctions
				.route(GET(version() + BED_SENSOR_ENDPOINT).and(accept(MediaType.APPLICATION_JSON)), bedSensorHandler::getAllBedSensors)
				.andRoute(GET(version() + BED_SENSOR_ENDPOINT_BY_ID).and(accept(MediaType.APPLICATION_JSON)), bedSensorHandler::getBedSensor)
				.andRoute(GET(version() + BED_SENSOR_ENDPOINT_STATUS).and(accept(MediaType.APPLICATION_JSON)), bedSensorHandler::getBedSensorStatus)
				.andRoute(PATCH(version() + BED_SENSOR_ENDPOINT_STATUS).and(accept(MediaType.APPLICATION_JSON)), bedSensorHandler::updateSensorStatus)
				.andRoute(POST(version() + BED_SENSOR_ENDPOINT).and(accept(MediaType.APPLICATION_JSON)), bedSensorHandler::createBedSensor);
	}

	private String version() {
		return "/v" + apiVersion.toString();
	}
}
