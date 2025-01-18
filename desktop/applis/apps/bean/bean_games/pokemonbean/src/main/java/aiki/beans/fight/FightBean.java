package aiki.beans.fight;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.BoolVal;

public class FightBean extends CommonFightBean {
    private int mult;
    private NatStringTreeMap<ActivityOfMove> enabledMoves;
    private NatStringTreeMap<BoolVal> stillEnabledMoves;
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
        NatStringTreeMap<ActivityOfMove> enabledMoves_;
        enabledMoves_ = new NatStringTreeMap<ActivityOfMove>();
        for (String m: fight_.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fight_.getEnabledMoves().getVal(m));
        }
        enabledMoves = enabledMoves_;
        NatStringTreeMap<BoolVal> stillEnabledMoves_;
        stillEnabledMoves_ = new NatStringTreeMap<BoolVal>();
        for (String m: fight_.getStillEnabledMoves().getKeys()) {
            stillEnabledMoves_.put(translationsMoves_.getVal(m), fight_.getStillEnabledMoves().getVal(m));
        }
        stillEnabledMoves = stillEnabledMoves_;
    }
    public boolean isStillEnabled(int _index) {
        String key_ = enabledMoves.getKey(_index);
        return stillEnabledMoves.contains(key_);
    }
    public String clickPlayer() {
        getForms().put(NO_TEAM, Fight.CST_PLAYER);
        return PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML;
    }
    public String clickFoe() {
        getForms().put(NO_TEAM, Fight.CST_FOE);
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

    public NatStringTreeMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }
}