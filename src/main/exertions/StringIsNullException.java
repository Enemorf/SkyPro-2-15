package main.exertions;

public class StringIsNullException extends RuntimeException
{
    public StringIsNullException(String message)
    {
        super(message);
    }
}
