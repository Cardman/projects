package code.gui.animations;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.util.CustList;
import code.util.consts.Constants;

public final class AnimatedImage extends Thread {

    private JLabel label;

    private CustList<BufferedImage> images;

    private int delay;

    private volatile boolean animated = true;

    public AnimatedImage(JLabel _label, CustList<BufferedImage> _images,
            int _delay) {
        label = _label;
        images = _images;
        delay = _delay;
    }

    @Override
    public void run() {
        int i = 0;
        while (animated) {
            label.setIcon(new ImageIcon(images.get(i)));
            Constants.sleep(delay);
            i++;
            if (i >= images.size()) {
                i = 0;
            }
        }
    }

    public void stopAnimation() {
        animated = false;
    }
}
