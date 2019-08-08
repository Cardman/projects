package code.expressionlanguage;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.TextLabel;
import code.util.StringList;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public final class GuiContextEl extends RunnableContextEl {
    private TextLabel textLabel;
    private FrameStruct frame;
    private String lgExec;
    private StringList mainArgs;

    GuiContextEl(int _stackOverFlow, DefaultLockingClass _lock, CustInitializer _init, Options _options, ExecutingOptions _exec, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _init, _options, _exec, _keyWords, _stds, _tabWidth);
    }

    public void initApplicationParts(String _lgExec, StringList _mainArgs) {
        mainArgs = _mainArgs;
        textLabel = new TextLabel("");
        MainInitFrame fr_ = new MainInitFrame(_lgExec);
        fr_.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame = new FrameStruct(fr_);
        lgExec = _lgExec;
    }

    GuiContextEl(ContextEl _context) {
        super(_context);
        textLabel = ((GuiContextEl)_context).textLabel;
        frame = ((GuiContextEl)_context).frame;
        lgExec = ((GuiContextEl)_context).lgExec;
        mainArgs = ((GuiContextEl)_context).mainArgs;
    }

    public void addWindowListener(FrameStruct _frame,Struct _event) {
        if (_event instanceof WindowListener) {
            _frame.addWindowEvent((WindowListener)_event);
            _frame.getCommonFrame().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
    public String getLgExec() {
        return lgExec;
    }

    public FrameStruct getFrame() {
        return frame;
    }

    public StringList getMainArgs() {
        return mainArgs;
    }

    public boolean matchMain(WindowEvent _event) {
        return frame.getCommonFrame().getComponent() == _event.getWindow();
    }
    public int stringWidth(FontStruct _font, StringStruct _string) {
        return textLabel.getFontMetrics(_font.getFont()).stringWidth(_string.getInstance());
    }
}
