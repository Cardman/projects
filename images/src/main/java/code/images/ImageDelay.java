package code.images;
import code.sml.FromAndToString;
import code.util.StringList;

public final class ImageDelay {

    public static final char SEPARATOR_IMAGE_DELAY = '_';

    private Image image;

    private long delay;

    public ImageDelay() {
    }

    public ImageDelay(Image _image, long _delay) {
        image = _image;
        delay = _delay;
    }

    public ImageDelay(String _str) {
        StringList infos_ = StringList.splitChars(_str, SEPARATOR_IMAGE_DELAY);
        image = new Image(infos_.first());
        delay = Long.parseLong(infos_.last());
    }

    @FromAndToString
    public static ImageDelay newImageDelay(String _string) {
        return new ImageDelay(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return image.toString()+SEPARATOR_IMAGE_DELAY+delay;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image _image) {
        image = _image;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long _delay) {
        delay = _delay;
    }
}
