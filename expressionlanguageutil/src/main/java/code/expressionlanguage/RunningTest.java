package code.expressionlanguage;

import code.expressionlanguage.options.Options;
import code.stream.StreamTextFile;
import code.stream.StreamZipFile;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;

public final class RunningTest implements Runnable {
    private String fileConfOrContent;
    private ProgressingTests progressingTests;
    private boolean file;

    private RunningTest() {
    }
    public static RunningTest newFromFile(String _fileConf, ProgressingTests _progressingTests) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        return r_;
    }

    public static RunningTest newFromContent(String _fileConf, ProgressingTests _progressingTests) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        return r_;
    }
    @Override
    public void run() {
        String content_ = "";
        if (file) {
            content_ = StreamTextFile.contentsOfFile(fileConfOrContent);
            if (content_ == null) {
                return;
            }
        } else {
            content_ = fileConfOrContent;
        }
        launchByConfContent(content_,progressingTests);
    }

    public static void launchByConfContent(String _content, ProgressingTests _progressingTests) {
        StringList lines_ = StringList.splitStrings(_content, "\n", "\r\n");
        StringList linesFiles_ = new StringList();
        for (String s: lines_) {
            if (s.trim().isEmpty()) {
                continue;
            }
            linesFiles_.add(s.trim());
        }
        if (linesFiles_.size() < 2) {
            return;
        }
        String archive_ = linesFiles_.first();
        String lg_ = linesFiles_.get(1);
        StringMap<String> zipFiles_ = getFiles(archive_);
        ExecutingOptions exec_ = new ExecutingOptions();
        setupOptionals(2, exec_,linesFiles_);
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(lg_,opt_,exec_,zipFiles_,_progressingTests);
    }

    public static StringMap<String> getFiles(String _archiveOrFolder) {
        StringMap<String> zipFiles_ = new StringMap<String>();
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                if (new File(f).isDirectory()) {
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),contentOfFile_);
            }
        } else {
            StringMap<byte[]> zip_ =  StreamZipFile.zippedBinaryFiles(_archiveOrFolder);
            if (zip_ == null) {
                return zipFiles_;
            }
            for (EntryCust<String,byte[]> e: zip_.entryList()) {
                String key_ = e.getKey();
                if (key_.endsWith("/")) {
                    continue;
                }
                String dec_ = StringList.decode(e.getValue());
                if (dec_ == null) {
                    continue;
                }
                zipFiles_.addEntry(key_,dec_);
            }
        }
        return zipFiles_;
    }
    public static void setupOptionals(int _from, ExecutingOptions _exec, StringList _lines) {
        for (String l: _lines.mid(_from)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    _exec.setLogFolder(output_.substring(0,lastSep_));
                    _exec.setMainThread(output_.substring(lastSep_+1));
                }
            }
            if (l.startsWith("cover=")) {
                _exec.setCovering(true);
                String output_ = l.substring("cover=".length());
                if (!output_.isEmpty()) {
                    _exec.setCoverFolder(output_);
                }
            }
            if (l.startsWith("src=")) {
                _exec.setCovering(true);
                String output_ = l.substring("src=".length());
                if (!output_.isEmpty()) {
                    if (output_.endsWith("/")) {
                        output_ = output_.substring(0,output_.length()-1);
                    }
                    _exec.setSrcFolder(output_);
                }
            }
            if (l.startsWith("tabWidth=")) {
                String output_ = l.substring("tabWidth=".length());
                int t_ = Numbers.parseInt(output_);
                if (t_ > 0) {
                    _exec.setTabWidth(t_);
                }
            }
            if (l.startsWith("invokeDirect=")) {
                _exec.setInvokeDirect(true);
            }
        }
    }
}
