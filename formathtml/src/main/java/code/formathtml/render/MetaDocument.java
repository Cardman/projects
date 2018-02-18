package code.formathtml.render;

import code.expressionlanguage.stds.LgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.Node;
import code.sml.Text;
import code.util.BooleanList;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class MetaDocument {

    private final MetaBlock root;

    private MetaDocument(Document _document) {
        ElementList style_ = _document.getElementsByTagName("style");
        StringMap<String> classesCssStyles_ = new StringMap<String>();
        StringMap<String> tagsCssStyles_ = new StringMap<String>();
        if (!style_.isEmpty()) {
            //process style parse
        }
        Element body_ = _document.getElementsByTagName("body").first();
        root = new MetaBlock(null);
        StringList stacks_ = new StringList();
        MetaLine mainLine_ = new MetaLine(root);
        root.appendChild(mainLine_);
        MetaContainer currentParent_ = mainLine_;
        Node current_ = body_;
        CustList<MetaContainer> containers_ = new CustList<MetaContainer>();
        CustList<MetaTable> tables_ = new CustList<MetaTable>();
        int partGroup_ = 0;
        int rowGroup_ = 0;
        Numbers<Integer> lis_ = new Numbers<Integer>();
        BooleanList ordered_ = new BooleanList();
        while (true) {
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                String text_ = txt_.getTextContent().trim();
                if (!text_.isEmpty()) {
                    String title_ = MetaComponent.EMPTY_STRING;
                    Element anchor_ = null;
                    Element par_ = current_.getParentNode();
                    int bold_ = 0;
                    int italic_ = 0;
                    MetaStyle styleLoc_ = new MetaStyle();
                    while (par_ != null) {
                        String tagName_ = par_.getTagName();
                        if (!par_.getAttribute("title").isEmpty() && title_.isEmpty()) {
                            title_ = par_.getAttribute("title");
                        }
                        if (StringList.quickEq(tagName_, "a") && anchor_ == null) {
                            anchor_ = par_;
                        }
                        if (StringList.quickEq(tagName_, "b") && bold_ == 0) {
                            bold_ = 1;
                        }
                        if (StringList.quickEq(tagName_, "i") && italic_ == 0) {
                            italic_ = 2;
                        }
                        par_ = par_.getParentNode();
                    }
                    styleLoc_.setBold(bold_);
                    styleLoc_.setItalic(italic_);
                    MetaSearchableLabel label_ = new MetaSearchableLabel(currentParent_, text_, title_, anchor_, partGroup_, rowGroup_);
                    label_.setStyle(styleLoc_);
                    currentParent_.appendChild(label_);
                }
            }
            boolean skipChildrenBuild_ = false;
            String tagName_ = MetaComponent.EMPTY_STRING;
            if (current_ instanceof Element) {
                Element elt_ = (Element) current_;
                tagName_ = elt_.getTagName();
                boolean newLine_ = false;
                if (StringList.quickEq(tagName_, "br")) {
                    skipChildrenBuild_ = true;
                    newLine_ = true;
                    rowGroup_++;
                }
                if (StringList.quickEq(tagName_, "hr")) {
                    skipChildrenBuild_ = true;
                    newLine_ = true;
                    rowGroup_ = 0;
                    partGroup_++;
                }
                MetaContainer curPar_ = currentParent_.getParent();
                boolean li_ = false;
                if (!stacks_.isEmpty()) {
                    if (StringList.quickEq(stacks_.last(), "li")) {
                        li_ = true;
                    }
                }
                if (newLine_) {
                    if (StringList.quickEq(tagName_, "hr")) {
                        MetaSeparator sep_ = new MetaSeparator(curPar_);
                        curPar_.appendChild(sep_);
                    }
                    MetaContainer line_ = new MetaLine(curPar_);
                    if (li_) {
                        MetaIndentNbLabel ind_ = new MetaIndentNbLabel(line_);
                        line_.appendChild(ind_);
                    }
                    curPar_.appendChild(line_);
                    currentParent_ = line_;
                }
                if (StringList.quickEq(tagName_, "img")) {
                    String title_ = MetaComponent.EMPTY_STRING;
                    Element anchor_ = null;
                    Element par_ = current_.getParentNode();
                    MetaStyle styleLoc_ = new MetaStyle();
                    while (par_ != null) {
                        String tagNamePar_ = par_.getTagName();
                        if (!par_.getAttribute("title").isEmpty() && title_.isEmpty()) {
                            title_ = par_.getAttribute("title");
                        }
                        if (StringList.quickEq(tagNamePar_, "a") && anchor_ == null) {
                            anchor_ = par_;
                        }
                        par_ = par_.getParentNode();
                    }
                    if (elt_.getAttribute("src").contains(";")) {
                        Integer delay_ = LgNames.parseInt(elt_.getAttribute("delay"));
                        MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent_, StringList.splitStrings(elt_.getAttribute("src"), ";"), title_, delay_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent_.appendChild(imgs_);
                    } else {
                        MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent_, elt_.getAttribute("src"), title_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent_.appendChild(imgs_);
                    }
                    skipChildrenBuild_ = true;
                    rowGroup_ = 0;
                    partGroup_++;
                }
                if (StringList.quickEq(tagName_, "select")) {
                    skipChildrenBuild_ = true;
                    rowGroup_ = 0;
                    partGroup_++;
                    if (elt_.hasAttribute("multiple")) {
                        Numbers<Integer> selected_ = new Numbers<Integer>();
                        NatTreeMap<String, String> elts_ = new NatTreeMap<String, String>();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute("selected")) {
                                selected_.add(i_);
                            }
                            elts_.put(c.getAttribute("value"), c.getTextContent());
                            i_++;
                        }
                        Integer vis_ = LgNames.parseInt(elt_.getAttribute("rows"));
                        if (vis_ == null) {
                            vis_ = 1;
                        }
                        MetaInput input_ = new MetaComboList(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), elts_, selected_, vis_);
                        currentParent_.appendChild(input_);
                    } else {
                        int selected_ = 0;
                        NatTreeMap<String, String> elts_ = new NatTreeMap<String, String>();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute("selected")) {
                                selected_ = i_;
                            }
                            elts_.put(c.getAttribute("value"), c.getTextContent());
                            i_++;
                        }
                        MetaInput input_ = new MetaComboBox(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), elts_, selected_);
                        currentParent_.appendChild(input_);
                    }
                }
                if (StringList.quickEq(tagName_, "input")) {
                    skipChildrenBuild_ = true;
                    String type_ = elt_.getAttribute("type");
                    if (StringList.quickEq(type_, "text")) {
                        Integer cols_ = LgNames.parseInt(elt_.getAttribute("cols"));
                        if (cols_ == null) {
                            cols_ = 32;
                        }
                        MetaInput input_ = new MetaTextField(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), cols_, elt_.getAttribute("value"));
                        currentParent_.appendChild(input_);
                    } else if (StringList.quickEq(type_, "checkbox")) {
                        MetaInput input_ = new MetaCheckedBox(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), elt_.hasAttribute("checked"));
                        currentParent_.appendChild(input_);
                    } else if (StringList.quickEq(type_, "radio")) {
                        MetaInput input_ = new MetaRadioButton(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), elt_.hasAttribute("checked"), elt_.getAttribute("value"));
                        currentParent_.appendChild(input_);
                    } else {
                        //button
                        Element form_ = null;
                        Element par_ = current_.getParentNode();
                        MetaStyle styleLoc_ = new MetaStyle();
                        while (par_ != null) {
                            String tagNamePar_ = par_.getTagName();
                            if (StringList.quickEq(tagNamePar_, "form") && form_ == null) {
                                form_ = par_;
                            }
                            par_ = par_.getParentNode();
                        }
                        MetaInput button_ = new MetaButton(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), form_, elt_.getAttribute("value"));
                        button_.setStyle(styleLoc_);
                        currentParent_.appendChild(button_);
                    }
                    rowGroup_ = 0;
                    partGroup_++;
                }
                if (StringList.quickEq(tagName_, "textarea")) {
                    skipChildrenBuild_ = true;
                    rowGroup_ = 0;
                    partGroup_++;
                    Integer rows_ = LgNames.parseInt(elt_.getAttribute("rows"));
                    if (rows_ == null) {
                        rows_ = 32;
                    }
                    Integer cols_ = LgNames.parseInt(elt_.getAttribute("cols"));
                    if (cols_ == null) {
                        cols_ = 32;
                    }
                    MetaInput input_ = new MetaTextArea(currentParent_, LgNames.parseInt(elt_.getAttribute("n-i")), cols_, rows_, elt_.getTextContent());
                    currentParent_.appendChild(input_);
                }
                if (StringList.quickEq(tagName_, "p")) {
                    MetaContainer bl_ = new MetaParagraph(curPar_);
                    MetaContainer line_ = new MetaLine(bl_);
                    bl_.appendChild(line_);
                  //indent
                    if (li_) {
                        MetaLabel ind_ = new MetaIndentNbLabel(curPar_);
                        curPar_.appendChild(ind_);
                    }
                    curPar_.appendChild(bl_);
                    containers_.add(bl_);
                    currentParent_ = line_;
                }
                if (StringList.quickEq(tagName_, "ul") || StringList.quickEq(tagName_, "ol")) {
                    MetaContainer line_ = new MetaLine(curPar_);
                    MetaContainer bl_;
                    if (StringList.quickEq(tagName_, "ul")) {
                        bl_ = new MetaUnorderedList(line_);
                    } else {
                        bl_ = new MetaOrderedList(line_);
                    }
                    //indent
                    if (li_) {
                        MetaLabel ind_ = new MetaIndentNbLabel(line_);
                        line_.appendChild(ind_);
                    }
                    MetaLabel ind_ = new MetaIndentLabel(line_,1);
                    line_.appendChild(ind_);
                    line_.appendChild(bl_);
                    curPar_.appendChild(line_);
                    containers_.add(curPar_);
                    containers_.add(bl_);
                    lis_.add(1);
                    ordered_.add(StringList.quickEq(tagName_, "ol"));
                    currentParent_ = bl_;
                }
                if (StringList.quickEq(tagName_, "li")) {
                    rowGroup_ = 0;
                    partGroup_++;
                    MetaContainer list_ = containers_.last();
                    MetaContainer bl_ = new MetaListItem(list_);
                    MetaContainer line_ = new MetaLine(bl_);
                    MetaLabel nb_;
                    if (ordered_.last()) {
                        nb_ = new MetaNumberedLabel(line_, lis_.last(), MetaNumberBase.NUMBER);
                    } else {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                    }
                    line_.appendChild(nb_);
                    lis_.setLast(lis_.last()+1);
                    bl_.appendChild(line_);
                    list_.appendChild(bl_);
                    currentParent_ = line_;
                }
                if (StringList.quickEq(tagName_, "table")) {
                    MetaContainer line_ = new MetaLine(curPar_);
                    MetaTable bl_ = new MetaTable(line_);
                    //indent
                    if (li_) {
                        MetaIndentNbLabel ind_ = new MetaIndentNbLabel(line_);
                        line_.appendChild(ind_);
                    }
                    line_.appendChild(bl_);
                    curPar_.appendChild(line_);
                    containers_.add(curPar_);
                    containers_.add(bl_);
                    currentParent_ = bl_;
                    tables_.add(bl_);
                }
                if (StringList.quickEq(tagName_, "caption")) {
                    rowGroup_ = 0;
                    partGroup_++;
                    MetaTable table_ = tables_.last();
                    MetaContainer line_ = new MetaCaption(table_);
                    table_.addRemainder();
                    table_.appendChild(line_);
                    currentParent_ = line_;
                }
                if (StringList.quickEq(tagName_, "td") || StringList.quickEq(tagName_, "th")) {
                    rowGroup_ = 0;
                    partGroup_++;
                    MetaTable table_ = tables_.last();
                    Integer rows_ = LgNames.parseInt(elt_.getAttribute("rowspan"));
                    if (rows_ == null) {
                        rows_ = 1;
                    }
                    Integer cols_ = LgNames.parseInt(elt_.getAttribute("colspan"));
                    if (cols_ == null) {
                        cols_ = 1;
                    }
                    MetaContainer bl_ = new MetaCell(table_, rows_, cols_);
                    MetaContainer line_ = new MetaLine(bl_);
                    bl_.appendChild(line_);
                    Node nextSib_ = elt_.getNextSibling();
                    if (!(nextSib_ instanceof Element) || !StringList.quickEq(((Element)nextSib_).getTagName(), tagName_)) {
                        table_.addRemainder();
                    }
                    table_.appendChild(bl_);
                    currentParent_ = line_;
                }
                if (StringList.quickEq(tagName_, "map")) {
                    Integer width_ = LgNames.parseInt(elt_.getAttribute("width"));
                    if (width_ == null) {
                        width_ = 1;
                    }
                    MetaContainer line_ = new MetaLine(curPar_);
                    if (li_) {
                        MetaIndentNbLabel ind_ = new MetaIndentNbLabel(line_);
                        line_.appendChild(ind_);
                    }
                    MetaContainer map_ = new MetaImageMap(line_, width_);
                    line_.appendChild(map_);
                    curPar_.appendChild(line_);
                    containers_.add(curPar_);
                    containers_.add(map_);
                    currentParent_ = map_;
                }
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null && !skipChildrenBuild_) {
                stacks_.add(tagName_);
                current_ = next_;
                continue;
            }
            next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            Element par_ = current_.getParentNode();
            String lastTag_ = par_.getTagName();
            MetaContainer nextParent_ = unstack(containers_, tables_, lastTag_);
            if (nextParent_ != null) {
                currentParent_ = nextParent_;
            }
            if (StringList.quickEq(lastTag_, "table")) {
                rowGroup_ = 0;
                partGroup_++;
            }
            if (StringList.quickEq(lastTag_, "ul") || StringList.quickEq(lastTag_, "ol")) {
                rowGroup_ = 0;
                partGroup_++;
                lis_.removeLast();
                ordered_.removeLast();
            }
            if (StringList.quickEq(lastTag_, "p")) {
                rowGroup_ = 0;
                partGroup_++;
            }
            if (StringList.quickEq(lastTag_, "map")) {
                rowGroup_ = 0;
                partGroup_++;
            }
            stacks_.removeLast();
            boolean li_ = false;
            if (!stacks_.isEmpty()) {
                if (StringList.quickEq(stacks_.last(), "li")) {
                    li_ = true;
                }
            }
            if (li_ && nextParent_ != null) {
                MetaContainer curPar_ = currentParent_;
                MetaIndentNbLabel indent_ = new MetaIndentNbLabel(curPar_);
                curPar_.appendChild(indent_);
            }
            if (par_ == body_) {
                break;
            }
            next_ = par_.getNextSibling();
            while (next_ == null) {
                Element grandPar_ = par_.getParentNode();
                lastTag_ = grandPar_.getTagName();
                nextParent_ = unstack(containers_, tables_, lastTag_);
                if (nextParent_ != null) {
                    currentParent_ = nextParent_;
                }
                if (StringList.quickEq(lastTag_, "table")) {
                    rowGroup_ = 0;
                    partGroup_++;
                }
                if (StringList.quickEq(lastTag_, "ul") || StringList.quickEq(lastTag_, "ol")) {
                    rowGroup_ = 0;
                    partGroup_++;
                    lis_.removeLast();
                    ordered_.removeLast();
                }
                if (StringList.quickEq(lastTag_, "p")) {
                    rowGroup_ = 0;
                    partGroup_++;
                }
                if (StringList.quickEq(lastTag_, "map")) {
                    rowGroup_ = 0;
                    partGroup_++;
                }
                stacks_.removeLast();
                li_ = false;
                if (!stacks_.isEmpty()) {
                    if (StringList.quickEq(stacks_.last(), "li")) {
                        li_ = true;
                    }
                }
                if (li_ && nextParent_ != null) {
                    MetaContainer curPar_ = currentParent_;
                    MetaIndentNbLabel indent_ = new MetaIndentNbLabel(curPar_);
                    curPar_.appendChild(indent_);
                }
                if (grandPar_ == body_) {
                    break;
                }
                next_ = grandPar_.getNextSibling();
                par_ = grandPar_;
            }
            if (next_ == null) {
                break;
            }
            current_ = next_;
        }
    }
    private static MetaContainer unstack(CustList<MetaContainer> _containers, CustList<MetaTable> _tables, String _last) {
        if (StringList.quickEq(_last, "ul") || StringList.quickEq(_last, "ol")) {
            _containers.removeLast();
            MetaContainer last_ = _containers.last();
            _containers.removeLast();
            MetaContainer line_ = new MetaLine(last_);
            last_.appendChild(line_);
            return line_;
        }
        if (StringList.quickEq(_last, "table")) {
            _containers.removeLast();
            MetaContainer last_ = _containers.last();
            _containers.removeLast();
            MetaContainer line_ = new MetaLine(last_);
            last_.appendChild(line_);
            _tables.removeLast();
            return line_;
        }
        if (StringList.quickEq(_last, "p")) {
            MetaContainer last_ = _containers.last();
            _containers.removeLast();
            MetaContainer line_ = new MetaLine(last_);
            last_.appendChild(line_);
            _tables.removeLast();
            return line_;
        }
        if (StringList.quickEq(_last, "map")) {
            _containers.removeLast();
            MetaContainer last_ = _containers.last();
            _containers.removeLast();
            MetaContainer line_ = new MetaLine(last_);
            last_.appendChild(line_);
            _tables.removeLast();
            return line_;
        }
        return null;
    }
    public MetaBlock getRoot() {
        return root;
    }
    public static MetaDocument newInstance(Document _document) {
        return new MetaDocument(_document);
    }
}
