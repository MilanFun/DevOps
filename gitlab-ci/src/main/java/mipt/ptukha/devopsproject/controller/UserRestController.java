package mipt.ptukha.devopsproject.controller;

import mipt.ptukha.devopsproject.service.UserService;
import org.springframework.http.HttpStatus;
import mipt.ptukha.devopsproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author aleksey
 */
@Controller
@RequestMapping(path = "/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/add", method=RequestMethod.POST)
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return this.userService.save(user);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        this.userService.deleteById(id);
        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
    }

    @RequestMapping(path = "/getuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        if (this.userService.exist(id)) {
            return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
