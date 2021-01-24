package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.BillDto;
import net.javaguides.springboot.dto.BillItemsDto;
import net.javaguides.springboot.model.Bill;
import net.javaguides.springboot.model.BillItems;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @GetMapping
    public List<BillDto> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream().map(this::formBillDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BillDto getBillByBillId(@PathVariable("id") final Long id) {
        return formBillDto(billRepository.findById(id).orElse(null));
    }

    private BillDto formBillDto(final Bill bill) {
        if (Objects.nonNull(bill)) {
            List<BillItemsDto> billItems = bill.getBillItems().stream()
                    .map(eachBillItem -> BillItemsDto.builder()
                            .productCode(eachBillItem.getProduct().getCode())
                            .productQuantity(eachBillItem.getQty())
                            .weightInGms(eachBillItem.getProduct().getWeightInGms())
                            .price(eachBillItem.getProduct().getPrice())
                            .cGst(eachBillItem.getProduct().getCGst())
                            .sGst(eachBillItem.getProduct().getSGst())
                            .build())
                    .collect(Collectors.toList());
            return BillDto.builder()
                    .billId(bill.getId())
                    .dateOfBill(bill.getDateOfBill())
                    .buyerName(bill.getBuyerName())
                    .buyerAddress(bill.getBuyerAddress())
                    .buyerMobileNumber(bill.getBuyerMobileNumber())
                    .buyerEmail(bill.getBuyerEmail())
                    .buyerGSTNumber(bill.getBuyerGSTNumber())
                    .discountRate(bill.getDiscountRate())
                    .billAmount(bill.getBillAmount())
                    .finalBillAmount(bill.getFinalBillAmount())
                    .totalBillTax(bill.getTotalBillTax())
                    .totalTaxRate(bill.getTotalTaxRate())
                    .billItems(billItems)
                    .build();
        } else {
            return null;
        }
    }

    @PostMapping
    public Bill createBill(@RequestBody BillDto billDto) {
        return billRepository.save(Bill.builder()
                .dateOfBill(billDto.getDateOfBill())
                .buyerName(billDto.getBuyerName())
                .buyerAddress(billDto.getBuyerAddress())
                .buyerMobileNumber(billDto.getBuyerMobileNumber())
                .buyerEmail(billDto.getBuyerEmail())
                .buyerGSTNumber(billDto.getBuyerGSTNumber())
                .discountRate(billDto.getDiscountRate())
                .billAmount(billDto.getBillAmount())
                .finalBillAmount(billDto.getFinalBillAmount())
                .totalBillTax(billDto.getTotalBillTax())
                .totalTaxRate(billDto.getTotalTaxRate())
                .billItems(formBillItems(billDto.getBillItems()))
                .build());
    }

    /**
     * This method is used to convert BillItemsDto to BillItems.
     *
     * @param billItemsDtos list of BillItemsDto.
     * @return list of BillItems.
     */
    private Set<BillItems> formBillItems(final List<BillItemsDto> billItemsDtos) {
        if (!billItemsDtos.isEmpty()) {
            return billItemsDtos.stream()
                    .map(billItemsDto -> BillItems.builder()
                            .product(Product.builder().code(billItemsDto.getProductCode()).build())
                            .qty(billItemsDto.getProductQuantity())
                            .build())
                    .collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }
}
