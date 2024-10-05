public class BmpHandlerRotator {

    public static void rotateImageHorizontal(String inputFileName) throws IOException {
        FileInputStream fis = new FileInputStream(inputFileName);
        FileOutputStream fos = new FileOutputStream("Image-hrotation.bmp");

        byte[] header = new byte[54];
        fis.read(header); // Leer y copiar el header
        fos.write(header);

        int width = ...;
        int height = ...;
        
        byte[] pixels = new byte[width * height * 3];
        fis.read(pixels);

        // Invertir horizontalmente
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                int indexLeft = (y * width + x) * 3;
                int indexRight = (y * width + (width - x - 1)) * 3;

                // Intercambiar píxeles
                for (int i = 0; i < 3; i++) {
                    byte temp = pixels[indexLeft + i];
                    pixels[indexLeft + i] = pixels[indexRight + i];
                    pixels[indexRight + i] = temp;
                }
            }
        }

        fos.write(pixels);
        fos.close();
        fis.close();
    }
}