package aiki.beans.facade.solution.dto;
import aiki.map.util.PlaceLevel;
import code.util.CustList;
import code.util.TreeMap;

public final class StepDto {

    private final TreeMap<PlaceLevel,CustList<WildPokemonDto>> pokemon;
    private CustList<PlaceTrainerDto> names = new CustList<PlaceTrainerDto>();

    public StepDto(TreeMap<PlaceLevel,CustList<WildPokemonDto>> _pokemon) {
        pokemon = _pokemon;
    }

    public CustList<PlaceTrainerDto> getNames() {
        return names;
    }

    public TreeMap<PlaceLevel,CustList<WildPokemonDto>> getPokemon() {
        return pokemon;
    }
}