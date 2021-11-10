package main.java;

import main.java.structures.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class MailManager {

    private LinkedList<Email> auxList;

    private final AvlTree<Long, Email> idTree = new AvlTree<>();
    private final AvlTree<Date, Email> dateTree = new AvlTree<>();
    private final AvlTree<String, Email> fromTree = new AvlTree<>();


    public MailManager(LinkedList<Email> aux){
        this.auxList = aux;
        loadData();
    }

    public void loadData(){

        for (int i = 0; i < auxList.getSize(); i++) {

            dateTree.insert(auxList.get(i).getDate(), auxList.get(i));
            idTree.insert(auxList.get(i).getId(), auxList.get(i));
            fromTree.insert(auxList.get(i).getFrom(), auxList.get(i));

        }

    }

    /**
     * Agrega un mail al gestor
     *
     * @param m mail a agregar
     */
    public void addMail(Email m) throws Exception {
        auxList.add(m);
        dateTree.insert(m.getDate(), m);
        idTree.insert(m.getId(), m);
    }

    /**
     * Elimina un mail del gestor
     *
     * @param id identificador del mail a borrar
     */
    public void deleteMail(long id) throws Exception {
        Email indexEmail = idTree.get(id);
        auxList.delete((int) (id-1));
        idTree.delete(id);
        dateTree.delete(indexEmail.getDate());
    }

    /**
     * Devuelve una lista de mails ordenados por fecha
     *
     * @return lista de mails ordenados
     */
    public Email[] getSortedByDate() {
        return getSortedByDate(Date.getMin(), Date.getMax());
    }

    /**
     * Devuelve una lista de mails oredenados por fecha que estan en el rango
     * desde - hasta
     *
     * @param init Fecha desde donde buscar
     * @param end Fecha hasta donde buscar
     * @return lista de mails ord-enados
     */
    public Email[] getSortedByDate(Date init, Date end) {
        Email[] array = new Email[dateTree.getSize()];
        Queue<Email> emailQueue = dateTree.getIOQueue(init, end);
        Object[] o = emailQueue.toArray();

        return toArray(o, dateTree.getSize());
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
    public Email[] getByQuery(String query) throws Exception {

        HashMap<String, LinkedList<Email>> hashMap = new HashMap<>();
        String[] aux;
        for(int i = 0; i < auxList.getSize(); i++){

            aux = auxList.get(i).getContent().split(" ");

            // Carga de el contenido en el HashMap
            for(int j = 0; j < aux.length; j++){
                try{
                    hashMap.put(aux[j], new LinkedList<Email>());
                    hashMap.get(aux[j]).add(auxList.get(i));
                } catch (Exception e){
                    hashMap.get(aux[j]).add(auxList.get(i));
                }
            }
        }
        Object[] o = hashMap.get(query).toArray();

        return toArray(o, hashMap.get(query).getSize());
    }



    public Email emailGenerator(){

        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter minute = DateTimeFormatter.ofPattern("mm");
        LocalDateTime now = LocalDateTime.now();
        Scanner sc = new Scanner(System.in);
        main.java.Date date = new Date();

        Email email = new Email();
        System.out.println("\nVamos a generar un email, por favor complete los datos a continuacion: \n");
        email.setTo("librebase@grulic.org.ar");

        System.out.print("Ingrese el email del remitente: ");
        email.setFrom(sc.nextLine());

        //Seteamos fecha del mail
        date.setDay(Integer.parseInt(day.format(now)));
        date.setYear(Integer.parseInt(year.format(now)));
        date.setMonth(Integer.parseInt(month.format(now)));
        date.setHour(Integer.parseInt(hour.format(now)));
        date.setMinute(Integer.parseInt(minute.format(now)));
        email.setDate(date);

        System.out.print("Ingrese el asunto del mail: ");
        email.setSubject(sc.nextLine());
        System.out.print("Ingrese el contenido del mail: ");
        email.setContent(sc.nextLine());

        Long newId = auxList.get(auxList.getSize() - 1).getId() + 1; // Seteamos el ID del mail +1 al ultimo id
        email.setId(newId);

        return email;

    }

    public AvlTree getDateTree(){
        return dateTree;
    }

    public AvlTree<String, Email> getFromTree() {
        return fromTree;
    }

    public AvlTree getIdTree(){
        return idTree;
    }


    public void printIdTree() {
        idTree.print();
    }

    public void printDateTree() {
        dateTree.print();
    }

    private Email[] toArray(Object[] o, int size) {
        Email[] array = new Email[size];
        for(int i = 0; i < o.length; i++){
            array[i] = (Email) o[i];
        }
        return array;
    }
    private Email[] toArray(Object[] o){
        return toArray(o, o.length);
    }

}
