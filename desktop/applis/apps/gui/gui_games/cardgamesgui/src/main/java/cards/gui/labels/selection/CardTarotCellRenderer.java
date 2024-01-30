package cards.gui.labels.selection;


import cards.consts.CouleurValeur;
import cards.gui.WindowCardsInt;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaFont;

/**
 */
public class CardTarotCellRenderer implements AbsCustCellRenderGene<CardTarot> {
    private final WindowCardsInt window;
    public CardTarotCellRenderer(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, CardTarot _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        return paintComponent(window, _info.getId(), _isSelected);
    }

    public static AbstractImage paintComponent(WindowCardsInt _win, CouleurValeur _id, boolean _sel) {
        AbstractImage img_ = _win.getImageFactory().newImageRgb(24, 10);
        updateImage(_win, _id, _sel, img_);
        return img_;
    }

    public static void updateImage(WindowCardsInt _win, CouleurValeur _id, boolean _sel, AbstractImage _img) {
        if(!_sel) {
            _img.drawImage(ConverterGraphicBufferedImage.decodeToImage(_win.getImageFactory(), _win.getFrames().currentLg().getMiniCardsDef().getVal(Long.toString(_id.nb()))),0,0);
        } else {
            _img.drawImage(ConverterGraphicBufferedImage.decodeToImage(_win.getImageFactory(), _win.getFrames().currentLg().getMiniCardsSel().getVal(Long.toString(_id.nb()))),0,0);
        }
    }

}
