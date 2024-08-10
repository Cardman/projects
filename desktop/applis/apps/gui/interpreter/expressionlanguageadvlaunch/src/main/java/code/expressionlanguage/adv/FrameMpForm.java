package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.MethodPoint;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class FrameMpForm extends AdvFrameMpForm{
    private final FrameMpFormContent frameMpFormContent;
    private AbsPlainLabel edited;
    private AbsTextField fileName;
    private AbsSpinner caret;
    public FrameMpForm(AbstractProgramInfos _c) {
        frameMpFormContent = new FrameMpFormContent(_c);
    }
    public void guiBuild(AbsDebuggerGui _d) {
        edited = _d.getCommonFrame().getFrames().getCompoFactory().newPlainLabel(AbsEditorTabList.EMPTY_STRING);
        fileName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        frameMpFormContent.guiBuildBase(_d);
        caret = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        AbsPanel f_ = frameMpFormContent.getContentPaneForm();
        frameMpFormContent.getContentPane().removeAll();
        frameMpFormContent.getContentPane().add(f_);
        frameMpFormContent.getContentPane().add(fileName);
        frameMpFormContent.getContentPane().add(caret);
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
            getGuiEnterStackForm().getDependantPointsForm().init(_r,MethodPointBlockPair.CMP);
            getGuiExitStackForm().getDependantPointsForm().init(_r,MethodPointBlockPair.CMP);
            updatePref(BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_ENTRY),getGuiEnterStackForm(),_c,_r);
            updatePref(BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_EXIT),getGuiExitStackForm(),_c,_r);
            getEdited().setText(AbsEditorTabList.EMPTY_STRING);
            frameMpFormContent.getRemove().setEnabled(false);
        }
    }

    public static void updatePref(CustList<BreakPointCondition> _bpc, GuiStackForm _g, AbsCommonFrame _c, ResultContext _r) {
        _g.getPref().setValue(BreakPointBlockList.prefIn(_bpc));
        GuiBaseUtil.initStringMapInt(_c,_g.getPrefs(),new StringMap<Integer>(),new StringList(_r.getContext().getClasses().getClassesBodies().getKeys()),new StrictTypeFromFilter(_r));
        GuiStackForm.initPrefs(_bpc, _g.getPrefs());
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
