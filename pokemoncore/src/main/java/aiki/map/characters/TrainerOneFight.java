package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.pokemon.PkTrainer;
import code.serialize.CheckedData;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public abstract class TrainerOneFight extends Trainer {

    @CheckedData
    private short reward;

    private CustList<PkTrainer> team;

    public void validate(DataBase _data) {
        validate();
        for (PkTrainer p: team) {
            p.validate(_data, true);
        }
        if (team.isEmpty()) {
            throw new DataException();
        }
        if (reward <= 0) {
            throw new DataException();
        }
    }

    @Override
    public void validateForEditing(DataBase _data) {
        super.validateForEditing(_data);
        CustList<PkTrainer> valid_ = new CustList<PkTrainer>();
        for (PkTrainer p: team) {
            valid_.add(p);
        }
        team.clear();
        team.addAllElts(valid_);
    }

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

//    @Override
//    public void beforeSave() {
////        List<PokemonTrainer> l_ = new List<>();
////        for (PokemonTrainer p: team) {
////            l_.add(new PkTrainer(p, p.getMoves()));
////        }
////        team = l_;
//    }
//
//    @Override
//    public void afterLoad() {
//    }
}
