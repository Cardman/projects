package aiki.map.places;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.pokemon.WildPk;
import aiki.util.Coords;

public abstract class Campaign extends Place {

    @Override
    public abstract LevelWithWildPokemon getLevelByCoords(Coords _coords);
    public abstract void initializeWildPokemon();
    public abstract void addPerson(Coords _coords, Person _person);
    public abstract boolean containsPokemon(Coords _coords);
    public abstract void addPokemon(Coords _coords,WildPk _pk);
    public abstract WildPk getPokemon(Coords _coords);
    public abstract boolean containsObject(Coords _coords);
    public abstract void addObject(Coords _coords,String _object);
    public abstract String getObject(Coords _coords);
    public abstract boolean containsDualFight(Coords _coords);
    public abstract void addDualFight(Coords _coords,DualFight _dualFight);
    public abstract DualFight getDualFight(Coords _coords);
    public abstract boolean containsHm(Coords _coords);
    public abstract void addHm(Coords _coords,short _hm);
    public abstract short getHm(Coords _coords);
    public abstract boolean containsTm(Coords _coords);
    public abstract void addTm(Coords _coords,short _tm);
    public abstract short getTm(Coords _coords);

    public abstract void setItem(Coords _coords, String _object);

    public abstract void setTm(Coords _coords, short _object);

    public abstract void setHm(Coords _coords, short _object);
}
