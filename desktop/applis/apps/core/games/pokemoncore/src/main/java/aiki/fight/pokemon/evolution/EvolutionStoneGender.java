package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.enums.Gender;


public final class EvolutionStoneGender extends EvolutionStone implements
        GenderConstraints {

    private Gender gender;

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        boolean ko_ = validateEvolutionStone(_dataBase);
        if (!_fPk.getGenderRep().getPossibleGenders().containsObj(gender)) {
            ko_ = true;

        }
        return ko_;
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
