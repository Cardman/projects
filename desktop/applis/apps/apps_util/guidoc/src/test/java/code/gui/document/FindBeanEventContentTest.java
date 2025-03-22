package code.gui.document;

import code.formathtml.render.*;
import code.gui.AbsCustComponent;
import code.gui.AbsTextPane;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.IdMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FindBeanEventContentTest extends EquallableGuiDocUtil {
    @Test
    public void action1() {
        MockProgramInfos a_ = getA();
        BeanBuilderHelperContent builder_ = new BeanBuilderHelperContent(a_);
        builder_.setFrame(a_.getFrameFactory().newCommonFrame());
        builder_.setScrollPane(a_.getCompoFactory().newAbsScrollPane());
        FindBeanEventContent ev_ = new FindBeanEventContent(a_.getCompoFactory().newTextField("t"),a_);
        ev_.setRefsSearch(builder_.getRefsSearch());
        ev_.setFonts(builder_.getFonts());
        ev_.setScrollPane(builder_.getScrollPane());
        ev_.setParents(new IdMap<AbsCustComponent, AbsCustComponent>());
        MetaSearchableContent s_ = new MetaSearchableContent("ta",0,0);
        builder_.getMetaSearchableContents().add(s_);
        builder_.getRefsSearch().addEntry(s_,a_.getCompoFactory().newTextPane());
        builder_.getMetaSearchableContents().add(new MetaSearchableContent(null, 0,1));
        ev_.setFinding(new FindNextElement(builder_.getMetaSearchableContents()));
        ev_.action();
        assertFalse(ev_.getLabels().isEmpty());
    }
    @Test
    public void action2() {
        MockProgramInfos a_ = getA();
        BeanBuilderHelperContent builder_ = new BeanBuilderHelperContent(a_);
        builder_.setFrame(a_.getFrameFactory().newCommonFrame());
        builder_.setScrollPane(a_.getCompoFactory().newAbsScrollPane());
        FindBeanEventContent ev_ = new FindBeanEventContent(a_.getCompoFactory().newTextField("s"),a_);
        ev_.setRefsSearch(builder_.getRefsSearch());
        ev_.setFonts(builder_.getFonts());
        ev_.setScrollPane(builder_.getScrollPane());
        ev_.setParents(new IdMap<AbsCustComponent, AbsCustComponent>());
        MetaSearchableContent s_ = new MetaSearchableContent("ta",0,0);
        builder_.getMetaSearchableContents().add(s_);
        builder_.getRefsSearch().addEntry(s_,a_.getCompoFactory().newTextPane());
        builder_.getMetaSearchableContents().add(new MetaSearchableContent(null, 0,1));
        ev_.setFinding(new FindNextElement(builder_.getMetaSearchableContents()));
        ev_.action();
        assertTrue(ev_.getLabels().isEmpty());
    }
    @Test
    public void action3() {
        MockProgramInfos a_ = getA();
        BeanBuilderHelperContent builder_ = new BeanBuilderHelperContent(a_);
        builder_.setFrame(a_.getFrameFactory().newCommonFrame());
        builder_.setScrollPane(a_.getCompoFactory().newAbsScrollPane());
        FindBeanEventContent ev_ = new FindBeanEventContent(a_.getCompoFactory().newTextField("t"),a_);
        ev_.setRefsSearch(builder_.getRefsSearch());
        ev_.setFonts(builder_.getFonts());
        ev_.setScrollPane(builder_.getScrollPane());
        MetaSearchableContent s_ = new MetaSearchableContent("ta",0,0);
        builder_.getMetaSearchableContents().add(s_);
        AbsTextPane tp_ = a_.getCompoFactory().newTextPane();
        builder_.getRefsSearch().addEntry(s_, tp_);
        builder_.getFonts().addEntry(tp_,a_.getCompoFactory().newAttrSet());
        builder_.getMetaSearchableContents().add(new MetaSearchableContent(null, 0,1));
        builder_.getParents().addEntry(tp_,builder_.getScrollPane());
        ev_.setParents(builder_.getParents());
        ev_.setFinding(new FindNextElement(builder_.getMetaSearchableContents()));
        ev_.action();
        ev_.action();
        assertTrue(ev_.getLabels().isEmpty());
    }
    @Test
    public void action4() {
        MockProgramInfos a_ = getA();
        BeanBuilderHelperContent builder_ = new BeanBuilderHelperContent(a_);
        builder_.setFrame(a_.getFrameFactory().newCommonFrame());
        builder_.setScrollPane(a_.getCompoFactory().newAbsScrollPane());
        FindBeanEventContent ev_ = new FindBeanEventContent(a_.getCompoFactory().newTextField(""),a_);
        ev_.setRefsSearch(builder_.getRefsSearch());
        ev_.setFonts(builder_.getFonts());
        ev_.setScrollPane(builder_.getScrollPane());
        ev_.setParents(new IdMap<AbsCustComponent, AbsCustComponent>());
        MetaSearchableContent s_ = new MetaSearchableContent("ta",0,0);
        builder_.getMetaSearchableContents().add(s_);
        builder_.getRefsSearch().addEntry(s_,a_.getCompoFactory().newTextPane());
        builder_.getMetaSearchableContents().add(new MetaSearchableContent(null, 0,1));
        ev_.setFinding(new FindNextElement(builder_.getMetaSearchableContents()));
        ev_.action();
        assertTrue(ev_.getLabels().isEmpty());
        builder_.getFrame();
        builder_.getApi();
        builder_.getRefsSearchDir();
        builder_.getColours();
    }
    private MockProgramInfos getA() {
        return newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
    }
}
