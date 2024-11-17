package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class CrudGeneFormMonteCarloSub<E> {
    private final CrudGeneFormMonteCarlo<E> law;
    private final GeneComponentModelEventEnum<E> compo;
    private final DisplayEntryCustSub<E> sub;
    private final AbsMap<E, String> messages;

    public CrudGeneFormMonteCarloSub(AbsCommonFrame _f, AbstractProgramInfos _core, GeneComponentModelEltEnumSub<E> _sub, AbsMap<E, String> _mess, DisplayEntryCustSub<E> _subsLoc) {
        sub = _subsLoc;
        messages = _mess;
        ComparingEnumKey<E, LgInt> cmp_ = new ComparingEnumKey<E, LgInt>(_mess);
        this.law = new CrudGeneFormMonteCarlo<E>(_core, cmp_);
        law.setFrame(_f);
        law.initForm();
        GeneComponentModelEventEnum<E> compo_ = new GeneComponentModelEventEnum<E>(_core, _sub);
        law.initFormKeys(new StatisticLgIntDisplayEntryCust<E>(_mess), compo_, cmp_);
        this.compo = compo_;
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(sub.buildSub(messages));
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
