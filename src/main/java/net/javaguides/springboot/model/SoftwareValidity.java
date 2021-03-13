package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareValidity implements Serializable {

    private static final long serialVersionUID = 5612491571800217406L;

    @Id
    private Long id;

    private LocalDateTime validityExpiryDate;
}
