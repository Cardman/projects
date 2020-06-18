package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecAffectationOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecStandardInstancingOperation;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ExecElementBlock extends ExecLeaf implements ExecInnerTypeOrElement{

    private final String fieldName;

    private final String value;

    private String importedClassName;

    private CustList<ExecOperationNode> opValue;

    private int fieldNameOffest;

    private int valueOffest;

    private int trOffset;
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public ExecElementBlock(ElementBlock _offset) {
        super(_offset.getOffset());
        fieldName = _offset.getUniqueFieldName();
        value = _offset.getValue();
        fieldNameOffest = _offset.getFieldNameOffset();
        valueOffest = _offset.getValueOffest();
        annotations = _offset.getAnnotations();
        annotationsIndexes = _offset.getAnnotationsIndexes();

    }

    @Override
    public void buildImportedTypes(InfoBlock _key) {
        importedClassName = _key.getImportedClassName();
        partOffsets = _key.getTypePartOffsets();
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
    public void setTrOffset(int trOffset) {
        this.trOffset = trOffset;
    }

    @Override
    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
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
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
        ExecAffectationOperation root_ = (ExecAffectationOperation) opValue.last();
        ExecStandardInstancingOperation inst_ = (ExecStandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
        String fileName_ = _cont.getCoverage().getCurrentFileName();
        CustList<ExecConstructorBlock> ctors_ = getConstructorBodiesById(_cont, cl_, c_);
        if (!ctors_.isEmpty()) {
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = ElUtil.relativize(fileName_,file_+"#m"+ctor_.getNameOffset());
            String tag_ = "<a name=\"m"+fieldNameOffest+"\" title=\""+ ElUtil.transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
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

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getValueEl();
    }
    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
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
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(fieldNameOffest);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ElUtil.tryToCalculate(_cont,el_, trOffset);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }
}
