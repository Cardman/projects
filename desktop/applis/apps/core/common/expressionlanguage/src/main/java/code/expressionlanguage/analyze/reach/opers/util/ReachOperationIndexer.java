package code.expressionlanguage.analyze.reach.opers.util;
import code.expressionlanguage.analyze.reach.opers.ReachNamedArgumentOperation;
import code.expressionlanguage.common.AbstractOperationIndexer;
import code.util.CustList;

public final class ReachOperationIndexer extends AbstractOperationIndexer {
    private final CustList<ReachNamedArgumentOperation> named;
    public ReachOperationIndexer(CustList<ReachNamedArgumentOperation> _named) {
        named = _named;
    }
    public int size(){
        return named.size();
    }
    public int getIndex(int _index){
        return named.get(_index).getIndex();
    }
}
