package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.MessagesExecutingOptions;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class OutputDialogExpresion implements SetupableFolder {
    private final WindowCdmEditor owner;
    private final AbsButton chooseFolder;
    private final AbsPlainLabel chosenFolder;
    private final AbsTextField srcFolder;
    private final ScrollCustomCombo chosenLanguage;
    private final AbsButton createEnv;
    private String folderExp = AbsEditorTabList.EMPTY_STRING;
    public OutputDialogExpresion(WindowCdmEditor _w) {
        owner = _w;
        AbstractProgramInfos frs_ = _w.getFrames();
        AbsPanel panel_ = frs_.getCompoFactory().newPageBox();
        StringMap<String> mes_ = MessagesIde.valManageString(frs_.currentLg());
        chooseFolder = frs_.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_MANAGE_STRING_FOLDER)));
        chooseFolder.addActionListener(new ChooseInitialFolderExp(this));
        chosenFolder = frs_.getCompoFactory().newPlainLabel(":");
        srcFolder = frs_.getCompoFactory().newTextField(32);
        StringList lgs_ = new StringList(frs_.getTranslations().getMapping().getKeys());
        lgs_.add(AbsEditorTabList.EMPTY_STRING);
        chosenLanguage = GuiBaseUtil.combo(frs_.getImageFactory(), lgs_, -1, frs_.getCompoFactory());
        createEnv = frs_.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_MANAGE_STRING_VALIDATE)));
        createEnv.addActionListener(new ValidateExpressionEnvEvent(this));
        createEnv.setEnabled(false);
        panel_.add(chooseFolder);
        panel_.add(chosenFolder);
        panel_.add(srcFolder);
        chosenLanguage.select(StringUtil.indexOf(lgs_,frs_.getLanguage()));
        panel_.add(chosenLanguage.getGlobal());
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

    public AbsButton getChooseFolder() {
        return chooseFolder;
    }

    public WindowCdmEditor getOwner() {
        return owner;
    }

    public void apply() {
        StringMap<String> mesKeys_ = MessagesExecutingOptions.valExecOptionsKeys(owner.getFrames().currentLg());
        String src_ = mesKeys_.getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_SRC);
        StringList def_ = new StringList();
        def_.add(folderExp);
        def_.add(StringUtil.nullToEmpty(GuiBaseUtil.getSelectedItem(chosenLanguage)));
        if (!srcFolder.getText().isEmpty()) {
            def_.add(src_+ ExecutingOptions.EXEC_OPTIONS_SEP+srcFolder.getText());
        }
        owner.getSoftParams().setLines(def_);
        owner.trySubmit();
        WindowWithTreeImpl.updateDoc(owner);
        owner.folderExp(folderExp);
    }

    public AbsTextField getSrcFolder() {
        return srcFolder;
    }

    public AbsButton getCreateEnv() {
        return createEnv;
    }
}
