package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffect {
    private GeneComponentModelSubscribeString fail;
    private GeneComponentModelEltEnumSub<TargetChoice> targetChoice;
    private GeneComponentModelSubscribeInts requiredSuccessfulEffects;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        fail = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEff.M_P_36_FAILS,fail.geneEnum()));
        fail.addComplete();
        targetChoice = ConverterCommonMapUtil.buildTargetChoice(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory());
        selected_.add(line(_core,MessagesDataEff.M_P_36_TARGETS,targetChoice.geneEnum()));
        requiredSuccessfulEffects = new GeneComponentModelSubscribeInts(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        selected_.add(line(_core,MessagesDataEff.M_P_36_NEED_SUCESS_EFF,requiredSuccessfulEffects.geneEnum(0,0)));
        return selected_;
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF, _key,_input);
    }

    void buildEntity(Effect _edited) {
        _edited.setFail(fail.tryRet());
        _edited.setTargetChoice(targetChoice.tryRet());
        _edited.setRequiredSuccessfulEffects(requiredSuccessfulEffects.tryRet());
    }
    void feedForm(Effect _edited) {
        fail.setupValue(_edited.getFail());
        targetChoice.setupValue(_edited.getTargetChoice());
        requiredSuccessfulEffects.setupValue(_edited.getRequiredSuccessfulEffects());
    }

    GeneComponentModelEltEnumSub<TargetChoice> getTargetChoice() {
        return targetChoice;
    }

    GeneComponentModelSubscribeString getFail() {
        return fail;
    }
}
