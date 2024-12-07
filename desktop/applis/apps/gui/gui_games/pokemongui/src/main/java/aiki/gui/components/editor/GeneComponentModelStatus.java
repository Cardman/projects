package aiki.gui.components.editor;

import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelStatus extends GeneComponentModelEntity<Status> implements ChangeableFormType {
    private final GeneComponentModelRate power;
    private GeneComponentModelEltEnumSub<Statistic> attack;
    private GeneComponentModelEltEnumSub<Statistic> defense;
    private CrudGeneFormMonteCarlo<BoolVal> lawForUsingAMove;
    private CrudGeneFormMonteCarlo<Rate> lawForUsingAMoveNbRound;
    private CrudGeneFormMonteCarlo<BoolVal> lawForUsingAMoveIfFoe;
    private CrudGeneFormMonteCarlo<BoolVal> lawForFullHealIfMove;
    private CrudGeneFormSimpleElementSub<EffectEndRoundStatus> effectEndRound;
    private CrudGeneFormSimpleElementSub<EffectPartnerStatus> effectsPartner;
    private CrudGeneFormSimpleFormSub<Statistic, Rate> multStat;
    private GeneComponentModelSubscribeString fail;
    private final GeneComponentModelRate catchingRate;
    private final GeneComponentModelInt incrementEndRound;
    private AbsCustCheckBox disabledEffIfSwitch;
    private AbsCustCheckBox incrementingEndRound;
    private AbsCustCheckBox statusType;
    private AbsPanel auto;
    private AbsPanel begin;
    private GeneComponentModelElt<String> effectKind;
    private Status edited;

    public GeneComponentModelStatus(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
        power = new GeneComponentModelRate(_core);
        catchingRate = new GeneComponentModelRate(_core);
        incrementEndRound = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        effectKind = new GeneComponentModelElt<String>(getCompoFactory(), MessagesPkEditor.getMessagesEditorSelectStatusTr(MessagesPkEditor.getAppliTr(getCompoFactory().currentLg())).getMapping(), new EmptyDefValue());
        SubscribedTranslationMessagesFactorySt factorySt_ = getSubscribedTranslationList().getFactorySt();
        buildKey(_select,factorySt_,factorySt_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());

        auto = compoFactory_.newLineBox();
        attack = ConverterCommonMapUtil.buildStatisticsElt(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        auto.add(attack.geneEnum());
        defense = ConverterCommonMapUtil.buildStatisticsElt(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        auto.add(defense.geneEnum());
        auto.add(power.geneRate());
        auto.setVisible(false);
        form_.add(auto);

        begin = compoFactory_.newLineBox();
        lawForUsingAMove = ConverterCommonMapUtil.buildMcBool(getFrame(),getCompoFactory());
        begin.add(lawForUsingAMove.getGroup());
        lawForUsingAMoveIfFoe = ConverterCommonMapUtil.buildMcBool(getFrame(),getCompoFactory());
        begin.add(lawForUsingAMoveIfFoe.getGroup());
        lawForFullHealIfMove = ConverterCommonMapUtil.buildMcBool(getFrame(),getCompoFactory());
        begin.add(lawForFullHealIfMove.getGroup());
        lawForUsingAMoveNbRound = ConverterCommonMapUtil.buildMcRate(getFrame(),getCompoFactory());
        begin.add(lawForUsingAMoveNbRound.getGroup());
        begin.setVisible(false);
        form_.add(begin);

        effectEndRound=new CrudGeneFormSimpleElementSub<EffectEndRoundStatus>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRoundStatus>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRoundStatus>(new GeneComponentModelSubscribeEffectEndRoundStatus(new GeneComponentModelEffectEndRoundStatus(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectEndRound.getGroup());
        effectsPartner=new CrudGeneFormSimpleElementSub<EffectPartnerStatus>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectsPartner.initForm(new DisplayEntryCustSubElementEffect<EffectPartnerStatus>(),new GeneComponentModelSubscribeFactoryDirect<EffectPartnerStatus>(new GeneComponentModelSubscribeEffectPartnerStatus(getCompoFactory())));
        form_.add(effectsPartner.getGroup());
        multStat=new CrudGeneFormSimpleFormSub<Statistic,Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())));
        form_.add(multStat.getGroup());
        fail = new GeneComponentModelSubscribeString(getCompoFactory(),getFacade());
        form_.add(fail.geneEnum());
        fail.addComplete();
        form_.add(catchingRate.geneRate());
        form_.add(incrementEndRound.geneInt());
        disabledEffIfSwitch = compoFactory_.newCustCheckBox();
        form_.add(disabledEffIfSwitch);
        incrementingEndRound = compoFactory_.newCustCheckBox();
        form_.add(incrementingEndRound);
        statusType = compoFactory_.newCustCheckBox();
        form_.add(statusType);
        sc_.setViewportView(form_);
        page_.add(sc_);
        getEffectKind().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.STAT_AUTO);
        return page_;
    }

    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        display(eff_);
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.STAT_AUTO)) {
            edited = Instances.newStatusBeginRoundAutoDamage();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.STAT_BEGIN)) {
            edited = Instances.newStatusBeginRoundSimple();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.STAT_SIMPLE)) {
            edited = Instances.newStatusSimple();
        }
        getFacade().getData().getStatus().put(DataBase.EMPTY_STRING,edited);
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    @Override
    public EditedCrudPair<String,Status> value() {
        edited.setEffectEndRound(effectEndRound.getList());
        edited.setEffectsPartner(effectsPartner.getList());
        edited.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticRate(multStat.getList()));
        edited.setFail(fail.tryRet());
        edited.setCatchingRate(catchingRate.valueRate());
        edited.setIncrementEndRound(incrementEndRound.valueInt());
        edited.setDisabledEffIfSwitch(disabledEffIfSwitch.isSelected());
        edited.setIncrementingEndRound(incrementingEndRound.isSelected());
        if (statusType.isSelected()) {
            edited.setStatusType(StatusType.INDIVIDUEL);
        } else {
            edited.setStatusType(StatusType.RELATION_UNIQUE);
        }
        if (edited instanceof StatusBeginRound) {
            ((StatusBeginRound)edited).setLawForUsingAMove(ConverterCommonMapUtil.buildMonteCarloBool(lawForUsingAMove.getList()));
            ((StatusBeginRound)edited).setLawForUsingAMoveIfFoe(ConverterCommonMapUtil.buildMonteCarloBool(lawForUsingAMoveIfFoe.getList()));
            ((StatusBeginRound)edited).setLawForFullHealIfMove(ConverterCommonMapUtil.buildMonteCarloBool(lawForFullHealIfMove.getList()));
            ((StatusBeginRound)edited).setLawForUsingAMoveNbRound(ConverterCommonMapUtil.buildMonteCarloNumber(lawForUsingAMoveNbRound.getList()));
        }
        if (edited instanceof StatusBeginRoundAutoDamage) {
            ((StatusBeginRoundAutoDamage)edited).setAttack(attack.tryRet());
            ((StatusBeginRoundAutoDamage)edited).setDefense(defense.tryRet());
            ((StatusBeginRoundAutoDamage)edited).setPower(power.valueRate());
        }
        return new EditedCrudPair<String, Status>(getGeneComponentModelSelectKey().tryRet(),edited);
    }

    @Override
    public void value(EditedCrudPair<String,Status> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        Status status_ = ConverterCommonMapUtil.copyStatus(_v.getValue());
        getFacade().getData().getStatus().put(DataBase.EMPTY_STRING,status_);
        effectEndRound.setupValues(status_.getEffectEndRound());
        effectsPartner.setupValues(status_.getEffectsPartner());
        multStat.setupValues(new MapToEntriesListUtil<Statistic,Rate>().build(status_.getMultStat()));
        catchingRate.valueRate(status_.getCatchingRate());
        incrementEndRound.valueInt(status_.getIncrementEndRound());
        fail.setupValue(status_.getFail());
        disabledEffIfSwitch.setSelected(status_.getDisabledEffIfSwitch());
        incrementingEndRound.setSelected(status_.getIncrementingEndRound());
        statusType.setSelected(status_.getStatusType() == StatusType.INDIVIDUEL);
        if (status_ instanceof StatusBeginRound) {
            lawForUsingAMove.setupValues(new MapToEntriesListUtil<BoolVal,LgInt>().build(((StatusBeginRound)status_).getLawForUsingAMove()));
            lawForUsingAMoveIfFoe.setupValues(new MapToEntriesListUtil<BoolVal,LgInt>().build(((StatusBeginRound)status_).getLawForUsingAMoveIfFoe()));
            lawForFullHealIfMove.setupValues(new MapToEntriesListUtil<BoolVal,LgInt>().build(((StatusBeginRound)status_).getLawForFullHealIfMove()));
            lawForUsingAMoveNbRound.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(((StatusBeginRound)status_).getLawForUsingAMoveNbRound()));
        }
        if (status_ instanceof StatusBeginRoundAutoDamage) {
            attack.setupValue(((StatusBeginRoundAutoDamage)status_).getAttack());
            defense.setupValue(((StatusBeginRoundAutoDamage)status_).getDefense());
            power.valueRate(((StatusBeginRoundAutoDamage)status_).getPower());
        }
        edited = status_;
        String type_;
        if (edited instanceof StatusBeginRoundAutoDamage) {
            type_ = MessagesEditorSelect.STAT_AUTO;
        } else if (edited instanceof StatusBeginRoundSimple) {
            type_ = MessagesEditorSelect.STAT_BEGIN;
        } else {
            type_ = MessagesEditorSelect.STAT_SIMPLE;
        }
        display(type_);
        getEffectKind().setupValue(type_);
        getEffectKind().getSelect().repaint();
    }

    private void display(String _eff) {
        begin.setVisible(StringUtil.quickEq(_eff,MessagesEditorSelect.STAT_BEGIN) || StringUtil.quickEq(_eff,MessagesEditorSelect.STAT_AUTO));
        auto.setVisible(StringUtil.quickEq(_eff,MessagesEditorSelect.STAT_AUTO));
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(attack.getSubs());
        ids_.addAllElts(defense.getSubs());
        ids_.addAllElts(fail.getSubs());
        return ids_;
    }

    public GeneComponentModelElt<String> getEffectKind() {
        return effectKind;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRoundStatus> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectPartnerStatus> getEffectsPartner() {
        return effectsPartner;
    }

    public AbsCustCheckBox getDisabledEffIfSwitch() {
        return disabledEffIfSwitch;
    }

    public AbsCustCheckBox getIncrementingEndRound() {
        return incrementingEndRound;
    }

    public AbsCustCheckBox getStatusType() {
        return statusType;
    }

    public GeneComponentModelRate getCatchingRate() {
        return catchingRate;
    }

}
