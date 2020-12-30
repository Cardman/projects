package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public interface Initializer {

    Struct processInit(ContextEl _context, Struct _parent, String _className, ExecRootBlock _rootBlock, String _fieldName, int _ordinal);
    CustList<ClassFieldStruct> feedFields(ContextEl _context, String _className,ExecRootBlock _rootBlock);
    Struct processInitAnnot(ContextEl _context, String _className,ExecRootBlock _rootBlock);

    void loopCalling(ContextEl _owner, StackCall _stackCall);
}
