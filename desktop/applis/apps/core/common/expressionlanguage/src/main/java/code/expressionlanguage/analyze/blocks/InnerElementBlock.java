package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class InnerElementBlock extends ImmutableNameRootBlock implements InnerTypeOrElement,UniqueRootedBlock {

    private final String fieldName;

    private final String value;

    private final String tempClass;

    private int tempClassOffset;

    private String importedClassName;

    private int fieldNameOffest;

    private int valueOffest;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private int trOffset;
    private OperationNode root;
    private int fieldNumber;
    private EnumBlock parentEnum;
    private StringList fieldList = new StringList();
    private int numberInner = -1;
    private CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();
    public InnerElementBlock(EnumBlock _m, String _pkgName,OffsetStringInfo _fieldName,
                             OffsetStringInfo _type,
                             OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_fieldName.getOffset(), _fieldName.getInfo(), _pkgName, new OffsetAccessInfo(0,AccessEnum.PUBLIC), "", new IntMap< String>(), _offset);
        parentEnum = _m;
        fieldNameOffest = _fieldName.getOffset();
        valueOffest = _value.getOffset();
        fieldName = _fieldName.getInfo();
        value = _value.getInfo();
        tempClass = _type.getInfo();
        tempClassOffset = _type.getOffset();
    }

    public EnumBlock getParentEnum() {
        return parentEnum;
    }

    @Override
    public void setupBasicOverrides(AnalyzedPageEl _page) {
        checkAccess(_page);
    }

    public String getValue() {
        return value;
    }

    public String getTempClass() {
        return tempClass;
    }

    public int getTempClassOffset() {
        return tempClassOffset;
    }

    @Override
    public boolean mustImplement() {
        return true;
    }

    @Override
    public String getUniqueFieldName() {
        return fieldName;
    }

    @Override
    public void retrieveNames(StringList _fieldNames, AnalyzedPageEl _page) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new PartOffset(fieldName,valueOffest),true, new StringList()));
        FieldBlock.checkFieldsNames(this,_fieldNames,fields_, _page);
        for (PartOffsetAffect n: fields_) {
            getNameErrors().addAllElts(n.getErrs());
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

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
    }

    public int getValueOffest() {
        return valueOffest;
    }

    public void buildExpressionLanguageReadOnly(ExecInnerTypeOrElement _exec, AnalyzedPageEl _page) {
        _page.setGlobalOffset(fieldNameOffest);
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(fieldName,"=",newKeyWord_, PAR_LEFT, value, PAR_RIGHT);
        int trOffset_ = valueOffest  -1 -fieldName.length()- fieldNameOffest - 1 - newKeyWord_.length();
        trOffset = trOffset_;
        _exec.setTrOffset(trOffset_);
        _page.setTranslatedOffset(trOffset_);
        int index_ = getIndex();
        _page.setIndexChildType(index_);
        root = ElUtil.getRootAnalyzedOperationsReadOnly(fullInstance_, new Calculation(fieldName), _page);
        _page.getCoverage().putBlockOperations((ExecBlock) _exec,this);
        _page.getCoverage().putBlockOperations(this);
        _exec.setOpValue(ReachOperationUtil.tryCalculateAndSupply(root,_page));
//        root = _page.getCurrentRoot();
        _page.setTranslatedOffset(0);
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
        return importedClassName;
    }

    @Override
    public void buildImportedType(AnalyzedPageEl _page) {
        _page.setGlobalOffset(tempClassOffset);
        _page.setOffset(0);
        _page.setCurrentBlock(this);
        _page.setCurrentAnaBlock(this);
        int i_ = 1;
        StringList j_ = new StringList();
        String fullName_ = parentEnum.getFullName();
        for (String p: StringExpUtil.getAllTypes(StringList.concat(fullName_, tempClass)).mid(1)) {
            int loc_ = StringList.getFirstPrintableCharIndex(p);
            j_.add(ResolvingImportTypes.resolveCorrectType(i_+loc_,p, _page));
            partOffsets.addAllElts(_page.getCurrentParts());
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        importedClassName = AnaTemplates.check(errs_,fullName_,j_,varsCt_, _page);
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
    public boolean isStaticField() {
        return true;
    }


    @Override
    public StringList getFieldName() {
        return fieldList;
    }

    @Override
    public boolean isStaticType() {
        return false;
    }


    public int getFieldNameOffest() {
        return fieldNameOffest;
    }

    public int getTrOffset() {
        return trOffset;
    }

    public OperationNode getRoot() {
        return root;
    }

    @Override
    public int getFieldNumber() {
        return fieldNumber;
    }

    @Override
    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public int getNumberInner() {
        return numberInner;
    }

    public void setNumberInner(int numberInner) {
        this.numberInner = numberInner;
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
