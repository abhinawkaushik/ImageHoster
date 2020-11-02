package ImageHoster.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Comments")
public class Comments {
    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String comment;

    // Write the annotation for many to many between images and tags where they are mapped by tags field in the images table
    //The 'tags' table is mapped to 'images' table with Many:Many mapping
    //One image can have multiple categories/tags and there can be multiple images under one category/tag
    //FetchType is LAZY
    //Note that no column will be generated for this attribute in the database instead a new table will be created
    //Since the mapping is Many to Many, a new table will be generated containing the two columns both referencing to the primary key of both the tables ('images', 'tags')
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Image> images;

    public Comments() {
    }

    public Comments(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
