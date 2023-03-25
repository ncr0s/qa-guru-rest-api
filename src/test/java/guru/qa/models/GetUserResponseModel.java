package guru.qa.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetUserResponseModel {
    private ResponseData data;
    private Support support;

    @Data
    @Accessors(chain = true)
    public static class ResponseData {
        private Integer id;
        private String email;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("last_name")
        private String lastName;
        private String avatar;
    }

    @Data
    @Accessors(chain = true)
    public static class Support {
        private String url;
        private String text;
    }
}
