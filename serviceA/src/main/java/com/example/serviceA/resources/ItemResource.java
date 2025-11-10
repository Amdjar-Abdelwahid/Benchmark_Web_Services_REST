package com.example.serviceA.resources;

import com.example.serviceA.dao.ItemDAO;
import com.example.serviceA.entities.Item;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    private final ItemDAO dao = new ItemDAO();

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size,
                           @QueryParam("categoryId") Long categoryId) {
        List<Item> list;
        if (categoryId != null) {
            list = dao.findByCategoryId(categoryId, page, size);
        } else {
            list = dao.findAll(page, size);
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Item it = dao.findById(id);
        if (it == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(it).build();
    }

    @POST
    public Response create(Item item, @Context UriInfo uriInfo) {
        Item created = dao.save(item);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId())).build();
        return Response.created(uri).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Item item) {
        Item updated = dao.update(id, item);
        if (updated == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean removed = dao.delete(id);
        if (!removed) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}
