package aiki.map.places;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.util.Coords;

public abstract class Campaign extends Place {

    public abstract LevelWithWildPokemon getLevelCompaignByCoords(Coords _coords);
    public abstract void initializeWildPokemon();


}
