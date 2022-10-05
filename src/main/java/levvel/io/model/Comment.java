package levvel.io.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {

    String author;
    String text;

    public Comment(String auth, String txt)
    {
        author = auth;
        text = txt;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getText()
    {
        return text;
    }

}
