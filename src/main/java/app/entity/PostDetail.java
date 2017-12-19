package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_details")
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "sequence")
    private int sequence;

    @Column(name = "file")
    private String file;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="content_type_id")
    private ContentType contentType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private app.entity.Post post;

    public PostDetail() {

    }

    public PostDetail(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public app.entity.Post getPost() {
        return post;
    }

    public void setPost(app.entity.Post post) {
        this.post = post;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getImage() {
        return file;
    }

    public void setImage(String image) {
        this.file = image;
    }

    @Override
    public String toString() {
        return "PostDetail{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sequence=" + sequence +
                ", image='" + file + '\'' +
                ", contentType=" + contentType +
                ", post=" + post +
                '}';
    }
}