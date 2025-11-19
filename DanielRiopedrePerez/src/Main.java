

class Main {
    private static final MyScanner sc = new MyScanner();
    static final double per_hour_tariff = 7.5;
    static final double max_worked_hours = 70.0;

    public static void main(String[] args) throws InvalidWorkedHoursError{
        int remaining_attempts = 3;

        String employee_name = sc.pedirSoloTexto("Input employee name.");
        double worked_hours;
        double salary;

        try {

            worked_hours = get_worked_hours(sc);

            if (worked_hours == 0.0) {
                throw new InvalidWorkedHoursError("Invalid worked hours. Exiting.");
            } else {
                salary = get_salary(worked_hours);
            }


        } catch (InvalidWorkedHoursError Exception) {
            System.out.println(Exception.getMessage());
            return;
        }

        show_resume(employee_name,  worked_hours, salary);




    }

    public static double get_salary(double worked_hours) {
            return worked_hours * per_hour_tariff;
    }

    public static double get_worked_hours(MyScanner sc) throws InvalidWorkedHours {

        int remaining_attempts = 3;

        do {

            try {
                double worked_hours = sc.pedirDecimal("Input worked hours.");

                if (worked_hours <= 0.0 || worked_hours > max_worked_hours) {
                    throw new InvalidWorkedHours("Worked hours must be between 0.0 and 70.0");
                } else {
                    return worked_hours;
                }


            } catch (InvalidWorkedHours Exception) {
                System.out.println(Exception.getMessage());
                remaining_attempts--;

            }




        }
        while (remaining_attempts > 0);

        return 0.0;
    }

    public static void show_resume(String employee_name, double worked_hours, double salary) {
        System.out.printf("Employee name: %s%n", employee_name);
        System.out.printf("Worked hours: %s%n", worked_hours);
        System.out.printf("Tariff: %s%n", per_hour_tariff);
        System.out.printf("Salary: %s%n", salary);
    }
}