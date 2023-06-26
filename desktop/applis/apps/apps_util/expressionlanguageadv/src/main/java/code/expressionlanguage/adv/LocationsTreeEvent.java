package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class LocationsTreeEvent implements AbsShortListTree {
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";
    public static final String SPAN = "span";
    public static final String HTML = "html";
    public static final String STYLE = "style";
    public static final String COLOR = "color";
    public static final String BACKGROUND_COLOR = "background-" + COLOR;
    private final AnalyzedPageEl page;
    private final WindowWithTreeImpl window;
    private final MetaCaller root;

    public LocationsTreeEvent(AnalyzedPageEl _p,WindowWithTreeImpl _w,MetaCaller _r) {
        this.page = _p;
        this.window = _w;
        this.root = _r;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        AbstractMutableTreeNodeCore e_ = MutableTreeNodeUtil.simular(root, (AbstractMutableTreeNode) _node);
        if (!(e_ instanceof MetaCaller)) {
            return;
        }
        CustList<FileBlockIndex> locs_ = ((MetaCaller)e_).getNumber();
        int len_ = locs_.size();
        AbsCompoFactory comp_ = window.getCommonFrame().getFrames().getCompoFactory();
        AbsPanel pa_ = comp_.newPageBox();
        for (int i = 0; i < len_; i++) {
            FileBlockIndex elt_ = locs_.get(i);
            String name_ = FileBlock.name(elt_.getFile());
            String content_ = resource(elt_.getFile());
            int lenContent_ = content_.length();
            int locIndex_ = elt_.getIndex();
            if (locIndex_ >= lenContent_) {
                continue;
            }
            int min_ = NumberUtil.max(0, content_.lastIndexOf('\n', locIndex_));
            int endLine_ = content_.indexOf('\n', locIndex_);
            if (endLine_ < 0) {
                endLine_ = lenContent_ - 1;
            }
            int max_ = NumberUtil.min(lenContent_, endLine_ +1);
            int until_ = ElResolver.incrAfterWord(locIndex_,content_);
            String before_ = transform(content_.substring(min_, locIndex_));
            String occurrence_ = transform(content_.substring(locIndex_,until_));
            String after_ = transform(content_.substring(until_,max_));
            String display_ = "<" + HTML + "><" + SPAN + " " + STYLE + "='" + BACKGROUND_COLOR + ":#000000;'>";
            display_ += "<" + SPAN + " " + STYLE + "='" + COLOR + ":#ff0000;'>" + locIndex_ + "</" + SPAN + ">";
            display_ += "&#160;";
            display_ += "<" + SPAN + " " + STYLE + "='" + COLOR + ":#ffffff;'>" +before_+ "</" + SPAN + ">";
            display_ += "<" + SPAN + " " + STYLE + "='" + COLOR + ":#ff0000;'>" +occurrence_+ "</" + SPAN + ">";
            display_ += "<" + SPAN + " " + STYLE + "='" + COLOR + ":#ffffff;'>" +after_+ "</" + SPAN + ">";
            display_ += "</" + SPAN + ">";
            display_ += "</" + HTML + ">";
            RowSrcLocation r_ = new RowSrcLocation(null, display_, name_, locIndex_);
            AbsPlainButton button_ = comp_.newPlainButton(display_);
            button_.disabledRichText(false);
            button_.addActionListener(new GoToDefinitionEvent(page,r_,window));
            pa_.add(button_);
        }
        window.getPanelSymbolsLocationScroll().setViewportView(pa_);
        GuiBaseUtil.recalculate(window.getPanelSymbolsLocationScroll());
    }

    static String resource(FileBlock _res) {
        if (_res == null) {
            return "";
        }
        return StringUtil.nullToEmpty(_res.getContent());
    }

    static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            str_.append(escapeNum(c));
        }
        return str_.toString();
    }
    private static String escapeNum(char _ch) {
        return BEGIN_ENCODE +(int)_ch+ END_ENCODE;
    }
}
