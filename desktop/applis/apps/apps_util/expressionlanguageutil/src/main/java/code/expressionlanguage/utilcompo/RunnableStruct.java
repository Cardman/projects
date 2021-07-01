package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.AbstractReflectElement;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithParentStruct;
import code.util.CustList;

public final class RunnableStruct implements WithParentStruct, EnumerableStruct,Runnable {
    private Struct parent;

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private final String name;
    private final int ordinal;
    private final String parentClassName;
    private final CommonExecutionInfos executionInfos;

    RunnableStruct(ContextEl _original, String _className,
                   String _name, int _ordinal,
                   CustList<ClassFieldStruct> _fields, Struct _parent, String _parendClassName) {
        executionInfos = _original.getExecutionInfos();
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
        parentClassName = _parendClassName;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    @Override
    public Struct getParent() {
        return parent;
    }

    @Override
    public String getParentClassName() {
        return parentClassName;
    }

    @Override
    public void setParent(Struct _parent) {
        parent = _parent;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }
    @Override
    public void run() {
        RunnableContextEl r_ = new RunnableContextEl(InitPhase.NOTHING, executionInfos);
        setupThread(r_);
        invoke(this,r_, ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunnableType(), ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunMethod(), new ArgumentListCall());
    }

    public static void invoke(Struct _instance, RunnableContextEl _r, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _method, ArgumentListCall _argList) {
        String base_ = StringExpUtil.getIdFromAllTypes(_instance.getClassName(_r));
        ExecOverrideInfo mId_ = _rootBlock.getRedirections().getVal(_method,base_);
        if (mId_ == null) {
            _r.getCustInit().removeThreadFromList(_r);
            return;
        }
        Argument arg_ = new Argument(_instance);
        RunnableStruct.invoke(arg_, mId_.getClassName(), _r, mId_.getPair(), StackCall.newInstance(InitPhase.NOTHING,_r), _argList);
    }
    public static Argument invoke(Argument _global, ExecFormattedRootBlock _class, RunnableContextEl _cont, ExecTypeFunction _pair, StackCall _stackCall, ArgumentListCall _argList) {
        Parameters parameters_ = ExecTemplates.wrapAndCall(_pair, _class, _global, _cont, _stackCall, _argList);
        Argument arg_ = ProcessMethod.calculateArgument(new CustomFoundMethod(_global, _class, _pair, parameters_), _cont, _stackCall).getValue();
        _cont.getCustInit().prExc(_cont, _stackCall);
        return arg_;
    }
    public static Argument reflect(RunnableContextEl _cont, AbstractReflectElement _ref, StackCall _stackCall) {
        Argument arg_ = ProcessMethod.reflectArgument(_cont,_ref, _stackCall).getValue();
        _cont.getCustInit().prExc(_cont, _stackCall);
        return arg_;
    }
    public static long setupThread(RunnableContextEl _r) {
        long nb_ = _r.getCustInit().increment();
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(CustAliases.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(nb_);
        dtPart_.append(".txt");
        _r.getCustInit().putNewCustTreadIdDate(_r, dtPart_.toString());
        return nb_;
    }
}
