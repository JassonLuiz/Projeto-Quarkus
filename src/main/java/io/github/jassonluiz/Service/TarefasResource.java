package io.github.jassonluiz.Service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.github.jassonluiz.Pojos.Tarefas;
import io.github.jassonluiz.Service.TarefasService;
import io.smallrye.mutiny.Uni;

@Path("/tarefas")
public class TarefasResource {

	@Inject
	TarefasService service;
	
	@GET
	public Uni<List<String>> keys(){
		return service.keys();
	}
	
	@POST
	public Tarefas create(Tarefas tarefas) {
		service.set(tarefas.key, tarefas.value);
		return tarefas;
	}
	
	@GET
	@Path("/{key}")
	public Tarefas get(@PathParam("key") String key) {
		return new Tarefas(key, Integer.valueOf(service.get(key)));
	}
	
	@PUT
	@Path("/{key}")
	public void tarefas(@PathParam("key") String key, Integer value) {
		service.increment(key, value);
	}
	
	public Uni<Void> delete(@PathParam("key") String key){
		return service.del(key);
	}
	
}
