package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.instr.PartOffsetAffect;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
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
    private OperationNode root;
    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private int trOffset;
    private final StringList nameErrors = new StringList();
    private int fieldNumber;
    private EnumBlock parentEnum;
    private StringList fieldList = new StringList();
    private CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();

    public ElementBlock(EnumBlock _m, OffsetStringInfo _fieldName,
                        OffsetStringInfo _type,
                        OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
        parentEnum = _m;
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
        return fieldList;
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
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(tempClassOffset);
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        int i_ = 1;
        StringList j_ = new StringList();
        String fullName_ = parentEnum.getFullName();
        for (String p: StringExpUtil.getAllTypes(StringList.concat(fullName_, tempClass)).mid(1)) {
            int loc_ = StringList.getFirstPrintableCharIndex(p);
            j_.add(ResolvingImportTypes.resolveCorrectType(_cont,i_+loc_,p));
            partOffsets.addAllElts(_cont.getAnalyzing().getCurrentParts());
            i_ += p.length() + 1;
        }
        StringMap<StringList> varsCt_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        StringList errs_ = new StringList();
        importedClassName = AnaTemplates.check(errs_,fullName_,j_,varsCt_,_cont);
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

    public void buildExpressionLanguageReadOnly(ContextEl _cont, ExecInnerTypeOrElement _exec) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(fieldNameOffest);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(fieldName,"=",newKeyWord_, PAR_LEFT, value, PAR_RIGHT);
        int tr_ = valueOffest -1 -fieldName.length() - fieldNameOffest - 1 - newKeyWord_.length();
        trOffset = tr_;
        _exec.setTrOffset(tr_);
        page_.setTranslatedOffset(tr_);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        page_.getCoverage().putBlockOperations(_cont, (ExecBlock) _exec,this);
        page_.getCoverage().putBlockOperations(_cont,this);
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

    public void buildAnnotations(ContextEl _context, ExecAnnotableBlock _ex) {
        CustList<CustList<ExecOperationNode>> ops_ = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        roots = new CustList<OperationNode>();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            ops_.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
            roots.add(page_.getCurrentRoot());
        }
        _ex.getAnnotationsOps().addAllElts(ops_);
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }


    public OperationNode getRoot() {
        return root;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public int getFieldNameOffest() {
        return fieldNameOffest;
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
    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
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
