package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.AbstractRefectLambdaMethodPageEl;
import code.expressionlanguage.exec.calls.AbstractRefectMethodPageEl;
import code.expressionlanguage.exec.inherits.AbstractFormatParamChecker;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.ArrayCustWrapper;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class CallCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos{

    private final ArgumentListCall args;
    private final ExecOverrideInfo poly;
    private final ExecFormattedRootBlock formattedRootBlock;
    private final boolean exit;

    public CallCheckedExecOperationNodeInfos(ContextEl _context, boolean _exit, ArrayCustWrapper _a) {
        super(ExecFormattedRootBlock.defValue(), _a.getParent());
        args = _a.args();
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, boolean _exit, ArrayCustWrapper _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.getParent());
        args = _a.argsWrite(_right);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecFctOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last));
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last));
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _exit, ExecCustArrOperation _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.instanceWrite(_nodes));
        args = _a.argsWrite(_right,_nodes);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrReadOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last));
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrWriteOperation _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.instanceWrite(_nodes, _last));
        args = _a.argsWrite(_context,getInstance(),_nodes,_right);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, AbstractRefectMethodPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), ArgumentListCall.toStr(_s.getInstance()), _s.getCache());
        this.exit = _s.getCheckedParams() == 2;
        this.args = ArgumentListCall.wrapCall(_s.getArrRef().getArray().listArgs());
        this.poly = new ExecOverrideInfo(_s.getClassName(),_s.getPair());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, AbstractRefectLambdaMethodPageEl _s) {
        super(ExecFormattedRootBlock.defValue(), _s.getParent(), _s.getCache());
        this.exit = _s.getCheckedParams() == 2;
        this.args = _s.getArray();
        this.poly = new ExecOverrideInfo(_s.getClassName(),_s.getPair());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
    }

    public LambdaStruct lda() {
        return AbstractFormatParamChecker.matchAbstract(getInstance(),getPoly());
    }

    public ArgumentListCall getArgs() {
        return args;
    }

    public ExecOverrideInfo getPoly() {
        return poly;
    }

    public ExecFormattedRootBlock getFormattedRootBlock() {
        return formattedRootBlock;
    }


    public boolean isExit() {
        return exit;
    }
}
