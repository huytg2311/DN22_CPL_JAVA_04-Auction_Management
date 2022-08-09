package java4.auction_management.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
