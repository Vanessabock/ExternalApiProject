package de.neuefische.paulkreft.externalapiproject.models.api;

import de.neuefische.paulkreft.externalapiproject.models.Character;

import java.util.List;

public record RickAndMortyCharacterResponse(
        List<Character> results
) {
}
