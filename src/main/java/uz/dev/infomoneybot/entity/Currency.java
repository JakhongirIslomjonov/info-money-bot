package uz.dev.infomoneybot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dev.infomoneybot.enums.CurrencyType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    @JsonProperty("Ccy")
    private CurrencyType code;

    @JsonProperty("CcyNm_UZ")
    private String ccynmUz;

    @JsonProperty("Rate")
    private Double rate;

    @JsonProperty("Diff")
    private Double diff;

    @JsonProperty("Nominal")
    private Integer nominal;

    @JsonProperty("Date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

}