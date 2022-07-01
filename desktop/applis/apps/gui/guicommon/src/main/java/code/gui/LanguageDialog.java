package code.gui;


import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.consts.Constants;


public final class LanguageDialog implements SetterLanguage {

    private static final String NO_TITLE = " ";
    private final AbsDialog absDialog;

    private final CustButtonGroup groupe = new CustButtonGroup();
    private String langue;

    public LanguageDialog(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
    }
    public static void changeLanguage(String _lg, AbstractProgramInfos _infos, String _folder){
        if(_lg == null || _lg.isEmpty()) {
            return;
        }
        FrameUtil.changeStaticLanguage(_lg, _infos);
        SoftApplicationCore.saveLanguage(_folder, _lg,_infos.getStreams());

    }

    public static void setLanguageDialog(GroupFrame _owner) {
        initWithoutTitle(_owner);
    }

    private static void initWithoutTitle(GroupFrame _owner) {
        setLanguageDialog(_owner, NO_TITLE);
    }

    public static void setLanguageDialog(GroupFrame _owner, String _title) {
        _owner.getLanguageDialog().init(_owner, _title);
    }

    private void init(GroupFrame _owner, String _title) {
        absDialog.setDialogIcon(_owner.getImageFactory(),_owner);
        absDialog.setLocationRelativeTo(_owner);
        absDialog.setTitle(_title);
        AbsPanel panneau_ = _owner.getCompoFactory().newGrid(0,1);
        for (String l: Constants.getAvailableLanguages()) {
            AbsRadioButton radio_ = _owner.getCompoFactory().newRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            groupe.add(radio_);
            panneau_.add(radio_);
        }
        absDialog.setContentPane(panneau_);
        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        absDialog.closeWindow();
    }

    public static String getStaticLanguage(LanguageDialog _dialog) {
        return _dialog.getLanguage();
    }

    @Override
    public String getLanguage() {
        absDialog.setVisible(true);
        return langue;
    }
}
