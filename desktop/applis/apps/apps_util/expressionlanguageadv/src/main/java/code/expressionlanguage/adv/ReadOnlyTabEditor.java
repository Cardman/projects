package code.expressionlanguage.adv;

import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class ReadOnlyTabEditor implements AbsTabEditor {
    private final AbsScrollPane scCenter;
    private final AbsTextPane center;
    private final AbsPanel panel;
    private final AbsPlainLabel label;
    private final ToggleBreakPointEvent toggleBp;
    private final ToggleBreakPointEnabledEvent toggleBpEvent;
    private final BreakPointFormEvent formBpEvent;
    private final AbsCompoFactory compoFactory;
    private final String fullPath;
    private final String useFeed;
    private final Options opt;
    public ReadOnlyTabEditor(AbsDebuggerGui _dbg, AbstractProgramInfos _frame, String _rel, String _lr, Options _options) {
        opt = _options;
        useFeed = _lr;
        fullPath = _rel;
        compoFactory = _frame.getCompoFactory();
        label = compoFactory.newPlainLabel(":");
        center = compoFactory.newTextPane();
        center.setFocusable(true);
        center.addMouseListener(new ClickTextPane(center));
        center.visibleCaret();
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        center.addCaretListener(new EditorCaretListener(this));
        center.setEditable(false);
        toggleBp = new ToggleBreakPointEvent(this);
        center.registerKeyboardAction(compoFactory.wrap(toggleBp),GuiConstants.VK_F2,0);
        toggleBpEvent = new ToggleBreakPointEnabledEvent(this);
        center.registerKeyboardAction(compoFactory.wrap(toggleBpEvent),GuiConstants.VK_F3,0);
        formBpEvent = new BreakPointFormEvent(_dbg, this);
        center.registerKeyboardAction(compoFactory.wrap(formBpEvent),GuiConstants.VK_F4,0);
        panel = compoFactory.newPageBox();
        AbsPanel upp_ = compoFactory.newPageBox();
        scCenter = compoFactory.newAbsScrollPane(center);
        scCenter.setPreferredSize(new MetaDimension(512,512));
        upp_.add(getScCenter());
        upp_.add(label);
        panel.add(upp_);
    }

    public AbsScrollPane getScCenter() {
        return scCenter;
    }

    public void update(ResultContext _result) {
        toggleBp.setResult(_result);
        toggleBpEvent.setResult(_result);
        formBpEvent.setResult(_result);
    }

    @Override
    public AbsTextPane getCenter() {
        return center;
    }

    @Override
    public int getTabWidth() {
        return opt.getTabWidth();
    }

    @Override
    public String getUseFeed() {
        return useFeed;
    }

    @Override
    public AbsPlainLabel getLabel() {
        return label;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    public String getFullPath() {
        return fullPath;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }
}
