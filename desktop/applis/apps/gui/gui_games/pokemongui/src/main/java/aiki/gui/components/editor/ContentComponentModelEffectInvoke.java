package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectInvoke {

    private CrudGeneFormSimpleFormSub<EnvironmentType, String> moveFctEnv;
    private CrudGeneFormSimpleFormSub<String, String> invokingMoveByUserTypes;
    private GeneComponentModelRate rateInvokationMove;
    private GeneComponentModelLsStrSub<String,StringList> movesNotToBeInvoked;
    private AbsCustCheckBox invokingMoveButUser;
    private AbsCustCheckBox invokingTargetChosenMove;
    private AbsCustCheckBox invokingUserMoveWhileSleep;
    private AbsCustCheckBox invokingAllyMove;
    private AbsCustCheckBox invokingTargetSuccesfulMove;
    private AbsCustCheckBox invokingSufferedMove;
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        rateInvokationMove = new GeneComponentModelRate(_core);
        selected_.add(rateInvokationMove.geneRate(Rate.zero()));
        movesNotToBeInvoked = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(movesNotToBeInvoked.geneEnum());
        moveFctEnv = new CrudGeneFormSimpleFormSub<EnvironmentType, String>(_core, _fac, _fact, _f);
        moveFctEnv.initFormWithVal(new DisplayEntryCustSubElementImpl<EnvironmentType, String>(_fact.getFactoryEnvironmentType(),_core,_fac, new IdMap<EnvironmentType, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<EnvironmentType>(_core,_fact.getFactoryEnvironmentType(),_fac), buildPart(_core,_fac,_fact.getFactoryMv(),new StringMap<String>()));
        selected_.add(moveFctEnv.getGroup());
        invokingMoveByUserTypes = new CrudGeneFormSimpleFormSub<String, String>(_core, _fac, _fact, _f);
        invokingMoveByUserTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, String>(_fact.getFactoryTy(),_core,_fac,ConverterCommonMapUtil.defKeyEmpty(" ")),buildPart(_core,_fac,_fact.getFactoryTy(),ConverterCommonMapUtil.defKeyEmpty(" ")),buildPart(_core,_fac,_fact.getFactoryMv(),new StringMap<String>()));
        selected_.add(invokingMoveByUserTypes.getGroup());
        invokingMoveButUser = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingMoveButUser);
        invokingTargetChosenMove = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingTargetChosenMove);
        invokingUserMoveWhileSleep = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingUserMoveWhileSleep);
        invokingAllyMove = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingAllyMove);
        invokingTargetSuccesfulMove = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingTargetSuccesfulMove);
        invokingSufferedMove = _core.getCompoFactory().newCustCheckBox();
        selected_.add(invokingSufferedMove);
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    void buildEntity(EffectInvoke _edited) {
        _edited.setRateInvokationMove(rateInvokationMove.valueRate());
        _edited.setMoveFctEnv(ConverterCommonMapUtil.buildIdMapEnvironmentTypeString(moveFctEnv.getList()));
        _edited.setInvokingMoveByUserTypes(ConverterCommonMapUtil.buildStringMapString(invokingMoveByUserTypes.getList()));
        _edited.setMovesNotToBeInvoked(movesNotToBeInvoked.tryRet());
        _edited.setInvokingMoveButUser(invokingMoveButUser.isSelected());
        _edited.setInvokingTargetChosenMove(invokingTargetChosenMove.isSelected());
        _edited.setInvokingUserMoveWhileSleep(invokingUserMoveWhileSleep.isSelected());
        _edited.setInvokingAllyMove(invokingAllyMove.isSelected());
        _edited.setInvokingTargetSuccesfulMove(invokingTargetSuccesfulMove.isSelected());
        _edited.setInvokingSufferedMove(invokingSufferedMove.isSelected());
    }
    void feedForm(EffectInvoke _edited) {
        rateInvokationMove.valueRate(_edited.getRateInvokationMove());
        moveFctEnv.setupValues(new MapToEntriesListUtil<EnvironmentType,String>().build(_edited.getMoveFctEnv()));
        invokingMoveByUserTypes.setupValues(new MapToEntriesListUtil<String,String>().build(_edited.getInvokingMoveByUserTypes()));
        movesNotToBeInvoked.setupValue(_edited.getMovesNotToBeInvoked());
        invokingMoveButUser.setSelected(_edited.getInvokingMoveButUser());
        invokingTargetChosenMove.setSelected(_edited.getInvokingTargetChosenMove());
        invokingUserMoveWhileSleep.setSelected(_edited.getInvokingUserMoveWhileSleep());
        invokingAllyMove.setSelected(_edited.getInvokingAllyMove());
        invokingTargetSuccesfulMove.setSelected(_edited.getInvokingTargetSuccesfulMove());
        invokingSufferedMove.setSelected(_edited.getInvokingSufferedMove());
    }

    public AbsCustCheckBox getInvokingAllyMove() {
        return invokingAllyMove;
    }

    public AbsCustCheckBox getInvokingMoveButUser() {
        return invokingMoveButUser;
    }

    public AbsCustCheckBox getInvokingSufferedMove() {
        return invokingSufferedMove;
    }

    public AbsCustCheckBox getInvokingTargetChosenMove() {
        return invokingTargetChosenMove;
    }

    public AbsCustCheckBox getInvokingTargetSuccesfulMove() {
        return invokingTargetSuccesfulMove;
    }

    public AbsCustCheckBox getInvokingUserMoveWhileSleep() {
        return invokingUserMoveWhileSleep;
    }

    public CrudGeneFormSimpleFormSub<EnvironmentType, String> getMoveFctEnv() {
        return moveFctEnv;
    }

    public CrudGeneFormSimpleFormSub<String, String> getInvokingMoveByUserTypes() {
        return invokingMoveByUserTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getMovesNotToBeInvoked() {
        return movesNotToBeInvoked;
    }
}
