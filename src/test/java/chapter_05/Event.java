package chapter_05;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENTS")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_DATE")
    private Date date;

    public Long getId() {return id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}
}
