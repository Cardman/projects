package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.IndexButtons;
import code.sml.*;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.CharacterCaseConverter;

public final class MetaDocument {

    public static final String SPACE = " ";
    public static final String SEP_IMG = "=";
    private static final String LF = "\n";
    private static final String CRLF = "\r\n";
    private static final String COLOR_PREFIX = "#";
    private final MetaBlock root;

    private MetaContainer currentParent;
    private int partGroup;
    private int rowGroup;
    private final CustList<MetaContainer> containers;
    private final CustList<MetaTable> tables;
//    private final Ints lis;
    private final CustList<MetaListOrderInfos> orderInfos = new CustList<MetaListOrderInfos>();
//    private final CustList<BoolVal> ordered;
    private final CustList<MetaContainer> dynamicNewLines = new CustList<MetaContainer>();
    private final Longs formIndex = new Longs();
    private final StringMap<MetaSearchableLabel> anchorsRef = new StringMap<MetaSearchableLabel>();
    private final StringMap<String> classesCssStyles = new StringMap<String>();
    private final StringMap<String> tagsCssStyles = new StringMap<String>();
    private final StringMap<String> tagsClassesCssStyles = new StringMap<String>();
    private final IntTreeMap<String> tagsClasses = new IntTreeMap<String>();
//    private final StringList typesLi = new StringList();
    private final CustList<IntForm> forms = new CustList<IntForm>();
    private String tagName = "";
    private boolean skipChildrenBuild;
    private int indexTagClass;
    private String title;
    private Element anchor;
    private String name="";
    private int bold;
    private int italic;
    private boolean pre;
    private Element ht;
    private int delta;
    private final int tabWidth;
    private final IndexButtons indexesButtons = new IndexButtons();

    private MetaDocument(Document _document, RendKeyWordsGroup _rend, String _keyWordDig, CharacterCaseConverter _converter) {
        tabWidth = _document.getTabWidth();
        //process style parse
        style(_document, _rend);
        root = new MetaBlock(null);
        MetaLine mainLine_ = new MetaLine(root);
        root.appendChild(mainLine_);
        currentParent = mainLine_;
        containers = new CustList<MetaContainer>();
        tables = new CustList<MetaTable>();
        partGroup = 0;
        rowGroup = 0;
//        lis = new Ints();
//        ordered = new CustList<BoolVal>();
        ElementList bodies_ = _document.getElementsByTagName(_rend.getKeyWordsTags().getKeyWordBody());
        if (bodies_.isEmpty()) {
            return;
        }
        Element body_ = bodies_.first();
        Node current_ = body_;
        while (current_ != null) {
            eltTxt(_rend, current_, _keyWordDig,_converter);
            Node next_ = current_.getFirstChild();
            if (next_ != null && !skipChildrenBuild) {
                current_ = next_;
                continue;
            }
            while (current_ != null) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element par_ = current_.getParentNode();
                unstack(par_, _rend);
                if (par_ == body_ || par_ == null) {
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        removeUseless();
    }

    private void eltTxt(RendKeyWordsGroup _rend, Node _curr, String _keyWordDig, CharacterCaseConverter _converter) {
        MetaStyle styleLoc_ = new MetaStyle();
        updateSty(_rend, _curr, styleLoc_,_keyWordDig,_converter);
        if (_curr instanceof Text) {
            Text txt_ = (Text) _curr;
            text(_rend, styleLoc_, txt_);
        }
        skipChildrenBuild = false;
        tagName = MetaComponent.EMPTY_STRING;
        if (!(_curr instanceof Element)) {
            return;
        }
        Element elt_ = (Element) _curr;
        tagName = elt_.getTagName();
        boolean newLine_ = false;
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordBr())) {
            skipChildrenBuild = true;
            newLine_ = true;
            rowGroup++;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordHr())) {
            skipChildrenBuild = true;
            newLine_ = true;
            rowGroup = 0;
            partGroup++;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordStyle())) {
            //put all style tags
            String css_ = _curr.getTextContent();
            processStyle(css_);
        } else {
            processStyle(elt_.getAttribute(_rend.getKeyWordsTags().getKeyWordStyle()));
        }
        MetaContainer curPar_ = currentParent.getParent();
        if (newLine_) {
            if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordHr())) {
                MetaSeparator sep_ = new MetaSeparator(curPar_);
                sep_.setStyle(styleLoc_);
                curPar_.appendChild(sep_);
            }
            MetaEndLine end_ = new MetaEndLine(currentParent);
            end_.setStyle(styleLoc_);
            currentParent.appendChild(end_);
            MetaContainer line_ = new MetaLine(curPar_);
            indent(styleLoc_, line_);
            curPar_.appendChild(line_);
            currentParent = line_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordImg())) {
            img(_rend, _curr, styleLoc_, elt_);
            skipChildrenBuild = true;
            rowGroup = 0;
            partGroup++;
        }
        form(_rend, _curr, styleLoc_, elt_, curPar_);
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordPar())) {
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
            indent(styleLoc_, surline_);
            surline_.appendChild(bl_);
            curPar_.appendChild(surline_);
            containers.add(curPar_);
            containers.add(bl_);
            currentParent = line_;
        }
        bulletNb(_rend, styleLoc_, elt_, curPar_);
        table(_rend, styleLoc_, curPar_);
        divMap(_rend, styleLoc_, elt_, curPar_);
    }

    private void bulletNb(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordUl()) || StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordOl())) {
            MetaContainer line_ = new MetaLine(_curPar);
            line_.setStyle(_styleLoc);
            MetaListOrderInfos infos_ = new MetaListOrderInfos();
            infos_.setTypeLi(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()));
            MetaOrderedList bl_;
            if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordUl())) {
                bl_ = new MetaOrderedList(line_, BoolVal.FALSE);
            } else {
                bl_ = new MetaOrderedList(line_, BoolVal.TRUE);
            }
            bl_.setStyle(_styleLoc);
            //indent
            indent(_styleLoc, line_);
            MetaLabel ind_ = new MetaIndentLabel(line_);
            ind_.setStyle(_styleLoc);
            line_.appendChild(ind_);
            line_.appendChild(bl_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(bl_);
            infos_.setLi(1);
            infos_.setOrder(ComparatorBoolean.of(StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordOl())));
            infos_.setContainer(bl_);
            orderInfos.add(infos_);
            currentParent = bl_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordLi())) {
            li(_rend, _styleLoc, _elt);
        }
    }

    private void form(RendKeyWordsGroup _rend, Node _curr, MetaStyle _styleLoc, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordSelect())) {
            skipChildrenBuild = true;
            rowGroup = 0;
            partGroup++;
            select(_rend, _styleLoc, _elt);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordInput())) {
            skipChildrenBuild = true;
            input(_rend, _curr, _styleLoc, _elt);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordTextarea())) {
            skipChildrenBuild = true;
            long idForm_ = getParentFormNb();
            rowGroup = 0;
            partGroup++;
            int rows_ = SetupableAnalyzingDoc.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrRows()),32);
            int cols_ = SetupableAnalyzingDoc.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrCols()),32);
            MetaInput input_ = new MetaTextArea(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), cols_, rows_, _elt.getTextContent(),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordForm())) {
            long lg_ = NumberUtil.parseLongZero(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNf()));
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
            indent(_styleLoc, surline_);
            surline_.appendChild(form_);
            _curPar.appendChild(surline_);
            containers.add(_curPar);
            containers.add(form_);
            currentParent = line_;
        }
    }

    private void table(RendKeyWordsGroup _rend, MetaStyle _styleLoc, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordTable())) {
            MetaContainer line_ = new MetaLine(_curPar);
            line_.setStyle(_styleLoc);
            MetaTable bl_ = new MetaTable(line_);
            //indent
            indent(_styleLoc, line_);
            bl_.setStyle(_styleLoc);
            line_.appendChild(bl_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(bl_);
            currentParent = bl_;
            tables.add(bl_);
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordCaption())&&!tables.isEmpty()) {
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
        if ((StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordTd()) || StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordTh()))&&!tables.isEmpty()) {
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

    private void divMap(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt, MetaContainer _curPar) {
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordMap())) {
            int width_ = SetupableAnalyzingDoc.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrWidth()),1);
            MetaContainer line_ = new MetaLine(_curPar);
            indent(_styleLoc, line_);
            MetaContainer map_ = new MetaImageMap(line_, width_);
            map_.setStyle(_styleLoc);
            line_.setStyle(_styleLoc);
            line_.appendChild(map_);
            _curPar.appendChild(line_);
            containers.add(_curPar);
            containers.add(map_);
            currentParent = map_;
        }
        if (StringUtil.quickEq(tagName, _rend.getKeyWordsTags().getKeyWordDiv())) {
            div(_rend, _styleLoc, _elt, _curPar);
        }
    }

    private void removeUseless() {
        for (MetaContainer c: dynamicNewLines) {
            if (c.onlyBlanks()) {
                c.getChildren().clear();
            }
        }
    }

    private void li(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt) {
        if (orderInfos.isEmpty()) {
            return;
        }
        MetaListOrderInfos info_ = orderInfos.last();
        int liLast_ = info_.getLi();
        String typeLiLast_ = info_.getTypeLi();
        rowGroup = 0;
        partGroup++;
        MetaOrderedList list_ = info_.getContainer();
        MetaListItem bl_ = new MetaListItem(list_);
        MetaContainer line_ = new MetaLine(bl_);
        line_.setStyle(_styleLoc);
        MetaLabel nb_;
        if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiNb())) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.NUMBER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiMinLet())) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.LETTER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiMajLet())) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.MAJ_LETTER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiMinLat())) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.LATIN_MIN, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiMajLat())) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.LATIN_MAJ, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiCircle())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.CIRCLE);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiDisk())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiSquare())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.SQUARRE);
        } else if (StringUtil.quickEq(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType()), _rend.getKeyWordsValues().getValueLiRect())) {
            nb_ = new MetaPointLabel(line_, MetaPointForm.RECT);
        } else if (!typeLiLast_.isEmpty()) {
            nb_ = liType(_rend, line_, info_);
        } else if (info_.getOrder() == BoolVal.TRUE) {
            nb_ = new MetaNumberedLabel(line_, liLast_, MetaNumberBase.NUMBER, _rend.getKeyWordsDefs());
        } else {
            nb_ = new MetaPointLabel(line_, MetaPointForm.DISK);
        }
        bl_.setStyle(_styleLoc);
        nb_.setStyle(_styleLoc);
        line_.appendChild(nb_);
        info_.setLi(liLast_+1);
        bl_.appendChild(line_);
        list_.appendChild(bl_);
        if (nb_ instanceof MetaNumberedLabel) {
            list_.getNumbers().add((MetaNumberedLabel) nb_);
        }
        bl_.setOrdered(list_);
        currentParent = line_;
    }

    private MetaLabel liType(RendKeyWordsGroup _rend, MetaContainer _line, MetaListOrderInfos _info) {
        int liLast_ = _info.getLi();
        String typeLiLast_ = _info.getTypeLi();
        MetaLabel nb_;
        if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiNb())) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.NUMBER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiMinLet())) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.LETTER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiMajLet())) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.MAJ_LETTER, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiMinLat())) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.LATIN_MIN, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiMajLat())) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.LATIN_MAJ, _rend.getKeyWordsDefs());
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiCircle())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.CIRCLE);
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiDisk())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.DISK);
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiSquare())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.SQUARRE);
        } else if (StringUtil.quickEq(typeLiLast_, _rend.getKeyWordsValues().getValueLiRect())) {
            nb_ = new MetaPointLabel(_line, MetaPointForm.RECT);
        } else if (_info.getOrder() == BoolVal.TRUE) {
            nb_ = new MetaNumberedLabel(_line, liLast_, MetaNumberBase.NUMBER, _rend.getKeyWordsDefs());
        } else {
            nb_ = new MetaPointLabel(_line, MetaPointForm.DISK);
        }
        return nb_;
    }

    private void input(RendKeyWordsGroup _rend, Node _current, MetaStyle _styleLoc, Element _elt) {
        String type_ = _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrType());
        long idForm_ = getParentFormNb();
        if (StringUtil.quickEq(type_, _rend.getKeyWordsValues().getValueText())) {
            int cols_ = SetupableAnalyzingDoc.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrCols()),32);
            MetaInput input_ = new MetaTextField(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), cols_, _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getKeyWordsValues().getValueNumber())) {
            MetaInput input_ = new MetaSpinner(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getKeyWordsValues().getValueRange())) {
            MetaInput input_ = new MetaSlider(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getKeyWordsValues().getValueCheckbox())) {
            MetaInput input_ = new MetaCheckedBox(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), _elt.hasAttribute(_rend.getKeyWordsAttrs().getAttrChecked()),idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else if (StringUtil.quickEq(type_, _rend.getKeyWordsValues().getValueRadio())) {
            int inputNb_ = NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi()));
            FormInputCoords id_ = new FormInputCoords();
            id_.setForm(idForm_);
            id_.setInput(inputNb_);
            int ind_ = indexesButtons.addOrIncr(id_);
            MetaInput input_ = new MetaRadioButton(currentParent, inputNb_, ind_, _elt.hasAttribute(_rend.getKeyWordsAttrs().getAttrChecked()), _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()),id_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else {
            //button
            Element form_ = null;
            Element par_ = _current.getParentNode();
            while (par_ != null) {
                String tagNamePar_ = par_.getTagName();
                if (StringUtil.quickEq(tagNamePar_, _rend.getKeyWordsTags().getKeyWordForm()) && form_ == null) {
                    form_ = par_;
                }
                par_ = par_.getParentNode();
            }
            MetaInput button_ = new MetaButton(currentParent, -1, form_, _elt.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()),idForm_);
            button_.setStyle(_styleLoc);
            currentParent.appendChild(button_);
        }
        rowGroup = 0;
        partGroup++;
    }

    private void select(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt) {
        long idForm_ = getParentFormNb();
        if (_elt.hasAttribute(_rend.getKeyWordsAttrs().getAttrMultiple())) {
            Ints selected_ = new Ints();
            StringList values_ = new StringList();
            StringList strings_ = new StringList();
            int i_ = 0;
            for (Element c: _elt.getChildElements()) {
                if (c.hasAttribute(_rend.getKeyWordsAttrs().getAttrSelected())) {
                    selected_.add(i_);
                }
                values_.add(c.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()));
                strings_.add(c.getTextContent());
                i_++;
            }
            int vis_ = SetupableAnalyzingDoc.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrRows()),8);
            MetaInput input_ = new MetaComboList(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), strings_, values_, selected_, vis_,idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        } else {
            int selected_ = -1;
            Ints selInd_ = new Ints();
            StringList values_ = new StringList();
            StringList strings_ = new StringList();
            int i_ = 0;
            for (Element c: _elt.getChildElements()) {
                if (c.hasAttribute(_rend.getKeyWordsAttrs().getAttrSelected())) {
                    selected_ = i_;
                    selInd_.add(i_);
                }
                values_.add(c.getAttribute(_rend.getKeyWordsAttrs().getAttrValue()));
                strings_.add(c.getTextContent());
                i_++;
            }
            MetaInput input_ = new MetaComboBox(currentParent, NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrNi())), strings_, values_, selected_,selInd_,idForm_);
            input_.setStyle(_styleLoc);
            currentParent.appendChild(input_);
        }
    }

    private void div(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt, MetaContainer _curPar) {
        int index_ = 1;
        int i_ = 0;
        Node first_ = _elt.getFirstChild();
        while (first_ != null) {
            if (first_ instanceof Element && (StringUtil.quickEq(((Element) first_).getTagName(), _rend.getKeyWordsTags().getKeyWordBr()) || StringUtil.quickEq(((Element) first_).getTagName(), _rend.getKeyWordsTags().getKeyWordHr()))) {
                index_ = i_;
                break;
            }
            first_ = first_.getNextSibling();
            if (first_ instanceof Element) {
                i_++;
            }
        }
        MetaContainer line_ = new MetaLine(_curPar);
        indent(_styleLoc, line_);
        MetaContainer map_ = new MetaImageMap(line_, index_);
        map_.setStyle(_styleLoc);
        line_.setStyle(_styleLoc);
        line_.appendChild(map_);
        _curPar.appendChild(line_);
        containers.add(_curPar);
        containers.add(map_);
        currentParent = map_;
    }

    private void img(RendKeyWordsGroup _rend, Node _current, MetaStyle _styleLoc, Element _elt) {
        String title_ = MetaComponent.EMPTY_STRING;
        Element anchor_ = null;
        Element par_ = _current.getParentNode();
        while (par_ != null) {
            String tagNamePar_ = par_.getTagName();
            if (!par_.getAttribute(_rend.getKeyWordsAttrs().getAttrTitle()).isEmpty() && title_.isEmpty()) {
                title_ = par_.getAttribute(_rend.getKeyWordsAttrs().getAttrTitle());
            }
            if (StringUtil.quickEq(tagNamePar_, _rend.getKeyWordsTags().getKeyWordAnchor()) && anchor_ == null) {
                anchor_ = par_;
            }
            par_ = par_.getParentNode();
        }
        imgs(_rend, _styleLoc, _elt, title_, anchor_);
//        if (_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrSrc()).contains(SEP_IMG)) {
//            int delay_ = NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrDelay()));
//            MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent, StringUtil.splitStrings(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrSrc()), SEP_IMG), title_, delay_, anchor_, _rend, _absImg);
//            imgs_.setStyle(_styleLoc);
//            currentParent.appendChild(imgs_);
//        } else {
//            MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, _absImg.build(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrSrc())), title_, anchor_, _rend);
//            imgs_.setStyle(_styleLoc);
//            currentParent.appendChild(imgs_);
//        }
    }

    private void imgs(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _elt, String _title, Element _anchor) {
//        for (Attr a: _elt.getAttributes()) {
//            if (a instanceof AbsImgAttr) {
//                MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, ((AbsImgAttr)a).getImg(), _title, _anchor, _rend);
//                imgs_.setStyle(_styleLoc);
//                currentParent.appendChild(imgs_);
//            }
//        }
        for (Attr a: _elt.getAttributes()) {
            if (a instanceof AbsImgAnimAttr) {
                int delay_ = NumberUtil.parseInt(_elt.getAttribute(_rend.getKeyWordsAttrs().getAttrDelay()));
                CustList<int[][]> anim_ = ((AbsImgAnimAttr) a).getAnim();
                if (anim_.size() != 1) {
                    MetaAnimatedImage imgs_ = new MetaAnimatedImage(currentParent, anim_, _title, delay_, _anchor, _rend);
                    imgs_.setStyle(_styleLoc);
                    currentParent.appendChild(imgs_);
                } else {
                    MetaSimpleImage imgs_ = new MetaSimpleImage(currentParent, anim_.get(0), _title, _anchor, _rend);
                    imgs_.setStyle(_styleLoc);
                    currentParent.appendChild(imgs_);
                }
            }
        }
    }

    private void text(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Text _txt) {
        String realText_ = _txt.getTextContent();
        String text_ = realText_.trim();
        title = MetaComponent.EMPTY_STRING;
        anchor = null;
        name = "";
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
                begin_ = NumberUtil.max(begin_,0);
            }
            if (trimRightText(_txt, _rend)) {
                end_ = StringUtil.getLastPrintableCharIndex(realText_);
                end_ = NumberUtil.max(end_,begin_-1);
            }
            text_ = realText_.substring(begin_, end_ + 1);
            StringBuilder adjustedText_ = new StringBuilder(text_.length());
            adj(text_, adjustedText_);
            text_ = adjustedText_.toString();
            label(_rend, _styleLoc, text_);
        } else if (pre) {
            pre(_rend, _styleLoc, realText_);
        }
    }

    private void attribute(RendKeyWordsGroup _rend, Element _par) {
        String tagName_ = _par.getTagName();
        if (!_par.getAttribute(_rend.getKeyWordsAttrs().getAttrTitle()).isEmpty() && title.isEmpty()) {
            title = _par.getAttribute(_rend.getKeyWordsAttrs().getAttrTitle());
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordAnchor()) && anchor == null) {
            anchor = _par;
        }
        if (!_par.getAttribute(_rend.getKeyWordsAttrs().getAttrName()).isEmpty() && name.isEmpty()) {
            name = _par.getAttribute(_rend.getKeyWordsAttrs().getAttrName());
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordBold())) {
            bold = 1;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordItalic())) {
            italic = 2;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordPre())) {
            pre = true;
        }
        title(_rend, _par);
    }

    private void title(RendKeyWordsGroup _rend, Element _par) {
        if (ht != null) {
            return;
        }
        String tagName_ = _par.getTagName();
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHOne())) {
            ht = _par;
            delta= 6;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHTwo())) {
            ht = _par;
            delta= 5;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHThree())) {
            ht = _par;
            delta= 4;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHFour())) {
            ht = _par;
            delta= 3;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHFive())) {
            ht = _par;
            delta= 2;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHSix())) {
            ht = _par;
            delta= 1;
        }
    }

    private void pre(RendKeyWordsGroup _rend, MetaStyle _style, String _real) {
        StringList strings_ = StringUtil.splitStrings(_real, LF, CRLF);
        int nbLines_ = strings_.size();
        for (int i = 0; i < nbLines_; i++) {
            linePre(_rend, _style, _real, nbLines_, i, strings_.get(i));
        }
        rowGroup--;
    }

    private void linePre(RendKeyWordsGroup _rend, MetaStyle _style, String _real, int _nbLines, int _indLine, String _l) {
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
        label(_rend, _style, text_);
        rowGroup++;
        if (_indLine + 1 < _nbLines) {
            MetaEndLine end_ = new MetaEndLine(currentParent);
            end_.setStyle(_style);
            currentParent.appendChild(end_);
            MetaContainer curPar_ = currentParent.getParent();
            MetaContainer lineBl_ = new MetaLine(curPar_);
            indent(_style, lineBl_);
            curPar_.appendChild(lineBl_);
            currentParent = lineBl_;
        }
    }

    private void label(RendKeyWordsGroup _rend, MetaStyle _style, String _text) {
        MetaSearchableLabel label_;
        if (anchor == null) {
            label_ = new MetaPlainLabel(currentParent, _text, title, partGroup, rowGroup);
        } else {
            label_ = new MetaAnchorLabel(currentParent, _text, title, anchor, partGroup, rowGroup, _rend);
        }
        if (!name.isEmpty()) {
            anchorsRef.put(name, label_);
        }
        label_.setStyle(_style);
        currentParent.appendChild(label_);
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

    private void updateSty(RendKeyWordsGroup _rend, Node _curr, MetaStyle _stLoc, String _keyWordDig, CharacterCaseConverter _converter) {
        Element parStyle_ = _curr.getParentNode();
        if (_curr instanceof Element) {
            Element currentElt_ = (Element) _curr;
            setupGeneStyle(_rend, _stLoc, currentElt_, true,_keyWordDig,_converter);
        }
        while (parStyle_ != null) {
            setupGeneStyle(_rend, _stLoc, parStyle_, false,_keyWordDig,_converter);
            parStyle_ = parStyle_.getParentNode();
        }
    }

    private void style(Document _document, RendKeyWordsGroup _rend) {
        for (Element e: _document.getElementsByTagName(_rend.getKeyWordsTags().getKeyWordHead())) {
            for (Element s: e.getElementsByTagName(_rend.getKeyWordsTags().getKeyWordStyle())) {
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

    private void indent(MetaStyle _styleLoc, MetaContainer _surline) {
        MetaListItem li_ = li(_surline);
        if (li_ != null) {
            MetaIndentNbLabel ind_ = new MetaIndentNbLabel(_surline);
            ind_.setStyle(_styleLoc);
            ind_.setOrdered(li_.getOrdered());
            _surline.appendChild(ind_);
        }
    }

    private void setupGeneStyle(RendKeyWordsGroup _rend, MetaStyle _styleLoc, Element _currentElt, boolean _local, String _keyWordDig, CharacterCaseConverter _converter) {
        int len_ = tagsClasses.size();
        int j_ = len_ - 1;
        while (j_ > -1) {
            String v_ = tagsClasses.getValue(j_);
            String result_ = styleIt(_rend, v_, _currentElt);
            if (result_ != null) {
                setupStyle(_rend,_styleLoc, result_, _local,_keyWordDig,_converter);
                break;
            }
            j_--;
        }
    }
    private String styleIt(RendKeyWordsGroup _rend, String _tag, Element _currentElt) {
        int f_ = _tag.indexOf('.');
        if (f_ == 0) {
            if (StringUtil.quickEq(_tag.substring(1), _currentElt.getAttribute(_rend.getKeyWordsAttrs().getAttrClass()))) {
                return StringUtil.nullToEmpty(classesCssStyles.getVal(_tag.substring(1)));
            }
        } else {
            if (f_ > 0) {
                String tag_ = _tag.substring(0, f_);
                String class_ = _tag.substring(f_ + 1);
                if (StringUtil.quickEq(class_, _currentElt.getAttribute(_rend.getKeyWordsAttrs().getAttrClass())) && StringUtil.quickEq(tag_, _currentElt.getTagName())) {
                    return StringUtil.nullToEmpty(tagsClassesCssStyles.getVal(_tag));
                }
            }
            if (StringUtil.quickEq(_tag, _currentElt.getTagName())) {
                return StringUtil.nullToEmpty(tagsCssStyles.getVal(_tag));
            }
        }
        return null;
    }

    private static void setupStyle(RendKeyWordsGroup _rend,MetaStyle _style, String _value, boolean _local, String _keyWordDig, CharacterCaseConverter _converter) {
        for (String p: StringUtil.splitChars(_value, ';')) {
            attrStyle(_rend, _style, _local, p,_keyWordDig,_converter);
        }
    }

    private static void attrStyle(RendKeyWordsGroup _rend, MetaStyle _style, boolean _local, String _p, String _keyWordDig, CharacterCaseConverter _converter) {
        int indexSep_ = _p.indexOf(':');
        if (indexSep_ < 0) {
            return;
        }
        String key_ = _p.substring(0, indexSep_).trim();
        String value_ = _p.substring(indexSep_ + 1).trim();
        if (StringUtil.quickEq(key_, _rend.getKeyWordsStyles().getStyleAttrFontFam())) {
            _style.setFontFamily(value_);
            return;
        }
        String styleUnitPx_ = _rend.getKeyWordsStyles().getStyleUnitPx();
        if (StringUtil.quickEq(key_, _rend.getKeyWordsStyles().getStyleAttrFontSize())) {
            if (value_.endsWith(styleUnitPx_)) {
                String size_ = value_.substring(0, value_.length() - styleUnitPx_.length());
                int val_ = NumberUtil.parseInt(size_);
                _style.setSize(val_);
            }
            String styleUnitEm_ = _rend.getKeyWordsStyles().getStyleUnitEm();
            if (value_.endsWith(styleUnitEm_)) {
                String size_ = value_.substring(0, value_.length() - styleUnitEm_.length());
                int val_ = NumberUtil.parseInt(size_);
                _style.setEm(val_);
            }
            return;
        }
        if (StringUtil.quickEq(key_, _rend.getKeyWordsStyles().getStyleAttrColor())) {
            _style.setFgColor(getColor(_rend,value_, _style.getFgColor(),_keyWordDig,_converter));
        } else if (StringUtil.quickEq(key_, _rend.getKeyWordsStyles().getStyleAttrBackground())) {
            _style.setBgColor(getColor(_rend,value_, _style.getBgColor(),_keyWordDig,_converter));
        } else if (StringUtil.quickEq(key_, _rend.getKeyWordsStyles().getStyleAttrBorder())) {
            if (!_local) {
                return;
            }
            for (String v: StringUtil.splitChars(value_, ' ','\t','\n','\r')) {
                borderStyle(_rend, _style, v,_keyWordDig,_converter);
            }
        }
    }

    private static void borderStyle(RendKeyWordsGroup _rend, MetaStyle _style, String _v, String _keyWordDig, CharacterCaseConverter _converter) {
        String styleUnitPx_ = _rend.getKeyWordsStyles().getStyleUnitPx();
        if (_v.endsWith(styleUnitPx_)) {
            String size_ = _v.substring(0, _v.length() - styleUnitPx_.length());
            int val_ = NumberUtil.parseInt(size_);
            _style.setBorderSize(val_);
            return;
        }
        if (StringUtil.quickEq(_v, _rend.getKeyWordsStyles().getStyleUnitSolid())){
            _style.setBorder(BorderEnum.SOLID);
            return;
        }
        _style.setBorderColor(getColor(_rend, _v, _style.getBorderColor(),_keyWordDig,_converter));
    }

    private static int getColor(RendKeyWordsGroup _rend,String _value, int _default, String _keyWordDig, CharacterCaseConverter _converter) {
        if (_value.startsWith(_rend.getKeyWordsStyles().getStyleValueRgb())&&_value.substring(_rend.getKeyWordsStyles().getStyleValueRgb().length()).trim().indexOf('(')==0) {
            String val_ = StringUtil.removeChars(_value.substring(_rend.getKeyWordsStyles().getStyleValueRgb().length()), '(', ')');
            return getRgb(val_);
        }
        if (_value.startsWith(COLOR_PREFIX)) {
            return geneHex(_value, _default, _keyWordDig, _converter);
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueRed())){
            return 255*256*256;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueGreen())){
            return 255*256;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueBlue())){
            return 255;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueYellow())){
            return 255*256*256+255*256;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueCyan())){
            return 255*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueMagenta())){
            return 255*256*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueWhite())){
            return 255*256*256 + 255*256 + 255;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueGrey())){
            return 127*256*256 + 127*256 + 127;
        }
        if (StringUtil.quickEq(_value, _rend.getKeyWordsStyles().getStyleValueBlack())){
            return 0;
        }
        return _default;
    }
    private static int geneHex(String _value, int _default, String _keyWordDig, CharacterCaseConverter _converter) {
        String v_ = _value.substring(1);
        Ints tr_ = new Ints();
        for (char c: v_.toCharArray()) {
            int i_ = _converter.index(_keyWordDig, c);
            if (i_ < 0) {
                return _default;
            }
            tr_.add(i_);
        }
        return (int) NumberUtil.buildQuickLong(tr_,16);
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
            int index_ = -1;
            int len_ = tagsClasses.size();
            for (int i =0; i < len_; i++) {
                if (StringUtil.quickEq(tagsClasses.getValue(i),tr_)) {
                    index_ = i;
                    break;
                }
            }
            if (index_ > -1) {
                tagsClasses.setValue(index_,"");
            }
            tagsClasses.put(indexTagClass,tr_);
            indexTagClass++;
        }
    }

    private static boolean trimLeftText(Text _text, RendKeyWordsGroup _rend) {
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
    private static boolean trimRightText(Text _text, RendKeyWordsGroup _rend) {
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
    private static boolean blockElement(Node _elt, RendKeyWordsGroup _rend) {
        if (!(_elt instanceof Element)) {
            return false;
        }
        String tagName_ = ((Element)_elt).getTagName();
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordBody())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordBr())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHr())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordLi())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordOl())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordUl())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordTable())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordTd())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordCaption())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordForm())) {
            return true;
        }
        if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordPar())) {
            return true;
        }
        return StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHOne()) || StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHTwo()) || StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHThree()) || StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHFour()) || StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHFive()) || StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordHSix());
    }
    public StringMap<MetaSearchableLabel> getAnchorsRef() {
        return anchorsRef;
    }
    private void unstack(Element _par, RendKeyWordsGroup _rend) {
        if (_par == null) {
            return;
        }
        unstack(_par.getTagName(), _rend);
    }

    private void unstack(String _last, RendKeyWordsGroup _rend) {
        MetaContainer line_ = null;
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordUl()) || StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordOl())) {
            containers.removeQuicklyLast();
            orderInfos.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordTable())) {
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
            tables.removeQuicklyLast();
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordForm())) {
            formIndex.removeQuicklyLast();
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordPar())) {
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
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordMap()) || StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordDiv())) {
            containers.removeQuicklyLast();
            MetaContainer last_ = containers.last();
            containers.removeQuicklyLast();
            line_ = new MetaLine(last_);
            last_.appendChild(line_);
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordTr())&&!tables.isEmpty()) {
            MetaTable table_ = tables.last();
            table_.addRemainder(true);
        }
        if (line_ != null) {
            currentParent = line_;
        }
        if (StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordTable())||StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordUl()) || StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordOl()) || StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordPar())||StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordMap()) || StringUtil.quickEq(_last, _rend.getKeyWordsTags().getKeyWordDiv())) {
            rowGroup = 0;
            partGroup++;
        }
        indentNb(line_);
    }

    private void indentNb(MetaContainer _line) {
        MetaListItem li_ = li(currentParent);
        if (li_ != null && _line != null) {
            dynamicNewLines.add(currentParent);
            MetaContainer curPar_ = currentParent;
            MetaIndentNbLabel indent_ = new MetaIndentNbLabel(curPar_);
            indent_.setOrdered(li_.getOrdered());
            curPar_.appendChild(indent_);
        }
    }

    private static MetaListItem li(MetaContainer _cur) {
        if (_cur.getParent() instanceof MetaListItem) {
            return (MetaListItem)_cur.getParent();
        }
        return null;
    }

    public MetaBlock getRoot() {
        return root;
    }

    public static MetaDocument newInstance(Document _document, RendKeyWordsGroup _rend, String _keyWordDig, CharacterCaseConverter _converter) {
        return new MetaDocument(_document,_rend,_keyWordDig,_converter);
    }
    public CustList<IntForm> getForms() {
        return forms;
    }
}
