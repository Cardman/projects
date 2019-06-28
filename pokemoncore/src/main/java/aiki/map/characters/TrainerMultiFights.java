package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.pokemon.PokemonTeam;
import code.util.CustList;


public final class TrainerMultiFights extends Trainer implements
        CharacterInRoadCave, Fightable {

    private CustList<PokemonTeam> teamsRewards;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (PokemonTeam p : teamsRewards) {
            p.validate(_data);
        }
        if (teamsRewards.isEmpty()) {
            _data.setError(true);

        }
    }

    public CustList<PokemonTeam> getTeamsRewards() {
        return teamsRewards;
    }

    public void setTeamsRewards(CustList<PokemonTeam> _teamsRewards) {
        teamsRewards = _teamsRewards;
    }

    @Override
    public int getMult() {
        return getMultiplicityFight();
    }

}
