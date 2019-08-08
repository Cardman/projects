package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.gui.GroupFrame;
import code.resources.ResourceFiles;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;


public class LgNamesGui extends LgNamesUtils {

    private String aliasActionListener;
    private String aliasActionPerformed;
    private String aliasActionEvent;
    private String aliasMouseListener;
    private String aliasMouseClicked;
    private String aliasMousePressed;
    private String aliasMouseReleased;
    private String aliasMouseEntered;
    private String aliasMouseExited;
    private String aliasMouseEvent;
    private String aliasGetMouseEventFirst;
    private String aliasGetMouseEventSecond;
    private String aliasWindowListener;
    private String aliasWindowOpened;
    private String aliasWindowClosed;
    private String aliasWindowClosing;
    private String aliasWindowIconified;
    private String aliasWindowDeiconified;
    private String aliasWindowActivated;
    private String aliasWindowDeactivated;
    private String aliasWindowEvent;
    private String aliasFrame;
    private String aliasPanel;
    private String aliasButton;
    private String aliasTextLabel;
    private String aliasComponent;
    private String aliasSetContent;
    private String aliasAddCompo;
    private String aliasGetParentCompo;
    private String aliasGetIndexCompo;
    private String aliasAddListener;
    private String aliasAddWindowListener;
    private String aliasSetLabelText;
    private String aliasRemoveCompo;
    private String aliasCount;
    private String aliasSetVisible;
    private String aliasWindow;
    private String aliasPack;
    private String aliasDispose;

    public void buildOther() {
        super.buildOther();
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFrame, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasPanel);
        method_ = new StandardMethod(aliasSetContent, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPack, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDispose, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindow, params_, aliasFrame, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasAddWindowListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasFrame, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasComponent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParentCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasComponent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasActionEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        std_ = stdcl_;
        getStandards().put(aliasActionEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasWindowEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        std_ = stdcl_;
        getStandards().put(aliasWindowEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMouseEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMouseEventFirst, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMouseEventSecond, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasMouseEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPanel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent,getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasRemoveCompo, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetIndexCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasPanel, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasButton, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasButton, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasSetLabelText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTextLabel, std_);
    }
    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        if (StringList.quickEq(_id,aliasFrame)) {
            return new Argument(new FrameStruct(new OtherFrame(((GuiContextEl)_cont).getLgExec())));
        }
        if (StringList.quickEq(_id,aliasPanel)) {
            return new Argument(PanelStruct.newFlow(aliasPanel));
        }
        if (StringList.quickEq(_id,aliasButton)) {
            return new Argument(new PlainButtonStruct(aliasButton));
        }
        if (StringList.quickEq(_id,aliasTextLabel)) {
            return new Argument(new TextLabelStruct(aliasTextLabel));
        }
        return super.defaultInstance(_cont,_id);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        String name_ = _method.getName();
        ResultErrorStd r_ = new ResultErrorStd();
        if (StringList.quickEq(name_,aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new FrameStruct(new OtherFrame(((GuiContextEl)_cont).getLgExec())));
            return r_;
        }
        if (StringList.quickEq(name_,aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelStruct.newFlow(aliasPanel));
            return r_;
        }
        if (StringList.quickEq(name_,aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new PlainButtonStruct(_args[0],aliasButton));
            } else {
                r_.setResult(new PlainButtonStruct(aliasButton));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new TextLabelStruct(_args[0],aliasTextLabel));
            } else {
                r_.setResult(new TextLabelStruct(aliasTextLabel));
            }
            return r_;
        }
        return super.getOtherResult(_cont,_method,_args);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        if (StringList.quickEq(type_, aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasWindow)) {
                res_.setResult(((GuiContextEl)_cont).getFrame());
                return res_;
            }
            GroupFrame inst_ = ((FrameStruct) _instance).getCommonFrame();
            if (StringList.quickEq(name_, aliasPack)) {
                inst_.pack();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasDispose)) {
                inst_.dispose();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddWindowListener)) {
                ((GuiContextEl)_cont).addWindowListener((FrameStruct) _instance,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSetVisible)) {
                inst_.setVisible(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_args[0] instanceof PanelStruct) {
                inst_.setContentPane(((PanelStruct)_args[0]).getPanel());
            }
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasComponent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            CustComponentStruct inst_ = (CustComponentStruct)_instance;
            res_.setResult(inst_.getParentComponent());
            return res_;
        }
        if (StringList.quickEq(type_, aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PanelStruct strPan_ = (PanelStruct) _instance;
            if (StringList.quickEq(name_, aliasCount)) {
                res_.setResult(new IntStruct(strPan_.getComponentCount()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddCompo)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (_method.getConstraints().getParametersTypes().size() == 2) {
                    strPan_.add((CustComponentStruct)_args[0],((NumberStruct)_args[1]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add((CustComponentStruct)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetIndexCompo)) {
                res_.setResult(strPan_.getComponent(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            if (getAliasPrimInteger().equals(_method.getConstraints().getParametersTypes().first())) {
                res_.setResult(strPan_.remove(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            res_.setResult(strPan_.remove(_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            TextLabelStruct txt_ = (TextLabelStruct) _instance;
            if (StringList.quickEq(name_, aliasAddListener)) {
                txt_.addMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            txt_.setText(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMouseEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            MouseEventStruct event_ = (MouseEventStruct)_instance;
            if (StringList.quickEq(name_, aliasGetMouseEventFirst)) {
                res_.setResult(new IntStruct(event_.getFirst()));
                return res_;
            }
            res_.setResult(new IntStruct(event_.getSecond()));
            return res_;
        }
        if (StringList.quickEq(type_, aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PlainButtonStruct pl_ = (PlainButtonStruct) _instance;
            pl_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return super.getOtherResult(_cont,_instance,_method,_args);
    }
    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg_gui/action_event.txt");
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ActionListener}", aliasActionListener);
        map_.put("{actionPerformed}", aliasActionPerformed);
        map_.put("{ActionEvent}", aliasActionEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasActionListener);
        stds_.put(aliasActionListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasActionListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/mouse_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{MouseListener}", aliasMouseListener);
        map_.put("{mouseClicked}", aliasMouseClicked);
        map_.put("{mousePressed}", aliasMousePressed);
        map_.put("{mouseReleased}", aliasMouseReleased);
        map_.put("{mouseEntered}", aliasMouseEntered);
        map_.put("{mouseExited}", aliasMouseExited);
        map_.put("{MouseEvent}", aliasMouseEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasMouseListener);
        stds_.put(aliasMouseListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasMouseListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/window_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WindowListener}", aliasWindowListener);
        map_.put("{windowOpened}", aliasWindowOpened);
        map_.put("{windowClosing}", aliasWindowClosing);
        map_.put("{windowClosed}", aliasWindowClosed);
        map_.put("{windowIconified}", aliasWindowIconified);
        map_.put("{windowDeiconified}", aliasWindowDeiconified);
        map_.put("{windowActivated}", aliasWindowActivated);
        map_.put("{windowDeactivated}", aliasWindowDeactivated);
        map_.put("{WindowEvent}", aliasWindowEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasWindowListener);
        stds_.put(aliasWindowListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasWindowListener);
        return stds_;
    }

    public String getAliasActionListener() {
        return aliasActionListener;
    }

    public void setAliasActionListener(String aliasActionListener) {
        this.aliasActionListener = aliasActionListener;
    }

    public String getAliasActionPerformed() {
        return aliasActionPerformed;
    }

    public void setAliasActionPerformed(String aliasActionPerformed) {
        this.aliasActionPerformed = aliasActionPerformed;
    }

    public String getAliasActionEvent() {
        return aliasActionEvent;
    }

    public void setAliasActionEvent(String aliasActionEvent) {
        this.aliasActionEvent = aliasActionEvent;
    }

    public String getAliasFrame() {
        return aliasFrame;
    }

    public void setAliasFrame(String aliasFrame) {
        this.aliasFrame = aliasFrame;
    }

    public String getAliasPanel() {
        return aliasPanel;
    }

    public void setAliasPanel(String aliasPanel) {
        this.aliasPanel = aliasPanel;
    }

    public String getAliasButton() {
        return aliasButton;
    }

    public void setAliasButton(String aliasButton) {
        this.aliasButton = aliasButton;
    }

    public String getAliasTextLabel() {
        return aliasTextLabel;
    }

    public void setAliasTextLabel(String aliasTextLabel) {
        this.aliasTextLabel = aliasTextLabel;
    }

    public String getAliasComponent() {
        return aliasComponent;
    }

    public void setAliasComponent(String aliasComponent) {
        this.aliasComponent = aliasComponent;
    }

    public String getAliasSetContent() {
        return aliasSetContent;
    }

    public void setAliasSetContent(String aliasSetContent) {
        this.aliasSetContent = aliasSetContent;
    }

    public String getAliasAddCompo() {
        return aliasAddCompo;
    }

    public void setAliasAddCompo(String aliasAddCompo) {
        this.aliasAddCompo = aliasAddCompo;
    }

    public String getAliasGetParentCompo() {
        return aliasGetParentCompo;
    }

    public void setAliasGetParentCompo(String aliasGetParentCompo) {
        this.aliasGetParentCompo = aliasGetParentCompo;
    }

    public String getAliasGetIndexCompo() {
        return aliasGetIndexCompo;
    }

    public void setAliasGetIndexCompo(String aliasGetIndexCompo) {
        this.aliasGetIndexCompo = aliasGetIndexCompo;
    }

    public String getAliasAddListener() {
        return aliasAddListener;
    }

    public void setAliasAddListener(String aliasAddListener) {
        this.aliasAddListener = aliasAddListener;
    }

    public String getAliasAddWindowListener() {
        return aliasAddWindowListener;
    }

    public void setAliasAddWindowListener(String aliasAddWindowListener) {
        this.aliasAddWindowListener = aliasAddWindowListener;
    }

    public String getAliasSetLabelText() {
        return aliasSetLabelText;
    }

    public void setAliasSetLabelText(String aliasSetLabelText) {
        this.aliasSetLabelText = aliasSetLabelText;
    }

    public String getAliasRemoveCompo() {
        return aliasRemoveCompo;
    }

    public void setAliasRemoveCompo(String aliasRemoveCompo) {
        this.aliasRemoveCompo = aliasRemoveCompo;
    }

    public String getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(String aliasCount) {
        this.aliasCount = aliasCount;
    }

    public String getAliasPack() {
        return aliasPack;
    }

    public void setAliasPack(String aliasPack) {
        this.aliasPack = aliasPack;
    }

    public String getAliasDispose() {
        return aliasDispose;
    }

    public void setAliasDispose(String aliasDispose) {
        this.aliasDispose = aliasDispose;
    }

    public String getAliasSetVisible() {
        return aliasSetVisible;
    }

    public void setAliasSetVisible(String aliasSetVisible) {
        this.aliasSetVisible = aliasSetVisible;
    }

    public String getAliasWindow() {
        return aliasWindow;
    }

    public void setAliasWindow(String aliasWindow) {
        this.aliasWindow = aliasWindow;
    }

    public String getAliasMouseListener() {
        return aliasMouseListener;
    }

    public void setAliasMouseListener(String aliasMouseListener) {
        this.aliasMouseListener = aliasMouseListener;
    }

    public String getAliasMouseClicked() {
        return aliasMouseClicked;
    }

    public void setAliasMouseClicked(String aliasMouseClicked) {
        this.aliasMouseClicked = aliasMouseClicked;
    }

    public String getAliasMousePressed() {
        return aliasMousePressed;
    }

    public void setAliasMousePressed(String aliasMousePressed) {
        this.aliasMousePressed = aliasMousePressed;
    }

    public String getAliasMouseReleased() {
        return aliasMouseReleased;
    }

    public void setAliasMouseReleased(String aliasMouseReleased) {
        this.aliasMouseReleased = aliasMouseReleased;
    }

    public String getAliasMouseEntered() {
        return aliasMouseEntered;
    }

    public void setAliasMouseEntered(String aliasMouseEntered) {
        this.aliasMouseEntered = aliasMouseEntered;
    }

    public String getAliasMouseExited() {
        return aliasMouseExited;
    }

    public void setAliasMouseExited(String aliasMouseExited) {
        this.aliasMouseExited = aliasMouseExited;
    }

    public String getAliasMouseEvent() {
        return aliasMouseEvent;
    }

    public void setAliasMouseEvent(String aliasMouseEvent) {
        this.aliasMouseEvent = aliasMouseEvent;
    }

    public String getAliasGetMouseEventFirst() {
        return aliasGetMouseEventFirst;
    }

    public void setAliasGetMouseEventFirst(String aliasGetMouseEventFirst) {
        this.aliasGetMouseEventFirst = aliasGetMouseEventFirst;
    }

    public String getAliasGetMouseEventSecond() {
        return aliasGetMouseEventSecond;
    }

    public void setAliasGetMouseEventSecond(String aliasGetMouseEventSecond) {
        this.aliasGetMouseEventSecond = aliasGetMouseEventSecond;
    }

    public String getAliasWindowListener() {
        return aliasWindowListener;
    }

    public void setAliasWindowListener(String aliasWindowListener) {
        this.aliasWindowListener = aliasWindowListener;
    }

    public String getAliasWindowOpened() {
        return aliasWindowOpened;
    }

    public void setAliasWindowOpened(String aliasWindowOpened) {
        this.aliasWindowOpened = aliasWindowOpened;
    }

    public String getAliasWindowClosed() {
        return aliasWindowClosed;
    }

    public void setAliasWindowClosed(String aliasWindowClosed) {
        this.aliasWindowClosed = aliasWindowClosed;
    }

    public String getAliasWindowClosing() {
        return aliasWindowClosing;
    }

    public void setAliasWindowClosing(String aliasWindowClosing) {
        this.aliasWindowClosing = aliasWindowClosing;
    }

    public String getAliasWindowIconified() {
        return aliasWindowIconified;
    }

    public void setAliasWindowIconified(String aliasWindowIconified) {
        this.aliasWindowIconified = aliasWindowIconified;
    }

    public String getAliasWindowDeiconified() {
        return aliasWindowDeiconified;
    }

    public void setAliasWindowDeiconified(String aliasWindowDeiconified) {
        this.aliasWindowDeiconified = aliasWindowDeiconified;
    }

    public String getAliasWindowActivated() {
        return aliasWindowActivated;
    }

    public void setAliasWindowActivated(String aliasWindowActivated) {
        this.aliasWindowActivated = aliasWindowActivated;
    }

    public String getAliasWindowDeactivated() {
        return aliasWindowDeactivated;
    }

    public void setAliasWindowDeactivated(String aliasWindowDeactivated) {
        this.aliasWindowDeactivated = aliasWindowDeactivated;
    }

    public String getAliasWindowEvent() {
        return aliasWindowEvent;
    }

    public void setAliasWindowEvent(String aliasWindowEvent) {
        this.aliasWindowEvent = aliasWindowEvent;
    }

    public void otherAlias(String _lang) {
        super.otherAlias(_lang);
        if (StringList.quickEq(_lang, "en")) {
            setAliasActionEvent("$core.ActionEvent");
            setAliasActionListener("$core.ActionListener");
            setAliasActionPerformed("actionPerformed");
            setAliasMouseEvent("$core.MouseEvent");
            setAliasMouseListener("$core.MouseListener");
            setAliasMouseClicked("mouseClicked");
            setAliasMousePressed("mousePressed");
            setAliasMouseReleased("mouseReleased");
            setAliasMouseEntered("mouseEntered");
            setAliasMouseExited("mouseExited");
            setAliasGetMouseEventFirst("x");
            setAliasGetMouseEventSecond("y");
            setAliasWindowListener("$core.WindowListener");
            setAliasWindowEvent("$core.WindowEvent");
            setAliasWindowActivated("activated");
            setAliasWindowDeactivated("deactivated");
            setAliasWindowOpened("opened");
            setAliasWindowIconified("iconified");
            setAliasWindowDeiconified("deiconified");
            setAliasWindowClosed("closed");
            setAliasWindowClosing("closing");
            setAliasAddWindowListener("addWindowListener");
            setAliasFrame("$core.Frame");
            setAliasPanel("$core.Panel");
            setAliasButton("$core.Button");
            setAliasTextLabel("$core.TextLabel");
            setAliasComponent("$core.Component");
            setAliasSetContent("setContent");
            setAliasAddCompo("add");
            setAliasAddListener("addList");
            setAliasSetLabelText("setText");
            setAliasRemoveCompo("remove");
            setAliasCount("count");
            setAliasGetIndexCompo("get");
            setAliasGetParentCompo("getParent");
            setAliasPack("pack");
            setAliasSetVisible("setVisible");
            setAliasWindow("window");
            setAliasDispose("dispose");
        } else {
            setAliasActionEvent("$coeur.ActionEvt");
            setAliasActionListener("$coeur.ActionEcouteur");
            setAliasActionPerformed("action");
            setAliasMouseEvent("$coeur.SourisEvt");
            setAliasMouseListener("$coeur.SourisEcouteur");
            setAliasMouseClicked("clic");
            setAliasMousePressed("presse");
            setAliasMouseReleased("relache");
            setAliasMouseEntered("entre");
            setAliasMouseExited("sorti");
            setAliasGetMouseEventFirst("x");
            setAliasGetMouseEventSecond("y");
            setAliasWindowListener("$coeur.FenetreEcouteur");
            setAliasWindowEvent("$coeur.FenetreEvt");
            setAliasWindowActivated("active");
            setAliasWindowDeactivated("desactive");
            setAliasWindowOpened("ouverte");
            setAliasWindowIconified("iconifie");
            setAliasWindowDeiconified("desiconifie");
            setAliasWindowClosed("ferme");
            setAliasWindowClosing("fermeture");
            setAliasAddWindowListener("ajFenetreEcout");
            setAliasFrame("$coeur.Fenetre");
            setAliasPanel("$coeur.Panneau");
            setAliasButton("$coeur.Bouton");
            setAliasTextLabel("$coeur.Etiquette");
            setAliasComponent("$coeur.Composant");
            setAliasSetContent("majContenu");
            setAliasAddCompo("ajout");
            setAliasAddListener("ajEcout");
            setAliasSetLabelText("majTexte");
            setAliasRemoveCompo("supprimer");
            setAliasCount("nb");
            setAliasGetIndexCompo("val");
            setAliasGetParentCompo("valParent");
            setAliasPack("cadrer");
            setAliasSetVisible("majVisible");
            setAliasWindow("fenetre");
            setAliasDispose("liberer");
        }
    }
    @Override
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasFrame(), new StringList(
                getAliasPack(),
                getAliasWindow(),
                getAliasAddWindowListener(),
                getAliasDispose(),
                getAliasSetContent()));
        m_.put(getAliasPanel(), new StringList(
                getAliasCount(),
                getAliasAddCompo(),
                getAliasRemoveCompo()));
        m_.put(getAliasMouseEvent(), new StringList(
                getAliasGetMouseEventFirst(),
                getAliasGetMouseEventSecond()));
        m_.put(getAliasComponent(), new StringList());
        m_.put(getAliasTextLabel(), new StringList(
                getAliasSetLabelText()));
        m_.put(getAliasButton(), new StringList(
                getAliasAddListener()));
        return m_;
    }

    @Override
    public StringList allRefTypes() {
        StringList ref_ =  super.allRefTypes();
        ref_.add(getAliasFrame());
        ref_.add(getAliasComponent());
        ref_.add(getAliasActionEvent());
        ref_.add(getAliasMouseEvent());
        ref_.add(getAliasWindowEvent());
        ref_.add(getAliasPanel());
        ref_.add(getAliasButton());
        ref_.add(getAliasTextLabel());
        return ref_;
    }
}