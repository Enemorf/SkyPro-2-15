package main.exertions;

public class ItemNotFoundException extends RuntimeException
{
    public ItemNotFoundException(String message)
    {
        super(message);
    }
}
