package aiki.map.characters;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class Ally {

    private CustList<PkTrainer> team;

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
    }

    public void validateForEditing() {
        CustList<PkTrainer> valid_ = new CustList<PkTrainer>();
        for (PkTrainer p: team) {
            valid_.add(p);
        }
        team.clear();
        team.addAllElts(valid_);
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
