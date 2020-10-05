package code.renders;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.formathtml.HtmlPage;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.gui.document.AbstractThreadActions;
import code.gui.document.RenderedPage;
import code.renders.utilcompo.CustBeanFileBuilder;
import code.renders.utilcompo.LgNamesRenderUtils;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class CustThreadActions extends AbstractThreadActions {

    private String lgCode = "";

    private String fileName;
    private StringMap<String> fileNames;

    private BeanLgNames stds;

    private String classDbName;

    private String methodName;

    private CustThreadActions(RenderedPage _page){
        super(_page);
    }

    public static CustThreadActions inst(RenderedPage _page, BeanCustLgNames _lgNames, String _lgCode, String _fileName, StringMap<String> _fileNames) {
        CustThreadActions t_ = new CustThreadActions(_page);
        t_.getPage().start();
        t_.stds = _lgNames;
        t_.lgCode = _lgCode;
        t_.fileName = _fileName;
        t_.fileNames = _fileNames;
        return t_;
    }
    void setClassDbName(String _classDbName) {
        classDbName = _classDbName;
    }

    void setMethodName(String _methodName) {
        methodName = _methodName;
    }

    @Override
    public void run() {
        String content_;
        content_ = fileNames.getVal(fileName);
        if (content_ == null) {
            afterActionWithoutRemove(null);
            return;
        }
        RendAnalysisMessages rend_ = new RendAnalysisMessages();
        AbstractFileBuilder fileBuilder_;
        fileBuilder_ = new CustBeanFileBuilder(stds.getContent(),((LgNamesRenderUtils)stds).getBeanAliases(), ((LgNamesRenderUtils)stds).getCustAliases());
        DualAnalyzedContext du_ = getPage().getNavigation().loadConfiguration(content_, lgCode, stds, rend_, fileBuilder_);
        ContextEl ctx_ = du_.getContext();
        getPage().setContext(ctx_);
        if (ctx_ != null) {
            HtmlPage htmlPage_ = getPage().getNavigation().getHtmlPage();
            htmlPage_.setUrl(-1);
            getPage().getNavigation().setFiles(fileNames);
            ReportedMessages reportedMessages_ = getPage().getNavigation().setupRendClassesInit(stds, rend_, du_);
            if (!reportedMessages_.isAllEmptyErrors()) {
                if (getPage().getArea() != null) {
                    getPage().getArea().append(reportedMessages_.displayErrors());
                }
                finish();
                return;
            }
            if (fileNames != null) {
                LgNames stds_ = ctx_.getStandards();
                String arrStr_ = StringExpUtil.getPrettyArrayType(stds_.getContent().getCharSeq().getAliasString());
                MethodId id_ = new MethodId(MethodAccessKind.STATIC, methodName, new StringList(arrStr_,arrStr_));
                ExecRootBlock classBody_ = ctx_.getClasses().getClassBody(classDbName);
                CustList<ExecNamedFunctionBlock> methods_ = ExecBlock.getMethodBodiesById(classBody_, id_);
                if (!methods_.isEmpty()) {
                    ProcessMethod.initializeClass(classDbName, classBody_,ctx_);
                    if (ctx_.callsOrException()) {
                        afterActionWithoutRemove(ctx_);
                        return;
                    }
                    Argument arg_ = new Argument();
                    CustList<Argument> args_ = new CustList<Argument>();
                    int len_ = fileNames.size();
                    ArrayStruct arrNames_ = new ArrayStruct(len_,arrStr_);
                    for (int i = 0; i < len_; i++) {
                        arrNames_.set(i, new StringStruct(fileNames.getKey(i)));
                    }
                    ArrayStruct arrContents_ = new ArrayStruct(len_,arrStr_);
                    for (int i = 0; i < len_; i++) {
                        arrContents_.set(i, new StringStruct(fileNames.getValue(i)));
                    }
                    args_.add(new Argument(arrNames_));
                    args_.add(new Argument(arrContents_));
                    ExecNamedFunctionBlock method_ = methods_.first();
                    Parameters parameters_ = ExecTemplates.wrapAndCall(method_,classBody_,classDbName,arg_,args_,ctx_);
                    Argument out_ = ProcessMethod.calculateArgument(arg_, classDbName,classBody_, method_, parameters_, ctx_);
                    if (ctx_.callsOrException()) {
                        afterActionWithoutRemove(ctx_);
                        return;
                    }
                    getPage().getNavigation().setDataBaseStruct(out_.getStruct());
                }
            }
            getPage().getNavigation().initializeRendSession(ctx_, du_.getStds());
        }
        afterActionWithoutRemove(ctx_);
    }
}
