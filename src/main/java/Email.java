package main.java;

import main.java.Date;
/**
 * Clase con los datos de un email. Puede tener mas propiedades o metodos
 */
class Email {

    private long id;
    private String from;    // Remitente del mail
    private String to;      // destinatario del mail
    private Date date;    // Fecha de envio
    private String subject; // Asunto del mail
    private String content; // Contenido del mail.

    public Email(long id, String from, String to, Date date, String subject, String content) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.content = content;
    }
    public Email(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void addContent(String content){
        this.content += content;
    }

    public Date parseDate(String aux){

        Date date = new Date();

        int year = Integer.valueOf(aux.substring(0,4));
        int month = Integer.valueOf(aux.substring(5,7));
        int day = Integer.valueOf(aux.substring(8,10));

        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);

        return date;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
