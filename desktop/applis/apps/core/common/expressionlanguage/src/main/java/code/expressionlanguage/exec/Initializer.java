package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public interface Initializer {

    Struct processInit(ContextEl _context, Struct _parent, ExecFormattedRootBlock _className, String _fieldName, int _ordinal);
    CustList<ClassFieldStruct> feedFields(ContextEl _context, ExecFormattedRootBlock _className);
    Struct processInitAnnot(ContextEl _context, ExecFormattedRootBlock _className,ExecRootBlock _rootBlock);

    void loopCalling(ContextEl _owner, StackCall _stackCall);
}
