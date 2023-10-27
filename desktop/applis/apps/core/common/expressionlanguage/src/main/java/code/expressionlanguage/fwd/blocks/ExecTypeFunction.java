package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class ExecTypeFunction {
    private final ExecRootBlock type;
    private final int typeNb;
    private final ExecNamedFunctionBlock fct;
    private final int fctNb;

    public ExecTypeFunction(ExecFormattedRootBlock _type, ExecNamedFunctionBlock _fct) {
        type = _type.getRootBlock();
        typeNb = ExecRootBlock.numberType(_type.getRootBlock());
        fct = _fct;
        fctNb = fctNb(_fct);
    }

    public ExecTypeFunction(ExecRootBlock _type, ExecNamedFunctionBlock _fct) {
        this(_type, ExecRootBlock.numberType(_type), _fct, fctNb(_fct));
    }

    public ExecTypeFunction(ExecRootBlock _type, int _tNb, ExecNamedFunctionBlock _f, int _nb) {
        type = _type;
        typeNb = _tNb;
        fct = _f;
        fctNb = _nb;
    }

    public static int fctNb(ExecMemberCallingsBlock _r) {
        if (_r != null) {
            return _r.getMem();
        }
        return -1;
    }

    public int getFctNb() {
        return fctNb;
    }

    public int getTypeNb() {
        return typeNb;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public ExecNamedFunctionBlock getFct() {
        return fct;
    }
}
