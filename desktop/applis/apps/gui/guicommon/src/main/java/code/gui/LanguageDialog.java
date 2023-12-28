package code.gui;


import code.gui.events.SetterLanguage;
import code.gui.initialize.AbstractProgramInfos;


public final class LanguageDialog implements SetterLanguage {

    private final AbsDialog absDialog;
    private final LanguageComponent content;

    private String langue;

    public LanguageDialog(AbstractProgramInfos _frameFactory) {
        content = new LanguageComponent(_frameFactory);
        absDialog = _frameFactory.getFrameFactory().newDialog();
    }

    public void init(AbsCommonFrame _owner,AbstractProgramInfos _pr, String _title) {
        absDialog.setDialogIcon(_pr.getImageFactory(),_owner);
        absDialog.setLocationRelativeTo(_owner);
        absDialog.setTitle(_title);
        content.init(_pr,this);
        absDialog.setContentPane(content.getPanel());
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    public LanguageComponent getContent() {
        return content;
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
