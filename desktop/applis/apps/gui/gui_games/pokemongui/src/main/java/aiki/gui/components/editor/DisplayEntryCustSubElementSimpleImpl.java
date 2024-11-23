package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementSimpleImpl<E> implements DisplayEntryCustSubElement<E> {
    private final AbsMap<E, String> messages;
    private final SubscribedTranslationMessagesFactoryCore<E> content;
    private final AbsMap<E, String> withEmpty;

    public DisplayEntryCustSubElementSimpleImpl(SubscribedTranslationMessagesFactoryCore<E> _c, AbstractProgramInfos _core, FacadeGame _fac, AbsMap<E, String> _w) {
        this.messages = _c.buildMessages(_core,_fac,_w);
        content = _c;
        withEmpty = _w;
    }

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<E>(messages,content,withEmpty));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, E> buildDisplay() {
        return new DisplayEntryCustElt<E>(messages);
    }

    @Override
    public Comparing<E> buildCmp() {
        return new ComparatorTrWrapper<E>().wrap(messages);
    }
}
