package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.sml.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private GuiAliases guiAliases = new GuiAliases();
    private GuiExecutingBlocks guiExecutingBlocks = new GuiExecutingBlocks();
    public LgNamesGui(FileInfos _infos) {
        super(_infos);
        setCalculator(new AdvancedExecConstantsCalculator(this));
    }

    public GuiExecutingBlocks getGuiExecutingBlocks() {
        return guiExecutingBlocks;
    }

    public GuiAliases getGuiAliases() {
        return guiAliases;
    }

    public void buildOther() {
        super.buildOther();
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
        return super.getStringOfObject(_cont,_arg);
    }
    public Argument defaultInstance(ContextEl _cont, String _id) {
        Argument arg_ = getCustAliases().defaultInstance(_cont, _id);
        if (!arg_.isNull() || _cont.callsOrException()) {
            return arg_;
        }
        return guiAliases.defaultInstance(getCustAliases(),_cont,_id);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return guiAliases.getOtherResult(getCustAliases(),_cont,_method,_args);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return guiAliases.getOtherResult(getCustAliases(),_cont,_instance,_method,_args);
    }

    @Override
    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBlock,LambdaStruct _functional,ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBlock);
        return new EventFunctionalInstance(_className,_functional,fs_, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className, ExecRootBlock _rootBlock,LambdaStruct _functional,ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBlock);
        return new EventFunctionalInstance(_className,_functional,fs_, _contextEl);
    }

    public String getAliasActionEvent() {
        return getGuiAliases().getAliasActionEvent();
    }

    public String getAliasTreeNode() {
        return getGuiAliases().getAliasTreeNode();
    }

    public String getAliasFrame() {
        return getGuiAliases().getAliasFrame();
    }

    public String getAliasDialog() {
        return getGuiAliases().getAliasDialog();
    }

    public String getAliasPanel() {
        return getGuiAliases().getAliasPanel();
    }

    public String getAliasFont() {
        return getGuiAliases().getAliasFont();
    }

    public String getAliasDimension() {
        return getGuiAliases().getAliasDimension();
    }

    public String getAliasImage() {
        return getGuiAliases().getAliasImage();
    }

    public String getAliasColor() {
        return getGuiAliases().getAliasColor();
    }

    public String getAliasPaint() {
        return getGuiAliases().getAliasPaint();
    }

    public String getAliasScrollPane() {
        return getGuiAliases().getAliasScrollPane();
    }

    public String getAliasWindow() {
        return getGuiAliases().getAliasWindow();
    }

    public String getAliasWindowSet() {
        return getGuiAliases().getAliasWindowSet();
    }

    public String getAliasMouseEvent() {
        return getGuiAliases().getAliasMouseEvent();
    }

    public String getAliasWheelEvent() {
        return getGuiAliases().getAliasWheelEvent();
    }

    public String getAliasKeyEvent() {
        return getGuiAliases().getAliasKeyEvent();
    }

    public String getAliasWindowEvent() {
        return getGuiAliases().getAliasWindowEvent();
    }

    public String getAliasRender() {
        return getGuiAliases().getAliasRender();
    }

    public String getAliasButtonGroup() {
        return getGuiAliases().getAliasButtonGroup();
    }

    public String getAliasMenuBar() {
        return getGuiAliases().getAliasMenuBar();
    }

    public String getAliasMenu() {
        return getGuiAliases().getAliasMenu();
    }

    public String getAliasMenuItem() {
        return getGuiAliases().getAliasMenuItem();
    }

    public String getAliasMenuItemCheck() {
        return getGuiAliases().getAliasMenuItemCheck();
    }

    public void otherAlias(String _lang, StringMap<String> _cust) {
        getCustAliases().otherAlias(getContent(),_lang,_cust);
        StringMap<String> util_ = ExtractFromFiles.getMessagesFromLocaleClass("resources_lg_gui/aliases",_lang,"typesgui");
        guiAliases.otherAliasGui(util_,_cust);
    }

    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        getCustAliases().allAlias(getContent(),_util,_cust);
        guiAliases.otherAliasGui(_util,_cust);
    }

}