package cards.gui.labels;

import cards.solitaire.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.sml.util.*;

public final class SolitaireGraphicCard extends AbsMetaLabelCard {
    private final AbstractImage bufferedImage;
    private final boolean selected;

    public SolitaireGraphicCard(CardSolitaire _e, AbstractProgramInfos _fact, TranslationsLg _l, boolean _sel) {
        super(_fact.getCompoFactory());
        setHorizontalAlignment(GuiConstants.RIGHT);
        setVerticalAlignment(GuiConstants.TOP);
        int[][] file_ = _l.getMaxiCards().getVal(Long.toString(_e.getId().getNo()));
        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact.getImageFactory(),file_);
        selected = _sel;
    }

    public void setPreferredSize(boolean _small) {
        setPreferredSize(getDimension(_small));
    }
    public static MetaDimension getDimension(boolean _small) {
        if (_small) {
            return new MetaDimension(100,25);
        }
        return new MetaDimension(100,150);
    }

    /**Methode importante dessinant les cartes des jeux de cartes face non cachee sauf pour certaines cartes du solitaire*/
    @Override
    public void paintComponent(AbstractImage _g2) {
        _g2.setColor(GuiConstants.WHITE);
        _g2.fillRect(0,0,getWidth(),getHeight());
        _g2.drawImage(bufferedImage, 0, 0);
        if (selected) {
            _g2.setColor(GuiConstants.RED);
        } else {
            _g2.setColor(GuiConstants.BLACK);
        }
        _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}
