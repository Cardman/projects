package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.FieldInitPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.AccessibleBlock;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ExecFieldBlock extends ExecLeaf implements ExecInfoBlock,AccessibleBlock {

    private final StringList fieldName = new StringList();
    private Ints valuesOffset = new Ints();
    private String importedClassName;
    private final String value;
    private int valueOffset;

    private final boolean staticField;

    private final boolean finalField;

    private final AccessEnum access;

    private CustList<ExecOperationNode> opValue;
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public ExecFieldBlock(FieldBlock _offset) {
        super(_offset.getOffset());
        value = _offset.getValue();
        valueOffset = _offset.getValueOffset();
        staticField = _offset.isStaticField();
        finalField = _offset.isFinalField();
        access = _offset.getAccess();
        annotations = _offset.getAnnotations();
        annotationsIndexes = _offset.getAnnotationsIndexes();
    }

    @Override
    public int getFieldNameOffset() {
        return valueOffset;
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

    @Override
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
        partOffsets = _key.getTypePartOffsets();
        fieldName.addAllElts(_key.getFieldName());
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }

    public void setValuesOffset(Ints valuesOffset) {
        this.valuesOffset = valuesOffset;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
        _parts.addAllElts(partOffsets);
        int blOffset_ = valueOffset;
        int endBl_ = blOffset_ + value.length();
        ElUtil.buildCoverageReport(_cont,blOffset_,this,getOpValue(),endBl_,_parts);
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
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
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
            ElUtil.tryToCalculate(_cont,el_,0);
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

    @Override
    public String getPackageName() {
        return ((ExecRootBlock)getParent()).getPackageName();
    }

    @Override
    public String getFullName() {
        return ((ExecRootBlock)getParent()).getFullName();
    }

    @Override
    public String getOuterFullName() {
        return ((ExecRootBlock)getParent()).getOuter().getFullName();
    }
}
