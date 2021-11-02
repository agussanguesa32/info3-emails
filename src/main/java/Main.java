package main.java;
import main.java.structures.LinkedList;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        LinkedList<Email> emailList = new LinkedList<>();
        emailList = MailReader.emailParser("src/emails/mails-2.txt");
        MailManager manager = new MailManager();
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;


        do {
            System.out.println("Bienvenido!");
            System.out.print("Seleccione la opcion que desea realizar: \n" +
                    "1) Agregar Mail\n" +
                    "2) Borrar Mail\n" +
                    "3) Mostrar mails ordenados por fecha\n" +
                    "4) Filtrar mails por rango de fechas\n" +
                    "5) Mostrar mails ordenados por remitente\n" +
                    "6) Buscar mails por remitente\n" +
                    "7) Buscar mails por palabras o asunto\n" +
                    "8) Salir\n" +
                    "Su opcion: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    manager.addMail(manager.emailGenerator());
                    break;
                case 2:
                    int id = 0;
                    manager.deleteMail(id);
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("La opcion elegida no es valida");
            }
        }while(!salir);

        System.out.println("\nHasta luego!");


    }

}
