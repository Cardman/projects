package code.gui;

import code.gui.events.OtherAnswerEvent;
import code.gui.events.OtherAnswerTextEvent;
import code.gui.events.OtherClosingDialogEvent;
import code.gui.events.OtherCrossClosingDialogEvent;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractLightProgramInfos;

public final class OtherConfirmDialog {

    public static final int OK_OPTION = GuiConstants.OK_OPTION;
    public static final int YES_OPTION = GuiConstants.YES_OPTION;
    public static final int NO_OPTION = GuiConstants.NO_OPTION;
    public static final int CANCEL_OPTION = GuiConstants.CANCEL_OPTION;
    private static final String EMPTY_STRING = "";
    private final AbsOtherDialog dialog;

    private int answer;

    private AbsTextField field;

    private String typedText;
    private final AbstractLightProgramInfos infos;

    public OtherConfirmDialog(AbstractLightProgramInfos _infos) {
        infos = _infos;
        dialog =_infos.getLightFrameFactory().newOtherDialog();
        dialog.addWindowListener(new OtherCrossClosingDialogEvent(this));
    }

    public String showTextField(WithListener _frame, String _value, String _message, String _title, String _ok, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initField(_message, _value, _title, _ok,_cancel);
        return getTypedText();
    }

    public String showTextField(AbstractImage _img,WithListener _frame, String _value, String _message, String _title, String _ok, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initField(_img,_message, _value, _title, _ok,_cancel);
        return getTypedText();
    }

    public void showMessage(WithListener _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initMessageSingleButton(_message, _title, _language);
    }

    public void showMessage(AbstractImage _img, WithListener _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initMessageSingleButton(_img, _message,_title, _language);
    }

    public int getAnswerOk(WithListener _frame, String _message, String _title, String _language) {
        return showMiniDialogOk(_frame, _message, _title, _language).getAnswer();
    }

    public OtherConfirmDialog showMiniDialogOk(WithListener _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initOk(_message, _title, _language);
        return this;
    }

    public int getAnswerOk(AbstractImage _img,WithListener _frame, String _message, String _title, String _language) {
        return showMiniDialogOk(_img,_frame, _message, _title, _language).getAnswer();
    }

    public OtherConfirmDialog showMiniDialogOk(AbstractImage _img,WithListener _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initOk(_img,_message, _title, _language);
        return this;
    }

    public int getAnswerYesNo(WithListener _frame, String _message, String _title, String _yes, String _no) {
        return showMiniDialogYesNo(_frame, _message, _title, _yes, _no).getAnswer();
    }

    public OtherConfirmDialog showMiniDialogYesNo(WithListener _frame, String _message, String _title, String _yes, String _no) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initYesNo(_message, _title, _yes, _no);
        return this;
    }

    public int getAnswerYesNo(AbstractImage _img,WithListener _frame, String _message, String _title, String _yes, String _no) {
        return showMiniDialogYesNo(_img,_frame, _message, _title, _yes, _no).getAnswer();
    }

    public OtherConfirmDialog showMiniDialogYesNo(AbstractImage _img,WithListener _frame, String _message, String _title, String _yes, String _no) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initYesNo(_img,_message, _title, _yes, _no);
        return this;
    }

    public int getAnswer(WithListener _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        return showMiniDialog(_frame, _message, _title, _yes, _no,_cancel).getAnswer();
    }

    public OtherConfirmDialog showMiniDialog(WithListener _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        init(_message, _title, _yes, _no,_cancel);
        return this;
    }

    public int getAnswer(AbstractImage _img, WithListener _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        return showMiniDialog(_img,_frame, _message, _title, _yes, _no,_cancel).getAnswer();
    }

    public OtherConfirmDialog showMiniDialog(AbstractImage _img,WithListener _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        init(_img,_message, _title, _yes, _no,_cancel);
        return this;
    }

    private void setLocationRelativeTo(WithListener _frame) {
        PlacableWindow pl_ = dialog;
        if (_frame == null) {
            pl_.setLocationRelativeToNull();
            return;
        }
        if (_frame instanceof AbsOtherDialog) {
            pl_.setLocationRelativeTo(((AbsOtherDialog)_frame));
        }
        if (_frame instanceof AbsOtherFrame) {
            pl_.setLocationRelativeTo(((AbsOtherFrame)_frame));
        }
    }

    private void initMessageSingleButton(String _message, String _title, String _language) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_language);
        button_.addActionListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initMessageSingleButton(AbstractImage _img,String _message, String _title, String _language) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        buttons_.add(infos.getCompoFactory().newPreparedLabel(_img));
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_language);
        button_.addActionListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initOk(String _message, String _title, String _language) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_language);
        button_.addActionListener(new OtherAnswerEvent(this, OK_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initOk(AbstractImage _img,String _message, String _title, String _language) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        buttons_.add(infos.getCompoFactory().newPreparedLabel(_img));
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_language);
        button_.addActionListener(new OtherAnswerEvent(this, OK_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initYesNo(String _message, String _title, String _yes, String _no) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        answer = NO_OPTION;
        yesNoCommon(_yes, _no, content_, buttons_);
    }

    private void initYesNo(AbstractImage _img,String _message, String _title, String _yes, String _no) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        answer = NO_OPTION;
        buttons_.add(infos.getCompoFactory().newPreparedLabel(_img));
        yesNoCommon(_yes, _no, content_, buttons_);
    }

    private void yesNoCommon(String _yes, String _no, AbsPanel _content, AbsPanel _buttons) {
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_yes);
        button_.addActionListener(new OtherAnswerEvent(this, YES_OPTION));
        _buttons.add(button_);
        button_ = infos.getCompoFactory().newPlainButton(_no);
        button_.addActionListener(new OtherAnswerEvent(this, NO_OPTION));
        _buttons.add(button_);
        _content.add(_buttons);
        dialog.setContentPane(_content);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void init(String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        answer = CANCEL_OPTION;
        initCommon(_yes, _no, _cancel, content_, buttons_);
    }

    private void init(AbstractImage _img,String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(wrapped(_message));
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        answer = CANCEL_OPTION;
        buttons_.add(infos.getCompoFactory().newPreparedLabel(_img));
        initCommon(_yes, _no, _cancel, content_, buttons_);
    }

    private void initCommon(String _yes, String _no, String _cancel, AbsPanel _content, AbsPanel _buttons) {
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_yes);
        button_.addActionListener(new OtherAnswerEvent(this, YES_OPTION));
        _buttons.add(button_);
        button_ = infos.getCompoFactory().newPlainButton(_no);
        button_.addActionListener(new OtherAnswerEvent(this, NO_OPTION));
        _buttons.add(button_);
        button_ = infos.getCompoFactory().newPlainButton(_cancel);
        button_.addActionListener(new OtherAnswerEvent(this, CANCEL_OPTION));
        _buttons.add(button_);
        _content.add(_buttons);
        dialog.setContentPane(_content);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initField(String _message, String _value, String _title, String _ok, String _cancel) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        initFieldCommon(_message, _value, _ok, _cancel, content_);
    }

    private void initField(AbstractImage _img, String _message, String _value, String _title, String _ok, String _cancel) {
        dialog.setTitle(_title);
        AbsPanel content_ = infos.getCompoFactory().newGrid(0,1);
        content_.add(infos.getCompoFactory().newPreparedLabel(_img));
        initFieldCommon(_message, _value, _ok, _cancel, content_);
    }

    private void initFieldCommon(String _message, String _value, String _ok, String _cancel, AbsPanel _content) {
        _content.add(wrapped(_message));
        field = infos.getCompoFactory().newTextField();
        field.setText(_value);
        _content.add(field);
        answer = NO_OPTION;
        AbsPanel buttons_ = infos.getCompoFactory().newLineBox();
        AbsButton button_ = infos.getCompoFactory().newPlainButton(_ok);
        button_.addActionListener(new OtherAnswerTextEvent(this, YES_OPTION));
        buttons_.add(button_);
        button_ = infos.getCompoFactory().newPlainButton(_cancel);
        button_.addActionListener(new OtherAnswerTextEvent(this, NO_OPTION));
        buttons_.add(button_);
        _content.add(buttons_);
        dialog.setContentPane(_content);
//        dialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void closeWindowText(int _answer) {
        answer = _answer;
        typedText = field.getText();
        if (answer == GuiConstants.NO_OPTION) {
            typedText = EMPTY_STRING;
        }
        closeWindow();
//        answer = _answer;
//        typedText = EMPTY_STRING;
//        if (field != null) {
//            typedText = field.getText();
//        }
//        if (answer == NO_OPTION) {
//            typedText = null;
//        } else if (typedText == null) {
//            typedText = EMPTY_STRING;
//        }
//        closeWindow();
    }

    public void closeWindow(int _answer) {
        answer = _answer;
        closeWindow();
    }

    public void closeWindow() {
        dialog.setVisible(false);
        dialog.getContentPane().removeAll();
    }

    public AbsOtherDialog getDialog() {
        return dialog;
    }

    public TextAnswerValue textValue() {
        return new TextAnswerValue(answer,typedText);
    }
    public int getAnswer() {
        return answer;
    }

    public String getTypedText() {
        return typedText;
    }

    private AbsPaintableLabel wrapped(String _message) {
        return new WrappedLabel(infos.getImageFactory(), _message, infos.getCompoFactory()).getPaintableLabel();
    }

}
