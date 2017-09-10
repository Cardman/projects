package aiki.fight.pokemon.evolution;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.items.EvolvingItem;
import aiki.fight.pokemon.PokemonData;
import code.serialize.CheckedData;
import code.util.annot.RwXml;


@CheckedData
@RwXml
public class EvolutionItem extends Evolution {

    private String item;

    @Override
    public void validate(DataBase _dataBase,PokemonData _fPk) {
        if (_dataBase.getItem(item) instanceof EvolvingItem) {
            return;
        }
        throw new DataException();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String _item) {
        item = _item;
    }
}
