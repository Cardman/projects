package code.formathtml.render;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.IndexButtons;
import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MetaDocument {

    public static final String SPACE = " ";
    private static final String LF = "\n";
    private static final String CRLF = "\r\n";
    private static final String SEP_IMG = ";";
    private static final String COLOR_PREFIX = "#";
    private final MetaBlock root;
    private final StringList stacks;

    private MetaContainer currentParent;
    private int partGroup;
    private int rowGroup;
    private final CustList<MetaContainer> containers;
    private final CustList<MetaTable> tables;
    private final Ints lis;
    private final CustList<BoolVal> ordered;
    private final CustList<MetaContainer> dynamicNewLines = new CustList<MetaContainer>();
    private final Longs formIndex = new Longs();
    private final StringMap<MetaAnchorLabel> anchorsRef = new StringMap<MetaAnchorLabel>();
    private final StringMap<String> classesCssStyles = new StringMap<String>();
    private final StringMap<String> tagsCssStyles = new StringMap<String>();
    private final StringMap<String> tagsClassesCssStyles = new StringMap<String>();
    private final StringMap<Integer> tagsClasses = new StringMap<Integer>();
    private final StringList typesLi = new StringList();
    private final CustList<IntForm> forms = new CustList<IntForm>();
    private String tagName = "";
    private boolean skipChildrenBuild;
    private int indexTagClass;
    private String title;
    private Element anchor;
    private int bold;
    private int italic;
    private boolean pre;
    private Element ht;
    private int delta;
    private final int tabWidth;

    private MetaDocument(Document _document, RendKeyWords _rend) {
        tabWidth = _document.getTabWidth();
        //process style parse
        IndexButtons indexesButtons_ = new IndexButtons();
        style(_document, _rend);
        root = new MetaBlock(null);
        stacks = new StringList();
        MetaLine mainLine_ = new MetaLine(root);
        root.appendChild(mainLine_);
        currentParent = mainLine_;
        containers = new CustList<MetaContainer>();
        tables = new CustList<MetaTable>();
        partGroup = 0;
        rowGroup = 0;
        lis = new Ints();
        ordered = new CustList<BoolVal>();
        ElementList bodies_ = _document.getElementsByTagName(_rend.getKeyWordBody());
        if (bodies_.isEmpty()) {
            return;
        }
        Element body_ = bodies_.first();
        Node current_ = body_;
        while (current_ != null) {
            MetaStyle styleLoc_ = new MetaStyle();
            eltTxt(_rend, indexesButtons_, current_, styleLoc_);
            Node next_ = current_.getFirstChild();
            if (next_ != null && !skipChildrenBuild) {
                stacks.add(tagName);
                current_ = next_;
                continue;
            }
            Node n_ = current_;
            while (current_ != null) {
                next_ = n_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element par_ = n_.getParentNode();
                String lastTag_ = par_.getTagName();
                unstack(lastTag_,_rend);
                if (par_ == body_) {
                    current_ = null;
                } else {
                    n_ = par_;
                }
            }
        }
        removeUseless();
    }

    private void eltTxt(RendKeyWords _rend, IndexButtons _indexes, Node _curr, MetaStyle _styleLoc) {
        updateSty(_rend, _curr, _styleLoc);
        boolean li_ = !stacks.isEmpty() && StringUtil.quickEq(stacks.last(), _rend.getKeyWordLi());
        if (_curr instanceof Text) {
            Text txt_ = (Text) _curr;
            text(_rend, _styleLoc, li_, txt_);
        }
        skipChildrenBuild = false;
        tagName = MetaComponent.EMPTY_STRING;
        if (!(_curr instanceof Element)) {
            return;
        }
        Element elt_ = (Element) _curr;
        tagName = elt_.getTagName();
        boolean newLine_ = false;
        if (StringUtil.quickEq(tagName, _rend.getKeyWordBr())) {
            skipChildrenBuild = true;
            newLine_ = true;
            rowGroup++;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordHr())) {
            skipChildrenBuild = true;
            newLine_ = true;
            rowGroup = 0;
            partGroup++;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordStyle())) {
            //put all style tags
            String css_ = _curr.getTextContent();
            processStyle(css_);
        } else {
            processStyle(elt_.getAttribute(_rend.getKeyWordStyle()));
        }
        MetaContainer curPar_ = currentParent.getParent();
        if (newLine_) {
            if (StringUtil.quickEq(tagName, _rend.getKeyWordHr())) {
                MetaSeparator sep_ = new MetaSeparator(curPar_);
                sep_.setStyle(_styleLoc);
                curPar_.appendChild(sep_);
            }
            MetaEndLine end_ = new MetaEndLine(currentParent);
            end_.setStyle(_styleLoc);
            currentParent.appendChild(end_);
            MetaContainer line_ = new MetaLine(curPar_);
            indent(_styleLoc, li_, line_);
            curPar_.appendChild(line_);
            currentParent = line_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordImg())) {
            img(_rend, _curr, _styleLoc, elt_);
            skipChildrenBuild = true;
            rowGroup = 0;
            partGroup++;
        }
        form(_rend, _indexes, _curr, _styleLoc, li_, elt_, curPar_);
        if (StringUtil.quickEq(tagName, _rend.getKeyWordPar())) {
            MetaContainer surline_ = new MetaLine(curPar_);
            surline_.setStyle(_styleLoc);
            MetaContainer bl_ = new MetaParagraph(surline_);
            bl_.setStyle(_styleLoc);
            MetaContainer preline_ = new MetaLine(bl_);
            preline_.setStyle(_styleLoc);
            MetaEndLine end_ = new MetaEndLine(preline_);
            end_.setStyle(_styleLoc);
            preline_.appendChild(end_);
            bl_.appendChild(preline_);
            MetaContainer line_ = new MetaLine(bl_);
            line_.setStyle(_styleLoc);
            bl_.appendChild(line_);
          //indent
            indent(_styleLoc, li_, surline_);
            surline_.appendChild(bl_);
            curPar_.appendChild(surline_);
            containers.add(curPar_);
            containers.add(bl_);
            currentParent = line_;
        }
        bulletNb(_rend, _styleLoc, li_, elt_, curPar_);
        table(_rend, _styleLoc, li_, curPar_);
        divMap(_rend, _styleLoc, li_, elt_, curPar_);
    }

    private void bulletNb(RendKeyWords _rend, MetaStyle _styleLoc, boolean _li, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordUl()) || StringUtil.quickEq(tagName, _rend.getKeyWordOl())) {
            MetaContainer line_ = new MetaLine(_curPar);
            line_.setStyle(_styleLoc);
            typesLi.add(_elt.getAttribute(_rend.getAttrType()));
            MetaContainer bl_;
            if (StringUtil.quickEq(tagName, _rend.getKeyWordUl())) {
                bl_ = new MetaUnorderedList(line_);
            } else {
                bl_ = new MetaOrderedList(line_);
            }
            bl_.setStyle(_styleLoc);
            //indent
            indent(_styleLoc, _li, line_);
            MetaLabel ind_ = new MetaIndentLabel(line_);
            ind_.setStyle(_styleLoc);
            line_.appendChild(ind_);
            line_.appendChild(bl_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(bl_);
            lis.add(1);
            ordered.add(ComparatorBoolean.of(StringUtil.quickEq(tagName, _rend.getKeyWordOl())));
            currentParent = bl_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordLi())) {
            li(_rend, _styleLoc, _elt);
        }
    }

    private void form(RendKeyWords _rend, IndexButtons _indexes, Node _curr, MetaStyle _styleLoc, boolean _li, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordSelect())) {
            skipChildrenBuild = true;
            rowGroup = 0;
            partGroup++;
            select(_rend, _styleLoc, _elt);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordInput())) {
            skipChildrenBuild = true;
            input(_rend, _indexes, _curr, _styleLoc, _elt);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordTextarea())) {
            skipChildrenBuild = true;
            long idForm_ = getParentFormNb();
            rowGroup = 0;
            partGroup++;
            int rows_ = BeanLgNames.parseInt(_elt.getAttribute(_rend.getAttrRows()),32);
            int cols_ = BeanLgNames.parseInt(_elt.getAttribute(_rend.getAttrCols()),32);
            MetaInput input_ = new MetaTextArea(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), cols_, rows_, _elt.getTextContent(),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordForm())) {
            long lg_ = NumberUtil.parseLongZero(_elt.getAttribute(_rend.getAttrNf()));
            formIndex.add(lg_);
            MetaContainer surline_ = new MetaLine(_curPar);
            surline_.setStyle(_styleLoc);
            MetaForm form_ = new MetaForm(surline_, lg_, _elt);
            form_.setStyle(_styleLoc);
            MetaContainer line_ = new MetaLine(form_);
            line_.setStyle(_styleLoc);
            form_.appendChild(line_);
            forms.add(form_);
            //indent
            indent(_styleLoc, _li, surline_);
            surline_.appendChild(form_);
            _curPar.appendChild(surline_);
            containers.add(_curPar);
            containers.add(form_);
            currentParent = line_;
        }
    }

    private void table(RendKeyWords _rend, MetaStyle _styleLoc, boolean _li, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordTable())) {
            MetaContainer line_ = new MetaLine(_curPar);
            line_.setStyle(_styleLoc);
            MetaTable bl_ = new MetaTable(line_);
            //indent
            indent(_styleLoc, _li, line_);
            bl_.setStyle(_styleLoc);
            line_.appendChild(bl_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(bl_);
            currentParent = bl_;
            tables.add(bl_);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordCaption())) {
            rowGroup = 0;
            partGroup++;
            MetaTable table_ = tables.last();
            MetaContainer line_ = new MetaCaption(table_);
            line_.setStyle(_styleLoc);
            line_.setAddEmpty(true);
            table_.addRemainder(false);
            table_.appendChild(line_);
            currentParent = line_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordTd()) || StringUtil.quickEq(tagName, _rend.getKeyWordTh())) {
            rowGroup = 0;
            partGroup++;
            MetaTable table_ = tables.last();
            MetaContainer bl_ = new MetaCell(table_);
            bl_.setStyle(_styleLoc);
            MetaContainer line_ = new MetaLine(bl_);
            line_.setStyle(_styleLoc);
            bl_.appendChild(line_);
            table_.appendChild(bl_);
            currentParent = line_;
        }
    }

    private void divMap(RendKeyWords _rend, MetaStyle _styleLoc, boolean _li, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordMap())) {
            int width_ = BeanLgNames.parseInt(_elt.getAttribute(_rend.getAttrWidth()),1);
            MetaContainer line_ = new MetaLine(_curPar);
            indent(_styleLoc, _li, line_);
            MetaContainer map_ = new MetaImageMap(line_, width_);
            map_.setStyle(_styleLoc);
            line_.setStyle(_styleLoc);
            line_.appendChild(map_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(map_);
            currentParent = map_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordDiv())) {
            div(_rend, _styleLoc, _li, _elt, _curPar);
        }
    }

    private void removeUseless() {
        for (MetaContainer c: dynamicNewLines) {
            if (c.onlyBlanks()) {
                c.getChildren().clear();
            }
        }
    }

    private void li(RendKeyWords _rend, MetaStyle _styleLoc, Element _elt) {
        rowGroup = 0;
        partGroup++;
        MetaContainer list_ = containers.last();
        MetaContainer bl_ = new MetaListItem(list_);
        MetaContainer line_ = new MetaLine(bl_);
        line_.setStyle(_styleLoc);
        MetaLabel nb_;
        if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiNb())) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiMinLet())) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LETTER);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiMajLet())) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.MAJ_LETTER);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiMinLat())) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MIN);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiMajLat())) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.LATIN_MAJ);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiCircle())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiDisk())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiSquare())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getAttrType()), _rend.getValueLiRect())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.RECT);
        } else if (!typesLi.last().isEmpty()) {
            nb_ = liType(_rend, line_);
        } else if (ordered.last() == BoolVal.TRUE) {
            nb_ = new MetaNumberedLabel(line_, lis.last(), MetaNumberBase.NUMBER);
        } else {
            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
        }
        bl_.setStyle(_styleLoc);
        nb_.setStyle(_styleLoc);
        line_.appendChild(nb_);
        lis.set(lis.getLastIndex(),lis.last()+1);
        bl_.appendChild(line_);
        list_.appendChild(bl_);
        currentParent = line_;
    }

    private MetaLabel liType(RendKeyWords _rend, MetaContainer _line) {
        MetaLabel nb_;
        if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiNb())) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.NUMBER);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMinLet())) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.LETTER);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMajLet())) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.MAJ_LETTER);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMinLat())) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.LATIN_MIN);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiMajLat())) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.LATIN_MAJ);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiCircle())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.CIRCLE);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiDisk())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.DISK);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiSquare())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.SQUARRE);
        } else if (StringUtil.quickEq(typesLi.last(), _rend.getValueLiRect())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.RECT);
        } else if (ordered.last() == BoolVal.TRUE) {
            nb_ = new MetaNumberedLabel(_line, lis.last(), MetaNumberBase.NUMBER);
        } else {
            nb_ = new MetaPointLabel(_line, MetaPointForm.DISK);
        }
        return nb_;
    }

    private void input(RendKeyWords _rend, IndexButtons _indexesButtons, Node _current, MetaStyle _styleLoc, Element _elt) {
        String type_ = _elt.getAttribute(_rend.getAttrType());
        long idForm_ = getParentFormNb();
        if (StringUtil.quickEq(type_, _rend.getValueText())) {
            int cols_ = BeanLgNames.parseInt(_elt.getAttribute(_rend.getAttrCols()),32);
            MetaInput input_ = new MetaTextField(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), cols_, _elt.getAttribute(_rend.getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getValueNumber())) {
            MetaInput input_ = new MetaSpinner(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), _elt.getAttribute(_rend.getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getValueRange())) {
            MetaInput input_ = new MetaSlider(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), _elt.getAttribute(_rend.getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getValueCheckbox())) {
            MetaInput input_ = new MetaCheckedBox(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), _elt.hasAttribute(_rend.getAttrChecked()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getValueRadio())) {
            int inputNb_ = NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi()));
            FormInputCoords id_ = new FormInputCoords();
            id_.setForm(idForm_);
            id_.setInput(inputNb_);
            int ind_ = _indexesButtons.addOrIncr(id_);
            MetaInput input_ = new MetaRadioButton(currentParent, inputNb_, ind_, _elt.hasAttribute(_rend.getAttrChecked()), _elt.getAttribute(_rend.getAttrValue()),id_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else {
            //button
            Element form_ = null;
            Element par_ = _current.getParentNode();
            while (par_ != null) {
                String tagNamePar_ = par_.getTagName();
                if (StringUtil.quickEq(tagNamePar_, _rend.getKeyWordForm()) && form_ == null) {
                    form_ = par_;
                }
                par_ = par_.getParentNode();
            }
            MetaInput button_ = new MetaButton(currentParent, -1, form_, _elt.getAttribute(_rend.getAttrValue()),idForm_);
            button_.setStyle(_styleLoc);
            currentParent.appendChild(button_);
        }
        rowGroup = 0;
        partGroup++;
    }

    private void select(RendKeyWords _rend, MetaStyle _styleLoc, Element _elt) {
        long idForm_ = getParentFormNb();
        if (_elt.hasAttribute(_rend.getAttrMultiple())) {
            Ints selected_ = new Ints();
            StringList values_ = new StringList();
            StringList strings_ = new StringList();
            int i_ = 0;
            for (Element c: _elt.getChildElements()) {
                if (c.hasAttribute(_rend.getAttrSelected())) {
                    selected_.add(i_);
                }
                values_.add(c.getAttribute(_rend.getAttrValue()));
                strings_.add(c.getTextContent());
                i_++;
            }
            int vis_ = BeanLgNames.parseInt(_elt.getAttribute(_rend.getAttrRows()),8);
            MetaInput input_ = new MetaComboList(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), strings_, values_, selected_, vis_,idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else {
            int selected_ = 0;
            Ints selInd_ = new Ints();
            StringList values_ = new StringList();
            StringList strings_ = new StringList();
            int i_ = 0;
            for (Element c: _elt.getChildElements()) {
                if (c.hasAttribute(_rend.getAttrSelected())) {
                    selected_ = i_;
                    selInd_.add(i_);
                }
                values_.add(c.getAttribute(_rend.getAttrValue()));
                strings_.add(c.getTextContent());
                i_++;
            }
            MetaInput input_ = new MetaComboBox(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrNi())), strings_, values_, selected_,selInd_,idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        }
    }

    private void div(RendKeyWords _rend, MetaStyle _styleLoc, boolean _li, Element _elt, MetaContainer _curPar) {
        int index_ = 1;
        int i_ = 0;
        Node first_ = _elt.getFirstChild();
        while (first_ != null) {
            if (first_ instanceof Element && (StringUtil.quickEq(((Element) first_).getTagName(), _rend.getKeyWordBr()) || StringUtil.quickEq(((Element) first_).getTagName(), _rend.getKeyWordHr()))) {
                index_ = i_;
                break;
            }
            first_ = first_.getNextSibling();
            if (first_ instanceof Element) {
                i_++;
            }
        }
        MetaContainer line_ = new MetaLine(_curPar);
        indent(_styleLoc, _li, line_);
        MetaContainer map_ = new MetaImageMap(line_, index_);
        map_.setStyle(_styleLoc);
        line_.setStyle(_styleLoc);
        line_.appendChild(map_);
        _curPar.appendChild(line_);
        containers.add(_curPar);
        containers.add(map_);
        currentParent = map_;
    }

    private void img(RendKeyWords _rend, Node _current, MetaStyle _styleLoc, Element _elt) {
        String title_ = MetaComponent.EMPTY_STRING;
        Element anchor_ = null;
        Element par_ = _current.getParentNode();
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
        if (_elt.getAttribute(_rend.getAttrSrc()).contains(SEP_IMG)) {
            int delay_ = NumberUtil.parseInt(_elt.getAttribute(_rend.getAttrDelay()));
            MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent, StringUtil.splitStrings(_elt.getAttribute(_rend.getAttrSrc()), SEP_IMG), title_, delay_, anchor_, _rend);
            imgs_.setStyle(_styleLoc);
            currentParent.appendChild(imgs_);
        } else {
            MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, _elt.getAttribute(_rend.getAttrSrc()), title_, anchor_, _rend);
            imgs_.setStyle(_styleLoc);
            currentParent.appendChild(imgs_);
        }
    }

    private void text(RendKeyWords _rend, MetaStyle _styleLoc, boolean _li, Text _txt) {
        String realText_ = _txt.getTextContent();
        String text_ = realText_.trim();
        title = MetaComponent.EMPTY_STRING;
        anchor = null;
        Element par_ = _txt.getParentNode();
        bold = 0;
        italic = 0;
        pre = false;
        ht = null;
        delta = 0;
        while (par_ != null) {
            attribute(_rend, par_);
            par_ = par_.getParentNode();
        }
        _styleLoc.setDelta(delta);
        _styleLoc.setBold(bold);
        _styleLoc.setItalic(italic);
        if (!text_.isEmpty() && !pre) {
            int begin_ = 0;
            int end_ = realText_.length() - 1;
            if (trimLeftText(_txt, _rend)) {
                begin_ = StringUtil.getFirstPrintableCharIndex(realText_);
                begin_ = Math.max(begin_,0);
            }
            if (trimRightText(_txt, _rend)) {
                end_ = StringUtil.getLastPrintableCharIndex(realText_);
                end_ = Math.max(end_,begin_-1);
            }
            text_ = realText_.substring(begin_, end_ + 1);
            StringBuilder adjustedText_ = new StringBuilder(text_.length());
            adj(text_, adjustedText_);
            text_ = adjustedText_.toString();
            MetaSearchableLabel label_;
            if (anchor == null) {
                label_ = new MetaPlainLabel(currentParent, text_, title, partGroup, rowGroup);
            } else {
                String name_ = anchor.getAttribute(_rend.getAttrName());
                label_ = new MetaAnchorLabel(currentParent, text_, title, anchor, partGroup, rowGroup, _rend);
                if (!name_.isEmpty()) {
                    anchorsRef.put(name_, (MetaAnchorLabel) label_);
                }
            }
            label_.setStyle(_styleLoc);
            currentParent.appendChild(label_);
        } else if (pre) {
            pre(_rend, _styleLoc, _li, realText_);
        }
    }

    private void attribute(RendKeyWords _rend, Element _par) {
        String tagName_ = _par.getTagName();
        if (!_par.getAttribute(_rend.getAttrTitle()).isEmpty() && title.isEmpty()) {
            title = _par.getAttribute(_rend.getAttrTitle());
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordAnchor()) && anchor == null) {
            anchor = _par;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordBold())) {
            bold = 1;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordItalic())) {
            italic = 2;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordPre())) {
            pre = true;
        }
        if (ht != null) {
            return;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHOne())) {
            ht = _par;
            delta= 6;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHTwo())) {
            ht = _par;
            delta= 5;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHThree())) {
            ht = _par;
            delta= 4;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFour())) {
            ht = _par;
            delta= 3;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHFive())) {
            ht = _par;
            delta= 2;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordHSix())) {
            ht = _par;
            delta= 1;
        }
    }

    private void pre(RendKeyWords _rend, MetaStyle _style, boolean _li, String _real) {
        StringList strings_ = StringUtil.splitStrings(_real, LF, CRLF);
        int nbLines_ = strings_.size();
        int indLine_ = 0;
        for (String l: strings_) {
            linePre(_rend, _style, _li, _real, nbLines_, indLine_, l);
            indLine_++;
        }
        rowGroup--;
    }

    private void linePre(RendKeyWords _rend, MetaStyle _style, boolean _li, String _real, int _nbLines, int _indLine, String _l) {
        StringBuilder line_ = new StringBuilder(_real.length());
        for (char c: _l.toCharArray()) {
            if (c == '\t') {
                for (int i = 0; i < tabWidth; i++) {
                    line_.append(SPACE);
                }
            } else {
                line_.append(c);
            }
        }
        String text_ = line_.toString();
        MetaSearchableLabel label_;
        if (anchor == null) {
            label_ = new MetaPlainLabel(currentParent, text_, title, partGroup, rowGroup);
        } else {
            String name_ = anchor.getAttribute(_rend.getAttrName());
            label_ = new MetaAnchorLabel(currentParent, text_, title, anchor, partGroup, rowGroup, _rend);
            if (!name_.isEmpty()) {
                anchorsRef.put(name_, (MetaAnchorLabel) label_);
            }
        }
        rowGroup++;
        label_.setStyle(_style);
        currentParent.appendChild(label_);
        if (_indLine + 1 < _nbLines) {
            MetaEndLine end_ = new MetaEndLine(currentParent);
            end_.setStyle(_style);
            currentParent.appendChild(end_);
            MetaContainer curPar_ = currentParent.getParent();
            MetaContainer lineBl_ = new MetaLine(curPar_);
            indent(_style, _li, lineBl_);
            curPar_.appendChild(lineBl_);
            currentParent = lineBl_;
        }
    }

    private void adj(String _txt, StringBuilder _adjustedText) {
        int len_ = _txt.length();
        int i_ = 0;
        while (i_ < len_) {
            char currentChar_ = _txt.charAt(i_);
            i_++;
            if (StringUtil.isWhitespace(currentChar_)) {
                _adjustedText.append(SPACE);
                while (i_ < len_) {
                    currentChar_ = _txt.charAt(i_);
                    if (!StringUtil.isWhitespace(currentChar_)) {
                        break;
                    }
                    i_++;
                }
            } else {
                _adjustedText.append(currentChar_);
            }
        }
    }

    private void updateSty(RendKeyWords _rend, Node _curr, MetaStyle _stLoc) {
        Element parStyle_ = _curr.getParentNode();
        IntTreeMap< String> tags_ = new IntTreeMap< String>(new CollCapacity(tagsClasses.size()));
        for (EntryCust<String, Integer> e: tagsClasses.entryList()) {
            tags_.put(e.getValue(), e.getKey());
        }
        if (_curr instanceof Element) {
            Element currentElt_ = (Element) _curr;
            setupGeneStyle(_rend, _stLoc, tags_, currentElt_, true);
        }
        while (parStyle_ != null) {
            setupGeneStyle(_rend, _stLoc, tags_, parStyle_, false);
            parStyle_ = parStyle_.getParentNode();
        }
    }

    private void style(Document _document, RendKeyWords _rend) {
        for (Element e: _document.getElementsByTagName(_rend.getKeyWordHead())) {
            for (Element s: e.getElementsByTagName(_rend.getKeyWordStyle())) {
                processStyle(s.getTextContent());
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
            String result_ = styleIt(_rend, v_, _currentElt);
            if (result_ != null) {
                setupStyle(_rend,_styleLoc, result_, _local);
                break;
            }
            j_--;
        }
    }
    private String styleIt(RendKeyWords _rend, String _tag, Element _currentElt) {
        int f_ = _tag.indexOf('.');
        if (f_ == 0) {
            if (StringUtil.quickEq(_tag.substring(1), _currentElt.getAttribute(_rend.getAttrClass()))) {
                return StringUtil.nullToEmpty(classesCssStyles.getVal(_tag.substring(1)));
            }
        } else {
            if (tagsClassesCssStyles.contains(_tag)) {
                String tag_ = _tag.substring(0, f_);
                String class_ = _tag.substring(f_ + 1);
                if (StringUtil.quickEq(class_, _currentElt.getAttribute(_rend.getAttrClass())) && StringUtil.quickEq(tag_, _currentElt.getTagName())) {
                    return StringUtil.nullToEmpty(tagsClassesCssStyles.getVal(_tag));
                }
            }
            if (StringUtil.quickEq(_tag, _currentElt.getTagName())) {
                return StringUtil.nullToEmpty(tagsCssStyles.getVal(_tag));
            }
        }
        return null;
    }

    private static void setupStyle(RendKeyWords _rend,MetaStyle _style, String _value, boolean _local) {
        for (String p: StringUtil.splitChars(_value, ';')) {
            attrStyle(_rend, _style, _local, p);
        }
    }

    private static void attrStyle(RendKeyWords _rend, MetaStyle _style, boolean _local, String _p) {
        int indexSep_ = _p.indexOf(':');
        if (indexSep_ < 0) {
            return;
        }
        String key_ = _p.substring(0, indexSep_).trim();
        String value_ = _p.substring(indexSep_ + 1).trim();
        if (StringUtil.quickEq(key_, _rend.getStyleAttrFontFam())) {
            _style.setFontFamily(value_);
            return;
        }
        String styleUnitPx_ = _rend.getStyleUnitPx();
        if (StringUtil.quickEq(key_, _rend.getStyleAttrFontSize())) {
            if (value_.endsWith(styleUnitPx_)) {
                String size_ = value_.substring(0, value_.length() - styleUnitPx_.length());
                int val_ = NumberUtil.parseInt(size_);
                _style.setSize(val_);
            }
            String styleUnitEm_ = _rend.getStyleUnitEm();
            if (value_.endsWith(styleUnitEm_)) {
                String size_ = value_.substring(0, value_.length() - styleUnitEm_.length());
                int val_ = NumberUtil.parseInt(size_);
                _style.setEm(val_);
            }
            return;
        }
        if (StringUtil.quickEq(key_, _rend.getStyleAttrColor())) {
            _style.setFgColor(getColor(_rend,value_, _style.getFgColor()));
        } else if (StringUtil.quickEq(key_, _rend.getStyleAttrBackground())) {
            _style.setBgColor(getColor(_rend,value_, _style.getBgColor()));
        } else if (StringUtil.quickEq(key_, _rend.getStyleAttrBorder())) {
            if (!_local) {
                return;
            }
            for (String v: StringUtil.splitChars(value_, ' ','\t','\n','\r')) {
                borderStyle(_rend, _style, v);
            }
        }
    }

    private static void borderStyle(RendKeyWords _rend, MetaStyle _style, String _v) {
        String styleUnitPx_ = _rend.getStyleUnitPx();
        if (_v.endsWith(styleUnitPx_)) {
            String size_ = _v.substring(0, _v.length() - styleUnitPx_.length());
            int val_ = NumberUtil.parseInt(size_);
            _style.setBorderSize(val_);
            return;
        }
        if (StringUtil.quickEq(_v, _rend.getStyleUnitSolid())){
            _style.setBorder(BorderEnum.SOLID);
            return;
        }
        _style.setBorderColor(getColor(_rend, _v, _style.getBorderColor()));
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
            iterate(nbOpened_, key_, str_, currentChar_);
            i_++;
        }
    }

    private void iterate(int _nb, StringBuilder _key, StringBuilder _str, char _curr) {
        if (_nb == 0) {
            if (_curr != '}') {
                _key.append(_curr);
            } else {
                styles(_key, _str);
                _key.delete(0, _key.length());
                _str.delete(0, _str.length());
            }
        } else {
            if (_curr != '{' || _nb > 1) {
                _str.append(_curr);
            }
        }
    }

    private void styles(StringBuilder _key, StringBuilder _str) {
        for (String p: StringUtil.splitChars(_key.toString(), ',')) {
            String tr_ = p.trim();
            int f_ = tr_.indexOf('.');
            if (f_ == 0) {
                classesCssStyles.put(tr_.substring(1), _str.toString());
            } else if (f_ > 0) {
                tagsClassesCssStyles.put(tr_, _str.toString());
            } else {
                tagsCssStyles.put(tr_, _str.toString());
            }
            tagsClasses.put(tr_, indexTagClass);
            indexTagClass++;
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
        return StringUtil.quickEq(tagName_, _rend.getKeyWordHOne()) || StringUtil.quickEq(tagName_, _rend.getKeyWordHTwo()) || StringUtil.quickEq(tagName_, _rend.getKeyWordHThree()) || StringUtil.quickEq(tagName_, _rend.getKeyWordHFour()) || StringUtil.quickEq(tagName_, _rend.getKeyWordHFive()) || StringUtil.quickEq(tagName_, _rend.getKeyWordHSix());
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
        indentNb(_rend, line_);
    }

    private void indentNb(RendKeyWords _rend, MetaContainer _line) {
        boolean li_ = !stacks.isEmpty() && StringUtil.quickEq(stacks.last(), _rend.getKeyWordLi());
        if (li_ && _line != null) {
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
