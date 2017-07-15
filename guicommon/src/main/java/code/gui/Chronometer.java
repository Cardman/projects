package code.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Chronometer implements ActionListener {

    private static final String EMPTY_STRING = "";

    private static final int DELTA = 200;

    private JLabel area;

    private SessionEditorPane session;

    private long sec;

    public Chronometer(JLabel _area, SessionEditorPane _session, long _sec) {
        area = _area;
        sec = _sec;
        session = _session;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        if (!session.isProcessing()) {
            area.setText(EMPTY_STRING);
            area.validate();
            sec = 0;
            return;
        }
        area.setText(Long.toString(sec));
        area.validate();
        sec += DELTA;
    }
}
