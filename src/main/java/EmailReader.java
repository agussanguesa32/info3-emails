package main.java;

import java.io.*;
import main.java.Date;

public class EmailReader {

    public static void readEmail() throws IOException {

        FileReader fr = new FileReader("src/emails/mails-1000.txt");
        BufferedReader br = new BufferedReader(fr);
        Email email = new Email();

        for (int i = 0; i < 6; i++) {

            switch (i){
                case 0:
                    br.readLine();
                    break;
                case 1:
                    String aux = br.readLine().substring(6,16);
                    email.setDate(email.parceDate(aux));
                    System.out.println(email.getDate());
                    break;
                case 2:
                    email.setFrom(br.readLine().substring(6));
                    System.out.println(email.getFrom());
                    break;
                case 3:
                    email.setTo(br.readLine().substring(4));
                    System.out.println(email.getTo());
                    break;
                case 4:
                    email.setSubject(br.readLine().substring(9));
                    System.out.println(email.getSubject());
                    break;
                case 5:
                    String index;
                    while((index = br.readLine()) != null) {
                        if(index.contains("-.-.-:-.-.-")){
                            break;
                        }
                        email.addContent(index); ;
                    }
                    email.setContent(email.getContent().substring(4));
                    System.out.println("\n" + email.getContent());
                    break;
            }

        }

    }

}
