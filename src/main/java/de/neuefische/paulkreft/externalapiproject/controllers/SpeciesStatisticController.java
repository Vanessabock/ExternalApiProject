package de.neuefische.paulkreft.externalapiproject.controllers;

import de.neuefische.paulkreft.externalapiproject.services.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/species-statistic")
@RequiredArgsConstructor
public class SpeciesStatisticController {

    private final RickAndMortyService rickAndMortyService;

    @GetMapping
    public int getSpeciesCount(@RequestParam Optional<String> species){
        return rickAndMortyService.getSpeciesCount(species);
    }
}
