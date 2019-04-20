package code.gui;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import code.gui.events.AnswerEvent;
import code.gui.events.AnswerTextEvent;
import code.gui.events.ClosingDialogEvent;
import code.sml.util.ExtractFromFiles;
import code.util.StringMap;

public final class ConfirmDialog extends Dialog {
    private static final String DIALOG_ACCESS = "gui.confirmdialog";

    private static final ConfirmDialog DIALOG = new ConfirmDialog();
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

    private JTextField field;

    private String typedText;

    private ConfirmDialog() {
    }


    public static void showMessage(Dialog _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.initMessageSingleButton(_message, _title, _language, _option);
    }

    public static void showComponent(Dialog _frame, JComponent _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.initComponentSingleButton(_message, _title, _language, _option);
    }

    public static void showComponent(Dialog _frame, CustComponent _message, String _title, String _language, int _option) {
//      ConfirmDialog conf_;
//      conf_ = new ConfirmDialog(_frame);
      DIALOG.setDialogIcon(_frame);
      DIALOG.setModal(true);
      DIALOG.setLocationRelativeTo(_frame);
      DIALOG.initComponentSingleButton(_message, _title, _language, _option);
  }

    public static int getAnswer(Dialog _frame, String _message, String _title, String _language, int _option) {
        return showMiniDialog(_frame, _message, _title, _language, _option).getAnswer();
    }

    public static ConfirmDialog showMiniDialog(Dialog _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.init(_message, _title, _language, _option);
        return DIALOG;
    }

    public static void showTextField(GroupFrame _frame, String _value, String _message, String _title, String _language) {
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.init(_message, _value, _title, _language);
    }

    public static void showComponent(GroupFrame _frame, JComponent _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.initComponentSingleButton(_message, _title, _language, _option);
    }
    public static void showComponent(GroupFrame _frame, CustComponent _message, String _title, String _language, int _option) {
//      ConfirmDialog conf_;
//      conf_ = new ConfirmDialog(_frame);
      DIALOG.setDialogIcon(_frame);
      DIALOG.setModal(true);
      DIALOG.setLocationRelativeTo(_frame);
      DIALOG.initComponentSingleButton(_message, _title, _language, _option);
  }

    public static void showMessage(GroupFrame _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.initMessageSingleButton(_message, _title, _language, _option);
    }

    public static int getAnswer(GroupFrame _frame, String _message, String _title, String _language, int _option) {
        return showMiniDialog(_frame, _message, _title, _language, _option).getAnswer();
    }

    public static ConfirmDialog showMiniDialog(GroupFrame _frame, String _message, String _title, String _language, int _option) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        DIALOG.setDialogIcon(_frame);
        DIALOG.setModal(true);
        DIALOG.setLocationRelativeTo(_frame);
        DIALOG.init(_message, _title, _language, _option);
        return DIALOG;
    }

    private void initMessageSingleButton(String _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        setTitle(_title);
        Panel content_ = new Panel();
        content_.setLayout(new GridLayout(0,1));
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = new Panel();
        if (_option == JOptionPane.INFORMATION_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(INFORMATION_ICON)));
        } else if (_option == JOptionPane.ERROR_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(ERROR_ICON)));
        } else if (_option == JOptionPane.WARNING_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(WARNING_ICON)));
        }
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initComponentSingleButton(JComponent _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        setTitle(_title);
        Panel content_ = new Panel();
        content_.setLayout(new GridLayout(0,1));
        content_.add(_message);
        Panel buttons_ = new Panel();
        if (_option == JOptionPane.INFORMATION_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(INFORMATION_ICON)));
        } else if (_option == JOptionPane.ERROR_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(ERROR_ICON)));
        } else if (_option == JOptionPane.WARNING_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(WARNING_ICON)));
        }
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private void initComponentSingleButton(CustComponent _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        setTitle(_title);
        Panel content_ = new Panel();
        content_.setLayout(new GridLayout(0,1));
        content_.add(_message);
        Panel buttons_ = new Panel();
        if (_option == JOptionPane.INFORMATION_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(INFORMATION_ICON)));
        } else if (_option == JOptionPane.ERROR_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(ERROR_ICON)));
        } else if (_option == JOptionPane.WARNING_MESSAGE) {
            buttons_.add(new JLabel(UIManager.getIcon(WARNING_ICON)));
        }
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }
    private void init(String _message, String _title, String _language, int _option) {
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        setTitle(_title);
        Panel content_ = new Panel();
        content_.setLayout(new GridLayout(0,1));
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = new Panel();
        if (_option == JOptionPane.YES_NO_OPTION) {
            answer = JOptionPane.NO_OPTION;
            buttons_.add(new JLabel(UIManager.getIcon(QUESTION_ICON)));
            LabelButton button_ = new LabelButton(messages_.getVal(YES));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.YES_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(NO));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.NO_OPTION));
            buttons_.add(button_);
        } else if (_option == JOptionPane.YES_NO_CANCEL_OPTION) {
            answer = JOptionPane.CANCEL_OPTION;
            buttons_.add(new JLabel(UIManager.getIcon(QUESTION_ICON)));
            LabelButton button_ = new LabelButton(messages_.getVal(YES));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.YES_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(NO));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.NO_OPTION));
            buttons_.add(button_);
            button_ = new LabelButton(messages_.getVal(CANCEL));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.CANCEL_OPTION));
            buttons_.add(button_);
        } else {
            LabelButton button_ = new LabelButton(messages_.getVal(OK));
            button_.addMouseListener(new AnswerEvent(this, JOptionPane.OK_OPTION));
            buttons_.add(button_);
        }
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void init(String _message, String _value, String _title, String _language) {
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        setTitle(_title);
        Panel content_ = new Panel();
        content_.setLayout(new GridLayout(0,1));
        content_.add(new JLabel(UIManager.getIcon(QUESTION_ICON)));
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(_message));
        field = new JTextField();
        field.setText(_value);
        content_.add(field);
        answer = JOptionPane.NO_OPTION;
        Panel buttons_ = new Panel();
        LabelButton button_ = new LabelButton(messages_.getVal(OK));
        button_.addMouseListener(new AnswerTextEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(messages_.getVal(CANCEL));
        button_.addMouseListener(new AnswerTextEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        setContentPane(content_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void closeWindowText(int _answer) {
        answer = _answer;
        typedText = field.getText();
        if (answer == JOptionPane.NO_OPTION) {
            typedText = EMPTY_STRING;
        }
        closeWindow();
    }

    public void closeWindow(int _answer) {
        answer = _answer;
        closeWindow();
    }

    public static String getStaticText() {
        return DIALOG.getTypedText();
    }

    public static int getStaticAnswer() {
        return DIALOG.getAnswer();
    }

    public int getAnswer() {
        return answer;
    }

    public String getTypedText() {
        return typedText;
    }
}
