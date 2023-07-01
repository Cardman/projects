package code.gui;



import code.gui.events.LanguageChoice;
import code.gui.events.QuittingEvent;
import code.gui.events.SetterLanguage;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamLanguageUtil;
import code.util.consts.Constants;

public final class LanguageFrame extends GroupFrame implements SetterLanguage {

    private static final String TITLE = " ";

    private CustButtonGroup group = new CustButtonGroup();

    private String langue;

    private String[] args;

    private SoftApplicationCore soft;

    private String dir;
    private final AbsCommonFrame commonFrame;

    LanguageFrame(String _dir, String[] _args, SoftApplicationCore _soft, AbstractImage _icon) {
        super("",_soft.getFrames());
        commonFrame = _soft.getFrames().getFrameFactory().newCommonFrame(Constants.getDefaultLanguage(),_soft.getFrames(), null);
        _soft.getFrames().getFrames().add(this);
        dir = _dir;
        if (_icon != null) {
            commonFrame.setIconImage(_icon);
        }
        soft = _soft;
        args = _args;
        init(null,_soft.getFrames(), TITLE);
    }

    @Override
    public void init(AbsCommonFrame _owner, AbstractProgramInfos _pr, String _title) {
        commonFrame.setTitle(TITLE);
        AbsPanel panneau_ = _pr.getCompoFactory().newGrid(0,1);
        for (String l: _pr.getLanguages()) {
            AbsRadioButton radio_ = _pr.getCompoFactory().newRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            group.add(radio_);
            panneau_.add(radio_);
        }
        commonFrame.setContentPane(panneau_);
//        commonFrame.setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        commonFrame.setLocationRelativeToNull();
        commonFrame.addWindowListener(new QuittingEvent(this));
        commonFrame.setVisible(true);
        commonFrame.pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        commonFrame.dispose();
        StreamLanguageUtil.saveLanguage(dir, _language,soft.getFrames().getStreams());
        commonFrame.getPane().removeAll();
        getFrames().getFrames().removeLast();
        soft.launchFile(args,langue);
    }

    @Override
    public String getLanguage() {
        return langue;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        quit();
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public void quit() {
        basicDispose();
    }
}

