package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.OperationNode;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public interface AnnotableBlock extends ReducableOperations {
    StringList getAnnotations();
    CustList<CustList<OperationNode>> getAnnotationsOps();
    Numbers<Integer> getAnnotationsIndexes();
    void buildAnnotations(ContextEl _context);

}
