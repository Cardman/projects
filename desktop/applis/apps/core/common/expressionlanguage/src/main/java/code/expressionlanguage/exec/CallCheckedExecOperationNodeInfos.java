package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.AbstractFormatParamChecker;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
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
    private final Cache cache;
    private final boolean exit;

    public CallCheckedExecOperationNodeInfos(ContextEl _context, boolean _exit, ArrayCustWrapper _a) {
        super(ExecFormattedRootBlock.defValue(), _a.getParent(), null);
        args = _a.args();
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, boolean _exit, ArrayCustWrapper _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.getParent(), null);
        args = _a.argsWrite(_right);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecFctOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last), null);
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last), null);
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _exit, ExecCustArrOperation _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.instanceWrite(_nodes), null);
        args = _a.argsWrite(_right,_nodes);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrReadOperation _a) {
        super(ExecFormattedRootBlock.defValue(), _a.instance(_nodes, _last), null);
        args = _a.args(_context,getInstance(),_nodes);
        poly = _a.poly(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
    }

    public CallCheckedExecOperationNodeInfos(ContextEl _context, IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _last, boolean _exit, ExecCustArrWriteOperation _a, Struct _right) {
        super(ExecFormattedRootBlock.defValue(), _a.instanceWrite(_nodes, _last), null);
        args = _a.argsWrite(_context,getInstance(),_nodes,_right);
        poly = _a.polyWrite(_context, getInstance());
        formattedRootBlock = ExecFctOperation.glClass(_context, getInstance(), poly.getClassName());
        cache = null;
        this.exit = _exit;
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

    public Cache getCache() {
        return cache;
    }

    public boolean isExit() {
        return exit;
    }
}
