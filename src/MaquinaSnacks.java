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
                2. Mostrar ticket
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
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion Inválida:" + opcion);
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

    private static void mostrarTicket(List<Snack> productos){
        var ticket = """
                -----------------------
                *** Ticket de Venta ***
                -----------------------""";
        var total = 0.0;
        for (var producto: productos){
            ticket += "\n\t* " + producto.getNombre() + " -- $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\t Total -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola){
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        Snacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Snack agregado exitosamente");
        Snacks.mostrarSnacks();
    }
}
