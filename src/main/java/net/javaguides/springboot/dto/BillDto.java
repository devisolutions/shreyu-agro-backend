package net.javaguides.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDto implements Serializable {

    private static final long serialVersionUID = 8480518598775426422L;

    private Long billId;

    private LocalDateTime dateOfBill;

    private String buyerName;

    private String buyerAddress;

    private Long buyerMobileNumber;

    private String buyerEmail;

    private String buyerGSTNumber;

    private Double discountRate;

    private Double billAmount;

    private Double finalBillAmount;

    private Double totalBillTax;

    private Double totalTaxRate;

    private List<BillItemsDto> billItems;

}
