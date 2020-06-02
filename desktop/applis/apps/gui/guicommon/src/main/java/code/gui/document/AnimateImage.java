package code.gui.document;

public final class AnimateImage implements Runnable {

    private DualAnimatedImage images;

    private boolean animated;

    public AnimateImage(DualAnimatedImage _images) {
        images = _images;
    }

    public void setAnimated(boolean _animated) {
        animated = _animated;
    }

    @Override
    public void run() {
        while (animated) {
            images.increment();
        }
    }
}
