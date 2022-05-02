package code.vi.sys.impl.deps;

import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.stream.AbsClipStream;
import code.vi.sys.impl.ProgramInfos;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.ByteArrayInputStream;

public abstract class ProgramInfosDeps extends ProgramInfos {
    protected ProgramInfosDeps(AbstractGraphicStringListGenerator _graphicStringListGenerator, AbstractGraphicComboBoxGenerator _graphicComboBoxGenerator, AbstractAdvGraphicListGenerator _graphicListGenerator) {
        super(_graphicStringListGenerator, _graphicComboBoxGenerator, _graphicListGenerator);
    }

    @Override
    public AbsClipStream openMp3(byte[] _file) {
        ByteArrayInputStream bis_ = new ByteArrayInputStream(_file);
        try {
            Bitstream bitstream_ = new Bitstream(bis_);
            Header header_ = bitstream_.readFrame();
            double millis_ = header_.total_ms(_file.length);
            long ratio_ = (long) header_.ms_per_frame();
            long micros_ = (long)millis_;
            micros_ *= 1000;
            AdvancedPlayer player_ = ClipStreamMp3.player(_file);
            return ret(micros_,ratio_, player_,_file);
        } catch (Exception e) {
            return null;
        }
    }

    private ClipStreamMp3 ret(long _micros, long _ratio, AdvancedPlayer _pl, byte[] _bytes) {
        close(_pl);
        return new ClipStreamMp3(_bytes, getThreadFactory(), _micros,_ratio);
    }

    private void close(AdvancedPlayer _pl) {
        _pl.close();
    }
}
