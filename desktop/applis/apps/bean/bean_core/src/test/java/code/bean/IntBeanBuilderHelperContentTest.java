package code.bean;

import code.formathtml.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import org.junit.Test;

public class IntBeanBuilderHelperContentTest extends EquallableBeanCoreUtil {
    @Test
    public void test() {
        IntBeanBuilderHelperCommonImpl content_ = new IntBeanBuilderHelperCommonImpl();
        content_.getColCount().add(2);
        content_.getColIndex().add(0);
        Translations ts_ = new Translations();
        TranslationsLg lg_ = new TranslationsLg();
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry("",new TranslationsFile());
        lg_.getMapping().addEntry("", ta_);
        ts_.getMapping().addEntry("", lg_);
        content_.setTranslations(ts_);
        assertEq(2,content_.colCount());
        content_.incColIndex();
        assertEq(1,content_.colIndex());
        content_.colCount(4);
        content_.getColIndex().set(0,2);
        content_.incColIndex();
        content_.setHeader(content_.getHeader());
        content_.setRefLk(content_.getRefLk());
        content_.setPartGroup(content_.getPartGroup());
        content_.setFormGroup(content_.getFormGroup());
        content_.setRowGroup(content_.getRowGroup());
        content_.getOrderedLists().clear();
        content_.setIndent(content_.getIndent());
        content_.setTranslations(content_.getTranslations());
        content_.setTitledBorder("");
        assertEq(3,content_.colIndex());
        content_.getColCount().set(content_.getColCount().getLastIndex(),0);
        content_.incColIndex();
        content_.getColCount().clear();
        content_.incColIndex();
    }
}
