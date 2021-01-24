package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {

    private static final long serialVersionUID = -4405799091645042137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfBill;

    private String buyerName;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String buyerAddress;

    private Long buyerMobileNumber;

    private String buyerEmail;

    private String buyerGSTNumber;

    private Double discountRate;

    private Double billAmount;

    private Double finalBillAmount;

    private Double totalBillTax;

    private Double totalTaxRate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Set<BillItems> billItems;
}
