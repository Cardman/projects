package code.expressionlanguage;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.OtherFrame;
import code.gui.TextLabel;
import code.util.StringList;

import javax.swing.*;
import java.awt.event.WindowListener;

public final class GuiContextEl extends RunnableContextEl {
    private TextLabel textLabel;
    private FrameStruct frame;
    private StringList mainArgs;

    GuiContextEl(int _stackOverFlow, DefaultLockingClass _lock, CustInitializer _init, Options _options, ExecutingOptions _exec, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _init, _options, _exec, _keyWords, _stds, _tabWidth);
    }

    public void initApplicationParts(StringList _mainArgs) {
        mainArgs = _mainArgs;
        textLabel = new TextLabel("");
        OtherFrame fr_ = new OtherFrame();
        fr_.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame = new FrameStruct(fr_);
    }

    GuiContextEl(ContextEl _context) {
        super(_context);
        textLabel = ((GuiContextEl)_context).textLabel;
        frame = ((GuiContextEl)_context).frame;
        mainArgs = ((GuiContextEl)_context).mainArgs;
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
                    _inst.getAbstractWindow().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
