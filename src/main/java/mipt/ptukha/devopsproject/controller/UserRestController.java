package mipt.ptukha.devopsproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mipt.ptukha.devopsproject.entity.UserForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import mipt.ptukha.devopsproject.entity.User;
import mipt.ptukha.devopsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author aleksey
 */
@Controller
@RequestMapping(path = "/user")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;
    @Value("User manager application")
    private String message;

    @Value("Error")
    private String errorMessage;
    
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
        return this.userRepository.findAll();
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
        return this.userRepository.save(user);
    }
    
    @Operation(summary = "Delete user by its id")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the user", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = User.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "User not found", 
        content = @Content) })
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        this.userRepository.deleteById(id);
        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
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
        return this.userRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("welcomeMessage", this.message);
        return "index";
    }
    @Operation(summary = "Get UI page with user list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content)})
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("users", this.userRepository.findAll());
        return "userList";
    }

    @Operation(summary = "Add user via UI page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adding successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content)})
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        UserForm user = new UserForm();
        model.addAttribute("userForm", user);
        return "addUser";
    }

    @Operation(summary = "POST via UI users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content)})
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute("userForm") UserForm user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        int age = user.getAge();

        if(firstName != null && lastName != null && firstName.length() > 0 &&
                lastName.length() > 0 && age > 0 && email.length() > 0) {
            User newUser = new User();
            newUser.setAge(age);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);

            this.userRepository.save(newUser);

            return "userList";
        }

        model.addAttribute("errorMessage", this.errorMessage);
        return "userList";
    }
}


