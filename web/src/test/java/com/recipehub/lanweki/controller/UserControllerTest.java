package com.recipehub.lanweki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RegisterRequest;
import com.recipehub.lanweki.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testLogin() throws Exception {
        var loginRequest = new LoginRequest("username", "password");

        when(service.login(loginRequest)).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(asJsonString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties.userId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties.userId").value(1));
    }

    @Test
    void testRegister() throws Exception {
        var registerRequest = new RegisterRequest("username", "password", "name", "surname");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .content(asJsonString(registerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").isEmpty());

        verify(service).register(registerRequest);
    }

//    @Test
//    void testAddRecipeToRecipeBook() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/users/1/recipebookadd/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").isEmpty());
//
//        verify(service).addRecipeToRecipeBook(1, 1);
//    }

    @Test
    void testGetRecipeBook() throws Exception {
        var recipe1 = new RecipeDto();
        recipe1.setId(1);
        recipe1.setName("Test1");

        var recipe2 = new RecipeDto();
        recipe2.setId(2);
        recipe2.setName("Test2");

        when(service.getRecipeBookByUserId(1)).thenReturn(List.of(recipe1, recipe2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1/recipebook")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Test2"));

        verify(service).getRecipeBookByUserId(1);
    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


