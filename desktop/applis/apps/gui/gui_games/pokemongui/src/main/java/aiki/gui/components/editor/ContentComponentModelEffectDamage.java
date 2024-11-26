package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectDamage {

    private GeneComponentModelInt chRate;
    private AbsCustCheckBox constDamage;
    private AbsCustCheckBox randMax;
    private AbsCustCheckBox summingUserTeamOkFighter;
    private AbsCustCheckBox userAttack;
    private AbsCustCheckBox targetDefense;
    private CrudGeneFormMonteCarlo<Rate> chLaw;
    private CrudGeneFormMonteCarlo<Rate> hitsLaw;
    private CrudGeneFormMonteCarlo<String> damageLaw;
    private GeneComponentModelText power;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageAgainst;
    private CrudGeneFormSimpleFormSub<Statistic,Byte> boostStatisOnceKoFoe;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatTargetPos;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> ignVarStatUserNeg;
    private GeneComponentModelEltEnumSub<Statistic> statisAtt;
    private GeneComponentModelEltEnumSub<Statistic> statisDef;
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        power = new GeneComponentModelText(_core);
        selected_.add(power.geneString());
        chRate = new GeneComponentModelInt(_core);
        selected_.add(chRate.geneInt());
        statisAtt = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac,_fact);
        selected_.add(statisAtt.geneEnum());
        statisDef = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac,_fact);
        selected_.add(statisDef.geneEnum());
        ignVarStatTargetPos = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(ignVarStatTargetPos.geneEnum());
        ignVarStatUserNeg = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(ignVarStatUserNeg.geneEnum());
        constDamage = _core.getCompoFactory().newCustCheckBox();
        selected_.add(constDamage);
        randMax = _core.getCompoFactory().newCustCheckBox();
        selected_.add(randMax);
        summingUserTeamOkFighter = _core.getCompoFactory().newCustCheckBox();
        selected_.add(summingUserTeamOkFighter);
        userAttack = _core.getCompoFactory().newCustCheckBox();
        selected_.add(userAttack);
        targetDefense = _core.getCompoFactory().newCustCheckBox();
        selected_.add(targetDefense);
        chLaw = ConverterCommonMapUtil.buildMcRate(_f, _core);
        selected_.add(chLaw.getGroup());
        hitsLaw = ConverterCommonMapUtil.buildMcRate(_f, _core);
        selected_.add(hitsLaw.getGroup());
        damageLaw = ConverterCommonMapUtil.buildMcString(_f, _core);
        selected_.add(damageLaw.getGroup());
        multDamageAgainst = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        multDamageAgainst.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_fact.getFactoryCa(),_core,_fac, new StringMap<String>()), buildPart(_core,_fac,_fact.getFactoryCa(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multDamageAgainst.getGroup());
        boostStatisOnceKoFoe = new CrudGeneFormSimpleFormSub<Statistic,Byte>(_core, _fac, _fact, _f);
        boostStatisOnceKoFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_core)));
        selected_.add(boostStatisOnceKoFoe.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectDamage _edited) {
        _edited.setPower(power.valueString());
        _edited.setChRate((byte) chRate.valueInt());
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
        _edited.setDamageLaw(ConverterCommonMapUtil.buildMonteCarloString(damageLaw.getList()));
        _edited.setMultDamageAgainst(ConverterCommonMapUtil.buildStringMapRate(multDamageAgainst.getList()));
        _edited.setBoostStatisOnceKoFoe(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatisOnceKoFoe.getList()));
    }
    void feedForm(EffectDamage _edited) {
        power.valueString(_edited.getPower());
        chRate.valueInt(_edited.getChRate());
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
        boostStatisOnceKoFoe.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_edited.getBoostStatisOnceKoFoe()));
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

    public CrudGeneFormMonteCarlo<String> getDamageLaw() {
        return damageLaw;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultDamageAgainst() {
        return multDamageAgainst;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }
}
