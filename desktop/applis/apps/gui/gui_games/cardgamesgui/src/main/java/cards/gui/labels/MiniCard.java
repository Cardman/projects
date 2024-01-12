package cards.gui.labels;


import cards.gui.WindowCardsInt;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.util.StringMap;

public final class MiniCard extends AbsMetaLabelCard {
    private final AbstractImageFactory imageFactory;
    private final StringMap<StringMap<int[][]>> imgs;

    private final String lg;
    private final int nb;

    public MiniCard(String _lg, WindowCardsInt _compoFactory, int _n) {
        super(_compoFactory.getCompoFactory());
        lg = _lg;
        setHorizontalAlignment(GuiConstants.RIGHT);
        setVerticalAlignment(GuiConstants.TOP);
        setPreferredSize(new MetaDimension(24, 10));
        imageFactory = _compoFactory.getImageFactory();
        imgs = _compoFactory.getImagesMiniDef();
        nb = _n;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.drawImage(ConverterGraphicBufferedImage.decodeToImage(imageFactory, imgs.getVal(lg).getVal(""+ nb)),0,0);
    }

}
