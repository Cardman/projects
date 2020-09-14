package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.CustComponent;
import code.gui.OtherConfirmDialog;
import code.gui.OtherFrame;
import code.gui.TextLabel;
import code.threads.ThreadUtil;
import code.util.StringList;

import javax.swing.WindowConstants;
import java.awt.event.WindowListener;

public final class GuiContextEl extends RunnableContextEl {
    private TextLabel textLabel;
    private FrameStruct frame;
    private StringList mainArgs;
    private OtherConfirmDialog confirm;
    private GuiInitializer guiInit;
    private MainWindow window;
    private ExecRootBlock actionListener;
    private ExecNamedFunctionBlock actionPerformed;
    private ExecRootBlock mouseListener;
    private ExecNamedFunctionBlock mouseClicked;
    private ExecNamedFunctionBlock mousePressed;
    private ExecNamedFunctionBlock mouseReleased;
    private ExecNamedFunctionBlock mouseEntered;
    private ExecNamedFunctionBlock mouseExited;
    private ExecNamedFunctionBlock mouseDragged;
    private ExecNamedFunctionBlock mouseMoved;
    private ExecRootBlock wheelListener;
    private ExecNamedFunctionBlock wheelMove;
    private ExecRootBlock windowListener;
    private ExecNamedFunctionBlock windowOpened;
    private ExecNamedFunctionBlock windowClosing;
    private ExecNamedFunctionBlock windowClosed;
    private ExecNamedFunctionBlock windowIconified;
    private ExecNamedFunctionBlock windowDeiconified;
    private ExecNamedFunctionBlock windowActivated;
    private ExecNamedFunctionBlock windowDeactivated;
    private ExecRootBlock listSelection;
    private ExecNamedFunctionBlock valueChanged;
    private ExecRootBlock changeListener;
    private ExecNamedFunctionBlock stateChanged;
    private ExecRootBlock treeListener;
    private ExecNamedFunctionBlock treeListenerValueChanged;
    private ExecRootBlock tableListener;
    private ExecNamedFunctionBlock tableValueTableChanged;
    private ExecRootBlock keyListener;
    private ExecNamedFunctionBlock keyPressed;
    private ExecNamedFunctionBlock keyTyped;
    private ExecNamedFunctionBlock keyReleased;
    private ExecRootBlock paint;
    private ExecNamedFunctionBlock paintRefresh;
    private ExecNamedFunctionBlock paintMethod;
    private ExecNamedFunctionBlock paintAdd;


    GuiContextEl(int _stackOverFlow, DefaultLockingClass _lock, GuiInitializer _init,
                 Options _options, ExecutingOptions _exec,
                 LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _init, _options, _exec, _stds, _tabWidth);
        guiInit = _init;
    }

    public void initApplicationParts(StringList _mainArgs, MainWindow _window) {
        mainArgs = _mainArgs;
        window = _window;
        textLabel = new TextLabel("");
        OtherFrame fr_ = new OtherFrame();
        fr_.setMainFrame(true);
        fr_.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame = new FrameStruct(fr_);
        guiInit.getWindows().add(frame,false);
        confirm = new OtherConfirmDialog();
    }

    GuiContextEl(ContextEl _context) {
        super(_context);
        textLabel = ((GuiContextEl)_context).textLabel;
        frame = ((GuiContextEl)_context).frame;
        mainArgs = ((GuiContextEl)_context).mainArgs;
        guiInit = ((GuiContextEl)_context).guiInit;
        confirm = ((GuiContextEl)_context).confirm;
        window = ((GuiContextEl)_context).window;
        actionListener = ((GuiContextEl)_context).actionListener;
        actionPerformed = ((GuiContextEl)_context).actionPerformed;
        mouseListener = ((GuiContextEl)_context).mouseListener;
        mouseClicked = ((GuiContextEl)_context).mouseClicked;
        mousePressed = ((GuiContextEl)_context).mousePressed;
        mouseReleased = ((GuiContextEl)_context).mouseReleased;
        mouseEntered = ((GuiContextEl)_context).mouseEntered;
        mouseExited = ((GuiContextEl)_context).mouseExited;
        mouseDragged = ((GuiContextEl)_context).mouseDragged;
        mouseMoved = ((GuiContextEl)_context).mouseMoved;
        wheelListener = ((GuiContextEl)_context).wheelListener;
        wheelMove = ((GuiContextEl)_context).wheelMove;
        windowListener = ((GuiContextEl)_context).windowListener;
        windowOpened = ((GuiContextEl)_context).windowOpened;
        windowClosed = ((GuiContextEl)_context).windowClosed;
        windowClosing = ((GuiContextEl)_context).windowClosing;
        windowActivated = ((GuiContextEl)_context).windowActivated;
        windowDeactivated = ((GuiContextEl)_context).windowDeactivated;
        windowIconified = ((GuiContextEl)_context).windowIconified;
        windowDeiconified = ((GuiContextEl)_context).windowDeiconified;
        listSelection = ((GuiContextEl)_context).listSelection;
        valueChanged = ((GuiContextEl)_context).valueChanged;
        changeListener = ((GuiContextEl)_context).changeListener;
        stateChanged = ((GuiContextEl)_context).stateChanged;
        treeListener = ((GuiContextEl)_context).treeListener;
        treeListenerValueChanged = ((GuiContextEl)_context).treeListenerValueChanged;
        tableListener = ((GuiContextEl)_context).tableListener;
        tableValueTableChanged = ((GuiContextEl)_context).tableValueTableChanged;
        keyListener = ((GuiContextEl)_context).keyListener;
        keyPressed = ((GuiContextEl)_context).keyPressed;
        keyReleased = ((GuiContextEl)_context).keyReleased;
        keyTyped = ((GuiContextEl)_context).keyTyped;
        paint = ((GuiContextEl)_context).paint;
        paintRefresh = ((GuiContextEl)_context).paintRefresh;
        paintMethod = ((GuiContextEl)_context).paintMethod;
        paintAdd = ((GuiContextEl)_context).paintAdd;
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana) {
        super.forwardAndClear(_ana);
        LgNamesGui standards_ = (LgNamesGui) _ana.getStandards();
        String aliasActListener_ = standards_.getAliasActionListener();
        actionListener = _ana.getClasses().getClassBody(aliasActListener_);
        String actionEvent_ = standards_.getAliasActionEvent();
        MethodId fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasActionPerformed(),new StringList(actionEvent_));
        actionPerformed = ExecBlock.getMethodBodiesById(actionListener,fct_).first();
        String aliasMouseListener_ = standards_.getAliasMouseListener();
        mouseListener = _ana.getClasses().getClassBody(aliasMouseListener_);
        String mouseEvent_ = standards_.getAliasMouseEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseClicked(),new StringList(mouseEvent_));
        mouseClicked = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMousePressed(),new StringList(mouseEvent_));
        mousePressed = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseReleased(),new StringList(mouseEvent_));
        mouseReleased = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseEntered(),new StringList(mouseEvent_));
        mouseEntered = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseExited(),new StringList(mouseEvent_));
        mouseExited = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseDragged(),new StringList(mouseEvent_));
        mouseDragged = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasMouseMoved(),new StringList(mouseEvent_));
        mouseMoved = ExecBlock.getMethodBodiesById(mouseListener,fct_).first();
        String aliasWheelListener_ = standards_.getAliasWheelListener();
        wheelListener = _ana.getClasses().getClassBody(aliasWheelListener_);
        String wheelEvent_ = standards_.getAliasWheelEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWheelMove(),new StringList(wheelEvent_));
        wheelMove = ExecBlock.getMethodBodiesById(wheelListener,fct_).first();
        String aliasWindowListener_ = standards_.getAliasWindowListener();
        windowListener = _ana.getClasses().getClassBody(aliasWindowListener_);
        String windowEvent_ = standards_.getAliasWindowEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowOpened(),new StringList(windowEvent_));
        windowOpened = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowClosed(),new StringList(windowEvent_));
        windowClosed = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowClosing(),new StringList(windowEvent_));
        windowClosing = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowActivated(),new StringList(windowEvent_));
        windowActivated = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowDeactivated(),new StringList(windowEvent_));
        windowDeactivated = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowIconified(),new StringList(windowEvent_));
        windowIconified = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasWindowDeiconified(),new StringList(windowEvent_));
        windowDeiconified = ExecBlock.getMethodBodiesById(windowListener,fct_).first();
        String aliasListSelection_ = standards_.getAliasListSelection();
        listSelection = _ana.getClasses().getClassBody(aliasListSelection_);
        String ind_ = standards_.getAliasPrimInteger();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasValueChanged(),new StringList(ind_,ind_));
        valueChanged = ExecBlock.getMethodBodiesById(listSelection,fct_).first();
        String aliasChangeListener_ = standards_.getAliasChangeListener();
        changeListener = _ana.getClasses().getClassBody(aliasChangeListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasStateChanged(),new StringList());
        stateChanged = ExecBlock.getMethodBodiesById(changeListener,fct_).first();
        String aliasTreeListener_ = standards_.getAliasTreeListener();
        treeListener = _ana.getClasses().getClassBody(aliasTreeListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasTreeListenerValueChanged(),new StringList(standards_.getAliasTreeNode()));
        treeListenerValueChanged = ExecBlock.getMethodBodiesById(treeListener,fct_).first();
        String aliasTableListener_ = standards_.getAliasTableListener();
        tableListener = _ana.getClasses().getClassBody(aliasTableListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasTableValueTableChanged(),new StringList(ind_,ind_));
        tableValueTableChanged = ExecBlock.getMethodBodiesById(tableListener,fct_).first();
        String aliasKeyListener_ = standards_.getAliasKeyListener();
        keyListener = _ana.getClasses().getClassBody(aliasKeyListener_);
        String keyEvent_ = standards_.getAliasKeyEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasKeyReleased(),new StringList(keyEvent_));
        keyReleased = ExecBlock.getMethodBodiesById(keyListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasKeyPressed(),new StringList(keyEvent_));
        keyPressed = ExecBlock.getMethodBodiesById(keyListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                standards_.getAliasKeyTyped(),new StringList(keyEvent_));
        keyTyped = ExecBlock.getMethodBodiesById(keyListener,fct_).first();
        String aliasPaint_ = standards_.getAliasPaint();
        paint = _ana.getClasses().getClassBody(aliasPaint_);
        fct_ = new MethodId(MethodAccessKind.STATIC,
                standards_.getAliasPaintRefresh(),new StringList(standards_.getAliasGrList()));
        paintRefresh = ExecBlock.getMethodBodiesById(paint,fct_).first();
        fct_ = new MethodId(MethodAccessKind.STATIC,
                standards_.getAliasPaintMethod(),new StringList(standards_.getAliasComponent()));
        paintMethod = ExecBlock.getMethodBodiesById(paint,fct_).first();
        fct_ = new MethodId(MethodAccessKind.STATIC,
                standards_.getAliasPaintAdd(),new StringList(standards_.getAliasGrList(),standards_.getAliasPrimInteger(),standards_.getAliasObject()));
        paintAdd = ExecBlock.getMethodBodiesById(paint,fct_).first();
    }

    public ExecRootBlock getActionListener() {
        return actionListener;
    }

    public ExecNamedFunctionBlock getActionPerformed() {
        return actionPerformed;
    }

    public ExecRootBlock getMouseListener() {
        return mouseListener;
    }

    public ExecNamedFunctionBlock getMouseClicked() {
        return mouseClicked;
    }

    public ExecNamedFunctionBlock getMousePressed() {
        return mousePressed;
    }

    public ExecNamedFunctionBlock getMouseReleased() {
        return mouseReleased;
    }

    public ExecNamedFunctionBlock getMouseEntered() {
        return mouseEntered;
    }

    public ExecNamedFunctionBlock getMouseExited() {
        return mouseExited;
    }

    public ExecNamedFunctionBlock getMouseDragged() {
        return mouseDragged;
    }

    public ExecNamedFunctionBlock getMouseMoved() {
        return mouseMoved;
    }

    public ExecRootBlock getWheelListener() {
        return wheelListener;
    }

    public ExecNamedFunctionBlock getWheelMove() {
        return wheelMove;
    }

    public ExecRootBlock getWindowListener() {
        return windowListener;
    }

    public ExecNamedFunctionBlock getWindowOpened() {
        return windowOpened;
    }

    public ExecNamedFunctionBlock getWindowClosed() {
        return windowClosed;
    }

    public ExecNamedFunctionBlock getWindowClosing() {
        return windowClosing;
    }

    public ExecNamedFunctionBlock getWindowActivated() {
        return windowActivated;
    }

    public ExecNamedFunctionBlock getWindowDeactivated() {
        return windowDeactivated;
    }

    public ExecNamedFunctionBlock getWindowIconified() {
        return windowIconified;
    }

    public ExecNamedFunctionBlock getWindowDeiconified() {
        return windowDeiconified;
    }

    public ExecRootBlock getListSelection() {
        return listSelection;
    }

    public ExecNamedFunctionBlock getValueChanged() {
        return valueChanged;
    }

    public ExecRootBlock getChangeListener() {
        return changeListener;
    }

    public ExecNamedFunctionBlock getStateChanged() {
        return stateChanged;
    }

    public ExecRootBlock getTreeListener() {
        return treeListener;
    }

    public ExecNamedFunctionBlock getTreeListenerValueChanged() {
        return treeListenerValueChanged;
    }

    public ExecRootBlock getTableListener() {
        return tableListener;
    }

    public ExecNamedFunctionBlock getTableValueTableChanged() {
        return tableValueTableChanged;
    }

    public ExecRootBlock getKeyListener() {
        return keyListener;
    }

    public ExecNamedFunctionBlock getKeyPressed() {
        return keyPressed;
    }

    public ExecNamedFunctionBlock getKeyReleased() {
        return keyReleased;
    }

    public ExecNamedFunctionBlock getKeyTyped() {
        return keyTyped;
    }

    public ExecRootBlock getPaint() {
        return paint;
    }

    public ExecNamedFunctionBlock getPaintRefresh() {
        return paintRefresh;
    }

    public ExecNamedFunctionBlock getPaintMethod() {
        return paintMethod;
    }

    public ExecNamedFunctionBlock getPaintAdd() {
        return paintAdd;
    }

    public void disposeAll() {
        for (Struct s: guiInit.getWindows().toSnapshotArray(this).getInstance()) {
            if (!(s instanceof WindowStruct)) {
                continue;
            }
            ((WindowStruct)s).setVisible(false);
            ((WindowStruct)s).dispose();
        }
        frame.setVisible(false);
        frame.dispose();
        interrupt();
        getGuiInit().launchHooks(this);
        window.setNullCurrent();
        Thread th_ = CustComponent.newThread(new CoveringCodeTask(this, getExecutingOptions()));
        th_.start();
        ThreadUtil.join(th_);

    }
    public GuiInitializer getGuiInit() {
        return guiInit;
    }

    public Struct showTextField(Struct _img,Struct _frame, Struct _value, Struct _message, Struct _title, Struct _ok, Struct _cancel) {
        if (_img instanceof ImageStruct) {
            ImageStruct img_ = (ImageStruct) _img;
            if (_frame instanceof WindowStruct) {
                return getReturned(confirm.showTextField(img_.getImage(),((WindowStruct)_frame).getAbstractWindow(),
                        getValue(_value),getValue(_message),getValue(_title),
                        getValue(_ok),getValue(_cancel)));
            }
            return getReturned(confirm.showTextField(img_.getImage(),null,
                    getValue(_value),getValue(_message),getValue(_title),
                    getValue(_ok),getValue(_cancel)));
        }
        return showTextField(_frame,_value,_message,_title,_ok,_cancel);
    }

    public Struct showTextField(Struct _frame, Struct _value, Struct _message, Struct _title, Struct _ok, Struct _cancel) {
        if (_frame instanceof WindowStruct) {
            return getReturned(confirm.showTextField(((WindowStruct)_frame).getAbstractWindow(),
                    getValue(_value),getValue(_message),getValue(_title),
                    getValue(_ok),getValue(_cancel)));
        }
        return getReturned(confirm.showTextField(null,
                getValue(_value),getValue(_message),getValue(_title),
                getValue(_ok),getValue(_cancel)));
    }

    private Struct getReturned(String _str) {
        if (_str == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_str);
    }
    public void showMessage(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _ok) {
        if (_img instanceof ImageStruct) {
            ImageStruct img_ = (ImageStruct) _img;
            if (_frame instanceof WindowStruct) {
                confirm.showMessage(img_.getImage(),((WindowStruct)_frame).getAbstractWindow(),
                        getValue(_message),getValue(_title),
                        getValue(_ok));
                return;
            }
            confirm.showMessage(img_.getImage(),null,
                    getValue(_message),getValue(_title),
                    getValue(_ok));
            return;
        }
        showMessage(_frame,_message,_title,_ok);
    }

    public void showMessage(Struct _frame, Struct _message, Struct _title, Struct _ok) {
        if (_frame instanceof WindowStruct) {
            confirm.showMessage(((WindowStruct)_frame).getAbstractWindow(),
                    getValue(_message),getValue(_title),
                    getValue(_ok));
            return;
        }
        confirm.showMessage(null,
                getValue(_message),getValue(_title),
                getValue(_ok));
    }

    public Struct getAnswerOk(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _ok) {
        if (_img instanceof ImageStruct) {
            ImageStruct img_ = (ImageStruct) _img;
            if (_frame instanceof WindowStruct) {
                return new IntStruct(confirm.getAnswerOk(img_.getImage(),((WindowStruct) _frame).getAbstractWindow(),
                        getValue(_message), getValue(_title),
                        getValue(_ok)));
            }
            return new IntStruct(confirm.getAnswerOk(img_.getImage(),null,
                    getValue(_message), getValue(_title),
                    getValue(_ok)));
        }
        return getAnswerOk(_frame,_message,_title,_ok);
    }
    public Struct getAnswerOk(Struct _frame, Struct _message, Struct _title, Struct _ok) {
        if (_frame instanceof WindowStruct) {
            return new IntStruct(confirm.getAnswerOk(((WindowStruct)_frame).getAbstractWindow(),
                    getValue(_message),getValue(_title),
                    getValue(_ok)));
        }
        return new IntStruct(confirm.getAnswerOk(null,
                getValue(_message),getValue(_title),
                getValue(_ok)));
    }

    public Struct getAnswerYesNo(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no) {
        if (_img instanceof ImageStruct) {
            ImageStruct img_ = (ImageStruct) _img;
            if (_frame instanceof WindowStruct) {
                return new IntStruct(confirm.getAnswerYesNo(img_.getImage(),((WindowStruct) _frame).getAbstractWindow(),
                        getValue(_message), getValue(_title),
                        getValue(_yes),getValue(_no)));
            }
            return new IntStruct(confirm.getAnswerYesNo(img_.getImage(),null,
                    getValue(_message), getValue(_title),
                    getValue(_yes),getValue(_no)));
        }
        return getAnswerYesNo(_frame,_message,_title,_yes,_no);
    }

    public Struct getAnswerYesNo(Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no) {
        if (_frame instanceof WindowStruct) {
            return new IntStruct(confirm.getAnswerYesNo(((WindowStruct)_frame).getAbstractWindow(),
                    getValue(_message),getValue(_title),
                    getValue(_yes),getValue(_no)));
        }
        return new IntStruct(confirm.getAnswerYesNo(null,
                getValue(_message),getValue(_title),
                getValue(_yes),getValue(_no)));
    }


    public Struct getAnswer(Struct _img,Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no, Struct _cancel) {
        if (_img instanceof ImageStruct) {
            ImageStruct img_ = (ImageStruct) _img;
            if (_frame instanceof WindowStruct) {
                return new IntStruct(confirm.getAnswer(img_.getImage(),((WindowStruct) _frame).getAbstractWindow(),
                        getValue(_message), getValue(_title),
                        getValue(_yes),getValue(_no),getValue(_cancel)));
            }
            return new IntStruct(confirm.getAnswer(img_.getImage(),null,
                    getValue(_message), getValue(_title),
                    getValue(_yes),getValue(_no),getValue(_cancel)));
        }
        return getAnswer(_frame,_message,_title,_yes,_no,_cancel);
    }
    public Struct getAnswer(Struct _frame, Struct _message, Struct _title, Struct _yes, Struct _no, Struct _cancel) {
        if (_frame instanceof WindowStruct) {
            return new IntStruct(confirm.getAnswer(((WindowStruct)_frame).getAbstractWindow(),
                    getValue(_message),getValue(_title),
                    getValue(_yes),getValue(_no),getValue(_cancel)));
        }
        return new IntStruct(confirm.getAnswer(null,
                getValue(_message),getValue(_title),
                getValue(_yes),getValue(_no),getValue(_cancel)));
    }
    private String getValue(Struct _str) {
        if (_str instanceof StringStruct) {
            return ((StringStruct)_str).getInstance();
        }
        return "";
    }
    public void addWindowListener(WindowStruct _frame,Struct _event) {
        if (_event instanceof WindowListener) {
            _frame.addWindowListener((WindowListener)_event);
            _frame.getAbstractWindow().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }

    public void removeWindowListener(WindowStruct _inst, Struct _event) {
        if (_event instanceof WindowListener) {
            _inst.removeWindowListener((WindowListener)_event);
            if (_inst.getWindowListeners().length == 0) {
                if (_inst == frame) {
                    _inst.getAbstractWindow().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                } else {
                    _inst.getAbstractWindow().setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                }
            }
        }
    }

    public FrameStruct getFrame() {
        return frame;
    }

    public StringList getMainArgs() {
        return mainArgs;
    }

    public IntStruct stringWidth(FontStruct _font, Struct _string) {
        if (!(_string instanceof StringStruct)) {
            return new IntStruct(-1);
        }
        return new IntStruct(textLabel.getFontMetrics(_font.getFont()).stringWidth(((StringStruct)_string).getInstance()));
    }

}
