package aiki.fight.pokemon.evolution;

import aiki.DataBase;
import aiki.fight.items.EvolvingStone;
import aiki.fight.pokemon.PokemonData;
import code.util.annot.RwXml;

@RwXml
public abstract class EvolutionStone extends Evolution {

    private String stone;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        if (_dataBase.getItem(stone) instanceof EvolvingStone) {
            return;
        }
        _dataBase.setError(true);
        return;

    }

    public String getStone() {
        return stone;
    }

    public void setStone(String _stone) {
        stone = _stone;
    }
}
