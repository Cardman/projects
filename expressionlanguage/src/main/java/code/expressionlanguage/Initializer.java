package code.expressionlanguage;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;

public interface Initializer {

    Struct processInit(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal);
    Struct processInitAnnot(ContextEl _context, String _className);
    String getInterfaceTask(LgNames _stds);
    String getRunTask(LgNames _stds);
    void loopCalling(ContextEl _owner);
}
