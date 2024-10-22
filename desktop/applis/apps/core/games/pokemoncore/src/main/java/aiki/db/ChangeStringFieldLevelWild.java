package aiki.db;

import aiki.map.levels.*;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;

public abstract class ChangeStringFieldLevelWild extends ChangeStringFieldLevel {
    @Override
    public void change(Level _l, String _oldName, String _newName) {
        if (_l instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) _l;
            for (AbsAreaApparition a: level_.getWildPokemonAreas()) {
                new ChangeStringFieldPkListWild(buildChange(),a.getWildPokemon()).changeValue(_oldName, _newName);
                new ChangeStringFieldPkListWild(buildChange(),a.getWildPokemonFishing()).changeValue(_oldName, _newName);
            }
            new ChangeStringFieldPkListWild(buildChange(),level_.getLegendaryPks().values()).changeValue(_oldName, _newName);
        }
        super.change(_l, _oldName, _newName);
    }

    @Override
    public CustList<ChangeStringFieldMatch> change(Level _l) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_l instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) _l;
            for (AbsAreaApparition a: level_.getWildPokemonAreas()) {
                ls_.addAllElts(new ChangeStringFieldPkListWild(buildChange(),a.getWildPokemon()).changeValue());
                ls_.addAllElts(new ChangeStringFieldPkListWild(buildChange(),a.getWildPokemonFishing()).changeValue());
            }
            ls_.addAllElts(new ChangeStringFieldPkListWild(buildChange(),level_.getLegendaryPks().values()).changeValue());
        }
        ls_.addAllElts(super.change(_l));
        return ls_;
    }

    protected abstract ChangeStringFieldVisit buildChange();

    @Override
    protected void changeValue(CustList<PkTrainer> _team, String _oldName, String _newName) {
        new ChangeStringFieldPkListTrainer(buildChange(),_team).changeValue(_oldName, _newName);
    }

    @Override
    protected CustList<ChangeStringFieldMatch> changeValue(CustList<PkTrainer> _team) {
        return new ChangeStringFieldPkListTrainer(buildChange(),_team).changeValue();
    }
}
