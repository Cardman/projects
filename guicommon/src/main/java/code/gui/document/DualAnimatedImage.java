package code.gui.document;

import java.awt.Cursor;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaAnimatedImage;
import code.gui.ThreadUtil;
import code.sml.Element;


public class DualAnimatedImage extends DualImage {
    private String href;

    private int index;

    private int delay;

    private AnimateImage imageThread;

    public DualAnimatedImage(DualContainer _container, MetaAnimatedImage _component, RenderedPage _page) {
        super(_container, _component, _page);
        Element anchor_ = _component.getAnchor();
        href = "";
        if (anchor_ != null) {
            JLabel label_ = getGraphic();
            label_.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        imageThread.start();
    }

    @Override
    public MetaAnimatedImage getComponent() {
        return (MetaAnimatedImage) super.getComponent();
    }

    public void increment() {
        paint();
        ThreadUtil.sleep(delay);
        index++;
        if (index >= getComponent().getImages().size()) {
            index = 0;
        }
    }

    @Override
    public void paint() {
        int[][] img_ = getComponent().getImages().get(index);
        if (img_.length == 0) {
            return;
        }
        BufferedImage imgBuf_ = new BufferedImage(img_[0].length, img_.length, BufferedImage.TYPE_INT_RGB);
        int y_ = 0;
        for (int[] r: img_) {
            int x_ = 0;
            for (int p: r) {
                imgBuf_.setRGB(x_, y_, p);
                x_++;
            }
            y_++;
        }
        getGraphic().setIcon(new ImageIcon(imgBuf_));
    }
    public String getHref() {
        return href;
    }
}
