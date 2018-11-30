package aiki.fight.pokemon.evolution;

import aiki.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.enums.Gender;


public final class EvolutionLevelGender extends EvolutionLevel implements
        GenderConstraints {

    private Gender gender;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        super.validate(_dataBase, _fPk);
        if (!_fPk.getGenderRep().getPossibleGenders().containsObj(gender)) {
            _dataBase.setError(true);
            return;

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
