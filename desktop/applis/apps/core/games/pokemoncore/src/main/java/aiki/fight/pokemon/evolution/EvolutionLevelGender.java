package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.enums.Gender;


public final class EvolutionLevelGender extends EvolutionLevel implements
        GenderConstraints {

    private Gender gender;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        validateEvolutionLevel(_dataBase);
        if (!_fPk.getGenderRep().getPossibleGenders().containsObj(gender)) {
            _dataBase.setError(true);
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
