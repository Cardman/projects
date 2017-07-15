package code.images;
import java.awt.image.BufferedImage;

import code.util.CustList;

public final class GifAnimation {

    private CustList<BufferedImage> images = new CustList<BufferedImage>();

    private long delayMillis;

    private boolean loop = true;

    public CustList<BufferedImage> getImages() {
        return images;
    }

    public void setImages(CustList<BufferedImage> _images) {
        images = _images;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long _delayMillis) {
        delayMillis = _delayMillis;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean _loop) {
        loop = _loop;
    }
}
