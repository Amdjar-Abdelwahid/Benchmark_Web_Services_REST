package com.example.serviceA.resources;

import com.example.serviceA.dao.CategoryDAO;
import com.example.serviceA.entities.Category;
import com.example.serviceA.dao.ItemDAO;
import com.example.serviceA.entities.Item;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CategoryDAO dao = new CategoryDAO();
    private final ItemDAO itemDAO = new ItemDAO();

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {
        List<Category> list = dao.findAll(page, size);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Category c = dao.findById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }

    @POST
    public Response create(Category category, @Context UriInfo uriInfo) {
        Category created = dao.save(category);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId())).build();
        return Response.created(uri).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Category category) {
        Category updated = dao.update(id, category);
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

    // Relation inverse: GET /categories/{id}/items
    @GET
    @Path("/{id}/items")
    public Response getItemsByCategory(@PathParam("id") Long id,
                                       @QueryParam("page") @DefaultValue("0") int page,
                                       @QueryParam("size") @DefaultValue("10") int size) {
        // check category exists
        Category c = dao.findById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        List<Item> items = itemDAO.findByCategoryId(id, page, size);
        return Response.ok(items).build();
    }
}
