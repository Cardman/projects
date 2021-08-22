package code.gui.animations;

import code.gui.PreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class AnimatedImage implements Runnable {

    private PreparedLabel label;

    private CustList<AbstractImage> images;

    private int delay;

    private AbstractAtomicBoolean animated;

    private AbstractThreadFactory fact;
    private AbstractImageFactory img;
    public AnimatedImage(AbstractImageFactory _img, AbstractThreadFactory _fact, PreparedLabel _label, CustList<AbstractImage> _images,
                         int _delay) {
        animated = _fact.newAtomicBoolean(true);
        label = _label;
        images = _images;
        delay = _delay;
        fact = _fact;
        img = _img;
    }

    @Override
    public void run() {
        int i = 0;
        while (animated.get()) {
            label.setIcon(img,images.get(i));
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
