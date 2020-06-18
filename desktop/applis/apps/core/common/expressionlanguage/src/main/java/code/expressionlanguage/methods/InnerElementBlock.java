package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.types.ResolvingSuperTypes;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecAffectationOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecStandardInstancingOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class InnerElementBlock extends RootBlock implements InnerTypeOrElement,UniqueRootedBlock {

    private final String fieldName;

    private final String value;

    private final String tempClass;

    private int tempClassOffset;

    private String importedClassName;

    private CustList<ExecOperationNode> opValue;

    private int fieldNameOffest;

    private int valueOffest;

    private int trOffset;

    private final StringList allSuperTypes = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String className;

    public InnerElementBlock(EnumBlock _m, String _pkgName,OffsetStringInfo _fieldName,
                             OffsetStringInfo _type,
                             OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_fieldName.getOffset(), 0, _fieldName.getInfo(), _pkgName, new OffsetAccessInfo(0,AccessEnum.PUBLIC), "", new IntMap< String>(), _offset);
        className = _m.getOriginalName();
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

    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
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
    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void retrieveNames(ContextEl _cont, StringList _fieldNames) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new PartOffset(fieldName,valueOffest),true));
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
        String idType_ = getFullName();
        String fullInstance_ = StringList.concat(fieldName,"=",newKeyWord_," ",idType_, PAR_LEFT, value, PAR_RIGHT);
        trOffset = valueOffest  -1 -fieldName.length()- fieldNameOffest - 2 - newKeyWord_.length() - idType_.length();
        _exec.setTrOffset(trOffset);
        page_.setTranslatedOffset(trOffset);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        _cont.getCoverage().putBlockOperations(_cont, (ExecBlock) _exec,this);
        _cont.getCoverage().putBlockOperations(_cont,this);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(fullInstance_, _cont, new Calculation(fieldName));
        _exec.setOpValue(opValue);
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
        int len_ = -className.length();
        String fullClassName_ =  StringList.concat(className, tempClass);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,len_,fullClassName_);
        partOffsets.addAllElts(_cont.getCoverage().getCurrentParts().mid(2));
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
        return partOffsets;
    }
    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public boolean isFinalField() {
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

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }

    @Override
    public void buildDirectGenericSuperTypes(ContextEl _classes,ExecRootBlock _exec) {
        IntMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        int i_ = 0;
        importedDirectSuperInterfaces.clear();
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            String s_ = ResolvingSuperTypes.resolveTypeInherits(_classes,s, _exec,index_);
            String c_ = getImportedDirectBaseSuperType(i_);
            _classes.addErrorIfNoMatch(s_,c_,this,index_);
            i_++;
            importedDirectSuperClass = s_;
        }
    }

    @Override
    public void buildErrorDirectGenericSuperTypes(ContextEl _classes) {
        importedDirectSuperInterfaces.clear();
        importedDirectSuperClass = _classes.getStandards().getAliasObject();
    }

    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }


    public int getTrOffset() {
        return trOffset;
    }
}
