package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecReturnMethod;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ReachReturnMethod extends ReachAbruptBlock {
    private String returnType;
    private OperationNode root;
    private int expressionOffset;

    protected ReachReturnMethod(ReturnMethod _info) {
        super(_info);
        returnType = _info.getReturnType();
        expressionOffset = _info.getExpressionOffset();
        root = _info.getRoot();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        if (root == null) {
//            ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), true, expressionOffset,null, returnType);
//            exec_.setFile(_page.getBlockToWrite().getFile());
//            _page.getBlockToWrite().appendChild(exec_);
//            _page.getCoverage().putBlockOperations(exec_,getInfo());
            return;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        ReachOperationUtil.tryCalculate(root, _page);
//        CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, root);
//        ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), false, expressionOffset,op_, returnType);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

}
