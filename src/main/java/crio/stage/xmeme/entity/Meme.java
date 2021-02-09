package crio.stage.xmeme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "memes")
public class Meme {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "url")
    String url;
    @Column(name = "caption")
    String caption;

    public Meme(String name, String caption, String url, int id) {
        this.name = name;
        this.caption = caption;
        this.url = url;
        this.id = id;
    }

    public Meme() {
    }
}
