package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.enums.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelMoveData extends GeneComponentModelEntity<MoveData> {
    private final GeneComponentModelInt pp;
    private final GeneComponentModelInt priority;
    private final GeneComponentModelInt nbPrepaRound;
    private final GeneComponentModelInt rankIncrementNbRound;
    private GeneComponentModelLsStrSub<String> types;
    private GeneComponentModelLsStrSub<String> boostedTypes;
    private GeneComponentModelLsStrSub<String> achieveDisappearedPkUsingMove;
    private GeneComponentModelLsStrSub<String> deletedStatus;
    private GeneComponentModelLsStrSub<String> requiredStatus;
    private GeneComponentModelString accuracy;
    private AbsCustCheckBox disappearBeforeUse;
    private AbsCustCheckBox rechargeRound;
    private AbsCustCheckBox constUserChoice;
    private AbsCustCheckBox stoppableMoveSolo;
    private AbsCustCheckBox stoppableMoveMulti;
    private AbsCustCheckBox stoppableMovePrio;
    private AbsCustCheckBox secEffectIfNoDamage;
    private AbsCustCheckBox ignVarAccurUserNeg;
    private AbsCustCheckBox ignVarEvasTargetPos;
    private AbsCustCheckBox switchType;
    private final CrudGeneFormMonteCarlo<Rate> repeatRoundLaw;
    private final CrudGeneFormSimpleFormSub<String,String> typesByOwnedItem;
    private final CrudGeneFormSimpleFormSub<String,String> typesByWeather;
    private final CrudGeneFormSimpleFormSub<String,Ints> secEffectsByItem;
    private final CrudGeneFormSimpleElementSub<Effect> effects;
    private GeneComponentModelEltEnumSub<TargetChoice> targetChoice;
    private AbsCustCheckBox damagingMove;
    private GeneComponentModelEltEnumSub<String> category;
    private AbsCustCheckBox direct;
    private AbsCustCheckBox cannotKo;
    private AbsCustCheckBox stoppableMoveKoSingle;
    private AbsCustCheckBox thievableMove;
    private AbsCustCheckBox counterableMove;
    private MoveData element;
    private AbsPanel damagingComponent;
    private AbsPanel statusComponent;


    public GeneComponentModelMoveData(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
        pp = new GeneComponentModelInt(_core);
        priority = new GeneComponentModelInt(_core);
        nbPrepaRound = new GeneComponentModelInt(_core);
        rankIncrementNbRound = new GeneComponentModelInt(_core);
        repeatRoundLaw = new CrudGeneFormMonteCarlo<Rate>(_frame, _core,new ComparingRateKey<LgInt>());
        typesByOwnedItem = new CrudGeneFormSimpleFormSub<String, String>(_core,_facade,_sub,_frame);
        typesByWeather = new CrudGeneFormSimpleFormSub<String, String>(_core,_facade,_sub,_frame);
        secEffectsByItem = new CrudGeneFormSimpleFormSub<String, Ints>(_core,_facade,_sub,_frame);
        effects = new CrudGeneFormSimpleElementSub<Effect>(_core,_facade,_sub,_frame);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryMv factoryMv_ = getSubscribedTranslationList().getFactoryMv();
        buildKey(_select,factoryMv_,factoryMv_.all(getFacade()).getKeys());
        types = ConverterCommonMapUtil.buildTypeList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        boostedTypes = ConverterCommonMapUtil.buildTypeList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        achieveDisappearedPkUsingMove = ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        deletedStatus = ConverterCommonMapUtil.buildStatusList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        requiredStatus = ConverterCommonMapUtil.buildStatusList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        accuracy = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        disappearBeforeUse = getCompoFactory().getCompoFactory().newCustCheckBox();
        rechargeRound = getCompoFactory().getCompoFactory().newCustCheckBox();
        constUserChoice = getCompoFactory().getCompoFactory().newCustCheckBox();
        stoppableMoveSolo = getCompoFactory().getCompoFactory().newCustCheckBox();
        stoppableMoveMulti = getCompoFactory().getCompoFactory().newCustCheckBox();
        stoppableMovePrio = getCompoFactory().getCompoFactory().newCustCheckBox();
        secEffectIfNoDamage = getCompoFactory().getCompoFactory().newCustCheckBox();
        ignVarAccurUserNeg = getCompoFactory().getCompoFactory().newCustCheckBox();
        ignVarEvasTargetPos = getCompoFactory().getCompoFactory().newCustCheckBox();
        switchType = getCompoFactory().getCompoFactory().newCustCheckBox();
        repeatRoundLaw.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(getCompoFactory()), new ComparingRateKey<LgInt>());
        typesByOwnedItem.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactoryIt(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty(" ")), buildPart(getSubscribedTranslationList().getFactoryIt(), ConverterCommonMapUtil.defKeyEmpty("")),buildPart(getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()));
        typesByWeather.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty(" ")), buildPart(getSubscribedTranslationList().getFactoryMv(), ConverterCommonMapUtil.defKeyEmpty("")),buildPart(getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()));
        secEffectsByItem.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Ints>(getSubscribedTranslationList().getFactoryIt(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty(" ")), buildPart(getSubscribedTranslationList().getFactoryIt(), ConverterCommonMapUtil.defKeyEmpty("")),new GeneComponentModelSubscribeFactoryDirect<Ints>(new GeneComponentModelSubscribeInts(getCompoFactory(), getFacade(), getSubscribedTranslationList(), secEffectsByItem.getCommonFrame())));
        effects.initForm(new DisplayEntryCustSubElementEffect<Effect>(),new GeneComponentModelSubscribeFactoryDirect<Effect>(new GeneComponentModelSubscribeEffect(new GeneComponentModelEffect(effects.getCommonFrame(), getCompoFactory(), getFacade(), getSubscribedTranslationList()))));
        targetChoice = ConverterCommonMapUtil.buildTargetChoice(getCompoFactory(), getFacade(),getSubscribedTranslationList());
        category = ConverterCommonMapUtil.buildCatElt(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        damagingComponent = getCompoFactory().getCompoFactory().newPageBox();
        damagingComponent.add(category.geneEnum());
        direct = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(direct);
        cannotKo = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(cannotKo);
        stoppableMoveKoSingle = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(stoppableMoveKoSingle);
        damagingComponent.setVisible(false);
        statusComponent = getCompoFactory().getCompoFactory().newPageBox();
        thievableMove = getCompoFactory().getCompoFactory().newCustCheckBox();
        statusComponent.add(thievableMove);
        counterableMove = getCompoFactory().getCompoFactory().newCustCheckBox();
        statusComponent.add(counterableMove);
        statusComponent.setVisible(false);
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        damagingMove = compoFactory_.newCustCheckBox();
        damagingMove.setSelected(true);
        applyChanges();
        damagingMove.addActionListener(new ChangeMoveKindEvent(this));
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(damagingMove);
        form_.add(pp.geneInt());
        form_.add(priority.geneInt());
        form_.add(nbPrepaRound.geneInt());
        form_.add(rankIncrementNbRound.geneInt());
        form_.add(types.geneEnum());
        form_.add(boostedTypes.geneEnum());
        form_.add(achieveDisappearedPkUsingMove.geneEnum());
        form_.add(deletedStatus.geneEnum());
        form_.add(requiredStatus.geneEnum());
        form_.add(accuracy.geneString());
        form_.add(disappearBeforeUse);
        form_.add(rechargeRound);
        form_.add(constUserChoice);
        form_.add(stoppableMoveSolo);
        form_.add(stoppableMoveMulti);
        form_.add(stoppableMovePrio);
        form_.add(secEffectIfNoDamage);
        form_.add(ignVarAccurUserNeg);
        form_.add(ignVarEvasTargetPos);
        form_.add(switchType);
        form_.add(repeatRoundLaw.getGroup());
        form_.add(typesByOwnedItem.getGroup());
        form_.add(typesByWeather.getGroup());
        form_.add(secEffectsByItem.getGroup());
        form_.add(targetChoice.geneEnum());
        form_.add(effects.getGroup());
        form_.add(damagingComponent);
        form_.add(statusComponent);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    public void applyChanges() {
        damagingComponent.setVisible(damagingMove.isSelected());
        statusComponent.setVisible(!damagingMove.isSelected());
        if (damagingMove.isSelected()) {
            element = Instances.newDamagingMoveData();
        } else {
            element = Instances.newStatusMoveData();
        }
        getFrame().pack();
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(), getFacade(), _facto, _abs);
    }

    @Override
    public EditedCrudPair<String,MoveData> value() {
        MoveData ent_ = element;
        ent_.setPp((short) pp.valueInt());
        ent_.setPriority((byte) priority.valueInt());
        ent_.setNbPrepaRound((short) nbPrepaRound.valueInt());
        ent_.setRankIncrementNbRound((short) rankIncrementNbRound.valueInt());
        ent_.setTypes(new StringList(types.tryRet()));
        ent_.setBoostedTypes(new StringList(boostedTypes.tryRet()));
        ent_.setAchieveDisappearedPkUsingMove(new StringList(achieveDisappearedPkUsingMove.tryRet()));
        ent_.setDeletedStatus(new StringList(deletedStatus.tryRet()));
        ent_.setRequiredStatus(new StringList(requiredStatus.tryRet()));
        ent_.setAccuracy(accuracy.valueString());
        ent_.setDisappearBeforeUse(disappearBeforeUse.isSelected());
        ent_.setRechargeRound(rechargeRound.isSelected());
        ent_.setConstUserChoice(constUserChoice.isSelected());
        ent_.setStoppableMoveSolo(stoppableMoveSolo.isSelected());
        ent_.setStoppableMoveMulti(stoppableMoveMulti.isSelected());
        ent_.setStoppableMovePrio(stoppableMovePrio.isSelected());
        ent_.setSecEffectIfNoDamage(secEffectIfNoDamage.isSelected());
        ent_.setIgnVarAccurUserNeg(ignVarAccurUserNeg.isSelected());
        ent_.setIgnVarEvasTargetPos(ignVarEvasTargetPos.isSelected());
        if (switchType.isSelected()) {
            ent_.setSwitchType(SwitchType.LANCEUR);
        } else {
            ent_.setSwitchType(SwitchType.NOTHING);
        }
        ent_.setRepeatRoundLaw(ConverterCommonMapUtil.buildMonteCarloNumber(repeatRoundLaw.getList()));
        ent_.setTypesByOwnedItem(ConverterCommonMapUtil.buildStringMapString(typesByOwnedItem.getList()));
        ent_.setTypesByWeather(ConverterCommonMapUtil.buildStringMapString(typesByWeather.getList()));
        ent_.setSecEffectsByItem(ConverterCommonMapUtil.buildStringMapInts(secEffectsByItem.getList()));
        ent_.setTargetChoice(targetChoice.tryRet());
        ent_.setEffects(effects.getList());
        if (ent_ instanceof DamagingMoveData) {
            ((DamagingMoveData)ent_).setCategory(category.tryRet());
            ((DamagingMoveData)ent_).setDirect(direct.isSelected());
            ((DamagingMoveData)ent_).setCannotKo(cannotKo.isSelected());
            ((DamagingMoveData)ent_).setStoppableMoveKoSingle(stoppableMoveKoSingle.isSelected());
        }
        if (ent_ instanceof StatusMoveData){
            ((StatusMoveData)ent_).setThievableMove(thievableMove.isSelected());
            ((StatusMoveData)ent_).setCounterableMove(counterableMove.isSelected());
        }
        return new EditedCrudPair<String, MoveData>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,MoveData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        MoveData move_ = _v.getValue();
        element = move_;
        pp.valueInt(move_.getPp());
        priority.valueInt(move_.getPriority());
        nbPrepaRound.valueInt(move_.getNbPrepaRound());
        rankIncrementNbRound.valueInt(move_.getRankIncrementNbRound());
        types.setupValue(move_.getTypes());
        boostedTypes.setupValue(move_.getBoostedTypes());
        achieveDisappearedPkUsingMove.setupValue(move_.getAchieveDisappearedPkUsingMove());
        deletedStatus.setupValue(move_.getDeletedStatus());
        requiredStatus.setupValue(move_.getRequiredStatus());
        accuracy.valueString(move_.getAccuracy());
        disappearBeforeUse.setSelected(move_.getDisappearBeforeUse());
        rechargeRound.setSelected(move_.getRechargeRound());
        constUserChoice.setSelected(move_.getConstUserChoice());
        stoppableMoveSolo.setSelected(move_.getStoppableMoveSolo());
        stoppableMoveMulti.setSelected(move_.getStoppableMoveMulti());
        stoppableMovePrio.setSelected(move_.getStoppableMovePrio());
        secEffectIfNoDamage.setSelected(move_.getSecEffectIfNoDamage());
        ignVarAccurUserNeg.setSelected(move_.getIgnVarAccurUserNeg());
        ignVarEvasTargetPos.setSelected(move_.getIgnVarEvasTargetPos());
        switchType.setSelected(move_.getSwitchType() == SwitchType.LANCEUR);
        repeatRoundLaw.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(move_.getRepeatRoundLaw()));
        typesByOwnedItem.setupValues(new MapToEntriesListUtil<String,String>().build(move_.getTypesByOwnedItem()));
        typesByWeather.setupValues(new MapToEntriesListUtil<String,String>().build(move_.getTypesByWeather()));
        secEffectsByItem.setupValues(new MapToEntriesListUtil<String,Ints>().build(move_.getSecEffectsByItem()));
        targetChoice.setupValue(move_.getTargetChoice());
        effects.setupValues(move_.getEffects());
        if (move_ instanceof DamagingMoveData) {
            category.setupValue(((DamagingMoveData)move_).getCategory());
            direct.setSelected(((DamagingMoveData)move_).isDirect());
            cannotKo.setSelected(((DamagingMoveData)move_).getCannotKo());
            stoppableMoveKoSingle.setSelected(((DamagingMoveData)move_).getStoppableMoveKoSingle());
        }
        if (move_ instanceof StatusMoveData){
            thievableMove.setSelected(((StatusMoveData)move_).getThievableMove());
            counterableMove.setSelected(((StatusMoveData)move_).getCounterableMove());
        }
    }

    public GeneComponentModelInt getPp() {
        return pp;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(types.getSubs());
        ids_.addAllElts(boostedTypes.getSubs());
        ids_.addAllElts(achieveDisappearedPkUsingMove.getSubs());
        ids_.addAllElts(deletedStatus.getSubs());
        ids_.addAllElts(requiredStatus.getSubs());
        ids_.addAllElts(category.getSubs());
        ids_.addAllElts(targetChoice.getSubs());
        ids_.addAllElts(typesByOwnedItem.subscribeButtons());
        ids_.addAllElts(typesByWeather.subscribeButtons());
        ids_.addAllElts(secEffectsByItem.subscribeButtons());
        ids_.addAllElts(getEffects().subscribeButtons());
        return ids_;
    }

    public AbsCustCheckBox getConstUserChoice() {
        return constUserChoice;
    }

    public AbsCustCheckBox getDisappearBeforeUse() {
        return disappearBeforeUse;
    }

    public AbsCustCheckBox getIgnVarAccurUserNeg() {
        return ignVarAccurUserNeg;
    }

    public AbsCustCheckBox getIgnVarEvasTargetPos() {
        return ignVarEvasTargetPos;
    }

    public AbsCustCheckBox getRechargeRound() {
        return rechargeRound;
    }

    public AbsCustCheckBox getSecEffectIfNoDamage() {
        return secEffectIfNoDamage;
    }

    public AbsCustCheckBox getStoppableMoveMulti() {
        return stoppableMoveMulti;
    }

    public AbsCustCheckBox getStoppableMovePrio() {
        return stoppableMovePrio;
    }

    public AbsCustCheckBox getStoppableMoveSolo() {
        return stoppableMoveSolo;
    }

    public AbsCustCheckBox getSwitchType() {
        return switchType;
    }

    public CrudGeneFormSimpleFormSub<String, String> getTypesByOwnedItem() {
        return typesByOwnedItem;
    }

    public CrudGeneFormSimpleFormSub<String, String> getTypesByWeather() {
        return typesByWeather;
    }

    public CrudGeneFormSimpleFormSub<String, Ints> getSecEffectsByItem() {
        return secEffectsByItem;
    }

    public GeneComponentModelLsStrSub<String> getRequiredStatus() {
        return requiredStatus;
    }

    public GeneComponentModelLsStrSub<String> getDeletedStatus() {
        return deletedStatus;
    }

    public AbsCustCheckBox getDamagingMove() {
        return damagingMove;
    }

    public AbsCustCheckBox getDirect() {
        return direct;
    }

    public AbsCustCheckBox getCannotKo() {
        return cannotKo;
    }

    public AbsCustCheckBox getStoppableMoveKoSingle() {
        return stoppableMoveKoSingle;
    }

    public AbsCustCheckBox getThievableMove() {
        return thievableMove;
    }

    public AbsCustCheckBox getCounterableMove() {
        return counterableMove;
    }

    public GeneComponentModelEltEnumSub<String> getCategory() {
        return category;
    }

    public CrudGeneFormSimpleElementSub<Effect> getEffects() {
        return effects;
    }

}
