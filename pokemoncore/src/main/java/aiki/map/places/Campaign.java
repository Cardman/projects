package aiki.map.places;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.util.Coords;

public abstract class Campaign extends Place {

    public abstract LevelWithWildPokemon getLevelCompaignByCoords(Coords _coords);
    public abstract void initializeWildPokemon();
    public abstract void addPerson(Coords _coords, Person _person);

    public abstract void addObject(Coords _coords,String _object);

    public abstract void addDualFight(Coords _coords,DualFight _dualFight);

    public abstract void addHm(Coords _coords,short _hm);

    public abstract void addTm(Coords _coords,short _tm);

}
