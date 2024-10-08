package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AbstractSymbolFactory;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LoggableLgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.formathtml.DualNavigationContext;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultInitialization {
    private final String lgCode;
    private final AbstractSymbolFactory symbolFactory;

    private final String fileName;
    private final StringMap<String> fileNames;

    private final BeanCustLgNames stds;

    private LoggableLgNames log;
    private ContextEl context;
    private String keyWordDigit = "";

    public DefaultInitialization(BeanCustLgNames _lgNames, AbstractSymbolFactory _fact, String _lgCode, String _fileName, StringMap<String> _fileNames) {
        stds = _lgNames;
        symbolFactory = _fact;
        lgCode = _lgCode;
        fileName = _fileName;
        fileNames = _fileNames;
    }

    public void setLog(LoggableLgNames _l) {
        this.log = _l;
    }

    public String execute(Navigation _nav, AbsContextGenerator _gene) {
        String content_ = fileNames.getVal(fileName);
        if (content_ == null) {
            return "";
        }
        DefaultConfigurationLoader def_ = new DefaultConfigurationLoader(stds,log);
        AbstractFileBuilder fileBuilder_;
        fileBuilder_ = stds.newFileBuilder();
        DualAnalyzedContext du_ = _nav.loadConfiguration(content_, lgCode, stds, fileBuilder_, stds.newFileBuilders(), def_);
        if (du_.getContext().isKo()) {
            return "";
        }
        keyWordDigit = du_.getAnalyzed().getKeyWords().getKeyWordNbDig();
        du_.getAnalyzed().setAbstractSymbolFactory(symbolFactory);
        _nav.setFiles(fileNames);
        ContextEl ctx_ = stds.setupAll(new DualNavigationContext(_nav, du_), _gene).getContext();
        if (ctx_ == null) {
            return "";
        }
        context = ctx_;
        DualConfigurationContext confCont_ = du_.getContext();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        String arrStr_ = StringExpUtil.getPrettyArrayType(stds.getContent().getCharSeq().getAliasString());
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, confCont_.getInitNameMethod(), new StringList(arrStr_,arrStr_));
        ExecRootBlock classBody_ = ctx_.getClasses().getClassBody(confCont_.getInitNameClass());
        if (classBody_ != null) {
            CustList<ExecOverridableBlock> methods_ = ExecClassesUtil.getMethodBodiesById(classBody_, id_);
            if (!methods_.isEmpty()) {
                ProcessMethod.initializeClass(confCont_.getInitNameClass(), classBody_,ctx_, rendStackCall_.getStackCall());
                if (ctx_.callsOrException(rendStackCall_.getStackCall())) {
                    return afterActionWithoutRemove(ctx_, rendStackCall_);
                }
                CustList<ArgumentWrapper> args_ = args(arrStr_);
                ExecNamedFunctionBlock method_ = methods_.first();
                ExecTypeFunction pair_ = new ExecTypeFunction(classBody_, method_);
                ArgumentListCall argList_ = new ArgumentListCall(args_);
                ExecTemplates.wrapAndCall(new ExecOverrideInfo(new ExecFormattedRootBlock(classBody_, confCont_.getInitNameClass()),pair_), NullStruct.NULL_VALUE, ctx_, rendStackCall_.getStackCall(), argList_);
                ArgumentWrapper aw_ = RendDynOperationNode.tryGetValue(ctx_, rendStackCall_, null);
                if (aw_ == null) {
                    return afterActionWithoutRemove(ctx_, rendStackCall_);
                }
                _nav.setDataBaseStruct(aw_.getValue());
            }
        }
        stds.initializeRendSessionDoc(ctx_, _nav, rendStackCall_);
        return afterActionWithoutRemove(ctx_, rendStackCall_);
    }

    private CustList<ArgumentWrapper> args(String _arrString) {
        CustList<ArgumentWrapper> args_ = new CustList<ArgumentWrapper>();
        int len_ = fileNames.size();
        ArrayStruct arrNames_ = new ArrayStruct(len_, _arrString);
        for (int i = 0; i < len_; i++) {
            arrNames_.set(i, new StringStruct(fileNames.getKey(i)));
        }
        ArrayStruct arrContents_ = new ArrayStruct(len_, _arrString);
        for (int i = 0; i < len_; i++) {
            arrContents_.set(i, new StringStruct(fileNames.getValue(i)));
        }
        args_.add(new ArgumentWrapper(arrNames_));
        args_.add(new ArgumentWrapper(arrContents_));
        return args_;
    }

    public ContextEl getContext() {
        return context;
    }

    public String getKeyWordDigit() {
        return keyWordDigit;
    }

    public static String afterActionWithoutRemove(ContextEl _ctx, RendStackCall _stackCall) {
        String error_ = ProcessMethod.error(_ctx, _stackCall.getStackCall());
        if (error_ != null) {
            _stackCall.getStackCall().setNullCallingState();
        }
        return error_;
    }
}
