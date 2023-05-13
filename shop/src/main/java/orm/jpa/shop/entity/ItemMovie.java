package orm.jpa.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class ItemMovie extends Item {

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "ACTOR")
    private String actor;

}
