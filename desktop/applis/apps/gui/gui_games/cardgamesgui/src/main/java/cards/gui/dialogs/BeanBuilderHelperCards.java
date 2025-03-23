package cards.gui.dialogs;

import code.bean.*;
import code.formathtml.render.MetaSearchableContent;
import code.gui.*;
import code.gui.document.BeanBuilderHelperContent;
import code.gui.images.*;
import code.gui.initialize.*;
import code.threads.*;
import code.util.*;
import code.util.core.StringUtil;

public final class BeanBuilderHelperCards extends IntBeanBuilderHelperCommon {
//    private final AbstractProgramInfos content.getApi();
    private final CustList<AbsPanel> stackCards = new CustList<AbsPanel>();
    private final BeanBuilderHelperContent content;
    private final IdMap<AbsCustComponent,AbsCustComponent> parentsCards = new IdMap<AbsCustComponent,AbsCustComponent>();
//    private final Ints colours = new Ints();
    /*private AbsScrollPane scrollPane;
    private final AbstractProgramInfos content.getApi();
    private final FindBeanEvent finder;
    private final IdMap<MetaSearchableContent,AbsTextPane> refsSearch = new IdMap<MetaSearchableContent,AbsTextPane>();
    private final StringMap<AbsTextPane> refsSearchDir = new StringMap<AbsTextPane>();
    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    private final CustList<AbsPanel> stack = new CustList<AbsPanel>();
    private AbsCommonFrame frame;
    private final IdMap<AbsCustComponent,AbsCustComponent> parents = new IdMap<AbsCustComponent,AbsCustComponent>();
    private final Ints colours = new Ints();
    private final IdMap<AbsTextPane,AbsAttrSet> fonts = new IdMap<AbsTextPane,AbsAttrSet>();*/
    public BeanBuilderHelperCards(AbstractProgramInfos _a) {
//        this.content.getApi() = _a;
        content = new BeanBuilderHelperContent(_a);
        content.getColours().add(GuiConstants.BLACK);
        content.getColours().add(GuiConstants.BLUE);
    }

    @Override
    public void initLine() {
        super.initLine();
        stackCards.add(content.getApi().getCompoFactory().newLineBox());
        setBackgroundBody();
    }

    @Override
    public void initPage() {
        super.initPage();
        stackCards.add(content.getApi().getCompoFactory().newPageBox());
        setBackgroundBody();
    }

    @Override
    public void initGrid() {
        super.initGrid();
        stackCards.add(content.getApi().getCompoFactory().newGrid());
        setBackgroundBody();
    }

    @Override
    public void feedParentsCts() {
        AbsPanel current_ = stackCards.get(stackCards.size()-2);
        AbsPanel ch_ = stackCards.last();
        ch_.setLineBorder(GuiConstants.BLACK);
        super.feedParentsCts();
        current_.add(ch_, cts());
        getParentsCards().addEntry(ch_, current_);
        stackCards.removeQuicklyLast();
        incColIndex();
    }
    public void setBackgroundBody() {
        stackCards.last().setBackground(GuiConstants.WHITE);
    }
    public void setTitledBorder(String _title){
        stackCards.last().setTitledBorder(_title);
    }
    @Override
    public void feedParents() {
        AbsPanel current_ = stackCards.get(stackCards.size()-2);
        AbsPanel ch_ = stackCards.last();
        current_.add(ch_);
        getParentsCards().addEntry(ch_, current_);
        stackCards.removeQuicklyLast();
        super.feedParents();
    }

    public void feedParentCts(AbsCustComponent _ch) {
        AbsPanel current_ = stackCards.last();
        current_.add(_ch, cts());
        getParentsCards().addEntry(_ch, current_);
        incColIndex();
    }

    public void feedParent(AbsCustComponent _ch) {
        AbsPanel current_ = stackCards.last();
        current_.add(_ch);
        getParentsCards().addEntry(_ch, current_);
    }
    @Override
    public String getLanguage() {
        return content.getApi().getLanguage();
    }

    public void formatMessageDir(String _txt) {
        AbsTextPane ch_ = messageCards(_txt);
        feedParent(ch_);
        MetaSearchableContent s_ = new MetaSearchableContent(_txt, getPartGroup(), getRowGroup());
        content.getMetaSearchableContents().add(s_);
        content.getRefsSearch().addEntry(s_,ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }

    public void formatMessageDirCtsHeader(String _txt) {
        AbsTextPane txt_ = messageCards(_txt);
        txt_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(txt_);
        txt_.setBackground(GuiConstants.YELLOW);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }
    public void formatMessageDirCts(String _txt) {
        AbsTextPane ch_ = messageCards(_txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }


    public AbsTextPane messageCards(String _txt) {
        AbsTextPane tp_ = content.getApi().getCompoFactory().newTextPane();
        tp_.setBackground(GuiConstants.WHITE);
        tp_.setForeground(content.getColours().get(getHeader()));
        tp_.setText(StringUtil.nullToEmpty(_txt));
        AbstractThread th_ = content.getApi().getThreadFactory().newThread(new SetTextThreadCards(tp_, StringUtil.nullToEmpty(_txt)));
        th_.start();
        th_.join();
        tp_.setEditable(false);
        return tp_;
    }

    @Override
    public void breakNext() {
        nextPart();
    }
    @Override
    public void paintIndent() {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        stackCards.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    @Override
    public void paintNb(int _nb) {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.drawString(Long.toString(_nb),0,16);
        stackCards.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void paintMetaLabelDisk() {
        AbstractImage img_ = content.getApi().getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.fillOval(0, 0, 16, 16);
        stackCards.last().add(content.getApi().getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void addImg(int[][] _img) {
        stackCards.last().add(content.getApi().getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(content.getApi().getImageFactory(), _img)));
    }

    @Override
    public void addImgCts(int[][] _img, String _tip) {
        AbsPreparedLabel lab_ = content.getApi().getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(content.getApi().getImageFactory(), _img));
        lab_.setToolTipText(_tip);
        stackCards.last().add(lab_, cts());
        incColIndex();
    }

    private AbsGridConstraints cts() {
        AbsGridConstraints cts_ = content.getApi().getCompoFactory().newGridCts();
        if (colIndex() +1 == colCount()) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }

    @Override
    public void nextPart() {
        breakLine();
        super.nextPart();
    }
    public void breakLine() {
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }
    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return content.getMetaSearchableContents();
    }
    public IdMap<AbsCustComponent, AbsCustComponent> getParentsCards() {
        return parentsCards;
    }
    public CustList<AbsPanel> getStackCards() {
        return stackCards;
    }

    public IdMap<MetaSearchableContent, AbsTextPane> getRefsSearch() {
        return content.getRefsSearch();
    }

    public IdMap<AbsTextPane,AbsAttrSet> getFonts() {
        return content.getFonts();
    }
//    public StringMap<AbsTextPane> getRefsSearchDir() {
//        return content.getRefsSearchDir();
//    }

    public AbsScrollPane getScrollPane() {
        return content.getScrollPane();
    }

    public void setScrollPane(AbsScrollPane _s) {
        this.content.setScrollPane(_s);
    }

//    public AbsCommonFrame getFrame() {
//        return content.getFrame();
//    }

    public void setFrame(AbsCommonFrame _f) {
        this.content.setFrame(_f);
    }
}
