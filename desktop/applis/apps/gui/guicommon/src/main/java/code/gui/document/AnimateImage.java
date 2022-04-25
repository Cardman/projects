package code.gui.document;

public final class AnimateImage implements Runnable {

    private final DualAnimatedImage images;

    public AnimateImage(DualAnimatedImage _images) {
        images = _images;
    }

    @Override
    public void run() {
        images.increment();
    }
}
