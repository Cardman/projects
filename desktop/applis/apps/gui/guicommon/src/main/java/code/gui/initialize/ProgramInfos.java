package code.gui.initialize;

import code.gui.GroupFrame;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.random.AdvancedGenerator;
import code.util.CustList;
import code.util.StringMap;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class ProgramInfos implements AbstractProgramInfos {
    private final CustList<GroupFrame> frames = new CustList<GroupFrame>();
    private final StringMap<AtomicInteger> counts = new StringMap<AtomicInteger>();
    private final AbstractGenerator generator;
    private final String tmpUserFolder;
    private final String homePath;
    public ProgramInfos() {
        tmpUserFolder = ConstFiles.getTmpUserFolderSl();
        homePath = ConstFiles.getHomePath();
        generator = new AdvancedGenerator();
        try {
            /*Permet d avoir une application graphique comme si c etait Windows*/
            String className_ = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(className_);
        } catch (Exception e) {
            //skip exception
        }
    }

    public String getHomePath() {
        return homePath;
    }

    public String getTmpUserFolder() {
        return tmpUserFolder;
    }

    public CustList<GroupFrame> getFrames() {
        return frames;
    }

    public StringMap<AtomicInteger> getCounts() {
        return counts;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }
}
