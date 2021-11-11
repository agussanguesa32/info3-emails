package main.java;

import main.java.structures.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class MailManager {

    private LinkedList<Email> auxList;

    private final AvlTree<Long, Email> idTree = new AvlTree<>();
    private final AvlTree<Date, Email> dateTree = new AvlTree<>();
    private final AvlTree<String, LinkedList<Email>> fromTree = new AvlTree<>();
    private final HashMap<String, LinkedList<Email>> queryMap = new HashMap<>();



    public MailManager(LinkedList<Email> aux){
        this.auxList = aux;
        try{
            loadData();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void loadData() throws Exception {

        String[] aux, aux2, aux3;
        String t;
        for (int i = 0; i < auxList.getSize(); i++) {
            //Armado del arbol por fechas
            dateTree.insert(auxList.get(i).getDate(), auxList.get(i));

            //Armado del arbol por id
            idTree.insert(auxList.get(i).getId(), auxList.get(i));

            //Armado del arbol por remitente
            try{
                fromTree.get(auxList.get(i).getFrom()).add(auxList.get(i));
            }catch (Exception e){
                fromTree.insert(auxList.get(i).getFrom(), new LinkedList<>());
                fromTree.get(auxList.get(i).getFrom()).add(auxList.get(i));
            }

            //Armado del hashMap para busqueda por query

            aux3 = auxList.get(i).getContent().split(" ");// Agregamos todo el contenido en un array de strings
            aux2 = auxList.get(i).getSubject().split(" ");
            aux = new String[aux2.length+aux3.length];
            System.arraycopy(aux3, 0, aux, 0, aux3.length);
            System.arraycopy(aux2, 0, aux, aux3.length, aux2.length);

            for(int j = 0; j < aux.length; j++){
                //Si la palabra no esta en la tabla como Key la creamos, si no solamente la agregamos a la lista enlazada
                t = aux[j].trim().toLowerCase();
                if(!t.contains(" ") || !t.contains("*") || !t.contains("\"")){
                    if(queryMap.get(t) == null){
                        queryMap.put(t, new LinkedList<>());
                    } if(!queryMap.get(t).contains(auxList.get(i))){
                        queryMap.get(t).add(auxList.get(i));
                    }
                }
            }

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
        try{
            fromTree.get(m.getFrom()).add(m);
        }catch (Exception e){
            fromTree.insert(m.getFrom(), new LinkedList<>());
            fromTree.get(m.getFrom()).add(m);
        };
        String[] aux = m.getContent().split(" ");   // Agregamos todo el contenido en un array de strings

        for(int j = 0; j < aux.length; j++){
            //Si la palabra no esta en la tabla como Key la creamos, si no solamente la agregamos a la lista enlazada
            if(!aux[j].contains(" ") || !aux[j].contains("*") || !aux[j].contains("\"")){
                if(queryMap.get(aux[j]) == null){
                    queryMap.put(aux[j], new LinkedList<>());
                } if(!queryMap.get(aux[j]).contains(m)){
                    queryMap.get(aux[j]).add(m);
                }
            }
        }

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
        LinkedList<Email> emailQueue = dateTree.getIOQueue(init, end);
        Object[] o = emailQueue.toArray();

        return toArray(o, dateTree.getSize());
    }

    /**
     * Devuelve una lista de mails ordenados por Remitente
     *
     * @return lista de mails ordenados
     */
    public Email[] getSortedByFrom() throws Exception {
        Email[] array = new Email[fromTree.getSize()];
        LinkedList<LinkedList<Email>> emailQueue = fromTree.getIOQueue();
        LinkedList<Email> emailList = new LinkedList<>();
        for(int i = 0; i < emailQueue.getSize(); i++){
            for(int j = 0; j < emailQueue.get(i).getSize(); j++){
                emailList.add(emailQueue.get(i).get(j));
            }

        }
        Object[] o = emailList.toArray();

        return toArray(o);

    }

    /**
     * Devuelve una lista de mails de un determinado remitente
     *
     * @param from String con direccion del remitente
     * @return lista de mails del remitente
     */
    public Email[] getByFrom(String from) {
        if(fromTree.get(from) != null){
            Object[] o = fromTree.get(from).toArray();
            return toArray(o);
        } else{
            return new Email[0];
        }

    }



    public Email[] getByQuery(String query) throws Exception {


        try{
            Object[] o = queryMap.get(query).toArray();
            return toArray(o, queryMap.get(query).getSize());
        }catch(Exception e){
            System.out.println("\nNo se ha encontrado la palabra deseada\n");
        }

        return new Email[0];
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

    public AvlTree<String, LinkedList<Email>> getFromTree() {
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
