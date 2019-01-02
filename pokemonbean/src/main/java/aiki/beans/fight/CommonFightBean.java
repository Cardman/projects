package aiki.beans.fight;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public class CommonFightBean extends Bean {

    protected static final String FIGHTER = "fighter";
    protected static final String NO_FIGHTER = "no_fighter";
    protected static final String NO_TEAM = "no_team";
    protected static final String TEAM = "team";

    protected static final String MOVES_SEPARATOR = ";";
    protected static final String SPACE = " ";

    protected static String getFighterAtPosition(FacadeGame _facade, TeamPosition _teamPosition) {
        DataBase data_ = _facade.getData();
        Team team_ = _facade.getGame().getFight().getTeams().getVal(_teamPosition.getTeam());
        Fighter fighter_ = _facade.getGame().getFight().getFighter(_teamPosition);
        Numbers<Byte> membersIndex_ = getMembers(_facade,_teamPosition.getTeam());
        byte i_ = CustList.FIRST_INDEX;
        byte nb_ = CustList.FIRST_INDEX;
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
            if (StringList.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        if (nb_ == CustList.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringList.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    protected static Numbers<Byte> getMembers(FacadeGame _facade, byte _noTeam) {
        Team team_ = _facade.getGame().getFight().getTeams().getVal(_noTeam);
        Numbers<Byte> numbers_ = new Numbers<Byte>(team_.getMembers().getKeys());
        numbers_.sort();
        return numbers_;
    }
}