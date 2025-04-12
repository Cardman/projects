package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectDamage {

    private GeneComponentModelLong chRate;
    private AbsCustCheckBox constDamage;
    private AbsCustCheckBox randMax;
    private AbsCustCheckBox summingUserTeamOkFighter;
    private AbsCustCheckBox userAttack;
    private AbsCustCheckBox targetDefense;
    private CrudGeneFormMonteCarlo<Rate> chLaw;
    private CrudGeneFormMonteCarlo<Rate> hitsLaw;
    private CrudGeneFormMonteCarloStrSub damageLaw;
    private GeneComponentModelSubscribeString power;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageAgainst;
    private CrudGeneFormSimpleFormSub<Statistic,Long> boostStatisOnceKoFoe;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatTargetPos;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatUserNeg;
    private GeneComponentModelEltEnumSub<Statistic> statisAtt;
    private GeneComponentModelEltEnumSub<Statistic> statisDef;
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        power = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_POWER,power.geneEnum()));
        power.addComplete();
        chRate = new GeneComponentModelLong(_core);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CH_RATE_INTRO,chRate.geneLong()));
        statisAtt = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac,_fact);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_ATTACK_INTRO,statisAtt.geneEnum()));
        statisDef = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac,_fact);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DEFENSE_INTRO,statisDef.geneEnum()));
        ignVarStatTargetPos = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_IGN_POS_STAT,ignVarStatTargetPos.geneEnum()));
        ignVarStatUserNeg = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT,ignVarStatUserNeg.geneEnum()));
        constDamage = _core.getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CONST_DAMAGE_INTRO,constDamage));
        randMax = _core.getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_RAND_MAX,randMax));
        summingUserTeamOkFighter = _core.getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_SUMMING_TEAM,summingUserTeamOkFighter));
        userAttack = _core.getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_USER,userAttack));
        targetDefense = _core.getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_TARGET,targetDefense));
        chLaw = ConverterCommonMapUtil.buildMcRate(_f, _core,MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CH_LAW,chLaw.getGroup()));
        hitsLaw = ConverterCommonMapUtil.buildMcRate(_f, _core,MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EVENT_NB_HITS,MessagesDataEffdamage.M_P_45_RATE_EVENT);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_HIT_LAW,hitsLaw.getGroup()));
        damageLaw = new CrudGeneFormMonteCarloStrSub(_core, _fac, _fact, _f);
        damageLaw.initFormKeys(_fac);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DAMAG_LAW,damageLaw.getCrud().getGroup()));
        multDamageAgainst = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        multDamageAgainst.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_fact.getFactoryCa(),_core,_fac, new StringMap<String>()), buildPart(_core,_fac,_fact.getFactoryCa(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CATEGORY,MessagesDataEffdamage.M_P_45_RATE);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DAMAGE_MULT_COUNTER,multDamageAgainst.getGroup()));
        boostStatisOnceKoFoe = new CrudGeneFormSimpleFormSub<Statistic,Long>(_core, _fac, _fact, _f);
        boostStatisOnceKoFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core)),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_STATISTIC,MessagesDataEffdamage.M_P_45_BOOST);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_BOOST_STATIS_ONCE_KO_FOE,boostStatisOnceKoFoe.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbstractProgramInfos _core, String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.lineDir(_core,formatTxt(_core,_key),_input);
    }

    private String formatTxt(AbstractProgramInfos _core,String _key) {
        return SubscribedTranslationList.formatTxt(_core, MessagesPkBean.EFF_DAMAGE, _key);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectDamage _edited) {
        _edited.setPower(power.tryRet());
        _edited.setChRate(chRate.valueLong());
        _edited.setConstDamage(constDamage.isSelected());
        _edited.setRandMax(randMax.isSelected());
        _edited.setSummingUserTeamOkFighter(summingUserTeamOkFighter.isSelected());
        _edited.setUserAttack(userAttack.isSelected());
        _edited.setTargetDefense(targetDefense.isSelected());
        _edited.setStatisAtt(statisAtt.tryRet());
        _edited.setStatisDef(statisDef.tryRet());
        _edited.setIgnVarStatTargetPos(ignVarStatTargetPos.tryRet());
        _edited.setIgnVarStatUserNeg(ignVarStatUserNeg.tryRet());
        _edited.setChLaw(ConverterCommonMapUtil.buildMonteCarloNumber(chLaw.getList()));
        _edited.setHitsLaw(ConverterCommonMapUtil.buildMonteCarloNumber(hitsLaw.getList()));
        _edited.setDamageLaw(PatchPkLawStringUtil.patch(ConverterCommonMapUtil.buildMonteCarloString(damageLaw.getCrud().getList())));
        _edited.setMultDamageAgainst(ConverterCommonMapUtil.buildStringMapRate(multDamageAgainst.getList()));
        _edited.setBoostStatisOnceKoFoe(ConverterCommonMapUtil.buildIdMapStatisticInteger(boostStatisOnceKoFoe.getList()));
    }
    void feedForm(EffectDamage _edited) {
        power.setupValue(_edited.getPower());
        chRate.valueLong(_edited.getChRate());
        constDamage.setSelected(_edited.getConstDamage());
        randMax.setSelected(_edited.getRandMax());
        summingUserTeamOkFighter.setSelected(_edited.getSummingUserTeamOkFighter());
        userAttack.setSelected(_edited.isUserAttack());
        targetDefense.setSelected(_edited.isTargetDefense());
        statisAtt.setupValue(_edited.getStatisAtt());
        statisDef.setupValue(_edited.getStatisDef());
        ignVarStatTargetPos.setupValue(_edited.getIgnVarStatTargetPos());
        ignVarStatUserNeg.setupValue(_edited.getIgnVarStatUserNeg());
        chLaw.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(_edited.getChLaw()));
        hitsLaw.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(_edited.getHitsLaw()));
        damageLaw.setupValues(new MapToEntriesListUtil<String,LgInt>().build(_edited.getDamageLaw()));
        multDamageAgainst.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultDamageAgainst()));
        boostStatisOnceKoFoe.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_edited.getBoostStatisOnceKoFoe()));
    }

    public AbsCustCheckBox getRandMax() {
        return randMax;
    }

    public AbsCustCheckBox getConstDamage() {
        return constDamage;
    }

    public AbsCustCheckBox getSummingUserTeamOkFighter() {
        return summingUserTeamOkFighter;
    }

    public AbsCustCheckBox getTargetDefense() {
        return targetDefense;
    }

    public AbsCustCheckBox getUserAttack() {
        return userAttack;
    }

    public GeneComponentModelSubscribeString getPower() {
        return power;
    }

    public GeneComponentModelEltEnumSub<Statistic> getStatisAtt() {
        return statisAtt;
    }

    public GeneComponentModelEltEnumSub<Statistic> getStatisDef() {
        return statisDef;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public CrudGeneFormMonteCarloStrSub getDamageLaw() {
        return damageLaw;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultDamageAgainst() {
        return multDamageAgainst;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Long> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }
}
