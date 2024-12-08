package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class SubscribeBuilderUtil<T> extends AbsSubscribeBuilderUtil<T,IdList<T>> {

    public SubscribeBuilderUtil(SubscribedTranslationMessagesFactoryCoreMessages<T> _f) {
        super(_f, new IntListConvertId<T>());
    }

    @Override
    public GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr) {
        return merge(_api, _sub, _excluded, _withEmptyStr, new NullDefValue<T>());
    }

    @Override
    protected AbEqList<T> copy(CustList<T> _ls) {
        return new IdList<T>(_ls);
    }

    public IdMap<T,StringMap<String>> toEntityLg(StringMap<IdMap<T,String>> _map) {
        IdMap<T,StringMap<String>> inv_ = new IdMap<T,StringMap<String>>();
        IdList<T> next_ = new IdList<T>();
        for (EntryCust<String,IdMap<T,String>> e: _map.entryList()) {
            next_.addAllElts(e.getValue().getKeys());
        }
        next_.removeDuplicates();
        for (T e: next_) {
            StringMap<String> trs_ = new StringMap<String>();
            for (EntryCust<String,IdMap<T,String>> l: _map.entryList()) {
                trs_.addEntry(l.getKey(), StringUtil.nullToEmpty(l.getValue().getVal(e)));
            }
            inv_.addEntry(e, trs_);
        }
        return inv_;
    }

}
