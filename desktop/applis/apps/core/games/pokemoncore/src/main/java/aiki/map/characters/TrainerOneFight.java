package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.pokemon.PkTrainer;
import aiki.util.DataInfoChecker;
import code.util.CustList;


public abstract class TrainerOneFight extends Trainer implements Rewardable {

    private short reward;

    private CustList<PkTrainer> team;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateTrainerOneFight(_data);
    }

    protected final void validateTrainerOneFight(DataBase _data) {
        for (PkTrainer p : team) {
            p.validateAsNpc(_data);
        }
        if (team.isEmpty()) {
            _data.setError(true);
        }
        DataInfoChecker.checkPositive(reward,_data);
    }

    @Override
    public short getReward() {
        return reward;
    }

    public void setReward(short _reward) {
        reward = _reward;
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
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
