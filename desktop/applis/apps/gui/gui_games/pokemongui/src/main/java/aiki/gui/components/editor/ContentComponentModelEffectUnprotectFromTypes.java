package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectUnprotectFromTypes {

    private GeneComponentModelLsStrSub<String,StringList> disableImmuAgainstTypes;
    private GeneComponentModelLsStrSub<String,StringList> disableImmuFromMoves;
    private GeneComponentModelLsStrSub<String,StringList> attackTargetWithTypes;
    private CrudGeneFormSimpleElementSub<TypesDuo> types;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        disableImmuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffunprotectfromtypes.M_P_68_DISABLE_IMMU_TYPES,disableImmuAgainstTypes.geneEnum()));
        disableImmuFromMoves = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffunprotectfromtypes.M_P_68_DISABLE_IMMU_FROM_MOVES,disableImmuFromMoves.geneEnum()));
        attackTargetWithTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffunprotectfromtypes.M_P_68_ATTACK_TARGET_TYPES,attackTargetWithTypes.geneEnum()));
        types = new CrudGeneFormSimpleElementSub<TypesDuo>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        types.initForm(new DisplayEntryCustSubElementTypesDuoElt(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory()),new GeneComponentModelSubscribeFactoryDirect<TypesDuo>(new GeneComponentModelSubscribeTypesDuo(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory(),MessagesPkBean.EFF_UNPROTECTFROMTYPES, MessagesDataEffunprotectfromtypes.M_P_68_TYPES_DAMAG,MessagesDataEffunprotectfromtypes.M_P_68_TYPES_PK)));
        selected_.add(line(_core,MessagesDataEffunprotectfromtypes.M_P_68_TYPES,types.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_UNPROTECTFROMTYPES, _key,_input);
    }
    void buildEntity(EffectUnprotectFromTypes _edited) {
        _edited.setDisableImmuAgainstTypes(disableImmuAgainstTypes.tryRet());
        _edited.setDisableImmuFromMoves(disableImmuFromMoves.tryRet());
        _edited.setAttackTargetWithTypes(attackTargetWithTypes.tryRet());
        _edited.setTypes(types.getList());
    }
    void feedForm(EffectUnprotectFromTypes _edited) {
        disableImmuAgainstTypes.setupValue(_edited.getDisableImmuAgainstTypes());
        disableImmuFromMoves.setupValue(_edited.getDisableImmuFromMoves());
        attackTargetWithTypes.setupValue(_edited.getAttackTargetWithTypes());
        types.setupValues(_edited.getTypes());
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    public CrudGeneFormSimpleElementSub<TypesDuo> getTypes() {
        return types;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getAttackTargetWithTypes() {
        return attackTargetWithTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDisableImmuFromMoves() {
        return disableImmuFromMoves;
    }
}
