package aiki.gui.dialogs;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.sml.LoadingGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.events.ValidateSoftParams;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogSoftParams {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.softparams";

    private static final String TITLE = "title";
    private static final String ZIP_LOAD = "zipLoad";
    private static final String GAME_LOAD = "gameLoad";
    private static final String SAVE_EXIT = "saveExit";
    private static final String ANIMATION = "animation";
    private static final String ANIMATION_MOVING = "animationMoving";
    private static final String CLICK_PAD = "clickPad";
    private static final String ENABLE_KEY_PAD = "enableKeyPad";
    private static final String SELECT_HOME_PATH = "selectHomePath";
    private static final String SELECT_HOME_PATH_ZIP = "selectHomePathZip";
    private final AbsDialog absDialog;

    private StringMap<String> messages;

    private AbsCustCheckBox loadLastRom;

    private AbsCustCheckBox loadLastGame;

    private AbsCustCheckBox saveGameAtExit;

    private AbsCustCheckBox enableAnimation;

    private AbsCustCheckBox enableMovingHerosAnimation;

    private AbsCustCheckBox clickButtonsPad;

    private AbsCustCheckBox enabledKeyPad;

    private AbsCustCheckBox selectHomePath;

    private AbsCustCheckBox selectHomePathZip;

    private boolean ok;

    public DialogSoftParams(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setSoftParams(WindowAiki _window, LoadingGame _loading) {
        _window.getSoftParams().init(_window, _loading);
    }

    private void init(WindowAiki _window, LoadingGame _loading) {
        absDialog.setDialogIcon(_window.getImageFactory(),_window);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        ok = false;
        absDialog.setTitle(messages.getVal(TITLE));
        absDialog.setLocationRelativeTo(_window);
        AbsPanel panel_ = _window.getCompoFactory().newGrid(0,1);
//        loadLastRom = _loading.isLoadLastRom();
//        loadLastGame = _loading.isLoadLastGame();
//        saveGameAtExit = _loading.isSaveGameAtExit();
//        enableAnimation = _loading.isEnableAnimation();
//        clickButtonsPad = _loading.isClickButtonsPad();
//        enabledKeyPad = _loading.isEnabledKeyPad();
//        selectHomePath = _loading.isSaveHomeFolder();
//        selectHomePathZip = _loading.isLoadHomeFolder();
//        JCheckBox check_;
        loadLastRom = _window.getCompoFactory().newCustCheckBox(messages.getVal(ZIP_LOAD));
        loadLastRom.setSelected(_loading.isLoadLastRom());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastRom = !loadLastRom;
//            }
//        });
        panel_.add(loadLastRom);
        loadLastGame = _window.getCompoFactory().newCustCheckBox(messages.getVal(GAME_LOAD));
        loadLastGame.setSelected(_loading.isLoadLastGame());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastGame = !loadLastGame;
//            }
//        });
        panel_.add(loadLastGame);
        enableAnimation = _window.getCompoFactory().newCustCheckBox(messages.getVal(ANIMATION));
        enableAnimation.setSelected(_loading.isEnableAnimation());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enableAnimation = !enableAnimation;
//            }
//        });
        panel_.add(enableAnimation);
        panel_.add(loadLastGame);
        enableMovingHerosAnimation = _window.getCompoFactory().newCustCheckBox(messages.getVal(ANIMATION_MOVING));
        enableMovingHerosAnimation.setSelected(_loading.isEnableMovingHerosAnimation());
        panel_.add(enableMovingHerosAnimation);
        clickButtonsPad = _window.getCompoFactory().newCustCheckBox(messages.getVal(CLICK_PAD));
        clickButtonsPad.setSelected(_loading.isClickButtonsPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                clickButtonsPad = !clickButtonsPad;
//            }
//        });
        panel_.add(clickButtonsPad);
        enabledKeyPad = _window.getCompoFactory().newCustCheckBox(messages.getVal(ENABLE_KEY_PAD));
        enabledKeyPad.setSelected(_loading.isEnabledKeyPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enabledKeyPad = !enabledKeyPad;
//            }
//        });
        panel_.add(enabledKeyPad);
        saveGameAtExit = _window.getCompoFactory().newCustCheckBox(messages.getVal(SAVE_EXIT));
        saveGameAtExit.setSelected(_loading.isSaveGameAtExit());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                saveGameAtExit = !saveGameAtExit;
//            }
//        });
        panel_.add(saveGameAtExit);
        selectHomePath = _window.getCompoFactory().newCustCheckBox(messages.getVal(SELECT_HOME_PATH));
        selectHomePath.setSelected(_loading.isSaveHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePath = !selectHomePath;
//            }
//        });
        panel_.add(selectHomePath);
        selectHomePathZip = _window.getCompoFactory().newCustCheckBox(messages.getVal(SELECT_HOME_PATH_ZIP));
        selectHomePathZip.setSelected(_loading.isLoadHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePathZip = !selectHomePathZip;
//            }
//        });
        panel_.add(selectHomePathZip);
        AbsPlainButton ok_ = _window.getCompoFactory().newPlainButton(WindowAiki.OK);
        ok_.addActionListener(new ValidateSoftParams(this));
        panel_.add(ok_);
        absDialog.setContentPane(panel_);
        absDialog.pack();
        absDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void validateChoices() {
        ok = true;
        absDialog.closeWindow();
    }

    public static boolean isOk(DialogSoftParams _dialog) {
        return _dialog.ok;
    }

    public static void setParams(LoadingGame _loading, DialogSoftParams _dialog) {
        _dialog.validateParams(_loading);
    }

    private void validateParams(LoadingGame _loading) {
        absDialog.setVisible(true);
        if (!ok) {
            return;
        }
        _loading.setLoadLastRom(loadLastRom.isSelected());
        _loading.setLoadLastGame(loadLastGame.isSelected());
        _loading.setSaveGameAtExit(saveGameAtExit.isSelected());
        _loading.setEnableAnimation(enableAnimation.isSelected());
        _loading.setEnableMovingHerosAnimation(enableMovingHerosAnimation.isSelected());
        _loading.setClickButtonsPad(clickButtonsPad.isSelected());
        _loading.setEnabledKeyPad(enabledKeyPad.isSelected());
        _loading.setSaveHomeFolder(selectHomePath.isSelected());
        _loading.setLoadHomeFolder(selectHomePathZip.isSelected());
    }
}
