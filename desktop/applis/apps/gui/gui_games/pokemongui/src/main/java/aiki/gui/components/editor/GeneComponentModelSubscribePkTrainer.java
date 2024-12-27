package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribePkTrainer implements AbsGeneComponentModelSubscribe<PkTrainer> {
    private final FormTrainerPk formTrainerPk;
    public GeneComponentModelSubscribePkTrainer(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        formTrainerPk = new FormTrainerPk(_ed, _facade, _sub,_f);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        formTrainerPk.feedForm();
        return formTrainerPk.getForm();
    }

    @Override
    public PkTrainer tryRet() {
        return formTrainerPk.buildEntity();
    }

    @Override
    public void setupValue(PkTrainer _value) {
        formTrainerPk.feedForm(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> subs_ = new IdList<SubscribedTranslation>();
        formTrainerPk.feedSubs(subs_);
        return subs_;
    }

    public FormTrainerPk getFormTrainerPk() {
        return formTrainerPk;
    }
}
