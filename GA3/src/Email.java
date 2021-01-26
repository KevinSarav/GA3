public class Email {
    protected String username, domain, extension;
    public int returnCode(){
        return 0;
    }
    public Email(){
        username = domain = extension = null;
    }
    public Email(String username, String domain, String extension)
    {
        this.username = username;
        this.domain = domain;
        this.extension = extension;
    }

    public String toString()
    {
        return(String.format(username+"@"+domain+"."+extension));
    }

    public String getType()
    {
        return "Email";
    }
}