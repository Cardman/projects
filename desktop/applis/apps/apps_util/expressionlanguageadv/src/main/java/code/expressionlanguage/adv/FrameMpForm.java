package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;

public final class FrameMpForm extends AdvFrameMpForm{
    private final FrameMpFormContent frameMpFormContent;
    private AbsPlainLabel edited;
    private AbsTextField fileName;
    private AbsSpinner caret;
    public FrameMpForm() {
        frameMpFormContent = new FrameMpFormContent();
    }
    public void guiBuild(AbsDebuggerGui _d) {
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel("");
        fileName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        frameMpFormContent.guiBuildBase(_d);
        caret = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        AbsPanel f_ = frameMpFormContent.getContentPaneForm();
        frameMpFormContent.getContentPane().removeAll();
        frameMpFormContent.getContentPane().add(f_);
        frameMpFormContent.getContentPane().add(fileName);
        frameMpFormContent.getContentPane().add(caret);
        frameMpFormContent.getContentPane().add(frameMpFormContent.getPref());
        frameMpFormContent.getContentPane().add(frameMpFormContent.getOk());
        frameMpFormContent.getContentPane().add(frameMpFormContent.getRemove());
    }
    public void initForm(MethodPointBlockPair _wp, AbsCommonFrame _c, ResultContext _r) {
        setSelectedMp(_wp);
        MethodPointBlockPair exc_ = getSelectedMp();
        if (exc_ != null) {
            BreakPointFormEvent.methodAction(exc_,this,_c,_r);
            frameMpFormContent.getRemove().setEnabled(true);
        } else {
            getGuiEnterStackForm().getDependantPointsForm().init(_r,3);
            getGuiExitStackForm().getDependantPointsForm().init(_r,3);
            getEdited().setText("");
            frameMpFormContent.getRemove().setEnabled(false);
        }
    }

    public AbsTextField getFileName() {
        return fileName;
    }

    public AbsSpinner getCaret() {
        return caret;
    }

    public AbsPlainLabel getEdited() {
        return edited;
    }

    public AbsPanel getContentPane() {
        return frameMpFormContent.getContentPane();
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
