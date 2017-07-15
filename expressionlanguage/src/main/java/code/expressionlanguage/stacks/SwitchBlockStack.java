package code.expressionlanguage.stacks;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.BracedBlock;
import code.util.CustList;

public final class SwitchBlockStack extends SwitchStack implements BreakableBlockStack, RemovableVars {

//    private static final String RETURN_LINE = "\n";

//    private static final String HAS_NEXT = "value";

//    private static final String SEP_KEY_VAL = ":";

//    private boolean finished;

//    private boolean entered;

//    private Struct value = new Struct();

//    private int visitedBlock = List.INDEX_NOT_FOUND_ELT;
    
//    private final List<Element> nodes = new List<Element>();

    private final CustList<BracedBlock> blocks = new CustList<BracedBlock>();

//    @Override
//    public String toString() {
//        try {
//            return HAS_NEXT+SEP_KEY_VAL+getValue()+RETURN_LINE;
//        } catch (Error _0) {
//            return HAS_NEXT+RETURN_LINE;
//        } catch (RuntimeException _0) {
//            return HAS_NEXT+RETURN_LINE;
//        }
//    }

//    public boolean isFinished() {
//        return finished;
//    }
//
//    @Override
//    public void setFinished(boolean _finished) {
//        finished = _finished;
//    }
//
//    public boolean isEntered() {
//        return entered;
//    }
//
//    public void setEntered(boolean _entered) {
//        entered = _entered;
//    }
//    
//    public Struct getStruct() {
//        return value;
//    }
//    
//    public void setStruct(Struct _value) {
//        value = _value;
//        if (value == null) {
//            value = new Struct();
//        }
//    }
//
//    public Object getValue() {
//        return value.getInstance();
//    }
//
//    public void setValue(Object _value) {
//        if (_value == null) {
//            value = new Struct();
//        } else {
//            value = new Struct(_value);
//        }
//    }
//
//    public int getVisitedBlock() {
//        return visitedBlock;
//    }
//
//    public void setVisitedBlock(int _visitedBlock) {
//        visitedBlock = _visitedBlock;
//    }

    public BracedBlock firstVisitedBlock() {
        return blocks.first();
    }
    
    public BracedBlock lastVisitedBlock() {
        return blocks.last();
    }

    public BracedBlock getCurentVisitedBlock() {
        return blocks.get(getVisitedBlock());
    }

    public CustList<BracedBlock> getBlocks() {
        return blocks;
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        BracedBlock cur_ = getCurentVisitedBlock();
        cur_.removeLocalVars(_ip);
        _ip.removeLastBlock();
    }

//    public Element firstVisitedNode() {
//        return nodes.first();
//    }
    
//    public Element lastVisitedNode() {
//        return nodes.last();
//    }
//
//    public Element getCurentVisitedNode() {
//        return nodes.get(visitedBlock);
//    }
//
//    public List<Element> getNodes() {
//        return nodes;
//    }

}
