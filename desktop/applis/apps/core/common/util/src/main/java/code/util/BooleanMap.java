package code.util;


import code.util.core.IndexConstants;

public final class BooleanMap<V> extends AbsBasicMap<Boolean,V> {

    //list cannot be null, even by reflection
//    private final CustList<EntryCust<Boolean,V>> list = new CustList<EntryCust<Boolean,V>>();

    public BooleanMap() {
    }
    
    public BooleanMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected boolean matchKeys(Boolean _one, Boolean _two) {
        return _one == _two;
    }
}
