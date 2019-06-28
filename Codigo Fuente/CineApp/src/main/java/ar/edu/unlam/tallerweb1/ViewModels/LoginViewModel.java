package ar.edu.unlam.tallerweb1.ViewModels;

public class LoginViewModel {
    private String emailOrNick;
    private String password;
    private Boolean rememberMe;

    public LoginViewModel(String emailOrNick, String password, Boolean rememberMe) {
        this.emailOrNick = emailOrNick;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public LoginViewModel() {
    }

    public String getEmailOrNick() {
        return emailOrNick;
    }

    public void setEmailOrNick(String emailOrNick) {
        this.emailOrNick = emailOrNick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
