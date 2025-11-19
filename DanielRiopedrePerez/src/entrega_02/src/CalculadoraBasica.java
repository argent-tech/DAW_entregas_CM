package entrega_02.src;

public class CalculadoraBasica {
    static final MyScanner sc = new MyScanner();

    static void main() throws DivisionEntreCeroException {

        // requests two numbers from user
        double input = pedirNumero("Input number 1.");
        double input_2 = pedirNumero("Input number 2.");



        System.out.println("---Resume---");
        System.out.println("Sum: " + sumar(input, input_2));
        System.out.println("Substraction: " + restar(input, input_2));
        System.out.println("Multiplication: " + multiplicar(input, input_2));
        try {
            System.out.println("Division: " + dividir(input, input_2));
        } catch (DivisionEntreCeroException e) {
            System.out.println("Division: " + e.getMessage());
        }

    }

    static double pedirNumero(String mensaje) {
        boolean exit_condition = false;
        double output = 0.0;

        System.out.println(mensaje);

        // requests a number from user between 0 and 100
        // repeats itself if number from user isn't between 0 and 100

        while (!exit_condition) {

            double new_input = sc.pedirDecimal("Type in a number between 0 and 100.");

            if (new_input < 0 || new_input > 100.0) {
                System.out.println("Input must be between 0 and 100");

            }
            else {
                exit_condition = true;
                output = new_input;
            }
        }

        return output;
    }

    // method that returns division and throws an exception if the second number is 0 to prevent division by 0
    static double dividir(double number_1, double number_2) throws DivisionEntreCeroException {


        // prevents division by 0 and throws exception

        // the main method receives the exception and throws an error message

        if (number_2 <= 0.0) {
            throw(new DivisionEntreCeroException("Can't divide by 0."));
        }

        return number_1 / number_2;

    }

    // method with self-explanatory name
    static double sumar(double number_1, double number_2) {
        return number_1 + number_2;
    }

    // method with self-explanatory name
    static double restar(double number_1, double number_2) {
        return number_1 - number_2;
    }

    // method with self-explanatory name
    static double multiplicar(double number_1, double number_2) {
        return number_1 * number_2;
    }


}