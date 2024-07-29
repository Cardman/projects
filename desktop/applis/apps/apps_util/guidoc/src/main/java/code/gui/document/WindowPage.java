package code.gui.document;



import code.formathtml.render.*;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class WindowPage implements Runnable {

    private final RenderedPage page;

    private final AbsScrollPane frame;

    private final MetaDocument meta;

    public WindowPage(MetaDocument _meta, AbsScrollPane _frame, RenderedPage _page) {
        page = _page;
        meta = _meta;
        frame = _frame;
    }

    @Override
    public void run() {
        page.setPage(new DualPanel(null, meta.getRoot(), page));
        page.setFinding(meta);
        page.getAnims().clear();
        MetaComponent metaroot_ = meta.getRoot();
        IntComponent meta_ = metaroot_.getFirstChildCompo();
        DualContainer cur_ = page.getPage();
        CustList<CustList<FormInputCoordsButtonGroup>> radiosGroup_ = new CustList<CustList<FormInputCoordsButtonGroup>>();
        while (meta_ != null) {
            if (meta_ instanceof MetaContainer) {
                MetaContainer container_ = (MetaContainer) meta_;
                DualContainer nextCont_ = metaCont(cur_, radiosGroup_, container_);
                IntComponent firstChild_ = container_.getFirstChildCompo();
                if (nextCont_ != null) {
                    meta_ = firstChild_;
                    cur_ = nextCont_;
                    continue;
                }
            } else {
                leaf(meta_, cur_, radiosGroup_);
            }
            while (meta_ != null) {
                IntComponent nextSibling_ = meta_.getNextSibling();
                if (nextSibling_ != null) {
                    meta_ = nextSibling_;
                    break;
                }
                IntComponent parent_ =  meta_.getParentCompo();
                adjustHeight(cur_);
                cur_ = cur_.getContainer();
                remGr(radiosGroup_, parent_);
                meta_ = parOrNull(metaroot_, parent_);
            }
        }
        frame.setViewportView(page.getPage().getGraphic());
        frame.validate();
        page.directScroll(meta);
        startAnims();
        page.enableLgButtons(true);
    }

    private void adjustHeight(DualComponent _cur) {
        IntComponent fi_ = _cur.getFirstChildCompo();
        if (fi_ instanceof DualIndentNbLabel) {
            IntComponent n_ = fi_.getNextSibling();
            int max_ = ((DualComponent)fi_).getGraphic().getPreferredSizeValue().getHeight();
            while (n_ instanceof DualComponent) {
                max_ = NumberUtil.max(max_,((DualComponent)n_).getGraphic().getPreferredSizeValue().getHeight());
                n_ = n_.getNextSibling();
            }
            ((DualIndentNbLabel) fi_).setRate(max_);
            ((DualIndentNbLabel) fi_).paint();
        }
    }

    private IntComponent parOrNull(MetaComponent _root, IntComponent _par) {
        IntComponent meta_;
        if (_par == _root) {
            meta_ = null;
        } else {
            meta_ = _par;
        }
        return meta_;
    }

    private void startAnims() {
        for (DualAnimatedImage a: page.getAnims()) {
            a.start();
        }
    }

    private void remGr(CustList<CustList<FormInputCoordsButtonGroup>> _radios, IntComponent _par) {
        if (_par instanceof MetaForm) {
            _radios.removeLast();
        }
    }

    private void leaf(IntComponent _meta, DualContainer _cur, CustList<CustList<FormInputCoordsButtonGroup>> _radios) {
        if (_meta instanceof MetaPlainLabel) {
            MetaPlainLabel lab_ = (MetaPlainLabel) _meta;
            _cur.add(new DualPlainLabel(_cur, lab_, page));
            return;
        }
        if (_meta instanceof MetaAnchorLabel) {
            MetaAnchorLabel lab_ = (MetaAnchorLabel) _meta;
            _cur.add(new DualAnchoredLabel(_cur, lab_, page));
            return;
        }
        if (_meta instanceof MetaSeparator) {
            _cur.add(new DualSeparator(_cur, (MetaComponent) _meta, page));
            return;
        }
        if (_meta instanceof MetaSimpleImage) {
            _cur.add(new DualSimpleImage(_cur, (MetaSimpleImage) _meta, page));
            return;
        }
        if (_meta instanceof MetaAnimatedImage) {
            DualAnimatedImage a_ = new DualAnimatedImage(_cur, (MetaAnimatedImage) _meta, page);
            _cur.add(a_);
            return;
        }
        if (_meta instanceof MetaIndentLabel) {
            _cur.add(new DualIndentLabel(_cur, (MetaIndentLabel) _meta, page));
            return;
        }
        if (_meta instanceof MetaIndentNbLabel) {
            int width_ = width((MetaIndentNbLabel) _meta);
            _cur.add(new DualIndentNbLabel(_cur, (MetaIndentNbLabel) _meta, page, width_));
            return;
        }
        if (_meta instanceof MetaNumberedLabel) {
            _cur.add(new DualNumberedLabel(_cur, (MetaNumberedLabel) _meta, page));
            return;
        }
        if (_meta instanceof MetaPointLabel) {
            _cur.add(new DualMetaPointLabel(_cur, (MetaPointLabel) _meta, page));
            return;
        }
        input(_meta, _cur, _radios);
    }

    private void input(IntComponent _meta, DualContainer _cur, CustList<CustList<FormInputCoordsButtonGroup>> _radios) {
        if (_meta instanceof MetaButton) {
            _cur.add(new DualButton(_cur, (MetaButton) _meta, page));
            return;
        }
        if (_meta instanceof MetaTextField) {
            _cur.add(new DualTextField(_cur, (MetaTextField) _meta, page));
            return;
        }
        if (_meta instanceof MetaSpinner) {
            _cur.add(new DualSpinner(_cur, (MetaSpinner) _meta, page));
            return;
        }
        if (_meta instanceof MetaSlider) {
            _cur.add(new DualSlider(_cur, (MetaSlider) _meta, page));
            return;
        }
        if (_meta instanceof MetaTextArea) {
            _cur.add(new DualTextArea(_cur, (MetaTextArea) _meta, page));
            return;
        }
        if (_meta instanceof MetaCheckedBox) {
            _cur.add(new DualCheckedBox(_cur, (MetaCheckedBox) _meta, page));
            return;
        }
        if (_meta instanceof MetaRadioButton) {
            MetaRadioButton radio_ = (MetaRadioButton) _meta;
            DualRadionButton dual_ = new DualRadionButton(_cur, radio_, page);
            _cur.add(dual_);
            AbsRadioButton radioButton_ = dual_.getRadio();
            processRadio(_radios, radio_, radioButton_, dual_.getValue());
//                if (!radiosGroup_.isEmpty()) {
//                    String value_ = dual_.getValue();
//                    CustList<FormInputCoordsButtonGroup> grs_ = radiosGroup_.last();
//                    if (radio_.getIndexButton() == 0) {
//                        CustButtonGroup gr_ = new CustButtonGroup();
//                        grs_.put(radio_.getId(), gr_);
//                        gr_.add(radioButton_,value_);
//                    } else {
//                        grs_.getVal(radio_.getId()).add(radioButton_,value_);
//                    }
//                }
            return;
        }
        if (_meta instanceof MetaComboBox) {
            _cur.add(new DualComboBox(_cur, (MetaComboBox) _meta, page));
            return;
        }
        if (_meta instanceof MetaComboList) {
            _cur.add(new DualComboList(_cur, (MetaComboList) _meta, page));
        }
    }


    private int width(MetaIndentNbLabel _meta) {
        int width_ = _meta.getStyle().getEmToPixels();
        MetaOrderedList gr_ = _meta.getOrdered();
        int len_ = gr_.getNumbers().size();
        for (int i = 0; i < len_; i++) {
            MetaNumberedLabel head_ = gr_.getNumbers().get(i);
            MetaStyle style_ = head_.getStyle();
            MetaFont copy_ = DualLabel.newFont(style_);
            int w_ = page.getCompoFactory().stringWidth(copy_, head_.getText() + DualNumberedLabel.PAD);
            width_ = NumberUtil.max(width_, w_);
        }
        return width_;
    }

    private DualContainer metaCont(DualContainer _cur, CustList<CustList<FormInputCoordsButtonGroup>> _radios, MetaContainer _cont) {
        int count_ = _cont.getChildren().size();
        if (count_ > 0 || _cont.isAddEmpty()) {
            DualContainer res_ = build(_cur, _radios, _cont);
            _cur.add(res_);
            if (_cont.containsOnlyEndLine()) {
                AbsPanel c_ = (AbsPanel) _cur.getGraphic();
                int em_ = _cur.getComponent().getStyle().getEmToPixels();
                _cur.getChildren().last().getGraphic().setPreferredSize(new MetaDimension(em_, page.getCompoFactory().heightFont(c_.getMetaFont())));
            }
            return res_;
        }
        return null;
    }
    private DualContainer build(DualContainer _cur, CustList<CustList<FormInputCoordsButtonGroup>> _radios, MetaContainer _cont) {
        if (_cont instanceof MetaTable) {
            return new DualTable(_cur,(MetaTable) _cont, page);
        }
        if (_cont instanceof MetaImageMap) {
            return new DualImageMap(_cur,(MetaImageMap) _cont, page);
        }
        if (_cont instanceof MetaForm) {
            _radios.add(new CustList<FormInputCoordsButtonGroup>());
            return new DualForm(_cur, (MetaForm) _cont, page);
        }
        return new DualPanel(_cur, _cont, page);
    }

    public static void processRadio(CustList<CustList<FormInputCoordsButtonGroup>> _lists, MetaRadioButton _meta, AbsRadioButton _radio, String _value) {
        if (!_lists.isEmpty()) {
            CustList<FormInputCoordsButtonGroup> grs_ = _lists.last();
            retrieve(grs_,_meta).add(_radio,_value);
        }
    }
    public static CustButtonGroup retrieve(CustList<FormInputCoordsButtonGroup> _ls, MetaRadioButton _meta) {
        for (FormInputCoordsButtonGroup f: _ls) {
            if (_meta.getId().eq(f.getInputCoords())) {
                return f.getGroup();
            }
        }
        CustButtonGroup gr_ = new CustButtonGroup();
        _ls.add(new FormInputCoordsButtonGroup(_meta.getId(), gr_));
        return gr_;
    }
}
