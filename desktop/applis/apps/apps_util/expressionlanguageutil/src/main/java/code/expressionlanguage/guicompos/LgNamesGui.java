package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private final GuiAliases guiAliases;
    private final GuiExecutingBlocks guiExecutingBlocks = new GuiExecutingBlocks();
    public LgNamesGui(FileInfos _infos, AbstractInterceptor _inter) {
        super(_infos,_inter, false);
        guiAliases = new GuiAliases();
        setCalculator(new AdvancedExecConstantsCalculator(this));
    }
    public LgNamesGui(FileInfos _infos, AbstractInterceptor _inter, boolean _light) {
        super(_infos,_inter, _light);
        guiAliases = new GuiAliases(_light);
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
        super.buildOther();
        guiAliases.buildOther(getContent(),getExecContent().getCustAliases(),getGuiExecutingBlocks());
    }

    public Struct getInnerSimpleResult(ClassField _classField) {
        return guiAliases.getInnerSimpleResult(_classField,getContent());
    }

    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
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

    public void forwardAndClear(GuiAliases _g, LgNamesContent _c, GuiContextEl _ctx, Classes _cl) {
        if (getExecContent().getCustAliases().isLight()) {
            return;
        }
        getGuiExecutingBlocks().forwardAndClear(_g, _c, _ctx, _cl);
    }
}