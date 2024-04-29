package aiki.gui.components.walk;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DefTileRender implements IntTileRender {

    private static final String CST_KO = "KO";
    private static final String PER_CENT = " %";
    @Override
    public AbstractImage render(AbstractImageFactory _fact, int[][] _img, int _defWidth, int _defHeight) {
        if (_img.length == 0) {
            return _fact.newImageArgb(_defWidth, _defHeight);
        }
        AbstractImage buff_ = ConverterGraphicBufferedImage.decodeToImage(_fact,_img);
        ConverterGraphicBufferedImage.transparentAllWhite(buff_);
        return buff_;
    }

    @Override
    public AbstractImage centerImage(AbstractImageFactory _fact, int[][] _img, int _def) {
        AbstractImage buff_ = ConverterGraphicBufferedImage.centerImage(_fact,_img,_def);
        ConverterGraphicBufferedImage.transparentAllWhite(buff_);
        return buff_;
    }

    @Override
    public AbstractImage centerImage(AbstractImageFactory _fact, int[][] _img, int _width, int _height) {
        AbstractImage buff_ = ConverterGraphicBufferedImage.centerImage(_fact,_img,_width,_height);
        ConverterGraphicBufferedImage.transparentAllWhite(buff_);
        return buff_;
    }

    public static int width(AbsCompoFactory _facto, MetaFont _lab) {
        return NumberUtil.max(_facto.stringWidth(_lab,CST_KO),_facto.stringWidth(_lab, StringUtil.concat("100",PER_CENT)));
    }
    public static int widthLgMax(AbsCompoFactory _facto, MetaFont _lab) {
        return _facto.stringWidth(_lab,Long.toString(Long.MAX_VALUE));
    }
}
