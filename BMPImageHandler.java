public class BMPImageHandler {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Uso: BMPImageHandler -core|-rotate|-resize <imagen.bmp>");
            return;
        }

        String option = args[0];
        String fileName = args[1];

        switch (option) {
            case "-core":
                BmpHandlerCore.separateColors(fileName);
                BmpHandlerCore.generateSepia(fileName);
                break;
            case "-rotate":
                BmpHandlerRotator.rotateImageHorizontal(fileName);
                // Llamar a otras rotaciones según sea necesario
                break;
            case "-resize":
                // Implementar las funcionalidades de redimensionar
                break;
            case "-all":
                BmpHandlerCore.separateColors(fileName);
                BmpHandlerCore.generateSepia(fileName);
                BmpHandlerRotator.rotateImageHorizontal(fileName);
                // Ejecutar todas las operaciones
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}