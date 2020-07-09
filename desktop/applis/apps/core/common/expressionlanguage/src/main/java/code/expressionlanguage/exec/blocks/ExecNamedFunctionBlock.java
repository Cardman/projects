package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.util.CustList;
import code.util.StringList;

public abstract class ExecNamedFunctionBlock extends ExecMemberCallingsBlock implements ExecAnnotableParametersBlock {

    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();

    private final String name;

    private int nameOffset;

    private String importedReturnType;

    private StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private final AccessEnum access;

    private final boolean varargs;

    private CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    ExecNamedFunctionBlock(NamedFunctionBlock _offset) {
        super(_offset.getOffset());
        importedParametersTypes = new StringList();
        name = _offset.getName();
        nameOffset = _offset.getNameOffset();
        varargs = _offset.isVarargs();
        access = _offset.getAccess();
        returnTypeOffset = _offset.getReturnTypeOffset();
        parametersNames = _offset.getParametersNames();
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
                l_.add(ExpressionLanguage.getReducedNodes(o_));
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
            annotationsOps_.add(ExpressionLanguage.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }

    @Override
    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }

    public int getNameOffset() {
        return nameOffset;
    }

    public int getReturnTypeOffset() {
        return returnTypeOffset;
    }

    public String getName() {
        return name;
    }

    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

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

}
