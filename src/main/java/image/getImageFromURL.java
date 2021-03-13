package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class getImageFromURL {
    private final URL url;
    private BufferedImage imageStream;

    public getImageFromURL(URL url) {
        this.url = url;
        getImage();
    }

    /**
     * 构造方法，获取图片流，用于后续数据获取
     */
    void getImage() {

        BufferedImage imageStream = null;
        try {
            //从URL中读取图片获取流
            imageStream = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imageStream = imageStream;
    }

    /**
     * 获取图片宽
     */
    public int getWidth() {

        return imageStream.getWidth();
    }

    /**
     * 获取图片高
     */
    public int getHeight() {

        return imageStream.getHeight();
    }

    /**
     * 获取图片大小,返回大小MB
     */
    public int getFileSize() {

        int len = imageStream.getData().getDataBuffer().getSize();
        return len / (int) (Math.pow(1024, 2));
    }
}

