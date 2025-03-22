package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class BeanBuilderHelperContent {
    private AbsScrollPane scrollPane;
    private final AbstractProgramInfos api;
    private final IdMap<MetaSearchableContent,AbsTextPane> refsSearch = new IdMap<MetaSearchableContent,AbsTextPane>();
    private final StringMap<AbsTextPane> refsSearchDir = new StringMap<AbsTextPane>();
    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    private AbsCommonFrame frame;
    private final IdMap<AbsCustComponent,AbsCustComponent> parents = new IdMap<AbsCustComponent,AbsCustComponent>();
    private final Ints colours = new Ints();
    private final IdMap<AbsTextPane,AbsAttrSet> fonts = new IdMap<AbsTextPane,AbsAttrSet>();

    public BeanBuilderHelperContent(AbstractProgramInfos _a) {
        this.api = _a;
    }

    public void setScrollPane(AbsScrollPane _v) {
        this.scrollPane = _v;
    }

    public void setFrame(AbsCommonFrame _v) {
        this.frame = _v;
    }

    public AbsScrollPane getScrollPane() {
        return scrollPane;
    }

    public AbstractProgramInfos getApi() {
        return api;
    }

    public IdMap<MetaSearchableContent, AbsTextPane> getRefsSearch() {
        return refsSearch;
    }

    public StringMap<AbsTextPane> getRefsSearchDir() {
        return refsSearchDir;
    }

    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return metaSearchableContents;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public IdMap<AbsCustComponent, AbsCustComponent> getParents() {
        return parents;
    }

    public Ints getColours() {
        return colours;
    }

    public IdMap<AbsTextPane, AbsAttrSet> getFonts() {
        return fonts;
    }
}
