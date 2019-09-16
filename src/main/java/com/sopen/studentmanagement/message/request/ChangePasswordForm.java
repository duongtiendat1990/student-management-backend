package com.sopen.studentmanagement.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordForm {
  @NotBlank
  @Size(min = 6, max = 40)
  private String oldPassword;

  @NotBlank
  @Size(min = 6, max = 40)
  private String newPassword;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public ChangePasswordForm() {
  }
}
