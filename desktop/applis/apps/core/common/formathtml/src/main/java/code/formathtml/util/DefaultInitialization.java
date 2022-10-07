package code.formathtml.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.formathtml.DualNavigationContext;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultInitialization {
    private final String lgCode;

    private final String fileName;
    private final StringMap<String> fileNames;

    private final BeanCustLgNames stds;

    private final String classDbName;

    private final String methodName;
    private ContextEl context;

    public DefaultInitialization(BeanCustLgNames _lgNames, String _lgCode, String _fileName, StringMap<String> _fileNames, String _clName, String _methodName) {
        stds = _lgNames;
        lgCode = _lgCode;
        fileName = _fileName;
        fileNames = _fileNames;
        classDbName = _clName;
        methodName = _methodName;
    }
    public String execute(Navigation _nav) {
        String content_ = fileNames.getVal(fileName);
        if (content_ == null) {
            return "";
        }
        DefaultConfigurationLoader def_ = new DefaultConfigurationLoader(stds);
        AbstractFileBuilder fileBuilder_;
        fileBuilder_ = stds.newFileBuilder();
        DualAnalyzedContext du_ = _nav.loadConfiguration(content_, lgCode, stds, fileBuilder_, def_);
        if (du_.getContext().isKo()) {
            return "";
        }
        _nav.setFiles(fileNames);
        ContextEl ctx_ = stds.setupAll(new DualNavigationContext(_nav, du_));
        if (ctx_ == null) {
            return "";
        }
        context = ctx_;
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        String arrStr_ = StringExpUtil.getPrettyArrayType(stds.getContent().getCharSeq().getAliasString());
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, methodName, new StringList(arrStr_,arrStr_));
        ExecRootBlock classBody_ = ctx_.getClasses().getClassBody(classDbName);
        if (classBody_ != null) {
            CustList<ExecOverridableBlock> methods_ = ExecClassesUtil.getMethodBodiesById(classBody_, id_);
            if (!methods_.isEmpty()) {
                ProcessMethod.initializeClass(classDbName, classBody_,ctx_, rendStackCall_.getStackCall());
                if (ctx_.callsOrException(rendStackCall_.getStackCall())) {
                    return afterActionWithoutRemove(ctx_, rendStackCall_);
                }
                Argument arg_ = new Argument();
                CustList<Argument> args_ = args(arrStr_);
                ExecNamedFunctionBlock method_ = methods_.first();
                ExecTypeFunction pair_ = new ExecTypeFunction(classBody_, method_);
                ArgumentListCall argList_ = ArgumentListCall.wrapCall(args_);
                Parameters parameters_ = ExecTemplates.wrapAndCall(pair_, new ExecFormattedRootBlock(classBody_, classDbName),arg_, ctx_, rendStackCall_.getStackCall(), argList_);
                Argument out_ = ProcessMethod.calculate(new CustomFoundMethod(arg_, new ExecFormattedRootBlock(classBody_, classDbName), pair_, parameters_), ctx_, rendStackCall_.getStackCall()).getValue();
                if (ctx_.callsOrException(rendStackCall_.getStackCall())) {
                    return afterActionWithoutRemove(ctx_, rendStackCall_);
                }
                _nav.setDataBaseStruct(out_.getStruct());
            }
        }
        stds.initializeRendSessionDoc(ctx_, _nav, rendStackCall_);
        return afterActionWithoutRemove(ctx_, rendStackCall_);
    }

    private CustList<Argument> args(String _arrString) {
        CustList<Argument> args_ = new CustList<Argument>();
        int len_ = fileNames.size();
        ArrayStruct arrNames_ = new ArrayStruct(len_, _arrString);
        for (int i = 0; i < len_; i++) {
            arrNames_.set(i, new StringStruct(fileNames.getKey(i)));
        }
        ArrayStruct arrContents_ = new ArrayStruct(len_, _arrString);
        for (int i = 0; i < len_; i++) {
            arrContents_.set(i, new StringStruct(fileNames.getValue(i)));
        }
        args_.add(new Argument(arrNames_));
        args_.add(new Argument(arrContents_));
        return args_;
    }

    public ContextEl getContext() {
        return context;
    }

    public static String afterActionWithoutRemove(ContextEl _ctx, RendStackCall _stackCall) {
        String error_ = ProcessMethod.error(_ctx, _stackCall.getStackCall());
        if (error_ != null) {
            _stackCall.getStackCall().setNullCallingState();
        }
        return error_;
    }
}
