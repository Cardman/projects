package cards.gui.labels;


import cards.facade.Games;
import cards.tarot.enumerations.Handfuls;
import code.gui.GuiConstants;
import code.gui.LabelButtonUtil;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;

public final class HandfulLabel extends AbsMetaLabelCard {

    private Handfuls bid;

    private boolean selected;

    private String text;

    public HandfulLabel(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
    }

    public void setSuit(Handfuls _bid, TranslationsLg _lg) {
        bid = _bid;
        setText(Games.toString(bid,_lg));
        getPaintableLabel().setBackground(GuiConstants.WHITE);
        getPaintableLabel().setForeground(GuiConstants.newColor(0, 0, 127));
        int h_ = getFact().heightFont(getPaintableLabel().getMetaFont());
        int w_ = getFact().stringWidth(getPaintableLabel().getMetaFont(),getText());
        setPreferredSize(new MetaDimension(w_, h_));
    }

    public void setText(String _emptyString) {
        text = _emptyString;
    }

    public void setSelected(Handfuls _bid) {
        selected = bid == _bid;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        int h_ = getFact().heightFont(getPaintableLabel().getMetaFont());
        int w_ = getFact().stringWidth(getPaintableLabel().getMetaFont(),text);
        LabelButtonUtil.paintDefaultLabel(_g, text, w_, getWidth(), h_, GuiConstants.newColor(0, 0, 127), GuiConstants.WHITE);
        _g.setColor(GuiConstants.BLACK);
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    private String getText() {
        return text;
    }

}
