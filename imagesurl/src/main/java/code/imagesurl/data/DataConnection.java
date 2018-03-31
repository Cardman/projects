package code.imagesurl.data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import code.imagesurl.ConverterBufferedImageAdv;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;

class DataConnection extends URLConnection {

    private static final StringList AVAILABLE_FORMATS = new StringList("png","jpg","bmp","gif","svg");
    private static final String SUFFIX = ";base64,";
    private static final String BASE_64 = "data:image/png;base64,";

    public DataConnection(URL _u) {
        super(_u);
        setDefaultUseCaches(false);
        useCaches = false;
    }

    @Override
    public void connect() {
        connected = true;
    }

    @Override
    public InputStream getInputStream() {
        String data_ = url.toString();
        String skippedPrefix_ = data_.substring(ConverterBufferedImageAdv.getDataImage().length());
        for (String f: AVAILABLE_FORMATS) {
            if (skippedPrefix_.startsWith(f)) {
                data_ = skippedPrefix_.substring(f.length()+SUFFIX.length());
                byte[] bytes_ = BaseSixtyFourUtil.parseBaseSixtyFourBinary(data_);
                return new ByteArrayInputStream(bytes_);
            }
        }
        data_ = data_.substring(BASE_64.length());
        byte[] bytes_ = BaseSixtyFourUtil.parseBaseSixtyFourBinary(data_);
        return new ByteArrayInputStream(bytes_);
    }
}
