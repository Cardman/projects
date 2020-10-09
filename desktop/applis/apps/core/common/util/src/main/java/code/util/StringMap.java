package code.util;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.ListableEntries;




public final class StringMap<V> extends AbsBasicMap<String,V> {

    public StringMap() {
    }

    public StringMap(ListableEntries<String, V> _arg0) {
        putAllMap(_arg0);
    }

    
    public StringMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected boolean matchKeys(String _one, String _two) {
        return StringUtil.quickEq(_one,_two);
    }
}
