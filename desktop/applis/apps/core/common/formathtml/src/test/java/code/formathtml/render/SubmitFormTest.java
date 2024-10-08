package code.formathtml.render;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.structs.*;
import code.formathtml.CommonRender;
import code.formathtml.DualNavigationContext;
import code.formathtml.EquallableRenderUtil;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanCustLgNames;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.SetupableAnalyzingDoc;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class SubmitFormTest extends CommonRender {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    private static final String CUST_PAIR_PATH = "pkg/CustPair";
    private static final String CUST_TABLE_PATH = "pkg/CustTable";
    @Test
    public void form1Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><c:select default=\"\" name=\"choice\" map=\"combo\" varValue=\"choice\" convertValue='conv'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" {");
        file_.append("  combo.add(\"ONE\",1);");
        file_.append("  combo.add(\"TWO\",2);");
        file_.append(" }");
        file_.append(" $public String choice=\"TWO\";");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(String)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        DualNavigationContext a_ = getDualNavigationContext(folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option></select></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("TWO", ((StringStruct) choice_).getInstance());
//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaComboBox combo_ = (MetaComboBox) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.getSelectedIndexes().clear();
//        combo_.getSelectedIndexes().add(0);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("ONE"));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
//        assertNotNull(intForm_.getElt());
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\" selected=\"selected\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("ONE", ((StringStruct) choice_).getInstance());
    }

    @Test
    public void form2Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><c:select multiple='' default=\"\" name=\"choice\" map=\"combo\" varValue=\"choice\" convertValue='conv'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" {");
        file_.append("  combo.add(\"ONE\",1);");
        file_.append("  combo.add(\"TWO\",2);");
        file_.append(" }");
        file_.append(" $public pkg.CustList<String> choice=$new pkg.CustList<>();");
        file_.append(" {");
        file_.append("  choice.add(\"TWO\");");
        file_.append(" }");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(CustList<String>)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public CustList<String> conv(String[] a){");
        file_.append("  CustList<String> o = $new CustList<>();");
        file_.append("  $foreach(String e:a){");
        file_.append("   o.add(e);");
        file_.append("  }");
        file_.append("  $return o;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        DualNavigationContext a_ = getDualNavigationContext(folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select multiple=\"multiple\" name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\" selected=\"selected\">2</option></select></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(1, ((NumberStruct)getStruct(choice_,new ClassField("pkg.CustList","length"))).intStruct());
        Struct array_ = getStruct(choice_,new ClassField("pkg.CustList", "list"));
        assertEq(1, ((ArrayStruct)array_).getInstance().length);
        assertEq("TWO", ((StringStruct) ((ArrayStruct)array_).getInstance()[0]).getInstance());

//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaComboList combo_ = (MetaComboList) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.getSelected().clear();
//        combo_.getSelected().add(0);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("ONE"));
        submit(0, stds_);

        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select multiple=\"multiple\" name=\"bean_one.choice\" n-i=\"0\"><option value=\"ONE\" selected=\"selected\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(1, ((NumberStruct)getStruct(choice_,new ClassField("pkg.CustList","length"))).intStruct());
        array_ = getStruct(choice_,new ClassField("pkg.CustList", "list"));
        assertEq(1, ((ArrayStruct)array_).getInstance().length);
        assertEq("ONE", ((StringStruct) ((ArrayStruct)array_).getInstance()[0]).getInstance());
    }
    @Test
    public void form3Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><c:select default=\"\" name=\"choice\" map=\"combo\" varValue=\"choice\" convertValue='conv'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" $public String choice=\"\";");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(String)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  $if (choice == $null){");
        file_.append("   getForms().put(\"choice\",\"\");");
        file_.append("   $return;");
        file_.append("  }");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        DualNavigationContext a_ = getDualNavigationContext(folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("", ((StringStruct) choice_).getInstance());
//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><select name=\"bean_one.choice\" n-i=\"0\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("", ((StringStruct) choice_).getInstance());
    }

    @Test
    public void form4Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><form c:command=\"validate\"><c:for var=\"n\" list=\"numbers\"><input type=\"radio\" name=\"index\" n-r=\"0\" c:varValue=\"n\"/></c:for><c:for var=\"n\" list=\"numbersTwo\"><input type=\"radio\" name=\"indexTwo\" n-r=\"1\" c:varValue=\"n\"/></c:for><input type=\"submit\" value=\"OK\"/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append("  $var ch = getForms().getVal(\"index\");");
        file_.append("  $if (ch != $null){");
        file_.append("   index=($int)ch;");
        file_.append("  }");
        file_.append("  ch = getForms().getVal(\"indexTwo\");");
        file_.append("  $if (ch != $null){");
        file_.append("   indexTwo=($int)ch;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"index\",index);");
        file_.append("  getForms().put(\"indexTwo\",indexTwo);");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        DualNavigationContext a_ = getDualNavigationContext(folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"6\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\" checked=\"checked\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "index"));
        assertEq(4, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "indexTwo"));
        assertEq(6, ((NumberStruct) choice_).intStruct());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaRadioButton combo_ = (MetaRadioButton) intForm_.getFirstChildCompo().getFirstChildCompo().getNextSibling();
//        combo_.setChecked(false);
//        combo_ = (MetaRadioButton) combo_.getNextSibling();
//        combo_.setChecked(true);
//        combo_ = (MetaRadioButton) combo_.getNextSibling().getNextSibling();
//        combo_.setChecked(true);
//        combo_ = (MetaRadioButton) combo_.getNextSibling();
//        combo_.setChecked(false);
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("6"));
        stds_.getPage().getContainer(0, 1).setEnabled(true);
        stds_.getPage().getContainer(0, 1).setValue(new StringList("4"));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"2\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"4\"/><input type=\"radio\" name=\"bean_one.index\" n-i=\"0\" value=\"6\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"2\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"4\" checked=\"checked\"/><input type=\"radio\" name=\"bean_one.indexTwo\" n-i=\"1\" value=\"6\"/><input type=\"submit\" value=\"OK\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "index"));
        assertEq(6, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "indexTwo"));
        assertEq(4, ((NumberStruct) choice_).intStruct());

    }
    @Test
    public void form5Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><input type='checkbox' name='choiceBool' c:varValue='choiceBool'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" {");
        file_.append("  combo.add(\"ONE\",1);");
        file_.append("  combo.add(\"TWO\",2);");
        file_.append(" }");
        file_.append(" $public String choice=\"TWO\";");
        file_.append(" $public $boolean choiceBool;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(String)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaCheckedBox combo_ = (MetaCheckedBox) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.setChecked(true);
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList(SetupableAnalyzingDoc.ON));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceBool"));
        assertTrue(BooleanStruct.isTrue(choice_));
    }
    @Test
    public void form6Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><input type='checkbox' name='choiceBool' c:varValue='choiceBool'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" {");
        file_.append("  combo.add(\"ONE\",1);");
        file_.append("  combo.add(\"TWO\",2);");
        file_.append(" }");
        file_.append(" $public String choice=\"TWO\";");
        file_.append(" $public $boolean choiceBool=$true;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(String)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaCheckedBox combo_ = (MetaCheckedBox) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.setChecked(false);
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList(SetupableAnalyzingDoc.OFF));
        submit(0, stds_);

        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_one.choiceBool\" n-i=\"0\"/></form></body></html>", nav_.getHtmlText());
        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceBool"));
        assertTrue(BooleanStruct.isFalse(choice_));
    }
    @Test
    public void form7Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><input type='text' name='choice' c:varValue='choice'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public pkg.CustTable<String,Integer> combo=$new pkg.CustTable<>();");
        file_.append(" {");
        file_.append("  combo.add(\"ONE\",1);");
        file_.append("  combo.add(\"TWO\",2);");
        file_.append(" }");
        file_.append(" $public String choice=\"TWO\";");
        file_.append(" $public $boolean choiceBool;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=(String)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"text\" name=\"bean_one.choice\" n-i=\"0\" value=\"TWO\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("TWO", ((StringStruct) choice_).getInstance());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaTextField combo_ = (MetaTextField) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.setValue("THREE");
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("THREE"));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"text\" name=\"bean_one.choice\" n-i=\"0\" value=\"THREE\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq("THREE", ((StringStruct) choice_).getInstance());
    }
    @Test
    public void form8Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><textarea name=\"choice\" c:varValue=\"choice\" c:convertValue='conv'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int choice=2;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=($int)ch;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append(" }");
        file_.append(" $public $int conv(String a){");
        file_.append("  $return Integer.parseInt(a);");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        DualNavigationContext a_ = getDualNavigationContext(folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><textarea name=\"bean_one.choice\" n-i=\"0\">2</textarea></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(2, ((NumberStruct) choice_).intStruct());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaTextArea combo_ = (MetaTextArea) intForm_.getFirstChildCompo().getFirstChildCompo();
//        combo_.setValue("1");
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("1"));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><textarea name=\"bean_one.choice\" n-i=\"0\">1</textarea></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(1, ((NumberStruct) choice_).intStruct());
    }
    @Test
    public void form9Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><input type='number'  id='\"myId\"' name='choice' c:varValue='choice'/><input type='range' id='\"myId2\"' name='choiceSec' c:varValue='choiceSec'/><span c:for='\"myId\"' c:valueMessage='msg_example,one'/><span c:for='\"myId2\"' c:valueMessage='msg_example,two'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int choice=2;");
        file_.append(" $public $int choiceSec=4;");
        file_.append(" $public $boolean choiceBool;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=($int)ch;");
        file_.append("  }");
        file_.append("  ch = getForms().getVal(\"choiceSec\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choiceSec=($int)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append("  getForms().put(\"choiceSec\",choiceSec);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"1\" value=\"4\"/><span c:for=\"myId\" c:valueMessage=\"msg_example,one\"/><span c:for=\"myId2\" c:valueMessage=\"msg_example,two\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(2, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(4, ((NumberStruct) choice_).intStruct());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaSpinner number_ = (MetaSpinner) intForm_.getFirstChildCompo().getFirstChildCompo();
//        number_.setValue("6");
//        MetaSlider range_ = (MetaSlider) number_.getNextSibling();
//        range_.setValue("8");
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("6"));
        stds_.getPage().getContainer(0, 1).setEnabled(true);
        stds_.getPage().getContainer(0, 1).setValue(new StringList("8"));
        submit(0, stds_);

        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"6\"/><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"1\" value=\"8\"/><span c:for=\"myId\" c:valueMessage=\"msg_example,one\"/><span c:for=\"myId2\" c:valueMessage=\"msg_example,two\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(6, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(8, ((NumberStruct) choice_).intStruct());
    }
    @Test
    public void form10Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><form c:command=\"validate\"><input type='number' id='\"myId\"' name='choice' c:varValue='choice'/><input type='submit' value='Validate'/></form><input type='range' id='\"myId2\"' name='choiceSec' c:varValue='choiceSec'/><input type='submit' value='Validate'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int choice=2;");
        file_.append(" $public $int choiceSec=4;");
        file_.append(" $public $boolean choiceBool;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=($int)ch;");
        file_.append("  }");
        file_.append("  ch = getForms().getVal(\"choiceSec\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choiceSec=($int)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append("  getForms().put(\"choiceSec\",choiceSec);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"4\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(2, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(4, ((NumberStruct) choice_).intStruct());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(1);
//        MetaSpinner number_ = (MetaSpinner) intForm_.getFirstChildCompo().getFirstChildCompo();
//        number_.setValue("6");
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(1, 0).setEnabled(true);
        stds_.getPage().getContainer(1, 0).setValue(new StringList("6"));
        submit(1, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"6\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"4\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(6, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(4, ((NumberStruct) choice_).intStruct());
    }
    @Test
    public void form11Test() {
        String locale_ = EN;
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><form c:command=\"validate\"><form c:command=\"validate\"><input type='number' id='\"myId\"' name='choice' c:varValue='choice'/><input type='submit' value='Validate'/></form><input type='range' id='\"myId2\"' name='choiceSec' c:varValue='choiceSec'/><input type='submit' value='Validate'/></form></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class [code.bean.Message;] pkg.MyVal:code.bean.Validator{");
        file_.append(" $public Message validate(Object n,Object o,Object b,Object[] f,String c,String fd){");
        file_.append("  $var mess = Message.newStandardMessage();");
        file_.append("  $return mess;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int choice=2;");
        file_.append(" $public $int choiceSec=4;");
        file_.append(" $public $boolean choiceBool;");
        file_.append(" $public $int index;");
        file_.append(" $public $int indexTwo;");
        file_.append(" $public $int[] numbers;");
        file_.append(" $public $int[] numbersTwo;");
        file_.append(" $public $void beforeDisplaying(){");
        file_.append("  $var ch = getForms().getVal(\"choice\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choice=($int)ch;");
        file_.append("  }");
        file_.append("  ch = getForms().getVal(\"choiceSec\");");
        file_.append("  $if (ch != $null){");
        file_.append("   choiceSec=($int)ch;");
        file_.append("  }");
        file_.append("  numbers={2,4,6};");
        file_.append("  numbersTwo={2,4,6};");
        file_.append("  index=4;");
        file_.append("  indexTwo=6;");
        file_.append(" }");
        file_.append(" $public $void validate(){");
        file_.append("  getForms().put(\"choice\",choice);");
        file_.append("  getForms().put(\"choiceSec\",choiceSec);");
        file_.append(" }");
        file_.append(" $public String conv(String a){");
        file_.append("  $return a;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());


        DualNavigationContext a_ = getDualNavigationContext(locale_, folder_, relative_);
        ContextEl ctx_ = ana(filesSec_,getStringMap(folder_, locale_, relative_, content_, html_),a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        Navigation navigation_ = a_.getNavigation();
        BeanCustLgNames stds_ = a_.getDualAnalyzedContext().getStds();
        initializeRendSessionDoc(stds_, ctx_, navigation_, build_);
        Navigation nav_ = a_.getNavigation();
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"4\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>", nav_.getHtmlText());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

        Struct choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(2, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(4, ((NumberStruct) choice_).intStruct());


//        MetaDocument meta_ = getMetaDocument(nav_);
//        IntForm intForm_ = meta_.getForms().get(0);
//        MetaSlider number_ = (MetaSlider) intForm_.getFirstChildCompo().getNextSibling().getNextSibling().getFirstChildCompo();
//        number_.setValue("8");
//        submit(intForm_, stds_);
        stds_.getPage().getContainer(0, 0).setEnabled(true);
        stds_.getPage().getContainer(0, 0).setValue(new StringList("8"));
        submit(0, stds_);
        processRendFormRequest(a_,ctx_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"0\"><form c:command=\"bean_one\" c:sgn=\"pkg.BeanOne.validate()\" action=\"\" n-f=\"1\"><input type=\"number\" id=\"myId\" name=\"bean_one.choice\" n-i=\"0\" value=\"2\"/><input type=\"submit\" value=\"Validate\"/></form><input type=\"range\" id=\"myId2\" name=\"bean_one.choiceSec\" n-i=\"0\" value=\"8\"/><input type=\"submit\" value=\"Validate\"/></form></body></html>", nav_.getHtmlText());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choice"));
        assertEq(2, ((NumberStruct) choice_).intStruct());
        choice_ = getStruct(a_.getDualAnalyzedContext().getStds().getBuiltBeans().getVal("bean_one"),new ClassField("pkg.BeanOne", "choiceSec"));
        assertEq(8, ((NumberStruct) choice_).intStruct());
    }

    private void submit(int _no, BeanCustLgNames _stds) {
        _stds.getPage().setForm(true);
        _stds.getPage().setUrl(_no);
    }

    private void initializeRendSessionDoc(BeanCustLgNames _stds, ContextEl _ctx, Navigation _navigation, RendStackCall _build) {
        _stds.initializeRendSessionDoc(_ctx, _navigation, _build);
    }


    private static void processRendFormRequest(DualNavigationContext _nav, ContextEl _ctx) {
        Element elt_ = DocumentBuilder.getFirstElementByAttribute(_nav.getNavigation().getDocument(), _nav.getNavigation().getSession().getRendKeyWords().getAttrNf(), Long.toString(_nav.getDualAnalyzedContext().getStds().getCustPage().getUrl()));
        _nav.getDualAnalyzedContext().getStds().execute(true,elt_, _nav.getNavigation(), _ctx);
//        _nav.getDualAnalyzedContext().getStds().processRendFormRequest(_nav.getNavigation(), _ctx, new RendStackCall(InitPhase.NOTHING, _ctx), elt_);
    }

    private static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustPair<U,V> :$pair<U,V>{\n");
        xml_.append(" $private U first;\n");
        xml_.append(" $private V second;\n");
        xml_.append(" $public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair(U f,V s){\n");
        xml_.append("  first = f;\n");
        xml_.append("  second = s;\n");
        xml_.append(" }\n");
        xml_.append(" $public U getFirst(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public V getSecond(){\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new CustList<CustPair<U,V>>();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(U f,V s){\n");
        xml_.append("  list.add($new CustPair<U,V>(f,s));\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int size(){\n");
        xml_.append("  $return list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> get($int index){\n");
        xml_.append("  $return list.get(index);\n");
        xml_.append(" }\n");
        xml_.append(" $public $iteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  $return $new CustIterTable<U,V>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIterTable<U,V> :$iteratorTable<U,V>{\n");
        xml_.append(" $private CustTable<U,V> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }

    private static void addVal(Navigation _nav, String _valId, String _class) {
        ValidatorInfo v_ = new ValidatorInfo();
        v_.setClassName(_class);
        _nav.getSession().getLateValidators().addEntry(_valId,v_);
    }

    private DualNavigationContext getDualNavigationContext(String _folder, String _relative) {
        DualNavigationContext a_ = buildNav();
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        setFirst("page1.html", a_.getNavigation().getSession());
        Navigation nav_ = a_.getNavigation();
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        return a_;
    }

    private DualNavigationContext getDualNavigationContext(String _locale, String _folder, String _relative) {
        DualNavigationContext a_ = buildNav();
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        setFirst("page1.html", a_.getNavigation().getSession());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);
        nav_.setSession(a_.getNavigation().getSession());
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        return a_;
    }

    private StringMap<String> getStringMap(String _folder, String _locale, String _relative, String _content, String _html) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        files_.put("page1.html", _html);
        return files_;
    }


}
