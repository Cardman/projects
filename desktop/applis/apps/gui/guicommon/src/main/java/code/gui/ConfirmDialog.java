package code.gui;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import code.gui.events.AnswerEvent;
import code.gui.events.AnswerTextEvent;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;

public final class ConfirmDialog extends Dialog {
    private static final String DIALOG_ACCESS = "gui.confirmdialog";

    private static final String OK = "ok";
    private static final String CANCEL = "cancel";
    private static final String NO = "no";
    private static final String YES = "yes";
    private static final String ERROR_ICON = "OptionPane.errorIcon";
    private static final String WARNING_ICON = "OptionPane.warningIcon";
    private static final String INFORMATION_ICON = "OptionPane.informationIcon";
    private static final String QUESTION_ICON = "OptionPane.questionIcon";

    private static final String EMPTY_STRING = "";

    private int answer;

    private TextField field;

    private String typedText;
    private final AbstractProgramInfos list;

    public ConfirmDialog(AbstractProgramInfos _list) {
        list = _list;
    }


    public static void showMessage(Dialog _frame, String _message, String _title, String _language, int _option, ConfirmDialog _dialog) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        _dialog.setDialogIcon(_dialog.list.getImageFactory(), _frame);
        _dialog.setModal(true);
        _dialog.setLocationRelativeTo(_frame);
        _dialog.initMessageSingleButton(_message, _title, _language, _option);
    }

    public static ConfirmDialog showMiniDialog(Dialog _frame, String _message, String _title, String _language, int _option, ConfirmDialog _dialog) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        _dialog.setDialogIcon(_dialog.list.getImageFactory(), _frame);
        _dialog.setModal(true);
        _dialog.setLocationRelativeTo(_frame);
        _dialog.init(_message, _title, _language, _option);
        return _dialog;
    }

    public static void showTextField(GroupFrame _frame, String _value, String _message, String _title, String _language) {
        _frame.getConfirmDialog().setDialogIcon(_frame.getConfirmDialog().list.getImageFactory(), _frame);
        _frame.getConfirmDialog().setModal(true);
        _frame.getConfirmDialog().setLocationRelativeTo(_frame);
        _frame.getConfirmDialog().init(_message, _value, _title, _language);
    }

    public static void showComponent(GroupFrame _frame, CustComponent _message, String _title, String _language, int _option) {
//      ConfirmDialog conf_;
//      conf_ = new ConfirmDialog(_frame);
        _frame.getConfirmDialog().setDialogIcon(_frame.getConfirmDialog().list.getImageFactory(), _frame);
        _frame.getConfirmDialog().setModal(true);
        _frame.getConfirmDialog().setLocationRelativeTo(_frame);
        _frame.getConfirmDialog().initComponentSingleButton(_message, _title, _language, _option);
  }

    public static void showMessage(GroupFrame _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        _frame.getConfirmDialog().setDialogIcon(_frame.getConfirmDialog().list.getImageFactory(),_frame);
        _frame.getConfirmDialog().setModal(true);
        _frame.getConfirmDialog().setLocationRelativeTo(_frame);
        _frame.getConfirmDialog().initMessageSingleButton(_message, _title, _language, _option);
    }

    public static int getAnswer(GroupFrame _frame, String _message, String _title, String _language, int _option) {
        return showMiniDialog(_frame, _message, _title, _language, _option).getAnswer();
    }

    public static ConfirmDialog showMiniDialog(GroupFrame _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        _frame.getConfirmDialog().setDialogIcon(_frame.getConfirmDialog().list.getImageFactory(), _frame);
        _frame.getConfirmDialog().setModal(true);
        _frame.getConfirmDialog().setLocationRelativeTo(_frame);
        _frame.getConfirmDialog().init(_message, _title, _language, _option);
        return _frame.getConfirmDialog();
    }

    private void initMessageSingleButton(String _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = confirm(_language);
        setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(list.getImageFactory(), _message));
        Panel buttons_ = Panel.newLineBox();
        if (_option == JOptionPane.INFORMATION_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(INFORMATION_ICON)));
        } else if (_option == JOptionPane.ERROR_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(ERROR_ICON)));
        } else if (_option == JOptionPane.WARNING_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(WARNING_ICON)));
        }
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseList(new ClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private static StringMap<String> confirm(String _language) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    private void initComponentSingleButton(CustComponent _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = confirm(_language);
        setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(_message);
        Panel buttons_ = Panel.newLineBox();
        if (_option == JOptionPane.INFORMATION_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(INFORMATION_ICON)));
        } else if (_option == JOptionPane.ERROR_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(ERROR_ICON)));
        } else if (_option == JOptionPane.WARNING_MESSAGE) {
            buttons_.add(new PreparedLabel(UIManager.getIcon(WARNING_ICON)));
        }
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseList(new ClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private void init(String _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = confirm(_language);
        setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(list.getImageFactory(), _message));
        Panel buttons_ = Panel.newLineBox();
        if (_option == JOptionPane.YES_NO_OPTION) {
            answer = JOptionPane.NO_OPTION;
            buttons_.add(new PreparedLabel(UIManager.getIcon(QUESTION_ICON)));
            LabelButton button_ = new LabelButton(messages_.getVal(YES));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.YES_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(NO));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.NO_OPTION));
            buttons_.add(button_);
        } else if (_option == JOptionPane.YES_NO_CANCEL_OPTION) {
            answer = JOptionPane.CANCEL_OPTION;
            buttons_.add(new PreparedLabel(UIManager.getIcon(QUESTION_ICON)));
            LabelButton button_ = new LabelButton(messages_.getVal(YES));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.YES_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(NO));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.NO_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(CANCEL));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.CANCEL_OPTION));
            buttons_.add(button_);
        } else {
            LabelButton button_ = new LabelButton(messages_.getVal(OK));
            button_.addMouseList(new AnswerEvent(this, JOptionPane.OK_OPTION));
            buttons_.add(button_);
        }
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void init(String _message, String _value, String _title, String _language) {
        StringMap<String> messages_ = confirm(_language);
        setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new PreparedLabel(UIManager.getIcon(QUESTION_ICON)));
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(list.getImageFactory(), _message));
        field = new TextField();
        field.setText(_value);
        content_.add(field);
        answer = JOptionPane.NO_OPTION;
        Panel buttons_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseList(new AnswerTextEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(messages_.getVal(CANCEL));
        button_.addMouseList(new AnswerTextEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void closeWindowText(int _answer) {
        answer = _answer;
        if (field != null) {
            typedText = field.getText();
        } else {
            typedText = EMPTY_STRING;
        }
        if (answer == JOptionPane.NO_OPTION) {
            typedText = EMPTY_STRING;
        }
        closeWindow();
    }

    public void closeWindow(int _answer) {
        answer = _answer;
        closeWindow();
    }

    public static String getStaticText(ConfirmDialog _dialog) {
        return _dialog.getTypedText();
    }

    public static int getStaticAnswer(ConfirmDialog _dialog) {
        return _dialog.getAnswer();
    }

    public int getAnswer() {
        return answer;
    }

    public String getTypedText() {
        return typedText;
    }
}
