package code.gui.document;

import aiki.beans.*;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;

public final class BeanBuilderHelper extends IntBeanBuilderHelper {
//    private AbsScrollPane scrollPane;
//    private final AbstractProgramInfos content.getApi();
    private final FindBeanEvent finder;
    private final BeanBuilderHelperContent content;
//    private final IdMap<MetaSearchableContent,AbsTextPane> refsSearch = new IdMap<MetaSearchableContent,AbsTextPane>();
//    private final StringMap<AbsTextPane> refsSearchDir = new StringMap<AbsTextPane>();
//    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    private final CustList<AbsPanel> stack = new CustList<AbsPanel>();
//    private AbsCommonFrame frame;
    private final IdMap<AbsCustComponent,AbsCustComponent> parents = new IdMap<AbsCustComponent,AbsCustComponent>();
//    private final Ints colours = new Ints();
//    private final IdMap<AbsTextPane,AbsAttrSet> fonts = new IdMap<AbsTextPane,AbsAttrSet>();
    public BeanBuilderHelper(AbstractProgramInfos _a, FindBeanEvent _f) {
//        this.content.getApi() = _a;
        this.finder = _f;
        content = new BeanBuilderHelperContent(_a);
        content.getColours().add(GuiConstants.BLACK);
        content.getColours().add(GuiConstants.RED);
        content.getColours().add(GuiConstants.GREEN);
        content.getColours().add(GuiConstants.BLUE);
        setGenInput(new DefBeanGeneInput(this,_a));
    }

    public void reset() {
        getMetaSearchableContents().clear();
        getParents().clear();
        getRefsSearch().clear();
        getRefsSearchDir().clear();
        getOrderedLists().clear();
        content.getFonts().clear();
        setPartGroup(0);
        setRowGroup(0);
    }

    @Override
    public void initLine() {
        super.initLine();
        stack.add(content.getApi().getCompoFactory().newLineBox());
        setBackgroundBody();
    }

    @Override
    public void initPage() {
        super.initPage();
        stack.add(content.getApi().getCompoFactory().newPageBox());
        setBackgroundBody();
    }

    @Override
    public void initGrid() {
        super.initGrid();
        stack.add(content.getApi().getCompoFactory().newGrid());
        setBackgroundBody();
    }

    @Override
    public void feedParentsCts() {
        AbsPanel current_ = stack.get(stack.size()-2);
        AbsPanel ch_ = stack.last();
        ch_.setLineBorder(GuiConstants.BLACK);
        super.feedParentsCts();
        current_.add(ch_, cts());
        getParents().addEntry(ch_, current_);
        stack.removeQuicklyLast();
        incColIndex();
    }
    public void setBackgroundBody() {
        stack.last().setBackground(GuiConstants.WHITE);
    }
    public void setTitledBorder(String _title){
        stack.last().setTitledBorder(_title);
    }
    @Override
    public void feedParents() {
        AbsPanel current_ = stack.get(stack.size()-2);
        AbsPanel ch_ = stack.last();
        current_.add(ch_);
        getParents().addEntry(ch_, current_);
        stack.removeQuicklyLast();
        super.feedParents();
    }

    public void feedParentCts(AbsCustComponent _ch) {
        AbsPanel current_ = stack.last();
        current_.add(_ch, cts());
        getParents().addEntry(_ch, current_);
        incColIndex();
    }

    public void feedParent(AbsCustComponent _ch) {
        AbsPanel current_ = stack.last();
        current_.add(_ch);
        getParents().addEntry(_ch, current_);
    }

    private void evt(IntBeanAction _e, AbsTextPane _tx) {
        anchor(content.getApi(), _tx);
        evtCompo(_e, _tx);
    }

    private void evtCompo(IntBeanAction _e, AbsCustComponent _tx) {
        getAnchors().add(_e);
        _tx.addMouseListener(new BeanAnchorEvent(this, _e));
    }

    public void formatMessageDir(String _txt) {
        AbsTextPane ch_ = message(_txt);
        feedParent(ch_);
        hierarchy(_txt, ch_);
    }

    @Override
    public void formatMessageDir(String _txt, IntBeanAction _e) {
        AbsTextPane ch_ = message(_txt);
        feedParent(ch_);
        hierarchy(_txt, ch_);
        evt(_e, ch_);
    }

    public void formatMessageDirCtsHeader(String _txt) {
        AbsTextPane txt_ = message(_txt);
        txt_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(txt_);
        hierarchy(_txt, txt_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        txt_.setBackground(GuiConstants.YELLOW);
    }
    public void formatMessageDirCts(String _txt) {
        AbsTextPane ch_ = message(_txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(ch_);
        hierarchy(_txt, ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }

    @Override
    public void formatMessageDirCts(String _txt, IntBeanAction _e) {
        AbsTextPane ch_ = message(_txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(ch_);
        hierarchy(_txt, ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        evt(_e, ch_);
    }

    public AbsTextPane message(String _txt) {
        AbsTextPane tp_ = content.getApi().getCompoFactory().newTextPane();
        tp_.setBackground(GuiConstants.WHITE);
        tp_.setForeground(content.getColours().get(getHeader()));
        AbstractThread th_ = content.getApi().getThreadFactory().newThread(new SetTextThread(tp_, StringUtil.nullToEmpty(_txt)));
        th_.start();
        th_.join();
        tp_.setEditable(false);
        return tp_;
    }

    @Override
    public void paintIndent() {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        stack.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    @Override
    public void paintNb(int _nb) {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.drawString(Long.toString(_nb),0,16);
        stack.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void paintMetaLabelDisk() {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.fillOval(0, 0, 16, 16);
        stack.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void addImg(int[][] _img) {
        stack.last().add(content.getApi().getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(content.getApi().getImageFactory(), _img)));
    }

    @Override
    public void addImgCts(int[][] _img, String _tip) {
        AbsPreparedLabel lab_ = content.getApi().getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(content.getApi().getImageFactory(), _img));
        lab_.setToolTipText(_tip);
        stack.last().add(lab_, cts());
        incColIndex();
    }

    @Override
    public void addImgCtsAnc(int[][] _img, String _tip, IntBeanAction _e) {
        AbsPreparedLabel lab_ = content.getApi().getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(content.getApi().getImageFactory(), _img));
        lab_.setToolTipText(_tip);
        stack.last().add(lab_, cts());
        incColIndex();
        evtCompo(_e, lab_);
    }

    public void hierarchy(String _txt, AbsTextPane _ch) {
        MetaSearchableContent s_ = new MetaSearchableContent(_txt, getPartGroup(), getRowGroup());
        content.getMetaSearchableContents().add(s_);
        getRefsSearch().addEntry(s_,_ch);
        if (!getRefLk().isEmpty()) {
            getRefsSearchDir().addEntry(getRefLk(),_ch);
        }
    }

    public CustList<AbsPanel> getStack() {
        return stack;
    }

    @Override
    public void build(String _dest) {
        int sep_ = _dest.indexOf('#');
        String page_;
        String name_;
        if (sep_ >= 0)  {
            page_ = _dest.substring(0,sep_);
            name_ = _dest.substring(sep_+1);
        } else {
            page_ = _dest;
            name_ = "";
        }
        BeanRenderWithAppName target_ = getRenders().getVal(page_);
        clearAnchors();
        reset();
        initPage();
        setBackgroundBody();
        target_.setBuilder(this);
        target_.build(target_.getFacade());
        getFinder().setFinding(this);
        getScrollPane().setViewportView(stack.last());
        getScrollPane().validate();
        AbsTextPane redir_ = getRefsSearchDir().getVal(name_);
        if (redir_ != null) {
            finder.scroll(redir_);
        }
        getFrame().pack();
        stack.removeQuicklyLast();
        decr();
    }

    public FindBeanEvent getFinder() {
        return finder;
    }


    private AbsGridConstraints cts() {
        AbsGridConstraints cts_ = content.getApi().getCompoFactory().newGridCts();
        if (colIndex() +1 == colCount()) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }

    private void anchor(AbstractProgramInfos _api, AbsTextPane _tx) {
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addUnderline(true);
        att_.addForeground(GuiConstants.BLUE);
        _tx.setCharacterAttributes(0, _tx.getText().length(), att_, false);
        content.getFonts().addEntry(_tx,att_);
    }

    public IdMap<AbsTextPane,AbsAttrSet> getFonts() {
        return content.getFonts();
    }

    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return content.getMetaSearchableContents();
    }

    public IdMap<AbsCustComponent, AbsCustComponent> getParents() {
        return parents;
    }

    public IdMap<MetaSearchableContent, AbsTextPane> getRefsSearch() {
        return content.getRefsSearch();
    }

    public StringMap<AbsTextPane> getRefsSearchDir() {
        return content.getRefsSearchDir();
    }

    public AbsScrollPane getScrollPane() {
        return content.getScrollPane();
    }

    public void setScrollPane(AbsScrollPane _s) {
        this.content.setScrollPane(_s);
    }

    public AbsCommonFrame getFrame() {
        return content.getFrame();
    }

    public void setFrame(AbsCommonFrame _f) {
        this.content.setFrame(_f);
    }

}
