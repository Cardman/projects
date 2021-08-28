package cards.gui.labels;


import cards.consts.Role;
import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;

public final class CellPlayer extends AbsMetaLabel {

    private Role st;

    private String textPlayer = "";

    public CellPlayer(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
    }

    public void setStatus(Role _st) {
        st = _st;
    }

    public void setTextPlayer(String _textPlayer) {
        textPlayer = _textPlayer;
    }

    @Override
    public void paintComponent(AbstractImage _arg0) {
        if (st == Role.TAKER) {
            _arg0.setColor(GuiConstants.RED);
        } else if (st == Role.CALLED_PLAYER) {
            _arg0.setColor(GuiConstants.YELLOW);
        } else if (st == Role.DEFENDER) {
            _arg0.setColor(GuiConstants.BLUE);
        } else {
            _arg0.setColor(GuiConstants.GREEN);
        }
        _arg0.fillRect(0, 0, getWidth(), getHeight());
        _arg0.setColor(GuiConstants.BLACK);
        _arg0.drawString(textPlayer, 0, getHeight());
    }
}
