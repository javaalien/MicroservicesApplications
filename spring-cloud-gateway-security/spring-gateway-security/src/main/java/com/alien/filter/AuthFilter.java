package com.alien.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.alien.config.RedisHashComponent;
import com.alien.dto.ApiKey;
import com.alien.util.AppConstants;
import com.alien.util.MapperUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

	@Autowired
	private RedisHashComponent redisHashComponent;

	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("gatewaykey");
		log.info("api key {} ", apiKeyHeader);

		Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

		String routeId = route != null ? route.getId() : null;

		if (routeId == null || CollectionUtils.isEmpty(apiKeyHeader) || !isAuthorize(routeId, apiKeyHeader.get(0))) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"you can't consume this service , Please validate your apikeys");
		}

		return chain.filter(exchange);
	}

	private boolean isAuthorize(String routeId, String apiKey) {
		Object apiKeyObject = redisHashComponent.hGet(AppConstants.RECORD_KEY, apiKey);

		if (apiKeyObject != null) {
			ApiKey key = MapperUtils.objectMapper(apiKeyObject, ApiKey.class);
			return key.getServices().contains(routeId);
		} else {
			return false;
		}
	}
}
