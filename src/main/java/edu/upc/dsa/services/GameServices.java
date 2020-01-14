package edu.upc.dsa.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.*;

import edu.upc.dsa.models.Mapa;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.QueryHelper;
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
public class GameServices {

    private GameManager gm;
    static private Boolean entrada=true;

    public GameServices() {
        this.gm = GameManagerImpl.getInstance();
        if (entrada){
        GameManagerImpl Impl = GameManagerImpl.getInstance();
        entrada=false;
        }

    }

    @GET
    @ApiOperation(value = "Get All Users", notes = "Returns all the users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

    ResultSet rs;
    List<Usuario> users=new ArrayList<>();
	try {
        Session session = FactorySession.openSession();
	    rs = session.simpleQuery(QueryHelper.createSELECTALL("users"));

        while(rs.next()){
            String a = rs.getString("objects");
            Objeto[] obj=new ObjectMapper().readValue(a,Objeto[].class);
            List<Objeto> o = Arrays.asList(obj);
            users.add(new Usuario(rs.getString("username"),rs.getString("password"),rs.getInt("money"),rs.getString("email"),o ));
        }

	}
	catch (Exception ex) {
		ex.printStackTrace();
	}

    GenericEntity<List<Usuario>> entity = new GenericEntity<>(users){};
    return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Create new user", notes = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario u) {
        u.setMoney(200); //Initial money
        if (u.getUsername()==null || u.getEmail()==null)  return Response.status(500).entity(u).build();

        try {
            Session session = FactorySession.openSession();
            session.insertQuery(QueryHelper.createQueryINSERT(u,"users"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).entity(u).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a user by name", notes = "Deletes a user by username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User does not exist")
    })
    @Path("deleteuser/{username}")
    public Response DelelteUser(@PathParam("username") String username) {
        if (username.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            int result = session.insertQuery(QueryHelper.createQueryDELETE("users","username", username));
            if (result==0){
                return Response.status(404).build();
            }
            else {
                return Response.status(201).build();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "Delete a object by name", notes = "Deletes a object by object name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Object does not exist")
    })
    @Path("deleteobject/{name}")
    public Response DelelteObject(@PathParam("name") String name) {
        if (name.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            int result = session.insertQuery(QueryHelper.createQueryDELETE("objects","objeto", name));
            if (result==0){
                return Response.status(404).build();
            }
            else {
                return Response.status(201).build();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "Get Info of User by username", notes = "You will see the info of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer=""),
    })
    @Path("/users/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo(@PathParam("username") String username) {
        ResultSet rs;
        Usuario u;
        if (username.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createQuerySELECT("users","username",username,"*"));

            rs.last();
            String a = rs.getString("objects");
            Objeto[] obj=new ObjectMapper().readValue(a,Objeto[].class);
            List<Objeto> o = Arrays.asList(obj);
            u = new Usuario(rs.getString("username"),rs.getString("password"),rs.getInt("money"),rs.getString("email"),o );

            return Response.status(201).entity(u).build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }



    }

    @PUT
    @ApiOperation(value = "Updates a User given a username", notes = "you can update the info of a user EXCEPT THE USERNAME (because its what we use to determine who is changed)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not Found")
    })
    @Path("/modifyUser")
    public Response updateUser(Usuario u) {

        if (u.getUsername()==null)  return Response.status(500).entity(u).build();

        try {
            Session session = FactorySession.openSession();
            int result = session.insertQuery(QueryHelper.createQueryUPDATE("users", "username", u));
            if (result==0){
                return Response.status(404).build();
            }
            else {
                return Response.status(201).build();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Get Objects of User by username", notes = "You will see the objects of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/users/objects/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userObjects(@PathParam("username") String username) {
        ResultSet rs;
        List<Objeto> ObjectList;
        if (username.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createQuerySELECT("users","username",username, "objects"));

            rs.last();
            String a = rs.getString("objects");
            Objeto[] obj=new ObjectMapper().readValue(a,Objeto[].class);
            ObjectList = Arrays.asList(obj);

            GenericEntity<List<Objeto>> entity = new GenericEntity<>(ObjectList) {};

            return Response.status(201).entity(entity).build();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "Get All Objects", notes = "Returns all the objects")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/objects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        ResultSet rs;
        List<Objeto> ObjectList = new ArrayList<>();;
        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createSELECTALL("objects"));

            while(rs.next()){
                ObjectList.add(new Objeto(rs.getString("objeto"),rs.getString("propiedades"),rs.getString("url"),rs.getInt("vida"),rs.getInt("ataque"),rs.getInt("escudo"),rs.getInt("speed")));
            }

            GenericEntity<List<Objeto>> entity = new GenericEntity<>(ObjectList) {};
            return Response.status(201).entity(entity).build();

        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }
    }


    @POST
    @ApiOperation(value = "Add an Object", notes = "adds an object to the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Unable to add this object")
    })
    @Path("/addObject")
    public Response addObject(Objeto o) {
        if (o.getObjeto()==null || o.getPropiedades()==null)  return Response.status(500).entity(o).build();

        try {
            Session session = FactorySession.openSession();
            session.insertQuery(QueryHelper.createQueryINSERT(o,"objects"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).entity(o).build();
    }


    @POST
    @ApiOperation(value = "Add a Map", notes = "adds a map to the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Unable to add this object")
    })
    @Path("/addmap")
    public Response addMap(Mapa m) {
        if (m.getData()==null || m.getName()==null)  return Response.status(500).entity(m).build();

        try {
            Session session = FactorySession.openSession();
            session.insertQuery(QueryHelper.createQueryINSERT(m,"maps"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return Response.status(201).entity(m).build();
    }


    @DELETE
    @ApiOperation(value = "Delete a map by name", notes = "Deletes a map by object name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Object does not exist")
    })
    @Path("deletemap/{name}")
    public Response DeleteMap(@PathParam("name") String name) {
        if (name.equals("")) return Response.status(404).build();
        try {
            Session session = FactorySession.openSession();
            int result = session.insertQuery(QueryHelper.createQueryDELETE("maps","name", name));
            if (result==0){
                return Response.status(404).build();
            }
            else {
                return Response.status(201).build();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "Get All Maps", notes = "Returns all the maps")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mapa.class, responseContainer="List"),
    })
    @Path("/maps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaps() {

        ResultSet rs;
        List<Mapa> maps=new ArrayList<>();
        try {
            Session session = FactorySession.openSession();
            rs = session.simpleQuery(QueryHelper.createSELECTALL("maps"));
            while(rs.next()){
                maps.add(new Mapa(rs.getString("name"),rs.getString("data")));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        GenericEntity<List<Mapa>> entity = new GenericEntity<>(maps){};
        return Response.status(201).entity(entity).build();
    }
}
