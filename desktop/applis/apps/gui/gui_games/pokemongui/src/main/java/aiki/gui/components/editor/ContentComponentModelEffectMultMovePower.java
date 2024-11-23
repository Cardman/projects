package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectMultMovePower {

    private CrudGeneFormSimpleFormSub<String, Rate> multMovePowerFctType;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        multMovePowerFctType = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(),_core.getFactory(), _core.getFrame());
        multMovePowerFctType.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactoryTy(), _core.getProgramInfos(), _core.getFacadeGame(),new StringMap<String>()),buildPart(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())));
        selected_.add(multMovePowerFctType.getGroup());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectMultMovePower _edited) {
        _edited.setMultMovePowerFctType(ConverterCommonMapUtil.buildStringMapRate(multMovePowerFctType.getList()));
    }
    void feedForm(EffectMultMovePower _edited) {
        multMovePowerFctType.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultMovePowerFctType()));
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }
}
