import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks() {
        var salir = false;
        var consola = new Scanner(System.in);

//        Se crea la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("""
                --------------------------
                **** Maquina de Snacks ***
                --------------------------""");
        Snacks.mostrarSnacks(); //Muestra el inventario de snacks disponibles
        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            } finally {
                System.out.println(); //Imprime salto de linea en cada iteración
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.println("""
                *************************
                Menu:
                1. Comprar snack
                2. Mostrar snack
                3. Agregar nuevo snack
                4. Salir
                Selecciona una opción: \s
                *************************""");
//        Se lee y retorna la opcion seleccionada
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos) {
        var salir = false;
        switch (opcion) {
            case 1 -> comprarSnack(consola, productos);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos) {
        System.out.print("Que snack quieres comprar (id)? ");
        var idsnack = Integer.parseInt(consola.nextLine());

//        Se válida que el snack exista en la lista de snacks
        var snackEncontrado = false;
        for (var snack : Snacks.getSnacks()) {
            if (idsnack == snack.getIdSnack()) {
//            Se agrega el snack a la lista de productos
                productos.add(snack);
                System.out.println("Snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idsnack);
        }
    }
}
