package code.expressionlanguage.analyze;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class ClassFieldBlock {

    private final CustList<OperationNode> className;

    private final FieldBlock fieldName;

    public ClassFieldBlock(CustList<OperationNode> _className, FieldBlock _fieldName) {
        className = _className;
        fieldName = _fieldName;
    }

    public CustList<OperationNode> getClassName() {
        return className;
    }

    public FieldBlock getFieldName() {
        return fieldName;
    }
}
