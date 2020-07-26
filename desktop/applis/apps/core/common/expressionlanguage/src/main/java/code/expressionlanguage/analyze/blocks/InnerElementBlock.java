package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class InnerElementBlock extends RootBlock implements InnerTypeOrElement,UniqueRootedBlock {

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
    private String classNameRes;
    private int fieldNumber;
    public InnerElementBlock(EnumBlock _m, String _pkgName,OffsetStringInfo _fieldName,
                             OffsetStringInfo _type,
                             OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_fieldName.getOffset(), _fieldName.getInfo(), _pkgName, new OffsetAccessInfo(0,AccessEnum.PUBLIC), "", new IntMap< String>(), _offset);
        classNameRes = _m.getFullName();
        fieldNameOffest = _fieldName.getOffset();
        valueOffest = _value.getOffset();
        fieldName = _fieldName.getInfo();
        value = _value.getInfo();
        tempClass = _type.getInfo();
        tempClassOffset = _type.getOffset();
    }

    @Override
    public void setupBasicOverrides(ContextEl _context,ExecRootBlock _exec) {
        checkAccess(_context,_exec);
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
    public void retrieveNames(ContextEl _cont, StringList _fieldNames) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new PartOffset(fieldName,valueOffest),true, new StringList()));
        FieldBlock.checkFieldsNames(_cont,this,_fieldNames,fields_);
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
    }

    public int getValueOffest() {
        return valueOffest;
    }

    public void buildExpressionLanguageReadOnly(ContextEl _cont, ExecInnerTypeOrElement _exec) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(fieldNameOffest);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(fieldName,"=",newKeyWord_, PAR_LEFT, value, PAR_RIGHT);
        int trOffset_ = valueOffest  -1 -fieldName.length()- fieldNameOffest - 1 - newKeyWord_.length();
        trOffset = trOffset_;
        _exec.setTrOffset(trOffset_);
        page_.setTranslatedOffset(trOffset_);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        _cont.getCoverage().putBlockOperations(_cont, (ExecBlock) _exec,this);
        _cont.getCoverage().putBlockOperations(_cont,this);
        _exec.setOpValue(ElUtil.getAnalyzedOperationsReadOnly(fullInstance_, _cont, new Calculation(fieldName)));
        root = page_.getCurrentRoot();
        page_.setTranslatedOffset(0);
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
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(tempClassOffset);
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        int i_ = 1;
        StringList j_ = new StringList();
        for (String p: StringExpUtil.getAllTypes(StringList.concat(classNameRes, tempClass)).mid(1)) {
            int loc_ = StringList.getFirstPrintableCharIndex(p);
            j_.add(ResolvingImportTypes.resolveCorrectType(_cont,i_+loc_,p));
            partOffsets.addAllElts(_cont.getAnalyzing().getCurrentParts());
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        importedClassName = AnaTemplates.check(errs_,classNameRes,j_,varsCt_,_cont);
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
        return new StringList(fieldName);
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
}
