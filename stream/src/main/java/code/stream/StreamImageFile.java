package code.stream;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StreamImageFile {

//    private static final String SEPARATEUR = "/";
    private static final String EMPTY_STRING = "";
    private static final String PNG_EXT = "png";
    private static final String RETURN_LINE = "\n";
    private static final String COMMA = ",";
//    private static final String COMMA_END = ",$";
    private static final int NB_BYTES = 1024;

    private StreamImageFile() {
    }
    public static void zipFiles(String _zipFileName,
            StringMap<String> _files,
            StringMap<BufferedImage> _imagesFiles) {
        byte[] buffer_ = new byte[NB_BYTES];

        try{

            FileOutputStream fos_ = new FileOutputStream(_zipFileName);
            ZipOutputStream zos_ = new ZipOutputStream(fos_);
            for (String n: _files.getKeys()) {
                String file_ = _files.getVal( n);
                ZipEntry ze_= new ZipEntry(n);
                zos_.putNextEntry(ze_);

                InputStream in_ = new ByteArrayInputStream(file_.getBytes());

                int len_;
                while (true) {
                    len_ = in_.read(buffer_);
                    if (len_ <= 0) {
                        break;
                    }
                    zos_.write(buffer_, 0, len_);
                }

                in_.close();
            }
            for (String n: _imagesFiles.getKeys()) {
                BufferedImage file_ = _imagesFiles.getVal( n);
                ZipEntry ze_ = new ZipEntry(n);
                zos_.putNextEntry(ze_);
                ImageIO.write(file_, PNG_EXT, zos_);
            }
            zos_.closeEntry();
            //remember close it
            zos_.close();
        }catch(IOException _0){
            _0.printStackTrace();
        }
    }

    public static BufferedImage zippedImageToBuffer(
            String _zipFileName,
            String _fileName, boolean _txt) {
        if (_txt) {
            ZipFile zipFile_ = null;
            try {
                zipFile_ = new ZipFile(_zipFileName);
                Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

                String file_ = EMPTY_STRING;
                while(entries_.hasMoreElements()){
                    ZipEntry entry_ = entries_.nextElement();
                    if (!StringList.quickEq(entry_.getName(),_fileName)) {
                        continue;
                    }
                    InputStream stream_ = zipFile_.getInputStream(entry_);
                    try {
                        BufferedReader br_ = new BufferedReader(new InputStreamReader(stream_));
                        while (true) {
                            String line_ = br_.readLine();
                            if (line_ == null) {
                                break;
                            }
                            file_ += line_ +RETURN_LINE;
                        }
                    } catch (RuntimeException _0) {
                        _0.printStackTrace();
                    } catch (IOException _0) {
                        _0.printStackTrace();
                    } finally {
                        stream_.close();
                    }
                    break;
                }
                return imageTxtToBuffer(file_);
            } catch (RuntimeException _0) {
                _0.printStackTrace();
                return null;
            } catch (IOException _0) {
                _0.printStackTrace();
                return null;
            } finally {
                if (zipFile_ != null) {
                    try {
                        zipFile_.close();
                    } catch (RuntimeException _0) {
                    } catch (IOException _0) {
                    }
                }
            }
        }
        ZipFile zipFile_ = null;
        try {
            zipFile_ = new ZipFile(_zipFileName);
            Enumeration<? extends ZipEntry> entries_ = zipFile_.entries();

            while(entries_.hasMoreElements()){
                ZipEntry entry_ = entries_.nextElement();
                if (!StringList.quickEq(entry_.getName(),_fileName)) {
                    continue;
                }
                try {
                    InputStream stream_ = zipFile_.getInputStream(entry_);
                    return ImageIO.read(stream_);
                } catch (RuntimeException _0) {
                    _0.printStackTrace();
                } catch (IOException _0) {
                    _0.printStackTrace();
                }
            }
            return null;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return null;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        } finally {
            if (zipFile_ != null) {
                try {
                    zipFile_.close();
                } catch (RuntimeException _0) {
                } catch (IOException _0) {
                }
            }
        }
    }
    public static BufferedImage imageTxtToBuffer(String _imgTxt) {
        try {
            StringList infos_ = StringList.splitStrings(_imgTxt, COMMA);
            int w_ = Integer.parseInt(infos_.first());
            int h_ = (infos_.size() - 1) / w_;
            BufferedImage image_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
            int n_ = 1;
            for (int i = CustList.FIRST_INDEX;i<h_;i++) {
                for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                    image_.setRGB(j, i, Integer.parseInt(infos_.get(n_)));
                    n_++;
                }
            }
            return image_;
        } catch (RuntimeException _0) {
            return null;
        }

    }
    public static String toString(BufferedImage _img) {
        int w_ = _img.getWidth();
        int h_ = _img.getHeight();
        StringBuilder string_ = new StringBuilder(w_ + COMMA);
        for (int i = CustList.FIRST_INDEX;i<h_;i++) {
            for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                string_.append(_img.getRGB(j, i));
                string_.append(COMMA);
//                string_ += _img.getRGB(j, i) +COMMA;
            }
        }
        string_.deleteCharAt(string_.length() - 1);
//        string_ = string_.replaceAll(COMMA_END, EMPTY_STRING);
        return string_.toString();
    }
    public static String imageToString(String _fileName) {
        StringBuilder string_ = new StringBuilder();
        BufferedImage img_;
        try {
            img_ = ImageIO.read(new File(_fileName));
            string_.append(img_.getWidth());
            string_.append(COMMA);
//            string_ = Integer.toString(img_.getWidth()) + COMMA;
            int h_ = img_.getHeight();
            int w_ = img_.getWidth();
            for (int i = CustList.FIRST_INDEX; i < h_; i++){
                for (int j = CustList.FIRST_INDEX; j < w_; j++){
                    string_.append(img_.getRGB(j, i));
                    string_.append(COMMA);
//                    string_ += img_.getRGB(j, i) + COMMA;
                }
            }
            string_.deleteCharAt(string_.length() - 1);
//            string_ = string_.replaceAll(COMMA_END, EMPTY_STRING);
        } catch (IOException _0) {
            _0.printStackTrace();
        }
        return string_.toString();
    }
}
