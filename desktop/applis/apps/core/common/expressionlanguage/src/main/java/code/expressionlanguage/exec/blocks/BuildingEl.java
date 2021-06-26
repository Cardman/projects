package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface BuildingEl {

    CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess);

}
