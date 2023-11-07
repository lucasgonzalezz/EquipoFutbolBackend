package net.ausiasmarch.equipo_futbol.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataGenerationHelper {

    private static final String[] aNames = {
            "Lionel Messi", "Cristiano Ronaldo", "Neymar Jr.", "Kylian Mbappé", "Robert Lewandowski",
            "Mohamed Salah", "Sergio Ramos", "Virgil van Dijk", "Luka Modrić", "Sadio Mané",
            "Karim Benzema", "Kevin De Bruyne", "Erling Haaland", "Harry Kane", "Antoine Griezmann", "Paul Pogba"
    };

    private static final String[] aCities = {
            "Madrid", "Barcelona", "Valencia", "Seville", "Zaragoza",
            "Málaga", "Murcia", "Palma de Mallorca", "Las Palmas de Gran Canaria", "Bilbao",
            "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Hospitalet de Llobregat",
            "Vitoria-Gasteiz", "Badalona", "Cartagena", "Tarragona",
            "Terrassa", "Sabadell", "Jerez de la Frontera", "Elche", "Getafe", "Alcalá de Henares",
            "San Cristóbal de La Laguna", "Castellón de la Plana", "Santander", "Leganes",
            "Alcorcón", "San Sebastian", "Parla", "Cornellà de Llobregat", "Toledo", "Burgos"
    };

    private static final Date[] aDates = {
        parseDate("2013-11-06"), parseDate("2014-05-15"), parseDate("2015-09-23"),
        parseDate("2016-03-10"), parseDate("2017-08-29"), parseDate("2018-02-14"),
        parseDate("2019-06-01"), parseDate("2020-12-07"), parseDate("2021-04-18"),
        parseDate("2022-10-25")
    };

        private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final String[] aStadiumNames = {
            "Camp Nou", "Santiago Bernabéu", "Old Trafford", "Anfield", "Allianz Arena",
            "San Siro", "Emirates Stadium", "Etihad Stadium", "Wembley Stadium", "Stamford Bridge",
            "Parc des Princes", "Signal Iduna Park", "Giuseppe Meazza", "Estadio Azteca", "Maracanã", "Stadio Olimpico"
    };

    private static final String[] aLeagueNames = {
            "La Liga", "Premier League", "Bundesliga", "Serie A", "Ligue 1",
            "Eredivisie", "Primeira Liga", "MLS", "Brasileirão", "Argentine Primera División",
            "Scottish Premiership", "J1 League", "Russian Premier League", "Major League Soccer", "Turkish Süper Lig",
            "Liga MX"
    };

    public static String getRadomName() {
        return aNames[(int) (Math.random() * aNames.length)];
    }

    public static String getRadomCity() {
        return aCities[(int) (Math.random() * aCities.length)];
    }

    public static Date getRandomYear() {
        return aDates[random.nextInt(aDates.length)];
    }

    public static String getRandomStadium() {
        return aStadiumNames[(int) (Math.random() * aStadiumNames.length)];
    }

    public static String getRandomLeague() {
        return aLeagueNames[(int) (Math.random() * aLeagueNames.length)];
    }

    public static String doNormalizeString(String cadena) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String cadenaSinAcentos = cadena;
        for (int i = 0; i < original.length(); i++) {
            cadenaSinAcentos = cadenaSinAcentos.replace(original.charAt(i), ascii.charAt(i));
        }
        return cadenaSinAcentos;
    }

    private static Random random = new Random();

    // Define arrays for different word categories
    private static String[] articles = { "the", "a", "one" };
    private static String[] nouns = { "cat", "dog", "book", "birds", "sun", "sandwich", "friend", "car", "house" };
    private static String[] verbs = { "sleeps", "barks", "reads", "fly", "shines", "run", "ate", "is" };
    private static String[] adverbs = { "loudly", "quickly", "brightly", "slowly", "softly" };
    private static String[] conjunctions = { "and", "but", "or" };
    private static String[] subordinatingConjunctions = { "although", "because", "while", "if", "when", "as", "after",
            "before", "since", "until", "unless", "where", "wherever", "whether", "while", "even if", "even though",
            "once", "provided that", "so that", "than", "though", "in order to", "so that", "that", "unless", "until",
            "when", "whenever", "where", "wherever", "whether", "while" };

    public static String generateSentence() {
        // Randomly decide if it's a simple, compound, or complex sentence
        int sentenceType = random.nextInt(3);
        if (sentenceType == 0) {
            return generateSimpleSentence();
        } else if (sentenceType == 1) {
            return generateCompoundSentence();
        } else {
            return generateComplexSentence();
        }
    }

    public static String generateSimpleSentence() {
        String subject = generateNounPhrase();
        String verb = generateVerbPhrase();
        return subject + " " + verb;
    }

    public static String generateCompoundSentence() {
        String simpleSentence1 = generateSimpleSentence();
        String conjunction = conjunctions[random.nextInt(conjunctions.length)];
        String simpleSentence2 = generateSimpleSentence();
        return simpleSentence1 + " " + conjunction + " " + simpleSentence2;
    }

    public static String generateComplexSentence() {
        String subordinatingConjunction = subordinatingConjunctions[random.nextInt(subordinatingConjunctions.length)];
        String subordinateClause = subordinatingConjunction + " " + generateSimpleSentence();
        String mainClause = generateMainClause();
        return subordinateClause + " " + mainClause;
    }

    public static String generateMainClause() {
        if (random.nextBoolean()) {
            return generateSimpleSentence();
        } else {
            return generateCompoundSentence();
        }
    }

    public static String generateNounPhrase() {
        String article = articles[random.nextInt(articles.length)];
        String noun = nouns[random.nextInt(nouns.length)];
        return article + " " + noun;
    }

    public static String generateVerbPhrase() {
        String verb = verbs[random.nextInt(verbs.length)];
        // Randomly decide if it's just a verb or a verb with an adverb
        if (random.nextBoolean()) {
            String adverb = adverbs[random.nextInt(adverbs.length)];
            return verb + " " + adverb;
        } else {
            return verb;
        }
    }

    public static String getSpeech(int amount) {
        String sentences = "";
        for (int i = 0; i < amount; i++) {
            String sentence = generateSentence();
            sentences += sentence.substring(0, 1).toUpperCase() + sentence.substring(1) + ". ";
        }
        return sentences;
    }

}