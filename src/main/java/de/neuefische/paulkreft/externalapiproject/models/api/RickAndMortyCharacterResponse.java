package de.neuefische.paulkreft.externalapiproject.models.api;

import de.neuefische.paulkreft.externalapiproject.models.Character;
import de.neuefische.paulkreft.externalapiproject.models.Info;

import java.util.List;

public record RickAndMortyCharacterResponse(
        List<Character> results,
        Info info
) {
}
