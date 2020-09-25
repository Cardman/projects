package code.expressionlanguage.exec.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
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

    public void put(String _cl, GeneStringOverridable _dest, AnalyzedPageEl _page) {
        int numberAll_ = _dest.getType().getNumberAll();
        ExecRootBlock exec_ = ForwardInfos.fetchType(numberAll_, _page);
        ExecNamedFunctionBlock first_ = ForwardInfos.fetchFunction(numberAll_, _dest.getBlock().getNameNumber(), _page);
        redirections.put(_cl, new ExecOverrideInfo(_dest.getGeneString(),exec_,first_));
    }
}
