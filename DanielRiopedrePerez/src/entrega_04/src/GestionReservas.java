package entrega_04.src;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GestionReservas {

    private static final MyScanner sc = new MyScanner();

    private static Reserva[] reservas = new Reserva[20];
    private static int contador = 0;

    public static void main(String[] args) {
        boolean exit;
        do {
            exit = false;
            int opcion = sc.pedirNumero("=====Reservas=====" +
                    "\n1. Añadir Reserva" +
                    "\n2. Mostrar Reservas" +
                    "\n3. Filtrar por año" +
                    "\n4. Filtrar por mes" +
                    "\n5. Filtrar por rango" +
                    "\n6. Salir" +
                    "\nIngrese la opción");
            switch (opcion){
                case 1:
                    addReserva();
                    break;
                case 2:
                    mostrarReservas();
                    break;
                case 3:
                    filtroYear();
                    break;
                case 4:
                    filtroMonth();
                    break;
                case 5:
                    filtroRangoYears();
                    break;
                case 6:
                    System.out.println("Saliendo ....");
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida!");
                    break;
            }

        } while (!exit);
    }

    public static void addReserva(){
        boolean correcto;
        LocalDate fecha_creacion = null;
        do {
            correcto = true;
            try {
                String fecha = sc.pideTexto("Ingrese la fecha de creación (YYYY-MM-DD): ");
                fecha_creacion = LocalDate.parse(fecha);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                correcto = false;
            }
        } while (!correcto);

        String nombreReserva = sc.pideTexto("Ingrese el nombre de la reserva: ");
        String nombreClienteReserva = sc.pideTexto("Ingrese el nombre del cliente de la reserva: ");
        TipoReserva tipo_registro = pedirEnum(TipoReserva.class, "Seleccione el tipo de reserva: ");

        if (contador < reservas.length) {
            reservas[contador] = new Reserva(fecha_creacion, nombreReserva, nombreClienteReserva, tipo_registro);
            contador++;
        } else {
            System.out.println("La lista de registros ya esta llena.");
        }
    }


    public static <E extends Enum<E>> E pedirEnum(
            Class<E> tipoEnum,
            String mensaje) {

        E[] valores = tipoEnum.getEnumConstants();
        int opcion;

        do {
            System.out.println(mensaje);
            for (int i = 0; i < valores.length; i++) {
                System.out.println((i + 1) + ". " + valores[i]);
            }
            opcion = sc.pedirNumero("Elige una opción: ");

        } while (opcion < 1 || opcion > valores.length);

        return valores[opcion - 1];
    }

    public static void mostrarReservas() {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null) {
                System.out.println(reservas[i]);
            }
        }
    }

    public static void filtroYear() {
        int year = sc.pedirNumero("Ingrese el año para filtrar: ");
        Reserva[] returnedReservas = obtenerReservasPorYear(year);
        printArrayReservas(returnedReservas);

    }

    public static void filtroMonth() {
        int month = sc.pedirNumero("Ingrese el mes para filtrar: ");
        if (month > 1 && month < 12) {
            Reserva[] returnedReservas = obtenerReservasPorMonth(month);
            printArrayReservas(returnedReservas);
        } else {
            System.out.println("Not a valid month.");
        }


    }

    public static void filtroRangoYears() {
        int min_year = sc.pedirNumero("Ingrese el año mínimo: ");
        int max_year = sc.pedirNumero("Ingrese el año máximo: ");
        Reserva[] returnedReservas = obtenerReservasEnRangoYears(min_year, max_year);
        printArrayReservas(returnedReservas);

    }

    public static Reserva[] obtenerReservasPorYear(int year) {
        Reserva[] reservasToReturn = new Reserva[reservas.length];
        for (int i = 0; i < reservas.length ; i++) {
            if (reservas[i] != null) {
                if (reservas[i].getFechaReserva().getYear() == year) {
                    reservasToReturn[getClosestEmptyArrayIndexReserva(reservasToReturn)] = reservas[i];
                }
            } else {
                // rest of array contains nothing
                break;
            }
        }
        return reservasToReturn;
    }

    public static Reserva[] obtenerReservasPorMonth(int month) {
        Reserva[] reservasToReturn = new Reserva[reservas.length];
        for (int i = 0; i < reservas.length ; i++) {
            if (reservas[i] != null) {
                if (reservas[i].getFechaReserva().getMonthValue() == month) {
                    reservasToReturn[getClosestEmptyArrayIndexReserva(reservasToReturn)] = reservas[i];
                }
            } else {
                // rest of array contains nothing
                break;
            }
        }
        return reservasToReturn;
    }

    public static Reserva[] obtenerReservasEnRangoYears(int min_year, int max_year) {
        Reserva[] reservasToReturn = new Reserva[reservas.length];
        for (int i = 0; i < reservas.length ; i++) {
            if (reservas[i] != null) {
                int year = reservas[i].getFechaReserva().getYear();
                if (min_year < year && year < max_year) {
                    reservasToReturn[getClosestEmptyArrayIndexReserva(reservasToReturn)] = reservas[i];
                }
            } else{
                // rest of array contains nothing
                break;
            }
        }
        return reservasToReturn;
    }

    public static void printArrayReservas(Reserva[] arrayReservas) {
        if (arrayReservas[0] == null) {
            System.out.println("No results.");
        } else {
            for (int i = 0; i < arrayReservas.length; i++) {
                if (arrayReservas[i] != null) {
                    System.out.println(arrayReservas[i]);
                }
            }
        }

    }

    // used only in the "obtenerReserva" functions
    // provided array length will thus never exceed that of reservas
    public static int getClosestEmptyArrayIndexReserva(Reserva[] arrayReservas) {
        for (int i = 0; i < arrayReservas.length; i++) {
            if (arrayReservas[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
