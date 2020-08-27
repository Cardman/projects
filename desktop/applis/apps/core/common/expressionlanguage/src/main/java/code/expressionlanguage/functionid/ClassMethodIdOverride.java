package code.expressionlanguage.functionid;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.util.StringMap;

public class ClassMethodIdOverride {
    private final ExecNamedFunctionBlock analyzedMethod;
    private StringMap<ExecOverrideInfo> redirections = new StringMap<ExecOverrideInfo>();

    public ClassMethodIdOverride(ExecNamedFunctionBlock analyzedMethod) {
        this.analyzedMethod = analyzedMethod;
    }

    public ExecNamedFunctionBlock getAnalyzedMethod() {
        return analyzedMethod;
    }

    public ExecOverrideInfo getVal(String _cl) {
        return redirections.getVal(_cl);
    }

    public void put(ContextEl _context, String _cl, GeneStringOverridable _dest) {
        int numberAll_ = _dest.getType().getNumberAll();
        ExecRootBlock exec_ = ExecOperationNode.fetchType(_context, numberAll_);
        ExecNamedFunctionBlock first_ = ExecOperationNode.fetchFunction(numberAll_, _dest.getBlock().getNameNumber(),_context);
        redirections.put(_cl, new ExecOverrideInfo(_dest.getGeneString(),exec_,first_));
    }
}
