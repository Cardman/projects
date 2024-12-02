package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelSubscribeTypesDuoRate implements GeneComponentModel<EditedCrudPair<TypesDuo, Rate>> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private GeneComponentModelSubscribeTypesDuo key;
    private GeneComponentModelRate eff;

    public GeneComponentModelSubscribeTypesDuoRate(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        api = _fact;
        facadeGame = _facade;
        factory = _sub;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        key = new GeneComponentModelSubscribeTypesDuo(api,facadeGame,factory);
        AbsPanel form_ = api.getCompoFactory().newLineBox();
        form_.add(key.geneEnum(_select,0));
        eff = new GeneComponentModelRate(api);
        form_.add(eff.geneRate());
        return form_;
    }
    @Override
    public EditedCrudPair<TypesDuo, Rate> value() {
        return new EditedCrudPair<TypesDuo, Rate>(key.tryRet(),eff.valueRate());
    }

    @Override
    public void value(EditedCrudPair<TypesDuo, Rate> _value) {
        key.setupValue(_value.getKey());
        eff.valueRate(_value.getValue());
    }

    public GeneComponentModelSubscribeTypesDuo getKey() {
        return key;
    }

    public GeneComponentModelRate getEff() {
        return eff;
    }

    public IdList<SubscribedTranslation> all() {
        return key.getSubs();
    }

}
