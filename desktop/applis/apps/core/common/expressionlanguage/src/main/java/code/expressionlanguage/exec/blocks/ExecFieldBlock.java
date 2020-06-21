package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.FieldInitPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ExecFieldBlock extends ExecLeaf implements ExecInfoBlock {

    private final StringList fieldName = new StringList();
    private Ints valuesOffset = new Ints();
    private String importedClassName;

    private int valueOffset;

    private final boolean staticField;

    private final boolean finalField;

    private final AccessEnum access;

    private CustList<ExecOperationNode> opValue;
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();

    public ExecFieldBlock(FieldBlock _offset) {
        super(_offset.getOffset());
        valueOffset = _offset.getValueOffset();
        staticField = _offset.isStaticField();
        finalField = _offset.isFinalField();
        access = _offset.getAccess();
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
        return staticField;
    }

    @Override
    public boolean isFinalField() {
        return finalField;
    }

    @Override
    public StringList getFieldName() {
        return fieldName;
    }

    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public void buildImportedTypes(InfoBlock _key) {
        importedClassName = _key.getImportedClassName();
        fieldName.addAllElts(_key.getFieldName());
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }

    public void setValuesOffset(Ints valuesOffset) {
        this.valuesOffset = valuesOffset;
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
            ip_.setGlobalOffset(valueOffset);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ExpressionLanguage.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    public String getPackageName() {
        return ((ExecRootBlock)getParent()).getPackageName();
    }

    public String getFullName() {
        return ((ExecRootBlock)getParent()).getFullName();
    }

    public String getOuterFullName() {
        return ((ExecRootBlock)getParent()).getOuter().getFullName();
    }
}
