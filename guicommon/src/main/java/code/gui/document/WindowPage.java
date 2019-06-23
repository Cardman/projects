package code.gui.document;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaAnimatedImage;
import code.formathtml.render.MetaButton;
import code.formathtml.render.MetaCheckedBox;
import code.formathtml.render.MetaComboBox;
import code.formathtml.render.MetaComboList;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaDocument;
import code.formathtml.render.MetaForm;
import code.formathtml.render.MetaImageMap;
import code.formathtml.render.MetaIndentLabel;
import code.formathtml.render.MetaIndentNbLabel;
import code.formathtml.render.MetaListItem;
import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaOrderedList;
import code.formathtml.render.MetaPlainLabel;
import code.formathtml.render.MetaPointLabel;
import code.formathtml.render.MetaRadioButton;
import code.formathtml.render.MetaSeparator;
import code.formathtml.render.MetaSimpleImage;
import code.formathtml.render.MetaTable;
import code.formathtml.render.MetaTextArea;
import code.formathtml.render.MetaTextField;
import code.util.CustList;
import code.util.StringMap;

public class WindowPage implements Runnable {

    private RenderedPage page;

    private JScrollPane frame;

    private MetaDocument meta;

    public WindowPage(MetaDocument _meta, JScrollPane _frame, RenderedPage _page) {
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
        MetaComponent meta_ = metaroot_.getFirstChild();
        DualContainer root_ = page.getPage();
        DualComponent cur_ = root_;
        CustList<StringMap<ButtonGroup>> radiosGroup_ = new CustList<StringMap<ButtonGroup>>();
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
                        radiosGroup_.add(new StringMap<ButtonGroup>());
                        cur_.add(new DualForm((DualContainer) cur_, (MetaForm) container_, page));
                    } else {
                        cur_.add(new DualPanel((DualContainer) cur_,container_, page));
                    }
                    if (container_.containsOnlyEndLine()) {
                        JComponent c_ = cur_.getGraphic();
                        Font font_ = c_.getFont();
                        FontMetrics fMetrics_ = c_.getFontMetrics(font_);
                        int em_ = cur_.getComponent().getStyle().getEmToPixels();
                        cur_.getChildren().last().getGraphic().setPreferredSize(new Dimension(em_, fMetrics_.getHeight()));
                    }
                }
                if (count_ > 0) {
                    meta_ = container_.getFirstChild();
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
                cur_.add(new DualSeparator((DualContainer) cur_,meta_, page));
            } else if (meta_ instanceof MetaSimpleImage) {
                cur_.add(new DualSimpleImage((DualContainer) cur_,(MetaSimpleImage) meta_, page));
            } else if (meta_ instanceof MetaAnimatedImage) {
                DualAnimatedImage a_ = new DualAnimatedImage((DualContainer) cur_,(MetaAnimatedImage) meta_, page);
                cur_.add(a_);
            } else if (meta_ instanceof MetaIndentLabel) {
                cur_.add(new DualIndentLabel((DualContainer) cur_,(MetaIndentLabel) meta_, page));
            } else if (meta_ instanceof MetaIndentNbLabel) {
                MetaContainer par_ = meta_.getParent();
                while (!(par_ instanceof MetaListItem)) {
                    par_ = par_.getParent();
                }
                MetaListItem li_ = (MetaListItem) par_;
                MetaContainer gr_ = li_.getParent();
                int width_ = meta_.getStyle().getEmToPixels();
                JPanel p_ = (JPanel) cur_.getGraphic();
                Font font_ = p_.getFont();
                FontMetrics fontMetrics_ = p_.getFontMetrics(font_);
                if (gr_ instanceof MetaOrderedList) {
                    int len_ = gr_.getChildren().size();
                    for (int i = 0; i < len_; i++) {
                        width_ = Math.max(width_, fontMetrics_.stringWidth(Integer.toString(i + 1)));
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
            } else if (meta_ instanceof MetaTextArea) {
                cur_.add(new DualTextArea((DualContainer) cur_,(MetaTextArea) meta_, page));
            } else if (meta_ instanceof MetaCheckedBox) {
                cur_.add(new DualCheckedBox((DualContainer) cur_,(MetaCheckedBox) meta_, page));
            } else if (meta_ instanceof MetaRadioButton) {
                MetaRadioButton radio_ = (MetaRadioButton) meta_;
                DualRadionButton dual_ = new DualRadionButton((DualContainer) cur_,radio_, page);
                cur_.add(dual_);
                JRadioButton radioButton_ = dual_.getRadio();
                if (!radiosGroup_.isEmpty()) {
                    StringMap<ButtonGroup> grs_ = radiosGroup_.last();
                    if (radio_.getIndexButton() == 0) {
                        ButtonGroup gr_ = new ButtonGroup();
                        grs_.put(radio_.getName(), gr_);
                        gr_.add(radioButton_);
                    } else {
                        grs_.getVal(radio_.getName()).add(radioButton_);
                    }
                }
            } else if (meta_ instanceof MetaComboBox) {
                cur_.add(new DualComboBox((DualContainer) cur_,(MetaComboBox) meta_, page));
            } else if (meta_ instanceof MetaComboList) {
                cur_.add(new DualComboList((DualContainer) cur_,(MetaComboList) meta_, page));
            }
            MetaComponent nextSibling_ = getNextSibling(meta_);
            if (nextSibling_ != null) {
                meta_ = nextSibling_;
                continue;
            }
            MetaComponent parent_ =  meta_.getParent();
            cur_ = cur_.getContainer();
            if (parent_ instanceof MetaForm) {
                radiosGroup_.removeLast();
            }
            if (parent_ == metaroot_) {
                break;
            }
            nextSibling_ = getNextSibling(parent_);
            while (nextSibling_ == null) {
                MetaComponent grParent_ = parent_.getParent();
                cur_ = cur_.getContainer();
                if (grParent_ instanceof MetaForm) {
                    radiosGroup_.removeLast();
                }
                if (grParent_ == metaroot_) {
                    break;
                }
                nextSibling_ = getNextSibling(grParent_);
                parent_ = grParent_;
            }
            if (nextSibling_ == null) {
                break;
            }
            meta_ = nextSibling_;
        }
        frame.setViewportView(page.getPage().getGraphic());
        frame.validate();
        page.directScroll();
        for (DualAnimatedImage a: page.getAnims()) {
            a.start();
        }
    }

    private static MetaComponent getNextSibling(MetaComponent _component) {
        MetaContainer par_ = _component.getParent();
        int len_ = par_.getChildren().size();
        int index_ = getIndexChild(_component);
        if (index_ + 1 >= len_) {
            return null;
        }
        return par_.getChildren().get(index_ + 1);
    }
    private static int getIndexChild(MetaComponent _component) {
        MetaContainer par_ = _component.getParent();
        int len_ = par_.getChildren().size();
        for (int i = 0; i < len_; i++) {
            if (par_.getChildren().get(i) == _component) {
                return i;
            }
        }
        return -1;
    }
}
