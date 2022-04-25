package code.gui.animations;

import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class AnimatedImage implements Runnable {

    private final AbsPreparedLabel label;

    private final CustList<AbstractImage> images;

    private final int delay;

    private final AbstractThreadFactory fact;
    private final AbstractImageFactory img;
    private int index;
    public AnimatedImage(AbstractImageFactory _img, AbstractThreadFactory _fact, AbsPreparedLabel _label, CustList<AbstractImage> _images,
                         int _delay) {
        label = _label;
        images = _images;
        delay = _delay;
        fact = _fact;
        img = _img;
    }

    @Override
    public void run() {
        label.setIcon(img,images.get(index));
        ThreadUtil.sleep(fact,delay);
        index++;
        if (index >= images.size()) {
            index = 0;
        }
    }

    public void reset() {
        index = 0;
    }

}
