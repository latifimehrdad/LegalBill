package ir.negra.legalbill.models;

public class MD_Login {

    private String NationalCode;
    private String AccountNumber;

    public MD_Login() {
    }


    public String getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }
}
