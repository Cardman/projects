package code.images;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Displayable;

public final class Animation implements Displayable {

    public static final String SEPARATOR_IMAGE_DELAY = "_";
    public static final String SEPARATOR_IMAGES = ",";

    private CustList<ImageDelay> imagesDelays;

    private Animation() {
        imagesDelays = new CustList<ImageDelay>();
    }
    public Animation(String _imagesDelays) {
        imagesDelays = new CustList<ImageDelay>();
        for (String l: StringList.splitStrings(_imagesDelays, SEPARATOR_IMAGES)) {
            imagesDelays.add(new ImageDelay(l));
        }
    }

    public Animation(Image _image) {
        imagesDelays = new CustList<ImageDelay>();
        imagesDelays.add(new ImageDelay(_image,0L));
    }

    
    public static Animation newAnimation(String _string) {
        return new Animation(_string);
    }

    public static Animation clipImage(Image _image,int _nbImagesForAnimation,long _delay) {
        Animation animation_ = new Animation();
        int w_ = _image.getWidth()/_nbImagesForAnimation;
        int h_ = _image.getHeight();
        for (int i = CustList.FIRST_INDEX;i<_nbImagesForAnimation;i++) {
            animation_.imagesDelays.add(new ImageDelay(_image.clip(w_*i, 0, w_, h_),_delay));
        }
        return animation_;
    }
    public CustList<ImageDelay> getImagesDelays() {
        return imagesDelays;
    }
    public void setImagesDelays(CustList<ImageDelay> _imagesDelays) {
        imagesDelays = _imagesDelays;
    }

    
    @Override
    public String display() {
        StringList lines_ = new StringList();
        for (ImageDelay p: imagesDelays) {
            lines_.add(p.display());
        }
        return lines_.join(SEPARATOR_IMAGES);
    }
}
