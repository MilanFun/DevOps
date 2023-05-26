package mipt.ptukha.devopsproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import mipt.ptukha.devopsproject.entity.User;
import mipt.ptukha.devopsproject.repository.UserRepository;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DevopsprojectApplicationTests {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @BeforeEach
        void setup(){
            this.userRepository.deleteAll();
        }

        @Test
        public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{

            // given - precondition or setup
            User user = new User();
            user.setFirstName("AlexTest");
            user.setLastName("PtukhaTest");
            user.setEmail("test@gamial.com");
            user.setAge(100);

            // when - action or behaviour that we are going test
            ResultActions response = mockMvc.perform(post("/user/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)));

            // then - verify the result or output using assert statements
            response.andDo(print()).
                    andExpect(status().is2xxSuccessful())
                    .andExpect((ResultMatcher) jsonPath("$.firstName",
                            is(user.getFirstName())))
                    .andExpect(jsonPath("$.lastName",
                            is(user.getLastName())))
                    .andExpect(jsonPath("$.email",
                            is(user.getEmail())));
        }

        // JUnit test for Get All employees REST API
        @Test
        public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception{
            // given - precondition or setup
            List<User> listOfEmployees = new ArrayList<>();
            User user1 = new User();
            user1.setFirstName("AlexTest1");
            user1.setLastName("PtukhaTest1");
            user1.setEmail("test1@gamial.com");
            user1.setAge(100);

            User user2 = new User();
            user2.setFirstName("AlexTest2");
            user2.setLastName("PtukhaTest2");
            user2.setEmail("test2@gamial.com");
            user2.setAge(100);
            listOfEmployees.add(user1);
            listOfEmployees.add(user2);
            for(User u : listOfEmployees) {
                this.userRepository.save(u);
            }
            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(get("/user/all"));

            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.size()",
                            is(listOfEmployees.size())));

        }

        // positive scenario - valid employee id
        // JUnit test for GET employee by id REST API
        @Test
        public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception{
            // given - precondition or setup
            User user1 = new User();
            user1.setFirstName("AlexTest3");
            user1.setLastName("PtukhaTest3");
            user1.setEmail("test3@gamial.com");
            user1.setAge(100);
            this.userRepository.save(user1);

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(get("/user/getuser/{id}", user1.getId()));

            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.firstName", is(user1.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(user1.getLastName())))
                    .andExpect(jsonPath("$.email", is(user1.getEmail())));
        }

        // negative scenario - valid employee id
        // JUnit test for GET employee by id REST API
        @Test
        public void givenInvalidUserId_whenGetUserById_thenReturnEmpty() throws Exception{
            // given - precondition or setup
            int employeeId = 0;
            User user1 = new User();
            user1.setFirstName("AlexTest4");
            user1.setLastName("PtukhaTest4");
            user1.setEmail("test4@gamial.com");
            user1.setAge(100);
            this.userRepository.save(user1);

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(get("/user/getuser/{id}", employeeId));

            // then - verify the output
            response.andExpect(status().isNotFound())
                    .andDo(print());
        }

        // JUnit test for delete employee REST API
        @Test
        public void givenUserId_whenDeleteEmployee_thenReturn200() throws Exception{
            // given - precondition or setup
            User user1 = new User();
            user1.setFirstName("AlexTest5");
            user1.setLastName("PtukhaTest5");
            user1.setEmail("test5@gamial.com");
            user1.setAge(100);
            this.userRepository.save(user1);

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(delete("/user/delete/{id}", user1.getId()));

            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print());
        }
}
