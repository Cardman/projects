package aiki.db;

import aiki.map.characters.*;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonTeam;
import code.util.CustList;

public abstract class ChangeStringFieldLevel {
    public void change(Level _l, String _oldName, String _newName) {
        if (_l instanceof LevelWithWildPokemon) {
            levelWithWildPokemon((LevelWithWildPokemon) _l, _oldName, _newName);
        }
        if (_l instanceof LevelIndoorGym) {
            levelIndoorGym((LevelIndoorGym) _l, _oldName, _newName);
        }
        if (_l instanceof LevelLeague) {
            levelLeague((LevelLeague) _l, _oldName, _newName);
        }
    }
    public CustList<ChangeStringFieldMatch> change(Level _l) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_l instanceof LevelWithWildPokemon) {
            ls_.addAllElts(levelWithWildPokemon((LevelWithWildPokemon) _l));
        }
        if (_l instanceof LevelIndoorGym) {
            ls_.addAllElts(levelIndoorGym((LevelIndoorGym) _l));
        }
        if (_l instanceof LevelLeague) {
            ls_.addAllElts(levelLeague((LevelLeague) _l));
        }
        return ls_;
    }

    private void levelLeague(LevelLeague _l, String _oldName, String _newName) {
        changeValue(_l.getTrainer().getTeam(), _oldName, _newName);
    }

    private CustList<ChangeStringFieldMatch> levelLeague(LevelLeague _l) {
        return changeValue(_l.getTrainer().getTeam());
    }

    private void levelIndoorGym(LevelIndoorGym _l, String _oldName, String _newName) {
        changeValue(_l.getGymLeader().getTeam(), _oldName, _newName);
        for (GymTrainer t: _l.getGymTrainers().values()) {
            changeValue(t.getTeam(), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> levelIndoorGym(LevelIndoorGym _l) {
        CustList<ChangeStringFieldMatch> ls_ = changeValue(_l.getGymLeader().getTeam());
        for (GymTrainer t: _l.getGymTrainers().values()) {
            ls_.addAllElts(changeValue(t.getTeam()));
        }
        return ls_;
    }

    private void levelWithWildPokemon(LevelWithWildPokemon _l, String _oldName, String _newName) {
        for (CharacterInRoadCave t: _l.getCharacters().values()) {
            if (t instanceof TrainerMultiFights) {
                TrainerMultiFights t_ = (TrainerMultiFights) t;
                for (PokemonTeam t2_: t_.getTeamsRewards()) {
                    changeValue(t2_.getTeam(), _oldName, _newName);
                }
            }
        }
        for (DualFight d: _l.getDualFights().values()) {
            changeValue(d.getAlly().getTeam(), _oldName, _newName);
            changeValue(d.getFoeTrainer().getTeam(), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> levelWithWildPokemon(LevelWithWildPokemon _l) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        for (CharacterInRoadCave t: _l.getCharacters().values()) {
            if (t instanceof TrainerMultiFights) {
                TrainerMultiFights t_ = (TrainerMultiFights) t;
                for (PokemonTeam t2_: t_.getTeamsRewards()) {
                    ls_.addAllElts(changeValue(t2_.getTeam()));
                }
            }
        }
        for (DualFight d: _l.getDualFights().values()) {
            ls_.addAllElts(changeValue(d.getAlly().getTeam()));
            ls_.addAllElts(changeValue(d.getFoeTrainer().getTeam()));
        }
        return ls_;
    }

    protected abstract void changeValue(CustList<PkTrainer> _team, String _oldName, String _newName);
    protected abstract CustList<ChangeStringFieldMatch> changeValue(CustList<PkTrainer> _team);
}
