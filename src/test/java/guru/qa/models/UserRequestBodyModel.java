package guru.qa.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestBodyModel {
    private String name;
    private String job;
}
