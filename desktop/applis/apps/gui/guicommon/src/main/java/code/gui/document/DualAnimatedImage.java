package code.gui.document;

import java.awt.Cursor;

import code.formathtml.render.MetaAnimatedImage;
import code.gui.PreparedLabel;
import code.gui.images.AbstractImage;
import code.threads.ThreadUtil;
import code.sml.Element;
import code.util.CustList;


public final class DualAnimatedImage extends DualImage {
    private String href;

    private int index;

    private int delay;

    private AnimateImage imageThread;

    private CustList<int[][]> images;

    public DualAnimatedImage(DualContainer _container, MetaAnimatedImage _component, RenderedPage _page) {
        super(_container, _component, _page);
        images = _component.getImages();
        Element anchor_ = _component.getAnchor();
        href = "";
        if (anchor_ != null) {
            PreparedLabel label_ = getLabel();
            label_.setCursor(Cursor.HAND_CURSOR);
            label_.addMouseListener(new AnchorEvent(anchor_, _page, this));
            if (!anchor_.getAttribute("command").isEmpty()) {
                href = anchor_.getAttribute("command");
            } else if (!anchor_.getAttribute("href").isEmpty()) {
                href = anchor_.getAttribute("href");
            } else {
                href = "";
            }
        }
        delay = _component.getDelay();
        if (delay <= 0) {
            delay = 100;
        }
        imageThread = new AnimateImage(this);
    }

    public AnimateImage getImageThread() {
        return imageThread;
    }

    public void start() {
        imageThread.setAnimated(true);
        getPage().getGene().getThreadFactory().newStartedThread(imageThread);
    }

    public void increment() {
        paint();
        ThreadUtil.sleep(getPage().getGene().getThreadFactory(),delay);
        index++;
        if (index >= images.size()) {
            index = 0;
        }
    }

    @Override
    public void paint() {
        int[][] img_ = images.get(index);
        if (img_.length == 0) {
            return;
        }
        AbstractImage imgBuf_ = getPage().getGene().getImageFactory().newImageRgb(img_[0].length, img_.length);
        int y_ = 0;
        for (int[] r: img_) {
            int x_ = 0;
            for (int p: r) {
                imgBuf_.setRGB(x_, y_, p);
                x_++;
            }
            y_++;
        }
        getLabel().setIcon(getPage().getGene().getImageFactory(), imgBuf_);
    }
    public String getHref() {
        return href;
    }
}
