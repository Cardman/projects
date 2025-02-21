package aiki.beans.fight;
import aiki.beans.BeanAnchorCstEvent;
import aiki.beans.BeanAnchorToTeamEvent;
import aiki.beans.StringMapObject;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.MessagesFightFight;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.NatStringTreeMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FightBean extends CommonFightBean {
    private int mult;
    private NatStringTreeMap<ActivityOfMoveStill> enabledMoves;
    private int nbFleeAttempt;
    private LgInt nbRounds;
    private Rate winningMoney;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade,_form);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightFight.M_P_90_TITLE_FIGHT)));
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,this), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_REFRESH);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,this),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_GO_DETAIL);
        formatMessageAnc(new BeanAnchorToTeamEvent(Fight.CST_PLAYER,this),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_YOURS);
        formatMessageAnc(new BeanAnchorToTeamEvent(Fight.CST_FOE,this),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOES);
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_MULT,Long.toString(getMult()));
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_NB_ROUNDS,getNbRounds().toNumberString());
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_NB_FLEE_ATTEMPTS,Long.toString(getNbFleeAttempt()));
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_WIN_MONEY,getWinningMoney().toNumberString());
        feedParents();
        displayHead(getEnabledMoves(),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ENBALED_MOVES, MessagesFightFight.M_P_90_ENBALED_MOVES_KEY, MessagesFightFight.M_P_90_ENBALED_MOVES_STILL, MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED, MessagesFightFight.M_P_90_ENBALED_MOVES_NB_ROUND);
        int len_ = getEnabledMoves().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDirCts(getEnabledMoves().getKey(i));
            if (isStillEnabled(i)) {
                displayActivityOfMoveEnabled(getEnabledMoves().getValue(i).getActivity(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            } else {
                formatMessageDirCts(formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ENBALED_MOVES_NO));
            }
            displayActivityOfMoveEnabled(getEnabledMoves().getValue(i).getActivity(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            displayActivityOfMoveNbRound(getEnabledMoves().getValue(i).getActivity(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_ENBALED_MOVES_NO);
        }
        feedParents();
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.FIGHT).getMapping();
    }
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