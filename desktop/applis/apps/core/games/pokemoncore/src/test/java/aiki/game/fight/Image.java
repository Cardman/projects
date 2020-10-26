package aiki.game.fight;

import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class Image {
    public static final char SEPARATOR_CHAR = ';';

    private static final String RETURN_LINE2 = "\r";

    private static final String RETURN_LINE = "\n";

    private int width;

    private Ints pixels;

    private Image() {
        pixels = new Ints();
    }
    public Image(String _contentFile) {
        pixels = new Ints();
        String string_ = StringUtil.removeStrings(_contentFile, RETURN_LINE, RETURN_LINE2);
        StringList lines_ = StringUtil.splitChars(string_, SEPARATOR_CHAR);
        width = NumberUtil.parseInt(lines_.first());
        int len_ = lines_.size();
        for (int i = IndexConstants.SECOND_INDEX; i<len_; i++) {
            pixels.add(NumberUtil.parseInt(lines_.get(i)));
        }
    }

    public int getHeight() {
        if (pixels.isEmpty()) {
            return 0;
        }
        return pixels.size()/width;
    }

    public int getWidth() {
        return width;
    }


    public Ints getPixels() {
        return pixels;
    }

}
