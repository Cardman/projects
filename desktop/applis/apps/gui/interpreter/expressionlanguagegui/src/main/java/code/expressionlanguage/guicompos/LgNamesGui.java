package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.sml.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private GuiAliases guiAliases = new GuiAliases();
    public LgNamesGui(FileInfos _infos) {
        super(_infos);
        setCalculator(new AdvancedExecConstantsCalculator(this));
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

    public String getAliasActionListener() {
        return getGuiAliases().getAliasActionListener();
    }

    public String getAliasActionPerformed() {
        return getGuiAliases().getAliasActionPerformed();
    }

    public String getAliasActionEvent() {
        return getGuiAliases().getAliasActionEvent();
    }

    public String getAliasChangeListener() {
        return getGuiAliases().getAliasChangeListener();
    }

    public String getAliasStateChanged() {
        return getGuiAliases().getAliasStateChanged();
    }

    public String getAliasTreeListener() {
        return getGuiAliases().getAliasTreeListener();
    }

    public String getAliasTreeListenerValueChanged() {
        return getGuiAliases().getAliasTreeListenerValueChanged();
    }

    public String getAliasTreeNode() {
        return getGuiAliases().getAliasTreeNode();
    }

    public String getAliasTableListener() {
        return getGuiAliases().getAliasTableListener();
    }

    public String getAliasTableValueTableChanged() {
        return getGuiAliases().getAliasTableValueTableChanged();
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

    public String getAliasComponent() {
        return getGuiAliases().getAliasComponent();
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

    public String getAliasPaintMethod() {
        return getGuiAliases().getAliasPaintMethod();
    }

    public String getAliasPaintAdd() {
        return getGuiAliases().getAliasPaintAdd();
    }

    public String getAliasPaintRefresh() {
        return getGuiAliases().getAliasPaintRefresh();
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

    public String getAliasMouseListener() {
        return getGuiAliases().getAliasMouseListener();
    }

    public String getAliasMouseClicked() {
        return getGuiAliases().getAliasMouseClicked();
    }

    public String getAliasMousePressed() {
        return getGuiAliases().getAliasMousePressed();
    }

    public String getAliasMouseReleased() {
        return getGuiAliases().getAliasMouseReleased();
    }

    public String getAliasMouseEntered() {
        return getGuiAliases().getAliasMouseEntered();
    }

    public String getAliasMouseExited() {
        return getGuiAliases().getAliasMouseExited();
    }

    public String getAliasMouseDragged() {
        return getGuiAliases().getAliasMouseDragged();
    }

    public String getAliasMouseMoved() {
        return getGuiAliases().getAliasMouseMoved();
    }

    public String getAliasMouseEvent() {
        return getGuiAliases().getAliasMouseEvent();
    }

    public String getAliasWheelListener() {
        return getGuiAliases().getAliasWheelListener();
    }

    public String getAliasWheelMove() {
        return getGuiAliases().getAliasWheelMove();
    }

    public String getAliasWheelEvent() {
        return getGuiAliases().getAliasWheelEvent();
    }

    public String getAliasKeyListener() {
        return getGuiAliases().getAliasKeyListener();
    }

    public String getAliasKeyPressed() {
        return getGuiAliases().getAliasKeyPressed();
    }

    public String getAliasKeyTyped() {
        return getGuiAliases().getAliasKeyTyped();
    }

    public String getAliasKeyReleased() {
        return getGuiAliases().getAliasKeyReleased();
    }

    public String getAliasKeyEvent() {
        return getGuiAliases().getAliasKeyEvent();
    }

    public String getAliasWindowListener() {
        return getGuiAliases().getAliasWindowListener();
    }

    public String getAliasWindowOpened() {
        return getGuiAliases().getAliasWindowOpened();
    }

    public String getAliasWindowClosed() {
        return getGuiAliases().getAliasWindowClosed();
    }

    public String getAliasWindowClosing() {
        return getGuiAliases().getAliasWindowClosing();
    }

    public String getAliasWindowIconified() {
        return getGuiAliases().getAliasWindowIconified();
    }

    public String getAliasWindowDeiconified() {
        return getGuiAliases().getAliasWindowDeiconified();
    }

    public String getAliasWindowActivated() {
        return getGuiAliases().getAliasWindowActivated();
    }

    public String getAliasWindowDeactivated() {
        return getGuiAliases().getAliasWindowDeactivated();
    }

    public String getAliasWindowEvent() {
        return getGuiAliases().getAliasWindowEvent();
    }

    public String getAliasListSelection() {
        return getGuiAliases().getAliasListSelection();
    }

    public String getAliasValueChanged() {
        return getGuiAliases().getAliasValueChanged();
    }

    public String getAliasRender() {
        return getGuiAliases().getAliasRender();
    }

    public String getAliasGrList() {
        return getGuiAliases().getAliasGrList();
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

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.addAllElts(guiAliases.allMergeTableTypeMethodNames(getCustAliases(),getContent()));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeFieldNames().entryList()) {
            f_.addEntry(o.getKey(),o.getValue());
        }
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        for (EntryCust<String,CustList<KeyValueMemberName>> o: guiAliases.allTableTypeMethodNames().entryList()) {
            m_.addEntry(o.getKey(),o.getValue());
        }
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        for (EntryCust<String, String> o: guiAliases.allRefTypes().entryList()) {
            ref_.addEntry(o.getKey(),o.getValue());
        }
        return ref_;
    }
}