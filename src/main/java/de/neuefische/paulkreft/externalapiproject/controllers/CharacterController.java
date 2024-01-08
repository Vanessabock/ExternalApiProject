package de.neuefische.paulkreft.externalapiproject.controllers;

import de.neuefische.paulkreft.externalapiproject.models.Character;
import de.neuefische.paulkreft.externalapiproject.services.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final RickAndMortyService rickAndMortyService;

    @GetMapping
    public List<Character> getCharacters() {
         return rickAndMortyService.getCharacters();
    }
}
