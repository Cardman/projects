package aiki.beans.facade.simulation.dto;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.WildPk;
import code.util.StringList;

public final class PokemonPlayerDto {

    private Pokemon pokemon = new WildPk();

    private StringList moves = new StringList();
    private boolean selected;
    private int index;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon _pokemon) {
        pokemon = _pokemon;
    }

    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}