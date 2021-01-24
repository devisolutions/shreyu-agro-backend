package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = -4305799091645042137L;

    @Id
    private Long code;

    private String name;

    private Double weightInGms;

    private Double price;

    private Double sGst;

    private Double cGst;

}
