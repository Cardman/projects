package aiki.beans.endround;

import aiki.beans.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.fight.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class EndRoundBean extends CommonBean implements BeanRenderWithAppName {

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
    private CustList<EffectEndRoundBean> effects;

    public EndRoundBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataEndroundEndround.M_P_3_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.ENDROUND_ENDROUND,MessagesDataEndroundEndround.M_P_3_INDEX);
        for (EffectEndRoundBean e: effects) {
            e.setAppName(getAppName());
            e.setBuilder(getBuilder());
            e.buildSub();
        }
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.ENDROUND_ENDROUND).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        evts = data_.getEvtEndRound();
        getForms().getEvts().clear();
        CustList<EffectEndRoundBean> res_ = new CustList<EffectEndRoundBean>();
        effects = res_;
        getForms().setCurrentBeanEnd(res_);
        for (EndRoundMainElements e: evts) {
            getForms().getEvtsGroups().add(new CustList<TranslatedKey>());
            feedEvts(getFacade(), e);
        }
        int len_ = evts.size();
        for (int i = 0; i < len_; i++) {
            build(res_,evts,i);
        }
    }

    public CustList<EffectEndRoundBean> getEffects() {
        return effects;
    }

    private void feedEvts(FacadeGame _data, EndRoundMainElements _e) {
        if (_e.getEndRoundType() == EndTurnType.ATTAQUE) {
            getForms().getEvts().add(buildMv(_data, _e.getElement()));
        } else if (_e.getEndRoundType() == EndTurnType.CAPACITE) {
            getForms().getEvts().add(buildAb(_data, _e.getElement()));
        } else if (_e.getEndRoundType() == EndTurnType.OBJET) {
            getForms().getEvts().add(buildIt(_data, _e.getElement()));
        } else if (_e.getEndRoundType() == EndTurnType.STATUT) {
            getForms().getEvts().add(buildSt(_data, _e.getElement()));
        } else {
            StringList moves_ = StringUtil.splitStrings(_e.getElement(), DataBase.SEPARATOR_MOVES);
            for (String m: moves_) {
                getForms().getEvtsGroups().last().add(buildMv(_data,m));
            }
            getForms().getEvts().add(buildTy(_data,""));
        }
    }

    private void build(CustList<EffectEndRoundBean> _curr, CustList<EndRoundMainElements> _effs, int _i) {
        EffectEndRound e_ = _effs.get(_i).getEff();
        if (e_ instanceof EffectEndRoundGlobal) {
            build(_curr, _i, new EffectEndRoundGlobalBean());
        } else if (e_ instanceof EffectEndRoundIndividual) {
            build(_curr, _i, new EffectEndRoundIndividualBean());
        } else if (e_ instanceof EffectEndRoundStatusRelation) {
            build(_curr, _i, new EffectEndRoundStatusRelationBean());
        } else if (e_ instanceof EffectEndRoundStatus) {
            build(_curr, _i, new EffectEndRoundStatusBean());
        } else if (e_ instanceof EffectEndRoundSingleRelation) {
            build(_curr, _i, new EffectEndRoundSingleRelationBean());
        } else if (e_ instanceof EffectEndRoundFoe) {
            build(_curr, _i, new EffectEndRoundFoeBean());
        } else if (e_ instanceof EffectEndRoundTeam) {
            build(_curr, _i, new EffectEndRoundTeamBean());
        } else if (e_ instanceof EffectEndRoundMultiRelation) {
            build(_curr, _i, new EffectEndRoundMultiRelationBean());
        } else if (e_ instanceof EffectEndRoundPositionRelation) {
            build(_curr, _i, new EffectEndRoundPositionRelationBean());
        } else if (e_ instanceof EffectEndRoundPositionTargetRelation) {
            build(_curr, _i, new EffectEndRoundPositionTargetBean());
        } else {
            build(_curr, _i, new EffectEndRoundBean());
        }
    }

    private void build(CustList<EffectEndRoundBean> _feed, int _i, EffectEndRoundBean _b) {
        _b.setAppName(getAppName());
        _b.setDataBase(db());
        _b.setForms(new StringMapObject());
        _b.getForms().putAllMap(getForms());
        _b.getForms().setCurrentBeanEnd(_feed);
        _b.setLanguage(getLanguage());
        _b.setBuilder(getBuilder());
        _b.setIndex(_i);
        _b.beforeDisplaying();
        _feed.add(_b);
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