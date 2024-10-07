package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.images.BaseSixtyFourUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.SetupableAnalyzingDoc;
import code.util.CustList;
import code.util.Ints;
import code.util.core.*;
import org.junit.Test;

public final class RenderedPageTest extends EquallableGuiDocUtil {
    @Test
    public void initConfOnly1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = newRenderedPage(pr_);
        assertFalse(r_.isProcessing());
        assertTrue(r_.initializeOnlyConf(new AbstractRenderActionWithAction(),new MockWithPageInfos(),null));
        assertTrue(r_.isProcessing());
    }
    @Test
    public void initConfOnly2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = newRenderedPage(pr_);
        r_.initializeOnlyConf(new AbstractRenderActionWithAction(),new MockWithPageInfos(),null);
        assertFalse(r_.initializeOnlyConf(new AbstractRenderActionWithAction(),new MockWithPageInfos(),null));
        assertTrue(r_.isProcessing());
    }
    @Test
    public void setFinding() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = newRenderedPage(pr_);
        r_.setFinding(getMetaDocument("<html/>"));
        AbsButton b_ = pr_.getCompoFactory().newPlainButton();
        r_.addFinder(pr_.getCompoFactory().newTextField(), b_);
        r_.setFinding(getMetaDocument("<html/>"));
        assertEq(1, ((MockPlainButton) b_).getActionListeners().size());
    }
    @Test
    public void render1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(newRenderedPage(pr_),"<html/>");
        assertEq(0,r_.allMainComponents().size());
    }
    @Test
    public void render2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html/>");
        assertEq(0,r_.allMainComponents().size());
    }
    @Test
    public void render3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><head><title>title</title></head></html>");
        assertEq(0,r_.allMainComponents().size());
    }
    @Test
    public void render4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><span name='lab'>_</span></body></html>","_#lab");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body>_<br/>_</body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body>_<hr/>_</body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ol><li>_</li></ol></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("1",((DualNumberedLabel)list_.get(0)).getNumber());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ol><li><ol><li>_</li></ol></li></ol></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(3, list_.size());
        assertEq("1",((DualNumberedLabel)list_.get(0)).getNumber());
        assertEq("1",((DualNumberedLabel)list_.get(1)).getNumber());
        assertEq("_",((DualPlainLabel)list_.get(2)).getText());
    }
    @Test
    public void render9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><a name='lab'>_</a></body></html>","_#lab");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualAnchoredLabel)list_.get(0)).getText());
    }
    @Test
    public void render10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ul><li>_</li></ul></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertSame(MetaPointForm.DISK,((DualMetaPointLabel)list_.get(0)).getForm());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ul><li><ul><li>_</li></ul></li></ul></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(3, list_.size());
        assertSame(MetaPointForm.DISK,((DualMetaPointLabel)list_.get(0)).getForm());
        assertSame(MetaPointForm.DISK,((DualMetaPointLabel)list_.get(1)).getForm());
        assertEq("_",((DualPlainLabel)list_.get(2)).getText());
    }
    @Test
    public void render12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ul type='circle'><li>_</li></ul></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertSame(MetaPointForm.CIRCLE,((DualMetaPointLabel)list_.get(0)).getForm());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ul type='square'><li>_</li></ul></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertSame(MetaPointForm.SQUARRE,((DualMetaPointLabel)list_.get(0)).getForm());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><ul type='rect'><li>_</li></ul></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertSame(MetaPointForm.RECT,((DualMetaPointLabel)list_.get(0)).getForm());
        assertEq("_",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><span name='lab'>_</span><pre>\n</pre></body></html>","_#lab");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(3, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
        assertEq("",((DualPlainLabel)list_.get(1)).getText());
        assertEq("",((DualPlainLabel)list_.get(2)).getText());
    }
    @Test
    public void render16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><span name='lab'>_</span><pre><a>\n</a></pre></body></html>","_#lab");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(3, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
        assertEq("",((DualAnchoredLabel)list_.get(1)).getText());
        assertEq("",((DualAnchoredLabel)list_.get(2)).getText());
    }
    @Test
    public void render17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)), "<html><body><img src='AAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(0,((DualSimpleImage)list_.get(0)).getImage().length);
    }
    @Test
    public void render18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><img src='AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(1,((DualSimpleImage)list_.get(0)).getImage().length);
        assertEq(1,((DualSimpleImage)list_.get(0)).getImage()[0].length);
    }
    @Test
    public void render19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><a><img src='AAABAAAA'/></a></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(1,((DualSimpleImage)list_.get(0)).getImage().length);
        assertEq(1,((DualSimpleImage)list_.get(0)).getImage()[0].length);
    }
    @Test
    public void render20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><img src='"+MetaDocument.SEP_IMG+"'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(2,((DualAnimatedImage)list_.get(0)).getImages().size());
        assertEq(0,((DualAnimatedImage)list_.get(0)).getImages().get(0).length);
        assertEq(0,((DualAnimatedImage)list_.get(0)).getImages().get(1).length);
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(1,((DualAnimatedImage)list_.get(0)).getIndex());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(0,((DualAnimatedImage)list_.get(0)).getIndex());
    }
    @Test
    public void render21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(3,((DualAnimatedImage)list_.get(0)).getImages().size());
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(0).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(0)[0].length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(1).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(1)[0].length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(2).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(2)[0].length);
        assertEq(0,((DualAnimatedImage)list_.get(0)).getIndex());
        assertTrue(((DualAnimatedImage)list_.get(0)).isStarted());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(1,((DualAnimatedImage)list_.get(0)).getIndex());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(2,((DualAnimatedImage)list_.get(0)).getIndex());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(0,((DualAnimatedImage)list_.get(0)).getIndex());
    }
    @Test
    public void render22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><a><img delay='1' src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></a></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(3,((DualAnimatedImage)list_.get(0)).getImages().size());
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(0).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(0)[0].length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(1).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(1)[0].length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(2).length);
        assertEq(1,((DualAnimatedImage)list_.get(0)).getImages().get(2)[0].length);
        assertEq(0,((DualAnimatedImage)list_.get(0)).getIndex());
        assertTrue(((DualAnimatedImage)list_.get(0)).isStarted());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(1,((DualAnimatedImage)list_.get(0)).getIndex());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(2,((DualAnimatedImage)list_.get(0)).getIndex());
        ((DualAnimatedImage)list_.get(0)).getImageThread().run();
        assertEq(0,((DualAnimatedImage)list_.get(0)).getIndex());
    }
    @Test
    public void render23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><p>_</p></body></html>","_#lab");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><p>_</p></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render25() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table>_</table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render26() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table><caption/></table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(0, list_.size());
    }
    @Test
    public void render27() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table><tr><td>_</td></tr></table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("_",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render28() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table><caption title='_'>0</caption><tr><td>1</td></tr></table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("0",((DualPlainLabel)list_.get(0)).getText());
        assertEq("1",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render29() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table><tr><td>0</td></tr><tr><td>1</td></tr></table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("0",((DualPlainLabel)list_.get(0)).getText());
        assertEq("1",((DualPlainLabel)list_.get(1)).getText());
    }
    @Test
    public void render30() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><table><caption>0</caption><tr><td>1</td></tr><tr><td>2</td></tr></table></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(3, list_.size());
        assertEq("0",((DualPlainLabel)list_.get(0)).getText());
        assertEq("1",((DualPlainLabel)list_.get(1)).getText());
        assertEq("2",((DualPlainLabel)list_.get(2)).getText());
    }
    @Test
    public void render31() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><span/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(0, list_.size());
    }
    @Test
    public void render32() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='radio' name='myradio' n-i='0' value='0'/><input type='radio' name='myradio' n-i='0' value='1' checked='checked'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(2, list_.size());
        assertEq("0",((DualRadionButton)list_.get(0)).getValue());
        assertFalse(((DualRadionButton)list_.get(0)).isChecked());
        assertEq("1",((DualRadionButton)list_.get(1)).getValue());
        assertTrue(((DualRadionButton)list_.get(1)).isChecked());
    }
    @Test
    public void render33() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='radio' name='myradio' n-i='0' value='0'/><input type='radio' name='myradio' n-i='0' value='1' checked='checked'/><input type='radio' name='myradio1' n-i='1' value='0'/><input type='radio' name='myradio1' n-i='1' value='1' checked='checked'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(4, list_.size());
        assertEq("0",((DualRadionButton)list_.get(0)).getValue());
        assertFalse(((DualRadionButton)list_.get(0)).isChecked());
        assertEq("1",((DualRadionButton)list_.get(1)).getValue());
        assertTrue(((DualRadionButton)list_.get(1)).isChecked());
        assertEq("0",((DualRadionButton)list_.get(2)).getValue());
        assertFalse(((DualRadionButton)list_.get(2)).isChecked());
        assertEq("1",((DualRadionButton)list_.get(3)).getValue());
        assertTrue(((DualRadionButton)list_.get(3)).isChecked());
    }
    @Test
    public void render34() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><input type='radio' name='myradio' n-i='0' value='0' checked='checked'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualRadionButton)list_.get(0)).getValue());
        assertTrue(((DualRadionButton)list_.get(0)).isChecked());
    }
    @Test
    public void render35() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='checkbox' name='myradio' n-i='0' value='0'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(SetupableAnalyzingDoc.OFF,((DualCheckedBox)list_.get(0)).getValue());
    }
    @Test
    public void render36() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='checkbox' name='myradio' n-i='0' value='0' checked='checked'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(SetupableAnalyzingDoc.ON,((DualCheckedBox)list_.get(0)).getValue());
    }
    @Test
    public void render37() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><head><style>select{border:#0BA0DF 1px solid;}</style></head><body><form n-f='0'><select name='myradio' n-i='0'><option value='0' selected='selected'>ZERO</option><option value='1'>ZERO</option></select></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualComboBox)list_.get(0)).getValue(0));
        assertEq("1",((DualComboBox)list_.get(0)).getValue(1));
    }
    @Test
    public void render38() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><head><style>select{border:#0BA0DF 1px solid;}</style></head><body><form n-f='0'><select name='myradio' n-i='0' multiple=''><option value='0' selected='selected'>ZERO</option><option value='1'>ZERO</option></select></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq(0,((DualComboList)list_.get(0)).getFormNb());
    }
    @Test
    public void render39() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='text' name='myradio' n-i='0' value='0'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualTextField)list_.get(0)).getValue());
    }
    @Test
    public void render40() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><textarea name='myradio' n-i='0'>0</textarea></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualTextArea)list_.get(0)).getValue());
    }
    @Test
    public void render41() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='number' name='myradio' n-i='0' value='0'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualSpinner)list_.get(0)).getValue());
    }
    @Test
    public void render42() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='range' name='myradio' n-i='0' value='0'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualSlider)list_.get(0)).getValue());
    }
    @Test
    public void render43() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><map>0</map></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualPlainLabel)list_.get(0)).getText());
    }
    @Test
    public void render44() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFrame(newRenderedPage(pr_)),"<html><body><form n-f='0'><input type='submit' name='myradio' value='0'/></form></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        assertEq(1, list_.size());
        assertEq("0",((DualButton)list_.get(0)).getValue());
    }
    @Test
    public void render45() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withFrame(newRenderedPage(pr_)),"<html><body><input type='submit' name='myradio' value='0'/><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        ((MockPlainButton)list_.get(0).getGraphic()).getMouseIntRelListeners().get(0).mouseReleased(null,null,null);
        assertTrue(r_.getAnims().get(0).isStarted());
    }
    @Test
    public void render46() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withStd(withFrame(newRenderedPage(pr_))),"<html><body><form n-f='0'><input type='submit' name='myradio' value='0'/></form><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        ((MockPlainButton)list_.get(0).getGraphic()).getMouseIntRelListeners().get(0).mouseReleased(null,null,null);
        assertFalse(r_.getAnims().get(0).isStarted());
    }
    @Test
    public void render47() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withStd(withFrame(newRenderedPage(pr_))),"<html><body><form n-f='0'><input type='submit' name='myradio' value='0'/><select name='myradio' n-i='0'><option value='0' selected='selected'>ZERO</option><option value='1'>ZERO</option><option value='2'>TWO</option><option value='3'>THREE</option></select></form><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        ((DualComboBox)list_.get(1)).setSelectedIndexes(Ints.newList(2));
        form(r_.getStandards().getPage(), 0L,0L);
        ((MockPlainButton)list_.get(0).getGraphic()).getMouseIntRelListeners().get(0).mouseReleased(null,null,null);
        assertEq(1,r_.getStandards().getPage().getContainer(0,0).getValue().size());
        assertEq("2",r_.getStandards().getPage().getContainer(0,0).getValue().get(0));
    }
    @Test
    public void render48() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withStd(withFrame(newRenderedPage(pr_))),"<html><body><form n-f='0'><input type='submit' name='myradio' value='0'/><select name='myradio' n-i='0' multiple=''><option value='0' selected='selected'>ZERO</option><option value='1'>ZERO</option><option value='2'>TWO</option><option value='3'>THREE</option></select></form><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        ((DualComboList)list_.get(1)).setSelectedIndexes(Ints.newList(1,3));
        form(r_.getStandards().getPage(), 0L,0L);
        ((MockPlainButton)list_.get(0).getGraphic()).getMouseIntRelListeners().get(0).mouseReleased(null,null,null);
        assertEq(2,r_.getStandards().getPage().getContainer(0,0).getValue().size());
        assertEq("1",r_.getStandards().getPage().getContainer(0,0).getValue().get(0));
        assertEq("3",r_.getStandards().getPage().getContainer(0,0).getValue().get(1));
    }
    @Test
    public void render49() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withImageDoc(withStd(withFrame(newRenderedPage(pr_))),"<html><body><a n-a='0'>0</a><img src='AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA"+MetaDocument.SEP_IMG+"AAABAAAA'/></body></html>");
        CustList<DualComponent> list_ = r_.allMainComponents();
        ((MockPreparedLabel)list_.get(0).getGraphic()).getMouseIntRelListeners().get(0).mouseReleased(null,null,null);
        assertFalse(r_.getAnims().get(0).isStarted());
    }
    @Test
    public void render50() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.start();
        r_.getField().setText("");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertEq(0,r_.getFinding().getLabels().size());
    }
    @Test
    public void render51() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.getField().setText("");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertEq(0,r_.getFinding().getLabels().size());
    }
    @Test
    public void render52() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.getField().setText("-");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertEq(0,r_.getFinding().getLabels().size());
    }
    @Test
    public void render53() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.getField().setText("_");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertFalse(r_.getFinding().getLabels().isEmpty());
    }
    @Test
    public void render54() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.getField().setText("_");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        r_.getField().setText("-");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertTrue(r_.getFinding().getLabels().isEmpty());
    }
    @Test
    public void render55() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body>_</body></html>");
        r_.getField().setText("_");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        r_.getField().setText("");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertTrue(r_.getFinding().getLabels().isEmpty());
    }
    @Test
    public void render56() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage r_ = withDoc(withFinder(withFrame(newRenderedPage(pr_))),"<html><body><a>_</a></body></html>");
        r_.getField().setText("_");
        ((MockPlainButton)r_.getFind()).getActionListeners().get(0).action();
        assertFalse(r_.getFinding().getLabels().isEmpty());
    }
}
