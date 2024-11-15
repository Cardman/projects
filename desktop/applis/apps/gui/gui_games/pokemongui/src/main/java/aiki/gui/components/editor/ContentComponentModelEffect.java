package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffect {
    private GeneComponentModelString fail;
    private GeneComponentModelEltEnumSub<TargetChoice> targetChoice;
    private AbsGeneComponentModelSubscribe<Ints> requiredSuccessfulEffects;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        fail = new GeneComponentModelString(_core,new StringList(),new DefValidateText());
        selected_.add(fail.geneString());
        targetChoice = ConverterCommonMapUtil.buildTargetChoice(_core, _fac, _fact);
        selected_.add(targetChoice.geneEnum());
        requiredSuccessfulEffects = new GeneComponentModelSubscribeFactoryInts(_core,_fac, _fact,_f).build();
        selected_.add(requiredSuccessfulEffects.geneEnum(0,0));
        return selected_;
    }
    void buildEntity(Effect _edited) {
        _edited.setFail(fail.valueString());
        _edited.setTargetChoice(targetChoice.tryRet());
        _edited.setRequiredSuccessfulEffects(requiredSuccessfulEffects.tryRet());
    }
    void feedForm(Effect _edited) {
        fail.valueString(_edited.getFail());
        targetChoice.setupValue(_edited.getTargetChoice());
        requiredSuccessfulEffects.setupValue(_edited.getRequiredSuccessfulEffects());
    }

    GeneComponentModelEltEnumSub<TargetChoice> getTargetChoice() {
        return targetChoice;
    }

    GeneComponentModelString getFail() {
        return fail;
    }
}
