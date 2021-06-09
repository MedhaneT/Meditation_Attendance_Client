package miu.edu.Client.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AttendanceRecord {
    private LocalDateTime scanTime;
    private String studentBarCodeId;

}
