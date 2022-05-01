package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.options.KeyWords;
import code.util.*;
import code.util.core.StringUtil;

public final class ElementBlock extends Leaf implements InnerTypeOrElement{

    private final AnaElementContent elementContent;

    private String importedClassName;

    private ResultParsedAnnots annotations = new ResultParsedAnnots();
    private final ResultExpression res = new ResultExpression();
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();
    private int trOffset;
    private final StringList nameErrors = new StringList();
    private int fieldNumber;
    private final StringList fieldList = new StringList();
    private final CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> anonymousFct = new CustList<NamedCalledFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    private final Ints lastBadIndexes = new Ints();

    private boolean koTy;
    public ElementBlock(EnumBlock _m, OffsetStringInfo _fieldName,
                        OffsetStringInfo _type,
                        OffsetStringInfo _value, int _offset) {
        super(_offset);
        elementContent = new AnaElementContent(_m, _fieldName, _type, _value);
    }

    @Override
    public RootBlock getDeclaringType() {
        return elementContent.getParentEnum();
    }
    @Override
    public int getFieldNameOffset() {
        return elementContent.getFieldNameOffest();
    }

    public int getValueOffest() {
        return elementContent.getValueOffest();
    }

    public String getTempClass() {
        return elementContent.getTempClass();
    }
    public int getTempClassOffset() {
        return elementContent.getTempClassOffset();
    }

    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public StringList getFieldName() {
        return fieldList;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new FieldPartOffset(elementContent.getFieldName(),elementContent.getValueOffest()),true));
        FieldBlock.checkFieldsNames(this,_fieldNames,fields_,_page);
        for (PartOffsetAffect n: fields_) {
            addNameErrors(n.getErrs());
        }
        for (PartOffsetAffect n: fields_) {
            StringList errs_ = n.getErrs();
            FieldPartOffset p_ = n.getPartOffset();
            String name_ = p_.getName();
            if (errs_.isEmpty()) {
                fieldList.add(name_);
            }
        }
        for (int i: lastBadIndexes) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            addNameErrors(b_.getBuiltError());
        }
    }

    public String getValue() {
        return elementContent.getValue();
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        _page.setGlobalOffset(elementContent.getTempClassOffset());
        _page.zeroOffset();
        _page.setCurrentBlock(this);
        int i_ = 1;
        StringList j_ = new StringList();
        EnumBlock parentEnum_ = elementContent.getParentEnum();
        String fullName_ = parentEnum_.getFullName();
        for (String p: StringExpUtil.getAllTypes(StringUtil.concat(fullName_, elementContent.getTempClass())).mid(1)) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(p);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(i_ + loc_, p, _page);
            j_.add(result_.getResult(_page));
            partOffsets.add(result_);
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        importedClassName = AnaInherits.check(errs_,parentEnum_,fullName_,j_,varsCt_, _page);
        for (String e: errs_) {
            addNameErrors(e);
            koTy = true;
        }
    }

    @Override
    public boolean koType() {
        return koTy;
    }

    @Override
    public CustList<AnaResultPartType> getTypePartOffsets() {
        return partOffsets;
    }

    @Override
    public boolean isFinalField() {
        return true;
    }

    @Override
    public AccessEnum getAccess() {
        return AccessEnum.PUBLIC;
    }

    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(elementContent.getFieldNameOffest());
        _page.zeroOffset();
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = buildVirtualCreate(newKeyWord_);
        int tr_ = retrieveTr(newKeyWord_);
        trOffset = tr_;
        _page.setTranslatedOffset(tr_);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, fullInstance_, new Calculation(this, nameErrors), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
        _page.setTranslatedOffset(0);
    }

    public String buildVirtualCreate(String _newKeyWord) {
        return StringUtil.concat(elementContent.getFieldName(),"=", _newKeyWord, PAR_LEFT, elementContent.getValue(), PAR_RIGHT);
    }

    public int retrieveTr(String _newKeyWord) {
        return elementContent.getValueOffest() - elementContent.getFieldNameOffest() + diffTr(_newKeyWord);
    }

    public int diffTr(String _newKeyWord) {
        return elementContent.diffTr(_newKeyWord);
    }

    public void buildAnnotations(AnalyzedPageEl _page) {
        int len_ = annotations.getAnnotations().size();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            ResultParsedAnnot begin_ = annotations.getAnnotations().get(i);
            _page.setGlobalOffset(begin_.getIndex());
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), begin_.getAnnotation().trim(), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }


    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _a) {
        this.annotations = _a;
    }

    @Override
    public Ints getLastBadIndexes() {
        return lastBadIndexes;
    }

    public ResultExpression getRes() {
        return res;
    }

    public CustList<ResultExpression> getResList() {
        return resList;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public int getFieldNameOffest() {
        return elementContent.getFieldNameOffest();
    }

    public int getTrOffset() {
        return trOffset;
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }


    public void addNameErrors(String _error) {
        nameErrors.add(_error);
    }
    public void addNameErrors(StringList _error) {
        nameErrors.addAllElts(_error);
    }
    public StringList getNameErrors() {
        return nameErrors;
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

    public AnaElementContent getElementContent() {
        return elementContent;
    }
}
