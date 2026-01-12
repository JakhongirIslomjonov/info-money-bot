package uz.dev.infomoneybot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    @JsonProperty("Ccy")
    private String ccy;

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
