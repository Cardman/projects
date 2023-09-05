package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.WatchPoint;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class OkWpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public OkWpFormEvent(AbsDebuggerGui _w, ResultContext _res) {
        this.window = _w;
        currentResult = _res;
    }

    @Override
    public void action() {
        WatchPointBlockPair wp_ = window.getFramePoints().getFrameWpFormContent().getSelectedWp();
        if (wp_ == null) {
            String clName_ = window.getFramePoints().getFrameWpFormContent().getClassName().getText();
            RootBlock r_ = currentResult.getPageEl().getAnaClassBody(clName_);
            String fieldName_ = window.getFramePoints().getFrameWpFormContent().getFieldName().getText();
            if (!valid(r_, window.getFramePoints().getFrameWpFormContent().getTrueField().isSelected(), fieldName_)) {
                return;
            }
            wp_ = currentResult.watch(window.getFramePoints().getFrameWpFormContent().getTrueField().isSelected(),new SynthFieldInfo(new ClassField(clName_, fieldName_),r_));
            currentResult.getContext().watchList().add(wp_);
        }
        wp_.getValue().setEnabled(window.getFramePoints().getFrameWpFormContent().getEnabledWp().isSelected());
        wp_.getValue().setRead(window.getFramePoints().getFrameWpFormContent().getRead().isSelected());
        wp_.getValue().setWrite(window.getFramePoints().getFrameWpFormContent().getWrite().isSelected());
        wp_.getValue().setCompoundRead(window.getFramePoints().getFrameWpFormContent().getCompoundRead().isSelected());
        wp_.getValue().setCompoundWrite(window.getFramePoints().getFrameWpFormContent().getCompoundWrite().isSelected());
        wp_.getValue().setCompoundWriteErr(window.getFramePoints().getFrameWpFormContent().getCompoundWriteErr().isSelected());
        update(wp_,wp_.getValue().getResultRead(),window,window.getFramePoints().getFrameWpFormContent().getGuiReadStackForm(),false, currentResult);
        update(wp_,wp_.getValue().getResultWrite(),window,window.getFramePoints().getFrameWpFormContent().getGuiWriteStackForm(),true, currentResult);
        update(wp_,wp_.getValue().getResultCompoundRead(),window,window.getFramePoints().getFrameWpFormContent().getGuiCompoundReadStackForm(),false, currentResult);
        update(wp_,wp_.getValue().getResultCompoundWrite(),window,window.getFramePoints().getFrameWpFormContent().getGuiCompoundWriteStackForm(),true, currentResult);
        update(wp_,wp_.getValue().getResultCompoundWriteErr(),window,window.getFramePoints().getFrameWpFormContent().getGuiCompoundWriteErrStackForm(),true, currentResult);
        window.getFramePoints().getFrameWpFormContent().setSelectedWp(null);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshWatch(window, currentResult);
        window.getFramePoints().getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
    }
    static boolean valid(RootBlock _r, boolean _selected, String _text) {
        if (_r == null) {
            return false;
        }
        if (_selected) {
            FieldInfo fi_ = ContextUtil.getFieldInfo(_r, _r.getFullName(), _text);
            return fi_ != null;
        }
        for (NamedCalledFunctionBlock b: _r.getAnnotationsMethodsBlocks()) {
            if (StringUtil.quickEq(b.getName(), _text)) {
                return true;
            }
        }
        return false;
    }
    private static void update(WatchPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, boolean _setting, ResultContext _res) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(),_res, _window.getResultContextNext().generateAdv(_res.getContext().getInterrupt()), _setting);
        OkMpFormEvent.update(_condition,_form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(WatchPoint.BPC_READ)) {
            _condition.getOthers().add(_mp.getValue().getResultRead());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(WatchPoint.BPC_WRITE)) {
            _condition.getOthers().add(_mp.getValue().getResultWrite());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(WatchPoint.BPC_COMPOUND_READ)) {
            _condition.getOthers().add(_mp.getValue().getResultCompoundRead());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(WatchPoint.BPC_COMPOUND_WRITE)) {
            _condition.getOthers().add(_mp.getValue().getResultCompoundWrite());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(WatchPoint.BPC_COMPOUND_WRITE_ERR)) {
            _condition.getOthers().add(_mp.getValue().getResultCompoundWriteErr());
        }
    }
}
