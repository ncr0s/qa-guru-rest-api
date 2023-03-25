package guru.qa.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class PutUserResponseBodyModel extends UserRequestBodyModel {
    String updatedAt;
}
