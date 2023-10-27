package org.example;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTime {
    int startTime;
    int endTime;
    List<String> names;

    @Override
    public String toString() {
        return startTime + " " + endTime + " " + names.toString();
    }
}
