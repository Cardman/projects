package code.gui;

import javax.swing.WindowConstants;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.gui.images.AbstractImage;
import code.util.StringMap;
import code.util.consts.Constants;

public final class LanguageFrame implements SetterLanguage {

    private static final String TITLE = " ";

    private CustButtonGroup group = new CustButtonGroup();

    private String langue;

    private String[] args;

    private SoftApplicationCore soft;

    private String dir;
    private final AbsCommonFrame commonFrame;

    LanguageFrame(String _dir, String[] _args, SoftApplicationCore _soft, AbstractImage _icon) {
        commonFrame = _soft.getFrames().getFrameFactory().newCommonFrame(Constants.getDefaultLanguage(),_soft.getFrames(), null);
        dir = _dir;
        if (_icon != null) {
            commonFrame.setIconImage(_icon);
        }
        init(_args, _soft);
    }

    private void init(String[] _args, SoftApplicationCore _soft) {
        soft = _soft;
        args = _args;
        commonFrame.setTitle(TITLE);
        AbsPanel panneau_ = _soft.getFrames().getCompoFactory().newGrid(0,1);
        for (String l: Constants.getAvailableLanguages()) {
            AbsRadioButton radio_ = _soft.getFrames().getCompoFactory().newRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            group.add(radio_);
            panneau_.add(radio_);
        }
        commonFrame.setContentPane(panneau_);
        commonFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        commonFrame.setLocationRelativeToNull();
        commonFrame.setVisible(true);
        commonFrame.pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        commonFrame.dispose();
        SoftApplicationCore.saveLanguage(dir, _language,soft.getFrames().getStreams());
        commonFrame.getPane().removeAll();
        StringMap<Object> file_ = soft.getFile(args);
        soft.launch(langue, file_);
    }

    @Override
    public String getLanguage() {
        return langue;
    }

}

