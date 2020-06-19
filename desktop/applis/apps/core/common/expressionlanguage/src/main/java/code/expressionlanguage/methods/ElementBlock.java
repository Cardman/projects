package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ElementBlock extends Leaf implements InnerTypeOrElement{

    private final String fieldName;

    private final String value;

    private final String tempClass;

    private int tempClassOffset;

    private String importedClassName;

    private int fieldNameOffest;

    private int valueOffest;

    private StringList annotations = new StringList();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String className;

    public ElementBlock(EnumBlock _m, OffsetStringInfo _fieldName,
                        OffsetStringInfo _type,
                        OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        className = _m.getOriginalName();
        fieldNameOffest = _fieldName.getOffset();
        valueOffest = _value.getOffset();
        fieldName = _fieldName.getInfo();
        value = _value.getInfo();
        tempClass = _type.getInfo();
        tempClassOffset = _type.getOffset();
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
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
        return new StringList(fieldName);
    }

    @Override
    public String getUniqueFieldName() {
        return fieldName;
    }

    @Override
    public void retrieveNames(ContextEl _cont, StringList _fieldNames) {
        CustList<PartOffsetAffect> fields_ = new CustList<PartOffsetAffect>();
        fields_.add(new PartOffsetAffect(new PartOffset(fieldName,valueOffest),true));
        FieldBlock.checkFieldsNames(_cont,this,_fieldNames,fields_);
    }

    public String getValue() {
        return value;
    }

    @Override
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(tempClassOffset);
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        int len_ = -className.length();
        String fullClassName_ = StringList.concat(className, tempClass);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,len_,fullClassName_);
        partOffsets.addAllElts(_cont.getCoverage().getCurrentParts().mid(2));
    }

    @Override
    public CustList<PartOffset> getTypePartOffsets() {
        return partOffsets;
    }

    public void buildExpressionLanguageReadOnly(ContextEl _cont, ExecInnerTypeOrElement _exec) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(fieldNameOffest);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(fieldName,"=",newKeyWord_," ",importedClassName, PAR_LEFT, value, PAR_RIGHT);
        int tr_ = valueOffest -1 -fieldName.length() - fieldNameOffest - 2 - newKeyWord_.length() - importedClassName.length();
        _exec.setTrOffset(tr_);
        page_.setTranslatedOffset(tr_);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        _cont.getCoverage().putBlockOperations(_cont, (ExecBlock) _exec,this);
        _cont.getCoverage().putBlockOperations(_cont,this);
        _exec.setOpValue(ElUtil.getAnalyzedOperationsReadOnly(fullInstance_, _cont, new Calculation(fieldName)));
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

    public void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ex) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            ops_.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }


    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }

}
