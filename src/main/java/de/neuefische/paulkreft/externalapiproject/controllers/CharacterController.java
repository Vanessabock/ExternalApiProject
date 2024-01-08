package de.neuefische.paulkreft.externalapiproject.controllers;

import de.neuefische.paulkreft.externalapiproject.models.Character;
import de.neuefische.paulkreft.externalapiproject.services.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final RickAndMortyService rickAndMortyService;

    @GetMapping
    public List<Character> getCharacters(@RequestParam Optional<String> status) {
         return rickAndMortyService.getCharacters(status);
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id){
        return rickAndMortyService.getCharacterById(id);
    }
}
