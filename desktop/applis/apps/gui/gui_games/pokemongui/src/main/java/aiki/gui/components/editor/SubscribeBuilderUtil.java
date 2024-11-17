package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class SubscribeBuilderUtil<T> {
    private final SubscribedTranslationMessagesFactoryCore<T> factory;

    public SubscribeBuilderUtil(SubscribedTranslationMessagesFactoryCore<T> _f) {
        this.factory = _f;
    }

    public GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr) {
        return merge(_api, _sub, _excluded, _withEmptyStr, new NullDefValue<T>());
    }
    public GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr, AbsDefValue<T> _d) {
        AbsMap<T, String> sub_ = factory.buildMessages(_api, _sub, _withEmptyStr);
        IdList<T> rem_ = new IdList<T>(sub_.getKeys());
        rem_.removeAllElements(_excluded);
        TreeMap<T, String> treeFilter_ = feedTree(sub_, rem_);
        GeneComponentModelElt<T> sel_ = new GeneComponentModelElt<T>(_api, treeFilter_, _d);
        GeneComponentModelEltEnumSub<T> g_ = new GeneComponentModelEltEnumSub<T>(sel_);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, sel_, _withEmptyStr));
        return g_;
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

    public GeneComponentModelLsStrSub<T> mergeLs(AbstractProgramInfos _api, FacadeGame _sub) {
        AbsMap<T, String> sub_ = factory.buildMessages(_api, _sub);
        TreeMap<T, String> treeFilter_ = feedTree(sub_, sub_.getKeys());
        GeneComponentModelLs<T> sel_ = new GeneComponentModelLs<T>(_api, treeFilter_);
        GeneComponentModelLsStrSub<T> g_ = new GeneComponentModelLsStrSub<T>(sel_);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, sel_, new IdMap<T, String>()));
        return g_;
    }
    public TreeMap<T, String> feedTree(AbsMap<T, String> _messages, CustList<T> _rem) {
        TreeMap<T, String> tree_ = new TreeMap<T, String>(new ComparatorTrWrapper<T>().wrap(_messages));
        for (T s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    public IdList<SubscribedTranslation> feedSub(AbsMap<T, String> _sub, TreeMap<T, String> _treeFilter, GeneComponentModelStr _sel, AbsMap<T, String> _withEmpty) {
        IdList<SubscribedTranslation> subs_ = new IdList<SubscribedTranslation>();
        subs_.add(factory.buildSub(_sub, _withEmpty));
        subs_.add(factory.buildSub(_treeFilter, _withEmpty));
        subs_.add(new SubscribedTranslationSelect(_sel));
        return subs_;
    }
}
