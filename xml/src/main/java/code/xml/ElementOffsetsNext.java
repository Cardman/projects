package code.xml;
import code.util.Numbers;
import code.util.StringMap;

public final class ElementOffsetsNext {

    private final StringMap<RowCol> attributes = new StringMap<RowCol>();
    
    private final StringMap<Numbers<Integer>> offsets = new StringMap<Numbers<Integer>>();

    private final StringMap<Numbers<Integer>> tabs = new StringMap<Numbers<Integer>>();

    private final RowCol nextCol;

    private final int nextElt;

    private final int begin;

    private RowCol endHeader;

//    private final int nextEltLineReturn;
    
//    public ElementOffsetsNext(Map<String,RowCol> _attributes, RowCol _rc, int _nextElt, int _n)
    public ElementOffsetsNext(StringMap<Numbers<Integer>> _tabs,
            StringMap<Numbers<Integer>> _ret, StringMap<RowCol> _attributes,
            RowCol _rc, RowCol _endHeader, int _nextElt, int _begin) {
        this(_rc, _nextElt, _begin);
        endHeader = _endHeader;
        tabs.putAllMap(_tabs);
        offsets.putAllMap(_ret);
        attributes.putAllMap(_attributes);
    }

//    public ElementOffsetsNext(RowCol _rc, int _nextElt, int _n)
    public ElementOffsetsNext(RowCol _rc, int _nextElt, int _begin) {
        nextCol = _rc;
        nextElt = _nextElt;
        begin = _begin;
//        nextEltLineReturn = _n;
    }

    public RowCol getEndHeader() {
        return endHeader;
    }

    public StringMap<Numbers<Integer>> getTabs() {
        return tabs;
    }

    public StringMap<Numbers<Integer>> getOffsets() {
        return offsets;
    }

    public RowCol getNextCol() {
        return nextCol;
    }

    public StringMap<RowCol> getAttributes() {
        return attributes;
    }

    public int getNextElt() {
        return nextElt;
    }

    public int getBegin() {
        return begin;
    }
//    public int getNextEltLineReturn() {
//        return nextEltLineReturn;
//    }
}
