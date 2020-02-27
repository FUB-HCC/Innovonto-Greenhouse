# Innovonto Greenhouse Backend

## Vocabulary

### Challenge

### Ideation Session
An Ideation Session captures events that

How the ideation session looks like to the
User is defined in the **IdeationSessionConfiguration**
Which is part of a **Challenge**


### Participation
A participation is intented to capture the details of doing crowdsourcing:
Each time a crowd-worker starts to work on a challenge, a participation will be generated
A participation includes: The tutorial, The IdeationSession and the Survey.



## Session Handling


    @RequestMapping(..)
    public void fooMethod(HttpSession session) {
        session.setAttribute(Constants.FOO, new Foo());
        //...
        Foo foo = (Foo) session.getAttribute(Constants.FOO);
    }
   
   