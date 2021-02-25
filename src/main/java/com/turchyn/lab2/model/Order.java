package com.turchyn.lab2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    @JsonIgnoreProperties(value = {"order_tb", "hibernateLazyInitializer"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name ="tour_id"), name = "tour_id")
    @JsonIgnoreProperties(value = {"order_tb", "hibernateLazyInitializer"})
    private Tour tour;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date")
    private Date orderDate;

}
