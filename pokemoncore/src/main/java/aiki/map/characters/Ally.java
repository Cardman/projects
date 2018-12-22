package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;


public final class Ally {

    private CustList<PkTrainer> team;

    public void validate(DataBase _data) {
        for (PkTrainer p : team) {
            p.validate(_data, true);
            // if (!p.isValid(_data)) {
            // _data.setError(true);
            return;

            // }
        }
        if (team.isEmpty()) {
            _data.setError(true);
            return;

        }
    }

    public void validateForEditing() {
        CustList<PkTrainer> valid_ = new CustList<PkTrainer>();
        for (PkTrainer p : team) {
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
