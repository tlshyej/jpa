package orm.jpa.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ALBUM")
public class ItemAlbum extends Item{

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "ETC")
    private String etc;

}
