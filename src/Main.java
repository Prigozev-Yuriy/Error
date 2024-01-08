import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import java.util.InputMismatchException;

import java.util.List;

import java.util.Scanner;



public class Main {



    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в произвольном порядке через пробел: Фамилия Имя Отчество дата_рождения(в формате дд.мм.гггг) номер_телефона пол(в формате m или f)");

        String input = scanner.nextLine();

        scanner.close();


        String[] data = input.split(" ");

        int expectedCount = 6; // ожидаемое количество данных

        if (data.length < expectedCount) {

            throw new IllegalArgumentException("Ошибка: количество данных меньше требуемого");

        }

        else if (data.length > expectedCount) {

            throw new IllegalArgumentException("Ошибка: количество данных больше требуемого");

        }

        String surname = data[0];

        String name = data[1];

        String patronymic = data[2];

        String dateStr = data[3];

        String number = data[4];

        char gender = data[5].charAt(0);




        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date date;

        try {
            // Проверка формата фамилии, имени и отчества

            if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty()) {

                throw new IllegalArgumentException("Ошибка: некорректные фамилия, имя или отчество");

            }

            date = format.parse(dateStr);

        } catch (ParseException e) {

            throw new IllegalArgumentException("Ошибка: неверный формат даты");

        }

        // Проверка формата даты

        if (!dateStr.matches("\\d{2}.\\d{2}.\\d{4}")) {

            throw new IllegalArgumentException("Ошибка: неверный формат даты");

        }



// Проверка формата номера

        Long.parseLong(number);

//        if (number <= 0) {
//
//            throw new IllegalArgumentException("Ошибка: неверный формат номера");
//
//        }



// Проверка формата пола

        if (gender != 'f' && gender != 'm') {

            throw new IllegalArgumentException("Ошибка: неверный формат пола");

        }






        String filename = surname + ".txt";

        File file = new File(filename);

        FileWriter writer = null;

        try {
            if(!file.exists()){
                file.createNewFile();
                writer = new FileWriter(file);
            } else {
                writer = new FileWriter(file, true);
                writer.write(System.lineSeparator());
            }

            writer.write("<" + surname + ">");

            writer.write("<" + name + ">");

            writer.write("<" + patronymic + ">");

            writer.write("<" + format.format(date) + ">");

            writer.write("<" + number + ">");

            writer.write("<" + gender + ">");

            writer.close();

            System.out.println("Данные успешно записаны в файл " + surname);

        } catch (NumberFormatException e) {

            System.out.println("Ошибка: некорректный формат номера");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println("Ошибка при записи в файл");



        }

    }
}