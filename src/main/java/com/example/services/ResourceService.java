package com.example.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.example.controllers.ResourceController;
import com.example.models.Resource;
import com.example.utils.Message;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/resource/")
@Api(value = "/resource", description = "Resource operations")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceService {

	private static final Logger log = Logger.getLogger(ResourceService.class.getName());

	@GET
	@ApiOperation(value = "Get all resources", notes = "Return all resources")
	public Response getResources() {
		Status status = Response.Status.BAD_REQUEST;
		Object out;
		try {
			ResourceController resourceController = ResourceController.getInstance();
			out = resourceController.getResources();
			status = Response.Status.OK;
			log.info("Get All Resources: Operation successful");
		} catch (Exception e) {
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}

	@GET
	@Path("/{key}")
	@ApiOperation(value = "Get Resource by key", notes = "Get Resource by key")
	public Response getResource(@PathParam("key") int key) {
		Status status = Response.Status.BAD_REQUEST;
		Object out;
		try {
			ResourceController resourceController = ResourceController.getInstance();
			out = resourceController.getResource(key);
			status = Response.Status.OK;
			log.info("Get Resource by key: Operation successful");
		} catch (Exception e) {
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}

	@POST
	@ApiOperation(value = "Create a resource", notes = "Create a resource")
	public Response postResource(Resource r) {
		Status status = Response.Status.BAD_REQUEST;
		Object out;
		try {
			ResourceController resourceController = ResourceController.getInstance();
			out = resourceController.createResource(r);
			status = Response.Status.OK;
			log.info("Insert: Operation successful");
		} catch (Exception e) {
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
}