package pactogramma.pactogramma.DataBaseContact;

public class Contacts {
    private int id;
    private String contact;

    public Contacts(int id, String contact) {
        this.id = id;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
