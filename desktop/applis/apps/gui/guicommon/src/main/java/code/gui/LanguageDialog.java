package code.gui;


import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.gui.initialize.AbstractProgramInfos;


public final class LanguageDialog implements SetterLanguage {

    private final AbsDialog absDialog;

    private final CustButtonGroup groupe = new CustButtonGroup();
    private String langue;

    public LanguageDialog(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
    }

    public void init(AbsCommonFrame _owner,AbstractProgramInfos _pr, String _title) {
        absDialog.setDialogIcon(_pr.getImageFactory(),_owner);
        absDialog.setLocationRelativeTo(_owner);
        absDialog.setTitle(_title);
        AbsPanel panneau_ = _pr.getCompoFactory().newGrid(0,1);
        for (String l: _pr.getLanguages()) {
            AbsRadioButton radio_ = _pr.getCompoFactory().newRadioButton(_pr.getDisplayLanguages().getVal(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            groupe.add(radio_);
            panneau_.add(radio_);
        }
        absDialog.setContentPane(panneau_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        absDialog.closeWindow();
    }

    @Override
    public String getLanguage() {
        absDialog.setVisible(true);
        return langue;
    }
}
