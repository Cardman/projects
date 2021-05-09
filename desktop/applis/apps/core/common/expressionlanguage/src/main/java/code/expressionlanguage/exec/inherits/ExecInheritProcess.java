package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractInheritProcess;
import code.expressionlanguage.common.MappingPairs;

public final class ExecInheritProcess extends AbstractInheritProcess {
    private final ContextEl context;

    public ExecInheritProcess(ContextEl _context) {
        this.context = _context;
    }

    @Override
    protected MappingPairs getExecutingCorrect(String _a, String _p) {
        return ExecInherits.getExecutingCorrect(_a,_p,context);
    }
}
