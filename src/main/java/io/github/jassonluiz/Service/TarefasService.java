package io.github.jassonluiz.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;

@Singleton
public class TarefasService {

	@Inject
	RedisClient redisClient;
	
	@Inject
	ReactiveRedisClient reactiveRedisClient;
	
	Uni<Void> del(String key){
		return reactiveRedisClient.del(Arrays.asList(key))
				.map(response -> null);
	}
	
	String get(String key) {
		return redisClient.get(key).toString();
	}
	
	void set(String key, Integer value) {
		redisClient.set(Arrays.asList(key, value.toString()));
	}
	
	void increment(String key, Integer incrementBy) {
		redisClient.incrby(key, incrementBy.toString());
	}
	
	Uni<List<String>> keys(){
		return reactiveRedisClient
				.keys("*")
				.map(response -> {
					List<String> resultado = new ArrayList<>();
					
					for(Response r : response) {
						resultado.add(r.toString());
					}
					return resultado;
				});
	}
	
}
