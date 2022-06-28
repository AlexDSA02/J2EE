package apirest;


import apirest.poke.Poke;
import apirest.user.User;
import apirest.user.UserBean;
import apirest.poke.PokeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;


@Path("sample")
public class amongousse {

    @Inject
    private UserBean userBean;

    @Inject
    private PokeBean pokeBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/among")
    public Response getCrew(User user) {


        var userFind = userBean.getUser(user.getMail());
        if (Objects.equals(userFind.getPassword(), user.getPassword())){
            System.out.println("TOUT EST OK");
        }
        else {
            System.out.println("PASSWORD MAUVAIS");
        }

        // src: https://attaque-des-titans.fandom.com/fr/wiki/Titans
        
        List<CrewMember> crewMember = new ArrayList<>();

        CrewMember Mycrew = new CrewMember();
        CrewMember Mycrew2 = new CrewMember();

        Mycrew2.setJob("Imposteur2");
        Mycrew.setJob("Imposteur");
        Mycrew2.setName("Nom Imposteur2");
        Mycrew.setName("Nom Imposteur");

        crewMember.add(Mycrew);
      // crewMember = {"test","test2"};
      //crewMember.add("test");


        return Response.ok(crewMember).build();
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/postamong")
    public Response testPost(CrewMember crewMemberPost) {
        CrewMember moncCrewMember = new CrewMember();
        moncCrewMember.setName("test");
        moncCrewMember.setJob("add");


        if (moncCrewMember.getName() == null){
            return Response.status(403).build();
        }
        //crewBean.addMember(crewMemberPost);
        return Response.ok("Posted " + moncCrewMember.getName() + ". Age: " + moncCrewMember.getJob()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/createUser")
    public Response testPost(User user) {
        if (user.getMail() == null){
            return Response.status(403).build();
        }

        var tokenCreated = "";
        for (int i = 0; i < 15; i++) {
            Random rand = new Random();
            char c = (char)(rand.nextInt(26) + 97);
            tokenCreated+=c;
        }



        user.setToken(tokenCreated);
        userBean.createUser(user);
        return Response.ok("Posted " + user.getMail() + ". Age: " + user.getPassword()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/createPokemon")
    public Response postPokemon(Poke poke) {
        if (poke.getName() == null){
            return Response.status(403).build();
        }

        pokeBean.createPoke(poke);
        return Response.ok("Posted " + poke.getName() + ". Type: " + poke.getType()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("updateUser/{idUser}")
    public Response updateUser(User user, @PathParam("idUser") String idUser) {
        userBean.updateUser(user, idUser);
        return Response.ok("Posted " + user.getMail() + ". Age: " + user.getPassword()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("updatePokemon/{idPoke}")
    public Response updatePoke(Poke poke, @PathParam("idPoke") String idPoke) {
        pokeBean.updatePoke(poke, idPoke);
        return Response.ok("Posted " + poke.getName() + ". Type: " + poke.getType()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getUserByMail")
    public Response getUserByMail(User user) {
        userBean.getUser(user.getMail());
        return Response.ok("Posted " + user.getMail() + ". Age: " + user.getPassword()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPokemons")
    public Response getPokemons() {
        return Response.ok(pokeBean.getPokes()).build();
        //return Response.ok("Posted " + poke.getName() + ". Type: " + poke.getType()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUsers")
    public Response getUsers() {
        return Response.ok(userBean.getUsers()).build();
        //return Response.ok("Posted " + poke.getName() + ". Type: " + poke.getType()).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("deletePokemon/{idPokemon}")
    public Response removeUser(@PathParam("idPokemon") String idPokemon) {
        pokeBean.deletePoke(idPokemon);
        return Response.ok("Deleted user " + idPokemon).build();
    }

    //return Response.ok(CrewMember).build();
}
