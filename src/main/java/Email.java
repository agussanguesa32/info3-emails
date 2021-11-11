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

    public Email() {

    }


    public Email clone(Email email) {
        return new Email(email.getId(), email.getFrom(), email.getTo(), email.getDate(), email.getSubject(), email.getContent());
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

    public void addSubject(String subject) {
        this.subject += subject;
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

    public void addContent(String content) {
        this.content += content;
    }

    public Date parseDate(String aux) {

        Date date = new Date();

        int year = Integer.valueOf(aux.substring(0, 4));
        int month = Integer.valueOf(aux.substring(5, 7));
        int day = Integer.valueOf(aux.substring(8, 10));
        int hour = Integer.valueOf(aux.substring(11, 13));
        int minute = Integer.valueOf(aux.substring(14, 16));

        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);
        date.setHour(hour);
        date.setMinute(minute);

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

    public static void shortToString(Email[] a) {
        for (int i = 0; i < a.length; i++) {

            if (a[i] != null) {
                System.out.println("**********");
                System.out.println(a[i].getFrom() + ": ");
                System.out.println("id: " + a[i].getId());
                System.out.println("to: " + a[i].getTo());
                System.out.println("subject: " + a[i].getSubject());
            }
        }
    }
}
