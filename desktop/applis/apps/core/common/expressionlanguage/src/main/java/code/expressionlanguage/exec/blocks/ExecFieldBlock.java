package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.FieldInitPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.fwd.blocks.AnaFieldContent;
import code.expressionlanguage.fwd.blocks.ExecFieldContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecFieldBlock extends ExecLeaf implements ExecInfoBlock {

    private final StringList fieldName = new StringList();

    private String importedClassName;

    private ExecFieldContent fieldContent;

    private CustList<ExecOperationNode> opValue;
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    public ExecFieldBlock(int _offsetTrim, AnaFieldContent _fieldContent) {
        super(_offsetTrim);
        fieldContent = new ExecFieldContent(_fieldContent);
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    @Override
    public boolean isStaticField() {
        return fieldContent.isStaticField();
    }

    @Override
    public boolean isFinalField() {
        return fieldContent.isFinalField();
    }

    @Override
    public StringList getFieldName() {
        return fieldName;
    }

    public AccessEnum getAccess() {
        return fieldContent.getAccess();
    }

    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getValueEl();
    }

    @Override
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ExpressionLanguage.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
        ExecOperationNode r_ = opValue.last();
        opValue = ExpressionLanguage.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        boolean static_ = isStaticField();
        boolean in_ = false;
        if (ip_ instanceof FieldInitPageEl && !static_) {
            in_ = true;
        } else if (ip_ instanceof StaticInitPageEl && static_) {
            in_ = true;
        }
        if (in_) {
            ip_.setGlobalOffset(fieldContent.getValueOffset());
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
            ExpressionLanguage.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        return anonymousLambda;
    }
}
