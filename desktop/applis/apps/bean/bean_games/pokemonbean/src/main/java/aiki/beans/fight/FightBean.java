package aiki.beans.fight;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.NatStringTreeMap;
import code.util.StringMap;

public class FightBean extends CommonFightBean {
    private int mult;
    private NatStringTreeMap<ActivityOfMoveStill> enabledMoves;
    private int nbFleeAttempt;
    private LgInt nbRounds;
    private Rate winningMoney;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        Fight fight_ = dataBaseFight_.getGame().getFight();
        mult = fight_.getMult();
        nbRounds = fight_.getNbRounds();
        nbFleeAttempt = fight_.getNbFleeAttempt();
        winningMoney = fight_.getWinningMoney();
        NatStringTreeMap<ActivityOfMoveStill> enabledMoves_;
        enabledMoves_ = new NatStringTreeMap<ActivityOfMoveStill>();
        for (String m: fight_.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), new ActivityOfMoveStill(fight_.getEnabledMoves().getVal(m),fight_.getStillEnabledMoves().contains(m)));
        }
        enabledMoves = enabledMoves_;
    }
    public boolean isStillEnabled(int _index) {
        return enabledMoves.getValue(_index).isStill();
    }
    public String clickPlayer() {
        return click(Fight.CST_PLAYER);
    }

    public String clickFoe() {
        return click(Fight.CST_FOE);
    }

    public String click(int _c) {
        getForms().put(NO_TEAM, _c);
        return PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML;
    }

    public int getMult() {
        return mult;
    }

    public LgInt getNbRounds() {
        return nbRounds;
    }

    public int getNbFleeAttempt() {
        return nbFleeAttempt;
    }

    public Rate getWinningMoney() {
        return winningMoney;
    }

    public NatStringTreeMap<ActivityOfMoveStill> getEnabledMoves() {
        return enabledMoves;
    }
}