package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.core.StringUtil;

public final class OutputDialogExpresion {
    private final WindowCdmEditor owner;
    private final AbsPlainButton chooseFolder;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private final GraphicComboGrInt chosenLanguage;
    private final AbsPlainButton createEnv;
    private String folderExp = "";
    public OutputDialogExpresion(WindowCdmEditor _w) {
        owner = _w;
        AbstractProgramInfos frs_ = _w.getCommonFrame().getFrames();
        AbsPanel panel_ = frs_.getCompoFactory().newPageBox();
        chooseFolder = frs_.getCompoFactory().newPlainButton("folder");
        chooseFolder.addActionListener(new ChooseInitialFolderExp(this));
        chosenFolder = frs_.getCompoFactory().newPlainLabel(":");
        srcFolder = frs_.getCompoFactory().newTextField(32);
        StringList lgs_ = new StringList(frs_.getTranslations().getMapping().getKeys());
        lgs_.add("");
        chosenLanguage = frs_.getGeneComboBox().createCombo(frs_.getImageFactory(), lgs_, -1, frs_.getCompoFactory());
        createEnv = frs_.getCompoFactory().newPlainButton("create");
        createEnv.addActionListener(new ValidateExpressionEnvEvent(this));
        createEnv.setEnabled(false);
        panel_.add(chooseFolder);
        panel_.add(chosenFolder);
        panel_.add(srcFolder);
        chosenLanguage.selectItem(StringUtil.indexOf(lgs_,_w.getCommonFrame().getLanguageKey()));
        panel_.add(chosenLanguage.self());
        panel_.add(createEnv);
        _w.getDialogFolderExpression().setContentPane(panel_);
        _w.getDialogFolderExpression().pack();
        _w.getDialogFolderExpression().setVisible(true);
    }

    public void folder(String _folderName) {
        folderExp = _folderName;
        chosenFolder.setText(_folderName);
        createEnv.setEnabled(true);
    }

    public String getFolderExp() {
        return folderExp;
    }

    public AbsPlainButton getChooseFolder() {
        return chooseFolder;
    }

    public WindowCdmEditor getOwner() {
        return owner;
    }

    public void apply() {
        StringList def_ = new StringList();
        def_.add(folderExp);
        def_.add(StringUtil.nullToEmpty(chosenLanguage.getSelectedItem()));
        if (!srcFolder.getText().isEmpty()) {
            def_.add("src="+ srcFolder.getText());
        }
        owner.getSoftParams().setLines(def_);
        owner.folderExp(folderExp);
    }

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public AbsPlainButton getCreateEnv() {
        return createEnv;
    }
}
