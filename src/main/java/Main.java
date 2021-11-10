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
        emailList = MailReader.emailParser("src/emails/mails-20.txt");
        MailManager manager = new MailManager(emailList);
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
                    int id;
                    System.out.print("Ingrese el id del mail que desea borrar: ");
                    id = sc.nextInt();
                    manager.deleteMail(id);
                    break;
                case 3:
                    manager.getDateTree().print();
                    System.out.println("Los mails en orden son: ");
                      Email[] array = manager.getSortedByDate();
                      for(int i = 0; i < array.length; i++){
                          System.out.println(array[i].toString());
                      }
                    break;
                case 4:
                    System.out.println("Ingrese la fecha inicial: (Formato: DDMMAAAA)");
                    int aux = sc.nextInt();
                    Date init = new Date();
                    Date end =  new Date();
                    try{
                        init = Date.parseDate(aux);
                    } catch (Exception e){
                        System.out.println("Fecha invalida.");
                        break;
                    }
                    System.out.println("Ingrese la fecha final: (Formato: DDMMAAAA)");
                    aux = sc.nextInt();
                    try{
                        end = Date.parseDate(aux);
                    } catch(Exception e){
                        System.out.println("Fecha invalida.");
                        break;
                    }

                    end.setHour(11);
                    end.setHour(59);
                    Email[] array2 = manager.getSortedByDate(init, end);
                    System.out.println("Los mails en ese rango, ordenados por fechas son: ");
                    for(int i = 0; i < array2.length; i++){
                        if(array2[i] != null){
                            System.out.println(array2[i].toString());
                        } else {
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("Todos los mail ordenados por remitente: ");
                    manager.getFromTree().print();
                    break;

                case 6:
                case 7:
                    Email[] a = manager.getByQuery("mbaldi");
                    for(int i = 0; i < a.length; i++){
                        System.out.println(a[i]);
                    }
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
