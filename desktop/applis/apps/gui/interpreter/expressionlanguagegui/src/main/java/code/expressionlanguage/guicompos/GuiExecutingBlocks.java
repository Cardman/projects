package code.expressionlanguage.guicompos;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.OtherConfirmDialog;
import code.gui.OtherFrame;
import code.gui.TextLabel;
import code.util.CustList;
import code.util.StringList;

import javax.swing.*;
import java.awt.event.WindowListener;

public final class GuiExecutingBlocks {
    private TextLabel textLabel;
    private FrameStruct frame;
    private StringList mainArgs;
    private OtherConfirmDialog confirm;
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
    private DefaultClosingMainWindow eventClose;

    public void initApplicationParts(GuiInitializer _guiInit,StringList _mainArgs, MainWindow _window) {
        mainArgs = _mainArgs;
        window = _window;
        textLabel = new TextLabel("");
        OtherFrame fr_ = new OtherFrame();
        fr_.setMainFrame(true);
        fr_.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame = new FrameStruct(fr_);
        _guiInit.getWindows().add(frame,false);
        confirm = new OtherConfirmDialog();
    }
    private void initEventParts(GuiContextEl _context) {
        eventClose = new DefaultClosingMainWindow(this, _context);
        frame.getAbstractWindow().addWindowListener(eventClose);
    }
    public void forwardAndClear(GuiAliases _guiAliases, LgNamesContent _content, GuiContextEl _ctx, Classes _classes) {
        initEventParts(_ctx);
        String aliasActListener_ = _guiAliases.getAliasActionListener();
        actionListener = _classes.getClassBody(aliasActListener_);
        String actionEvent_ = _guiAliases.getAliasActionEvent();
        MethodId fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasActionPerformed(),new StringList(actionEvent_));
        actionPerformed = ExecClassesUtil.getMethodBodiesById(actionListener,fct_).first();
        String aliasMouseListener_ = _guiAliases.getAliasMouseListener();
        mouseListener = _classes.getClassBody(aliasMouseListener_);
        String mouseEvent_ = _guiAliases.getAliasMouseEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseClicked(),new StringList(mouseEvent_));
        mouseClicked = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMousePressed(),new StringList(mouseEvent_));
        mousePressed = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseReleased(),new StringList(mouseEvent_));
        mouseReleased = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseEntered(),new StringList(mouseEvent_));
        mouseEntered = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseExited(),new StringList(mouseEvent_));
        mouseExited = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseDragged(),new StringList(mouseEvent_));
        mouseDragged = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasMouseMoved(),new StringList(mouseEvent_));
        mouseMoved = ExecClassesUtil.getMethodBodiesById(mouseListener,fct_).first();
        String aliasWheelListener_ = _guiAliases.getAliasWheelListener();
        wheelListener = _classes.getClassBody(aliasWheelListener_);
        String wheelEvent_ = _guiAliases.getAliasWheelEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWheelMove(),new StringList(wheelEvent_));
        wheelMove = ExecClassesUtil.getMethodBodiesById(wheelListener,fct_).first();
        String aliasWindowListener_ = _guiAliases.getAliasWindowListener();
        windowListener = _classes.getClassBody(aliasWindowListener_);
        String windowEvent_ = _guiAliases.getAliasWindowEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowOpened(),new StringList(windowEvent_));
        windowOpened = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowClosed(),new StringList(windowEvent_));
        windowClosed = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowClosing(),new StringList(windowEvent_));
        windowClosing = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowActivated(),new StringList(windowEvent_));
        windowActivated = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowDeactivated(),new StringList(windowEvent_));
        windowDeactivated = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowIconified(),new StringList(windowEvent_));
        windowIconified = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasWindowDeiconified(),new StringList(windowEvent_));
        windowDeiconified = ExecClassesUtil.getMethodBodiesById(windowListener,fct_).first();
        String aliasListSelection_ = _guiAliases.getAliasListSelection();
        listSelection = _classes.getClassBody(aliasListSelection_);
        String ind_ = _content.getPrimTypes().getAliasPrimInteger();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasValueChanged(),new StringList(ind_,ind_));
        valueChanged = ExecClassesUtil.getMethodBodiesById(listSelection,fct_).first();
        String aliasChangeListener_ = _guiAliases.getAliasChangeListener();
        changeListener = _classes.getClassBody(aliasChangeListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasStateChanged(),new StringList());
        stateChanged = ExecClassesUtil.getMethodBodiesById(changeListener,fct_).first();
        String aliasTreeListener_ = _guiAliases.getAliasTreeListener();
        treeListener = _classes.getClassBody(aliasTreeListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasTreeListenerValueChanged(),new StringList(_guiAliases.getAliasTreeNode()));
        treeListenerValueChanged = ExecClassesUtil.getMethodBodiesById(treeListener,fct_).first();
        String aliasTableListener_ = _guiAliases.getAliasTableListener();
        tableListener = _classes.getClassBody(aliasTableListener_);
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasTableValueTableChanged(),new StringList(ind_,ind_));
        tableValueTableChanged = ExecClassesUtil.getMethodBodiesById(tableListener,fct_).first();
        String aliasKeyListener_ = _guiAliases.getAliasKeyListener();
        keyListener = _classes.getClassBody(aliasKeyListener_);
        String keyEvent_ = _guiAliases.getAliasKeyEvent();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasKeyReleased(),new StringList(keyEvent_));
        keyReleased = ExecClassesUtil.getMethodBodiesById(keyListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasKeyPressed(),new StringList(keyEvent_));
        keyPressed = ExecClassesUtil.getMethodBodiesById(keyListener,fct_).first();
        fct_ = new MethodId(MethodAccessKind.INSTANCE,
                _guiAliases.getAliasKeyTyped(),new StringList(keyEvent_));
        keyTyped = ExecClassesUtil.getMethodBodiesById(keyListener,fct_).first();
        String aliasPaint_ = _guiAliases.getAliasPaint();
        paint = _classes.getClassBody(aliasPaint_);
        fct_ = new MethodId(MethodAccessKind.STATIC,
                _guiAliases.getAliasPaintRefresh(),new StringList(_guiAliases.getAliasGrList()));
        paintRefresh = ExecClassesUtil.getMethodBodiesById(paint,fct_).first();
        fct_ = new MethodId(MethodAccessKind.STATIC,
                _guiAliases.getAliasPaintMethod(),new StringList(_guiAliases.getAliasComponent()));
        paintMethod = ExecClassesUtil.getMethodBodiesById(paint,fct_).first();
        fct_ = new MethodId(MethodAccessKind.STATIC,
                _guiAliases.getAliasPaintAdd(),new StringList(_guiAliases.getAliasGrList(),_content.getPrimTypes().getAliasPrimInteger(),_content.getCoreNames().getAliasObject()));
        paintAdd = ExecClassesUtil.getMethodBodiesById(paint,fct_).first();
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
            _frame.removeWindowListener(eventClose);
            _frame.addWindowListener((WindowListener)_event);
            _frame.getAbstractWindow().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }

    public void removeWindowListener(WindowStruct _inst, Struct _event) {
        if (_event instanceof WindowListener) {
            _inst.removeWindowListener((WindowListener)_event);
            CustList<Struct> user_ = new CustList<Struct>();
            for (WindowListener w: _inst.getWindowListeners()) {
                if (w instanceof Struct) {
                    user_.add((Struct)w);
                }
            }
            if (user_.isEmpty()) {
                if (_inst == frame) {
                    _inst.removeWindowListener(eventClose);
                    _inst.addWindowListener(eventClose);
                    _inst.getAbstractWindow().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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

    public MainWindow getWindow() {
        return window;
    }

    public IntStruct stringWidth(FontStruct _font, Struct _string) {
        if (!(_string instanceof StringStruct)) {
            return new IntStruct(-1);
        }
        return new IntStruct(textLabel.getFontMetrics(_font.getFont()).stringWidth(((StringStruct)_string).getInstance()));
    }


}
