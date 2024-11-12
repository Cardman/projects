package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.status.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelStatus extends GeneComponentModelEntity<Status> {
    private final GeneComponentModelRate catchingRate;

    public GeneComponentModelStatus(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
        catchingRate = new GeneComponentModelRate(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactorySt factorySt_ = getSubscribedTranslationList().getFactorySt();
        buildKey(_select,factorySt_,factorySt_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(catchingRate.geneRate(Rate.zero()));
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,Status> value() {
        Status ent_ = Instances.newStatusSimple();
        ent_.setCatchingRate(catchingRate.valueRate());
        return new EditedCrudPair<String, Status>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,Status> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        Status status_ = _v.getValue();
        catchingRate.valueRate(status_.getCatchingRate());
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        return ids_;
    }

    public GeneComponentModelRate getCatchingRate() {
        return catchingRate;
    }

}
