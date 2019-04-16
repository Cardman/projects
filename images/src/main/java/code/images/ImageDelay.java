package code.images;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;

public final class ImageDelay implements Displayable {

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
        delay = Numbers.parseLongZero(infos_.last());
    }

    
    public static ImageDelay newImageDelay(String _string) {
        return new ImageDelay(_string);
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(image.display());
        str_.append(SEPARATOR_IMAGE_DELAY);
        str_.append(delay);
        return str_.toString();
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
