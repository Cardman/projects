package aiki.fight.pokemon.evolution;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.pokemon.PokemonData;
import code.util.annot.RwXml;


@RwXml
public abstract class EvolutionLevel extends Evolution {

    private short level;

    @Override
    public void validate(DataBase _dataBase,PokemonData _fPk) {
        if (level <= 0) {
            throw new DataException();
        }
        if (level > _dataBase.getMaxLevel()) {
            throw new DataException();
        }
    }
    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }
}
