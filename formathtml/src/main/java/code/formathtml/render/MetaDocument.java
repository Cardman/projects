package code.formathtml.render;

import code.expressionlanguage.stds.NumParsers;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.FormInputCoords;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.sml.Text;
import code.util.BooleanList;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public final class MetaDocument {

    private MetaBlock root;
    private StringList stacks;

    private MetaContainer currentParent;
    private int partGroup;
    private int rowGroup;
    private CustList<MetaContainer> containers;
    private CustList<MetaTable> tables;
    private Ints lis;
    private BooleanList ordered;
    private CustList<MetaContainer> dynamicNewLines = new CustList<MetaContainer>();
    private Longs formIndex = new Longs();
    private StringMap<MetaAnchorLabel> anchorsRef = new StringMap<MetaAnchorLabel>();
    private StringMap<String> classesCssStyles = new StringMap<String>();
    private StringMap<String> tagsCssStyles = new StringMap<String>();
    private StringMap<String> tagsClassesCssStyles = new StringMap<String>();
    private StringMap<Integer> tagsClasses = new StringMap<Integer>();
    private StringList typesLi = new StringList();
    private CustList<IntForm> forms = new CustList<IntForm>();
    private int indexTagClass;

    private MetaDocument(Document _document) {
        int tabWidth_ = _document.getTabWidth();
        //process style parse
        ObjectMap<FormInputCoords, Integer> indexesButtons_ = new ObjectMap<FormInputCoords, Integer>();
        for (Element e: _document.getElementsByTagName("head")) {
            for (Element s: e.getElementsByTagName("style")) {
                processStyle(s.getTextContent());
            }
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
        lis = new Ints();
        ordered = new BooleanList();
        while (true) {
            MetaStyle styleLoc_ = new MetaStyle();
            Element parStyle_ = current_.getParentNode();
            IntTreeMap< String> tags_ = new IntTreeMap< String>(new CollCapacity(tagsClasses.size()));
            for (EntryCust<String, Integer> e: tagsClasses.entryList()) {
                tags_.put(e.getValue(), e.getKey());
            }
            if (current_ instanceof Element) {
                Element currentElt_ = (Element) current_;
                setupGeneStyle(styleLoc_, tags_, currentElt_, true);
            }
            while (parStyle_ != null) {
                setupGeneStyle(styleLoc_, tags_, parStyle_, false);
                parStyle_ = parStyle_.getParentNode();
            }
            boolean li_ = false;
            if (!stacks.isEmpty()) {
                if (StringList.quickEq(stacks.last(), "li")) {
                    li_ = true;
                }
            }
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                String realText_ = txt_.getTextContent();
                String text_ = realText_.trim();
                String title_ = MetaComponent.EMPTY_STRING;
                Element anchor_ = null;
                Element par_ = current_.getParentNode();
                int bold_ = 0;
                int italic_ = 0;
                boolean pre_ = false;
                Element ht_ = null;
                int delta_ = 0;
                while (par_ != null) {
                    String tagName_ = par_.getTagName();
                    if (!par_.getAttribute("title").isEmpty() && title_.isEmpty()) {
                        title_ = par_.getAttribute("title");
                    }
                    if (StringList.quickEq(tagName_, "a") && anchor_ == null) {
                        anchor_ = par_;
                    }
                    if (StringList.quickEq(tagName_, "b")) {
                        bold_ = 1;
                    }
                    if (StringList.quickEq(tagName_, "i")) {
                        italic_ = 2;
                    }
                    if (StringList.quickEq(tagName_, "pre")) {
                        pre_ = true;
                    }
                    if (ht_ == null) {
                        if (StringList.quickEq(tagName_, "h1")) {
                            ht_ = par_;
                            delta_= 6;
                        }
                        if (StringList.quickEq(tagName_, "h2")) {
                            ht_ = par_;
                            delta_= 5;
                        }
                        if (StringList.quickEq(tagName_, "h3")) {
                            ht_ = par_;
                            delta_= 4;
                        }
                        if (StringList.quickEq(tagName_, "h4")) {
                            ht_ = par_;
                            delta_= 3;
                        }
                        if (StringList.quickEq(tagName_, "h5")) {
                            ht_ = par_;
                            delta_= 2;
                        }
                        if (StringList.quickEq(tagName_, "h6")) {
                            ht_ = par_;
                            delta_= 1;
                        }
                    }
                    par_ = par_.getParentNode();
                }
                styleLoc_.setDelta(delta_);
                styleLoc_.setBold(bold_);
                styleLoc_.setItalic(italic_);
                if (!text_.isEmpty() && !pre_) {
                    int begin_ = 0;
                    int end_ = realText_.length() - 1;
                    if (trimLeftText(txt_)) {
                        begin_ = StringList.getFirstPrintableCharIndex(realText_);
                        begin_ = Math.max(begin_,0);
                    }
                    if (trimRightText(txt_)) {
                        end_ = StringList.getLastPrintableCharIndex(realText_);
                        end_ = Math.max(end_,begin_-1);
                    }
                    text_ = realText_.substring(begin_, end_ + 1);
                    StringBuilder adjustedText_ = new StringBuilder(text_.length());
                    int len_ = text_.length();
                    int i_ = 0;
                    while (i_ < len_) {
                        char currentChar_ = text_.charAt(i_);
                        i_++;
                        if (Character.isWhitespace(currentChar_)) {
                            adjustedText_.append(" ");
                            while (i_ < len_) {
                                currentChar_ = text_.charAt(i_);
                                if (!Character.isWhitespace(currentChar_)) {
                                    break;
                                }
                                i_++;
                            }
                        } else {
                            adjustedText_.append(currentChar_);
                        }
                    }
                    text_ = adjustedText_.toString();
                    MetaSearchableLabel label_;
                    if (anchor_ == null) {
                        label_ = new MetaPlainLabel(currentParent, text_, title_, partGroup, rowGroup);
                    } else {
                        String name_ = anchor_.getAttribute("name");
                        label_ = new MetaAnchorLabel(currentParent, text_, title_, anchor_, partGroup, rowGroup);
                        if (!name_.isEmpty()) {
                            anchorsRef.put(name_, (MetaAnchorLabel) label_);
                        }
                    }
                    label_.setStyle(styleLoc_);
                    currentParent.appendChild(label_);
                } else if (pre_) {
                    StringList strings_ = StringList.splitStrings(realText_, "\n", "\r\n");
                    int nbLines_ = strings_.size();
                    int indLine_ = 0;
                    for (String l: strings_) {
                        StringBuilder line_ = new StringBuilder(realText_.length());
                        for (char c: l.toCharArray()) {
                            if (c == '\t') {
                                for (int i = 0; i < tabWidth_; i++) {
                                    line_.append(" ");
                                }
                            } else {
                                line_.append(c);
                            }
                        }
                        text_ = line_.toString();
                        MetaSearchableLabel label_;
                        if (anchor_ == null) {
                            label_ = new MetaPlainLabel(currentParent, text_, title_, partGroup, rowGroup);
                        } else {
                            String name_ = anchor_.getAttribute("name");
                            label_ = new MetaAnchorLabel(currentParent, text_, title_, anchor_, partGroup, rowGroup);
                            if (!name_.isEmpty()) {
                                anchorsRef.put(name_, (MetaAnchorLabel) label_);
                            }
                        }
                        rowGroup++;
                        label_.setStyle(styleLoc_);
                        currentParent.appendChild(label_);
                        if (indLine_ + 1 < nbLines_) {
                            MetaEndLine end_ = new MetaEndLine(currentParent);
                            end_.setStyle(styleLoc_);
                            currentParent.appendChild(end_);
                            MetaContainer curPar_ = currentParent.getParent();
                            MetaContainer lineBl_ = new MetaLine(curPar_);
                            indent(styleLoc_, li_, lineBl_);
                            curPar_.appendChild(lineBl_);
                            currentParent = lineBl_;
                        }
                        indLine_++;
                    }
                    rowGroup--;
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
                if (StringList.quickEq(tagName_, "style")) {
                    //put all style tags
                    String css_ = current_.getTextContent();
                    processStyle(css_);
                } else {
                    processStyle(elt_.getAttribute("style"));
                }
                MetaContainer curPar_ = currentParent.getParent();
                if (newLine_) {
                    if (StringList.quickEq(tagName_, "hr")) {
                        MetaSeparator sep_ = new MetaSeparator(curPar_);
                        sep_.setStyle(styleLoc_);
                        curPar_.appendChild(sep_);
                    }
                    MetaEndLine end_ = new MetaEndLine(currentParent);
                    end_.setStyle(styleLoc_);
                    currentParent.appendChild(end_);
                    MetaContainer line_ = new MetaLine(curPar_);
                    indent(styleLoc_, li_, line_);
                    curPar_.appendChild(line_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "img")) {
                    String title_ = MetaComponent.EMPTY_STRING;
                    Element anchor_ = null;
                    Element par_ = current_.getParentNode();
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
                        Integer delay_ = Numbers.parseInt(elt_.getAttribute("delay"));
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
                    long idForm_ = getParentFormNb();
                    if (elt_.hasAttribute("multiple")) {
                        Ints selected_ = new Ints();
                        StringList values_ = new StringList();
                        StringList strings_ = new StringList();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute("selected")) {
                                selected_.add(i_);
                            }
                            values_.add(c.getAttribute("value"));
                            strings_.add(c.getTextContent());
                            i_++;
                        }
                        int vis_ = BeanLgNames.parseInt(elt_.getAttribute("rows"),8);
                        MetaInput input_ = new MetaComboList(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), strings_, values_, selected_, vis_,idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else {
                        int selected_ = 0;
                        Ints selInd_ = new Ints();
                        StringList values_ = new StringList();
                        StringList strings_ = new StringList();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute("selected")) {
                                selected_ = i_;
                                selInd_.add(i_);
                            }
                            values_.add(c.getAttribute("value"));
                            strings_.add(c.getTextContent());
                            i_++;
                        }
                        MetaInput input_ = new MetaComboBox(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), strings_, values_, selected_,selInd_,idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    }
                }
                if (StringList.quickEq(tagName_, "input")) {
                    skipChildrenBuild_ = true;
                    String type_ = elt_.getAttribute("type");
                    long idForm_ = getParentFormNb();
                    if (StringList.quickEq(type_, "text")) {
                        int cols_ = BeanLgNames.parseInt(elt_.getAttribute("cols"),32);
                        MetaInput input_ = new MetaTextField(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), cols_, elt_.getAttribute("value"),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "number")) {
                        MetaInput input_ = new MetaSpinner(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), elt_.getAttribute("value"),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "range")) {
                        MetaInput input_ = new MetaSlider(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), elt_.getAttribute("value"),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "checkbox")) {
                        MetaInput input_ = new MetaCheckedBox(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), elt_.hasAttribute("checked"),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringList.quickEq(type_, "radio")) {
                        int inputNb_ = Numbers.parseInt(elt_.getAttribute("n-i"));
                        FormInputCoords id_ = new FormInputCoords();
                        id_.setForm(idForm_);
                        id_.setInput(inputNb_);
                        if (indexesButtons_.contains(id_)) {
                            indexesButtons_.put(id_, indexesButtons_.getVal(id_) + 1);
                        } else {
                            indexesButtons_.put(id_, 0);
                        }
                        MetaInput input_ = new MetaRadioButton(currentParent, inputNb_, indexesButtons_.getVal(id_),elt_.hasAttribute("checked"), elt_.getAttribute("value"),id_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else {
                        //button
                        Element form_ = null;
                        Element par_ = current_.getParentNode();
                        while (par_ != null) {
                            String tagNamePar_ = par_.getTagName();
                            if (StringList.quickEq(tagNamePar_, "form") && form_ == null) {
                                form_ = par_;
                            }
                            par_ = par_.getParentNode();
                        }
                        MetaInput button_ = new MetaButton(currentParent, -1, form_, elt_.getAttribute("value"),idForm_);
                        button_.setStyle(styleLoc_);
                        currentParent.appendChild(button_);
                    }
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringList.quickEq(tagName_, "textarea")) {
                    skipChildrenBuild_ = true;
                    long idForm_ = getParentFormNb();
                    rowGroup = 0;
                    partGroup++;
                    int rows_ = BeanLgNames.parseInt(elt_.getAttribute("rows"),32);
                    int cols_ = BeanLgNames.parseInt(elt_.getAttribute("cols"),32);
                    MetaInput input_ = new MetaTextArea(currentParent, Numbers.parseInt(elt_.getAttribute("n-i")), cols_, rows_, elt_.getTextContent(),idForm_);
                    input_.setStyle(styleLoc_);
                    currentParent.appendChild(input_);
                }
                if (StringList.quickEq(tagName_, "form")) {
                    long lg_ = Numbers.parseLongZero(elt_.getAttribute("n-f"));
                    formIndex.add(lg_);
                    MetaContainer surline_ = new MetaLine(curPar_);
                    surline_.setStyle(styleLoc_);
                    MetaForm form_ = new MetaForm(surline_, lg_);
                    form_.setStyle(styleLoc_);
                    MetaContainer line_ = new MetaLine(form_);
                    line_.setStyle(styleLoc_);
                    form_.appendChild(line_);
                    forms.add(form_);
                    //indent
                    indent(styleLoc_, li_, surline_);
                    surline_.appendChild(form_);
                    curPar_.appendChild(surline_);
                    containers.add(curPar_);
                    containers.add(form_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "p")) {
                    MetaContainer surline_ = new MetaLine(curPar_);
                    surline_.setStyle(styleLoc_);
                    MetaContainer bl_ = new MetaParagraph(surline_);
                    bl_.setStyle(styleLoc_);
                    MetaContainer preline_ = new MetaLine(bl_);
                    preline_.setStyle(styleLoc_);
                    MetaEndLine end_ = new MetaEndLine(preline_);
                    end_.setStyle(styleLoc_);
                    preline_.appendChild(end_);
                    bl_.appendChild(preline_);
                    MetaContainer line_ = new MetaLine(bl_);
                    line_.setStyle(styleLoc_);
                    bl_.appendChild(line_);
                  //indent
                    indent(styleLoc_, li_, surline_);
                    surline_.appendChild(bl_);
                    curPar_.appendChild(surline_);
                    containers.add(curPar_);
                    containers.add(bl_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "ul") || StringList.quickEq(tagName_, "ol")) {
                    MetaContainer line_ = new MetaLine(curPar_);
                    line_.setStyle(styleLoc_);
                    typesLi.add(elt_.getAttribute("type"));
                    MetaContainer bl_;
                    if (StringList.quickEq(tagName_, "ul")) {
                        bl_ = new MetaUnorderedList(line_);
                    } else {
                        bl_ = new MetaOrderedList(line_);
                    }
                    bl_.setStyle(styleLoc_);
                    //indent
                    indent(styleLoc_, li_, line_);
                    MetaLabel ind_ = new MetaIndentLabel(line_);
                    ind_.setStyle(styleLoc_);
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
                    line_.setStyle(styleLoc_);
                    MetaLabel nb_;
                    if (StringList.quickEq(elt_.getAttribute("type"), "1")) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "a")) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LETTER);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "A")) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.MAJ_LETTER);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "i")) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MIN);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "I")) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MAJ);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "circle")) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "disc")) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "square")) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
                    } else if (StringList.quickEq(elt_.getAttribute("type"), "rect")) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.RECT);
                    } else if (!typesLi.last().isEmpty()) {
                        if (StringList.quickEq(typesLi.last(), "1")) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                        } else if (StringList.quickEq(typesLi.last(), "a")) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LETTER);
                        } else if (StringList.quickEq(typesLi.last(), "A")) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.MAJ_LETTER);
                        } else if (StringList.quickEq(typesLi.last(), "i")) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MIN);
                        } else if (StringList.quickEq(typesLi.last(), "I")) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MAJ);
                        } else if (StringList.quickEq(typesLi.last(), "circle")) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
                        } else if (StringList.quickEq(typesLi.last(), "disc")) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                        } else if (StringList.quickEq(typesLi.last(), "square")) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
                        } else if (StringList.quickEq(typesLi.last(), "rect")) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.RECT);
                        } else if (ordered.last()) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                        } else {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                        }
                    } else if (ordered.last()) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                    } else {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                    }
                    bl_.setStyle(styleLoc_);
                    nb_.setStyle(styleLoc_);
                    line_.appendChild(nb_);
                    lis.setLast(lis.last()+1);
                    bl_.appendChild(line_);
                    list_.appendChild(bl_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "table")) {
                    MetaContainer line_ = new MetaLine(curPar_);
                    line_.setStyle(styleLoc_);
                    MetaTable bl_ = new MetaTable(line_);
                    //indent
                    indent(styleLoc_, li_, line_);
                    bl_.setStyle(styleLoc_);
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
                    line_.setStyle(styleLoc_);
                    line_.setAddEmpty(true);
                    table_.addRemainder(false);
                    table_.appendChild(line_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "td") || StringList.quickEq(tagName_, "th")) {
                    rowGroup = 0;
                    partGroup++;
                    MetaTable table_ = tables.last();
                    MetaContainer bl_ = new MetaCell(table_);
                    bl_.setStyle(styleLoc_);
                    MetaContainer line_ = new MetaLine(bl_);
                    line_.setStyle(styleLoc_);
                    bl_.appendChild(line_);
                    table_.appendChild(bl_);
                    currentParent = line_;
                }
                if (StringList.quickEq(tagName_, "map")) {
                    int width_ = BeanLgNames.parseInt(elt_.getAttribute("width"),1);
                    MetaContainer line_ = new MetaLine(curPar_);
                    indent(styleLoc_, li_, line_);
                    MetaContainer map_ = new MetaImageMap(line_, width_);
                    map_.setStyle(styleLoc_);
                    line_.setStyle(styleLoc_);
                    line_.appendChild(map_);
                    curPar_.appendChild(line_);
                    containers.add(curPar_);
                    containers.add(map_);
                    currentParent = map_;
                }
                if (StringList.quickEq(tagName_, "div")) {
                    int index_ = 1;
                    int i_ = 0;
                    Node first_ = elt_.getFirstChild();
                    while (first_ != null) {
                        if (first_ instanceof Element) {
                            if (StringList.quickEq(((Element)first_).getTagName(), "br")) {
                                index_ = i_;
                                break;
                            }
                            if (StringList.quickEq(((Element)first_).getTagName(), "hr")) {
                                index_ = i_;
                                break;
                            }
                        }
                        first_ = first_.getNextSibling();
                        if (first_ instanceof Element) {
                            i_++;
                        }
                    }
                    MetaContainer line_ = new MetaLine(curPar_);
                    indent(styleLoc_, li_, line_);
                    MetaContainer map_ = new MetaImageMap(line_, index_);
                    map_.setStyle(styleLoc_);
                    line_.setStyle(styleLoc_);
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
            Node n_ = current_;
            boolean stop_ = false;
            while (true) {
                next_ = n_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element par_ = n_.getParentNode();
                String lastTag_ = par_.getTagName();
                unstack(lastTag_);
                if (par_ == body_) {
                    stop_ = true;
                    break;
                }
                n_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        for (MetaContainer c: dynamicNewLines) {
            if (c.onlyBlanks()) {
                c.getChildren().clear();
            }
        }
    }

    private long getParentFormNb() {
        if (formIndex.isEmpty()) {
            return -1;
        }
        return formIndex.last();
    }

    private static void indent(MetaStyle _styleLoc, boolean _li, MetaContainer _surline) {
        if (_li) {
            MetaLabel ind_ = new MetaIndentNbLabel(_surline);
            ind_.setStyle(_styleLoc);
            _surline.appendChild(ind_);
        }
    }

    private void setupGeneStyle(MetaStyle _styleLoc, IntTreeMap<String> _tags, Element _currentElt, boolean _local) {
        int len_ = _tags.size();
        int j_ = len_ - 1;
        while (j_ > -1) {
            String v_ = _tags.getVal(j_);
            if (v_.startsWith(".")) {
                String value_ = getValueOrEmpty(classesCssStyles, v_.substring(1));
                if (StringList.quickEq(v_.substring(1), _currentElt.getAttribute("class"))) {
                    setupStyle(_styleLoc, value_, _local);
                    break;
                }
                j_--;
                continue;
            }
            if (tagsClassesCssStyles.contains(v_)) {
                String tag_ = v_.substring(0, v_.indexOf('.'));
                String class_ = v_.substring(v_.indexOf('.') + 1);
                if (StringList.quickEq(class_, _currentElt.getAttribute("class")) && StringList.quickEq(tag_, _currentElt.getTagName())) {
                    setupStyle(_styleLoc, tagsClassesCssStyles.getVal(v_), _local);
                    break;
                }
            }
            String value_ = getValueOrEmpty(tagsCssStyles, v_);
            if (StringList.quickEq(v_, _currentElt.getTagName())) {
                setupStyle(_styleLoc, value_, _local);
                break;
            }
            j_--;
        }
    }
    private static String getValueOrEmpty(StringMap<String> _map, String _key) {
        String val_ = _map.getVal(_key);
        if (val_ == null) {
            return "";
        }
        return val_;
    }

    private static void setupStyle(MetaStyle _style, String _value, boolean _local) {
        for (String p: StringList.splitChars(_value, ';')) {
            int indexSep_ = p.indexOf(':');
            if (indexSep_ < 0) {
                continue;
            }
            String key_ = p.substring(0, indexSep_).trim();
            String value_ = p.substring(indexSep_ + 1).trim();
            if (StringList.quickEq(key_, "font-family")) {
                _style.setFontFamily(value_);
                continue;
            }
            if (StringList.quickEq(key_, "font-size")) {
                if (value_.endsWith("px")) {
                    String size_ = value_.substring(0, value_.length() - 2);
                    int val_ = Numbers.parseInt(size_);
                    _style.setSize(val_);
                }
                if (value_.endsWith("em")) {
                    String size_ = value_.substring(0, value_.length() - 2);
                    int val_ = Numbers.parseInt(size_);
                    _style.setEm(val_);
                }
                continue;
            }
            if (StringList.quickEq(key_, "color")) {
                _style.setFgColor(getColor(value_,_style.getFgColor()));
            } else if (StringList.quickEq(key_, "background")) {
                _style.setBgColor(getColor(value_,_style.getBgColor()));
            } else if (StringList.quickEq(key_, "border")) {
                if (!_local) {
                    continue;
                }
                for (String v: StringList.splitChars(value_, ' ','\t','\n','\r')) {
                    if (v.endsWith("px")) {
                        String size_ = v.substring(0, v.length() - 2);
                        int val_ = Numbers.parseInt(size_);
                        _style.setBorderSize(val_);
                        continue;
                    }
                    if (StringList.quickEq(v, "solid")){
                        _style.setBorder(BorderEnum.SOLID);
                        continue;
                    }
                    _style.setBorderColor(getColor(v,_style.getBorderColor()));
                }
            }
        }
    }

    private static int getColor(String _value, int _default) {
        if (_value.startsWith("rgb")&&_value.substring("rgb".length()).trim().indexOf('(')==0) {
            String val_ = StringList.removeChars(_value.substring("rgb".length()), '(', ')');
            return getRgb(val_);
        }
        if (_value.startsWith("#")) {
            Long val_ = NumParsers.parseLong(_value.substring(1), 16);
            if (val_ != null) {
                return val_.intValue();
            }
            return _default;
        }
        if (StringList.quickEq(_value, "red")){
            return 255*256*256;
        }
        if (StringList.quickEq(_value, "green")){
            return 255*256;
        }
        if (StringList.quickEq(_value, "blue")){
            return 255;
        }
        if (StringList.quickEq(_value, "yellow")){
            return 255*256*256+255*256;
        }
        if (StringList.quickEq(_value, "cyan")){
            return 255*256 + 255;
        }
        if (StringList.quickEq(_value, "magenta")){
            return 255*256*256 + 255;
        }
        if (StringList.quickEq(_value, "white")){
            return 255*256*256 + 255*256 + 255;
        }
        if (StringList.quickEq(_value, "grey")){
            return 127*256*256 + 127*256 + 127;
        }
        if (StringList.quickEq(_value, "black")){
            return 0;
        }
        return _default;
    }
    private static int getRgb(String _value) {
        int rgb_ = 0;
        Ints rates_ = new Ints();
        for (String c: StringList.splitChars(_value, ',')) {
            int l_ = Numbers.parseInt(c.trim());
            rates_.add(l_);
        }
        int power_ = rates_.size()-1;
        for (int i: rates_) {
            int p_ = 1;
            for (int j = 0; j < power_; j++) {
                p_ *= 256;
            }
            rgb_ += i * p_;
            power_--;
        }
        return rgb_;
    }

    private void processStyle(String _style) {
        int len_ = _style.length();
        int i_ = 0;
        int nbOpened_ = 0;
        StringBuilder key_ = new StringBuilder(_style.length());
        StringBuilder str_ = new StringBuilder(_style.length());
        while (i_ < len_) {
            char currentChar_ = _style.charAt(i_);
            if (currentChar_ == '{') {
                nbOpened_++;
            }
            if (currentChar_ == '}') {
                nbOpened_--;
            }
            if (nbOpened_ == 0) {
                if (currentChar_ != '}') {
                    key_.append(currentChar_);
                } else {
                    for (String p: StringList.splitChars(key_.toString(), ',')) {
                        if (p.trim().startsWith(".")) {
                            classesCssStyles.put(p.trim().substring(1), str_.toString());
                        } else if (p.contains(".")) {
                            tagsClassesCssStyles.put(p.trim(), str_.toString());
                        } else {
                            tagsCssStyles.put(p.trim(), str_.toString());
                        }
                        tagsClasses.put(p.trim(), indexTagClass);
                        indexTagClass++;
                    }
                    key_.delete(0, key_.length());
                    str_.delete(0, str_.length());
                }
            } else {
                if (currentChar_ != '{' || nbOpened_ > 1) {
                    str_.append(currentChar_);
                }
            }
            i_++;
        }
    }
    private static boolean trimLeftText(Text _text) {
        Node curr_ = _text;
        while (true) {
            Node previous_ = curr_.getPreviousSibling();
            if (previous_ != null) {
                return blockElement(previous_);
            }
            Element par_ = curr_.getParentNode();
            if (blockElement(par_)) {
                return true;
            }
            curr_ = par_;
        }
    }
    private static boolean trimRightText(Text _text) {
        Node curr_ = _text;
        while (true) {
            Node previous_ = curr_.getNextSibling();
            if (previous_ != null) {
                return blockElement(previous_);
            }
            Element par_ = curr_.getParentNode();
            if (blockElement(par_)) {
                return true;
            }
            curr_ = par_;
        }
    }
    private static boolean blockElement(Node _elt) {
        if (!(_elt instanceof Element)) {
            return false;
        }
        String tagName_ = ((Element)_elt).getTagName();
        if (StringList.quickEq(tagName_, "body")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "br")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "hr")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "li")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "ol")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "ul")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "table")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "td")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "caption")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "form")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "p")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h1")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h2")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h3")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h4")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h5")) {
            return true;
        }
        if (StringList.quickEq(tagName_, "h6")) {
            return true;
        }
        return false;
    }
    public StringMap<MetaAnchorLabel> getAnchorsRef() {
        return anchorsRef;
    }
    private void unstack(String _last) {
        MetaContainer line_ = null;
        if (StringList.quickEq(_last, "ul") || StringList.quickEq(_last, "ol")) {
            containers.removeLast();
            typesLi.removeLast();
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
        if (StringList.quickEq(_last, "form")) {
            formIndex.removeLast();
            containers.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringList.quickEq(_last, "p")) {
            MetaContainer last_ = containers.last();
            MetaLine postline_ = new MetaLine(last_);
            MetaEndLine end_ = new MetaEndLine(postline_);
            postline_.appendChild(end_);
            last_.appendChild(postline_);
            containers.removeLast();
            last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringList.quickEq(_last, "map") || StringList.quickEq(_last, "div")) {
            containers.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringList.quickEq(_last, "tr")) {
            MetaTable table_ = tables.last();
            table_.addRemainder(true);
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
        if (StringList.quickEq(_last, "div")) {
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
            dynamicNewLines.add(currentParent);
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

    public CustList<IntForm> getForms() {
        return forms;
    }
}
