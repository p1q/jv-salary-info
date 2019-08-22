package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     *
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     *
     * names:
     * Сергей
     * Андрей
     * София
     *
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     *
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate finalDate = LocalDate.parse(dateTo, dateFormat);
        if (finalDate.isBefore(LocalDate.parse(dateFrom, dateFormat))) {
            return null;
        }

        String salaryInfo = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            LocalDate currentDate = LocalDate.parse(dateFrom, dateFormat);

            while(currentDate.isBefore(finalDate.plusDays(1))) {
                salary += calculateSalary(names[i], data, currentDate.format(dateFormat));
                currentDate = currentDate.plusDays(1);
            }
            salaryInfo += names[i] + " - " + salary + "\n";
        }
        return salaryInfo;
    }

    private int calculateSalary(String name, String[] data, String currentDate) {
        int salary = 0;
        for (String i: data) {
            String[] tmp = i.split(" ");
            if (tmp[0].equals(currentDate) && tmp[1].equals(name)) {
                salary += Integer.parseInt(tmp[2]) * Integer.parseInt(tmp[3]);
            }
        }
        return salary;
    }
}
