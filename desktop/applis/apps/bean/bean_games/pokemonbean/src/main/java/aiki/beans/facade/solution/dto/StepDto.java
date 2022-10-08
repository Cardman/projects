package aiki.beans.facade.solution.dto;

import aiki.comparators.DictionaryComparatorPlaceLevel;
import code.util.CustList;

public final class StepDto {

    private final DictionaryComparatorPlaceLevel<CustList<WildPokemonDto>> pokemon;
    private final CustList<PlaceTrainerDto> names = new CustList<PlaceTrainerDto>();

    public StepDto(DictionaryComparatorPlaceLevel<CustList<WildPokemonDto>> _pokemon) {
        pokemon = _pokemon;
    }

    public CustList<PlaceTrainerDto> getNames() {
        return names;
    }

    public DictionaryComparatorPlaceLevel<CustList<WildPokemonDto>> getPokemon() {
        return pokemon;
    }
}