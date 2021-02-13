package code.gui.document;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import code.formathtml.render.*;
import code.formathtml.util.FormInputCoords;
import code.gui.*;
import code.util.CustList;
import code.util.ObjectMap;

public final class WindowPage implements Runnable {

    private final RenderedPage page;

    private final ScrollPane frame;

    private final MetaDocument meta;

    public WindowPage(MetaDocument _meta, ScrollPane _frame, RenderedPage _page) {
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
        DualComponent cur_ = page.getPage();
        CustList<ObjectMap<FormInputCoords,CustButtonGroup>> radiosGroup_ = new CustList<ObjectMap<FormInputCoords,CustButtonGroup>>();
        while (true) {
            if (meta_ instanceof MetaContainer) {
                MetaContainer container_ = (MetaContainer) meta_;
                int count_ = container_.getChildren().size();
                if (count_ > 0 || container_.isAddEmpty()) {
                    if (container_ instanceof MetaTable) {
                        cur_.add(new DualTable((DualContainer) cur_,(MetaTable) container_, page));
                    } else if (container_ instanceof MetaImageMap) {
                        cur_.add(new DualImageMap((DualContainer) cur_,(MetaImageMap) container_, page));
                    } else if (container_ instanceof MetaForm) {
                        radiosGroup_.add(new ObjectMap<FormInputCoords,CustButtonGroup>());
                        cur_.add(new DualForm((DualContainer) cur_, (MetaForm) container_, page));
                    } else {
                        cur_.add(new DualPanel((DualContainer) cur_,container_, page));
                    }
                    if (container_.containsOnlyEndLine()) {
                        Panel c_ = (Panel) cur_.getGraphic();
                        Font font_ = c_.getFont();
                        FontMetrics fMetrics_ = c_.getFontMetrics(font_);
                        int em_ = cur_.getComponent().getStyle().getEmToPixels();
                        cur_.getChildren().last().getGraphic().setPreferredSize(new Dimension(em_, fMetrics_.getHeight()));
                    }
                }
                IntComponent firstChild_ = container_.getFirstChildCompo();
                if (firstChild_ != null) {
                    meta_ = firstChild_;
                    cur_ = cur_.getChildren().last();
                    continue;
                }
            } else if (meta_ instanceof MetaPlainLabel) {
                MetaPlainLabel lab_ = (MetaPlainLabel) meta_;
                cur_.add(new DualPlainLabel((DualContainer) cur_,lab_, page));
            } else if (meta_ instanceof MetaAnchorLabel) {
                MetaAnchorLabel lab_ = (MetaAnchorLabel) meta_;
                cur_.add(new DualAnchoredLabel((DualContainer) cur_,lab_, page));
            } else if (meta_ instanceof MetaSeparator) {
                cur_.add(new DualSeparator((DualContainer) cur_,(MetaComponent) meta_, page));
            } else if (meta_ instanceof MetaSimpleImage) {
                cur_.add(new DualSimpleImage((DualContainer) cur_,(MetaSimpleImage) meta_, page));
            } else if (meta_ instanceof MetaAnimatedImage) {
                DualAnimatedImage a_ = new DualAnimatedImage((DualContainer) cur_,(MetaAnimatedImage) meta_, page);
                cur_.add(a_);
            } else if (meta_ instanceof MetaIndentLabel) {
                cur_.add(new DualIndentLabel((DualContainer) cur_,(MetaIndentLabel) meta_, page));
            } else if (meta_ instanceof MetaIndentNbLabel) {
                MetaContainer par_ = ((MetaIndentNbLabel)meta_).getParent();
                while (!(par_ instanceof MetaListItem)) {
                    par_ = par_.getParent();
                }
                MetaListItem li_ = (MetaListItem) par_;
                MetaContainer gr_ = li_.getParent();
                int width_ = ((MetaIndentNbLabel)meta_).getStyle().getEmToPixels();
                Panel p_ = (Panel) cur_.getGraphic();
                Font font_ = p_.getFont();
                FontMetrics fontMetrics_ = p_.getFontMetrics(font_);
                if (gr_ instanceof MetaOrderedList) {
                    int len_ = gr_.getChildren().size();
                    for (int i = 0; i < len_; i++) {
                        width_ = Math.max(width_, fontMetrics_.stringWidth(Long.toString(i + 1L)));
                    }
                }
                cur_.add(new DualIndentNbLabel((DualContainer) cur_,(MetaIndentNbLabel) meta_, page, width_));
            } else if (meta_ instanceof MetaNumberedLabel) {
                cur_.add(new DualNumberedLabel((DualContainer) cur_,(MetaNumberedLabel) meta_, page));
            } else if (meta_ instanceof MetaPointLabel) {
                cur_.add(new DualMetaPointLabel((DualContainer) cur_,(MetaPointLabel) meta_, page));
            } else if (meta_ instanceof MetaButton) {
                cur_.add(new DualButton((DualContainer) cur_,(MetaButton) meta_, page));
            } else if (meta_ instanceof MetaTextField) {
                cur_.add(new DualTextField((DualContainer) cur_,(MetaTextField) meta_, page));
            } else if (meta_ instanceof MetaSpinner) {
                cur_.add(new DualSpinner((DualContainer) cur_,(MetaSpinner) meta_, page));
            } else if (meta_ instanceof MetaSlider) {
                cur_.add(new DualSlider((DualContainer) cur_,(MetaSlider) meta_, page));
            } else if (meta_ instanceof MetaTextArea) {
                cur_.add(new DualTextArea((DualContainer) cur_,(MetaTextArea) meta_, page));
            } else if (meta_ instanceof MetaCheckedBox) {
                cur_.add(new DualCheckedBox((DualContainer) cur_,(MetaCheckedBox) meta_, page));
            } else if (meta_ instanceof MetaRadioButton) {
                MetaRadioButton radio_ = (MetaRadioButton) meta_;
                DualRadionButton dual_ = new DualRadionButton((DualContainer) cur_,radio_, page);
                cur_.add(dual_);
                RadioButton radioButton_ = dual_.getRadio();
                if (!radiosGroup_.isEmpty()) {
                    String value_ = dual_.getValue();
                    ObjectMap<FormInputCoords,CustButtonGroup> grs_ = radiosGroup_.last();
                    if (radio_.getIndexButton() == 0) {
                        CustButtonGroup gr_ = new CustButtonGroup();
                        grs_.put(radio_.getId(), gr_);
                        gr_.add(radioButton_,value_);
                    } else {
                        grs_.getVal(radio_.getId()).add(radioButton_,value_);
                    }
                }
            } else if (meta_ instanceof MetaComboBox) {
                cur_.add(new DualComboBox((DualContainer) cur_,(MetaComboBox) meta_, page));
            } else if (meta_ instanceof MetaComboList) {
                cur_.add(new DualComboList((DualContainer) cur_,(MetaComboList) meta_, page));
            }
            boolean stop_ = false;
            while (true) {
                IntComponent nextSibling_ = meta_.getNextSibling();
                if (nextSibling_ != null) {
                    meta_ = nextSibling_;
                    break;
                }
                IntComponent parent_ =  meta_.getParentCompo();
                cur_ = cur_.getContainer();
                if (parent_ instanceof MetaForm) {
                    radiosGroup_.removeLast();
                }
                if (parent_ == metaroot_) {
                    stop_ = true;
                    break;
                }
                meta_ = parent_;
            }
            if (stop_) {
                break;
            }
        }
        frame.setViewportView(page.getPage().getGraphic());
        frame.validate();
        page.directScroll(meta);
        for (DualAnimatedImage a: page.getAnims()) {
            a.start();
        }
    }

}
