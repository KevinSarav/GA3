import java.beans.BeanProperty;

public class UniversityEmail extends Email {
    protected String department;
    protected int code;

    public UniversityEmail(String username, String domain, String extension, String department){
        super(username,domain,extension);
        this.department = department;
    }

    @Override
    public int returnCode() {
        switch (department) {
            case "art":
                code = 1;
                break;
            case "chee":
                code = 2;
                break;
            case "chem":
                code = 3;
                break;
            case "coe":
                code = 4;
                break;
            case "cs":
                code = 5;
                break;
            case "egr":
                code = 6;
                break;
            case "polsci":
                code = 7;
                break;
        }
        return code;
    }
    @Override
    public String toString()
    {
        return(String.format(username+"@"+department+"."+domain+"."+extension));

    }

    @Override
    public String getType()
    {
        return "UniversityEmail";
    }
}