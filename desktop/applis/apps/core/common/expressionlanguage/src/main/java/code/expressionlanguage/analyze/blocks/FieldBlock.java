package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.fwd.blocks.AnaFieldContent;
import code.util.*;
import code.util.core.StringUtil;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final StringList fieldName = new StringList();

    private final AnaFieldContent fieldContent = new AnaFieldContent();
    private final String className;

    private final int classNameOffset;

    private String importedClassName;

    private final String value;

    private final Ints valuesOffset = new Ints();

    private final int staticFieldOffset;

    private final int finalFieldOffset;

    private final int accessOffset;

    private final StringList annotations = new StringList();

    private final Ints annotationsIndexes = new Ints();
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private final StringList assignedDeclaredFields = new StringList();
    private final ResultExpression res = new ResultExpression();
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private final StringList nameRetErrors = new StringList();
    private final CustList<StringList> nameErrorsFields = new CustList<StringList>();
    private final CustList<StringList> cstErrorsFields = new CustList<StringList>();
    private final CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> anonymousFct = new CustList<NamedCalledFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    private final RootBlock parentType;
    private int fieldNumber;
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

    @Override
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
        _page.zeroOffset();
        _page.setCurrentBlock(this);
        importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
        return partOffsets;
    }
    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        _page.setGlobalOffset(fieldContent.getValueOffset());
        _page.zeroOffset();
        Calculation calcul_ = Calculation.staticCalculation(fieldContent.isStaticField());
        CustList<PartOffsetAffect> names_ = ElUtil.getFieldNames(res,fieldContent.getValueOffset(),value, calcul_, _page);
        if (names_.isEmpty()) {
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(getOffset());
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
            addNameErrorsFields(new StringList(errs_));
        }
    }

    static void checkFieldsNames(AbsBk _bl, StringList _fieldNames, CustList<PartOffsetAffect> _names, AnalyzedPageEl _page) {
        StringList idsField_ = new StringList(_fieldNames);
        for (PartOffsetAffect n: _names) {
            PartOffset p_ = n.getPartOffset();
            String trName_ = p_.getPart();
            StringList err_ = new StringList();
            TokenErrorMessage mess_ = ManageTokens.partField(_page).checkToken(trName_, _page);
            if (mess_.isError()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(_bl.getFile().getFileName());
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
                    duplicate_.setFileName(_bl.getFile().getFileName());
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
        _page.setGlobalOffset(fieldContent.getValueOffset());
        _page.zeroOffset();
        _page.setIndexBlock(0);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, value, Calculation.staticCalculation(fieldContent.isStaticField()), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
    }

    public CustList<OperationNode> buildExpressionLanguageQuickly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(fieldContent.getValueOffset());
        _page.zeroOffset();
        _page.setIndexBlock(0);
        return ElUtil.getAnalyzedOperationsQucikly(res,value, Calculation.staticCalculation(fieldContent.isStaticField()), _page);
    }

    public void buildAnnotations(AnalyzedPageEl _page) {
        int len_ = annotationsIndexes.size();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), annotations.get(i).trim(), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public OperationNode getRoot() {
        return res.getRoot();
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

    public CustList<ResultExpression> getResList() {
        return resList;
    }

    public ResultExpression getRes() {
        return res;
    }

    @Override
    public int getFieldNumber() {
        return fieldNumber;
    }

    @Override
    public void setFieldNumber(int _fieldNumber) {
        this.fieldNumber = _fieldNumber;
    }

    @Override
    public CustList<AnonymousTypeBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<NamedCalledFunctionBlock> getAnonymousFct() {
        return anonymousFct;
    }

    @Override
    public CustList<SwitchMethodBlock> getSwitchMethods() {
        return switchMethods;
    }

    public AnaFieldContent getFieldContent() {
        return fieldContent;
    }
}
