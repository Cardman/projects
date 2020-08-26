package code.expressionlanguage.functionid;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
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

    public void put(ContextEl _context, String _cl, ClassMethodId _dest) {
        ExecNamedFunctionBlock first_ = ExecBlock.getMethodBodiesById(_context, StringExpUtil.getIdFromAllTypes(_dest.getClassName()), _dest.getConstraints()).first();
        redirections.put(_cl, new ExecOverrideInfo(_dest.getClassName(),first_));
    }
}
