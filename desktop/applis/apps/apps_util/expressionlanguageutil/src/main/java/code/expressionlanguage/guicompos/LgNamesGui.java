package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private final GuiAliases guiAliases = new GuiAliases();
    private final GuiExecutingBlocks guiExecutingBlocks = new GuiExecutingBlocks();
    public LgNamesGui(FileInfos _infos, AbstractInterceptor _inter) {
        super(_infos,_inter);
        setCalculator(new AdvancedExecConstantsCalculator(this));
    }

    public GuiExecutingBlocks getGuiExecutingBlocks() {
        return guiExecutingBlocks;
    }

    public GuiAliases getGuiAliases() {
        return guiAliases;
    }

    @Override
    public void buildOther() {
        getCustAliases().buildOther(getContent(), getExecutingBlocks());
        guiAliases.buildOther(getContent(),getCustAliases(),getGuiExecutingBlocks());
    }

    public Struct getInnerSimpleResult(ClassField _classField) {
        return guiAliases.getInnerSimpleResult(_classField,getContent());
    }

    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        if (_arg instanceof EventStruct) {
            String className_ = _arg.getClassName(_cont);
            String id_ = StringExpUtil.getIdFromAllTypes(className_);
            ExecRootBlock clBody_ = _cont.getClasses().getClassBody(id_);
            if (!CustAliases.isEnumType(clBody_)) {
                return new StringStruct(_arg.getClassName(_cont));
            }
        }
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    @Override
    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return newGuiFunctionnal(_contextEl, _className, _functional, _named);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return newGuiFunctionnal(_contextEl, _className, _functional, _named);
    }

    static EventFunctionalInstance newGuiFunctionnal(ContextEl _contextEl, ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new EventFunctionalInstance(_className.getFormatted(), _functional, fs_, (RunnableContextEl) _contextEl, _named);
    }

    public StringMap<String> addon(String _lang) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/aliases", _lang, "typesgui");
        String loadedResourcesMessages_ = guiAliases.res(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new GuiContextEl(null, new CommonExecutionInfos(getCustAliases().getInterceptor().newInterceptorStdCaller(getCustAliases().getAliasConcurrentError()),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new GuiInitializer(getInfos().getThreadFactory().newAtomicLong(), getCustAliases().getInterceptor())), new StringList(getExecutingOptions().getArgs()));
    }
    @Override
    public AbstractConstantsCalculator newConstantsCalculator() {
        return new AdvancedConstantsCalculator(this);
    }
}