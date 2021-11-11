package main.java;
import main.java.structures.LinkedList;

import main.java.structures.Node;

import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Main {

    public static void main(String[] args) throws Exception {


        LinkedList<Email> emailList = new LinkedList<>();
        emailList = MailReader.emailParser("src/emails/mails-1000.txt");
        MailManager manager = new MailManager(emailList);
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        Email[] a;
        String query;

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
                    int id;
                    System.out.print("Ingrese el id del mail que desea borrar: ");
                    id = sc.nextInt();
                    manager.deleteMail(id);
                    break;
                case 3:
                    manager.getDateTree().print();
                    System.out.println("Los mails en orden son: ");
                      a = manager.getSortedByDate();
                      Email.shortToString(a);
                    break;
                case 4:
                    System.out.println("Ingrese la fecha inicial: (Formato: DDMMAAAA)");
                    Date init = new Date();
                    init.setDay(sc.nextInt());
                    init.setMonth(sc.nextInt());
                    init.setYear(sc.nextInt());
                    Date end =  new Date();
                    System.out.println("Ingrese la fecha final: (Formato: DDMMAAAA)");
                    end.setDay(sc.nextInt());
                    end.setMonth(sc.nextInt());
                    end.setYear(sc.nextInt());

                    end.setHour(11);
                    end.setHour(59);
                    a = manager.getSortedByDate(init, end);
                    if(a[0] != null){
                        System.out.println("Los mails en ese rango, ordenados por fechas son: ");
                        Email.shortToString(a);
                    } else{
                        System.out.println("No hay mails en ese rango de fechas.");
                    }

                    break;

                case 5:
                    System.out.println("Todos los mail ordenados por remitente: ");
                    a = manager.getSortedByFrom();
                    Email.shortToString(a);

                    break;

                case 6:
                    System.out.print("Buscar mails por remitente: ");
                    query = sc.next();
                    a = manager.getByFrom(query.toLowerCase());
                    for(int i = 0; i < a.length; i++){
                        System.out.println(a[i].getId());
                    }
                    break;


                case 7:
                    System.out.print("Buscar mails por palabra: ");
                    query = sc.next();
                    a = manager.getByQuery(query.toLowerCase());
                    if(a.length != 0){
                        System.out.println("La palabra aparece en los siguientes mails: ");
                    }
                    Email.shortToString(a);
                    break;
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
