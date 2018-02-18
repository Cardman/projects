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

    private MetaBlock root;
    private StringList stacks;

    private MetaContainer currentParent;
    private int partGroup = 0;
    private int rowGroup = 0;
    private CustList<MetaContainer> containers;
    private CustList<MetaTable> tables;
    private Numbers<Integer> lis;
    private BooleanList ordered;

    private MetaDocument(Document _document) {
        ElementList style_ = _document.getElementsByTagName("style");
        StringMap<String> classesCssStyles_ = new StringMap<String>();
        StringMap<String> tagsCssStyles_ = new StringMap<String>();
        if (!style_.isEmpty()) {
            //process style parse
        }
        Element body_ = _document.getElementsByTagName("body").first();
        root = new MetaBlock(null);
        stacks = new StringList();
        MetaLine mainLine_ = new MetaLine(root);
        root.appendChild(mainLine_);
        currentParent = mainLine_;
        Node current_ = body_;
        containers = new CustList<MetaContainer>();
        tables = new CustList<MetaTable>();
        partGroup = 0;
        rowGroup = 0;
        lis = new Numbers<Integer>();
        ordered = new BooleanList();
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
                    MetaSearchableLabel label_;
                    if (anchor_ == null) {
                        label_ = new MetaPlainLabel(currentParent, text_, title_, partGroup, rowGroup);
                    } else {
                        label_ = new MetaAnchorLabel(currentParent, text_, title_, anchor_, partGroup, rowGroup);
                    }
                    label_.setStyle(styleLoc_);
                    currentParent.appendChild(label_);
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
                    rowGroup++;
                }
                if (StringList.quickEq(tagName_, "hr")) {
                    skipChildrenBuild_ = true;
                    newLine_ = true;
                    rowGroup = 0;
                    partGroup++;
                }
                MetaContainer curPar_ = currentParent.getParent();
                boolean li_ = false;
                if (!stacks.isEmpty()) {
                    if (StringList.quickEq(stacks.last(), "li")) {
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
                    currentParent = line_;
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
                        MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent, StringList.splitStrings(elt_.getAttribute("src"), ";"), title_, delay_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent.appendChild(imgs_);
                    } else {
                        MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, elt_.getAttribute("src"), title_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent.appendChild(imgs_);
                    }
                    skipChildrenBuild_ = true;
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringList.quickEq(tagName_, "select")) {
                    skipChildrenBuild_ = true;
                    rowGroup = 0;
                    partGroup++;
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
                        MetaInput input_ = new MetaComboList(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), elts_, selected_, vis_);
                        currentParent.appendChild(input_);
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
                        MetaInput input_ = new MetaComboBox(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), elts_, selected_);
                        currentParent.appendChild(input_);
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
                        MetaInput input_ = new MetaTextField(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), cols_, elt_.getAttribute("value"));
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "checkbox")) {
                        MetaInput input_ = new MetaCheckedBox(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), elt_.hasAttribute("checked"));
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "radio")) {
                        MetaInput input_ = new MetaRadioButton(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), elt_.hasAttribute("checked"), elt_.getAttribute("value"));
                        currentParent.appendChild(input_);
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
                        MetaInput button_ = new MetaButton(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), form_, elt_.getAttribute("value"));
                        button_.setStyle(styleLoc_);
                        currentParent.appendChild(button_);
                    }
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringList.quickEq(tagName_, "textarea")) {
                    skipChildrenBuild_ = true;
                    rowGroup = 0;
                    partGroup++;
                    Integer rows_ = LgNames.parseInt(elt_.getAttribute("rows"));
                    if (rows_ == null) {
                        rows_ = 32;
                    }
                    Integer cols_ = LgNames.parseInt(elt_.getAttribute("cols"));
                    if (cols_ == null) {
                        cols_ = 32;
                    }
                    MetaInput input_ = new MetaTextArea(currentParent, LgNames.parseInt(elt_.getAttribute("n-i")), cols_, rows_, elt_.getTextContent());
                    currentParent.appendChild(input_);
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
                    containers.add(bl_);
                    currentParent = line_;
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
                    containers.add(curPar_);
                    containers.add(bl_);
                    lis.add(1);
                    ordered.add(StringList.quickEq(tagName_, "ol"));
                    currentParent = bl_;
                }
                if (StringList.quickEq(tagName_, "li")) {
                    rowGroup = 0;
                    partGroup++;
                    MetaContainer list_ = containers.last();
                    MetaContainer bl_ = new MetaListItem(list_);
                    MetaContainer line_ = new MetaLine(bl_);
                    MetaLabel nb_;
                    if (ordered.last()) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                    } else {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                    }
                    line_.appendChild(nb_);
                    lis.setLast(lis.last()+1);
                    bl_.appendChild(line_);
                    list_.appendChild(bl_);
                    currentParent = line_;
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
                    containers.add(curPar_);
                    containers.add(bl_);
                    currentParent = bl_;
                    tables.add(bl_);
                }
                if (StringList.quickEq(tagName_, "caption")) {
                    rowGroup = 0;
                    partGroup++;
                    MetaTable table_ = tables.last();
                    MetaContainer line_ = new MetaCaption(table_);
                    table_.addRemainder();
                    table_.appendChild(line_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "td") || StringList.quickEq(tagName_, "th")) {
                    rowGroup = 0;
                    partGroup++;
                    MetaTable table_ = tables.last();
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
                    currentParent = line_;
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
                    containers.add(curPar_);
                    containers.add(map_);
                    currentParent = map_;
                }
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null && !skipChildrenBuild_) {
                stacks.add(tagName_);
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
            unstack(lastTag_);
            if (par_ == body_) {
                break;
            }
            next_ = par_.getNextSibling();
            while (next_ == null) {
                Element grandPar_ = par_.getParentNode();
                lastTag_ = grandPar_.getTagName();
                unstack(lastTag_);
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
    private void unstack(String _last) {
        MetaContainer line_ = null;
        if (StringList.quickEq(_last, "ul") || StringList.quickEq(_last, "ol")) {
            containers.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
        }
        if (StringList.quickEq(_last, "table")) {
            containers.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringList.quickEq(_last, "p")) {
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringList.quickEq(_last, "map")) {
            containers.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (line_ != null) {
            currentParent = line_;
        }
        if (StringList.quickEq(_last, "table")) {
            rowGroup = 0;
            partGroup++;
        }
        if (StringList.quickEq(_last, "ul") || StringList.quickEq(_last, "ol")) {
            rowGroup = 0;
            partGroup++;
            lis.removeLast();
            ordered.removeLast();
        }
        if (StringList.quickEq(_last, "p")) {
            rowGroup = 0;
            partGroup++;
        }
        if (StringList.quickEq(_last, "map")) {
            rowGroup = 0;
            partGroup++;
        }
        stacks.removeLast();
        boolean li_ = false;
        if (!stacks.isEmpty()) {
            if (StringList.quickEq(stacks.last(), "li")) {
                li_ = true;
            }
        }
        if (li_ && line_ != null) {
            MetaContainer curPar_ = currentParent;
            MetaIndentNbLabel indent_ = new MetaIndentNbLabel(curPar_);
            curPar_.appendChild(indent_);
        }
    }
    public MetaBlock getRoot() {
        return root;
    }
    public static MetaDocument newInstance(Document _document) {
        return new MetaDocument(_document);
    }
}
