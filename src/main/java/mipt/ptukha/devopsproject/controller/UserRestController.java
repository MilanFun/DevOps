package mipt.ptukha.devopsproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import mipt.ptukha.devopsproject.entity.User;
import mipt.ptukha.devopsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author aleksey
 */
@Controller
@RequestMapping(path = "/user")
public class UserRestController {
    @Autowired
    private UserRepository userReposytory;
    
    @Operation(summary = "Get all user in database")
        @ApiResponses(value = { 
          @ApiResponse(responseCode = "200", description = "Ok! All user exists", 
            content = { @Content(mediaType = "application/json", 
              schema = @Schema(implementation = User.class)) }), 
          @ApiResponse(responseCode = "404", description = "Table not found", 
            content = @Content) })
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getAllUsers() {
        return this.userReposytory.findAll();
    }
    
    @Operation(summary = "Add user by its first name, last name, email and age")
        @ApiResponses(value = { 
          @ApiResponse(responseCode = "200", description = "Add new user was success", 
            content = { @Content(mediaType = "application/json", 
              schema = @Schema(implementation = User.class)) }),
          @ApiResponse(responseCode = "400", description = "Invalid parameters or parameters is not present", 
            content = @Content)})
    @RequestMapping(path = "/add", method=RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return this.userReposytory.save(user);
    }
    
    @Operation(summary = "Delete user by its id")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the user", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Book not found", 
        content = @Content) })
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        this.userReposytory.deleteById(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }
   
    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Add new user was success", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid parameters or parameters is not present", 
        content = @Content)})
    @RequestMapping(path = "/getuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return this.userReposytory.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
