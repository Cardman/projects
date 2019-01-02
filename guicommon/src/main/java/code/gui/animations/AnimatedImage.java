package code.gui.animations;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.gui.ThreadUtil;
import code.util.CustList;

public final class AnimatedImage extends Thread {

    private JLabel label;

    private CustList<BufferedImage> images;

    private int delay;

    private AtomicBoolean animated = new AtomicBoolean(true);

    public AnimatedImage(JLabel _label, CustList<BufferedImage> _images,
            int _delay) {
        label = _label;
        images = _images;
        delay = _delay;
    }

    @Override
    public void run() {
        int i = 0;
        while (animated.get()) {
            label.setIcon(new ImageIcon(images.get(i)));
            ThreadUtil.sleep(delay);
            i++;
            if (i >= images.size()) {
                i = 0;
            }
        }
    }

    public void stopAnimation() {
        animated.set(false);
    }
}
