package model;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class InputStreamConverter {
    public static String toBase64(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);

        BufferedImage bufferedImage = ImageIO.read(inputStream);
        ImageIO.write(bufferedImage, "png", bos);
        bos.flush();
        bos.close();

        byte[] bImage = baos.toByteArray();

        // バイト配列→BASE64へ変換する
        byte[] encoded = Base64.getEncoder().encode(bImage);
        return new String(encoded);
    }
}
