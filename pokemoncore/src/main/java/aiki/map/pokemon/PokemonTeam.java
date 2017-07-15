package aiki.map.pokemon;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class PokemonTeam {

    private CustList<PkTrainer> team;

    @CheckedData
    private short reward;

    public void validate(DataBase _data) {
        for (PkTrainer p: team) {
            p.validate(_data, true);
//            if (!p.isValid(_data)) {
//                throw new DataException();
//            }
        }
        if (team.isEmpty()) {
            throw new DataException();
        }
        if (reward <= 0) {
            throw new DataException();
        }
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
    }

    public short getReward() {
        return reward;
    }

    public void setReward(short _reward) {
        reward = _reward;
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
