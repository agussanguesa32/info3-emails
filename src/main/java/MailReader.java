package main.java;

import java.io.*;

import main.java.structures.LinkedList;

public class MailReader {

    public static LinkedList<Email> emailParser(String route) throws IOException {

        LinkedList<main.java.Email> emailLinkedList = new LinkedList<>();
        FileReader fr = new FileReader(route);
        BufferedReader br = new BufferedReader(fr);
        Email email = new Email();
        String temp;
        Boolean bandera = false;

        do{
            temp = br.readLine();
            if (email.getContent()!=null) {
                emailLinkedList.insert(email);
                bandera = false;
                email.setContent(null);

            }
            if(temp == null) break;

            if (temp.contains("date: ")) {
                email.setDate(email.parseDate(temp.substring(6)));
            } else if (temp.contains("from: ")) {
                email.setFrom(temp.substring(6));
            } else if (temp.contains("to: ")) {
                email.setTo(temp.substring(4));
            } else if (temp.contains("subject: ")) {
                email.setSubject(temp.substring(9));
            } else  {
                while (!bandera && !temp.equals("-.-.-:-.-.-")) {
                        email.addContent(temp);
                        temp = br.readLine();
                        if(temp.equals("-.-.-:-.-.-")){
                            bandera =true;
                            email.setContent(email.getContent().substring(4));
                        }
                    }


            }


        }while(temp!=null);
        LinkedList.printList(emailLinkedList);

        return emailLinkedList;

    }

}
