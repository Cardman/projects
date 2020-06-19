package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.Returnable;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements Returnable,ExecAnnotableParametersBlock {
    private StringList annotations;
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes;

    private final String name;

    private int nameOffset;

    private String importedReturnType;

    private StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private Ints parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

    private final boolean varargs;
    private CustList<StringList> annotationsParams = new CustList<StringList>();
    private CustList<Ints> annotationsIndexesParams;
    private CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    private CustList<CustList<PartOffset>> partOffsetsParams = new CustList<CustList<PartOffset>>();
    private CustList<PartOffset> partOffsetsReturn = new CustList<PartOffset>();

    ExecNamedFunctionBlock(NamedFunctionBlock _offset) {
        super(_offset.getOffset());
        importedParametersTypes = new StringList();
        name = _offset.getName();
        nameOffset = _offset.getNameOffset();
        varargs = _offset.isVarargs();
        access = _offset.getAccess();
        annotations = _offset.getAnnotations();
        annotationsParams = _offset.getAnnotationsParams();
        accessOffset = _offset.getAccessOffset();
        returnTypeOffset = _offset.getReturnTypeOffset();
        parametersNames = _offset.getParametersNames();
        parametersNamesOffset = _offset.getParametersNamesOffset();
        annotationsIndexes = _offset.getAnnotationsIndexes();
        annotationsIndexesParams = _offset.getAnnotationsIndexesParams();
    }

    protected void buildAnnotationsReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
    }
    protected void buildAnnotationsReport(int _index, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexesParams.get(_index).size();
        StringList list_ = annotationsParams.get(_index);
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexesParams.get(_index).get(i);
            int end_ = begin_ + list_.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOpsParams.get(_index).get(i),end_,_parts,0,"",true);
        }
    }

    @Override
    public void reduce(ContextEl _context) {
        reduceBasic(_context);
        CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams_;
        annotationsOpsParams_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        for (CustList<CustList<ExecOperationNode>> l: annotationsOpsParams) {
            CustList<CustList<ExecOperationNode>> l_;
            l_ = new CustList<CustList<ExecOperationNode>>();
            for (CustList<ExecOperationNode> k: l) {
                ExecOperationNode o_ = k.last();
                l_.add(ElUtil.getReducedNodes(o_));
            }
            annotationsOpsParams_.add(l_);
        }
        annotationsOpsParams = annotationsOpsParams_;
    }

    public void reduceBasic(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }

    @Override
    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    public Ints getParametersNamesOffset() {
        return parametersNamesOffset;
    }

    public int getNameOffset() {
        return nameOffset;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public int getReturnTypeOffset() {
        return returnTypeOffset;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    @Override
    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

    @Override
    public String getImportedReturnType() {
        return importedReturnType;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    public void setImportedReturnType(String _importedReturnType) {
        importedReturnType = _importedReturnType;
    }

    public CustList<CustList<PartOffset>> getPartOffsetsParams() {
        return partOffsetsParams;
    }

    public CustList<PartOffset> getPartOffsetsReturn() {
        return partOffsetsReturn;
    }

    public void setPartOffsetsParams(CustList<CustList<PartOffset>> partOffsetsParams) {
        this.partOffsetsParams = partOffsetsParams;
    }

    public void setPartOffsetsReturn(CustList<PartOffset> partOffsetsReturn) {
        this.partOffsetsReturn = partOffsetsReturn;
    }
}
