package aiki.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.game.params.LoadingGame;
import aiki.gui.MainWindow;
import aiki.gui.dialogs.events.ValidateSoftParams;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.util.StringMap;

public final class SoftParams extends Dialog {
    private static final String DIALOG_ACCESS = "dbpokemon.gui.dialogs.SoftParams";

    private static final SoftParams DIALOG = new SoftParams();
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

    private StringMap<String> messages;

    private JCheckBox loadLastRom;

    private JCheckBox loadLastGame;

    private JCheckBox saveGameAtExit;

    private JCheckBox enableAnimation;

    private JCheckBox enableMovingHerosAnimation;

    private JCheckBox clickButtonsPad;

    private JCheckBox enabledKeyPad;

    private JCheckBox selectHomePath;

    private JCheckBox selectHomePathZip;

    private boolean ok;

    private SoftParams() {
    	setAccessFile(DIALOG_ACCESS);
    }

    public static void setSoftParams(MainWindow _window, LoadingGame _loading) {
        DIALOG.init(_window, _loading);
    }

    private void init(MainWindow _window, LoadingGame _loading) {
        setDialogIcon(_window);
        messages = getMessages(Resources.MESSAGES_FOLDER);
        ok = false;
        setTitle(messages.getVal(TITLE));
        setLocationRelativeTo(_window);
        JPanel panel_ = new JPanel();
        panel_.setLayout(new GridLayout(0,1));
//        loadLastRom = _loading.isLoadLastRom();
//        loadLastGame = _loading.isLoadLastGame();
//        saveGameAtExit = _loading.isSaveGameAtExit();
//        enableAnimation = _loading.isEnableAnimation();
//        clickButtonsPad = _loading.isClickButtonsPad();
//        enabledKeyPad = _loading.isEnabledKeyPad();
//        selectHomePath = _loading.isSaveHomeFolder();
//        selectHomePathZip = _loading.isLoadHomeFolder();
//        JCheckBox check_;
        loadLastRom = new JCheckBox(messages.getVal(ZIP_LOAD));
        loadLastRom.setSelected(_loading.isLoadLastRom());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastRom = !loadLastRom;
//            }
//        });
        panel_.add(loadLastRom);
        loadLastGame = new JCheckBox(messages.getVal(GAME_LOAD));
        loadLastGame.setSelected(_loading.isLoadLastGame());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                loadLastGame = !loadLastGame;
//            }
//        });
        panel_.add(loadLastGame);
        enableAnimation = new JCheckBox(messages.getVal(ANIMATION));
        enableAnimation.setSelected(_loading.isEnableAnimation());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enableAnimation = !enableAnimation;
//            }
//        });
        panel_.add(enableAnimation);
        panel_.add(loadLastGame);
        enableMovingHerosAnimation = new JCheckBox(messages.getVal(ANIMATION_MOVING));
        enableMovingHerosAnimation.setSelected(_loading.isEnableMovingHerosAnimation());
        panel_.add(enableMovingHerosAnimation);
        clickButtonsPad = new JCheckBox(messages.getVal(CLICK_PAD));
        clickButtonsPad.setSelected(_loading.isClickButtonsPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                clickButtonsPad = !clickButtonsPad;
//            }
//        });
        panel_.add(clickButtonsPad);
        enabledKeyPad = new JCheckBox(messages.getVal(ENABLE_KEY_PAD));
        enabledKeyPad.setSelected(_loading.isEnabledKeyPad());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                enabledKeyPad = !enabledKeyPad;
//            }
//        });
        panel_.add(enabledKeyPad);
        saveGameAtExit = new JCheckBox(messages.getVal(SAVE_EXIT));
        saveGameAtExit.setSelected(_loading.isSaveGameAtExit());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                saveGameAtExit = !saveGameAtExit;
//            }
//        });
        panel_.add(saveGameAtExit);
        selectHomePath = new JCheckBox(messages.getVal(SELECT_HOME_PATH));
        selectHomePath.setSelected(_loading.isSaveHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePath = !selectHomePath;
//            }
//        });
        panel_.add(selectHomePath);
        selectHomePathZip = new JCheckBox(messages.getVal(SELECT_HOME_PATH_ZIP));
        selectHomePathZip.setSelected(_loading.isLoadHomeFolder());
//        check_.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent _e) {
//                selectHomePathZip = !selectHomePathZip;
//            }
//        });
        panel_.add(selectHomePathZip);
        LabelButton ok_ = new LabelButton(MainWindow.OK);
        ok_.addMouseListener(new ValidateSoftParams(this));
        panel_.add(ok_);
        setContentPane(panel_);
        pack();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void validateChoices() {
        ok = true;
        closeWindow();
    }

    public static boolean isOk() {
        return DIALOG.ok;
    }

    public static void setParams(LoadingGame _loading) {
        DIALOG.validateParams(_loading);
    }

    private void validateParams(LoadingGame _loading) {
        setVisible(true);
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
