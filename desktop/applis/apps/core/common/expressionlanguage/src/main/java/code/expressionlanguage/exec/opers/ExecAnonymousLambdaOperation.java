package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnonymousFunctionBlock;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAnonymousLambdaOperation extends ExecAbstractLambdaOperation {
    private ClassMethodId method;
    private ExecAnnotableParametersBlock functionBlock;
    private ExecNamedFunctionBlock function;
    private ExecRootBlock declaring;
    public ExecAnonymousLambdaOperation(AnonymousLambdaOperation _l) {
        super(_l);
    }
    public void setExecAnonymousLambdaOperation(AnonymousLambdaOperation _s) {
        method = _s.getMethod();
    }
    public void setExecAnonymousLambdaOperation(AnonymousLambdaOperation _s, AnalyzedPageEl _page) {
        setExecAnonymousLambdaOperation(_s);
        declaring = _page.getMapTypes().getValue(_s.getRootNumber());
        AnonymousFunctionBlock block_ = _s.getBlock();
        block_.setNumberLambda(_page.getMapAnonLambda().size());
        ExecAnonymousFunctionBlock fct_ = new ExecAnonymousFunctionBlock(block_);
        fct_.setParentType(declaring);
        _page.getMapAnonLambda().addEntry(block_,fct_);
        fct_.buildImportedTypes(block_);
        function = fct_;
        functionBlock = fct_;
    }

    public ExecNamedFunctionBlock getFunction() {
        return function;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), method, getReturnFieldType()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType) {
        return ExecMethodLambdaOperation.newLambda(_previous, _conf, foundClass, method, returnFieldType, 0, false, false, false, false, isShiftArgument(), isSafeInstance(),
                getResultClass().getName(), _conf.getLastPage(), getFileName(),functionBlock,function,declaring);
    }
}
