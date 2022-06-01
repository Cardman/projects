package code.formathtml.render;

import code.formathtml.HtmlPage;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.util.Ints;
import code.util.StringList;

public final class SubmitForm {
    private SubmitForm(){}
    public static void submit(IntForm _form, BeanLgNames _stds) {
        HtmlPage htmlPage_ = _stds.getPage();
        htmlPage_.setForm(true);
        long formNb_ = _form.getNumber();
        htmlPage_.setUrl(formNb_);
        IntComponent current_ = _form.getFirstChildCompo();
        while (current_ != null) {
            if (current_ instanceof IntInput) {
                IntInput input_ = (IntInput) current_;
                input(htmlPage_, formNb_, input_);
            }
            IntComponent first_ = current_.getFirstChildCompo();
            if (first_ != null) {
                current_ = first_;
                continue;
            }
            while (current_ != null) {
                IntComponent n_ = current_.getNextSibling();
                if (n_ != null) {
                    current_ = n_;
                    break;
                }
                IntComponent par_ = current_.getParentCompo();
                if (par_ == _form) {
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void input(HtmlPage _page, long _form, IntInput _input) {
        if (_input.getFormNb() != _form) {
            return;
        }
        long nbId_ = _input.getGroup();
        NodeContainer nCont_ = _page.getContainer(_form,nbId_);
        if (_input instanceof IntTextArea) {
            nCont_.setEnabled(true);
            IntTextArea area_ = (IntTextArea) _input;
            nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
        } else if (_input instanceof IntTextField) {
            nCont_.setEnabled(true);
            IntTextField area_ = (IntTextField) _input;
            nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
        } else if (_input instanceof IntSpinner) {
            nCont_.setEnabled(true);
            IntSpinner area_ = (IntSpinner) _input;
            nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
        } else if (_input instanceof IntSlider) {
            nCont_.setEnabled(true);
            IntSlider area_ = (IntSlider) _input;
            nCont_.getNodeInformation().setValue(new StringList(area_.getValue()));
        } else if (_input instanceof IntCheckBox) {
            nCont_.setEnabled(true);
            IntCheckBox ch_ = (IntCheckBox) _input;
            nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
        } else if (_input instanceof IntRadioButton) {
            IntRadioButton ch_ = (IntRadioButton) _input;
            if (ch_.isChecked()) {
                nCont_.getNodeInformation().setValue(new StringList(ch_.getValue()));
                nCont_.setEnabled(true);
            }
        } else if (_input instanceof IntComboBox) {
            nCont_.setEnabled(true);
            IntComboBox c_ = (IntComboBox) _input;
            Ints selected_ = c_.getSelectedIndexes();
            if (selected_.isEmpty()) {
                nCont_.getNodeInformation().setValue(new StringList());
            } else {
                nCont_.getNodeInformation().setValue(new StringList(c_.getValue(selected_.first())));
            }
        } else if (_input instanceof IntComboList) {
            nCont_.setEnabled(true);
            IntComboList c_ = (IntComboList) _input;
            nCont_.getNodeInformation().setValue(c_.getValue());
        }
    }
}
