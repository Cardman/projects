package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.util.*;
import code.gui.*;
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
    private GeneComponentModelRate closestFoeDamageRateHp;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageAgainst;
    private CrudGeneFormSimpleFormSub<Statistic,Long> boostStatisOnceKoFoe;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatTargetPos;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatUserNeg;
    private GeneComponentModelEltEnumSub<Statistic> statisAtt;
    private GeneComponentModelEltEnumSub<Statistic> statisDef;
    private AbsPanel form;

    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        power = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_POWER,power.geneEnum()));
        power.addComplete();
        closestFoeDamageRateHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CLOSEST_FOE_DAMAGE_RATE_HP_INTRO,closestFoeDamageRateHp.geneRate()));
        chRate = new GeneComponentModelLong(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CH_RATE_INTRO,chRate.geneLong()));
        statisAtt = ConverterCommonMapUtil.buildStatisticsElt(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_ATTACK_INTRO,statisAtt.geneEnum()));
        statisDef = ConverterCommonMapUtil.buildStatisticsElt(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DEFENSE_INTRO,statisDef.geneEnum()));
        ignVarStatTargetPos = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_IGN_POS_STAT,ignVarStatTargetPos.geneEnum()));
        ignVarStatUserNeg = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_IGN_NEG_STAT,ignVarStatUserNeg.geneEnum()));
        constDamage = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CONST_DAMAGE_INTRO,constDamage));
        randMax = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_RAND_MAX,randMax));
        summingUserTeamOkFighter = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_SUMMING_TEAM,summingUserTeamOkFighter));
        userAttack = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_USER,userAttack));
        targetDefense = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_TARGET,targetDefense));
        chLaw = ConverterCommonMapUtil.buildMcRate(_core.getFrame(), _core.getProgramInfos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EVENT_RATE,MessagesDataEffdamage.M_P_45_RATE);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_CH_LAW,chLaw.getGroup()));
        hitsLaw = ConverterCommonMapUtil.buildMcRate(_core.getFrame(), _core.getProgramInfos(),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_EVENT_NB_HITS,MessagesDataEffdamage.M_P_45_RATE_EVENT);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_HIT_LAW,hitsLaw.getGroup()));
        damageLaw = new CrudGeneFormMonteCarloStrSub(_core);
        damageLaw.initFormKeys(_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DAMAG_LAW,damageLaw.getCrud().getGroup()));
        multDamageAgainst = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamageAgainst.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_core.getFactory().getFactoryCa(),_core.getProgramInfos(),_core.getFacadeGame(), new StringMap<String>()), buildPart(_core, _core.getFactory().getFactoryCa(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_CATEGORY,MessagesDataEffdamage.M_P_45_RATE);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_DAMAGE_MULT_COUNTER,multDamageAgainst.getGroup()));
        boostStatisOnceKoFoe = new CrudGeneFormSimpleFormSub<Statistic,Long>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        boostStatisOnceKoFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())),MessagesPkBean.EFF_DAMAGE,MessagesDataEffdamage.M_P_45_STATISTIC,MessagesDataEffdamage.M_P_45_BOOST);
        selected_.add(line(_core,MessagesDataEffdamage.M_P_45_BOOST_STATIS_ONCE_KO_FOE,boostStatisOnceKoFoe.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_DAMAGE, _key,_input);
    }

    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
    }
    void buildEntity(EffectDamage _edited) {
        _edited.setPower(power.tryRet());
        _edited.setClosestFoeDamageRateHp(closestFoeDamageRateHp.valueRate());
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
        closestFoeDamageRateHp.valueRate(_edited.getClosestFoeDamageRateHp());
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
