package de.fuberlin.innovonto.brainstormingapp;

import de.fuberlin.innovonto.brainstormingapp.backend.challenge.Challenge;
import de.fuberlin.innovonto.brainstormingapp.backend.challenge.ChallengeConfig;
import de.fuberlin.innovonto.brainstormingapp.backend.challenge.ChallengeRepository;
import de.fuberlin.innovonto.brainstormingapp.backend.inspiration.Inspiration;
import de.fuberlin.innovonto.brainstormingapp.backend.faq.Faq;
import de.fuberlin.innovonto.brainstormingapp.backend.faq.FaqEntry;
import de.fuberlin.innovonto.brainstormingapp.backend.faq.FaqRepository;
import de.fuberlin.innovonto.brainstormingapp.backend.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static de.fuberlin.innovonto.brainstormingapp.backend.challenge.ChallengeState.draft;
import static de.fuberlin.innovonto.brainstormingapp.backend.challenge.ChallengeState.published;
import static java.time.LocalDateTime.parse;

@SpringBootApplication
public class BrainstormingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrainstormingApplication.class, args);
    }


    private Faq createDefaultFaq() {
        final Faq defaultFaq = new Faq();
        defaultFaq.addEntry(new FaqEntry("what happens to my ideas?", "Hier findet der Nutzer eine exakte Antwort auf seine frage"));
        return defaultFaq;
    }

    @Bean
    @Autowired
    public CommandLineRunner createTestData(ChallengeRepository challengeRepository,
                                            FaqRepository faqRepository,
                                            IdeaRepository ideaRepository,
                                            IdeatorRepository ideatorRepository,
                                            IdeationSessionRepository ideationSessionRepository) {
        return (args) -> {
            //Setup Users:

            //Setup FAQ:
            faqRepository.save(createDefaultFaq());

            //Setup Challenges:
            Challenge challenge1 = new Challenge();
            challenge1.setState(draft);
            challenge1.setTitle("Challenge 1");
            //TODO Understand the use of timezones in LocalDateTime / NonlocalDateTime
            challenge1.setCreated(parse("2018-11-18T10:34:11"));
            challenge1.setLastModified(parse("2018-12-18T10:34:11"));
            challenge1 = challengeRepository.save(challenge1);

            //Challenge 2 is directly created by "data.sql" to ensure that the UUID is always the same.
            /*
            Challenge challenge2 = new Challenge();
            challenge2.setState(published);
            challenge2.setTitle("Challenge 2");
            challenge2.setCreated(parse("2018-12-10T09:30:46"));
            challenge2.setLastModified(parse("2018-12-10T09:30:46"));
            challenge2.setPublished(parse("2018-12-18T10:34:11"));
            challenge2.setNumberIdeas(12);
            challenge2.setNumberSessions(2);
            challenge2.setSessionLink("https://bit.ly/2ErydFz");

            final ChallengeConfig challenge2Config = new ChallengeConfig();
            challenge2Config.setTimePerSessionInMs(20 * 60);
            challenge2Config.setTaskDescription("Task Description Challenge 2");
            challenge2Config.setChallengeDescription("Challenge Description Challenge 2");
            challenge2.setConfig(challenge2Config);

            challenge2 = challengeRepository.save(challenge2);
            */


            Challenge challenge3 = new Challenge();
            challenge3.setState(published);
            challenge3.setTitle("Challenge 3");
            challenge3.setCreated(parse("2018-09-20T10:40:08"));
            challenge3.setLastModified(parse("2018-12-18T10:34:11"));
            challenge3.setPublished(parse("2018-09-28T10:35:40"));
            challenge3.setClosed(parse("2018-10-04T10:40:25"));
            challenge3.setNumberIdeas(158);
            challenge3.setNumberSessions(12);

            final ChallengeConfig challenge3Config = new ChallengeConfig();
            challenge3Config.setTimePerSessionInMs(20 * 60);
            challenge3Config.setTaskDescription("Task Description Challenge 3");
            challenge3Config.setChallengeDescription("Challenge Description Challenge 3");

            Set<Inspiration> challenge3Inspirations = new HashSet<>();
            challenge3Inspirations.add(new Inspiration("Methodical Description Text","/demo/speculative-item-01.jpg","Challenge Text"));
            challenge3Config.setInspirations(challenge3Inspirations);
            challenge3.setConfig(challenge3Config);

            challenge3 = challengeRepository.save(challenge3);

            //Ideators
            Ideator ideator1 = new Ideator("MXFTUV");
            ideator1 = ideatorRepository.save(ideator1);

            //IdeationSession
            /*
                        challenge2.setNumberIdeas(12);
            challenge2.setNumberSessions(2);
             */
            IdeationSession session1 = new IdeationSession();
            session1.setChallengeId(challenge3.getId());
            session1.setIdeatorId(ideator1.getId());
            session1.setState(IdeationSessionState.submitted);
            session1.setStartTime(null);
            session1.setEndTime(null);
            session1.setLastActive(null);
            session1 = ideationSessionRepository.save(session1);
            //TODO session->events

            //TODO create ideas
            Idea idea1 = new Idea("This is one of the super awesome ideas");
            idea1.setChallengeId(challenge3.getId());
            idea1.setCreatorId(ideator1.getId());
            idea1.setIdeationSessionId(session1.getId());
            idea1 = ideaRepository.save(idea1);

            //Session -> Ideas
            List<Idea> ideas = new ArrayList<>();
            ideas.add(idea1);
            session1.setIdeas(ideas);
            ideationSessionRepository.save(session1);
        };
    }
}
