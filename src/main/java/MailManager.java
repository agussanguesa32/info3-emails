package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MailManager {
    /**
     * Agrega un mail al gestor
     *
     * @param m mail a agregar
     */
    public void addMail(Email m) {

    }

    /**
     * Elimina un mail del gestor
     *
     * @param id identificador del mail a borrar
     */
    public void deleteMail(long id) {

    }

    /**
     * Devuelve una lista de mails ordenados por fecha
     *
     * @return lista de mails ordenados
     */
    public Email[] getSortedByDate() {
        return new Email[0];
    }

    /**
     * Devuelve una lista de mails oredenados por fecha que estan en el rango
     * desde - hasta
     *
     * @param desde Fecha desde donde buscar
     * @param hasta Fecha hasta donde buscar
     * @return lista de mails ord-enados
     */
    public Email[] getSortedByDate(String desde, String hasta) {
        return new Email[0];
    }

    /**
     * Devuelve una lista de mails ordenados por Remitente
     *
     * @return lista de mails ordenados
     */
    public Email[] getSortedByFrom() {
        return new Email[0];
    }

    /**
     * Devuelve una lista de mails de un determinado remitente
     *
     * @param from String con direccion del remitente
     * @return lista de mails del remitente
     */
    public Email[] getByFrom(String from) {
        return new Email[0];
    }

    /**
     * Devuelve una lista de mails que contengan las palabras de 'query'
     * en su asunto o en su contenido
     *
     * @param query String con palabra/s a buscar
     * @return lista de mails que contienen dicha/s palabra/s
     */
    public Email[] getByQuery(String query) {
        return new Email[0];
    }

    public Email emailGenerator(){

        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        Scanner sc = new Scanner(System.in);
        main.java.Date date = new Date();

        Email email = new Email();
        System.out.println("\nVamos a generar un email, por favor complete los datos a continuacion: \n");
        email.setFrom("agussanguesa@gmail.com");

        System.out.print("Ingrese el email del destinatario: ");
        email.setTo(sc.nextLine());

        //Seteamos fecha del mail
        date.setDay(Integer.valueOf(day.format(now)));
        date.setYear(Integer.valueOf(year.format(now)));
        date.setMonth(Integer.valueOf(month.format(now)));
        email.setDate(date);

        System.out.print("Ingrese el asunto del mail: ");
        email.setSubject(sc.nextLine());
        System.out.print("Ingrese el contenido del mail: ");
        email.setContent(sc.nextLine());
        return email;

    }
}
