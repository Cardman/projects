package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
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

    public void buildOther() {
        getCustAliases().buildOther(getContent());
        guiAliases.buildOther(getContent(),getCustAliases());
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
    public Argument defaultInstance(ContextEl _cont, String _id, StackCall _stackCall) {
        Argument arg_ = getCustAliases().defaultInstance(_cont, _id, _stackCall);
        if (!arg_.isNull() || _cont.callsOrException(_stackCall)) {
            return arg_;
        }
        return guiAliases.defaultInstance(getCustAliases(),_cont,_id, _stackCall);
    }
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return guiAliases.getOtherResult(getCustAliases(),_cont,_method, getGuiExecutingBlocks(),_stack,_args);
    }
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return guiAliases.getOtherResult(getCustAliases(),_cont,_instance,_method, getExecutingBlocks(), guiExecutingBlocks, _stack, _args);
    }

    @Override
    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new EventFunctionalInstance(_className.getFormatted(),_functional,fs_, _contextEl, _named);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new EventFunctionalInstance(_className.getFormatted(),_functional,fs_, _contextEl, _named);
    }

    public void otherAlias(String _lang, StringMap<String> _cust) {
        getCustAliases().otherAlias(getContent(),_lang,_cust);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg_gui/aliases", _lang, "typesgui");
        String loadedResourcesMessages_ = guiAliases.res(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        guiAliases.otherAliasGui(util_,_cust);
    }

    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        getCustAliases().allAlias(getContent(),_util,_cust);
        guiAliases.otherAliasGui(_util,_cust);
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage) {
        return new GuiContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(_tabWidth, _stack, this, new Classes(new ClassesCommon()), _coverage, new DefaultLockingClass(), new GuiInitializer(getInfos().getThreadFactory().newAtomicLong())));
    }
}