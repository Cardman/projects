package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class CrudGeneFormMonteCarloSub<E> {
    private DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> displayEntryCustSub;
    private final CrudGeneFormMonteCarlo<E> law;
    private GeneComponentModelEventEnum<E> compo;

    public CrudGeneFormMonteCarloSub(AbsCommonFrame _f, AbstractProgramInfos _core) {
        this.law = new CrudGeneFormMonteCarlo<E>(_f, _core, null);
    }

    public void initFormKeys(GeneComponentModelEltEnumSub<E> _sub, DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> _subsLoc) {
        displayEntryCustSub = _subsLoc;
        GeneComponentModelEventEnum<E> compo_ = new GeneComponentModelEventEnum<E>(law.getFactory(), _sub);
        law.initFormKeys(_subsLoc.buildDisplay(), compo_, _subsLoc.buildCmp());
        this.compo = compo_;
    }
    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(displayEntryCustSub.buildSub());
        ids_.add(new SubscribedTranslationPkKey<EditedCrudPair<E, LgInt>>(law));
        return ids_;
    }
    public AbsPanel getGroup() {
        return getLaw().getGroup();
    }

    public CustList<EditedCrudPair<E, LgInt>> getList() {
        return getLaw().getList();
    }
    public void setupValues(CustList<EditedCrudPair<E, LgInt>> _ls) {
        getLaw().setupValues(_ls);
    }
    public CrudGeneFormMonteCarlo<E> getLaw() {
        return law;
    }

    public GeneComponentModelEventEnum<E> getCompo() {
        return compo;
    }

}
