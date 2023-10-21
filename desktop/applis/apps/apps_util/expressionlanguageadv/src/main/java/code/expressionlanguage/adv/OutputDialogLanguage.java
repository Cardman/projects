package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.core.StringUtil;

public final class OutputDialogLanguage implements WithFrame {
    private final AbsPlainButton val;
    private final ScrollCustomCombo chosenLanguage;
    private final AbsCommonFrame frame;
    private final AbsMenuItem associated;

    public OutputDialogLanguage(WindowWithTreeImpl _w,AbsCommonFrame _fr, AbsMenuItem _c) {
        frame = _fr;
        associated = _c;
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        StringList lgs_ = new StringList(factories_.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage = GuiBaseUtil.combo(factories_.getImageFactory(), lgs_, StringUtil.indexOf(lgs_,_w.getUsedLg()), factories_.getCompoFactory());
        all_.add(chosenLanguage.getGlobal());
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateUsedLanguage(chosenLanguage,_w));
        all_.add(val);
        frame.setContentPane(all_);
        frame.pack();
        frame.setVisible(true);
        associated.setEnabled(false);
    }

    public void reinit(WindowWithTreeImpl _w) {
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        StringList lgs_ = new StringList(factories_.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage.select(StringUtil.indexOf(lgs_,_w.getUsedLg()));
        frame.setVisible(true);
        associated.setEnabled(false);
    }
    public ScrollCustomCombo getChosenLanguage() {
        return chosenLanguage;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    @Override
    public AbsMenuItem getMenu() {
        return associated;
    }
    public AbsPlainButton getVal() {
        return val;
    }
}
