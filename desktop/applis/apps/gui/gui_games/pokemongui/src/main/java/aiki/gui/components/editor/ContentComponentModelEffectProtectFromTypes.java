package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class ContentComponentModelEffectProtectFromTypes {

    private GeneComponentModelLsStrSub<String,StringList> immuAgainstTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        immuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
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
