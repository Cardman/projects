package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public interface AnnotableBlock extends ReducableOperations {
    StringList getAnnotations();
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
    Numbers<Integer> getAnnotationsIndexes();
    void buildAnnotations(ContextEl _context);

}
