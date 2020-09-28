package code.formathtml.exec;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class RendAndOperation extends RendQuickOperation {

    public RendAndOperation(ExecOperationContent _content, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_content, _staticEltContent, _named, _rootBlock, _converter);
    }

    @Override
    public boolean match(Struct _struct) {
        return BooleanStruct.isFalse(_struct);
    }
}
