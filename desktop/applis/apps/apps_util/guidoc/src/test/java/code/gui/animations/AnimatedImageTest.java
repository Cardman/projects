package code.gui.animations;

import code.formathtml.render.*;
import code.gui.TextAnswerValue;
import code.gui.images.AbstractImage;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockImage;
import code.mock.MockProgramInfos;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class AnimatedImageTest extends EquallableGuiDocUtil {
    @Test
    public void incr1() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        AnimatedImage i_ = new AnimatedImage(pr_.getImageFactory(), pr_.getThreadFactory(), pr_.getCompoFactory().newPreparedLabel(""), one(), 0);
        i_.run();
        assertEq(0,i_.getIndex());
    }
    @Test
    public void incr2() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        AnimatedImage i_ = new AnimatedImage(pr_.getImageFactory(), pr_.getThreadFactory(), pr_.getCompoFactory().newPreparedLabel(""), two(), 0);
        i_.run();
        assertEq(1,i_.getIndex());
    }

    private CustList<AbstractImage> one() {
        CustList<AbstractImage> l_ = new CustList<AbstractImage>();
        l_.add(new MockImage(new int[1][1]));
        return l_;
    }

    private CustList<AbstractImage> two() {
        CustList<AbstractImage> l_ = new CustList<AbstractImage>();
        l_.add(new MockImage(new int[1][1]));
        l_.add(new MockImage(new int[1][1]));
        return l_;
    }
}
