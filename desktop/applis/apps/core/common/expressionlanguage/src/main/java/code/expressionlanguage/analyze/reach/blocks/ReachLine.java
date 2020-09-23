package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecDeclareVariable;
import code.expressionlanguage.exec.blocks.ExecLine;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ReachLine extends ReachLeaf implements ReachBuildableElMethod {
    private String importedClass;
    private int expressionOffset;
    private OperationNode root;

    protected ReachLine(Line _info) {
        super(_info);
        expressionOffset = _info.getExpressionOffset();
        root = _info.getRoot();
        importedClass = _info.getImportedClass();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        CustList<ExecOperationNode> op_ = ReachOperationUtil.tryCalculateAndSupply(root, _page);
        ExecDeclareVariable ex_ = _page.getExecDeclareVariable();
        if (ex_ != null) {
            ex_.setImportedClassName(importedClass);
        }
        _page.setExecDeclareVariable(null);
        ExecLine exec_ = new ExecLine(getOffset(), expressionOffset,op_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
}
