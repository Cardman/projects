package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.gui.AbsPanel;
import code.gui.AbsRadioButton;
import code.gui.CustButtonGroup;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ExactMatchingTypeForm {
    private AbsPanel panel;
    private AbsRadioButton inherit;
    private AbsRadioButton same;
    private AbsRadioButton sameFamily;

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valGroup(_d.getFrames().currentLg());
        String inh_ = mes_.getVal(MessagesIde.IDE_POINTS_INH_FROM);
        String fam_ = mes_.getVal(MessagesIde.IDE_POINTS_FAMILY);
        String ex_ = mes_.getVal(MessagesIde.IDE_POINTS_EXACT);
        panel = _d.getFrames().getCompoFactory().newPageBox();
        CustButtonGroup group_ = new CustButtonGroup();
        inherit = _d.getFrames().getCompoFactory().newRadioButton(StringUtil.nullToEmpty(inh_));
        group_.add(inherit);
        panel.add(inherit);
        sameFamily = _d.getFrames().getCompoFactory().newRadioButton(StringUtil.nullToEmpty(fam_));
        group_.add(sameFamily);
        panel.add(sameFamily);
        same = _d.getFrames().getCompoFactory().newRadioButton(StringUtil.nullToEmpty(ex_));
        group_.add(same);
        panel.add(same);
    }
    public int code() {
        if (inherit.isSelected()) {
            return ExcPointBlockKey.INHERIT;
        }
        if (same.isSelected()) {
            return ExcPointBlockKey.SAME;
        }
        return ExcPointBlockKey.SAME_FAMILY;
    }

    public void updateValue(ExcPointBlockKey _key) {
        if (_key == null) {
            inherit.setEnabled(true);
            same.setEnabled(true);
            sameFamily.setEnabled(true);
            inherit.setSelected(true);
            return;
        }
        if (ExcPointBlockKey.INHERIT == _key.isExact()) {
            getInherit().setSelected(true);
        }
        if (ExcPointBlockKey.SAME == _key.isExact()) {
            getSame().setSelected(true);
        }
        if (ExcPointBlockKey.SAME_FAMILY == _key.isExact()) {
            getSameFamily().setSelected(true);
        }
        inherit.setEnabled(false);
        sameFamily.setEnabled(false);
        same.setEnabled(false);
    }

    public AbsRadioButton getInherit() {
        return inherit;
    }

    public AbsRadioButton getSame() {
        return same;
    }

    public AbsRadioButton getSameFamily() {
        return sameFamily;
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
