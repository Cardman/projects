package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public abstract class RendAbstractFieldOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private ExecFieldOperationContent fieldOperationContent;

    public RendAbstractFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content);
        fieldOperationContent = _fieldOperationContent;
    }

    public int getOff() {
        return fieldOperationContent.getOff();
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldOperationContent.isIntermediate();
    }


}
