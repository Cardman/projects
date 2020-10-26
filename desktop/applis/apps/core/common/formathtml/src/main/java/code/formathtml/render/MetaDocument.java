package code.formathtml.render;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.formathtml.errors.RendKeyWords;
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
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MetaDocument {

    public static final String SPACE = " ";
    private static final String LF = "\n";
    private static final String CRLF = "\r\n";
    private static final String SEP_IMG = ";";
    private static final String COLOR_PREFIX = "#";
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

    private MetaDocument(Document _document, RendKeyWords _rend) {
        int tabWidth_ = _document.getTabWidth();
        //process style parse
        ObjectMap<FormInputCoords, Integer> indexesButtons_ = new ObjectMap<FormInputCoords, Integer>();
        for (Element e: _document.getElementsByTagName(_rend.getKeyWordHead())) {
            for (Element s: e.getElementsByTagName(_rend.getKeyWordStyle())) {
                processStyle(s.getTextContent());
            }
        }
        Element body_ = _document.getElementsByTagName(_rend.getKeyWordBody()).first();
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
                setupGeneStyle(_rend,styleLoc_, tags_, currentElt_, true);
            }
            while (parStyle_ != null) {
                setupGeneStyle(_rend,styleLoc_, tags_, parStyle_, false);
                parStyle_ = parStyle_.getParentNode();
            }
            boolean li_ = false;
            if (!stacks.isEmpty()) {
                if (StringUtil.quickEq(stacks.last(), _rend.getKeyWordLi())) {
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
                    if (!par_.getAttribute(_rend.getAttrTitle()).isEmpty() && title_.isEmpty()) {
                        title_ = par_.getAttribute(_rend.getAttrTitle());
                    }
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordAnchor()) && anchor_ == null) {
                        anchor_ = par_;
                    }
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordBold())) {
                        bold_ = 1;
                    }
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordItalic())) {
                        italic_ = 2;
                    }
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordPre())) {
                        pre_ = true;
                    }
                    if (ht_ == null) {
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHOne())) {
                            ht_ = par_;
                            delta_= 6;
                        }
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHTwo())) {
                            ht_ = par_;
                            delta_= 5;
                        }
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHThree())) {
                            ht_ = par_;
                            delta_= 4;
                        }
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFour())) {
                            ht_ = par_;
                            delta_= 3;
                        }
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFive())) {
                            ht_ = par_;
                            delta_= 2;
                        }
                        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHSix())) {
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
                    if (trimLeftText(txt_,_rend)) {
                        begin_ = StringUtil.getFirstPrintableCharIndex(realText_);
                        begin_ = Math.max(begin_,0);
                    }
                    if (trimRightText(txt_,_rend)) {
                        end_ = StringUtil.getLastPrintableCharIndex(realText_);
                        end_ = Math.max(end_,begin_-1);
                    }
                    text_ = realText_.substring(begin_, end_ + 1);
                    StringBuilder adjustedText_ = new StringBuilder(text_.length());
                    int len_ = text_.length();
                    int i_ = 0;
                    while (i_ < len_) {
                        char currentChar_ = text_.charAt(i_);
                        i_++;
                        if (StringUtil.isWhitespace(currentChar_)) {
                            adjustedText_.append(SPACE);
                            while (i_ < len_) {
                                currentChar_ = text_.charAt(i_);
                                if (!StringUtil.isWhitespace(currentChar_)) {
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
                        String name_ = anchor_.getAttribute(_rend.getAttrName());
                        label_ = new MetaAnchorLabel(currentParent, text_, title_, anchor_, partGroup, rowGroup);
                        if (!name_.isEmpty()) {
                            anchorsRef.put(name_, (MetaAnchorLabel) label_);
                        }
                    }
                    label_.setStyle(styleLoc_);
                    currentParent.appendChild(label_);
                } else if (pre_) {
                    StringList strings_ = StringUtil.splitStrings(realText_, LF, CRLF);
                    int nbLines_ = strings_.size();
                    int indLine_ = 0;
                    for (String l: strings_) {
                        StringBuilder line_ = new StringBuilder(realText_.length());
                        for (char c: l.toCharArray()) {
                            if (c == '\t') {
                                for (int i = 0; i < tabWidth_; i++) {
                                    line_.append(SPACE);
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
                            String name_ = anchor_.getAttribute(_rend.getAttrName());
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordBr())) {
                    skipChildrenBuild_ = true;
                    newLine_ = true;
                    rowGroup++;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordHr())) {
                    skipChildrenBuild_ = true;
                    newLine_ = true;
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordStyle())) {
                    //put all style tags
                    String css_ = current_.getTextContent();
                    processStyle(css_);
                } else {
                    processStyle(elt_.getAttribute(_rend.getKeyWordStyle()));
                }
                MetaContainer curPar_ = currentParent.getParent();
                if (newLine_) {
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordHr())) {
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordImg())) {
                    String title_ = MetaComponent.EMPTY_STRING;
                    Element anchor_ = null;
                    Element par_ = current_.getParentNode();
                    while (par_ != null) {
                        String tagNamePar_ = par_.getTagName();
                        if (!par_.getAttribute(_rend.getAttrTitle()).isEmpty() && title_.isEmpty()) {
                            title_ = par_.getAttribute(_rend.getAttrTitle());
                        }
                        if (StringUtil.quickEq(tagNamePar_, _rend.getKeyWordAnchor()) && anchor_ == null) {
                            anchor_ = par_;
                        }
                        par_ = par_.getParentNode();
                    }
                    if (elt_.getAttribute(_rend.getAttrSrc()).contains(SEP_IMG)) {
                        Integer delay_ = NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrDelay()));
                        MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent, StringUtil.splitStrings(elt_.getAttribute(_rend.getAttrSrc()), SEP_IMG), title_, delay_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent.appendChild(imgs_);
                    } else {
                        MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, elt_.getAttribute(_rend.getAttrSrc()), title_, anchor_);
                        imgs_.setStyle(styleLoc_);
                        currentParent.appendChild(imgs_);
                    }
                    skipChildrenBuild_ = true;
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordSelect())) {
                    skipChildrenBuild_ = true;
                    rowGroup = 0;
                    partGroup++;
                    long idForm_ = getParentFormNb();
                    if (elt_.hasAttribute(_rend.getAttrMultiple())) {
                        Ints selected_ = new Ints();
                        StringList values_ = new StringList();
                        StringList strings_ = new StringList();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute(_rend.getAttrSelected())) {
                                selected_.add(i_);
                            }
                            values_.add(c.getAttribute(_rend.getAttrValue()));
                            strings_.add(c.getTextContent());
                            i_++;
                        }
                        int vis_ = BeanLgNames.parseInt(elt_.getAttribute(_rend.getAttrRows()),8);
                        MetaInput input_ = new MetaComboList(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), strings_, values_, selected_, vis_,idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else {
                        int selected_ = 0;
                        Ints selInd_ = new Ints();
                        StringList values_ = new StringList();
                        StringList strings_ = new StringList();
                        int i_ = 0;
                        for (Element c: elt_.getChildElements()) {
                            if (c.hasAttribute(_rend.getAttrSelected())) {
                                selected_ = i_;
                                selInd_.add(i_);
                            }
                            values_.add(c.getAttribute(_rend.getAttrValue()));
                            strings_.add(c.getTextContent());
                            i_++;
                        }
                        MetaInput input_ = new MetaComboBox(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), strings_, values_, selected_,selInd_,idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    }
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordInput())) {
                    skipChildrenBuild_ = true;
                    String type_ = elt_.getAttribute(_rend.getAttrType());
                    long idForm_ = getParentFormNb();
                    if (StringUtil.quickEq(type_, _rend.getValueText())) {
                        int cols_ = BeanLgNames.parseInt(elt_.getAttribute(_rend.getAttrCols()),32);
                        MetaInput input_ = new MetaTextField(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), cols_, elt_.getAttribute(_rend.getAttrValue()),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringUtil.quickEq(type_, _rend.getValueNumber())) {
                        MetaInput input_ = new MetaSpinner(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), elt_.getAttribute(_rend.getAttrValue()),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringUtil.quickEq(type_, _rend.getValueRange())) {
                        MetaInput input_ = new MetaSlider(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), elt_.getAttribute(_rend.getAttrValue()),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringUtil.quickEq(type_, _rend.getValueCheckbox())) {
                        MetaInput input_ = new MetaCheckedBox(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), elt_.hasAttribute(_rend.getAttrChecked()),idForm_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else if (StringUtil.quickEq(type_, _rend.getValueRadio())) {
                        int inputNb_ = NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi()));
                        FormInputCoords id_ = new FormInputCoords();
                        id_.setForm(idForm_);
                        id_.setInput(inputNb_);
                        if (indexesButtons_.contains(id_)) {
                            indexesButtons_.put(id_, indexesButtons_.getVal(id_) + 1);
                        } else {
                            indexesButtons_.put(id_, 0);
                        }
                        MetaInput input_ = new MetaRadioButton(currentParent, inputNb_, indexesButtons_.getVal(id_),elt_.hasAttribute(_rend.getAttrChecked()), elt_.getAttribute(_rend.getAttrValue()),id_);
                        input_.setStyle(styleLoc_);
                        currentParent.appendChild(input_);
                    } else {
                        //button
                        Element form_ = null;
                        Element par_ = current_.getParentNode();
                        while (par_ != null) {
                            String tagNamePar_ = par_.getTagName();
                            if (StringUtil.quickEq(tagNamePar_, _rend.getKeyWordForm()) && form_ == null) {
                                form_ = par_;
                            }
                            par_ = par_.getParentNode();
                        }
                        MetaInput button_ = new MetaButton(currentParent, -1, form_, elt_.getAttribute(_rend.getAttrValue()),idForm_);
                        button_.setStyle(styleLoc_);
                        currentParent.appendChild(button_);
                    }
                    rowGroup = 0;
                    partGroup++;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordTextarea())) {
                    skipChildrenBuild_ = true;
                    long idForm_ = getParentFormNb();
                    rowGroup = 0;
                    partGroup++;
                    int rows_ = BeanLgNames.parseInt(elt_.getAttribute(_rend.getAttrRows()),32);
                    int cols_ = BeanLgNames.parseInt(elt_.getAttribute(_rend.getAttrCols()),32);
                    MetaInput input_ = new MetaTextArea(currentParent, NumberUtil.parseInt(elt_.getAttribute(_rend.getAttrNi())), cols_, rows_, elt_.getTextContent(),idForm_);
                    input_.setStyle(styleLoc_);
                    currentParent.appendChild(input_);
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordForm())) {
                    long lg_ = NumberUtil.parseLongZero(elt_.getAttribute(_rend.getAttrNf()));
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordPar())) {
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordUl()) || StringUtil.quickEq(tagName_, _rend.getKeyWordOl())) {
                    MetaContainer line_ = new MetaLine(curPar_);
                    line_.setStyle(styleLoc_);
                    typesLi.add(elt_.getAttribute(_rend.getAttrType()));
                    MetaContainer bl_;
                    if (StringUtil.quickEq(tagName_, _rend.getKeyWordUl())) {
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
                    ordered.add(StringUtil.quickEq(tagName_, _rend.getKeyWordOl()));
                    currentParent = bl_;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordLi())) {
                    rowGroup = 0;
                    partGroup++;
                    MetaContainer list_ = containers.last();
                    MetaContainer bl_ = new MetaListItem(list_);
                    MetaContainer line_ = new MetaLine(bl_);
                    line_.setStyle(styleLoc_);
                    MetaLabel nb_;
                    if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()),_rend.getValueLiNb())) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiMinLet())) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LETTER);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiMajLet())) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.MAJ_LETTER);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiMinLat())) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MIN);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiMajLat())) {
                        nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MAJ);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiCircle())) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiDisk())) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiSquare())) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
                    } else if (StringUtil.quickEq(elt_.getAttribute(_rend.getAttrType()), _rend.getValueLiRect())) {
                        nb_ = new MetaPointLabel(line_, MetaPointForm.RECT);
                    } else if (!typesLi.last().isEmpty()) {
                        if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiNb())) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMinLet())) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LETTER);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMajLet())) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.MAJ_LETTER);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMinLat())) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MIN);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMajLat())) {
                            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MAJ);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiCircle())) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiDisk())) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiSquare())) {
                            nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
                        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiRect())) {
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
                    lis.set(lis.getLastIndex(),lis.last()+1);
                    bl_.appendChild(line_);
                    list_.appendChild(bl_);
                    currentParent = line_;
                }
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordTable())) {
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordCaption())) {
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordTd()) || StringUtil.quickEq(tagName_, _rend.getKeyWordTh())) {
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordMap())) {
                    int width_ = BeanLgNames.parseInt(elt_.getAttribute(_rend.getAttrWidth()),1);
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
                if (StringUtil.quickEq(tagName_, _rend.getKeyWordDiv())) {
                    int index_ = 1;
                    int i_ = 0;
                    Node first_ = elt_.getFirstChild();
                    while (first_ != null) {
                        if (first_ instanceof Element) {
                            if (StringUtil.quickEq(((Element)first_).getTagName(), _rend.getKeyWordBr())) {
                                index_ = i_;
                                break;
                            }
                            if (StringUtil.quickEq(((Element)first_).getTagName(), _rend.getKeyWordHr())) {
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
                unstack(lastTag_,_rend);
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

    private void setupGeneStyle(RendKeyWords _rend,MetaStyle _styleLoc, IntTreeMap<String> _tags, Element _currentElt, boolean _local) {
        int len_ = _tags.size();
        int j_ = len_ - 1;
        while (j_ > -1) {
            String v_ = _tags.getVal(j_);
            int f_ = v_.indexOf('.');
            if (f_ == 0) {
                String value_ = getValueOrEmpty(classesCssStyles, v_.substring(1));
                if (StringUtil.quickEq(v_.substring(1), _currentElt.getAttribute(_rend.getAttrClass()))) {
                    setupStyle(_rend,_styleLoc, value_, _local);
                    break;
                }
                j_--;
                continue;
            }
            if (tagsClassesCssStyles.contains(v_)) {
                String tag_ = v_.substring(0, f_);
                String class_ = v_.substring(f_ + 1);
                if (StringUtil.quickEq(class_, _currentElt.getAttribute(_rend.getAttrClass())) && StringUtil.quickEq(tag_, _currentElt.getTagName())) {
                    setupStyle(_rend,_styleLoc, tagsClassesCssStyles.getVal(v_), _local);
                    break;
                }
            }
            String value_ = getValueOrEmpty(tagsCssStyles, v_);
            if (StringUtil.quickEq(v_, _currentElt.getTagName())) {
                setupStyle(_rend,_styleLoc, value_, _local);
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

    private static void setupStyle(RendKeyWords _rend,MetaStyle _style, String _value, boolean _local) {
        for (String p: StringUtil.splitChars(_value, ';')) {
            int indexSep_ = p.indexOf(':');
            if (indexSep_ < 0) {
                continue;
            }
            String key_ = p.substring(0, indexSep_).trim();
            String value_ = p.substring(indexSep_ + 1).trim();
            if (StringUtil.quickEq(key_, _rend.getStyleAttrFontFam())) {
                _style.setFontFamily(value_);
                continue;
            }
            if (StringUtil.quickEq(key_, _rend.getStyleAttrFontSize())) {
                if (value_.endsWith(_rend.getStyleUnitPx())) {
                    String size_ = value_.substring(0, value_.length() - 2);
                    int val_ = NumberUtil.parseInt(size_);
                    _style.setSize(val_);
                }
                if (value_.endsWith(_rend.getStyleUnitEm())) {
                    String size_ = value_.substring(0, value_.length() - 2);
                    int val_ = NumberUtil.parseInt(size_);
                    _style.setEm(val_);
                }
                continue;
            }
            if (StringUtil.quickEq(key_, _rend.getStyleAttrColor())) {
                _style.setFgColor(getColor(_rend,value_,_style.getFgColor()));
            } else if (StringUtil.quickEq(key_, _rend.getStyleAttrBackground())) {
                _style.setBgColor(getColor(_rend,value_,_style.getBgColor()));
            } else if (StringUtil.quickEq(key_, _rend.getStyleAttrBorder())) {
                if (!_local) {
                    continue;
                }
                for (String v: StringUtil.splitChars(value_, ' ','\t','\n','\r')) {
                    if (v.endsWith(_rend.getStyleUnitPx())) {
                        String size_ = v.substring(0, v.length() - 2);
                        int val_ = NumberUtil.parseInt(size_);
                        _style.setBorderSize(val_);
                        continue;
                    }
                    if (StringUtil.quickEq(v, _rend.getStyleUnitSolid())){
                        _style.setBorder(BorderEnum.SOLID);
                        continue;
                    }
                    _style.setBorderColor(getColor(_rend,v,_style.getBorderColor()));
                }
            }
        }
    }

    private static int getColor(RendKeyWords _rend,String _value, int _default) {
        if (_value.startsWith(_rend.getStyleValueRgb())&&_value.substring(_rend.getStyleValueRgb().length()).trim().indexOf('(')==0) {
            String val_ = StringUtil.removeChars(_value.substring(_rend.getStyleValueRgb().length()), '(', ')');
            return getRgb(val_);
        }
        if (_value.startsWith(COLOR_PREFIX)) {
            LongInfo val_ = NumParsers.parseLong(_value.substring(1), 16);
            if (val_.isValid()) {
                return (int) val_.getValue();
            }
            return _default;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueRed())){
            return 255*256*256;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueGreen())){
            return 255*256;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueBlue())){
            return 255;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueYellow())){
            return 255*256*256+255*256;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueCyan())){
            return 255*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueMagenta())){
            return 255*256*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueWhite())){
            return 255*256*256 + 255*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueGrey())){
            return 127*256*256 + 127*256 + 127;
        }
        if (StringUtil.quickEq(_value, _rend.getStyleValueBlack())){
            return 0;
        }
        return _default;
    }
    private static int getRgb(String _value) {
        int rgb_ = 0;
        Ints rates_ = new Ints();
        for (String c: StringUtil.splitChars(_value, ',')) {
            int l_ = NumberUtil.parseInt(c.trim());
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
                    for (String p: StringUtil.splitChars(key_.toString(), ',')) {
                        String tr_ = p.trim();
                        int f_ = tr_.indexOf('.');
                        if (f_ == 0) {
                            classesCssStyles.put(tr_.substring(1), str_.toString());
                        } else if (f_ > 0) {
                            tagsClassesCssStyles.put(tr_, str_.toString());
                        } else {
                            tagsCssStyles.put(tr_, str_.toString());
                        }
                        tagsClasses.put(tr_, indexTagClass);
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
    private static boolean trimLeftText(Text _text, RendKeyWords _rend) {
        Node curr_ = _text;
        while (true) {
            Node previous_ = curr_.getPreviousSibling();
            if (previous_ != null) {
                return blockElement(previous_,_rend);
            }
            Element par_ = curr_.getParentNode();
            if (blockElement(par_,_rend)) {
                return true;
            }
            curr_ = par_;
        }
    }
    private static boolean trimRightText(Text _text, RendKeyWords _rend) {
        Node curr_ = _text;
        while (true) {
            Node previous_ = curr_.getNextSibling();
            if (previous_ != null) {
                return blockElement(previous_,_rend);
            }
            Element par_ = curr_.getParentNode();
            if (blockElement(par_,_rend)) {
                return true;
            }
            curr_ = par_;
        }
    }
    private static boolean blockElement(Node _elt, RendKeyWords _rend) {
        if (!(_elt instanceof Element)) {
            return false;
        }
        String tagName_ = ((Element)_elt).getTagName();
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordBody())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordBr())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHr())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordLi())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordOl())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordUl())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordTable())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordTd())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordCaption())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordForm())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordPar())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHOne())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHTwo())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHThree())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFour())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFive())) {
            return true;
        }
        return StringUtil.quickEq(tagName_, _rend.getKeyWordHSix());
    }
    public StringMap<MetaAnchorLabel> getAnchorsRef() {
        return anchorsRef;
    }
    private void unstack(String _last, RendKeyWords _rend) {
        MetaContainer line_ = null;
        if (StringUtil.quickEq(_last, _rend.getKeyWordUl()) || StringUtil.quickEq(_last, _rend.getKeyWordOl())) {
            containers.removeQuicklyLast();
            typesLi.removeLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordTable())) {
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordForm())) {
            formIndex.removeLast();
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordPar())) {
            MetaContainer last_ = containers.last();
            MetaLine postline_ = new MetaLine(last_);
            MetaEndLine end_ = new MetaEndLine(postline_);
            postline_.appendChild(end_);
            last_.appendChild(postline_);
            containers.removeQuicklyLast();
            last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordMap()) || StringUtil.quickEq(_last, _rend.getKeyWordDiv())) {
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordTr())) {
            MetaTable table_ = tables.last();
            table_.addRemainder(true);
        }
        if (line_ != null) {
            currentParent = line_;
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordTable())) {
            rowGroup = 0;
            partGroup++;
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordUl()) || StringUtil.quickEq(_last, _rend.getKeyWordOl())) {
            rowGroup = 0;
            partGroup++;
            lis.removeLast();
            ordered.removeLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordPar())) {
            rowGroup = 0;
            partGroup++;
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordMap())) {
            rowGroup = 0;
            partGroup++;
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordDiv())) {
            rowGroup = 0;
            partGroup++;
        }
        stacks.removeLast();
        boolean li_ = false;
        if (!stacks.isEmpty()) {
            if (StringUtil.quickEq(stacks.last(), _rend.getKeyWordLi())) {
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
    public static MetaDocument newInstance(Document _document, RendKeyWords _rend) {
        return new MetaDocument(_document,_rend);
    }

    public CustList<IntForm> getForms() {
        return forms;
    }
}
