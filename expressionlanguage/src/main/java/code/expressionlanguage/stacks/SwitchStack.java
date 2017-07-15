package code.expressionlanguage.stacks;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;

public abstract class SwitchStack extends BlockStack implements BreakableStack {

    private static final String RETURN_LINE = "\n";

    private static final String HAS_NEXT = "value";

    private static final String SEP_KEY_VAL = ":";

    private boolean finished;

    private boolean entered;

    private Struct value = new Struct();

    private int visitedBlock = CustList.INDEX_NOT_FOUND_ELT;
    
//    private final CustList<Element> nodes = new CustList<Element>();

//    private final CustList<Block> blocks = new CustList<Block>();

    @Override
    public String toString() {
        try {
            return HAS_NEXT+SEP_KEY_VAL+value+RETURN_LINE;
        } catch (Error _0) {
            return HAS_NEXT+RETURN_LINE;
        } catch (RuntimeException _0) {
            return HAS_NEXT+RETURN_LINE;
        }
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished(boolean _finished) {
        finished = _finished;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean _entered) {
        entered = _entered;
    }
    
    public Struct getStruct() {
        return value;
    }
    
    public void setStruct(Struct _value) {
        value = _value;
        if (value == null) {
            value = new Struct();
        }
    }

    public Object getValue() {
        return value.getInstance();
    }

    public void setValue(Object _value) {
        if (_value == null) {
            value = new Struct();
        } else {
            value = new Struct(_value);
        }
    }

    public int getVisitedBlock() {
        return visitedBlock;
    }

    public void increment() {
        visitedBlock++;
    }

    public void setVisitedBlock(int _visitedBlock) {
        visitedBlock = _visitedBlock;
    }

//    public Block firstVisitedBlock() {
//        return blocks.first();
//    }
    
//    public Block lastVisitedBlock() {
//        return blocks.last();
//    }

//    public Block getCurentVisitedBlock() {
//        return blocks.get(visitedBlock);
//    }

//    public CustList<Block> getBlocks() {
//        return blocks;
//    }

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
//    public CustList<Element> getNodes() {
//        return nodes;
//    }

}
