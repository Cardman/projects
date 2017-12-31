package code.stream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StreamImageFile {

    private static final String PNG_EXT = "png";
    private static final String COMMA = ",";
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
        StringBuilder string_ = new StringBuilder(StringList.concatNb(w_, COMMA));
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
        }
        return string_.toString();
    }
}
