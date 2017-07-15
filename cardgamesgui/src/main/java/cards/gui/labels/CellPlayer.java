package cards.gui.labels;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import cards.consts.Status;

public class CellPlayer extends JLabel {

    private Status st;

    private String textPlayer = "";

    public void setStatus(Status _st) {
        st = _st;
    }

    public void setTextPlayer(String _textPlayer) {
        textPlayer = _textPlayer;
    }

    @Override
    protected void paintComponent(Graphics _arg0) {
        if (st == Status.TAKER) {
            _arg0.setColor(Color.RED);
        } else if (st == Status.CALLED_PLAYER) {
            _arg0.setColor(Color.YELLOW);
        } else if (st == Status.DEFENDER) {
            _arg0.setColor(Color.BLUE);
        } else {
            _arg0.setColor(Color.GREEN);
        }
        _arg0.fillRect(0, 0, getWidth(), getHeight());
        _arg0.setColor(Color.BLACK);
        _arg0.drawString(textPlayer, 0, getHeight());
    }
}
