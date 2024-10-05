public class BmpHandlerCore {

    public static void separateColors(String inputFileName) throws IOException {
        FileInputStream fis = new FileInputStream(inputFileName);
        FileOutputStream fosRed = new FileOutputStream("Image-red.bmp");
        FileOutputStream fosGreen = new FileOutputStream("Image-green.bmp");
        FileOutputStream fosBlue = new FileOutputStream("Image-blue.bmp");
        
        byte[] header = new byte[54];
        fis.read(header); // Leer y copiar el header a cada archivo de salida
        
        fosRed.write(header);
        fosGreen.write(header);
        fosBlue.write(header);
        
        // Obtener las dimensiones de la imagen (igual que el paso anterior)
        int width = ...; // lee el ancho de los píxeles
        int height = ...; // lee el alto de los píxeles
        
        byte[] pixels = new byte[width * height * 3];
        fis.read(pixels);
        
        for (int i = 0; i < pixels.length; i += 3) {
            // Rojo: deja el canal rojo, elimina verde y azul
            fosRed.write(new byte[]{pixels[i], 0, 0});
            // Verde: deja el canal verde, elimina rojo y azul
            fosGreen.write(new byte[]{0, pixels[i + 1], 0});
            // Azul: deja el canal azul, elimina rojo y verde
            fosBlue.write(new byte[]{0, 0, pixels[i + 2]});
        }
        
        fosRed.close();
        fosGreen.close();
        fosBlue.close();
        fis.close();
    }
}

public class BmpHandlerCore {

    public static void generateSepia(String inputFileName) throws IOException {
        FileInputStream fis = new FileInputStream(inputFileName);
        FileOutputStream fos = new FileOutputStream("Image-sepia.bmp");

        byte[] header = new byte[54];
        fis.read(header); // Leer y copiar el header a la nueva imagen sepia
        fos.write(header);

        int width = ...; // lee el ancho de los píxeles
        int height = ...; // lee el alto de los píxeles
        
        byte[] pixels = new byte[width * height * 3];
        fis.read(pixels);

        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i] & 0xFF;
            int g = pixels[i + 1] & 0xFF;
            int b = pixels[i + 2] & 0xFF;
            
            // Aplicar fórmula sepia
            int tr = (int)(0.393 * r + 0.769 * g + 0.189 * b);
            int tg = (int)(0.349 * r + 0.686 * g + 0.168 * b);
            int tb = (int)(0.272 * r + 0.534 * g + 0.131 * b);
            
            // Limitar valores a 255
            tr = Math.min(255, tr);
            tg = Math.min(255, tg);
            tb = Math.min(255, tb);
            
            fos.write(new byte[]{(byte)tr, (byte)tg, (byte)tb});
        }

        fos.close();
        fis.close();
    }
}