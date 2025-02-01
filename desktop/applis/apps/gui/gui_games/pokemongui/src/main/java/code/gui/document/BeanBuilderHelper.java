package code.gui.document;

import aiki.beans.DifficultyCommon;
import aiki.beans.StringMapObject;
import code.formathtml.render.MetaSearchableContent;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.*;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.StringUtil;

public final class BeanBuilderHelper {
    private AbsScrollPane scrollPane;
    private final AbstractProgramInfos api;
    private final FindBeanEvent finder;
    private final IdMap<MetaSearchableContent,AbsTextPane> refsSearch = new IdMap<MetaSearchableContent,AbsTextPane>();
    private final IdList<MetaSearchableContent> metaSearchableContents = new IdList<MetaSearchableContent>();
    private final CustList<AbsPanel> stack = new CustList<AbsPanel>();
    private final IdList<AbsTextPane> anchors = new IdList<AbsTextPane>();
    private StringMap<AbsBeanRender> renders = new StringMap<AbsBeanRender>();
    private int partGroup;
    private int rowGroup;
    private AbsCommonFrame frame;
    private final IdMap<AbsCustComponent,AbsCustComponent> parents = new IdMap<AbsCustComponent,AbsCustComponent>();
    private int colCount;
    private int colIndex;
    public BeanBuilderHelper(AbstractProgramInfos _a, FindBeanEvent _f) {
        this.api = _a;
        this.finder = _f;
    }

    public void clearAnchors() {
        anchors.clear();
    }

    public void initLine() {
        stack.add(api.getCompoFactory().newLineBox());
    }

    public void initPage() {
        stack.add(api.getCompoFactory().newPageBox());
    }

    public void initGrid() {
        stack.add(api.getCompoFactory().newGrid());
    }

    public void feedParentsCts() {
        AbsPanel current_ = stack.get(stack.size()-2);
        AbsPanel ch_ = stack.last();
        current_.add(ch_, cts());
        getParents().addEntry(ch_, current_);
        stack.removeQuicklyLast();
        incColIndex();
    }
    public void setBackground(int _color) {
        stack.last().setBackground(_color);
    }
    public void setTitledBorder(String _title){
        stack.last().setTitledBorder(_title);
    }
    public void feedParents() {
        AbsPanel current_ = stack.get(stack.size()-2);
        AbsPanel ch_ = stack.last();
        current_.add(ch_);
        getParents().addEntry(ch_, current_);
        stack.removeQuicklyLast();
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

    public void formatMessageAnc(BeanRenderWithAppName _with,IntBeanAction _e,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        AbsTextPane tx_ = formatMessageDir(txt_);
        anchor(tx_);
        tx_.addMouseListener(new BeanAnchorEvent(this,_e));
    }

    public AbsTextPane formatMessage(BeanRenderWithAppName _with,String _file, String _key, String... _values) {
        String txt_ = formatMessageRend(_with,_file, _key, _values);
        return formatMessageDir(txt_);
    }

    public String formatMessageRend(BeanRenderWithAppName _with,String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(file(_with, _file).getMapping().getVal(_key), _values);
    }

    public TranslationsFile file(BeanRenderWithAppName _with, String _file) {
        return files(_with).getVal(_file);
    }

    public  StringMap<TranslationsFile> files(BeanRenderWithAppName _with) {
        return files(api, _with.appName());
    }
    public static StringMap<TranslationsFile> files(AbstractProgramInfos _api, String _name) {
        return _api.currentLg().getMapping().getVal(_name).getMapping();
    }
    public AbsTextPane formatMessageDir(String _txt) {
        AbsTextPane ch_ = message(_txt);
        feedParent(ch_);
        hierarchy(_txt, ch_);
        return ch_;
    }

    public void formatMessageDirCts(String _txt, int _bg) {
        AbsTextPane txt_ = formatMessageDirCts(_txt);
        txt_.setBackground(_bg);
    }
    public AbsTextPane formatMessageDirCts(String _txt) {
        AbsTextPane ch_ = message(_txt);
        ch_.setLineBorder(GuiConstants.BLACK);
        feedParentCts(ch_);
        hierarchy(_txt, ch_);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        return ch_;
    }

    public AbsTextPane message(String _txt) {
        AbsTextPane tp_ = api.getCompoFactory().newTextPane();
        tp_.setBackground(GuiConstants.WHITE);
        tp_.setForeground(GuiConstants.BLACK);
        tp_.setText(_txt);
        tp_.setEditable(false);
        return tp_;
    }
    public void breakLine() {
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
    }
    public void paintMetaLabelDisk() {
        AbstractImage img_ = api.getImageFactory().newImageArgb(16, 16);
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, 16, 16);
        img_.setColor(GuiConstants.BLACK);
        img_.fillOval(0, 0, 16, 16);
        stack.last().add(api.getCompoFactory().newPreparedLabel(img_));
        img_.dispose();
    }

    public void addImg(int[][] _img) {
        stack.last().add(api.getCompoFactory().newPreparedLabel(ConverterGraphicBufferedImage.decodeToImage(api.getImageFactory(), _img)));
    }

    public void nextPart() {
        partGroup++;
        rowGroup = 0;
    }

    public void hierarchy(String _txt, AbsTextPane _ch) {
        MetaSearchableContent s_ = new MetaSearchableContent(_txt, partGroup, rowGroup);
        metaSearchableContents.add(s_);
        getRefsSearch().addEntry(s_,_ch);
    }

    public CustList<AbsPanel> getStack() {
        return stack;
    }

    public void build(String _dest, StringMapObject _form) {
        AbsBeanRender target_ = getRenders().getVal(_dest);
        target_.build(target_.getFacade(), _form, this);
        getFinder().setFinding(this);
        getScrollPane().setViewportView(stack.last());
        getScrollPane().validate();
        getFrame().pack();
        stack.removeQuicklyLast();
    }

    public FindBeanEvent getFinder() {
        return finder;
    }


    public void formatMessageDirAnc(String _txt, BeanAnchorToFighterEvent _b) {
        AbsTextPane tx_ = formatMessageDir(_txt);
        anchor(tx_);
        tx_.addMouseListener(new BeanAnchorEvent(this,_b));
    }

    public void anchor(AbsTextPane _tx) {
        anchor(api,_tx);
        anchors.add(_tx);
    }

    private AbsGridConstraints cts() {
        AbsGridConstraints cts_ = api.getCompoFactory().newGridCts();
        if (colIndex +1 == getColCount()) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        return cts_;
    }

    private void anchor(AbstractProgramInfos _api, AbsTextPane _tx) {
        AbsAttrSet att_ = _api.getCompoFactory().newAttrSet();
        att_.addUnderline(true);
        att_.addForeground(GuiConstants.BLUE);
        _tx.setCharacterAttributes(0, _tx.getText().length(), att_, false);
    }
    public void displayDiff(DifficultyBeanForm _form,AbsBeanRender _rend, DifficultyCommon _common, String _file) {
        _form.displayDiff(_rend,_common,api,_file);
    }

    public void incColIndex() {
        colIndex=(colIndex + 1) % colCount;
    }

    public IdList<AbsTextPane> getAnchors() {
        return anchors;
    }

    public StringMap<AbsBeanRender> getRenders() {
        return renders;
    }

    public void setRenders(StringMap<AbsBeanRender> _r) {
        this.renders = _r;
    }

    public AbsButton button(String _txt) {
        return api.getCompoFactory().newPlainButton(_txt);
    }
    public IdList<MetaSearchableContent> getMetaSearchableContents() {
        return metaSearchableContents;
    }

    public IdMap<AbsCustComponent, AbsCustComponent> getParents() {
        return parents;
    }

    public IdMap<MetaSearchableContent, AbsTextPane> getRefsSearch() {
        return refsSearch;
    }

    public int getPartGroup() {
        return partGroup;
    }

    public void setPartGroup(int _p) {
        this.partGroup = _p;
    }

    public int getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(int _r) {
        this.rowGroup = _r;
    }

    public AbsScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(AbsScrollPane _s) {
        this.scrollPane = _s;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public void setFrame(AbsCommonFrame _f) {
        this.frame = _f;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int _c) {
        this.colCount = _c;
    }
}
