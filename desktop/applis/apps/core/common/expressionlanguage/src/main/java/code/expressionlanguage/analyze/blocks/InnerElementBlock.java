package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.util.IntMap;
import code.util.StringList;

public final class InnerElementBlock extends RootBlock implements InnerTypeOrElement,UniqueRootedBlock {

    private final AnaElementContent elementContent;

    private final ResultExpression res = new ResultExpression();
    private int numberInner = -1;
    private final AnonymousElementsField elements = new AnonymousElementsField();

    public InnerElementBlock(EnumBlock _m, String _pkgName,OffsetStringInfo _fieldName,
                             OffsetStringInfo _type,
                             OffsetStringInfo _value, int _offset) {
        super(new OffsetAccessInfo(0,AccessEnum.PUBLIC), "", new IntMap< String>(), _offset, contentRoot(_fieldName.getOffset(), _pkgName, _fieldName.getInfo()));
        elementContent = new AnaElementContent(_m, _fieldName, _type, _value);
        setupOffsets(_fieldName.getInfo().trim(),_pkgName);
        parentType(_m);
    }

    @Override
    public RootBlock getDeclaringType() {
        return getParentEnum();
    }

    public EnumBlock getParentEnum() {
        return elementContent.getParentEnum();
    }

    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    public String getValue() {
        return elementContent.getValue();
    }

    public String getTempClass() {
        return elementContent.getTempClass();
    }

    public int getTempClassOffset() {
        return elementContent.getTempClassOffset();
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        ElementBlock.retrieveElementNames(_fieldNames,_page,this,this);
    }

    @Override
    public int getFieldNameOffset() {
        return elementContent.getFieldNameOffest();
    }

    public int getValueOffest() {
        return elementContent.getValueOffest();
    }

    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.zeroOffset();
        _page.setSumOffset(res.getSumOffset());
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, new Calculation(this, getNameErrors()), _page));
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
    }


    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public String getImportedClassName() {
        return getFullName();
    }

    @Override
    public String getRealImportedClassName() {
        return elements.getImportedClassName();
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        ElementBlock.buildElementType(_page,this,this);
    }

    public ResultExpression getRes() {
        return res;
    }

    @Override
    public boolean isFinalField() {
        return true;
    }

    @Override
    public boolean isStaticField() {
        return true;
    }


    @Override
    public int getInfoBlockNb() {
        return elementContent.getInfoBlockNb();
    }

    @Override
    public void setInfoBlockNb(int _i) {
        this.elementContent.setInfoBlockNb(_i);
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public int getNumberInner() {
        return numberInner;
    }

    public void setNumberInner(int _numberInner) {
        this.numberInner = _numberInner;
    }

    @Override
    public AnonymousElementsField getElements() {
        return elements;
    }

    public AnaElementContent getElementContent() {
        return elementContent;
    }
}
