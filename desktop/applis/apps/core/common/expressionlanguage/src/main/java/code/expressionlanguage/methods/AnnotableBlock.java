package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public interface AnnotableBlock {

    void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ann);

}
