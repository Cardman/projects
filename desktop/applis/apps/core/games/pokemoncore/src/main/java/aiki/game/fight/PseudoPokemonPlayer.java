package aiki.game.fight;
import aiki.map.pokemon.PokemonPlayer;
import code.maths.Rate;

public class PseudoPokemonPlayer {

    private final Rate wonPointsSinceLastLevel;

    private short level;

    private String name;

    private String item;

    public PseudoPokemonPlayer(PokemonPlayer _pokemon) {
        wonPointsSinceLastLevel = new Rate(_pokemon.getWonExpSinceLastLevel());
        level = _pokemon.getLevel();
        name = _pokemon.getName();
        item = _pokemon.getItem();
    }

    public void affectWonPointsSinceLastLevel(Rate _other) {
        wonPointsSinceLastLevel.affect(_other);
    }

    public Rate copyWonPointsSinceLastLevel() {
        return new Rate(wonPointsSinceLastLevel);
    }

    public Rate getWonPointsSinceLastLevel() {
        return wonPointsSinceLastLevel;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }
}
