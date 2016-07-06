package fk.sp.sa.event;

import fk.sp.sa.event.input.InputEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private InputEvent inputEvent;
}
