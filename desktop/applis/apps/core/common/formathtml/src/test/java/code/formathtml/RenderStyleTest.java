package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderStyleTest extends CommonRender {
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>.classTest{color:blue;}CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}</style></head><style>{0}CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String html_ = "<html><head><style>CONTENT</style><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        assertEq("<html><head><style>CONTENT.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String html_ = "<html><head><style/><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;};'}'\"/></head><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        assertEq("<html><head><style>.classTest{color:blue;}</style><link href=\"main.css\" rel=\"stylesheet\"/></head><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        assertEq("<html><head><link rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String html_ = "<html><link href=\"main.css\" rel=\"stylesheet\"/><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        assertEq("<html><link href=\"main.css\" rel=\"stylesheet\"/><style>.classTest{color:blue;}CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }

    @Test
    public void process7Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", ".classTest{color:blue;}");
        assertEq("<html><head><link href=\"main.css\"/></head><style>CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main1.css", ".classTest{color:blue;}");
        assertEq("<html><head><link href=\"main.css\" rel=\"stylesheet\"/></head><style>CONTENT</style><body/></html>", getRes(folder_, html_, files_, new StringMap<String>()));
    }

    @Test
    public void process9Test() {
        String folder_ = "messages";
        String html_ = "<html><head><link href=\"main.css\" rel=\"stylesheet\" param0=\".classTest'{'color:{&quot;blue&quot;+1/0};'}'\"/></head><style>CONTENT</style><body/></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("main.css", "{0}");
        assertNotNull(getEx(folder_, html_, files_));
    }



}
