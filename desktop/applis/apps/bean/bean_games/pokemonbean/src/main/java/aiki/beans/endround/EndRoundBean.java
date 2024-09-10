package aiki.beans.endround;

import aiki.beans.CommonBean;
import aiki.db.DataBase;
import aiki.fight.EndRoundMainElements;
import aiki.fight.moves.effects.*;
import code.scripts.confs.PkScriptPages;
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
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_GLOBAL_HTML;
        }
        if (_effect instanceof EffectEndRoundIndividual) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_INDIVIDUAL_HTML;
        }
        if (_effect instanceof EffectEndRoundStatusRelation) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUSRELATION_HTML;
        }
        if (_effect instanceof EffectEndRoundStatus) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_STATUS_HTML;
        }
        if (_effect instanceof EffectEndRoundSingleRelation) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_SINGLERELATION_HTML;
        }
        if (_effect instanceof EffectEndRoundFoe) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_FOE_HTML;
        }
        if (_effect instanceof EffectEndRoundTeam) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_TEAM_HTML;
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_MULTIRELATION_HTML;
        }
        if (_effect instanceof EffectEndRoundPositionRelation) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_POSITIONRELATION_HTML;
        }
        if (_effect instanceof EffectEndRoundPositionTargetRelation) {
            return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_POSITIONTARGET_HTML;
        }
        return PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_EFF_HTML;
    }

    public CustList<EndRoundMainElements> getEvts() {
        return evts;
    }
}