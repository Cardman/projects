package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaRecordConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecRecordConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecRootBlock pair;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final CustList<ExecFormattedRootBlock> supInts;

    public ExecRecordConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecRootBlock _pair, CustList<ExecNamedFieldContent> _namedFields, CustList<ExecFormattedRootBlock> _supIntsList) {
        super(_opCont, _lamCont);
        pair = _pair;
        namedFields = _namedFields;
        supInts = _supIntsList;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        ExecFormattedRootBlock ownerType_ = formatVarType(_stack);
        String clArg_ = formatVarTypeRes(_stack);
        Struct res_ = newLambda(getLambdaCommonContent(),previous_,ownerType_, clArg_, pair, namedFields, supInts);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _cont, Struct _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg,
                                   ExecRootBlock _root, CustList<ExecNamedFieldContent> _namedFields, CustList<ExecFormattedRootBlock> _supInts) {
        return new LambdaRecordConstructorStruct(_cont,_previous,_root, _clArg, _ownerType, _namedFields, _supInts);
    }

}
