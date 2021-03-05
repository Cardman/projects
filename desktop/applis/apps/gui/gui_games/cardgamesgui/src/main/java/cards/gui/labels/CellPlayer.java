package cards.gui.labels;
import java.awt.Color;

import cards.consts.Role;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;

public class CellPlayer extends PaintableLabel {

    private Role st;

    private String textPlayer = "";

    public void setStatus(Role _st) {
        st = _st;
    }

    public void setTextPlayer(String _textPlayer) {
        textPlayer = _textPlayer;
    }

    @Override
    public void paintComponent(CustGraphics _arg0) {
        if (st == Role.TAKER) {
            _arg0.setColor(Color.RED);
        } else if (st == Role.CALLED_PLAYER) {
            _arg0.setColor(Color.YELLOW);
        } else if (st == Role.DEFENDER) {
            _arg0.setColor(Color.BLUE);
        } else {
            _arg0.setColor(Color.GREEN);
        }
        _arg0.fillRect(0, 0, getWidth(), getHeight());
        _arg0.setColor(Color.BLACK);
        _arg0.drawString(textPlayer, 0, getHeight());
    }
}
