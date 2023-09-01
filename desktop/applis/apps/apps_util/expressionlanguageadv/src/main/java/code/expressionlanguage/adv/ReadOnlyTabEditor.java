package code.expressionlanguage.adv;

import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;

public final class ReadOnlyTabEditor extends AbsReadOnlyTabEditor implements AbsTabEditor {
    private final String fullPath;
    private final String useFeed;
    public ReadOnlyTabEditor(AbsDebuggerGui _dbg, AbstractProgramInfos _frame, String _rel, String _lr, Options _options, ResultContext _res) {
        super(_dbg, _frame, _options);
        fullPath = _rel;
        useFeed = _lr;
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new ToggleBreakPointEvent(this, _res)),GuiConstants.VK_F2,0);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new ToggleBreakPointEnabledEvent(this,_res)),GuiConstants.VK_F3,0);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new BreakPointFormEvent(_dbg, this, _res)),GuiConstants.VK_F4,0);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new ToggleWatchPointEvent(this,_res)),GuiConstants.VK_F2,GuiConstants.CTRL_DOWN_MASK);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new ToggleWatchPointEnabledEvent(this,_res)),GuiConstants.VK_F3,GuiConstants.CTRL_DOWN_MASK);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new WatchPointFormEvent(_dbg, this,_res)),GuiConstants.VK_F4,GuiConstants.CTRL_DOWN_MASK);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new GoToDbgDefinitionEvent(_dbg, this, _res)),GuiConstants.VK_F5,0);
        getCenter().registerKeyboardAction(getCompoFactory().wrap(new CloseReadOnlyTabEditorEvent(this)),GuiConstants.VK_K,GuiConstants.CTRL_DOWN_MASK);
    }

    @Override
    public String getUseFeed() {
        return useFeed;
    }

    @Override
    public String getFullPath() {
        return fullPath;
    }

}
