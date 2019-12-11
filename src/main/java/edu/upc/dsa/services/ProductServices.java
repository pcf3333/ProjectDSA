package edu.upc.dsa.services;
import edu.upc.dsa.*;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.QueryHelper;
import edu.upc.dsa.util.RandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.util.*;

@Api(value = "/", description = "Endpoint to Track Service")
@Path("/")
public class ProductServices {

    private GameManager gm;
    static private Boolean entrada=true;

    public ProductServices() {
        this.gm = GameManagerImpl.getInstance();
        if (entrada){
        GameManagerImpl Impl = GameManagerImpl.getInstance();
        entrada=false;
        }

    }

    @GET
    @ApiOperation(value = "Get All Users", notes = "Te da todos los Usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

    Session session = FactorySession.openSession();
    ResultSet rs;
    List<Usuario> users=new ArrayList<>();

	try {
         rs = session.simpleQuery(QueryHelper.createSELECTALL("users"));



        while(rs.next()){
            users.add(new Usuario(rs.getString("username"),rs.getString("password"),rs.getString("email")));
        }

        System.out.println(" "+rs.getObject(1));

        System.out.println(" "+rs.getObject(3));
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}


    GenericEntity<List<Usuario>> entity = new GenericEntity<>(users){};
    return Response.status(201).entity(entity).build();


    }

    @POST
    @ApiOperation(value = "Create new user", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario u) {
        if (u.getNombre()==null || u.getEmail()==null)  return Response.status(500).entity(u).build();
        this.gm.addUser(u);

        return Response.status(201).entity(u).build();
    }
    @PUT
    @ApiOperation(value = "Update User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not Found")
    })
    @Path("/modifyUser")
    public Response updateUser(Usuario u) {

        Usuario user =gm.modifyUser(u.getNombre(),u.getEmail(),u.getListaObjetos());

        if (user == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @GET
    @ApiOperation(value = "Get Info of User", notes = "You will see the infoooo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer=""),
    })
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@PathParam("id") String id) {

        GenericEntity<Usuario> entity = new GenericEntity<>(gm.infoUser(id)){};
        return Response.status(201).entity(entity).build()  ;

    }
    @GET
    @ApiOperation(value = "Get Objects of User", notes = "You will see the infoooo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/users/objects/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userObjects(@PathParam("id") String id) {

        GenericEntity<List<Objeto>> entity = new GenericEntity<>(gm.getListObjects(id)){};
        return Response.status(201).entity(entity).build()  ;

    }
    @PUT
    @ApiOperation(value = "Add Object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not Found")
    })
    @Path("/addObject/{id}")
    public Response addObject(Objeto o, @PathParam("id") String id) {

        int error = gm.addObject(o,id);

        if (error == -1) return Response.status(404).build();

        return Response.status(201).build();
    }
/*
    @GET
    @ApiOperation(value = "get a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Track.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        Track t = this.pm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }





*/
}
