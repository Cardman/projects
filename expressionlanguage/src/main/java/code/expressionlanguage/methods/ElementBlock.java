package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.StaticInitPageEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.exec.ExecStandardInstancingOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ElementBlock extends Leaf implements InnerTypeOrElement{

    private final String fieldName;

    private final String value;

    private final String tempClass;

    private int tempClassOffset;

    private String importedClassName;

    private CustList<ExecOperationNode> opValue;

    private int fieldNameOffest;

    private int valueOffest;

    private int trOffset;
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ElementBlock(OffsetStringInfo _fieldName,
                        OffsetStringInfo _type,
                        OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_offset);
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
    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public AccessEnum getAccess() {
        return AccessEnum.PUBLIC;
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
    public String getClassName() {
        Block b_ = getParent();
        RootBlock r_ = (RootBlock) b_;
        return r_.getFullName();
    }

    @Override
    public StringList getFieldName() {
        return new StringList(fieldName);
    }

    public String getUniqueFieldName() {
        return fieldName;
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
        String className_ = getClassName();
        int len_ = -className_.length();
        className_ = StringList.concat(className_, tempClass);
        importedClassName = _cont.resolveCorrectType(len_,className_);
        partOffsets.addAllElts(_cont.getCoverage().getCurrentParts().mid(2));
    }
    @Override
    public void setAssignmentBefore(Analyzable _an) {
        Block prev_ = getPreviousSibling();
        AssignedVariables ass_;
        if (prev_ == null) {
            ass_ = _an.getContextEl().getAssignedVariables().getFinalVariablesGlobal();
            IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
            id_.put(this, ass_);
        } else {
            IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
            AssignedVariables parAss_ = id_.getVal(prev_);
            AssignedVariables assBl_ = buildNewAssignedVariable();
            assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(fieldNameOffest);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(newKeyWord_," ",importedClassName, PAR_LEFT, value, PAR_RIGHT);
        trOffset = valueOffest - fieldNameOffest - 2 - newKeyWord_.length() - importedClassName.length();
        page_.setTranslatedOffset(trOffset);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        _cont.getCoverage().putBlockOperations(_cont,this);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(fullInstance_, _cont, new Calculation(fieldName));
        page_.setTranslatedOffset(0);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(fieldNameOffest);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String fullInstance_ = StringList.concat(newKeyWord_," ",importedClassName, PAR_LEFT, value, PAR_RIGHT);
        trOffset = valueOffest - fieldNameOffest - 2 - newKeyWord_.length() - importedClassName.length();
        page_.setTranslatedOffset(trOffset);
        int index_ = getIndex();
        _cont.setCurrentChildTypeIndex(index_);
        _cont.getCoverage().putBlockOperations(_cont,this);
        opValue = ElUtil.getAnalyzedOperations(fullInstance_, _cont, new Calculation(fieldName));
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
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            annotationsOps.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
        }
    }
    @Override
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
    }
    @Override
    public StringList getAnnotations() {
        return annotations;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }
    @Override
    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public void setAssignmentAfter(Analyzable _an) {
        AssignedVariablesBlock glAss_ = _an.getContextEl().getAssignedVariables();
        AssignedVariables varsAss_ = glAss_.getFinalVariables().getVal(this);
        StringMap<SimpleAssignment> as_ = varsAss_.getFieldsRoot();
        as_.putAllMap(AssignmentsUtil.assignAfterClassic(varsAss_.getFieldsRootBefore()));
        as_.put(fieldName, Assignment.assignClassic(true, false));
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.getInitFields().add(fieldName);
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(fieldNameOffest);
            ip_.setOffset(0);
            Struct struct_;
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = el_.calculateMember(_cont, trOffset);
            if (_cont.callsOrException()) {
                return;
            }
            struct_ = arg_.getStruct();
            ip_.clearCurrentEls();
            RootBlock r_ = (RootBlock) getParent();
            ClassField staticField_ = new ClassField(r_.getFullName(), fieldName);
            _cont.getClasses().initializeStaticField(staticField_, struct_);
        }
        processBlock(_cont);
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getValueEl();
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
        ExecOperationNode root_ = opValue.last();
        String cl_ = ((ExecStandardInstancingOperation)root_).getClassName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        ConstructorId c_ = ((ExecStandardInstancingOperation)root_).getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String file_ = ((RootBlock) type_).getFile().getRenderFileName();
        String fileName_ = _cont.getCoverage().getCurrentFileName();
        CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_cont, cl_, c_);
        if (!ctors_.isEmpty()) {
            ConstructorBlock ctor_ = (ConstructorBlock) ctors_.first();
            String rel_ = ElUtil.relativize(fileName_,file_+"#m"+ctor_.getNameOffset());
            String tag_ = "<a name=\"m"+fieldNameOffest+"\" title=\""+ cl_ +"."+ c_.getSignature(_cont)+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,fieldNameOffest));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,fieldNameOffest+fieldName.length()));
        } else {
            String tag_ = "<a name=\"m"+fieldNameOffest+"\">";
            _parts.add(new PartOffset(tag_,fieldNameOffest));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,fieldNameOffest+fieldName.length()));
        }
        _parts.addAllElts(partOffsets);
        int blOffset_ = valueOffest;
        int endBl_ = valueOffest + value.length();
        ElUtil.buildCoverageReport(_cont,blOffset_,this,opValue,endBl_,_parts,trOffset-1,fieldName,false);
    }
}
