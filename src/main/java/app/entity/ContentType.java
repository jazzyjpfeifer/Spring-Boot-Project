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
    private int sequence;

    public ContentType() {

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
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ContentType{" +
                "id=" + id +
                ", ContentType='" + ContentType + '\'' +
                ", sequence=" + sequence +
                '}';
    }
}
