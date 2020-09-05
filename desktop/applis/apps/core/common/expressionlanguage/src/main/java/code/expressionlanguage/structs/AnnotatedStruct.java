package code.expressionlanguage.structs;

import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.util.CustList;

public interface AnnotatedStruct extends DisplayableStruct {
    String getFileName();
    ExecAnnotableBlock getAnnotableBlock();
    String getDeclaringClass();
    CustList<ExecAnonymousFunctionBlock> getAnonymousLambda();
}
