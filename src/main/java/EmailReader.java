package main.java;

import java.io.*;

public class EmailReader {

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("src/emails/mails-20.txt");
        BufferedReader br = new BufferedReader(fr);
        Email email = new Email();

        for (int i = 0; i < 6; i++) {

            switch (i){
                case 0:
                    br.readLine();
                    break;
                case 1:
                    email.date = br.readLine().substring(6);
                    System.out.println(email.date);
                    break;
                case 2:
                    email.from = br.readLine().substring(6);
                    System.out.println(email.from);
                    break;
                case 3:
                    email.to = br.readLine().substring(4);
                    System.out.println(email.to);
                    break;
                case 4:
                    email.subject = br.readLine().substring(9);
                    System.out.println(email.subject);
                    break;
                case 5:
                    String index;
                    while((index = br.readLine()) != null) {
                        if(index.contains("-.-.-:-.-.-")){
                            break;
                        }
                        email.content += index;
                    }
                    email.content = email.content.substring(4);
                    System.out.println("\n" + email.content);
                    break;
            }

        }
        //return email;

    }

}
