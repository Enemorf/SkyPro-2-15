package exceptions;

public class IndexTooBigException extends RuntimeException
{
    public IndexTooBigException(String message)
    {
        super(message);
    }
}
