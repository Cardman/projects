package aiki.fight.pokemon.evolution;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.enums.Gender;
import code.util.annot.RwXml;

@RwXml
public final class EvolutionStoneGender extends EvolutionStone implements GenderConstraints {

    private Gender gender;

    @Override
    public void validate(DataBase _dataBase,PokemonData _fPk) {
        super.validate(_dataBase, _fPk);
        if (!_fPk.getGenderRep().getPossibleGenders().containsObj(gender)) {
            throw new DataException();
        }
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender _gender) {
        gender = _gender;
    }
}
