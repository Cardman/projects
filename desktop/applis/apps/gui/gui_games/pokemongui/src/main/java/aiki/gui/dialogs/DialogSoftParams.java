package aiki.gui.dialogs;



import aiki.main.PkNonModalEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPkSoftParams;
import aiki.sml.LoadingGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.events.ValidateSoftParams;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogSoftParams {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.softparams";

//    private static final String TITLE = "title";
//    private static final String ZIP_LOAD = "zipLoad";
//    private static final String GAME_LOAD = "gameLoad";
//    private static final String SAVE_EXIT = "saveExit";
//    private static final String ANIMATION = "animation";
//    private static final String ANIMATION_MOVING = "animationMoving";
//    private static final String CLICK_PAD = "clickPad";
//    private static final String ENABLE_KEY_PAD = "enableKeyPad";
//    private static final String SELECT_HOME_PATH = "selectHomePath";
//    private static final String SELECT_HOME_PATH_ZIP = "selectHomePathZip";
    private final AbsCommonFrame absDialog;

    private AbsCustCheckBox loadLastRom;

    private AbsCustCheckBox loadLastGame;

    private AbsCustCheckBox saveGameAtExit;

    private AbsCustCheckBox enableAnimation;

    private AbsCustCheckBox enableMovingHerosAnimation;

    private AbsCustCheckBox clickButtonsPad;

    private AbsCustCheckBox enabledKeyPad;

    private AbsCustCheckBox selectHomePath;

    private AbsCustCheckBox selectHomePathZip;
    private AbsButton validChoice;

//    private boolean ok;

    public DialogSoftParams(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newCommonFrame(_frameFactory,null);
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setSoftParams(WindowAiki _window, LoadingGame _loading) {
        _window.getSoftParams().init(_window, _loading);
    }

    private void init(WindowAiki _window, LoadingGame _loading) {
        absDialog.setIconImage(_window.getCommonFrame().getImageIconFrame());
        StringMap<String> messages_ = GamesPk.getSoftParamsContentTr(GamesPk.getAppliTr(_window.getFrames().currentLg())).getMapping();
//        StringMap<String> messages_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
//        ok = false;
        absDialog.setTitle(messages_.getVal(MessagesRenderPkSoftParams.TITLE));
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
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
        loadLastRom = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.ZIP_LOAD));
        loadLastRom.setSelected(_loading.isLoadLastRom());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastRom = !loadLastRom;
//            }
//        });
        panel_.add(loadLastRom);
        loadLastGame = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.GAME_LOAD));
        loadLastGame.setSelected(_loading.isLoadLastGame());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastGame = !loadLastGame;
//            }
//        });
        panel_.add(loadLastGame);
        enableAnimation = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.ANIMATION));
        enableAnimation.setSelected(_loading.isEnableAnimation());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enableAnimation = !enableAnimation;
//            }
//        });
        panel_.add(enableAnimation);
        panel_.add(loadLastGame);
        enableMovingHerosAnimation = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.ANIMATION_MOVING));
        enableMovingHerosAnimation.setSelected(_loading.isEnableMovingHerosAnimation());
        panel_.add(enableMovingHerosAnimation);
        clickButtonsPad = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.CLICK_PAD));
        clickButtonsPad.setSelected(_loading.isClickButtonsPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                clickButtonsPad = !clickButtonsPad;
//            }
//        });
        panel_.add(clickButtonsPad);
        enabledKeyPad = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.ENABLE_KEY_PAD));
        enabledKeyPad.setSelected(_loading.isEnabledKeyPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enabledKeyPad = !enabledKeyPad;
//            }
//        });
        panel_.add(enabledKeyPad);
        saveGameAtExit = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.SAVE_EXIT));
        saveGameAtExit.setSelected(_loading.isSaveGameAtExit());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                saveGameAtExit = !saveGameAtExit;
//            }
//        });
        panel_.add(saveGameAtExit);
        selectHomePath = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.SELECT_HOME_PATH));
        selectHomePath.setSelected(_loading.isSaveHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePath = !selectHomePath;
//            }
//        });
        panel_.add(selectHomePath);
        selectHomePathZip = _window.getCompoFactory().newCustCheckBox(messages_.getVal(MessagesRenderPkSoftParams.SELECT_HOME_PATH_ZIP));
        selectHomePathZip.setSelected(_loading.isLoadHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePathZip = !selectHomePathZip;
//            }
//        });
        panel_.add(selectHomePathZip);
        validChoice = _window.getCompoFactory().newPlainButton(WindowAiki.OK);
        validChoice.addActionListener(new PkNonModalEvent(_window.getModal()),new ValidateSoftParams(this,_window));
        panel_.add(validChoice);
        absDialog.setContentPane(panel_);
        absDialog.pack();
        absDialog.setVisible(true);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    public void validateChoices(WindowAiki _frame) {
//        ok = true;
        absDialog.setVisible(false);
        _frame.afterClickParam();
    }

//    public static boolean isOk(DialogSoftParams _dialog) {
//        return _dialog.ok;
//    }

    public static void setParams(LoadingGame _loading, DialogSoftParams _dialog) {
        _dialog.validateParams(_loading);
    }

    private void validateParams(LoadingGame _loading) {
//        if (!ok) {
//            return;
//        }
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

    public AbsCustCheckBox getLoadLastRom() {
        return loadLastRom;
    }

    public AbsCustCheckBox getLoadLastGame() {
        return loadLastGame;
    }

    public AbsCustCheckBox getSaveGameAtExit() {
        return saveGameAtExit;
    }

    public AbsCustCheckBox getEnableAnimation() {
        return enableAnimation;
    }

    public AbsCustCheckBox getEnableMovingHerosAnimation() {
        return enableMovingHerosAnimation;
    }

    public AbsCustCheckBox getClickButtonsPad() {
        return clickButtonsPad;
    }

    public AbsCustCheckBox getEnabledKeyPad() {
        return enabledKeyPad;
    }

    public AbsCustCheckBox getSelectHomePath() {
        return selectHomePath;
    }

    public AbsCustCheckBox getSelectHomePathZip() {
        return selectHomePathZip;
    }

    public AbsButton getValidChoice() {
        return validChoice;
    }
}
