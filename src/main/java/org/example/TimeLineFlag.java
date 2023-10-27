package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLineFlag {
    private boolean startTime;
    private boolean endTime;
    public boolean isStartAndEndTime() {
        return startTime && endTime;
    }
}
