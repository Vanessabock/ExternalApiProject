package de.neuefische.paulkreft.externalapiproject;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

    }

    @DynamicPropertySource
    public static void configureUrl(DynamicPropertyRegistry registry) {
        registry.add("app.rickandmorty.api.url", () -> mockWebServer.url("/").toString());
    }

    @AfterAll
    public static void cleanup() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getAllCharactersTest() throws Exception {
        //Given
        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                                                "info": {
                                                       "count": 3,
                                                       "pages": 1
                                                   },
                                                "results": [
                                                         {
                                                            "id": 1,
                                                            "name": "TEst",
                                                            "status": "Alive",
                                                            "species": "Human",
                                                            "type": "",
                                                            "gender": "Male",
                                                            "origin": {
                                                                "name": "Earth (C-137)",
                                                                "url": "https://rickandmortyapi.com/api/location/1"
                                                            },
                                                            "location": {
                                                                "name": "Citadel of Ricks",
                                                                "url": "https://rickandmortyapi.com/api/location/3"
                                                            },
                                                            "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                            "episode": [
                                                                "https://rickandmortyapi.com/api/episode/1",
                                                                "https://rickandmortyapi.com/api/episode/2",
                                                                "https://rickandmortyapi.com/api/episode/3"         
                                                            ],
                                                            "url": "https://rickandmortyapi.com/api/character/1",
                                                            "created": "2017-11-04T18:48:46.250Z"
                                                        }
                                                    ]
                                                }
                                                
                                                
                                                
                        """));


        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                           {"id":"1",
                           "name":"TEst",
                           "species": "Human"}
                        ]
                            """))
                .andReturn();


        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    public void getCharactersByStatusTest() throws Exception{

       //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                                                                          "info": {
                                                                                 "count": 3,
                                                                                 "pages": 1
                                                                             },
                                                                          "results": [
                                                                                   {
                                                                                      "id": 1,
                                                                                      "name": "TEst",
                                                                                      "status": "Alive",
                                                                                      "species": "Human",
                                                                                      "type": "",
                                                                                      "gender": "Male",
                                                                                      "origin": {
                                                                                          "name": "Earth (C-137)",
                                                                                          "url": "https://rickandmortyapi.com/api/location/1"
                                                                                      },
                                                                                      "location": {
                                                                                          "name": "Citadel of Ricks",
                                                                                          "url": "https://rickandmortyapi.com/api/location/3"
                                                                                      },
                                                                                      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                                                      "episode": [
                                                                                          "https://rickandmortyapi.com/api/episode/1",
                                                                                          "https://rickandmortyapi.com/api/episode/2",
                                                                                          "https://rickandmortyapi.com/api/episode/3"        
                                                                                      ],
                                                                                      "url": "https://rickandmortyapi.com/api/character/1",
                                                                                      "created": "2017-11-04T18:48:46.250Z"
                                                                                  },
                                                                                  {
                                                                                      "id": 4,
                                                                                      "name": "Bert",
                                                                                      "status": "Alive",
                                                                                      "species": "Human",
                                                                                      "type": "",
                                                                                      "gender": "Male",
                                                                                      "origin": {
                                                                                                 "name": "Earth (C-137)",
                                                                                                 "url": "https://rickandmortyapi.com/api/location/1"
                                                                                       },
                                                                                      "location": {
                                                                                                   "name": "Citadel of Ricks",
                                                                                                    "url": "https://rickandmortyapi.com/api/location/3"
                                                                                      },
                                                                                      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                                                      "episode": [
                                                                                          "https://rickandmortyapi.com/api/episode/1",
                                                                                          "https://rickandmortyapi.com/api/episode/2",
                                                                                          "https://rickandmortyapi.com/api/episode/3"        
                                                                                      ],
                                                                                      "url": "https://rickandmortyapi.com/api/character/1",
                                                                                      "created": "2017-11-04T18:48:46.250Z"
                                                                               }
                                                                              ]
                        }
                        """));

        //THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/characters?status=Alive"))

          //WHEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                                    [
                           {"id":"1",
                           "name":"TEst",
                           "species": "Human"},
                           {"id": "4",
                            "name": "Bert",
                            "species": "Human"}
                        ]
                                                    """))
                .andReturn();

        assertEquals(result.getResponse().getStatus(), 200);

    }
}
