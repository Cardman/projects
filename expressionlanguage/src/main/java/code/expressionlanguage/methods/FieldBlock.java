package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.FieldInitPageEl;
import code.expressionlanguage.calls.StaticInitPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadFieldName;
import code.expressionlanguage.errors.custom.BadParamName;
import code.expressionlanguage.errors.custom.DuplicateField;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecDeclaringOperation;
import code.expressionlanguage.opers.exec.ExecMethodOperation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.exec.ExecSettableFieldOperation;
import code.expressionlanguage.opers.util.*;
import code.util.*;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final StringList fieldName = new StringList();

    private final String className;

    private int classNameOffset;

    private String importedClassName;

    private final String value;

    private int valueOffset;
    private Ints valuesOffset = new Ints();

    private final boolean staticField;

    private int staticFieldOffset;

    private final boolean finalField;

    private int finalFieldOffset;

    private final AccessEnum access;

    private int accessOffset;

    private CustList<ExecOperationNode> opValue;
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private StringList assignedDeclaredFields = new StringList();
    public FieldBlock(OffsetAccessInfo _access,
                      OffsetBooleanInfo _static, OffsetBooleanInfo _final,
                      OffsetStringInfo _type, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        staticField = _static.isInfo();
        staticFieldOffset = _static.getOffset();
        finalField = _final.isInfo();
        finalFieldOffset = _final.getOffset();
        className = _type.getInfo();
        classNameOffset = _type.getOffset();
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    @Override
    public int getFieldNameOffset() {
        return valueOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public int getStaticFieldOffset() {
        return staticFieldOffset;
    }

    public int getFinalFieldOffset() {
        return finalFieldOffset;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
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
    public String getClassName() {
        return className;
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public StringList getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    public StringList getAssignedDeclaredFields() {
        return assignedDeclaredFields;
    }

    @Override
    public void setAssignmentBefore(Analyzable _an) {
        Block prev_ = getPreviousSibling();
        while (prev_ != null) {
            if (prev_ instanceof InitBlock) {
                if ((((InitBlock)prev_).getStaticContext() == MethodAccessKind.STATIC) == isStaticField()) {
                    break;
                }
            }
            if (prev_ instanceof InfoBlock) {
                if (((InfoBlock)prev_).isStaticField() == isStaticField()) {
                    break;
                }
            }
            prev_ = prev_.getPreviousSibling();
        }
        AssignedVariables ass_;
        if (prev_ == null) {
            ass_ = _an.getContextEl().getAssignedVariables().getFinalVariablesGlobal();
            IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
            id_.put(this, ass_);
        } else {
            IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
            AssignedVariables parAss_ = id_.getVal(prev_);
            AssignedVariables assBl_ = buildNewAssignedVariable();
            assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        AssignedVariablesBlock asBlock_ = page_.getAssignedVariables();
        StringMap<SimpleAssignment> fieldsRoot_ = asBlock_.getFinalVariables().getVal(this).getFieldsRoot();
        for (EntryCust<String, SimpleAssignment> f: fieldsRoot_.entryList()) {
            String name_ = f.getKey();
            SimpleAssignment a_ = f.getValue();
            if (a_.isAssignedAfter()) {
                _an.getAnalyzing().getInitFields().add(name_);
            }
        }
    }
    @Override
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getClassNameOffset());
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        importedClassName = _cont.resolveCorrectType(className);
        partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
    }
    public void retrieveNames(ContextEl _cont, StringList _fieldNames) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        CustList<PartOffsetAffect> names_ = ElUtil.getFieldNames(valueOffset,value, _cont, Calculation.staticCalculation(staticField));
        if (names_.isEmpty()) {
            BadParamName b_;
            b_ = new BadParamName();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(b_);
        }
        StringList idsField_ = new StringList(_fieldNames);
        for (PartOffsetAffect n: names_) {
            PartOffset p_ = n.getPartOffset();
            String trName_ = p_.getPart();
            if (!_cont.isValidToken(trName_)) {
                BadFieldName b_;
                b_ = new BadFieldName();
                b_.setFileName(getFile().getFileName());
                b_.setIndexFile(getOffset().getOffsetTrim());
                b_.setName(trName_);
                _cont.getClasses().addError(b_);
            }
            for (String m: idsField_) {
                if (StringList.quickEq(m, trName_)) {
                    int r_ = getOffset().getOffsetTrim();
                    DuplicateField duplicate_;
                    duplicate_ = new DuplicateField();
                    duplicate_.setIndexFile(r_);
                    duplicate_.setFileName(getFile().getFileName());
                    duplicate_.setId(p_.getPart());
                    _cont.getClasses().addError(duplicate_);
                }
            }
            idsField_.add(trName_);
        }
        for (PartOffsetAffect n: names_) {
            PartOffset p_ = n.getPartOffset();
            String name_ = p_.getPart();
            if (n.isAffect()) {
                assignedDeclaredFields.add(name_);
            }
            _fieldNames.add(name_);
            fieldName.add(name_);
            valuesOffset.add(p_.getOffset());
        }
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }


    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        processPutCoverage(_cont);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(staticField));
        processReducing(_cont);
    }

    private void processReducing(ContextEl _cont) {
        if (_cont.isGearConst()) {
            opValue = ElUtil.getReducedNodes(opValue.last());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        processPutCoverage(_cont);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(staticField));
        processReducing(_cont);
    }

    private void processPutCoverage(ContextEl _cont) {
        if (!_cont.isGearConst()) {
            _cont.getCoverage().putBlockOperations(_cont,this);
        }
    }

    @Override
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            annotationsOps.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
        }
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
    public StringList getAnnotations() {
        return annotations;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }
    @Override
    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public EqList<ClassField> getStaticConstantDependencies(Analyzable _an, String _name) {
        ExecOperationNode last_ = opValue.last();
        if (!(last_ instanceof ExecDeclaringOperation)) {
            EqList<ClassField> eq_;
            eq_ = getDeps(_an, opValue.mid(1));
            return eq_;
        }
        EqList<ClassField> eq_;
        ExecMethodOperation m_ = (ExecMethodOperation)last_;
        int index_ = StringList.indexOf(fieldName,_name);
        CustList<ExecOperationNode> ch_ = m_.getChildrenNodes();
        int from_;
        int to_ = ch_.get(index_).getOrder();
        if (index_ == 0) {
            from_ = 0;
        } else {
            from_ = ch_.get(index_-1).getOrder() + 1;
        }
        eq_ = getDeps(_an, opValue.sub(from_, to_));
        return eq_;
    }

    private static EqList<ClassField> getDeps(Analyzable _an, CustList<ExecOperationNode> _op) {
        EqList<ClassField> eq_ = new EqList<ClassField>();
        for (ExecOperationNode o: _op) {
            ClassField key_ = getDep(_an, o);
            if (key_ == null) {
                continue;
            }
            eq_.add(key_);
        }
        return eq_;
    }
    private static ClassField getDep(Analyzable _an, ExecOperationNode _op) {
        if (!(_op instanceof ExecSettableFieldOperation)) {
            return null;
        }
        ExecSettableFieldOperation cst_ = (ExecSettableFieldOperation) _op;
        return cst_.getFieldId();
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
            el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getValueEl();
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
}
