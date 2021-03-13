package net.javaguides.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillItemsDto implements Serializable {

    private static final long serialVersionUID = -2443167237783938439L;

    private Long productCode;

    private Long productQuantity;

    private Double weightInGms;

    private Double price;

    private Double sGst;

    private Double cGst;

    private String hsnCode;

    private String name;

    private Long productOrder;

    private Long itemsPerBox;
}
