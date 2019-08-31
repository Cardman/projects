package code.expressionlanguage;

import code.expressionlanguage.options.Options;
import code.stream.StreamTextFile;
import code.stream.StreamZipFile;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;

public final class RunningTest implements Runnable {
    private String fileConf;

    public RunningTest(String _fileConf) {
        fileConf = _fileConf;
    }

    @Override
    public void run() {
        String content_ = StreamTextFile.contentsOfFile(fileConf);
        if (content_ == null) {
            return;
        }
        StringList lines_ = StringList.splitStrings(content_, "\n", "\r\n");
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
        StringMap<String> zipFiles_ = new StringMap<String>();
        String lg_ = linesFiles_.get(1);
        if (new File(archive_).isDirectory()) {
            for (String f: StreamTextFile.allSortedFiles(archive_)) {
                if (new File(f).isDirectory()) {
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(archive_.length()+1),contentOfFile_);
            }
        } else {
            StringMap<byte[]> zip_ =  StreamZipFile.zippedBinaryFiles(archive_);
            if (zip_ == null) {
                return;
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
        ExecutingOptions exec_ = new ExecutingOptions();
        for (String l: linesFiles_.mid(2)) {
            if (l.startsWith("log=")) {
                String output_ = l.substring("log=".length());
                int lastSep_ = output_.lastIndexOf('>');
                if (lastSep_ > -1) {
                    exec_.setLogFolder(output_.substring(0,lastSep_));
                    exec_.setMainThread(output_.substring(lastSep_+1));
                }
            }
            if (l.startsWith("cover=")) {
                exec_.setCovering(true);
                String output_ = l.substring("cover=".length());
                if (!output_.isEmpty()) {
                    exec_.setCoverFolder(output_);
                }
            }
        }
        Options opt_ = new Options();
        CustContextFactory.executeDefKw(lg_,opt_,exec_,zipFiles_);
    }
}
