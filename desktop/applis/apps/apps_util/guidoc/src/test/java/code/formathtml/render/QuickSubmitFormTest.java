package code.formathtml.render;

import code.mock.MockCharacterCaseConverter;
import code.sml.*;
import code.util.LongTreeMap;
import code.util.StringList;
import org.junit.Test;

public final class QuickSubmitFormTest extends EquallableGuiDocUtil {
    @Test
    public void form1Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option></select></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaComboBox combo_ = (MetaComboBox) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.getSelectedIndexes().clear();
        combo_.getSelectedIndexes().add(0);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L, 0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("ONE", l_.get(0));
    }
    @Test
    public void form2Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select multiple=\"multiple\" name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option></select></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaComboList combo_ = (MetaComboList) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.getSelected().clear();
        combo_.getSelected().add(0);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L, 0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("ONE", l_.get(0));
    }
    @Test
    public void form3Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(0, l_.size());
    }
    @Test
    public void form4Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"6\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"submit\" value=\"OK\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaRadioButton combo_ = (MetaRadioButton) intForm_.getFirstChildCompo().getFirstChildCompo().getNextSibling();
        combo_.setChecked(false);
        combo_ = (MetaRadioButton) combo_.getNextSibling();
        combo_.setChecked(true);
        combo_ = (MetaRadioButton) combo_.getNextSibling().getNextSibling();
        combo_.setChecked(true);
        combo_ = (MetaRadioButton) combo_.getNextSibling();
        combo_.setChecked(false);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L,1L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("6", l_.get(0));
        StringList m_ = page_.getContainer(0, 1).getValue();
        assertEq(1, m_.size());
        assertEq("4", m_.get(0));
    }
    @Test
    public void form5Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaCheckedBox combo_ = (MetaCheckedBox) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.setChecked(true);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("on", l_.get(0));
    }
    @Test
    public void form6Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\" checked=\"checked\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaCheckedBox combo_ = (MetaCheckedBox) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.setChecked(false);
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("off", l_.get(0));
    }
    @Test
    public void form7Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"text\" name=\"bean_one.choice\" n-i=\"0\" value=\"TWO\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaTextField combo_ = (MetaTextField) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.setValue("THREE");
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("THREE", l_.get(0));
    }
    @Test
    public void form8Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><textarea name=\"bean_one.choice\" n-i=\"0\">2</textarea></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaTextArea combo_ = (MetaTextArea) intForm_.getFirstChildCompo().getFirstChildCompo();
        combo_.setValue("1");
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("1", l_.get(0));
    }
    @Test
    public void form9Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"1\" value=\"4\"/><span c:for=\"myId\" c:valueMessage=\"msg_example,one\"/><span c:for=\"myId2\" c:valueMessage=\"msg_example,two\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaSpinner number_ = (MetaSpinner) intForm_.getFirstChildCompo().getFirstChildCompo();
        number_.setValue("6");
        MetaSlider range_ = (MetaSlider) number_.getNextSibling();
        range_.setValue("8");
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L,1L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("6", l_.get(0));
        StringList m_ = page_.getContainer(0, 1).getValue();
        assertEq(1, m_.size());
        assertEq("8", m_.get(0));
    }
    @Test
    public void form10Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"4\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(1);
        MetaSpinner number_ = (MetaSpinner) intForm_.getFirstChildCompo().getFirstChildCompo();
        number_.setValue("6");
        HtmlPage page_ = new HtmlPage();
        form(page_, 1L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(1, 0).getValue();
        assertEq(1, l_.size());
        assertEq("6", l_.get(0));
    }
    @Test
    public void form11Test() {
        MetaDocument meta_ = getMetaDocument("<html><body><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"$bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"4\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>");
        IntForm intForm_ = meta_.getForms().get(0);
        MetaSlider number_ = (MetaSlider) intForm_.getFirstChildCompo().getNextSibling().getNextSibling().getFirstChildCompo();
        number_.setValue("8");
        HtmlPage page_ = new HtmlPage();
        form(page_, 0L,0L);
        submit(intForm_, page_);
        StringList l_ = page_.getContainer(0, 0).getValue();
        assertEq(1, l_.size());
        assertEq("8", l_.get(0));
    }

    private void form(HtmlPage _page, long _f, long _n) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        v_.addEntry(_n,new NodeContainer());
        _page.getContainersBase().addEntry(_f, v_);
    }

    private void form(HtmlPage _page, long _f, long _n, long _o) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        v_.addEntry(_n,new NodeContainer());
        v_.addEntry(_o,new NodeContainer());
        _page.getContainersBase().addEntry(_f, v_);
    }

    private void form(HtmlPage _page, long _n) {
        LongTreeMap<NodeContainer> v_ = new LongTreeMap<NodeContainer>();
        _page.getContainersBase().addEntry(_n, v_);
    }

    private void submit(IntForm _intForm, HtmlPageInt _stds) {
        SubmitForm.submit(_intForm, _stds);
    }
    private static MetaDocument getMetaDocument(String _nav) {
        return MetaDocument.newInstance(DocumentBuilder.parseSaxNotNullRowCol(_nav).getDocument(),new RendKeyWordsGroup(),"ABCDEF",new MockCharacterCaseConverter());
    }
}
