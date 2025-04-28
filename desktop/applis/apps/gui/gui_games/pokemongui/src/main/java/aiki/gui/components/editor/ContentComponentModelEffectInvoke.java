package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
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

    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateInvokationMove = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_RATE_INVOKE_MOVE_INTRO,rateInvokationMove.geneRate()));
        movesNotToBeInvoked = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_MOVES_NOT_INVOKED,movesNotToBeInvoked.geneEnum()));
        moveFctEnv = new CrudGeneFormSimpleFormSub<EnvironmentType, String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        moveFctEnv.initFormWithVal(new DisplayEntryCustSubElementImpl<EnvironmentType, String>(_core.getFactory().getFactoryEnvironmentType(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<EnvironmentType, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<EnvironmentType>(_core.getProgramInfos(),_core.getFactory().getFactoryEnvironmentType(),_core.getFacadeGame()), buildPart(_core, _core.getFactory().getFactoryMv(),new StringMap<String>()),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_ENV_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_MOVE_FCT_ENV,moveFctEnv.getGroup()));
        invokingMoveByUserTypes = new CrudGeneFormSimpleFormSub<String, String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        invokingMoveByUserTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, String>(_core.getFactory().getFactoryTy(),_core.getProgramInfos(),_core.getFacadeGame(),ConverterCommonMapUtil.defKeyEmpty(" ")),buildPart(_core, _core.getFactory().getFactoryTy(),ConverterCommonMapUtil.defKeyEmpty(" ")),buildPart(_core, _core.getFactory().getFactoryMv(),new StringMap<String>()),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_OWNED_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_TYPE,invokingMoveByUserTypes.getGroup()));
        invokingMoveButUser = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_BUT_USER,invokingMoveButUser));
        invokingTargetChosenMove = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_TARGET_CHOSEN_MOVE,invokingTargetChosenMove));
        invokingUserMoveWhileSleep = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP,invokingUserMoveWhileSleep));
        invokingAllyMove = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_PART,invokingAllyMove));
        invokingTargetSuccesfulMove = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_SUCCESS_TARGET,invokingTargetSuccesfulMove));
        invokingSufferedMove = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffinvoke.M_P_50_INVOKE_SUFFERED_MOVE,invokingSufferedMove));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_INVOKE, _key,_input);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
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
