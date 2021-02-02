package aiki.beans.fight;
import aiki.beans.StringMapObject;
import aiki.beans.WithFacade;
import aiki.beans.WithForms;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import code.bean.Bean;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class CommonFightBean extends Bean implements WithFacade, WithForms {

    protected static final String FIGHTER = "fighter";
    protected static final String NO_FIGHTER = "no_fighter";
    protected static final String NO_TEAM = "no_team";
    protected static final String TEAM = "team";

    protected static final String MOVES_SEPARATOR = ";";
    protected static final String SPACE = " ";

    private FacadeGame dataBase;

    private StringMapObject forms;

    public FacadeGame getDataBase() {
        return db();
    }

    @Override
    public FacadeGame db() {
        return dataBase;
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        dataBase = _dataBase;
    }

    public StringMapObject getForms() {
        return forms;
    }

    public void setForms(StringMapObject _forms) {
        forms = _forms;
    }

    protected static String getFighterAtPosition(FacadeGame _facade, TeamPosition _teamPosition) {
        DataBase data_ = _facade.getData();
        Team team_ = _facade.getGame().getFight().getTeams().getVal(_teamPosition.getTeam());
        Fighter fighter_ = _facade.getGame().getFight().getFighter(_teamPosition);
        Bytes membersIndex_ = getMembers(_facade,_teamPosition.getTeam());
        byte i_ = IndexConstants.FIRST_INDEX;
        byte nb_ = IndexConstants.FIRST_INDEX;
        while (true) {
            if (i_ >= membersIndex_.size()) {
                break;
            }
            byte iTmp_ = membersIndex_.get(i_);
            if (iTmp_ >= _teamPosition.getPosition()) {
                break;
            }
            Fighter current_ = team_.getMembers().getVal(iTmp_);
            //fighter_ != current_
            if (StringUtil.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    protected static Bytes getMembers(FacadeGame _facade, int _noTeam) {
        Team team_ = _facade.getGame().getFight().getTeams().getVal((byte) _noTeam);
        Bytes numbers_ = new Bytes(team_.getMembers().getKeys());
        numbers_.sort();
        return numbers_;
    }
}