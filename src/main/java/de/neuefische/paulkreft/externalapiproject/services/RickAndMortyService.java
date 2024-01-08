package de.neuefische.paulkreft.externalapiproject.services;

import de.neuefische.paulkreft.externalapiproject.models.Character;
import de.neuefische.paulkreft.externalapiproject.models.api.RickAndMortyCharacterResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {
    private final RestClient restClient = RestClient.builder().baseUrl("https://rickandmortyapi.com/api").build();

    public List<Character> getCharacters() {
        RickAndMortyCharacterResponse response = restClient.get().uri("/character").retrieve().body(RickAndMortyCharacterResponse.class);

        if(response == null) {
            return List.of();
        }

        return response.results();
    }
}
