package aiki.gui.components.editor;

import aiki.comparators.ComparatorTrWrapper;
import aiki.facade.FacadeGame;
import code.gui.AbsDefValue;
import code.gui.GeneComponentModelElt;
import code.gui.GeneComponentModelLs;
import code.gui.GeneComponentModelStr;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public abstract class AbsSubscribeBuilderUtil<T> {
    private final SubscribedTranslationMessagesFactoryCore<T> factory;
    protected AbsSubscribeBuilderUtil(SubscribedTranslationMessagesFactoryCore<T> _f){
        this.factory = _f;
    }

    public GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr, AbsDefValue<T> _d) {
        AbsMap<T, String> sub_ = factory.buildMessages(_api, _sub, _withEmptyStr);
        AbEqList<T> rem_ = copy(sub_.getKeys());
        rem_.removeAllElements(_excluded);
        TreeMap<T, String> treeFilter_ = feedTree(sub_, rem_);
        GeneComponentModelElt<T> sel_ = new GeneComponentModelElt<T>(_api, treeFilter_, _d);
        GeneComponentModelEltEnumSub<T> g_ = new GeneComponentModelEltEnumSub<T>(sel_);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, sel_, _withEmptyStr));
        return g_;
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
    public abstract GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr);
    protected abstract AbEqList<T> copy(CustList<T> _ls);
}
