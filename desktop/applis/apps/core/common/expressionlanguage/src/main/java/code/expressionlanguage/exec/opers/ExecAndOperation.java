package code.expressionlanguage.exec.opers;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecAndOperation extends ExecQuickOperation {

    public ExecAndOperation(ExecOperationContent _opCont, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, int _opOffset) {
        super(_opCont, _staticEltContent, _pair, _converter,_opOffset);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
