package com.recipehub.lanweki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.CommentAddRequest;
import com.recipehub.lanweki.request.RecipeAddRequest;
import com.recipehub.lanweki.service.RecipeService;
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
class RecipeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeService service;

    @InjectMocks
    private RecipeController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetById_whenSuccess() throws Exception {
        var recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Test");

        when(service.getById(1)).thenReturn(recipeDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"));

    }

    @Test
    void testGetAll_whenSuccess() throws Exception {
        var recipeDto1 = new RecipeDto();
        recipeDto1.setId(1);
        recipeDto1.setName("Test1");
        var recipeDto2 = new RecipeDto();
        recipeDto2.setId(2);
        recipeDto2.setName("Test2");

        when(service.getAll(0, 2)).thenReturn(List.of(recipeDto1, recipeDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes?page=0&size=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Test2"));

    }

    @Test
    void testSearch_whenPatternIsProvidedAndSuccess() throws Exception {
        var recipeDto1 = new RecipeDto();
        recipeDto1.setId(1);
        recipeDto1.setName("test");
        var recipeDto2 = new RecipeDto();
        recipeDto2.setId(2);
        recipeDto2.setName("test");

        when(service.searchByPattern("test", 0, 2)).thenReturn(List.of(recipeDto1, recipeDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes?pattern=test&page=0&size=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("test"));

    }

    @Test
    void testSearch_whenCategoryIsProvidedAndSuccess() throws Exception {
        var recipeDto1 = new RecipeDto();
        recipeDto1.setId(1);
        recipeDto1.setCategory("SALADS");
        var recipeDto2 = new RecipeDto();
        recipeDto2.setId(2);
        recipeDto2.setCategory("SALADS");

        when(service.searchByCategory("salads", 0, 2)).thenReturn(List.of(recipeDto1, recipeDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes?category=SALADS&page=0&size=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("SALADS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").value("SALADS"));

    }

    @Test
    void testSearch_whenCuisineIsProvidedAndSuccess() throws Exception {
        var recipeDto1 = new RecipeDto();
        recipeDto1.setId(1);
        recipeDto1.setCuisine("FRENCH");
        var recipeDto2 = new RecipeDto();
        recipeDto2.setId(2);
        recipeDto2.setCuisine("FRENCH");

        when(service.searchByCuisine("french", 0, 2)).thenReturn(List.of(recipeDto1, recipeDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes?cuisine=FRENCH&page=0&size=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cuisine").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cuisine").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cuisine").value("FRENCH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cuisine").value("FRENCH"));

    }

    @Test
    void testSearch_whenCategoryAndCuisineIsProvidedAndSuccess() throws Exception {
        var recipeDto1 = new RecipeDto();
        recipeDto1.setId(1);
        recipeDto1.setCuisine("FRENCH");
        recipeDto1.setCategory("SALADS");
        var recipeDto2 = new RecipeDto();
        recipeDto2.setId(2);
        recipeDto2.setCuisine("FRENCH");
        recipeDto2.setCategory("SALADS");

        when(service.searchCategoryAndCuisine("salads", "french", 0, 2)).thenReturn(List.of(recipeDto1, recipeDto2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes?cuisine=FRENCH&category=SALADS&page=0&size=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cuisine").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cuisine").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cuisine").value("FRENCH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("SALADS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cuisine").value("FRENCH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").value("SALADS"));

    }

    @Test
    void testCreate_whenSuccess() throws Exception {
        var recipeAddRequest = new RecipeAddRequest("Name", "Category", "Cuisine",
                "Ingredients", "Description", "Instruction", 1, null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes")
                        .content(asJsonString(recipeAddRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").isEmpty());

        verify(service).create(recipeAddRequest);
    }

    @Test
    void testAddCommentToRecipe_whenSuccess() throws Exception {
        var commentAddRequest = new CommentAddRequest("1", "1", "Comment");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes/comments")
                        .content(asJsonString(commentAddRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").isEmpty());

        verify(service).addComment(commentAddRequest);
    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
