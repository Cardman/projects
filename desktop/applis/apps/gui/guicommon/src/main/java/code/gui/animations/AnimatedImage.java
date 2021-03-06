package code.gui.animations;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import code.gui.PreparedLabel;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class AnimatedImage implements Runnable {

    private PreparedLabel label;

    private CustList<BufferedImage> images;

    private int delay;

    private AtomicBoolean animated = new AtomicBoolean(true);

    public AnimatedImage(PreparedLabel _label, CustList<BufferedImage> _images,
                         int _delay) {
        label = _label;
        images = _images;
        delay = _delay;
    }

    @Override
    public void run() {
        int i = 0;
        while (animated.get()) {
            label.setIcon(images.get(i));
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
