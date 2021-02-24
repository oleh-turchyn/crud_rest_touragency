package com.turchyn.lab2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_tb")
public class Order {
    @EmbeddedId
    private OrderId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @MapsId("tourId")
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date")
    private Date orderDate;

    public Order(User user, Tour tour, Date orderDate) {
        this.id = new OrderId(user.getId(), tour.getId());
        this.user = user;
        this.tour = tour;
        this.orderDate = orderDate;
    }
}
