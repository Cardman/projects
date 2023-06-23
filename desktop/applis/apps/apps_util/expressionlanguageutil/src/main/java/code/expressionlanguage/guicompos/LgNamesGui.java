package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
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
        getExecContent().getCustAliases().buildOther(getContent(), getExecContent().getExecutingBlocks());
        guiAliases.buildOther(getContent(),getExecContent().getCustAliases(),getGuiExecutingBlocks());
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
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return newGuiFunctionnal(_contextEl, _className, _functional, _named);
    }

    static EventFunctionalInstance newGuiFunctionnal(ContextEl _contextEl, ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new EventFunctionalInstance(_className.getFormatted(), _functional, fs_, (RunnableContextEl) _contextEl, _named);
    }

    public static StringMap<String> addon(CustAliases _cust) {
        return defAliases(_cust.getUserLg(), _cust.getTranslations(), _cust.getLanguage());
    }
    public static StringMap<String> extractAliasesKeys(CustAliases _cust) {
        TranslationsLg lg_ = CustAliases.lg(_cust.getTranslations(), _cust.getUserLg(), _cust.getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.TYPES_GUI);
        return TranslationsFile.extractKeys(com_);
    }

    public static StringMap<String> defAliases(String _lg, Translations _trs, String _language) {
        TranslationsLg lg_ = CustAliases.lg(_trs, _lg, _language);
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.TYPES_GUI);
        return TranslationsFile.extractMap(com_);
    }

    @Override
    public StringList args() {
        return new StringList(getExecContent().getExecutingOptions().getArgs());
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(getExecContent().getCustAliases().getInterceptor().newInterceptorStdCaller(getExecContent().getCustAliases().getAliasConcurrentError()),_opt,_options, new GuiInitializer(getExecContent().getInfos().getThreadFactory().newAtomicLong(), getExecContent().getCustAliases().getInterceptor()));
    }

    @Override
    public ContextEl newContext(AbstractAtomicBoolean _at, CommonExecutionInfos _common, StringList _args) {
        return new GuiContextEl(_at,null, _common, _args);
    }
    @Override
    public AbstractConstantsCalculator newConstantsCalculator() {
        return new AdvancedConstantsCalculator(this);
    }
}