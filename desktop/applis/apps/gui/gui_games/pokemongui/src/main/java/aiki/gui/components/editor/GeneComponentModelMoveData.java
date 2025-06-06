package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.scripts.pages.aiki.*;

public final class GeneComponentModelMoveData extends GeneComponentModelEntity<MoveData> implements ChangeableFormType {
    private final GeneComponentModelLong pp;
    private final GeneComponentModelLong priority;
    private final GeneComponentModelLong nbPrepaRound;
    private final GeneComponentModelLong rankIncrementNbRound;
    private GeneComponentModelLsStrSub<String,StringList> types;
    private GeneComponentModelLsStrSub<String,StringList> boostedTypes;
    private GeneComponentModelLsStrSub<String,StringList> achieveDisappearedPkUsingMove;
    private GeneComponentModelLsStrSub<String,StringList> deletedStatus;
    private GeneComponentModelLsStrSub<String,StringList> requiredStatus;
    private GeneComponentModelSubscribeString accuracy;
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
    private CrudGeneFormMonteCarlo<Rate> repeatRoundLaw;
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
        pp = new GeneComponentModelLong(_core);
        priority = new GeneComponentModelLong(_core);
        nbPrepaRound = new GeneComponentModelLong(_core);
        rankIncrementNbRound = new GeneComponentModelLong(_core);
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
        accuracy = new GeneComponentModelSubscribeString(getCompoFactory(),getFacade());
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
        repeatRoundLaw = ConverterCommonMapUtil.buildMcRate(getFrame(),getCompoFactory(),MessagesPkBean.MV_DATA,MessagesDataMovesData.M_P_35_NB_ROUNDS,MessagesDataMovesData.M_P_35_RATE);
        typesByOwnedItem.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactoryIt(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getSubscribedTranslationList().getFactoryIt(), ConverterCommonMapUtil.defKeyEmpty()),buildPart(getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()), MessagesPkBean.MV_DATA, MessagesDataMovesData.M_P_35_ITEM, MessagesDataMovesData.M_P_35_TYPE_TITLE);
        typesByWeather.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getSubscribedTranslationList().getFactoryMv(), ConverterCommonMapUtil.defKeyEmpty()),buildPart(getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()), MessagesPkBean.MV_DATA, MessagesDataMovesData.M_P_35_WEATHER, MessagesDataMovesData.M_P_35_TYPE_TITLE);
        secEffectsByItem.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Ints>(getSubscribedTranslationList().getFactoryIt(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getSubscribedTranslationList().getFactoryIt(), ConverterCommonMapUtil.defKeyEmpty()),new GeneComponentModelSubscribeFactoryDirect<Ints>(new GeneComponentModelSubscribeInts(getCompoFactory(), getFacade(), getSubscribedTranslationList(), secEffectsByItem.getCommonFrame())), MessagesPkBean.MV_DATA, MessagesDataMovesData.M_P_35_ITEM, MessagesDataMovesData.M_P_35_EFFECTS_NB);
        effects.initForm(new DisplayEntryCustSubElementEffect<Effect>(),new GeneComponentModelSubscribeFactoryDirect<Effect>(new GeneComponentModelSubscribeEffect(new GeneComponentModelEffect(effects.getCommonFrame(), getCompoFactory(), getFacade(), getSubscribedTranslationList()))));
        targetChoice = ConverterCommonMapUtil.buildTargetChoice(getCompoFactory(), getFacade(),getSubscribedTranslationList());
        category = ConverterCommonMapUtil.buildCatElt(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        damagingComponent = getCompoFactory().getCompoFactory().newPageBox();
        damagingComponent.add(line(MessagesDataMovesData.M_P_35_CAT,category.geneEnum()));
        direct = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(line(MessagesDataMovesData.M_P_35_CAT_DIRECT,direct));
        cannotKo = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(line(MessagesDataMovesData.M_P_35_CANNOT_KO,cannotKo));
        stoppableMoveKoSingle = getCompoFactory().getCompoFactory().newCustCheckBox();
        damagingComponent.add(line(MessagesDataMovesData.M_P_35_STOPPABLE_KO,stoppableMoveKoSingle));
        damagingComponent.setVisible(false);
        statusComponent = getCompoFactory().getCompoFactory().newPageBox();
        thievableMove = getCompoFactory().getCompoFactory().newCustCheckBox();
        statusComponent.add(line(MessagesDataMovesData.M_P_35_THIEVABLE,thievableMove));
        counterableMove = getCompoFactory().getCompoFactory().newCustCheckBox();
        statusComponent.add(line(MessagesDataMovesData.M_P_35_COUNTERABLE,counterableMove));
        statusComponent.setVisible(false);
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        damagingMove = compoFactory_.newCustCheckBox();
        damagingMove.setSelected(true);
        applyChange();
        damagingMove.addActionListener(new ChangeMoveKindEvent(this));
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(line(MessagesDataMovesData.M_P_35_NAME,geneComponentModelSelectKey()));
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(line(MessagesDataMovesData.M_P_35_DAMAGING,damagingMove));
        form_.add(line(MessagesDataMovesData.M_P_35_PP_INTRO,pp.geneLong()));
        form_.add(line(MessagesDataMovesData.M_P_35_PRIORITY_INTRO,priority.geneLong()));
        form_.add(line(MessagesDataMovesData.M_P_35_PREPA_TOUR_CLIMAT_INTRO,nbPrepaRound.geneLong()));
        form_.add(line(MessagesDataMovesData.M_P_35_RANK_INCREMENTING_NB_ROUND,rankIncrementNbRound.geneLong()));
        form_.add(line(MessagesDataMovesData.M_P_35_TYPE_TITLE,types.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_TYPESBOOST,boostedTypes.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_TOUCHE_PK_DISPARUS,achieveDisappearedPkUsingMove.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_DELETED_STATUS,deletedStatus.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_REQUIERED_STATUS,requiredStatus.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_ACCURACY_TITLE,accuracy.geneEnum()));
        accuracy.addComplete();
        form_.add(line(MessagesDataMovesData.M_P_35_DISPARITION_TOUR,disappearBeforeUse));
        form_.add(line(MessagesDataMovesData.M_P_35_RECHARGE,rechargeRound));
        form_.add(line(MessagesDataMovesData.M_P_35_CONST_USER_CHOICE,constUserChoice));
        form_.add(line(MessagesDataMovesData.M_P_35_STOPPABLE_SOLO,stoppableMoveSolo));
        form_.add(line(MessagesDataMovesData.M_P_35_STOPPABLE_MULTI,stoppableMoveMulti));
        form_.add(line(MessagesDataMovesData.M_P_35_STOPPABLE_PRIO,stoppableMovePrio));
        form_.add(line(MessagesDataMovesData.M_P_35_EFFECT_WHILE_NO_DAMAGE,secEffectIfNoDamage));
        form_.add(line(MessagesDataMovesData.M_P_35_IGN_VAR_PREC_LANCEUR_NEG,ignVarAccurUserNeg));
        form_.add(line(MessagesDataMovesData.M_P_35_IGN_VAR_ESQ_CIBLE_POS,ignVarEvasTargetPos));
        form_.add(line(MessagesDataMovesData.M_P_35_SWITCH,switchType));
        form_.add(line(MessagesDataMovesData.M_P_35_REPEATED_ROUND_INTRO,repeatRoundLaw.getGroup()));
        form_.add(line(MessagesDataMovesData.M_P_35_TYPES_ITEM,typesByOwnedItem.getGroup()));
        form_.add(line(MessagesDataMovesData.M_P_35_TYPES_WEATHER,typesByWeather.getGroup()));
        form_.add(line(MessagesDataMovesData.M_P_35_EFFECTS_SEC_DPT,secEffectsByItem.getGroup()));
        form_.add(line(MessagesDataMovesData.M_P_35_TARGETS,targetChoice.geneEnum()));
        form_.add(line(MessagesDataMovesData.M_P_35_EFFECTS_TITLE,effects.getGroup()));
        form_.add(damagingComponent);
        form_.add(statusComponent);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return line(MessagesPkBean.MV_DATA,_key,_input);
    }
    @Override
    public void applyChange() {
        damagingComponent.setVisible(damagingMove.isSelected());
        statusComponent.setVisible(!damagingMove.isSelected());
        if (damagingMove.isSelected()) {
            element = Instances.newDamagingMoveData();
        } else {
            element = Instances.newStatusMoveData();
        }
        getFacade().getData().getMoves().put(DataBase.EMPTY_STRING,element);
        getFrame().pack();
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(), getFacade(), _facto, _abs);
    }

    @Override
    public EditedCrudPair<String,MoveData> value() {
        MoveData ent_ = element;
        ent_.setPp(pp.valueLong());
        ent_.setPriority(priority.valueLong());
        ent_.setNbPrepaRound(nbPrepaRound.valueLong());
        ent_.setRankIncrementNbRound(rankIncrementNbRound.valueLong());
        ent_.setTypes(types.tryRet());
        ent_.setBoostedTypes(boostedTypes.tryRet());
        ent_.setAchieveDisappearedPkUsingMove(new StringList(achieveDisappearedPkUsingMove.tryRet()));
        ent_.setDeletedStatus(deletedStatus.tryRet());
        ent_.setRequiredStatus(requiredStatus.tryRet());
        ent_.setAccuracy(accuracy.tryRet());
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
        MoveData move_ = ConverterCommonMapUtil.copyMoveData(_v.getValue());
        getFacade().getData().getMoves().put(DataBase.EMPTY_STRING, move_);
        element = move_;
        pp.valueLong(move_.getPp());
        priority.valueLong(move_.getPriority());
        nbPrepaRound.valueLong(move_.getNbPrepaRound());
        rankIncrementNbRound.valueLong(move_.getRankIncrementNbRound());
        types.setupValue(move_.getTypes());
        boostedTypes.setupValue(move_.getBoostedTypes());
        achieveDisappearedPkUsingMove.setupValue(move_.getAchieveDisappearedPkUsingMove());
        deletedStatus.setupValue(move_.getDeletedStatus());
        requiredStatus.setupValue(move_.getRequiredStatus());
        accuracy.setupValue(move_.getAccuracy());
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

    public GeneComponentModelLong getPp() {
        return pp;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(accuracy.getSubs());
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

    public GeneComponentModelLsStrSub<String,StringList> getRequiredStatus() {
        return requiredStatus;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDeletedStatus() {
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
