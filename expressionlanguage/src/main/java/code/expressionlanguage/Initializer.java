package code.expressionlanguage;

import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;

public interface Initializer {

    Struct processInit(ContextEl _context, Struct _parent, String _className, String _fieldName, int _ordinal);
    String getInterfaceTask(LgNames _stds);
    String getRunTask(LgNames _stds);
}
