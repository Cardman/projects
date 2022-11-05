package code.mock;

import code.threads.FileStruct;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MockFileSetTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/",pr_.getCurrentPath());
    }
    @Test
    public void f2() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/",pr_.getFileCoreStream().newFile("/").getAbsolutePath());
    }
    @Test
    public void f3() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/tmp",pr_.getFileCoreStream().newFile("/tmp").getAbsolutePath());
    }
    @Test
    public void f4() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/tmp",pr_.getFileCoreStream().newFile("tmp").getAbsolutePath());
    }
    @Test
    public void f5() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("/tmp",pr_.getFileCoreStream().newFile(".").getAbsolutePath());
    }
    @Test
    public void f6() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("/",pr_.getFileCoreStream().newFile("..").getAbsolutePath());
    }
    @Test
    public void f7() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("",pr_.getFileCoreStream().newFile("../..").getAbsolutePath());
    }
    @Test
    public void f8() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("",pr_.getFileCoreStream().newFile("a/%%/b").getAbsolutePath());
    }
    @Test
    public void f9() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp");
        assertEq("",pr_.getFileCoreStream().newFile("a//b").getAbsolutePath());
    }
    @Test
    public void f10() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/tmp/tmp2",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp/tmp2");
        assertEq("/tmp",pr_.getFileCoreStream().newFile("..").getAbsolutePath());
    }
    @Test
    public void f11() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("/tmp/tmp2",new FileStruct(null,0));
        pr_.setCurrentPath("/tmp/tmp2");
        assertEq("/",pr_.getFileCoreStream().newFile("../..").getAbsolutePath());
    }
    @Test
    public void f12() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        assertEq("c:/",pr_.getCurrentPath());
    }
    @Test
    public void f13() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        assertEq("c:/",pr_.getFileCoreStream().newFile("c:/").getAbsolutePath());
    }
    @Test
    public void f14() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        assertEq("c:/tmp",pr_.getFileCoreStream().newFile("c:/tmp").getAbsolutePath());
    }
    @Test
    public void f15() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        assertEq("c:/tmp",pr_.getFileCoreStream().newFile("tmp").getAbsolutePath());
    }
    @Test
    public void f16() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("c:/tmp");
        assertEq("c:/tmp",pr_.getFileCoreStream().newFile(".").getAbsolutePath());
    }
    @Test
    public void f17() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.setCurrentPath("c:/tmp");
        assertEq("c:/",pr_.getFileCoreStream().newFile("..").getAbsolutePath());
    }
    @Test
    public void f18() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        pr_.setCurrentPath("c:/tmp");
        assertEq("c:/",pr_.getFileCoreStream().newFile("..").getAbsolutePath());
    }
    @Test
    public void f19() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        pr_.setCurrentPath("d:/tmp2");
        assertEq("d:/",pr_.getFileCoreStream().newFile("..").getAbsolutePath());
    }
    @Test
    public void f20() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/");
        assertEq("",pr_.getFileCoreStream().newFile("/c:/").getAbsolutePath());
    }
    @Test
    public void f21() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/tmp",pr_.getFileCoreStream().newFile("/tmp/").getAbsolutePath());
    }
    @Test
    public void f22() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("/tmp",pr_.getFileCoreStream().newFile("tmp/").getAbsolutePath());
    }
    @Test
    public void f23() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"/");
        assertEq("",pr_.getFileCoreStream().newFile("").getAbsolutePath());
    }
    @Test
    public void f24() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        pr_.setCurrentPath("e:/tmp2");
        assertEq("c:/",pr_.getCurrentPath());
    }
    @Test
    public void f25() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        assertEq("c:/",pr_.getMockFileSet().linkedRoot("c:/tmp"));
    }
    @Test
    public void f26() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        assertEq("d:/",pr_.getMockFileSet().linkedRoot("d:/tmp2"));
    }
    @Test
    public void f27() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,0,wrapLongs(1,2),true,"c:/","d:/");
        pr_.getMockFileSet().getFiles().addEntry("c:/tmp",new FileStruct(null,0));
        pr_.getMockFileSet().getFiles().addEntry("d:/tmp2",new FileStruct(null,0));
        assertEq("",pr_.getMockFileSet().linkedRoot("e:/tmp3"));
    }
    @Test
    public void f28() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        assertTrue(pr_.getFileCoreStream().newFile("/tmp").mkdirs());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp");
        assertNull(val_.getContent());
        assertEq(8, val_.getLastDate());
    }
    @Test
    public void f29() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,7,wrapLongs(1,2),true,"/");
        assertTrue(pr_.getFileCoreStream().newFile("tmp").mkdirs());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp");
        assertNull(val_.getContent());
        assertEq(10, val_.getLastDate());
    }
    @Test
    public void f30() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        assertTrue(pr_.getFileCoreStream().newFile("/tmp").mkdirs());
        assertFalse(pr_.getFileCoreStream().newFile("/tmp").mkdirs());
    }
    @Test
    public void f31() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        assertFalse(pr_.getFileCoreStream().newFile("/tmp%").mkdirs());
    }
    @Test
    public void f32() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(new byte[0],0));
        assertFalse(pr_.getFileCoreStream().newFile("tmp").mkdirs());
    }
    @Test
    public void f33() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(null,0));
        assertTrue(pr_.getFileCoreStream().newFile("/tmp/tmp2").mkdirs());
        pr_.setCurrentPath("/tmp");
        assertEq("/tmp/tmp2",pr_.getFileCoreStream().newFile("tmp2").getAbsolutePath());
    }
    @Test
    public void f34() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2),true,"/");
        pr_.getMockFileSet().getFiles().addEntry("/tmp",new FileStruct(new byte[0],0));
        assertFalse(pr_.getFileCoreStream().newFile("tmp/tmp2").mkdirs());
    }
    @Test
    public void f35() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertTrue(pr_.getStreams().getTextFact().write("txt","content",false));
        assertEq("content",pr_.getStreams().getTextFact().contentsOfFile("txt",new DefaultUniformingString(),7));
        assertEq("/tmp/txt",pr_.getFileCoreStream().newFile("txt").getAbsolutePath());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp/txt");
        assertEq(15, val_.getLastDate());
        assertEq("content", StringUtil.decode(val_.getContent()));
    }
    @Test
    public void f36() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.25,5,wrapLongs(1,2,7),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertFalse(pr_.getStreams().getTextFact().write("txt","content",false));
        assertNull(pr_.getStreams().getTextFact().contentsOfFile("txt",new DefaultUniformingString(),7));
    }
    @Test
    public void f37() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertFalse(pr_.getStreams().getTextFact().write("/tmp","content",false));
        assertNull(pr_.getStreams().getTextFact().contentsOfFile("/tmp",new DefaultUniformingString(),7));
    }
    @Test
    public void f38() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,4),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertTrue(pr_.getStreams().getTextFact().write("txt","content",false));
        assertTrue(pr_.getStreams().getTextFact().write("txt","content2",false));
        assertEq("content2",pr_.getStreams().getTextFact().contentsOfFile("txt",new DefaultUniformingString(),7));
        assertEq("/tmp/txt",pr_.getFileCoreStream().newFile("txt").getAbsolutePath());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp/txt");
        assertEq(19, val_.getLastDate());
        assertEq("content2", StringUtil.decode(val_.getContent()));
    }
    @Test
    public void f39() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,4),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("txt").mkdirs();
        assertFalse(pr_.getStreams().getTextFact().write("txt","content",false));
    }
    @Test
    public void f40() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,4),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertTrue(pr_.getStreams().getTextFact().write("txt","content",false));
        assertFalse(pr_.getStreams().getTextFact().write("/tmp/txt/after","content2",false));
    }
    @Test
    public void f41() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,4),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertFalse(pr_.getStreams().getTextFact().write("%","content",false));
    }
    @Test
    public void f42() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertTrue(pr_.getStreams().getTextFact().write("txt","content",false));
        assertTrue(pr_.getStreams().getTextFact().write("txt"," after",true));
        assertEq("content after",pr_.getStreams().getTextFact().contentsOfFile("txt",new DefaultUniformingString(),7));
        assertEq("/tmp/txt",pr_.getFileCoreStream().newFile("txt").getAbsolutePath());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp/txt");
        assertEq(21, val_.getLastDate());
        assertEq("content after", StringUtil.decode(val_.getContent()));
    }
    @Test
    public void f43() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        assertTrue(pr_.getStreams().getTextFact().write("txt","content",true));
        assertTrue(pr_.getStreams().getTextFact().write("txt"," after",true));
        assertEq("content after",pr_.getStreams().getTextFact().contentsOfFile("txt",new DefaultUniformingString(),7));
        assertEq("/tmp/txt",pr_.getFileCoreStream().newFile("txt").getAbsolutePath());
        FileStruct val_ = pr_.getMockFileSet().getFiles().getVal("/tmp/txt");
        assertEq(21, val_.getLastDate());
        assertEq("content after", StringUtil.decode(val_.getContent()));
    }
    @Test
    public void f44() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertFalse(pr_.getFileCoreStream().newFile("/tmp").delete());
    }
    @Test
    public void f45() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("tm").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertTrue(pr_.getFileCoreStream().newFile("/tm").delete());
        assertFalse(pr_.getFileCoreStream().newFile("/tm").delete());
    }
    @Test
    public void f46() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertTrue(pr_.getFileCoreStream().newFile("txt").renameTo(pr_.getFileCoreStream().newFile("txt2")));
        assertEq("content",pr_.getStreams().getTextFact().contentsOfFile("/tmp/txt2",new DefaultUniformingString(),7));
        assertFalse(pr_.getFileCoreStream().newFile("txt").exists());
        assertTrue(pr_.getFileCoreStream().newFile("txt2").exists());
    }
    @Test
    public void f47() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertFalse(pr_.getFileCoreStream().newFile("txt").renameTo(pr_.getFileCoreStream().newFile("%")));
        assertEq("content",pr_.getStreams().getTextFact().contentsOfFile("/tmp/txt",new DefaultUniformingString(),7));
        assertTrue(pr_.getFileCoreStream().newFile("txt").exists());
    }
    @Test
    public void f48() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertFalse(pr_.getFileCoreStream().newFile("txt").renameTo(pr_.getFileCoreStream().newFile("txt")));
        assertEq("content",pr_.getStreams().getTextFact().contentsOfFile("/tmp/txt",new DefaultUniformingString(),7));
        assertTrue(pr_.getFileCoreStream().newFile("txt").exists());
    }
    @Test
    public void f49() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertFalse(pr_.getFileCoreStream().newFile("txt2").renameTo(pr_.getFileCoreStream().newFile("txt3")));
    }
    @Test
    public void f50() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","content",true);
        assertEq("txt",pr_.getFileCoreStream().newFile("/tmp/txt").getName());
        assertEq("/tmp",pr_.getFileCoreStream().newFile("/tmp/txt").getParent());
        assertEq(7,pr_.getFileCoreStream().newFile("/tmp/txt").length());
        assertEq(15,pr_.getFileCoreStream().newFile("/tmp/txt").lastModified());
        assertFalse(pr_.getFileCoreStream().newFile("/tmp/txt").isDirectory());
        assertTrue(pr_.getFileCoreStream().newFile("/tmp").isDirectory());
        assertEq(0,pr_.getFileCoreStream().newFile("/tmp/txt2").length());
        assertEq(0,pr_.getFileCoreStream().newFile("/tmp/txt2").lastModified());
        assertFalse(pr_.getFileCoreStream().newFile("/tmp/txt2").isDirectory());
    }
    @Test
    public void f51() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"/");
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getFileCoreStream().newFile("tmp/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2/sub").mkdirs();
        pr_.getStreams().getTextFact().write("/tmp/txt","content",true);
        pr_.getStreams().getTextFact().write("/tmp2/txt2","content",true);
        pr_.getStreams().getTextFact().write("/tmp/sub/txt3","content",true);
        pr_.getStreams().getTextFact().write("/tmp2/sub/txt4","content",true);
        pr_.getStreams().getTextFact().write("/txt5","content",true);
        pr_.getStreams().getTextFact().write("/txt6","content",true);
        String[] l1_ = pr_.getFileCoreStream().newFile("/").list();
        assertEq(4, l1_.length);
        assertEq("tmp", l1_[0]);
        assertEq("tmp2", l1_[1]);
        assertEq("txt5", l1_[2]);
        assertEq("txt6", l1_[3]);
        String[] l2_ = pr_.getFileCoreStream().newFile("/tmp").list();
        assertEq(2, l2_.length);
        assertEq("sub", l2_[0]);
        assertEq("txt", l2_[1]);
        String[] l3_ = pr_.getFileCoreStream().newFile("/tmp2").list();
        assertEq(2, l3_.length);
        assertEq("sub", l3_[0]);
        assertEq("txt2", l3_[1]);
        assertNull(pr_.getFileCoreStream().newFile("/txt5").list());
    }
    @Test
    public void f52() {
        MockProgramInfosSample pr_ = new MockProgramInfosSample("","",0.75,5,wrapLongs(1,2,7,6),true,"c:/","d:/");
        pr_.getFileCoreStream().newFile("c:/tmp").mkdirs();
        pr_.getFileCoreStream().newFile("d:/tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("c:/txt5","content",true);
        pr_.getStreams().getTextFact().write("d:/txt6","content",true);
        String[] l1_ = pr_.getFileCoreStream().newFile("c:/").list();
        assertEq(2, l1_.length);
        assertEq("tmp", l1_[0]);
        assertEq("txt5", l1_[1]);
        String[] l2_ = pr_.getFileCoreStream().newFile("d:/").list();
        assertEq(2, l2_.length);
        assertEq("tmp2", l2_[0]);
        assertEq("txt6", l2_[1]);
    }
}
