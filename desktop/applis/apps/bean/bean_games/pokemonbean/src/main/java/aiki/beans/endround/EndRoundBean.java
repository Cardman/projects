package aiki.beans.endround;

import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.moves.effects.*;
import code.util.CustList;

public class EndRoundBean extends CommonBean {

//    private static final String PAGE_EFF = "web/html/endround/eff.html";
//    private static final String PAGE_GLOBAL = "web/html/endround/global.html";
//    private static final String PAGE_INDIVIDUAL = "web/html/endround/individual.html";
//    private static final String PAGE_STATUSRELATION = "web/html/endround/statusrelation.html";
//    private static final String PAGE_STATUS = "web/html/endround/status.html";
//    private static final String PAGE_SINGLERELATION = "web/html/endround/singlerelation.html";
//    private static final String PAGE_FOE = "web/html/endround/foe.html";
//    private static final String PAGE_TEAM = "web/html/endround/team.html";
//    private static final String PAGE_MULTIRELATION = "web/html/endround/multirelation.html";
//    private static final String PAGE_POSITIONRELATION = "web/html/endround/positionrelation.html";
//    private static final String PAGE_POSITIONTARGET = "web/html/endround/positiontarget.html";
    private CustList<EndRoundMainElements> evts;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        evts = data_.getEvtEndRound();
    }

    public String getPage(int _index) {
        CustList<EndRoundMainElements> evts_ = getEvts();
        EndRoundMainElements elt_ = evts_.get(_index);
        return endRound(elt_.getEff());
    }

    private String endRound(EffectEndRound _effect) {
        if (_effect instanceof EffectEndRoundGlobal) {
            return AikiBeansEndroundStd.PAGE_GLOBAL;
        }
        if (_effect instanceof EffectEndRoundIndividual) {
            return AikiBeansEndroundStd.PAGE_INDIVIDUAL;
        }
        if (_effect instanceof EffectEndRoundStatusRelation) {
            return AikiBeansEndroundStd.PAGE_STATUSRELATION;
        }
        if (_effect instanceof EffectEndRoundStatus) {
            return AikiBeansEndroundStd.PAGE_STATUS;
        }
        if (_effect instanceof EffectEndRoundSingleRelation) {
            return AikiBeansEndroundStd.PAGE_SINGLERELATION;
        }
        if (_effect instanceof EffectEndRoundFoe) {
            return AikiBeansEndroundStd.PAGE_FOE;
        }
        if (_effect instanceof EffectEndRoundTeam) {
            return AikiBeansEndroundStd.PAGE_TEAM;
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            return AikiBeansEndroundStd.PAGE_MULTIRELATION;
        }
        if (_effect instanceof EffectEndRoundPositionRelation) {
            return AikiBeansEndroundStd.PAGE_POSITIONRELATION;
        }
        if (_effect instanceof EffectEndRoundPositionTargetRelation) {
            return AikiBeansEndroundStd.PAGE_POSITIONTARGET;
        }
        return AikiBeansEndroundStd.PAGE_EFF;
    }

    public CustList<EndRoundMainElements> getEvts() {
        return evts;
    }
}