package code.scripts.confs;

import code.formathtml.structs.BeanInfo;
import code.util.CollCapacity;
import code.util.StringMap;

public final class NavBuilder {
    private NavBuilder() {}
    public static void buildNav(StringMap<StringMap<String>> _nav,String _key, EntryNav... _redirects) {
        StringMap<String> n_ = new StringMap<String>(new CollCapacity(_redirects.length));
        for (EntryNav e: _redirects) {
            n_.addEntry(e.getNavCase(),e.getRedirect());
        }
        _nav.addEntry(_key,n_);
    }
    public static void buildBeans(StringMap<BeanInfo> _nav, String _key, String _value) {
        BeanInfo b_ = new BeanInfo();
        b_.setScope("session");
        b_.setClassName(_value);
        b_.setResolvedClassName(_value);
        _nav.addEntry(_key,b_);
    }
}
