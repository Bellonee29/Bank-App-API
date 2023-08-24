package com.Bellonee.javademoapp.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryDto {
    private String accountNumber;
    private LocalDate startDate;
    private LocalDate endDate;
}
