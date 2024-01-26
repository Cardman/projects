package code.gui.files;



import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class ConfirmDialog {
    public static final String CONFIRM = "confirm";

    private static final String ERROR_ICON = "OptionPane.errorIcon";
    private static final String WARNING_ICON = "OptionPane.warningIcon";
    private static final String INFORMATION_ICON = "OptionPane.informationIcon";
    private static final String QUESTION_ICON = "OptionPane.questionIcon";

    private static final String EMPTY_STRING = "";
    private final AbsDialog absDialog;

    private int answer;

    private AbsTextField field;

    private String typedText;
    private final AbstractProgramInfos list;

    public ConfirmDialog(AbstractProgramInfos _list) {
        list = _list;
        absDialog = _list.getFrameFactory().newDialog();
    }

    public static void showMessage(AbsDialog _frame, String _message, String _title, int _option, ConfirmDialog _dialog) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        _dialog.absDialog.setDialogIcon(_dialog.list.getImageFactory(), _frame);
        _dialog.absDialog.setModal(true);
        _dialog.absDialog.setLocationRelativeTo(_frame);
        _dialog.initComponentSingleButton(build(_dialog, _message), _title, _option);
    }

    public static ConfirmDialog showMiniDialog(AbsDialog _frame, String _message, String _title, int _option, ConfirmDialog _dialog) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        _dialog.absDialog.setDialogIcon(_dialog.list.getImageFactory(), _frame);
        _dialog.absDialog.setModal(true);
        _dialog.absDialog.setLocationRelativeTo(_frame);
        _dialog.init(_message, _title, _option);
        return _dialog;
    }

    public static void showTextField(String _value, String _message, String _title, ConfirmDialog _conf, AbsCommonFrame _fr) {
        _conf.absDialog.setDialogIcon(_conf.list.getImageFactory(), _fr);
        _conf.absDialog.setModal(true);
        _conf.absDialog.setLocationRelativeTo(_fr);
        _conf.init(_message, _value, _title);
    }

    public static void showComponent(AbsCustComponent _message, String _title, int _option, ConfirmDialog _conf, AbsCommonFrame _fr) {
//      ConfirmDialog conf_;
//      conf_ = new ConfirmDialog(_frame);
        _conf.absDialog.setDialogIcon(_conf.list.getImageFactory(), _fr);
        _conf.absDialog.setModal(true);
        _conf.absDialog.setLocationRelativeTo(_fr);
        _conf.initComponentSingleButton(_message, _title, _option);
  }

    public static void showMessage(String _message, String _title, int _option, ConfirmDialog _conf, AbsCommonFrame _fr) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame);
        showComponent(build(_conf, _message), _title, _option, _conf, _fr);
    }

    public static AbsCustComponent build(ConfirmDialog _frame, String _message) {
        return new WrappedLabel(_frame.list.getImageFactory(), _message, _frame.list.getCompoFactory()).getPaintableLabel();
    }

    public static int getAnswer(String _message, String _title, int _option, ConfirmDialog _conf, AbsCommonFrame _fr) {
        return showMiniDialog(_message, _title, _option, _conf, _fr).getAnswer();
    }

    public static ConfirmDialog showMiniDialog(String _message, String _title, int _option, ConfirmDialog _conf, AbsCommonFrame _fr) {
//        ConfirmDialog conf_;
//        conf_ = new ConfirmDialog(_frame, _message, _title, _language, _option);
        _conf.absDialog.setDialogIcon(_conf.list.getImageFactory(), _fr);
        _conf.absDialog.setModal(true);
        _conf.absDialog.setLocationRelativeTo(_fr);
        _conf.init(_message, _title, _option);
        return _conf;
    }

    private void buttons(int _option, StringMap<String> _messages, AbsPanel _content) {
        AbsPanel buttons_ = this.list.getCompoFactory().newLineBox();
        if (_option == GuiConstants.INFORMATION_MESSAGE) {
            buttons_.add(this.list.getCompoFactory().newPreparedLabel(ConfirmDialog.INFORMATION_ICON));
        } else if (_option == GuiConstants.ERROR_MESSAGE) {
            buttons_.add(this.list.getCompoFactory().newPreparedLabel(ConfirmDialog.ERROR_ICON));
        } else {
            buttons_.add(this.list.getCompoFactory().newPreparedLabel(ConfirmDialog.WARNING_ICON));
        }
        AbsButton button_ = this.list.getCompoFactory().newPlainButton(_messages.getVal(MessagesConfirmDialog.OK));
        button_.addActionListener(new ClosingDialogEvent(this.absDialog));
        buttons_.add(button_);
        _content.add(buttons_);
        this.absDialog.setContentPane(_content);
//        this.absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        this.absDialog.pack();
        this.absDialog.setVisible(true);
    }

    private StringMap<String> confirm() {
        return FileDialog.getAppliTr(list.currentLg()).getMapping().getVal(CONFIRM).getMapping();
    }

    private void initComponentSingleButton(AbsCustComponent _abs, String _title, int _option) {
        StringMap<String> messages_ = confirm();
        absDialog.setTitle(_title);
        AbsPanel content_ = list.getCompoFactory().newGrid(0,1);
        content_.add(_abs);
        buttons(_option, messages_, content_);
    }
    private void init(String _message, String _title, int _option) {
        StringMap<String> messages_ = confirm();
        absDialog.setTitle(_title);
        AbsPanel content_ = list.getCompoFactory().newGrid(0,1);
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(list.getImageFactory(), _message, list.getCompoFactory()).getPaintableLabel());
        AbsPanel buttons_ = list.getCompoFactory().newLineBox();
        if (_option == GuiConstants.YES_NO_OPTION) {
            answer = GuiConstants.NO_OPTION;
            buttons_.add(list.getCompoFactory().newPreparedLabel(QUESTION_ICON));
            AbsButton button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.YES));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.YES_OPTION));
            buttons_.add(button_);
            button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.NO_OPTION));
            buttons_.add(button_);
        } else if (_option == GuiConstants.YES_NO_CANCEL_OPTION) {
            answer = GuiConstants.CANCEL_OPTION;
            buttons_.add(list.getCompoFactory().newPreparedLabel(QUESTION_ICON));
            AbsButton button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.YES));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.YES_OPTION));
            buttons_.add(button_);
            button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.NO_OPTION));
            buttons_.add(button_);
            button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.CANCEL));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.CANCEL_OPTION));
            buttons_.add(button_);
        } else {
            AbsButton button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.OK));
            button_.addActionListener(new AnswerEvent(this, GuiConstants.OK_OPTION));
            buttons_.add(button_);
        }
        content_.add(buttons_);
        absDialog.setContentPane(content_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    private void init(String _message, String _value, String _title) {
        StringMap<String> messages_ = confirm();
        absDialog.setTitle(_title);
        AbsPanel content_ = list.getCompoFactory().newGrid(0,1);
        content_.add(list.getCompoFactory().newPreparedLabel(QUESTION_ICON));
//        JLabel message_ = new JLabel(_message);
//        Font font_ = message_.getFont();
//        FontMetrics fontMet_ = message_.getFontMetrics(font_);
//        int w_ = fontMet_.stringWidth(_message);
//        int h_ = fontMet_.getHeight();
//        message_.setPreferredSize(new Dimension(w_,h_));
//        content_.add(message_);
        content_.add(new WrappedLabel(list.getImageFactory(), _message, list.getCompoFactory()).getPaintableLabel());
        field = list.getCompoFactory().newTextField();
        field.setText(_value);
        content_.add(field);
        answer = GuiConstants.NO_OPTION;
        AbsPanel buttons_ = list.getCompoFactory().newLineBox();
        AbsButton button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.OK));
        button_.addActionListener(new AnswerTextEvent(this, GuiConstants.YES_OPTION));
        buttons_.add(button_);
        button_ = list.getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.CANCEL));
        button_.addActionListener(new AnswerTextEvent(this, GuiConstants.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        absDialog.setContentPane(content_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public void closeWindowText(int _answer) {
        answer = _answer;
        typedText = field.getText();
        if (answer == GuiConstants.NO_OPTION) {
            typedText = EMPTY_STRING;
        }
        absDialog.closeWindow();
    }

    public void closeWindow(int _answer) {
        answer = _answer;
        absDialog.closeWindow();
    }

    public TextAnswerValue textValue() {
        return new TextAnswerValue(answer,typedText);
    }
    public int getAnswer() {
        return answer;
    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }
}
