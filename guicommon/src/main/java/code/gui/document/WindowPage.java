package code.gui.document;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaAnimatedImage;
import code.formathtml.render.MetaButton;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaDocument;
import code.formathtml.render.MetaImageMap;
import code.formathtml.render.MetaIndentLabel;
import code.formathtml.render.MetaIndentNbLabel;
import code.formathtml.render.MetaListItem;
import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaOrderedList;
import code.formathtml.render.MetaPlainLabel;
import code.formathtml.render.MetaSeparator;
import code.formathtml.render.MetaSimpleImage;
import code.formathtml.render.MetaTable;
import code.formathtml.render.MetaTextField;
import code.sml.Document;
import code.util.CustList;
import code.util.IdMap;

public class WindowPage implements Runnable {

    public static IdMap<JComponent, String> _texts_ = new IdMap<JComponent, String>();

    private RenderedPage page;

    private RootPaneContainer frame;

    private Document document;

    private MetaDocument meta;

    public WindowPage(Document _document, RootPaneContainer _frame) {
        document = _document;
        meta = MetaDocument.newInstance(document);
        frame = _frame;
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        page = new RenderedPage(meta, frame);
        MetaComponent metaroot_ = meta.getRoot();
        MetaComponent meta_ = metaroot_.getFirstChild();
        DualContainer root_ = page.getPage();
        DualComponent cur_ = root_;
        CustList<DualAnimatedImage> anims_ = new CustList<DualAnimatedImage>();
        while (true) {
            if (meta_ instanceof MetaContainer) {
                MetaContainer container_ = (MetaContainer) meta_;
                int count_ = container_.getChildren().size();
                if (count_ > 0 || container_.isAddEmpty()) {
                    if (container_ instanceof MetaTable) {
                        cur_.add(new DualTable((DualContainer) cur_,(MetaTable) container_, page));
                    } else if (container_ instanceof MetaImageMap) {
                        cur_.add(new DualImageMap((DualContainer) cur_,(MetaImageMap) container_, page));
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
                cur_.add(new DualAnchoredLabel((DualContainer) cur_,lab_, page, anims_));
            } else if (meta_ instanceof MetaSeparator) {
                cur_.add(new DualSeparator((DualContainer) cur_,meta_, page));
            } else if (meta_ instanceof MetaSimpleImage) {
                cur_.add(new DualSimpleImage((DualContainer) cur_,(MetaSimpleImage) meta_, page, anims_));
            } else if (meta_ instanceof MetaAnimatedImage) {
                DualAnimatedImage a_ = new DualAnimatedImage((DualContainer) cur_,(MetaAnimatedImage) meta_, page, anims_);
                cur_.add(a_);
                anims_.add(a_);
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
            } else if (meta_ instanceof MetaButton) {
                cur_.add(new DualButton((DualContainer) cur_,(MetaButton) meta_, page, anims_));
            } else if (meta_ instanceof MetaTextField) {
                cur_.add(new DualTextField((DualContainer) cur_,(MetaTextField) meta_, page));
            }
            MetaComponent nextSibling_ = getNextSibling(meta_);
            if (nextSibling_ != null) {
                meta_ = nextSibling_;
                continue;
            }
            MetaComponent parent_ =  meta_.getParent();
            cur_ = cur_.getContainer();
            if (parent_ == metaroot_) {
                break;
            }
            nextSibling_ = getNextSibling(parent_);
            while (nextSibling_ == null) {
                MetaComponent grParent_ = parent_.getParent();
                cur_ = cur_.getContainer();
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
        walk(page.getPage().getGraphic());
        JScrollPane panel_ = new JScrollPane(page.getPage().getGraphic());
        panel_.setPreferredSize(new Dimension(300, 200));
        frame.setContentPane(panel_);
        ((Window) frame).pack();
        for (DualAnimatedImage a: anims_) {
            a.start();
        }
    }
    private static void walk(JPanel _panel) {
        JComponent root_ = _panel;
        JComponent cur_ = root_;
        while (true) {
            System.out.println();
            System.out.println(cur_.getClass());
            if (cur_.getLayout() != null) {
                System.out.println(cur_.getLayout().getClass());
            }
            if (cur_ instanceof JLabel) {
                System.out.println(_texts_.getVal(cur_));
            }
            int count_ = cur_.getComponentCount();
            System.out.println(count_);
            if (count_ > 0) {
                cur_ = (JComponent) cur_.getComponent(0);
                continue;
            }
            JComponent nextSibling_ = getNextSibling(cur_);
            if (nextSibling_ != null) {
                cur_ = nextSibling_;
                continue;
            }
            JComponent parent_ =  (JComponent) cur_.getParent();
            if (parent_ == _panel) {
                break;
            }
            nextSibling_ = getNextSibling(parent_);
            while (nextSibling_ == null) {
                JComponent grParent_ = (JComponent) parent_.getParent();
                if (grParent_ == _panel) {
                    break;
                }
                nextSibling_ = getNextSibling(grParent_);
                parent_ = grParent_;
            }
            if (nextSibling_ == null) {
                break;
            }
            cur_ = nextSibling_;
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
    private static JComponent getNextSibling(JComponent _component) {
        JComponent par_ = (JComponent) _component.getParent();
        int len_ = par_.getComponentCount();
        int index_ = getIndexChild(_component);
        if (index_ + 1 >= len_) {
            return null;
        }
        return (JComponent) par_.getComponent(index_ + 1);
    }
    private static int getIndexChild(JComponent _component) {
        JComponent par_ = (JComponent) _component.getParent();
        int len_ = par_.getComponentCount();
        for (int i = 0; i < len_; i++) {
            if (par_.getComponent(i) == _component) {
                return i;
            }
        }
        return -1;
    }
}
