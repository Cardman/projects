package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.FieldInitPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
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

    public String getClassName() {
        return className;
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
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getClassNameOffset());
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
        partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
        return partOffsets;
    }
    @Override
    public void retrieveNames(ContextEl _cont, StringList _fieldNames) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        CustList<PartOffsetAffect> names_ = ElUtil.getFieldNames(valueOffset,value, _cont, Calculation.staticCalculation(staticField));
        if (names_.isEmpty()) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(getOffset().getOffsetTrim());
            //value len
            b_.buildError(_cont.getAnalysisMessages().getNotRetrievedFields());
            _cont.addError(b_);
        }
        checkFieldsNames(_cont, this, _fieldNames, names_);
        for (PartOffsetAffect n: names_) {
            PartOffset p_ = n.getPartOffset();
            String name_ = p_.getPart();
            if (n.isAffect()) {
                assignedDeclaredFields.add(name_);
            }
            fieldName.add(name_);
            valuesOffset.add(p_.getOffset());
        }
    }

    static void checkFieldsNames(ContextEl _cont, Block _bl, StringList _fieldNames, CustList<PartOffsetAffect> _names) {
        StringList idsField_ = new StringList(_fieldNames);
        for (PartOffsetAffect n: _names) {
            PartOffset p_ = n.getPartOffset();
            String trName_ = p_.getPart();
            if (!_cont.isValidToken(trName_)) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(_bl.getFile().getFileName());
                b_.setIndexFile(_bl.getOffset().getOffsetTrim());
                //trName_ len
                b_.buildError(_cont.getAnalysisMessages().getBadFieldName(),
                        trName_);
                _cont.addError(b_);
            }
            for (String m: idsField_) {
                if (StringList.quickEq(m, trName_)) {
                    int r_ = _bl.getOffset().getOffsetTrim();
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(r_);
                    duplicate_.setFileName(_bl.getFile().getFileName());
                    //trName_ len
                    duplicate_.buildError(_cont.getAnalysisMessages().getDuplicateField(),
                            trName_);
                    _cont.addError(duplicate_);
                }
            }
            idsField_.add(trName_);
            _fieldNames.add(trName_);
        }
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }


    public void buildExpressionLanguageReadOnly(ContextEl _cont, ExecFieldBlock _exec) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        processPutCoverage(_cont,_exec);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(staticField));
        _exec.setOpValue(opValue);
        processReducing(_cont);
    }
    public CustList<OperationNode> buildExpressionLanguageQuickly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        return ElUtil.getAnalyzedOperationsQucikly(value, _cont, Calculation.staticCalculation(staticField));
    }

    private void processReducing(ContextEl _cont) {
        opValue = ElUtil.getReducedNodes(opValue.last());
    }

    private void processPutCoverage(ContextEl _cont, ExecFieldBlock _exec) {
        _cont.getCoverage().putBlockOperations(_cont,_exec,this);
        _cont.getCoverage().putBlockOperations(_cont,this);
    }

    public void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ex) {
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
        _ex.getAnnotationsOps().addAllElts(annotationsOps);
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

}
