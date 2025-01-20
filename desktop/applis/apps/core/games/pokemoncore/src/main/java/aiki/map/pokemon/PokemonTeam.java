package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.characters.Rewardable;
import aiki.util.DataInfoChecker;
import code.util.CustList;


public final class PokemonTeam implements Rewardable {

    private CustList<PkTrainer> team;

    private long reward;

    public void validate(DataBase _data) {
        for (PkTrainer p : team) {
            p.validateAsNpc(_data);
        }
        if (team.isEmpty()) {
            _data.setError(true);
        }
        DataInfoChecker.checkPositive(reward,_data);
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
    }

    @Override
    public long getReward() {
        return reward;
    }

    public void setReward(long _reward) {
        reward = _reward;
    }

    // @Override
    // public void beforeSave() {
    // // List<PokemonTrainer> l_ = new List<>();
    // // for (PokemonTrainer p: team) {
    // // l_.add(new PkTrainer(p, p.getMoves()));
    // // }
    // // team = l_;
    // }
    //
    // @Override
    // public void afterLoad() {
    // }
}
