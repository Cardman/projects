package code.expressionlanguage.exec.util;
import code.expressionlanguage.common.AbstractOperationIndexer;
import code.expressionlanguage.exec.opers.ExecNamedArgumentOperation;
import code.util.CustList;

public final class ExecOperationIndexer extends AbstractOperationIndexer {
    private final CustList<ExecNamedArgumentOperation> named;
    public ExecOperationIndexer(CustList<ExecNamedArgumentOperation> _named) {
        named = _named;
    }
    public int size(){
        return named.size();
    }
    public int getIndex(int _index){
        return named.get(_index).getIndex();
    }

}
