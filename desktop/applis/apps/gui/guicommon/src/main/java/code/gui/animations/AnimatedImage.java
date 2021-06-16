package code.gui.animations;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

import code.gui.PreparedLabel;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class AnimatedImage implements Runnable {

    private PreparedLabel label;

    private CustList<BufferedImage> images;

    private int delay;

    private AbstractAtomicBoolean animated;

    private AbstractThreadFactory fact;
    public AnimatedImage(AbstractThreadFactory _fact, PreparedLabel _label, CustList<BufferedImage> _images,
                         int _delay) {
        animated = _fact.newAtomicBoolean(true);
        label = _label;
        images = _images;
        delay = _delay;
        fact = _fact;
    }

    @Override
    public void run() {
        int i = 0;
        while (animated.get()) {
            label.setIcon(images.get(i));
            ThreadUtil.sleep(fact,delay);
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
