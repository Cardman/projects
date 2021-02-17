package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.options.KeyWords;
import code.util.*;
import code.util.core.StringUtil;

public final class ElementBlock extends Leaf implements InnerTypeOrElement{

    private final AnaElementContent elementContent = new AnaElementContent();

    private final String value;

    private final String tempClass;

    private final int tempClassOffset;

    private String importedClassName;

    private final int valueOffest;

    private final StringList annotations = new StringList();
    private final ResultExpression res = new ResultExpression();
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private final Ints annotationsIndexes = new Ints();
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private int trOffset;
    private final StringList nameErrors = new StringList();
    private int fieldNumber;
    private final EnumBlock parentEnum;
    private final StringList fieldList = new StringList();
    private final CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private final CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();

    public ElementBlock(EnumBlock _m, OffsetStringInfo _fieldName,
                        OffsetStringInfo _type,
                        OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        parentEnum = _m;
        elementContent.setFieldNameOffest(_fieldName.getOffset());
        valueOffest = _value.getOffset();
        elementContent.setFieldName(_fieldName.getInfo());
        value = _value.getInfo();
        tempClass = _type.getInfo();
        tempClassOffset = _type.getOffset();
    }

    @Override
    public int getFieldNameOffset() {
        return elementContent.getFieldNameOffest();
    }

    public int getValueOffest() {
        return valueOffest;
    }

    public String getTempClass() {
        return tempClass;
    }
    public int getTempClassOffset() {
        return tempClassOffset;
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
        fields_.add(new PartOffsetAffect(new PartOffset(elementContent.getFieldName(),valueOffest),true, new StringList()));
        FieldBlock.checkFieldsNames(this,_fieldNames,fields_,_page);
        for (PartOffsetAffect n: fields_) {
            addNameErrors(n.getErrs());
        }
        for (PartOffsetAffect n: fields_) {
            StringList errs_ = n.getErrs();
            PartOffset p_ = n.getPartOffset();
            String name_ = p_.getPart();
            if (errs_.isEmpty()) {
                fieldList.add(name_);
            }
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        _page.setGlobalOffset(tempClassOffset);
        _page.setOffset(0);
        _page.setCurrentBlock(this);
        int i_ = 1;
        StringList j_ = new StringList();
        String fullName_ = parentEnum.getFullName();
        for (String p: StringExpUtil.getAllTypes(StringUtil.concat(fullName_, tempClass)).mid(1)) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(p);
            j_.add(ResolvingTypes.resolveCorrectType(i_+loc_,p, _page));
            partOffsets.addAllElts(_page.getCurrentParts());
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        importedClassName = AnaInherits.check(errs_,fullName_,j_,varsCt_, _page);
        for (String e: errs_) {
            addNameErrors(e);
        }
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
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
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = buildVirtualCreate(newKeyWord_);
        int tr_ = retrieveTr(newKeyWord_);
        trOffset = tr_;
        _page.setTranslatedOffset(tr_);
        int index_ = getIndex();
        _page.setIndexChildType(index_);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, fullInstance_, new Calculation(elementContent.getFieldName()), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
        _page.setTranslatedOffset(0);
    }

    public String buildVirtualCreate(String _newKeyWord) {
        return StringUtil.concat(elementContent.getFieldName(),"=", _newKeyWord, PAR_LEFT, value, PAR_RIGHT);
    }

    public int retrieveTr(String _newKeyWord) {
        return valueOffest -1 - elementContent.getFieldName().length() - elementContent.getFieldNameOffest() - 1 - _newKeyWord.length();
    }

    private int getIndex() {
        int index_ = 0;
        Block n_ = getPreviousSibling();
        while (n_ != null) {
            index_++;
            n_ = n_.getPreviousSibling();
        }
        return index_;
    }

    public void buildAnnotations(AnalyzedPageEl _page) {
        int len_ = annotationsIndexes.size();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.setOffset(0);
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
    public CustList<AnonymousFunctionBlock> getAnonymousFct() {
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
