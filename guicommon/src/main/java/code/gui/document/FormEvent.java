package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public class FormEvent extends MouseAdapter {

    private Element form;

    private DualButton current;

    private RenderedPage page;

    private CustList<DualAnimatedImage> anims;

    public FormEvent(Element _form, DualButton _current, RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        form = _form;
        current = _current;
        page = _page;
        anims = _anims;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        DualForm form_ = current.getParentForm();
        if (form_ == null) {
            return;
        }
        for (DualAnimatedImage d: anims) {
            d.getImageThread().setAnimated(false);
        }
        Navigation nav_ = page.getNavigation();
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(true);
        String nbForm_ = form.getAttribute("n-f");
        htmlPage_.setUrl(Long.parseLong(nbForm_));
        NatTreeMap<Long,NodeContainer> inputsMap_;
        inputsMap_ = htmlPage_.getContainers().getVal(Long.parseLong(nbForm_));
        DualComponent current_ = form_.getChildren().first();
        while (true) {
            if (current_ instanceof DualInput) {
                DualInput input_ = (DualInput) current_;
                if (input_.getParentForm() == form_) {
                    long nbId_ = input_.getGroup();
                    NodeContainer nCont_ = inputsMap_.getVal(nbId_);
                    if (input_ instanceof DualTextArea) {
                        nCont_.setEnabled(true);
                        DualTextArea area_ = (DualTextArea) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof DualTextField) {
                        nCont_.setEnabled(true);
                        DualTextField area_ = (DualTextField) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof DualCheckedBox) {
                        nCont_.setEnabled(true);
                        DualCheckedBox ch_ = (DualCheckedBox) input_;
                        nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
                    } else if (input_ instanceof DualRadionButton) {
                        DualRadionButton ch_ = (DualRadionButton) input_;
                        nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
                        if (!ch_.getValue().isEmpty()) {
                            nCont_.setEnabled(true);
                        } else if (nCont_.getNodeInformation().getValue().isEmpty()) {
                            nCont_.setEnabled(false);
                        }
                    } else if (input_ instanceof DualComboBox) {
                        nCont_.setEnabled(true);
                        FormInputCoords fi_ = new FormInputCoords();
                        fi_.setForm(Long.parseLong(nbForm_));
                        fi_.setInput(nbId_);
                        DualComboBox c_ = (DualComboBox) input_;
                        if (c_.getSelectedIndexes().isEmpty()) {
                            nCont_.getNodeInformation().setValue(new StringList());
                        } else {
                            nCont_.getNodeInformation().setValue(new StringList(c_.getValue()));
                        }
                    } else if (input_ instanceof DualComboList) {
                        nCont_.setEnabled(true);
                        DualComboList c_ = (DualComboList) input_;
                        nCont_.getNodeInformation().setValue(c_.getValue());
                    }
                }
            }
            CustList<DualComponent> ch_ = current_.getChildren();
            if (!ch_.isEmpty()) {
                current_ = ch_.first();
                continue;
            }
            DualComponent n_ = getNextSibling(current_);
            if (n_ != null) {
                current_ = n_;
                continue;
            }
            DualContainer par_ = current_.getContainer();
            if (par_ == form_) {
                break;
            }
            n_ = getNextSibling(par_);
            while (n_ != null) {
                DualContainer grPar_ = par_.getContainer();
                if (grPar_ == form_) {
                    break;
                }
                n_ = getNextSibling(grPar_);
                par_ = grPar_;
            }
            if (n_ == null) {
                break;
            }
            current_ = n_;
        }
        new ThreadActions(page, "", "", true, false, false).start();
    }
    private static DualComponent getNextSibling(DualComponent _current) {
        DualContainer par_ = _current.getContainer();
        CustList<DualComponent> next_ = par_.getChildren();
        int len_ = next_.size();
        int index_ = -1;
        for (int i = 0; i < len_; i++) {
            if (next_.get(i) == _current) {
                index_ = i + 1;
                break;
            }
        }
        if (index_ < len_) {
            return next_.get(index_);
        }
        return null;
    }
}
