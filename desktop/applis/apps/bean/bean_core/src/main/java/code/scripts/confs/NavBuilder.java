package code.scripts.confs;

import code.util.*;

public final class NavBuilder {
    private NavBuilder() {}
//    public static void buildNav(StringMap<StringMap<String>> _nav,String _key, EntryNav... _redirects) {
//        StringMap<String> n_ = new StringMap<String>(new CollCapacity(_redirects.length));
//        for (EntryNav e: _redirects) {
//            n_.addEntry(e.getNavCase(),e.getRedirect());
//        }
//        _nav.addEntry(BeanNatCommonLgNames.methName(_key),n_);
//    }
    public static void buildBeans(StringMap<String> _nav, String _key, String _value) {
        _nav.addEntry(_key,_value);
    }
}
