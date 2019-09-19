package code.gui;

import code.gui.events.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class OtherConfirmDialog {

    private static final String EMPTY_STRING = "";
    private JDialog dialog = new JDialog();

    private int answer;

    private TextField field;

    private String typedText;

    public OtherConfirmDialog() {
        dialog.addWindowListener(new OtherCrossClosingDialogEvent(this));
    }

    public String showTextField(BufferedImage _img,GroupFrame _frame, String _value, String _message, String _title, String _ok, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initField(_img,_message, _value, _title, _ok,_cancel);
        return getTypedText();
    }

    public void showComponent(GroupFrame _frame, CustComponent _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initComponentSingleButton(_message, _title, _language);
    }

    public void showComponent(BufferedImage _img, GroupFrame _frame, CustComponent _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initComponentSingleButton(_img,_message, _title, _language);
    }

    public void showMessage(GroupFrame _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initMessageSingleButton(_message, _title, _language);
    }

    public void showMessage(BufferedImage _img, GroupFrame _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initMessageSingleButton(_img, _message,_title, _language);
    }

    public int getAnswerOk(GroupFrame _frame, String _message, String _title, String _language) {
        return showMiniDialogOk(_frame, _message, _title, _language).getAnswer();
    }

    private OtherConfirmDialog showMiniDialogOk(GroupFrame _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initOk(_message, _title, _language);
        return this;
    }

    public int getAnswerOk(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _language) {
        return showMiniDialogOk(_img,_frame, _message, _title, _language).getAnswer();
    }

    private OtherConfirmDialog showMiniDialogOk(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _language) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initOk(_img,_message, _title, _language);
        return this;
    }

    public int getAnswerYesNo(GroupFrame _frame, String _message, String _title, String _yes, String _no) {
        return showMiniDialogYesNo(_frame, _message, _title, _yes, _no).getAnswer();
    }

    private OtherConfirmDialog showMiniDialogYesNo(GroupFrame _frame, String _message, String _title, String _yes, String _no) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initYesNo(_message, _title, _yes, _no);
        return this;
    }

    public int getAnswerYesNo(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _yes, String _no) {
        return showMiniDialogYesNo(_img,_frame, _message, _title, _yes, _no).getAnswer();
    }

    private OtherConfirmDialog showMiniDialogYesNo(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _yes, String _no) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        initYesNo(_img,_message, _title, _yes, _no);
        return this;
    }

    public int getAnswer(GroupFrame _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        return showMiniDialog(_frame, _message, _title, _yes, _no,_cancel).getAnswer();
    }

    private OtherConfirmDialog showMiniDialog(GroupFrame _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        init(_message, _title, _yes, _no,_cancel);
        return this;
    }

    public int getAnswer(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        return showMiniDialog(_img,_frame, _message, _title, _yes, _no,_cancel).getAnswer();
    }

    private OtherConfirmDialog showMiniDialog(BufferedImage _img,GroupFrame _frame, String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setModal(true);
        setLocationRelativeTo(_frame);
        init(_img,_message, _title, _yes, _no,_cancel);
        return this;
    }

    private void setLocationRelativeTo(GroupFrame _frame) {
        if (_frame == null) {
            dialog.setLocationRelativeTo(null);
            return;
        }
        dialog.setLocationRelativeTo(_frame.getComponent());
    }

    private void initMessageSingleButton(String _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initMessageSingleButton(BufferedImage _img,String _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        buttons_.add(new PreparedLabel(_img));
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initComponentSingleButton(CustComponent _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(_message);
        Panel buttons_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initComponentSingleButton(BufferedImage _img, CustComponent _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(_message);
        Panel buttons_ = Panel.newLineBox();
        buttons_.add(new PreparedLabel(_img));
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherClosingDialogEvent(this));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initOk(String _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.OK_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initOk(BufferedImage _img,String _message, String _title, String _language) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        buttons_.add(new PreparedLabel(_img));
        LabelButton button_ = new LabelButton(_language);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.OK_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initYesNo(String _message, String _title, String _yes, String _no) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        answer = JOptionPane.NO_OPTION;
        LabelButton button_ = new LabelButton(_yes);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_no);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void initYesNo(BufferedImage _img,String _message, String _title, String _yes, String _no) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        answer = JOptionPane.NO_OPTION;
        buttons_.add(new PreparedLabel(_img));
        LabelButton button_ = new LabelButton(_yes);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_no);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void init(String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        answer = JOptionPane.CANCEL_OPTION;
        LabelButton button_ = new LabelButton(_yes);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_no);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_cancel);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.CANCEL_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }
    private void init(BufferedImage _img,String _message, String _title, String _yes, String _no, String _cancel) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new WrappedLabel(_message));
        Panel buttons_ = Panel.newLineBox();
        answer = JOptionPane.CANCEL_OPTION;
        buttons_.add(new PreparedLabel(_img));
        LabelButton button_ = new LabelButton(_yes);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_no);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_cancel);
        button_.addMouseListener(new OtherAnswerEvent(this, JOptionPane.CANCEL_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initField(BufferedImage _img, String _message, String _value, String _title, String _ok, String _cancel) {
        dialog.setTitle(_title);
        Panel content_ = Panel.newGrid(0,1);
        content_.add(new PreparedLabel(_img));
        content_.add(new WrappedLabel(_message));
        field = new TextField();
        field.setText(_value);
        content_.add(field);
        answer = JOptionPane.NO_OPTION;
        Panel buttons_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(_ok);
        button_.addMouseListener(new OtherAnswerTextEvent(this, JOptionPane.YES_OPTION));
        buttons_.add(button_);
        button_ = new LabelButton(_cancel);
        button_.addMouseListener(new OtherAnswerTextEvent(this, JOptionPane.NO_OPTION));
        buttons_.add(button_);
        content_.add(buttons_);
        dialog.setContentPane(content_.getComponent());
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
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

    public void closeWindow() {
        dialog.setVisible(false);
        dialog.getContentPane().removeAll();
    }
    public int getAnswer() {
        return answer;
    }

    public String getTypedText() {
        return typedText;
    }

}
