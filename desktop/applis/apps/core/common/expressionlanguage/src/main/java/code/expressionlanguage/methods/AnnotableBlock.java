package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public interface AnnotableBlock {
    StringList getAnnotations();
    CustList<CustList<ExecOperationNode>> getAnnotationsOps();
    Ints getAnnotationsIndexes();
    void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ann);

}
