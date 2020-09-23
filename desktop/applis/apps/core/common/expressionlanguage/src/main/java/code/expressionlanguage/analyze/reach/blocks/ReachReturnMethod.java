package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecReturnMethod;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;

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
            ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), true, expressionOffset,null, returnType);
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getCoverage().putBlockOperations(exec_,getInfo());
            return;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        CustList<ExecOperationNode> op_ = ReachOperationUtil.tryCalculateAndSupply(root, _page);
//        ElUtil.setImplicits(op_.last(), _page, root);
        ExecReturnMethod exec_ = new ExecReturnMethod(getOffset(), false, expressionOffset,op_, returnType);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

//    @Override
//    public void abrupt(AnalyzingEl _anEl) {
//        super.abrupt(_anEl);
//        ReachBracedBlock par_ = getParent();
//        IdList<ReachBracedBlock> pars_ = new IdList<ReachBracedBlock>();
//        ReachBracedBlock a_;
//        if (_anEl.getReachParentsReturnables().isEmpty()) {
//            a_ = _anEl.getReachRoot();
//        } else {
//            a_ = (ReachBracedBlock) _anEl.getReachParentsReturnables().last();
//        }
//        while (par_ != a_) {
//            pars_.add(par_);
//            par_ = par_.getParent();
//        }
//        if (a_ instanceof ReachEval) {
//            IdMap<ReachReturnMethod, ReachEval> breakables_ = _anEl.getReachReturnables();
//            IdMap<ReachReturnMethod, IdMap<ReachEval, IdList<ReachBracedBlock>>> breakablesAncestors_ = _anEl.getReachReturnablesAncestors();
//            IdMap<ReachEval, IdList<ReachBracedBlock>> id_;
//            id_ = new IdMap<ReachEval, IdList<ReachBracedBlock>>();
//            id_.put((ReachEval) a_, pars_);
//            breakablesAncestors_.put(this, id_);
//            breakables_.put(this, (ReachEval) a_);
//        }
//    }

}
