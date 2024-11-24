package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectProtectFromTypes {

    private GeneComponentModelLsStrSub<String,StringList> immuAgainstTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        immuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(immuAgainstTypes.geneEnum());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    void buildEntity(EffectProtectFromTypes _edited) {
        _edited.setImmuAgainstTypes(immuAgainstTypes.tryRet());
    }
    void feedForm(EffectProtectFromTypes _edited) {
        immuAgainstTypes.setupValue(_edited.getImmuAgainstTypes());
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public GeneComponentModelLsStrSub<String,StringList> getImmuAgainstTypes() {
        return immuAgainstTypes;
    }
}
