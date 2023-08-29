package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.initialize.AbstractProgramInfos;

public final class FrameMpForm extends AdvFrameMpForm{
    private final AbsCommonFrame commonFrame;
    private final FrameMpFormContent frameMpFormContent;
    public FrameMpForm(AbsDebuggerGui _d, String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        commonFrame.addWindowListener(new CancelMpFormEvent(_d));
        frameMpFormContent = new FrameMpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        frameMpFormContent.guiBuildBase(_d);
        AbsPanel f_ = frameMpFormContent.getContentPaneForm();
        frameMpFormContent.getContentPane().removeAll();
        frameMpFormContent.getContentPane().add(f_);
        frameMpFormContent.getContentPane().add(frameMpFormContent.getPref());
        frameMpFormContent.getContentPane().add(frameMpFormContent.getOk());
        commonFrame.setContentPane(frameMpFormContent.getContentPane());
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    @Override
    public AbsFrameMpFormContent form() {
        return getFrameMpFormContent();
    }

    public FrameMpFormContent getFrameMpFormContent() {
        return frameMpFormContent;
    }

    public MethodPointBlockPair getSelectedMp() {
        return getFrameMpFormContent().getSelectedMp();
    }

    public void setSelectedMp(MethodPointBlockPair _s) {
        this.getFrameMpFormContent().setSelectedMp(_s);
    }

}
