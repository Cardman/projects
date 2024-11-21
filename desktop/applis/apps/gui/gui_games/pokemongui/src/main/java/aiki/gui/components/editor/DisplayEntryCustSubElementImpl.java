package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementImpl<K,V> implements DisplayEntryCustSubElement<EditedCrudPair<K, V>> {
    private final AbsMap<K, String> messages;
    private final SubscribedTranslationMessagesFactoryCore<K> content;
    private final AbsMap<K, String> withEmpty;

    public DisplayEntryCustSubElementImpl(SubscribedTranslationMessagesFactoryCore<K> _c, AbstractProgramInfos _core, FacadeGame _fac, AbsMap<K, String> _w) {
        this.messages = _c.buildMessages(_core,_fac,_w);
        content = _c;
        withEmpty = _w;
    }

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<K>(messages,content,withEmpty));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<K, V>> buildDisplay() {
        return new DisplayKeyOnly<K, V>(messages);
    }

    @Override
    public Comparing<EditedCrudPair<K, V>> buildCmp() {
        return new ComparatorTrWrapperPairs<K, V>().wrap(messages);
    }
}
