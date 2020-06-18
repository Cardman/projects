package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface ExecInnerTypeOrElement extends ExecInfoBlock {
    String getUniqueFieldName();

    CustList<ExecOperationNode> getOpValue();
    void setOpValue(CustList<ExecOperationNode> _op);
    void setTrOffset(int _off);
}
