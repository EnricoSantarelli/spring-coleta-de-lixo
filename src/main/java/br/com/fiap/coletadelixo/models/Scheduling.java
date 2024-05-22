package br.com.fiap.coletadelixo.models;

import br.com.fiap.coletadelixo.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_SCHEDULING_SEQ")
    @SequenceGenerator(name = "TBL_SCHEDULING_SEQ", sequenceName = "TBL_SCHEDULING_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Long timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
