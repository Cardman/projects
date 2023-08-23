package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.DirectStdRefectMethodPageEl;
import code.expressionlanguage.exec.calls.LambdaDirectStdRefectMethodPageEl;
import code.expressionlanguage.exec.calls.ReflectConstructorPageEl;
import code.expressionlanguage.exec.calls.ReflectLambdaConstructorPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.StdParamsOperable;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class StdMethodCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final ArgumentListCall args;
    private final String own;
    private final boolean exiting;
    private final StandardNamedFunction fct;

    public StdMethodCheckedExecOperationNodeInfos(String _aliasObject, StdParamsOperable _s, String _cl, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Struct _i, boolean _ex) {
        super(ExecFormattedRootBlock.defValue(), _i, null);
        this.own = _cl;
        this.exiting = _ex;
        this.fct = _s.fct();
        this.args = _s.args(_nodes,_aliasObject).getArguments();
    }

    public StdMethodCheckedExecOperationNodeInfos(ReflectConstructorPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), NullStruct.NULL_VALUE, null);
        this.own = "";
        this.exiting = _s.getCheckedParams() == 2;
        this.fct = _s.getMetaInfo().getStandardConstructor();
        this.args = ArgumentListCall.wrapCall(_s.getArrRef().getArray().listArgs());
    }

    public StdMethodCheckedExecOperationNodeInfos(ReflectLambdaConstructorPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), NullStruct.NULL_VALUE, null);
        this.own = "";
        this.exiting = _s.getCheckedParams() == 2;
        this.fct = _s.getMetaInfo().getStandardConstructor();
        this.args = _s.getArray();
    }

    public StdMethodCheckedExecOperationNodeInfos(DirectStdRefectMethodPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), ArgumentListCall.toStr(_s.getInstance()), null);
        this.own = _s.getClassName().getFormatted();
        this.exiting = _s.getCheckedParams() == 2;
        this.fct = _s.getStdCallee();
        this.args = ArgumentListCall.wrapCall(_s.getArrRef().getArray().listArgs());
    }

    public StdMethodCheckedExecOperationNodeInfos(LambdaDirectStdRefectMethodPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), ArgumentListCall.toStr(_s.getInstance()), null);
        this.own = _s.getClassName().getFormatted();
        this.exiting = _s.getCheckedParams() == 2;
        this.fct = _s.getStdCallee();
        this.args = _s.getArray();
    }

    public String getOwn() {
        return own;
    }

    public StandardNamedFunction getFct() {
        return fct;
    }

    public ArgumentListCall getArgs() {
        return args;
    }

    public boolean isExiting() {
        return exiting;
    }
}
