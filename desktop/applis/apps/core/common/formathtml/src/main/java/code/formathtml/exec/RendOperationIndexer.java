package code.formathtml.exec;

import code.expressionlanguage.common.AbstractOperationIndexer;
import code.formathtml.exec.opers.RendNamedArgumentOperation;
import code.util.CustList;

public final class RendOperationIndexer extends AbstractOperationIndexer {
    private final CustList<RendNamedArgumentOperation> named;
    public RendOperationIndexer(CustList<RendNamedArgumentOperation> _named){
        named = _named;
    }
    public int size(){
        return named.size();
    }
    public int getIndex(int _index){
        return named.get(_index).getIndex();
    }

}
