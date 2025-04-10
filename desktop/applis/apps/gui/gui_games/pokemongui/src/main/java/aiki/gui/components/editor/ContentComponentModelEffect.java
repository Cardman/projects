package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffect {
    private GeneComponentModelSubscribeString fail;
    private GeneComponentModelEltEnumSub<TargetChoice> targetChoice;
    private GeneComponentModelSubscribeInts requiredSuccessfulEffects;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        fail = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(line(_core,MessagesDataEff.M_P_36_FAILS,fail.geneEnum()));
        fail.addComplete();
        targetChoice = ConverterCommonMapUtil.buildTargetChoice(_core, _fac, _fact);
        selected_.add(line(_core,MessagesDataEff.M_P_36_TARGETS,targetChoice.geneEnum()));
        requiredSuccessfulEffects = new GeneComponentModelSubscribeInts(_core, _fac, _fact, _f);
        selected_.add(line(_core,MessagesDataEff.M_P_36_NEED_SUCESS_EFF,requiredSuccessfulEffects.geneEnum(0,0)));
        return selected_;
    }

    private AbsCustComponent line(AbstractProgramInfos _core, String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.lineDir(_core,formatTxt(_core,_key),_input);
    }

    private String formatTxt(AbstractProgramInfos _core,String _key) {
        return SubscribedTranslationList.formatTxt(_core, MessagesPkBean.EFF, _key);
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
