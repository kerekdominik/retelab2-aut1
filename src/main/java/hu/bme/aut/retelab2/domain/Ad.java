package hu.bme.aut.retelab2.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private Double price;

    private Date lastUpdated;

    private String key;

    @ElementCollection
    private Set<String> tags;

    private LocalDateTime expireTime;


    //Getters & Setters
    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void lastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
