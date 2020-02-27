# Innovonto Greenhouse Backend

## Vocabulary

### Challenge

### Participation

### Ideation Session
An Ideation Session captures events that

How the ideation session looks like to the
User is defined in the **IdeationSessionConfiguration**
Which is part of a **Challenge**



## Session Handling


    @RequestMapping(..)
    public void fooMethod(HttpSession session) {
        session.setAttribute(Constants.FOO, new Foo());
        //...
        Foo foo = (Foo) session.getAttribute(Constants.FOO);
    }
   
   