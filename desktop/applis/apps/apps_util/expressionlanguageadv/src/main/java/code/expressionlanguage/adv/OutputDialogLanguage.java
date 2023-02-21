package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.GraphicComboGrInt;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.core.StringUtil;

public final class OutputDialogLanguage {
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    private final GraphicComboGrInt chosenLanguage;

    public OutputDialogLanguage(WindowCdmEditor _w) {
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        StringList lgs_ = new StringList(factories_.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage = factories_.getGeneComboBox().createCombo(factories_.getImageFactory(), lgs_, StringUtil.indexOf(lgs_,_w.getUsedLg()), factories_.getCompoFactory());
        all_.add(chosenLanguage.self());
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateUsedLanguage(chosenLanguage,_w));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogLanguage()));
        all_.add(cancel);
        _w.getDialogLanguage().setContentPane(all_);
        _w.getDialogLanguage().pack();
        _w.getDialogLanguage().setVisible(true);
    }

    public GraphicComboGrInt getChosenLanguage() {
        return chosenLanguage;
    }
    public AbsPlainButton getCancel() {
        return cancel;
    }


    public AbsPlainButton getVal() {
        return val;
    }
}
