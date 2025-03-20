package cards.gui.dialogs;

import code.bean.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.StringUtil;

public final class BeanBuilderHelperCards extends IntBeanBuilderHelperCommon {
    private final AbstractProgramInfos api;
    private final CustList<AbsPanel> stackCards = new CustList<AbsPanel>();
    private final IdMap<AbsCustComponent,AbsCustComponent> parentsCards = new IdMap<AbsCustComponent,AbsCustComponent>();
    private final Ints colours = new Ints();
    public BeanBuilderHelperCards(AbstractProgramInfos _a) {
        this.api = _a;
        colours.add(GuiConstants.BLACK);
        colours.add(GuiConstants.BLUE);
    }

    @Override
    public void initLine() {
        super.initLine();
        stackCards.add(api.getCompoFactory().newLineBox());
        setBackgroundBody();
    }

    @Override
    public void initPage() {
        super.initPage();
        stackCards.add(api.getCompoFactory().newPageBox());
        setBackgroundBody();
    }

    @Override
    public void initGrid() {
        super.initGrid();
        stackCards.add(api.getCompoFactory().newGrid());
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
        return api.getLanguage();
    }

    public void formatMessageDir(String _txt) {
        AbsTextPane ch_ = messageCards(_txt);
        feedParent(ch_);
    }

    public void formatMessageDirCtsHeader(String _txt) {
        AbsTextPane txt_ = messageCards(_txt);
        txt_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(txt_);
        txt_.setBackground(GuiConstants.YELLOW);
    }
    public void formatMessageDirCts(String _txt) {
        AbsTextPane ch_ = messageCards(_txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(ch_);
    }


    public AbsTextPane messageCards(String _txt) {
        AbsTextPane tp_ = api.getCompoFactory().newTextPane();
        tp_.setBackground(GuiConstants.WHITE);
        tp_.setForeground(colours.get(getHeader()));
        tp_.setText(StringUtil.nullToEmpty(_txt));
//        api.getThreadFactory().newStartedThread(new SetTextThread(tp_, StringUtil.nullToEmpty(_txt))).join();
        tp_.setEditable(false);
        return tp_;
    }

    @Override
    public void breakNext() {
        nextPart();
    }
    @Override
    public void paintIndent() {
        AbstractImage img_ = api.getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        stackCards.last().add(api.getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    @Override
    public void paintNb(int _nb) {
        AbstractImage img_ = api.getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.drawString(Long.toString(_nb),0,16);
        stackCards.last().add(api.getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void paintMetaLabelDisk() {
        AbstractImage img_ = api.getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.fillOval(0, 0, 16, 16);
        stackCards.last().add(api.getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void addImg(int[][] _img) {
        stackCards.last().add(api.getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(api.getImageFactory(), _img)));
    }

    @Override
    public void addImgCts(int[][] _img, String _tip) {
        AbsPreparedLabel lab_ = api.getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(api.getImageFactory(), _img));
        lab_.setToolTipText(_tip);
        stackCards.last().add(lab_, cts());
        incColIndex();
    }

    private AbsGridConstraints cts() {
        AbsGridConstraints cts_ = api.getCompoFactory().newGridCts();
        if (colIndex() +1 == colCount()) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }

    public IdMap<AbsCustComponent, AbsCustComponent> getParentsCards() {
        return parentsCards;
    }
    public CustList<AbsPanel> getStackCards() {
        return stackCards;
    }

}
