import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BmpReader {
    public static void main(String[] args) throws IOException {
        String fileName = "Image.bmp";
        FileInputStream fis = new FileInputStream(fileName);
        
        byte[] header = new byte[54]; // Los primeros 54 bytes del header del archivo BMP
        fis.read(header);
        
        // Leer ancho y alto de la imagen desde el header BMP
        int width = ((header[21] & 0xFF) << 24) | ((header[20] & 0xFF) << 16) |
                    ((header[19] & 0xFF) << 8) | (header[18] & 0xFF);
        int height = ((header[25] & 0xFF) << 24) | ((header[24] & 0xFF) << 16) |
                     ((header[23] & 0xFF) << 8) | (header[22] & 0xFF);
        
        System.out.println("Width: " + width + ", Height: " + height);
        
        // Leer los píxeles (3 bytes por cada píxel: RGB)
        byte[] pixels = new byte[width * height * 3];
        fis.read(pixels);
        fis.close();
    }
}