package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.montecarlo.*;
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
    private GeneComponentModelString power;
    private CrudGeneFormSimpleForm<String,Rate> multDamageAgainst;
    private CrudGeneFormSimpleForm<Statistic,Byte> boostStatisOnceKoFoe;
    private GeneComponentModelLsStrSub<Statistic> ignVarStatTargetPos;
    private GeneComponentModelLsStrSub<Statistic> ignVarStatUserNeg;
    private GeneComponentModelEltEnum<Statistic> statisAtt;
    private GeneComponentModelEltEnum<Statistic> statisDef;
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        power = new GeneComponentModelString(_core,new StringList(),new DefValidateText());
        selected_.add(power.geneString());
        chRate = new GeneComponentModelInt(_core);
        selected_.add(chRate.geneInt());
        statisAtt = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac);
        selected_.add(statisAtt.geneEnum(Statistic.ATTACK));
        statisDef = ConverterCommonMapUtil.buildStatisticsElt(_core,_fac);
        selected_.add(statisDef.geneEnum(Statistic.DEFENSE));
        ignVarStatTargetPos = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
        selected_.add(ignVarStatTargetPos.geneEnum());
        ignVarStatUserNeg = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
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
        chLaw = buildMcRate(_f, _core);
        selected_.add(chLaw.getGroup());
        hitsLaw = buildMcRate(_f, _core);
        selected_.add(hitsLaw.getGroup());
        damageLaw = buildMcString(_f, _core);
        selected_.add(damageLaw.getGroup());
        multDamageAgainst = new CrudGeneFormSimpleForm<String, Rate>(_core, _fac, _fact, _f);
        multDamageAgainst.initForm();
        multDamageAgainst.initForm(new DisplayEntryCustSubImpl(_fact.getFactoryCa(), new StringMap<String>()),_fact.getFactoryCa().buildMessages(_core,_fac),_core, buildPart(_core,_fac,_fact.getFactoryCa(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryRate(_core),new StringMap<Rate>());
        selected_.add(multDamageAgainst.getGroup());
        boostStatisOnceKoFoe = new CrudGeneFormSimpleForm<Statistic,Byte>(_core, _fac, _fact, _f);
        boostStatisOnceKoFoe.initForm();
        IdMap<Statistic, String> trsStat_ = _fac.getData().getTranslatedStatistics().getVal(_core.getLanguage());
        boostStatisOnceKoFoe.initForm(new DisplayEntryCustNoSubImpl<Statistic>(),trsStat_,_core, new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core,trsStat_), new GeneComponentModelSubscribeFactoryByte(_core),new IdMap<Statistic, Byte>());
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
    private CrudGeneFormMonteCarlo<Rate> buildMcRate(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<Rate> out_ = new CrudGeneFormMonteCarlo<Rate>(_core, new ComparingRateKey<LgInt>());
        out_.setFrame(_f);
        out_.initForm();
        out_.initForm(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(_core),new MonteCarloNumber(),new ComparingRateKey<LgInt>());
        return out_;
    }
    private CrudGeneFormMonteCarlo<String> buildMcString(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<String> out_ = new CrudGeneFormMonteCarlo<String>(_core, new ComparingStringKey<LgInt>());
        out_.setFrame(_f);
        out_.initForm();
        out_.initForm(new StringLgIntDisplayEntryCust(),new GeneComponentModelEventString(_core),new MonteCarloString(),new ComparingStringKey<LgInt>());
        return out_;
    }
    void buildEntity(EffectDamage _edited) {
        _edited.setPower(power.valueString());
        _edited.setChRate((byte) chRate.valueInt());
        _edited.setConstDamage(constDamage.isSelected());
        _edited.setRandMax(randMax.isSelected());
        _edited.setSummingUserTeamOkFighter(summingUserTeamOkFighter.isSelected());
        _edited.setUserAttack(userAttack.isSelected());
        _edited.setTargetDefense(targetDefense.isSelected());
        _edited.setStatisAtt(statisAtt.tryRet(Statistic.ATTACK));
        _edited.setStatisDef(statisDef.tryRet(Statistic.DEFENSE));
        _edited.setIgnVarStatTargetPos(new IdList<Statistic>(ignVarStatTargetPos.tryRet()));
        _edited.setIgnVarStatUserNeg(new IdList<Statistic>(ignVarStatUserNeg.tryRet()));
        _edited.setChLaw(new MonteCarloNumber());
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(chLaw.getList(),_edited.getChLaw());
        _edited.setHitsLaw(new MonteCarloNumber());
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(hitsLaw.getList(),_edited.getHitsLaw());
        _edited.setDamageLaw(new MonteCarloString());
        new MapToEntriesListUtil<String,LgInt>().feedMap(damageLaw.getList(),_edited.getDamageLaw());
        _edited.setMultDamageAgainst(new StringMap<Rate>());
        new MapToEntriesListUtil<String,Rate>().feedMap(multDamageAgainst.getList(),_edited.getMultDamageAgainst());
        _edited.setBoostStatisOnceKoFoe(new IdMap<Statistic, Byte>());
        new MapToEntriesListUtil<Statistic,Byte>().feedMap(boostStatisOnceKoFoe.getList(),_edited.getBoostStatisOnceKoFoe());
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

    public CrudGeneFormMonteCarlo<String> getDamageLaw() {
        return damageLaw;
    }

    public CrudGeneFormSimpleForm<String, Rate> getMultDamageAgainst() {
        return multDamageAgainst;
    }

    public CrudGeneFormSimpleForm<Statistic, Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }
}
