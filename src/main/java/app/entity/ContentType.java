package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "content_type")
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content_type")
    private String ContentType;

    @Column(name = "sequence")
    private int Sequence;

    public ContentType() {

    }

    public ContentType(String contentType) {
        ContentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }

    @Override
    public String toString() {
        return "ContentType{" +
                "id=" + id +
                ", ContentType='" + ContentType + '\'' +
                ", Sequence=" + Sequence +
                '}';
    }
}
