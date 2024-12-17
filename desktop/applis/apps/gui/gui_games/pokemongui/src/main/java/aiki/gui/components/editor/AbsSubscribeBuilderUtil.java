package aiki.gui.components.editor;

import aiki.comparators.ComparatorTrWrapper;
import aiki.facade.FacadeGame;
import code.gui.AbsDefValue;
import code.gui.GeneComponentModelElt;
import code.gui.GeneComponentModelLs;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public abstract class AbsSubscribeBuilderUtil<T,U> {
    private final SubscribedTranslationMessagesFactoryCoreMessages<T> factory;
    private final IntListConvert<T, U> converter;
    protected AbsSubscribeBuilderUtil(SubscribedTranslationMessagesFactoryCoreMessages<T> _f, IntListConvert<T, U> _conv){
        this.factory = _f;
        converter = _conv;
    }

    public GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr, AbsDefValue<T> _d) {
        AbsMap<T, String> sub_ = factory.getContainer().buildMessages(_api, _sub, _withEmptyStr);
        TreeMap<T, String> treeFilter_ = common(_excluded, sub_);
        GeneComponentModelElt<T> sel_ = new GeneComponentModelElt<T>(_api, treeFilter_, _d);
        GeneComponentModelEltEnumSub<T> g_ = new GeneComponentModelEltEnumSub<T>(sel_);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, _withEmptyStr));
        g_.getSubs().add(new SubscribedTranslationSelect(sel_));
        return g_;
    }

    public GeneComponentModelEltEnumSub<T> mergeQuick(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr, AbsDefValue<T> _d) {
        AbsMap<T, String> sub_ = factory.getContainer().buildMessages(_api, _sub, _withEmptyStr);
        TreeMap<T, String> treeFilter_ = common(_excluded, sub_);
        GeneComponentModelElt<T> sel_ = new GeneComponentModelElt<T>(_api, treeFilter_, _d);
        GeneComponentModelEltEnumSub<T> g_ = new GeneComponentModelEltEnumSub<T>(sel_);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, _withEmptyStr));
        return g_;
    }

    private TreeMap<T, String> common(CustList<T> _excluded, AbsMap<T, String> _sub) {
        AbEqList<T> rem_ = copy(_sub.getKeys());
        rem_.removeAllElements(_excluded);
        return feedTree(_sub, rem_);
    }

    public GeneComponentModelLsStrSub<T,U> mergeLs(AbstractProgramInfos _api, FacadeGame _sub) {
        AbsMap<T, String> sub_ = factory.getContainer().buildMessages(_api, _sub);
        TreeMap<T, String> treeFilter_ = feedTree(sub_, sub_.getKeys());
        GeneComponentModelLs<T> sel_ = new GeneComponentModelLs<T>(_api, treeFilter_);
        GeneComponentModelLsStrSub<T,U> g_ = new GeneComponentModelLsStrSub<T,U>(sel_,converter);
        g_.getSubs().addAllElts(feedSub(sub_, treeFilter_, new IdMap<T, String>()));
        g_.getSubs().add(new SubscribedTranslationSelect(sel_));
        return g_;
    }
    public TreeMap<T, String> feedTree(AbsMap<T, String> _messages, CustList<T> _rem) {
        TreeMap<T, String> tree_ = new TreeMap<T, String>(new ComparatorTrWrapper<T>().wrap(_messages));
        for (T s: _rem) {
            tree_.put(s,_messages.getVal(s));
        }
        return tree_;
    }
    public IdList<SubscribedTranslation> feedSub(AbsMap<T, String> _sub, TreeMap<T, String> _treeFilter, AbsMap<T, String> _withEmpty) {
        IdList<SubscribedTranslation> subs_ = new IdList<SubscribedTranslation>();
        subs_.add(factory.getContainer().buildSub(_sub, _withEmpty));
        subs_.add(factory.getContainer().buildSub(_treeFilter, _withEmpty));
        return subs_;
    }
    public abstract GeneComponentModelEltEnumSub<T> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<T> _excluded, AbsMap<T,String> _withEmptyStr);
    protected abstract AbEqList<T> copy(CustList<T> _ls);
}
