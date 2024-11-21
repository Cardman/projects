package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectUnprotectFromTypes {

    private GeneComponentModelLsStrSub<String> disableImmuAgainstTypes;
    private GeneComponentModelLsStrSub<String> disableImmuFromMoves;
    private GeneComponentModelLsStrSub<String> attackTargetWithTypes;
    private CrudGeneFormSimpleElementSub<TypesDuo> types;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        disableImmuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(disableImmuAgainstTypes.geneEnum());
        disableImmuFromMoves = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(disableImmuFromMoves.geneEnum());
        attackTargetWithTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(attackTargetWithTypes.geneEnum());
        types = new CrudGeneFormSimpleElementSub<TypesDuo>(_core, _fac, _fact, _f);
        types.initForm(new DisplayEntryCustSubElementTypesDuoElt(_core,_fac,_fact),new GeneComponentModelSubscribeFactoryDirect<TypesDuo>(new GeneComponentModelSubscribeTypesDuo(_core,_fac,_fact)));
        selected_.add(types.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    void buildEntity(EffectUnprotectFromTypes _edited) {
        _edited.setDisableImmuAgainstTypes(new StringList(disableImmuAgainstTypes.tryRet()));
        _edited.setDisableImmuFromMoves(new StringList(disableImmuFromMoves.tryRet()));
        _edited.setAttackTargetWithTypes(new StringList(attackTargetWithTypes.tryRet()));
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

    public GeneComponentModelLsStrSub<String> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public GeneComponentModelLsStrSub<String> getAttackTargetWithTypes() {
        return attackTargetWithTypes;
    }

    public GeneComponentModelLsStrSub<String> getDisableImmuFromMoves() {
        return disableImmuFromMoves;
    }
}
