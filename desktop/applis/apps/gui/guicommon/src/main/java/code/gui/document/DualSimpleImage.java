package code.gui.document;

import code.formathtml.render.MetaSimpleImage;
import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.sml.Element;


public final class DualSimpleImage extends DualImage {
    private final int[][] image;

    public DualSimpleImage(DualContainer _container, MetaSimpleImage _component, RenderedPage _page) {
        super(_container, _component, _page);
        Element anchor_ = _component.getAnchor();
        image = _component.getImage();
        if (anchor_ != null) {
            AbsPreparedLabel label_ = getLabel();
            label_.setHandCursor();
            label_.addMouseListener(new AnchorEvent(anchor_, _page));
        }
    }

    @Override
    public void paint() {
        if (image.length == 0) {
            return;
        }
        AbstractImage imgBuf_ = getPage().getGene().getImageFactory().newImageRgb(image[0].length, image.length);
        int y_ = 0;
        for (int[] r: image) {
            int x_ = 0;
            for (int p: r) {
                imgBuf_.setRGB(x_, y_, p);
                x_++;
            }
            y_++;
        }
        getLabel().setIcon(getPage().getGene().getImageFactory(), imgBuf_);
    }
}
