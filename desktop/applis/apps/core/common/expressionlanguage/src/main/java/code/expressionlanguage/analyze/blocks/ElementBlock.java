package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ElementBlock extends Leaf implements InnerTypeOrElement{

    private final AnaElementContent elementContent;

    private ResultParsedAnnots annotations = new ResultParsedAnnots();
    private final ResultExpression res = new ResultExpression();
    private final StringList nameErrors = new StringList();
    private final AnonymousElementsField elements = new AnonymousElementsField();

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
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        retrieveElementNames(_fieldNames,_page,this,this);
    }

    static void retrieveElementNames(StringList _fieldNames, AnalyzedPageEl _page, AbsBk _simple, InnerTypeOrElement _elt) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new FieldPartOffset(_elt.getElementContent().getFieldName(),_elt.getElementContent().getValueOffest()),true));
        FieldBlock.checkFieldsNames(_simple,_fieldNames,fields_,_page);
        for (PartOffsetAffect n: fields_) {
            _elt.getNameErrors().addAllElts(n.getErrs());
        }
        for (PartOffsetAffect n: fields_) {
            StringList errs_ = n.getErrs();
            FieldPartOffset p_ = n.getPartOffset();
            String name_ = p_.getName();
            if (errs_.isEmpty()) {
                _elt.getElements().getFieldName().add(name_);
            }
        }
        for (int i: _elt.getElementContent().getLastBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_simple.getFile());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _elt.addNameErrors(b_.getBuiltError());
        }
    }

    public String getValue() {
        return elementContent.getValue();
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        buildElementType(_page,this,this);
    }
    static void buildElementType(AnalyzedPageEl _page, AbsBk _simple, InnerTypeOrElement _elt) {
        _page.setSumOffset(_elt.getElementContent().getTempClassOffset());
        _page.zeroOffset();
        _page.setCurrentBlock(_simple);
        int i_ = 1;
        StringList j_ = new StringList();
        EnumBlock parentEnum_ = _elt.getElementContent().getParentEnum();
        String fullName_ = parentEnum_.getFullName();
        for (String p: StringExpUtil.getAllTypes(StringUtil.concat(fullName_, _elt.getElementContent().getTempClass())).mid(1)) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(p);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(i_ + loc_, p, _page);
            j_.add(result_.getResult(_page));
            _elt.getElementContent().getPartOffsets().add(result_);
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        _elt.getElements().setImportedClassName(AnaInherits.check(errs_, parentEnum_, fullName_, j_, varsCt_, _page));
        for (String e: errs_) {
            _elt.addNameErrors(e);
            _elt.getElementContent().setKoTy(true);
        }
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
        _page.zeroOffset();
        _page.setSumOffset(res.getSumOffset());
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, new Calculation(this, nameErrors), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
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
        annotations.buildAnnotations(_page);
    }


    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _a) {
        this.annotations = _a;
    }

    public ResultExpression getRes() {
        return res;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return elements.getImportedClassName();
    }


    public void addNameErrors(String _error) {
        nameErrors.add(_error);
    }

    public StringList getNameErrors() {
        return nameErrors;
    }


    @Override
    public AnonymousElementsField getElements() {
        return elements;
    }

    public AnaElementContent getElementContent() {
        return elementContent;
    }
}
