package code.bean.nat.exec.blocks;

public abstract class NatBlock implements NatRendWithEl{
    public static final String SPACE = " ";
    public static final String RETURN_LINE = "\n";
//    public static final String CALL_METHOD = "$";
    public static final String EMPTY_STRING = "";
//    public static final String LT_END_TAG = "</";
//    public static final char GT_TAG = '>';
//    public static final char LT_BEGIN_TAG = '<';

    public static final String DOT = ".";

    private NatParentBlock parent;

    private NatBlock nextSibling;

    protected NatBlock() {
    }

    public final NatBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(NatBlock _nextSibling) {
        nextSibling = _nextSibling;
    }

    public final NatParentBlock getParent() {
        return parent;
    }

    protected void setParent(NatParentBlock _par) {
        parent = _par;
    }
}
