package code.renders;

import code.expressionlanguage.ContextEl;
import code.formathtml.util.DefaultInitialization;
import code.gui.document.AbstractThreadActions;
import code.gui.document.RenderedPage;

public final class CustThreadActions extends AbstractThreadActions {

//    private String lgCode = "";
//
//    private String fileName;
//    private StringMap<String> fileNames;
//
//    private BeanCustLgNames stds;
//
//    private String classDbName;
//
//    private String methodName;
    private final DefaultInitialization init;

    private CustThreadActions(RenderedPage _page, DefaultInitialization _i){
        super(_page);
        init = _i;
    }

    public static CustThreadActions inst(RenderedPage _page, DefaultInitialization _init) {
        return new CustThreadActions(_page, _init);
    }

    @Override
    public void run() {
        String res_ = init.execute(getPage().getNavigation());
        ContextEl ctx_ = init.getContext();
        if (ctx_ != null) {
            getPage().setContext(ctx_);
        }
        if (res_ != null) {
            if (getPage().getArea() != null) {
                getPage().getArea().append(res_);
            }
            finish();
            return;
        }
        afterActionWithoutRemove();
//        String content_ = fileNames.getVal(fileName);
//        if (content_ == null) {
//            finish();
//            return;
//        }
//        DefaultConfigurationLoader def_ = new DefaultConfigurationLoader(stds);
//        AbstractFileBuilder fileBuilder_;
//        fileBuilder_ = new CustBeanFileBuilder(stds.getContent(), stds.getBeanAliases(), ((LgNamesRenderUtils)stds).getCustAliases());
//        Navigation navigation_ = getPage().getNavigation();
//        DualAnalyzedContext du_ = navigation_.loadConfiguration(content_, lgCode, stds, fileBuilder_, def_);
//        if (du_.getContext().isKo()) {
//            finish();
//            return;
//        }
//        navigation_.setFiles(fileNames);
//        ContextEl ctx_ = stds.setupAll(new DualNavigationContext(navigation_, du_));
//        if (ctx_ == null) {
//            finish();
//            return;
//        }
//        getPage().setContext(ctx_);
//        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
//        if (fileNames != null) {
//            LgNames stds_ = ctx_.getStandards();
//            String arrStr_ = StringExpUtil.getPrettyArrayType(stds_.getContent().getCharSeq().getAliasString());
//            MethodId id_ = new MethodId(MethodAccessKind.STATIC, methodName, new StringList(arrStr_,arrStr_));
//            ExecRootBlock classBody_ = ctx_.getClasses().getClassBody(classDbName);
//            if (classBody_ != null) {
//                CustList<ExecOverridableBlock> methods_ = ExecClassesUtil.getMethodBodiesById(classBody_, id_);
//                if (!methods_.isEmpty()) {
//                    ProcessMethod.initializeClass(classDbName, classBody_,ctx_, rendStackCall_.getStackCall());
//                    if (ctx_.callsOrException(rendStackCall_.getStackCall())) {
//                        afterActionWithoutRemove(ctx_, rendStackCall_);
//                        return;
//                    }
//                    Argument arg_ = new Argument();
//                    CustList<Argument> args_ = new CustList<Argument>();
//                    int len_ = fileNames.size();
//                    ArrayStruct arrNames_ = new ArrayStruct(len_,arrStr_);
//                    for (int i = 0; i < len_; i++) {
//                        arrNames_.set(i, new StringStruct(fileNames.getKey(i)));
//                    }
//                    ArrayStruct arrContents_ = new ArrayStruct(len_,arrStr_);
//                    for (int i = 0; i < len_; i++) {
//                        arrContents_.set(i, new StringStruct(fileNames.getValue(i)));
//                    }
//                    args_.add(new Argument(arrNames_));
//                    args_.add(new Argument(arrContents_));
//                    ExecNamedFunctionBlock method_ = methods_.first();
//                    ExecTypeFunction pair_ = new ExecTypeFunction(classBody_, method_);
//                    ArgumentListCall argList_ = ArgumentListCall.wrapCall(args_);
//                    Parameters parameters_ = ExecTemplates.wrapAndCall(pair_, new ExecFormattedRootBlock(classBody_, classDbName),arg_, ctx_, rendStackCall_.getStackCall(), argList_);
//                    Argument out_ = ProcessMethod.calculate(new CustomFoundMethod(arg_, new ExecFormattedRootBlock(classBody_, classDbName), pair_, parameters_), ctx_, rendStackCall_.getStackCall()).getValue();
//                    if (ctx_.callsOrException(rendStackCall_.getStackCall())) {
//                        afterActionWithoutRemove(ctx_, rendStackCall_);
//                        return;
//                    }
//                    navigation_.setDataBaseStruct(out_.getStruct());
//                }
//            }
//        }
//        BeanCustLgNames stds_ = du_.getStds();
//        stds_.initializeRendSessionDoc(ctx_, navigation_, rendStackCall_);
//        afterActionWithoutRemove(ctx_, rendStackCall_);
    }
}
