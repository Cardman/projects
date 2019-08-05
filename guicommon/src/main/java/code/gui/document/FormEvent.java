package code.gui.document;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.render.*;
import code.formathtml.util.NodeContainer;
import code.gui.CustComponent;
import code.util.*;
import code.util.StringList;

public class FormEvent extends MouseAdapter {

    private DualButton current;

    private RenderedPage page;

    public FormEvent(DualButton _current, RenderedPage _page) {
        current = _current;
        page = _page;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        DualForm form_ = current.getParentForm();
        if (form_ == null) {
            return;
        }
        for (DualAnimatedImage d: page.getAnims()) {
            d.getImageThread().setAnimated(false);
        }
        Navigation nav_ = page.getNavigation();
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        htmlPage_.setForm(true);
        long formNb_ = form_.getNumber();
        htmlPage_.setUrl(formNb_);
        LongTreeMap<NodeContainer> inputsMap_;
        inputsMap_ = htmlPage_.getContainers().getVal(formNb_);
        IntComponent current_ = form_.getChildren().first();
        while (true) {
            if (current_ instanceof IntInput) {
                IntInput input_ = (IntInput) current_;
                if (input_.getFormNb() == formNb_) {
                    long nbId_ = input_.getGroup();
                    NodeContainer nCont_ = inputsMap_.getVal(nbId_);
                    if (input_ instanceof IntTextArea) {
                        nCont_.setEnabled(true);
                        IntTextArea area_ = (IntTextArea) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof IntTextField) {
                        nCont_.setEnabled(true);
                        IntTextField area_ = (IntTextField) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof IntSpinner) {
                        nCont_.setEnabled(true);
                        IntSpinner area_ = (IntSpinner) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof IntSlider) {
                        nCont_.setEnabled(true);
                        IntSlider area_ = (IntSlider) input_;
                        nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
                    } else if (input_ instanceof IntCheckBox) {
                        nCont_.setEnabled(true);
                        IntCheckBox ch_ = (IntCheckBox) input_;
                        nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
                    } else if (input_ instanceof IntRadioButton) {
                        IntRadioButton ch_ = (IntRadioButton) input_;
                        if (ch_.isChecked()) {
                            nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
                            nCont_.setEnabled(true);
                        }
                    } else if (input_ instanceof IntComboBox) {
                        nCont_.setEnabled(true);
                        IntComboBox c_ = (IntComboBox) input_;
                        Ints selected_ = c_.getSelectedIndexes();
                        if (selected_.isEmpty()) {
                            nCont_.getNodeInformation().setValue(new StringList());
                        } else {
                            nCont_.getNodeInformation().setValue(new StringList(c_.getValue(selected_.first())));
                        }
                    } else if (input_ instanceof IntComboList) {
                        nCont_.setEnabled(true);
                        IntComboList c_ = (IntComboList) input_;
                        nCont_.getNodeInformation().setValue(c_.getValue());
                    }
                }
            }
            IntComponent first_ = current_.getFirstChildCompo();
            if (first_ != null) {
                current_ = first_;
                continue;
            }
            while (true) {
                IntComponent n_ = current_.getNextSibling();
                if (n_ != null) {
                    current_ = n_;
                    break;
                }
                IntComponent par_ = current_.getParentCompo();
                if (par_ == form_) {
                    current_ = null;
                    break;
                }
                current_ = par_;
            }
            if (current_ == null) {
                break;
            }
        }
        CustComponent.newThread(new ThreadActions(page, page.getStandards(), "", "", true, false, false)).start();
        page.animateProcess();
    }
}
