package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
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

    private StringList annotations = new StringList();

    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private StringList assignedDeclaredFields = new StringList();
    private OperationNode root;
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final StringList nameRetErrors = new StringList();
    private final CustList<StringList> nameErrorsFields = new CustList<StringList>();
    private final CustList<StringList> cstErrorsFields = new CustList<StringList>();
    private CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();
    private int fieldNumber;
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

    @Override
    public boolean isStaticField() {
        return staticField;
    }

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
    public void buildImportedType(AnalyzedPageEl _page) {
        _page.setGlobalOffset(getClassNameOffset());
        _page.setOffset(0);
        _page.setCurrentBlock(this);
        _page.setCurrentAnaBlock(this);
        importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
        return partOffsets;
    }
    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        Calculation calcul_ = Calculation.staticCalculation(staticField);
        CustList<PartOffsetAffect> names_ = ElUtil.getFieldNames(valueOffset,value, calcul_, _page);
        if (names_.isEmpty()) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(getOffset().getOffsetTrim());
            //value len
            b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
            _page.addLocError(b_);
            addNameRetErrors(b_);
        }
        checkFieldsNames(this, _fieldNames, names_, _page);
        for (PartOffsetAffect n: names_) {
            PartOffset p_ = n.getPartOffset();
            String name_ = p_.getPart();
            if (n.isAffect()) {
                assignedDeclaredFields.add(name_);
            }
            StringList errs_ = n.getErrs();
            if (errs_.isEmpty()) {
                fieldName.add(name_);
                valuesOffset.add(p_.getOffset());
                addCstErrorsFields(new StringList());
            }
            if (!name_.trim().isEmpty()) {
                addNameErrorsFields(new StringList(errs_));
            }
        }
    }

    static CustList<StringList> checkFieldsNames(Block _bl, StringList _fieldNames, CustList<PartOffsetAffect> _names, AnalyzedPageEl _page) {
        StringList idsField_ = new StringList(_fieldNames);
        CustList<StringList> found_ = new CustList<StringList>();
        AnalyzedPageEl page_ = _page;
        for (PartOffsetAffect n: _names) {
            PartOffset p_ = n.getPartOffset();
            String trName_ = p_.getPart();
            StringList err_ = new StringList();
            TokenErrorMessage mess_ = ManageTokens.partField(page_).checkToken(trName_, page_);
            if (mess_.isError()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(_bl.getFile().getFileName());
                b_.setIndexFile(_bl.getOffset().getOffsetTrim());
                //trName_ len
                b_.setBuiltError(mess_.getMessage());
                _page.addLocError(b_);
                err_.add(b_.getBuiltError());
            }
            for (String m: idsField_) {
                if (StringList.quickEq(m, trName_)) {
                    int r_ = _bl.getOffset().getOffsetTrim();
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(r_);
                    duplicate_.setFileName(_bl.getFile().getFileName());
                    //trName_ len
                    duplicate_.buildError(_page.getAnalysisMessages().getDuplicateField(),
                            trName_);
                    _page.addLocError(duplicate_);
                    err_.add(duplicate_.getBuiltError());
                }
            }
            n.getErrs().addAllElts(err_);
            found_.add(err_);
            idsField_.add(trName_);
            _fieldNames.add(trName_);
        }
        return found_;
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }


    public void buildExpressionLanguageReadOnly(ExecFieldBlock _exec, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        processPutCoverage(_exec, _page);
        _page.setIndexBlock(0);
        _exec.setOpValue(ElUtil.getAnalyzedOperationsReadOnly(value, Calculation.staticCalculation(staticField), _page));
        root = _page.getCurrentRoot();
    }
    public CustList<OperationNode> buildExpressionLanguageQuickly(AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        page_.setIndexBlock(0);
        return ElUtil.getAnalyzedOperationsQucikly(value, Calculation.staticCalculation(staticField), _page);
    }

    private void processPutCoverage(ExecFieldBlock _exec, AnalyzedPageEl _page) {
        _page.getCoverage().putBlockOperations(_exec,this);
        _page.getCoverage().putBlockOperations(this);
    }

    public void buildAnnotations(ExecAnnotableBlock _ex, AnalyzedPageEl _page) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            ops_.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), c_, _page));
            roots.add(_page.getCurrentRoot());
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public OperationNode getRoot() {
        return root;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public void addNameErrorsFields(StringList _error) {
        nameErrorsFields.add(_error);
    }

    public void addCstErrorsFields(StringList _error) {
        cstErrorsFields.add(_error);
    }

    public CustList<StringList> getNameErrorsFields() {
        return nameErrorsFields;
    }

    public CustList<StringList> getCstErrorsFields() {
        return cstErrorsFields;
    }

    public void addNameRetErrors(FoundErrorInterpret _error) {
        nameRetErrors.add(_error.getBuiltError());
    }
    public StringList getNameRetErrors() {
        return nameRetErrors;
    }

    @Override
    public int getFieldNumber() {
        return fieldNumber;
    }

    @Override
    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    @Override
    public CustList<AnonymousTypeBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<AnonymousFunctionBlock> getAnonymousFct() {
        return anonymousFct;
    }
}
