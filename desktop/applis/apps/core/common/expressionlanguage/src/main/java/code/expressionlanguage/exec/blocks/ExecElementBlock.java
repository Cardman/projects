package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.util.CustList;
import code.util.StringList;

public final class ExecElementBlock extends ExecLeaf implements ExecInnerTypeOrElement{

    private final String fieldName;

    private String importedClassName;

    private CustList<ExecOperationNode> opValue;

    private int fieldNameOffest;

    private int trOffset;

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();

    public ExecElementBlock(ElementBlock _offset) {
        super(_offset.getOffset());
        fieldName = _offset.getUniqueFieldName();
        fieldNameOffest = _offset.getFieldNameOffset();

    }

    @Override
    public void buildImportedTypes(InfoBlock _key) {
        importedClassName = _key.getImportedClassName();
    }

    @Override
    public String getUniqueFieldName() {
        return fieldName;
    }

    @Override
    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void setTrOffset(int trOffset) {
        this.trOffset = trOffset;
    }

    @Override
    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }

    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public boolean isFinalField() {
        return true;
    }

    @Override
    public StringList getFieldName() {
        return new StringList(fieldName);
    }

    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getValueEl();
    }
    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }
    @Override
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(fieldNameOffest);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ElUtil.tryToCalculate(_cont,el_, trOffset);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }
}
