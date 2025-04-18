package aiki.beans.fight;

import aiki.beans.*;
import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.Ints;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class CommonFightBean extends CommonBean implements BeanRenderWithAppName {

    public static final String MOVES_SEPARATOR = ";";
    protected static final String NO_FIGHTER = "_1";
    protected static final String NO_TEAM = "_0";

    protected static final String SPACE = " ";

    protected CommonFightBean() {
        setAppName(MessagesPkBean.APP_BEAN_FIGHT);
    }

    public StringMap<TranslationsFile> filesFight() {
        return getBuilder().files(getAppName());
    }
    public FacadeGame facade() {
        return db();
    }

    protected static String getFighterAtPosition(FacadeGame _facade, TeamPosition _teamPosition) {
        DataBase data_ = _facade.getData();
        Team team_ = _facade.getGame().getFight().getTeams().getVal(_teamPosition.getTeam());
//        return number(team_,_teamPosition.getPosition(),_teamPosition.getPosition(),membersIndex_);
        Fighter fighter_ = _facade.getGame().getFight().getFighter(_teamPosition);
        Ints membersIndex_ = getMembers(_facade,_teamPosition.getTeam());
//        byte i_ = IndexConstants.FIRST_INDEX;
        int nb_ = number(team_,_teamPosition.getPosition(),_teamPosition.getPosition(),membersIndex_);
//        byte nb_ = IndexConstants.FIRST_INDEX;
//        while (i_ < membersIndex_.size()) {
//            byte iTmp_ = membersIndex_.get(i_);
//            if (iTmp_ >= _teamPosition.getPosition()) {
//                break;
//            }
//            Fighter current_ = team_.getMembers().getVal(iTmp_);
//            //fighter_ != current_
//            if (StringUtil.quickEq(fighter_.getName(), current_.getName())) {
//                nb_++;
//            }
//            i_++;
//        }
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    protected static int number(Team _team, int _indexOne, int _index, CustList<Integer> _members) {
        Fighter fighter_ = _team.getMembers().getVal(_indexOne);
        int i_ = IndexConstants.FIRST_INDEX;
        int nb_ = IndexConstants.FIRST_INDEX;
        while (i_ < _index) {
            int iTmp_ = _members.get(i_);
            Fighter current_ = _team.getMembers().getVal(iTmp_);
            //fighter_ != current_
            if (StringUtil.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        return nb_;
    }

    protected static Ints getMembers(FacadeGame _facade, int _noTeam) {
        Team team_ = _facade.getGame().getFight().getTeams().getVal(_noTeam);
        Ints numbers_ = new Ints(team_.getMembers().getKeys());
        numbers_.sort();
        return numbers_;
    }
}