package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
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
import code.stream.ThreadUtil;
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

    GuiContextEl(int _stackOverFlow, DefaultLockingClass _lock, GuiInitializer _init,
                 Options _options, ExecutingOptions _exec,
                 AnalysisMessages _mess,
                 KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _init, _options, _exec, _mess,_keyWords, _stds, _tabWidth);
        guiInit = _init;
    }

    public void initApplicationParts(StringList _mainArgs, MainWindow _window) {
        mainArgs = _mainArgs;
        window = _window;
        textLabel = new TextLabel("");
        OtherFrame fr_ = new OtherFrame();
        fr_.setMainFrame(true);
        fr_.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
