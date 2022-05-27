package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.AnaFieldContent;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final AnaFieldContent fieldContent = new AnaFieldContent();
    private final String className;

    private final int classNameOffset;

    private final String value;

    private final Ints valuesOffset = new Ints();

    private final int staticFieldOffset;

    private final int finalFieldOffset;

    private final int accessOffset;
    private ResultParsedAnnots annotations = new ResultParsedAnnots();

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private final StringList assignedDeclaredFields = new StringList();
    private final ResultExpression res = new ResultExpression();
    private final StringList nameRetErrors = new StringList();
    private final CustList<StringList> nameErrorsFields = new CustList<StringList>();
    private final AnonymousElementsField elements = new AnonymousElementsField();
    private final RootBlock parentType;
    public FieldBlock(RootBlock _parent,OffsetAccessInfo _access,
                      OffsetBooleanInfo _static, OffsetBooleanInfo _final,
                      OffsetStringInfo _type, OffsetStringInfo _value, int _offset) {
        super(_offset);
        parentType = _parent;
        fieldContent.setAccess(_access.getInfo());
        accessOffset = _access.getOffset();
        fieldContent.setStaticField(_static.isInfo());
        staticFieldOffset = _static.getOffset();
        fieldContent.setFinalField(_final.isInfo());
        finalFieldOffset = _final.getOffset();
        className = _type.getInfo();
        classNameOffset = _type.getOffset();
        value = _value.getInfo();
        fieldContent.setValueOffset(_value.getOffset());
    }

    @Override
    public RootBlock getDeclaringType() {
        return parentType;
    }

    public int getFieldNameOffset() {
        return fieldContent.getValueOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getValueOffset() {
        return fieldContent.getValueOffset();
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
        return fieldContent.getAccess();
    }

    @Override
    public boolean isStaticField() {
        return fieldContent.isStaticField();
    }

    public boolean isFinalField() {
        return fieldContent.isFinalField();
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
        return elements.getImportedClassName();
    }

    public String getValue() {
        return value;
    }

    public StringList getAssignedDeclaredFields() {
        return assignedDeclaredFields;
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        _page.setSumOffset(getClassNameOffset());
        _page.zeroOffset();
        _page.setCurrentBlock(this);
        partOffsets = ResolvingTypes.resolveCorrectType(className, _page);
        elements.setImportedClassName(partOffsets.getResult(_page));
    }

    public AnaResultPartType getTypePartOffsets() {
        return partOffsets;
    }
    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        Calculation calcul_ = Calculation.staticCalculation(fieldContent.isStaticField());
        CustList<PartOffsetAffect> names_ = ElUtil.getFieldNames(res,fieldContent.getValueOffset(), calcul_, _page);
        if (names_.isEmpty()) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(getOffset());
            //value len
            b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
            _page.addLocError(b_);
            addNameRetErrors(b_);
        }
        checkFieldsNames(this, _fieldNames, names_, _page);
        for (PartOffsetAffect n: names_) {
            FieldPartOffset p_ = n.getPartOffset();
            String name_ = p_.getName();
            if (n.isAffect()) {
                assignedDeclaredFields.add(name_);
            }
            StringList errs_ = n.getErrs();
            if (errs_.isEmpty()) {
                getElements().getFieldName().add(name_);
                valuesOffset.add(p_.getOff());
            }
            addNameErrorsFields(new StringList(errs_));
        }
    }

    static void checkFieldsNames(AbsBk _bl, StringList _fieldNames, CustList<PartOffsetAffect> _names, AnalyzedPageEl _page) {
        StringList idsField_ = new StringList(_fieldNames);
        for (PartOffsetAffect n: _names) {
            FieldPartOffset p_ = n.getPartOffset();
            String trName_ = p_.getName();
            StringList err_ = new StringList();
            TokenErrorMessage mess_ = ManageTokens.partField(_page).checkToken(trName_, _page);
            if (mess_.isError()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFile(_bl.getFile());
                b_.setIndexFile(_bl.getOffset());
                //trName_ len
                b_.setBuiltError(mess_.getMessage());
                _page.addLocError(b_);
                err_.add(b_.getBuiltError());
            }
            for (String m: idsField_) {
                if (StringUtil.quickEq(m, trName_)) {
                    int r_ = _bl.getOffset();
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(r_);
                    duplicate_.setFile(_bl.getFile());
                    //trName_ len
                    duplicate_.buildError(_page.getAnalysisMessages().getDuplicateField(),
                            trName_);
                    _page.addLocError(duplicate_);
                    err_.add(duplicate_.getBuiltError());
                }
            }
            n.getErrs().addAllElts(err_);
            idsField_.add(trName_);
            _fieldNames.add(trName_);
        }
    }

    public Ints getValuesOffset() {
        return valuesOffset;
    }


    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(fieldContent.isStaticField()), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
    }

    public CustList<OperationNode> buildExpressionLanguageQuickly(AnalyzedPageEl _page) {
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        return ElUtil.getAnalyzedOperationsQucikly(res, Calculation.staticCalculation(fieldContent.isStaticField()), _page);
    }

    public void buildAnnotations(AnalyzedPageEl _page) {
        annotations.buildAnnotations(_page);
    }

    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _a) {
        this.annotations = _a;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public void addNameErrorsFields(StringList _error) {
        nameErrorsFields.add(_error);
    }

    public CustList<StringList> getNameErrorsFields() {
        return nameErrorsFields;
    }

    public void addNameRetErrors(FoundErrorInterpret _error) {
        nameRetErrors.add(_error.getBuiltError());
    }
    public StringList getNameRetErrors() {
        return nameRetErrors;
    }

    public ResultExpression getRes() {
        return res;
    }

    @Override
    public AnonymousElementsField getElements() {
        return elements;
    }

    public AnaFieldContent getFieldContent() {
        return fieldContent;
    }
}
